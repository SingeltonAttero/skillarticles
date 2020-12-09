package ru.skillbranch.skillarticles.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Observable
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.skillbranch.skillarticles.core.adapter.ProductDelegate
import ru.skillbranch.skillarticles.core.decor.GridPaddingItemDecoration
import ru.skillbranch.skillarticles.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        ProductDelegate().createAdapter {
            // TODO handle click
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        binding.rvProductGrid.adapter = adapter
        binding.rvProductGrid.addItemDecoration(GridPaddingItemDecoration(17))
        val searchEvent = Observable.create<String> { emitter ->
            binding.searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    emitter.onNext(query ?: "")
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    emitter.onNext(newText ?: "")
                    return false
                }
            })
        }
        viewModel.setSearchEvent(searchEvent)
    }

    private fun renderState(searchState: SearchState) {
        adapter.items = searchState.items
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        binding.rvProductGrid.adapter = null
        _binding = null
        super.onDestroyView()
    }

}
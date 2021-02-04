package ru.skillbranch.skillarticles.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.skillbranch.skillarticles.core.adapter.ProductDelegate
import ru.skillbranch.skillarticles.core.decor.GridPaddingItemDecoration
import ru.skillbranch.skillarticles.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModel()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy {
        ProductDelegate().createAdapter {
            // TODO handle click
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initState()
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        binding.rvProductGrid.adapter = adapter
        binding.rvProductGrid.addItemDecoration(GridPaddingItemDecoration(17))
        val searchEvent = binding.searchInput.queryTextChanges().skipInitialValue().map { it.toString() }
        binding.swipeRefresh.setOnRefreshListener(this)
        viewModel.setSearchEvent(searchEvent)
    }

    private fun renderState(searchState: SearchState) {
        binding.swipeRefresh.isRefreshing = searchState is SearchState.Loading
        binding.emptyHolder.isVisible = searchState is SearchState.Error
        when (searchState) {
            is SearchState.Result -> {
                adapter.items = searchState.items
                adapter.notifyDataSetChanged()
            }
            SearchState.Loading -> {
                // TODO handle loading
            }
            is SearchState.Error -> {
                adapter.items = emptyList()
                adapter.notifyDataSetChanged()
                binding.emptyHolder.text = searchState.errorDescription
            }
        }

    }

    override fun onDestroyView() {
        binding.rvProductGrid.adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onRefresh() {
        binding.swipeRefresh.isRefreshing = false
    }

}
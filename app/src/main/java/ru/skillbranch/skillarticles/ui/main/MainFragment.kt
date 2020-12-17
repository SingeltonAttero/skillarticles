package ru.skillbranch.skillarticles.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.core.adapter.ProductDelegate
import ru.skillbranch.skillarticles.core.decor.GridPaddingItemDecoration
import ru.skillbranch.skillarticles.databinding.MainFragmentBinding
import ru.skillbranch.skillarticles.ui.search.SearchFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()
    private val productAdapter by lazy {
        ProductDelegate().createAdapter {
            // TODO handle click
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::renderState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        binding.rvProductGrid.adapter = productAdapter
        binding.rvProductGrid.addItemDecoration(GridPaddingItemDecoration(17))
        binding.btnRetry.setOnClickListener {
            viewModel.loadDishes()
        }
    }

    private fun renderState(state: MainState) {
        binding.progressProduct.isVisible = state == MainState.Loader
        binding.rvProductGrid.isVisible = state is MainState.Result
        binding.toolbar.isVisible = state is MainState.Result
        binding.tvErrorMessage.isVisible = state is MainState.Error
        binding.btnRetry.isVisible = state is MainState.Error
        if (state is MainState.Result) {
            productAdapter.items = state.productItems
            productAdapter.notifyDataSetChanged()
        } else if (state is MainState.Error) {
            binding.tvErrorMessage.text = state.message
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dishes, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSearchDishes -> {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onDestroyView() {
        binding.rvProductGrid.adapter = null
        (requireActivity() as AppCompatActivity).setSupportActionBar(null)
        _binding = null
        super.onDestroyView()
    }

}
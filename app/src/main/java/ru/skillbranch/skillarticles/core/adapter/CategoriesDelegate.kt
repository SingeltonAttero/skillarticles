package ru.skillbranch.skillarticles.core.adapter

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.skillbranch.skillarticles.databinding.ItemCategoryBinding


class CategoriesDelegate {

    fun createAdapter(itemClick: (CategoryItemState) -> Unit) =
        ListDelegationAdapter(createCategories(itemClick))

    private fun createCategories(itemClick: (CategoryItemState) -> Unit) =
        adapterDelegateViewBinding<CategoryItemState, CategoryItemState, ItemCategoryBinding>({ layoutInflater, parent ->
            ItemCategoryBinding.inflate(layoutInflater, parent, false)
        }) {
            itemView.setOnClickListener { itemClick.invoke(item) }
            bind {
                binding.tvTitle.text = item.title
            }
        }
}
package ru.skillbranch.skillarticles.core.adapter

import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.databinding.ItemProductBinding

class ProductDelegate {

    fun createAdapter(addClick: (ProductItemState) -> Unit) =
        AsyncListDifferDelegationAdapter(ProductDiffUtils, AdapterDelegatesManager(productItem(addClick)))


    private fun productItem(addClick: (ProductItemState) -> Unit) =
        adapterDelegateViewBinding<ProductItemState, ProductItemState, ItemProductBinding>({ layoutInflater, parent ->
            ItemProductBinding.inflate(layoutInflater, parent, false)
        }) {
            binding.btnAddBasket.setOnClickListener { addClick.invoke(item) }
            bind {
                binding.tvPrice.text = item.price
                binding.tvTitle.text = item.title
                binding.ivProductPhoto.load(item.image) {
                    error(R.drawable.img_empty_place_holder)
                    listener(onError = { _, _ ->
                        binding.ivProductPhoto.setPadding(48)
                    }, onStart = {
                        binding.ivProductPhoto.setPadding(0)
                    })
                }
            }
        }

}

object ProductDiffUtils : DiffUtil.ItemCallback<ProductItemState>() {
    override fun areItemsTheSame(oldItem: ProductItemState, newItem: ProductItemState): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItemState, newItem: ProductItemState): Boolean {
        return oldItem == newItem
    }
}
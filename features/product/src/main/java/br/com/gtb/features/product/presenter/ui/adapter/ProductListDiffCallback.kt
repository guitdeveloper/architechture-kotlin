package br.com.gtb.features.product.presenter.ui.adapter

import br.com.gtb.libraries.data.model.Product
import androidx.recyclerview.widget.DiffUtil

internal class ProductListDiffCallback constructor(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].name == newList[newItemPosition].name

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        true
}

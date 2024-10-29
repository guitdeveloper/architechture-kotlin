package br.com.gtb.features.product.presenter.ui.adapter

import android.view.ViewGroup
import br.com.gtb.features.product.databinding.ProductItemBinding
import br.com.gtb.features.product.presenter.ui.viewholder.ProductItemViewHolder
import br.com.gtb.libraries.data.model.Product
import br.com.gtb.libraries.uicore.extensions.getInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

internal class ProductAdapter : RecyclerView.Adapter<ProductItemViewHolder>() {

    private var _dataSource = emptyList<Product>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ProductListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = ProductItemBinding.inflate(parent.getInflater(), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(_dataSource[position])
    }

    override fun getItemCount(): Int =
        _dataSource.size

    fun setDataSource(dataSource: List<Product>) {
        _dataSource = dataSource
        notifyDataSetChanged()
    }
}
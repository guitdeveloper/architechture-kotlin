package br.com.gtb.features.product.presenter.ui.viewholder

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.gtb.features.product.R
import br.com.gtb.features.product.databinding.ProductItemBinding
import br.com.gtb.libraries.data.model.Product
import br.com.gtb.libraries.uicore.extensions.gone
import br.com.gtb.libraries.uicore.extensions.show
import br.com.gtb.libraries.uicore.R as uiCoreLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

internal class ProductItemViewHolder constructor(
    private val binding: ProductItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.DONUT)
    fun bind(item: Product) {
        with(binding) {
            imgMainItem.contentDescription = item.name
            progressImage.show()

            Picasso.get()
                .load(item.imageURL)
                .error(uiCoreLayout.drawable.ic_alert_circle)
                .into(imgMainItem, object : Callback {
                    override fun onSuccess() {
                        progressImage.gone()
                    }

                    override fun onError(e: Exception?) {
                        progressImage.gone()
                    }
                })
        }
    }
}

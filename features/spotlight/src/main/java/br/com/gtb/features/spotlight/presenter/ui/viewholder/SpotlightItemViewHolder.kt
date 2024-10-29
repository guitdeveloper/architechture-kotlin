package br.com.gtb.features.spotlight.presenter.ui.viewholder

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.gtb.features.spotlight.databinding.SpotlightItemBinding
import br.com.gtb.libraries.data.model.Spotlight
import br.com.gtb.libraries.uicore.extensions.gone
import br.com.gtb.libraries.uicore.extensions.show
import br.com.gtb.libraries.uicore.R as uiCoreLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

internal class SpotlightItemViewHolder constructor(
    private val binding: SpotlightItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    @RequiresApi(Build.VERSION_CODES.DONUT)
    fun bind(item: Spotlight) {
        with(binding) {
            imgMainItem.contentDescription = item.name
            progressImage.show()

            Picasso.get()
                .load(item.bannerURL)
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

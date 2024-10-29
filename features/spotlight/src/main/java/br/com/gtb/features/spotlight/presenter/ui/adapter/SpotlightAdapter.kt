package br.com.gtb.features.spotlight.presenter.ui.adapter

import android.view.ViewGroup
import br.com.gtb.features.spotlight.databinding.SpotlightItemBinding
import br.com.gtb.features.spotlight.presenter.ui.viewholder.SpotlightItemViewHolder
import br.com.gtb.libraries.data.model.Spotlight
import br.com.gtb.libraries.uicore.extensions.getInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

internal class SpotlightAdapter : RecyclerView.Adapter<SpotlightItemViewHolder>() {

    private var _dataSource = emptyList<Spotlight>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                SpotlightListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightItemViewHolder {
        val binding = SpotlightItemBinding.inflate(parent.getInflater(), parent, false)
        return SpotlightItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpotlightItemViewHolder, position: Int) {
        holder.bind(_dataSource[position])
    }

    override fun getItemCount(): Int =
        _dataSource.size

    fun setDataSource(dataSource: List<Spotlight>) {
        _dataSource = dataSource
        notifyDataSetChanged()
    }
}
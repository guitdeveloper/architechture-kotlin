package br.com.gtb.features.spotlight.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import br.com.gtb.features.spotlight.databinding.SpotlightFragmentBinding
import br.com.gtb.features.spotlight.presenter.ui.adapter.SpotlightAdapter
import br.com.gtb.features.spotlight.presenter.viewmodel.SpotlightViewModel
import br.com.gtb.libraries.data.model.Spotlight
import br.com.gtb.libraries.uicore.extensions.gone
import br.com.gtb.libraries.uicore.extensions.visible
import br.com.gtb.libraries.uicore.utils.Result
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpotlightFragment : Fragment() {
    private var _binding: SpotlightFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SpotlightViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = SpotlightFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        binding.listSpotlights.adapter = SpotlightAdapter()
        viewModel.getList()
    }

    private fun setupObservers() {
        with(viewModel) {
            statusGetListFromServer
                .observe(viewLifecycleOwner) { status ->
                    checkStatusGetListFromServer(status)
                }

            getListFromDb()
                .observe(viewLifecycleOwner) {
                    it?.let {
                        if (it.isNotEmpty()) {
                            getAdapter()?.setDataSource(it)
                            with(binding) {
                                listSpotlights.visible()
                                loadSpotlights.gone()
                            }
                        }
                    }
                }
        }
    }

    private fun checkStatusGetListFromServer(status: Result<List<Spotlight>>?) {
        when (status) {
            is Result.InProgress -> {
                binding.loadSpotlights.visible()
            }
            is Result.Success -> {
                status.data?.let {
                    viewModel.createListToDb(it)
                }
                viewModel.resetStatus(ISpotlightRepository.SpotlightRepositoryStatus.GetListFromServer)
            }
            is Result.Error -> {
                viewModel.resetStatus(ISpotlightRepository.SpotlightRepositoryStatus.GetListFromServer)
            }
            else -> {
            }
        }
    }

    private fun getAdapter(): SpotlightAdapter? {
        return binding.listSpotlights.adapter as SpotlightAdapter?
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.listSpotlights.adapter = null
        _binding = null
    }

}
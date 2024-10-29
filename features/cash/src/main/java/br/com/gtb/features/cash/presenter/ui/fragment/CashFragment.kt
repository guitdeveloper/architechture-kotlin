package br.com.gtb.features.cash.presenter.ui.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gtb.features.cash.R
import br.com.gtb.features.cash.data.irepostory.ICashRepository
import br.com.gtb.features.cash.databinding.CashFragmentBinding
import br.com.gtb.features.cash.presenter.viewmodel.CashViewModel
import br.com.gtb.libraries.data.model.Cash
import br.com.gtb.libraries.uicore.extensions.gone
import br.com.gtb.libraries.uicore.extensions.visible
import br.com.gtb.libraries.uicore.R as uiCoreLayout
import br.com.gtb.libraries.uicore.utils.Result
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class CashFragment : Fragment() {
    private var _binding: CashFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = CashFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        viewModel.getCash()
    }

    private fun setupObservers() {
        with(viewModel) {
            statusGetListFromServer
                .observe(viewLifecycleOwner) { status ->
                    checkStatusGetListFromServer(status)
                }

            getCashFromDb()
                .observe(viewLifecycleOwner) {
                    it?.let {
                        with(binding) {
                            setupCashText()
                            setupCashBanner(it)
                            loadCash.gone()
                        }
                    }
                }
        }
    }

    private fun checkStatusGetListFromServer(status: Result<Cash>?) {
        when (status) {
            is Result.InProgress -> {
                binding.loadCash.visible()
            }
            is Result.Success -> {
                status.data?.let {
                    viewModel.createCashToDb(it)
                }
                viewModel.resetStatus(ICashRepository.CashRepositoryStatus.GetCashFromServer)
            }
            is Result.Error -> {
                viewModel.resetStatus(ICashRepository.CashRepositoryStatus.GetCashFromServer)
            }
            else -> {
            }
        }
    }

    private fun setupCashText() {
        val cacheText = "digio Cache"
        binding.textCashTitle.text = SpannableString(cacheText).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(requireContext(), R.color.blue_darker)
                ),
                0,
                5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(requireContext(), R.color.font_color_digio_cash)
                ),
                6,
                cacheText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.textCashTitle.visible()
    }

    private fun setupCashBanner(cash: Cash) {
        Picasso.get()
            .load(cash.bannerURL)
            .error(uiCoreLayout.drawable.ic_alert_circle)
            .into(binding.imageCash, object : Callback {
                override fun onSuccess() {
                    binding.loadCash.gone()
                }
                override fun onError(e: Exception?) {
                    binding.loadCash.gone()
                }
            })
        binding.containerCash.visible()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
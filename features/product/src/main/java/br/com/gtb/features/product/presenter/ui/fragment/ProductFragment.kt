package br.com.gtb.features.product.presenter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gtb.features.product.data.irepostory.IProductRepository
import br.com.gtb.features.product.databinding.ProductFragmentBinding
import br.com.gtb.features.product.presenter.ui.adapter.ProductAdapter
import androidx.fragment.app.Fragment
import br.com.gtb.features.product.presenter.viewmodel.ProductViewModel
import br.com.gtb.libraries.data.model.Product
import br.com.gtb.libraries.uicore.extensions.gone
import br.com.gtb.libraries.uicore.extensions.visible
import br.com.gtb.libraries.uicore.utils.Result
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {
    private var _binding: ProductFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ProductFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        binding.listProducts.adapter = ProductAdapter()
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
                                listProducts.visible()
                                loadProducts.gone()
                            }
                        }
                    }
                }
        }
    }

    private fun checkStatusGetListFromServer(status: Result<List<Product>>?) {
        when (status) {
            is Result.InProgress -> {
                binding.loadProducts.visible()
            }
            is Result.Success -> {
                status.data?.let {
                    viewModel.createListToDb(it)
                }
                viewModel.resetStatus(IProductRepository.ProductRepositoryStatus.GetListFromServer)
            }
            is Result.Error -> {
                viewModel.resetStatus(IProductRepository.ProductRepositoryStatus.GetListFromServer)
            }
            else -> {
            }
        }
    }

    private fun getAdapter(): ProductAdapter? {
        return binding.listProducts.adapter as ProductAdapter?
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.listProducts.adapter = null
        _binding = null
    }

}
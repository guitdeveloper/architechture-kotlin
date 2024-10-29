package br.com.gtb.features.product.presenter.viewmodel

import br.com.gtb.features.product.data.irepostory.IProductRepository
import androidtest.libraries.data.model.Product
import io.mockk.*

internal fun withProductViewModelRobot(fn: ProductViewModelRobot.() -> Unit) =
    ProductViewModelRobot().apply(fn)

class Params {
    data class List(
        var list: MutableList<Product> = mutableListOf()
    )
}

internal class ProductViewModelRobot {
    var repository = mockk<IProductRepository>(relaxed = true)
    var viewModel = ProductViewModel(repository)
    lateinit var paramsList: Params.List

    infix fun action(fn: ProductViewModelActionRobot.() -> Unit) =
        ProductViewModelActionRobot(this).apply(fn)

    fun when_none() {
        return
    }

    fun when_createListToDb_listEmpty() {
        paramsList = Params.List()
    }

    fun when_createListToDb_listFilled() {
        paramsList = Params.List()
        paramsList.list = mutableListOf()
        paramsList.list.add(Product(
            "http:",
            "productOne",
            "descriptionOne"
        ))
    }

    fun when_createListToDb_listTwoItems() {
        paramsList = Params.List()
        paramsList.list = mutableListOf()
        paramsList.list.add(Product(
            "http:",
            "productOne",
            "descriptionOne"
        ))
        paramsList.list.add(Product(
            "http:",
            "productTwo",
            "descriptionTwo"
        ))
    }

}

internal class ProductViewModelActionRobot(private val robot: ProductViewModelRobot) {

    infix fun result(block: ProductViewModelResultRobot.() -> Unit) =
        ProductViewModelResultRobot(robot).apply(block)

    fun getList() =
        robot.viewModel.getList()

    fun createListToDb() =
        robot.viewModel.createListToDb(robot.paramsList.list.toList())

    fun getListFromDb() =
        robot.viewModel.getListFromDb()

    fun resetStatus(status: IProductRepository.ProductRepositoryStatus) =
        robot.viewModel.resetStatus(status)

}

internal class ProductViewModelResultRobot(private val robot: ProductViewModelRobot) {

    fun check_getList() {
        verify { robot.viewModel.getList() }
        coVerifySequence {
            verifyStatusFun()
            robot.repository.getList()
        }
        confirmVerified(
            robot.repository
        )
    }

    fun check_createListToDb_listEmpty() {
        assert(robot.paramsList.list.size == 0)
        verifySequence {
            verifyStatusFun()
        }
    }

    fun check_createListToDb_listFilled() {
        with(robot) {
            assert(paramsList.list.size == 1)
            coVerifySequence {
                verifyStatusFun()
                repository.createListToDb(any())
            }
            confirmVerified(
                repository
            )
        }
    }

    fun check_createListToDb_listTwoItems() {
        with(robot) {
            assert(paramsList.list.size == 2)
            coVerifySequence {
                verifyStatusFun()
                repository.createListToDb(any())
            }
            confirmVerified(
                repository
            )
        }
    }

    fun check_getListFromDb() {
        verify { robot.viewModel.getListFromDb() }
        verifySequence {
            verifyStatusFun()
            robot.repository.getListFromDb()
        }
        confirmVerified(
            robot.repository
        )
    }

    fun check_resetStatus(status: IProductRepository.ProductRepositoryStatus) {
        verify {
            robot.repository.resetStatus(status)
        }
    }

    private fun verifyStatusFun() {
        robot.repository.statusGetListFromServer
    }

}
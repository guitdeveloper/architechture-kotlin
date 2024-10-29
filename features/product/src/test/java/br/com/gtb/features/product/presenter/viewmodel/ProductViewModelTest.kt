package br.com.gtb.features.product.presenter.viewmodel

import br.com.gtb.features.product.data.irepostory.IProductRepository
import androidtest.libraries.testcore.junitutils.v5.CoroutineTest
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.*
import org.koin.test.AutoCloseKoinTest

@ExperimentalCoroutinesApi
@DisplayName("Feature > Product - ProductViewModel")
internal class ProductViewModelTest : AutoCloseKoinTest(), CoroutineTest {

    override lateinit var testScope: TestCoroutineScope
    override lateinit var dispatcher: TestCoroutineDispatcher

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @DisplayName("getList - WHEN get list")
    @Nested
    inner class GetList {

        @Test
        fun `THEN the result should be an product list`() {
            withProductViewModelRobot {
                when_none()
            } action {
                getList()
            } result {
                check_getList()
            }
        }

    }

    @DisplayName("createListToDb - GIVEN list")
    @Nested
    inner class CreateListToDb {

        @DisplayName("WHEN create list of product to DB")
        @Nested
        inner class When {

            @Test
            fun `THEN should to execute nothing`() {
                withProductViewModelRobot {
                    when_createListToDb_listEmpty()
                } action {
                    createListToDb()
                } result {
                    check_createListToDb_listEmpty()
                }
            }

            @Test
            fun `THEN should to show list with one product`() {
                withProductViewModelRobot {
                    when_createListToDb_listFilled()
                } action {
                    createListToDb()
                } result {
                    check_createListToDb_listFilled()
                }
            }

            @Test
            fun `THEN should to show list with two products`() {
                withProductViewModelRobot {
                    when_createListToDb_listTwoItems()
                } action {
                    createListToDb()
                } result {
                    check_createListToDb_listTwoItems()
                }
            }

        }
    }

    @DisplayName("getListFromDb - WHEN get list from DB")
    @Nested
    inner class GetListFromDb {

        @Test
        fun `THEN the result should be get list of products from DB`() {
            withProductViewModelRobot {
                when_none()
            } action {
                getListFromDb()
            } result {
                check_getListFromDb()
            }
        }

    }

    @DisplayName("resetStatus... - WHEN reset status")
    @Nested
    inner class ResetStatus {

        @TestFactory
        fun `THEN result is none`() = IProductRepository.ProductRepositoryStatus.values()
            .map {
                DynamicTest.dynamicTest("Status [$it] should call repository") {
                    withProductViewModelRobot {
                        when_none()
                    } action {
                        resetStatus(it)
                    } result {
                        check_resetStatus(it)
                    }
                }
            }
    }

}
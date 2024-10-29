package br.com.gtb.features.product.data.repository

import br.com.gtb.features.product.data.irepostory.IProductRepository
import androidtest.libraries.testcore.junitutils.v5.InstantTaskExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.test.AutoCloseKoinTest

@DisplayName("Feature > Product - ProductRepository")
@ExtendWith(InstantTaskExecutorExtension::class)
internal class ProductRepositoryTest : AutoCloseKoinTest() {

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @DisplayName("getList - WHEN get list of product")
    @Nested
    inner class GetList {

        @Test
        fun `THEN the result should be null from db`() {
            withProductRepositoryRobot {
                when_getList_nullFromDb()
            } action {
                getList()
            } result {
                check_getList_nullFromDb()
            }
        }

        @Test
        fun `THEN the result should be empty list from db`() {
            withProductRepositoryRobot {
                when_getList_emptyListFromDb()
            } action {
                getList()
            } result {
                check_getList_emptyListFromDb()
            }
        }

        @Test
        fun `THEN the result should be list of product with item`() {
            withProductRepositoryRobot {
                when_getList_listFromDb()
            } action {
                getList()
            } result {
                check_getList_listFromDb()
            }
        }
    }

    @DisplayName("getListFromServer - WHEN get list from server")
    @Nested
    inner class GetListFromServer {

        @Test
        fun `THEN the result should be successful`() {
            withProductRepositoryRobot {
                when_getListFromServer_successful()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_successful()
            }
        }

        @Test
        fun `THEN the result should be error`() {
            withProductRepositoryRobot {
                when_getListFromServer_error()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_error()
            }
        }

        @Test
        fun `THEN the result should be an exception`() {
            withProductRepositoryRobot {
                when_getListFromServer_exception()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_exception()
            }
        }

        @Test
        fun `THEN the result should be an ListIsEmptyException`() {
            withProductRepositoryRobot {
                when_getListFromServer_listIsEmptyException()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_listIsEmptyException()
            }
        }

        @Test
        fun `THEN the result should be an ResponseNoBodyException`() {
            withProductRepositoryRobot {
                when_getListFromServer_responseNoBodyException()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_responseNoBodyException()
            }
        }

    }

    @DisplayName("createListToDb - GIVEN list")
    @Nested
    inner class CreateListToDb {

        @DisplayName("WHEN execute dao")
        @Nested
        inner class When {

            @Test
            fun `THEN the result should be successful`() {
                withProductRepositoryRobot {
                    when_none()
                } action {
                    createListToDb()
                } result {
                    check_createListToDb_successful()
                }
            }

            @Test
            fun `THEN the result should be an exception`() {
                withProductRepositoryRobot {
                    when_createListToDb_exception()
                } action {
                    createListToDb()
                } result {
                    check_createListToDb_exception()
                }
            }

        }
    }

    @DisplayName("getListFromDb - WHEN get list from db")
    @Nested
    inner class GetListFromDb {

        @Test
        fun `THEN the result should be livedata of list`() {
            withProductRepositoryRobot {
                when_getListFromDb_success()
            } action {
                getListFromDb()
            } result {
                check_getListFromDb_success()
            }
        }

        @Test
        fun `THEN the result should be livedata with none product`() {
            withProductRepositoryRobot {
                when_none()
            } action {
                getListFromDb()
            } result {
                check_getListFromDb_null()
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
                    withProductRepositoryRobot {
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
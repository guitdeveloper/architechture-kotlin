package br.com.gtb.features.cash.presenter.viewmodel

import br.com.gtb.features.cash.data.irepostory.ICashRepository
import androidtest.libraries.testcore.junitutils.v5.CoroutineTest
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.*
import org.koin.test.AutoCloseKoinTest

@ExperimentalCoroutinesApi
@DisplayName("Feature > Cash - CashViewModel")
internal class CashViewModelTest : AutoCloseKoinTest(), CoroutineTest {

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

    @DisplayName("getCash - WHEN get cash")
    @Nested
    inner class GetCash {

        @Test
        fun `THEN the result should be the cash`() {
            withCashViewModelRobot {
                when_none()
            } action {
                getCash()
            } result {
                check_getCash()
            }
        }

    }

    @DisplayName("createCashToDb - GIVEN cash")
    @Nested
    inner class CreateCashToDb {

        @DisplayName("WHEN create cash to DB")
        @Nested
        inner class When {

            @Test
            fun `THEN the result should be cash created on DB`() {
                withCashViewModelRobot {
                    when_none()
                } action {
                    createCashToDb()
                } result {
                    check_createCashToDb()
                }
            }

        }
    }

    @DisplayName("getCashFromDb - WHEN get cash from DB")
    @Nested
    inner class GetCashFromDb {

        @Test
        fun `THEN the result should be get cash from DB`() {
            withCashViewModelRobot {
                when_none()
            } action {
                getCashFromDb()
            } result {
                check_getCashFromDb()
            }
        }

    }

    @DisplayName("resetStatus... - WHEN reset status")
    @Nested
    inner class ResetStatus {

        @TestFactory
        fun `THEN result is none`() = ICashRepository.CashRepositoryStatus.values()
            .map {
                DynamicTest.dynamicTest("Status [$it] should call repository") {
                    withCashViewModelRobot {
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
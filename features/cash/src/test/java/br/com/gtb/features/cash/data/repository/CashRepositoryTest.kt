package br.com.gtb.features.cash.data.repository

import androidtest.features.cash.data.repository.withCashRepositoryRobot
import br.com.gtb.features.cash.data.irepostory.ICashRepository
import androidtest.libraries.testcore.junitutils.v5.InstantTaskExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.test.AutoCloseKoinTest

@DisplayName("Feature > Cash - CashRepository")
@ExtendWith(InstantTaskExecutorExtension::class)
internal class CashRepositoryTest : AutoCloseKoinTest() {

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
        fun `THEN the result should be null`() {
            withCashRepositoryRobot {
                when_getCash_null()
            } action {
                getCash()
            } result {
                check_getCash_null()
            }
        }

        @Test
        fun `THEN the result should be cash`() {
            withCashRepositoryRobot {
                when_getCash_notNull()
            } action {
                getCash()
            } result {
                check_getCash_notNull()
            }
        }
    }

    @DisplayName("getCashFromServer - WHEN get cash from server")
    @Nested
    inner class GetCashFromServer {

        @Test
        fun `THEN the result should be successful`() {
            withCashRepositoryRobot {
                when_getCashFromServer_successful()
            } action {
                getCashFromServer()
            } result {
                check_getCashFromServer_successful()
            }
        }

        @Test
        fun `THEN the result should be error`() {
            withCashRepositoryRobot {
                when_getCashFromServer_error()
            } action {
                getCashFromServer()
            } result {
                check_getCashFromServer_error()
            }
        }

        @Test
        fun `THEN the result should be an exception`() {
            withCashRepositoryRobot {
                when_getCashFromServer_exception()
            } action {
                getCashFromServer()
            } result {
                check_getCashFromServer_exception()
            }
        }

        @Test
        fun `THEN the result should be an ResponseNoBodyException`() {
            withCashRepositoryRobot {
                when_getCashFromServer_responseNoBodyException()
            } action {
                getCashFromServer()
            } result {
                check_getCashFromServer_responseNoBodyException()
            }
        }

    }

    @DisplayName("createCashToDb - GIVEN cash")
    @Nested
    inner class CreateCashToDb {

        @DisplayName("WHEN execute dao")
        @Nested
        inner class When {

            @Test
            fun `THEN the result should be successful`() {
                withCashRepositoryRobot {
                    when_none()
                } action {
                    createCashToDb()
                } result {
                    check_createCashToDb_successful()
                }
            }

            @Test
            fun `THEN the result should be an exception`() {
                withCashRepositoryRobot {
                    when_createCashToDb_exception()
                } action {
                    createCashToDb()
                } result {
                    check_createCashToDb_exception()
                }
            }

        }
    }

    @DisplayName("getCashFromDb - WHEN get cash from db")
    @Nested
    inner class GetCashFromDb {

        @Test
        fun `THEN the result should be livedata of cash`() {
            withCashRepositoryRobot {
                when_getCashFromDb_success()
            } action {
                getCashFromDb()
            } result {
                check_getCashFromDb_success()
            }
        }

        @Test
        fun `THEN the result should be livedata with none cash`() {
            withCashRepositoryRobot {
                when_none()
            } action {
                getCashFromDb()
            } result {
                check_getCashFromDb_null()
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
                    withCashRepositoryRobot {
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
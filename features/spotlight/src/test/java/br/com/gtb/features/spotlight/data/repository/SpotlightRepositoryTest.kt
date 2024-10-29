package br.com.gtb.features.spotlight.data.repository

import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import androidtest.libraries.testcore.junitutils.v5.InstantTaskExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.test.AutoCloseKoinTest

@DisplayName("Feature > Spotlight - SpotlightRepository")
@ExtendWith(InstantTaskExecutorExtension::class)
internal class SpotlightRepositoryTest : AutoCloseKoinTest() {

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @DisplayName("getList - WHEN get list of Spotlight")
    @Nested
    inner class GetList {

        @Test
        fun `THEN the result should be null from db`() {
            withSpotlightRepositoryRobot {
                when_getList_nullFromDb()
            } action {
                getList()
            } result {
                check_getList_nullFromDb()
            }
        }

        @Test
        fun `THEN the result should be empty list from db`() {
            withSpotlightRepositoryRobot {
                when_getList_emptyListFromDb()
            } action {
                getList()
            } result {
                check_getList_emptyListFromDb()
            }
        }

        @Test
        fun `THEN the result should be list of Spotlight with item`() {
            withSpotlightRepositoryRobot {
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
            withSpotlightRepositoryRobot {
                when_getListFromServer_successful()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_successful()
            }
        }

        @Test
        fun `THEN the result should be error`() {
            withSpotlightRepositoryRobot {
                when_getListFromServer_error()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_error()
            }
        }

        @Test
        fun `THEN the result should be an exception`() {
            withSpotlightRepositoryRobot {
                when_getListFromServer_exception()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_exception()
            }
        }

        @Test
        fun `THEN the result should be an ListIsEmptyException`() {
            withSpotlightRepositoryRobot {
                when_getListFromServer_listIsEmptyException()
            } action {
                getListFromServer()
            } result {
                check_getListFromServer_listIsEmptyException()
            }
        }

        @Test
        fun `THEN the result should be an ResponseNoBodyException`() {
            withSpotlightRepositoryRobot {
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
                withSpotlightRepositoryRobot {
                    when_none()
                } action {
                    createListToDb()
                } result {
                    check_createListToDb_successful()
                }
            }

            @Test
            fun `THEN the result should be an exception`() {
                withSpotlightRepositoryRobot {
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
            withSpotlightRepositoryRobot {
                when_getListFromDb_success()
            } action {
                getListFromDb()
            } result {
                check_getListFromDb_success()
            }
        }

        @Test
        fun `THEN the result should be livedata with none Spotlight`() {
            withSpotlightRepositoryRobot {
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
        fun `THEN result is none`() = ISpotlightRepository.SpotlightRepositoryStatus.values()
            .map {
                DynamicTest.dynamicTest("Status [$it] should call repository") {
                    withSpotlightRepositoryRobot {
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
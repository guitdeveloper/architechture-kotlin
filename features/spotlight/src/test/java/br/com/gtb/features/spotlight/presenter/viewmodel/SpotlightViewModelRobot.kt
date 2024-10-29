package br.com.gtb.features.spotlight.presenter.viewmodel

import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import androidtest.libraries.data.model.Spotlight
import io.mockk.*

internal fun withSpotlightViewModelRobot(fn: SpotlightViewModelRobot.() -> Unit) =
    SpotlightViewModelRobot().apply(fn)

class Params {
    data class List(
        var list: MutableList<Spotlight> = mutableListOf()
    )
}

internal class SpotlightViewModelRobot {
    var repository = mockk<ISpotlightRepository>(relaxed = true)
    var viewModel = SpotlightViewModel(repository)
    lateinit var paramsList: Params.List

    infix fun action(fn: SpotlightViewModelActionRobot.() -> Unit) =
        SpotlightViewModelActionRobot(this).apply(fn)

    fun when_none() {
        return
    }

    fun when_createListToDb_listEmpty() {
        paramsList = Params.List()
    }

    fun when_createListToDb_listFilled() {
        paramsList = Params.List()
        paramsList.list = mutableListOf()
        paramsList.list.add(
            Spotlight(
                "http:",
                "SpotlightOne",
                "descriptionOne"
            )
        )
    }

    fun when_createListToDb_listTwoItems() {
        paramsList = Params.List()
        paramsList.list = mutableListOf()
        paramsList.list.add(
            Spotlight(
                "http:",
                "SpotlightOne",
                "descriptionOne"
            )
        )
        paramsList.list.add(
            Spotlight(
                "http:",
                "SpotlightTwo",
                "descriptionTwo"
            )
        )
    }

}

internal class SpotlightViewModelActionRobot(private val robot: SpotlightViewModelRobot) {

    infix fun result(block: SpotlightViewModelResultRobot.() -> Unit) =
        SpotlightViewModelResultRobot(robot).apply(block)

    fun getList() =
        robot.viewModel.getList()

    fun createListToDb() =
        robot.viewModel.createListToDb(robot.paramsList.list.toList())

    fun getListFromDb() =
        robot.viewModel.getListFromDb()

    fun resetStatus(status: ISpotlightRepository.SpotlightRepositoryStatus) =
        robot.viewModel.resetStatus(status)

}

internal class SpotlightViewModelResultRobot(private val robot: SpotlightViewModelRobot) {

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

    fun check_resetStatus(status: ISpotlightRepository.SpotlightRepositoryStatus) {
        verify {
            robot.repository.resetStatus(status)
        }
    }

    private fun verifyStatusFun() {
        robot.repository.statusGetListFromServer
    }

}
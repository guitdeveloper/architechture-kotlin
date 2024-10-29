package br.com.gtb.features.cash.presenter.viewmodel

import br.com.gtb.features.cash.data.irepostory.ICashRepository
import androidtest.libraries.data.model.Cash
import io.mockk.*

internal fun withCashViewModelRobot(fn: CashViewModelRobot.() -> Unit) =
    CashViewModelRobot().apply(fn)

internal class CashViewModelRobot {
    var repository = mockk<ICashRepository>(relaxed = true)
    var viewModel = CashViewModel(repository)

    infix fun action(fn: CashViewModelActionRobot.() -> Unit) =
        CashViewModelActionRobot(this).apply(fn)

    fun when_none() {
        return
    }

}

internal class CashViewModelActionRobot(private val robot: CashViewModelRobot) {

    infix fun result(block: CashViewModelResultRobot.() -> Unit) =
        CashViewModelResultRobot(robot).apply(block)

    fun getCash() =
        robot.viewModel.getCash()

    fun createCashToDb() =
        robot.viewModel.createCashToDb(Cash("", "", ""))

    fun getCashFromDb() =
        robot.viewModel.getCashFromDb()

    fun resetStatus(status: ICashRepository.CashRepositoryStatus) =
        robot.viewModel.resetStatus(status)

}

internal class CashViewModelResultRobot(private val robot: CashViewModelRobot) {

    fun check_getCash() {
        verify { robot.viewModel.getCash() }
        coVerifySequence {
            verify_statusFun()
            robot.repository.getCash()
        }
        confirmVerified(
            robot.repository
        )
    }

    fun check_createCashToDb() {
        verify { robot.viewModel.createCashToDb(any()) }
        coVerifySequence {
            verify_statusFun()
            robot.repository.createCashToDb(any())
        }
        confirmVerified(
            robot.repository
        )
    }

    fun check_getCashFromDb() {
        verify { robot.viewModel.getCashFromDb() }
        verifySequence {
            verify_statusFun()
            robot.repository.getCashFromDb()
        }
        confirmVerified(
            robot.repository
        )
    }

    fun check_resetStatus(status: ICashRepository.CashRepositoryStatus) {
        verify {
            robot.repository.resetStatus(status)
        }
    }

    private fun verify_statusFun() {
        robot.repository.statusGetCashFromServer
    }

}
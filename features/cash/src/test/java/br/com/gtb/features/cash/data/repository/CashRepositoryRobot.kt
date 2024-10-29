package androidtest.features.cash.data.repository

import br.com.gtb.features.cash.data.irepostory.ICashRepository
import androidx.lifecycle.LiveData
import br.com.gtb.features.cash.data.repository.CashRepository
import br.com.gtb.libraries.data.dao.CashDao
import br.com.gtb.libraries.data.model.Cash
import br.com.gtb.libraries.data.response.ArchitectureProductResponse
import br.com.gtb.libraries.data.service.ArchitectureService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal fun withCashRepositoryRobot(fn: CashRepositoryRobot.() -> Unit) = CashRepositoryRobot().apply(fn)

class Params {
    data class Cash(
        val bannerURL: String = "url",
        val title: String = "title",
        val description: String = "description",
    )
}

internal class CashRepositoryRobot {
    var dao = mockk<CashDao>(relaxed = true)
    var service = mockk<ArchitectureService>(relaxed = true)
    var repository =
        spyk(CashRepository(dao, service), recordPrivateCalls = true)
    lateinit var resultLiveDataCash: LiveData<Cash?>
    lateinit var resultException: Exception
    lateinit var responseGetCashFromServer: Response<ArchitectureProductResponse>

    infix fun action(fn: CashRepositoryActionRobot.() -> Unit) =
        CashRepositoryActionRobot(this).apply(fn)

    fun when_none() {
        return
    }

    fun when_getCash_null() {
        val liveData = mockk<LiveData<Cash?>>()
        coEvery { liveData.value } returns null
        coEvery { repository.getCashFromDb() } returns liveData
        coEvery { repository.getCashFromServer() } returns mockk()
    }

    fun when_getCash_notNull() {
        val liveData = mockk<LiveData<Cash?>>()
        coEvery { liveData.value } returns Cash("","","")
        coEvery { repository.getCashFromDb() } returns liveData
    }

    fun when_getCashFromServer_successful() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        val objResponse = ArchitectureProductResponse(
            Cash("", "", ""),
            listOf(),
            listOf(),
        )
        responseGetCashFromServer = mockk()
        coEvery { responseGetCashFromServer.isSuccessful } returns true
        coEvery { responseGetCashFromServer.body() } returns objResponse
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts().enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetCashFromServer)
        }
    }

    fun when_getCashFromServer_error() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        responseGetCashFromServer = mockk()
        coEvery { responseGetCashFromServer.isSuccessful } returns false
        coEvery { responseGetCashFromServer.code() } returns 404
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts().enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetCashFromServer)
        }
    }

    fun when_getCashFromServer_exception() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        responseGetCashFromServer = mockk()
        coEvery { responseGetCashFromServer.isSuccessful } throws Exception()
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts().enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetCashFromServer)
        }
    }

    fun when_getCashFromServer_responseNoBodyException() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        responseGetCashFromServer = mockk()
        coEvery { responseGetCashFromServer.isSuccessful } returns true
        coEvery { responseGetCashFromServer.body() } returns null
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts().enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetCashFromServer)
        }
    }

    fun when_createCashToDb_exception() {
        resultException = Exception()
        coEvery { dao.clearAndInsert(any()) } throws resultException
    }

    fun when_getCashFromDb_success() {
        val model = Cash(
            Params.Cash().bannerURL,
            Params.Cash().title,
            Params.Cash().description,
        )
        val liveData = mockk<LiveData<Cash?>>()
        every { liveData.value } returns model
        every { repository.getCashFromDb() } returns liveData
    }

}

internal class CashRepositoryActionRobot(private val robot: CashRepositoryRobot) {

    infix fun result(block: CashRepositoryResultRobot.() -> Unit) =
        CashRepositoryResultRobot(robot).apply(block)

    fun getCash() {
        runBlocking {
            robot.repository.getCash()
        }
    }

    fun getCashFromServer() {
        runBlocking {
            robot.repository.getCashFromServer()
        }
    }

    fun createCashToDb() {
        runBlocking {
            robot.repository.createCashToDb(Cash("","",""))
        }
    }

    fun getCashFromDb() {
        robot.resultLiveDataCash = robot.repository.getCashFromDb()
    }

    fun resetStatus(status: ICashRepository.CashRepositoryStatus) {
        robot.repository.resetStatus(status)
    }

}

internal class CashRepositoryResultRobot(private val robot: CashRepositoryRobot) {

    fun check_getCash_null() {
        coVerifySequence {
            robot.repository.getCash()
            robot.repository.getCashFromDb()
            robot.repository.getCashFromServer()
        }
        confirmVerified(
            robot.repository,
        )
    }

    fun check_getCash_notNull() {
        coVerifySequence {
            robot.repository.getCash()
            robot.repository.getCashFromDb()
        }
        confirmVerified(
            robot.repository,
        )
    }

    fun check_getCashFromServer_successful() {
        coVerifySequence {
            robot.service.getProducts()
            robot.responseGetCashFromServer.isSuccessful
            robot.responseGetCashFromServer.body()
        }
        confirmVerified(
            robot.service,
        )
        val result = robot.repository.statusGetCashFromServer.value
        assertTrue(result is Result.Success)
    }

    fun check_getCashFromServer_error() {
        coVerifySequence {
            robot.service.getProducts()
            robot.responseGetCashFromServer.isSuccessful
            robot.responseGetCashFromServer.code()
        }
        confirmVerified(
            robot.service,
        )
        assertTrue(robot.repository.statusGetCashFromServer.value is Result.Error)
    }

    fun check_getCashFromServer_exception() {
        coVerifySequence {
            robot.service.getProducts()
        }
        confirmVerified(
            robot.service,
        )
        assertTrue(robot.repository.statusGetCashFromServer.value is Result.Error)
    }

    fun check_getCashFromServer_responseNoBodyException() {
        coVerifySequence {
            robot.service.getProducts()
            robot.responseGetCashFromServer.isSuccessful
            robot.responseGetCashFromServer.body()
        }
        confirmVerified(
            robot.service,
        )
        val result = robot.repository.statusGetCashFromServer.value as Result.Error
        assertTrue(result.exception is ResponseNoBodyException)
    }

    fun check_createCashToDb_successful() {
        coVerifySequence {
            robot.repository.createCashToDb(any())
            robot.repository.dao
            robot.dao.clearAndInsert(any())
        }
        confirmVerified(
            robot.repository,
            robot.dao,
        )
    }

    fun check_createCashToDb_exception() {
        coVerifySequence {
            robot.repository.createCashToDb(any())
            robot.repository.dao
            robot.dao.clearAndInsert(any())
        }
        confirmVerified(
            robot.repository,
            robot.dao,
        )
        assertTrue(robot.resultException is Exception)
    }

    fun check_getCashFromDb_success() {
        verifySequence {
            robot.repository.getCashFromDb()
        }
        confirmVerified(
            robot.repository,
        )
        assertNotNull(robot.resultLiveDataCash.value)
        robot.resultLiveDataCash.value?.let {
            Assertions.assertEquals(Params.Cash().title, it.title)
        }
    }

    fun check_getCashFromDb_null() {
        verifySequence {
            robot.repository.getCashFromDb()
            robot.dao.getList()
        }
        confirmVerified(
            robot.repository,
            robot.dao,
        )
        assertNull(robot.resultLiveDataCash.value)
    }

    fun check_resetStatus(status: ICashRepository.CashRepositoryStatus) {
        assertTrue(
            when (status) {
                ICashRepository.CashRepositoryStatus.GetCashFromServer ->
                    robot.repository.statusGetCashFromServer.value
            }
                    is Result.None
        )
    }

}
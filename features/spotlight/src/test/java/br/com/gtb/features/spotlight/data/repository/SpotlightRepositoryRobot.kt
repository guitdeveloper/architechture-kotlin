package br.com.gtb.features.spotlight.data.repository

import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import androidx.lifecycle.LiveData
import br.com.gtb.libraries.data.dao.SpotlightDao
import br.com.gtb.libraries.data.model.Cash
import br.com.gtb.libraries.data.model.Spotlight
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

internal fun withSpotlightRepositoryRobot(fn: SpotlightRepositoryRobot.() -> Unit) = SpotlightRepositoryRobot().apply(fn)

class Params {
    data class Spotlight(
        val imageURL: String = "url",
        val name: String = "name",
        val description: String = "description",
    )
}

internal class SpotlightRepositoryRobot {
    var dao = mockk<SpotlightDao>(relaxed = true)
    var service = mockk<ArchitectureService>(relaxed = true)
    var repository =
        spyk(SpotlightRepository(dao, service), recordPrivateCalls = true)
    lateinit var resultLiveList: LiveData<List<Spotlight>?>
    lateinit var resultException: Exception
    lateinit var responseGetListFromServer: Response<ArchitectureProductResponse>

    infix fun action(fn: SpotlightRepositoryActionRobot.() -> Unit) =
        SpotlightRepositoryActionRobot(this).apply(fn)

    fun when_none() {
        return
    }

    fun when_getList_nullFromDb() {
        val liveData = mockk<LiveData<List<Spotlight>?>>()
        coEvery { liveData.value } returns null
        coEvery { repository.getListFromDb() } returns liveData
        coEvery { repository.getListFromServer() } returns mockk()
    }

    fun when_getList_emptyListFromDb() {
        val liveData = mockk<LiveData<List<Spotlight>?>>()
        coEvery { liveData.value } returns emptyList()
        coEvery { repository.getListFromDb() } returns liveData
        coEvery { repository.getListFromServer() } returns mockk()
    }

    fun when_getList_listFromDb() {
        val liveData = mockk<LiveData<List<Spotlight>?>>()
        coEvery { liveData.value } returns listOf(
            Spotlight("","",""),
        )
        coEvery { repository.getListFromDb() } returns liveData
        coEvery { repository.getListFromServer() } returns mockk()
    }

    fun when_getListFromServer_successful() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        val objResponse = ArchitectureProductResponse(
            Cash("", "", ""),
            listOf(),
            listOf(
                Spotlight(
                    Params.Spotlight().imageURL,
                    Params.Spotlight().name,
                    Params.Spotlight().description
                ),
            ),
        )
        responseGetListFromServer = mockk()
        coEvery { responseGetListFromServer.isSuccessful } returns true
        coEvery { responseGetListFromServer.body() } returns objResponse
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts() .enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetListFromServer)
        }
    }

    fun when_getListFromServer_error() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        responseGetListFromServer = mockk()
        coEvery { responseGetListFromServer.isSuccessful } returns false
        coEvery { responseGetListFromServer.code() } returns 404
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts() .enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetListFromServer)
        }
    }

    fun when_getListFromServer_exception() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        responseGetListFromServer = mockk()
        coEvery { responseGetListFromServer.isSuccessful } throws Exception()
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts() .enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetListFromServer)
        }
    }

    fun when_getListFromServer_listIsEmptyException() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        val objResponse = ArchitectureProductResponse(
            Cash("", "", ""),
            listOf(),
            listOf(),
        )
        responseGetListFromServer = mockk()
        coEvery { responseGetListFromServer.isSuccessful } returns true
        coEvery { responseGetListFromServer.body() } returns objResponse
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts() .enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetListFromServer)
        }
    }

    fun when_getListFromServer_responseNoBodyException() {
        coEvery { service.getProducts() } returns mockk()
        val call = mockk<Call<ArchitectureProductResponse>>()
        responseGetListFromServer = mockk()
        coEvery { responseGetListFromServer.isSuccessful } returns true
        coEvery { responseGetListFromServer.body() } returns null
        val slot = CapturingSlot<Callback<ArchitectureProductResponse>>()
        coEvery { service.getProducts() .enqueue(capture(slot)) } answers {
            slot.captured.onResponse(call, responseGetListFromServer)
        }
    }

    fun when_createListToDb_exception() {
        resultException = Exception()
        coEvery { dao.clearAndInsert(any()) } throws resultException
    }

    fun when_getListFromDb_success() {
        val model = listOf(
            Spotlight(
                Params.Spotlight().imageURL,
                Params.Spotlight().name,
                Params.Spotlight().description,
            )
        )
        val liveData = mockk<LiveData<List<Spotlight>?>>()
        every { liveData.value } returns model
        every { repository.getListFromDb() } returns liveData
    }

}

internal class SpotlightRepositoryActionRobot(private val robot: SpotlightRepositoryRobot) {

    infix fun result(block: SpotlightRepositoryResultRobot.() -> Unit) =
        SpotlightRepositoryResultRobot(robot).apply(block)

    fun getList() {
        runBlocking {
            robot.repository.getList()
        }
    }

    fun getListFromServer() {
        runBlocking {
            robot.repository.getListFromServer()
        }
    }

    fun createListToDb() {
        runBlocking {
            robot.repository.createListToDb(
                listOf(
                    Spotlight("","","")
                )
            )
        }
    }

    fun getListFromDb() {
        robot.resultLiveList = robot.repository.getListFromDb()
    }

    fun resetStatus(status: ISpotlightRepository.SpotlightRepositoryStatus) {
        robot.repository.resetStatus(status)
    }

}

internal class SpotlightRepositoryResultRobot(private val robot: SpotlightRepositoryRobot) {

    fun check_getList_nullFromDb() {
        coVerifySequence {
            robot.repository.getList()
            robot.repository.getListFromDb()
            robot.repository.getListFromServer()
        }
        confirmVerified(
            robot.repository,
        )
    }

    fun check_getList_emptyListFromDb() {
        coVerifySequence {
            robot.repository.getList()
            robot.repository.getListFromDb()
            robot.repository.getListFromServer()
        }
        confirmVerified(
            robot.repository,
        )
    }

    fun check_getList_listFromDb() {
        coVerifySequence {
            robot.repository.getList()
            robot.repository.getListFromDb()
        }
        confirmVerified(
            robot.repository,
        )
    }

    fun check_getListFromServer_successful() {
        coVerifySequence {
            robot.service.getProducts() 
            robot.responseGetListFromServer.isSuccessful
            robot.responseGetListFromServer.body()
        }
        confirmVerified(
            robot.service,
        )
        val result = robot.repository.statusGetListFromServer.value
        assertTrue(result is Result.Success)
    }

    fun check_getListFromServer_error() {
        coVerifySequence {
            robot.service.getProducts() 
            robot.responseGetListFromServer.isSuccessful
            robot.responseGetListFromServer.code()
        }
        confirmVerified(
            robot.service,
        )
        assertTrue(robot.repository.statusGetListFromServer.value is Result.Error)
    }

    fun check_getListFromServer_exception() {
        coVerifySequence {
            robot.service.getProducts() 
        }
        confirmVerified(
            robot.service,
        )
        assertTrue(robot.repository.statusGetListFromServer.value is Result.Error)
    }

    fun check_getListFromServer_listIsEmptyException() {
        coVerifySequence {
            robot.service.getProducts()
            robot.responseGetListFromServer.isSuccessful
            robot.responseGetListFromServer.body()
        }
        confirmVerified(
            robot.service,
        )
        val result = robot.repository.statusGetListFromServer.value as Result.Error
        assertTrue(result.exception is ListIsEmptyException)
    }

    fun check_getListFromServer_responseNoBodyException() {
        coVerifySequence {
            robot.service.getProducts() 
            robot.responseGetListFromServer.isSuccessful
            robot.responseGetListFromServer.body()
        }
        confirmVerified(
            robot.service,
        )
        val result = robot.repository.statusGetListFromServer.value as Result.Error
        assertTrue(result.exception is ResponseNoBodyException)
    }

    fun check_createListToDb_successful() {
        coVerifySequence {
            robot.repository.createListToDb(any())
            robot.repository.dao
            robot.dao.clearAndInsert(any())
        }
        confirmVerified(
            robot.repository,
            robot.dao,
        )
    }

    fun check_createListToDb_exception() {
        coVerifySequence {
            robot.repository.createListToDb(any())
            robot.repository.dao
            robot.dao.clearAndInsert(any())
        }
        confirmVerified(
            robot.repository,
            robot.dao,
        )
        assertTrue(robot.resultException is Exception)
    }

    fun check_getListFromDb_success() {
        verifySequence {
            robot.repository.getListFromDb()
        }
        confirmVerified(
            robot.repository,
        )
        assertNotNull(robot.resultLiveList.value)
        robot.resultLiveList.value?.let {
            Assertions.assertEquals(Params.Spotlight().name, it[0].name)
        }
    }

    fun check_getListFromDb_null() {
        verifySequence {
            robot.repository.getListFromDb()
            robot.dao.getList()
        }
        confirmVerified(
            robot.repository,
            robot.dao,
        )
        assertNull(robot.resultLiveList.value)
    }

    fun check_resetStatus(status: ISpotlightRepository.SpotlightRepositoryStatus) {
        assertTrue(
            when (status) {
                ISpotlightRepository.SpotlightRepositoryStatus.GetListFromServer ->
                    robot.repository.statusGetListFromServer.value
            }
                    is Result.None
        )
    }

}
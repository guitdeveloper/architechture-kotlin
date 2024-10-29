package br.com.gtb.features.cash.presenter.ui.fragment

import androidtest.features.cash.R
import br.com.gtb.features.cash.presenter.viewmodel.CashViewModel
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gtb.features.cash.presenter.ui.fragment.CashFragment
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
internal class CashFragmentTest : AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<CashFragment>
    private val cashViewModel: CashViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module { viewModel { cashViewModel } }
            )
        }
        scenario = launchFragmentInContainer()
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun display() {
        onView(withId(R.id.image_cash)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.text_cash_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

}
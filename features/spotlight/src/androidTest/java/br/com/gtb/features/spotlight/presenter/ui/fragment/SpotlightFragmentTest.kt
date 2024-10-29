package br.com.gtb.features.spotlight.presenter.ui.fragment

import androidtest.features.spotlight.R
import br.com.gtb.features.spotlight.presenter.viewmodel.SpotlightViewModel
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gtb.features.spotlight.presenter.ui.fragment.SpotlightFragment
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
internal class SpotlightFragmentTest : AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<SpotlightFragment>
    private val spotlightViewModel: SpotlightViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module { viewModel { spotlightViewModel } }
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
    fun onDisplayList() {
        onView(withId(R.id.list_spotlights)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

}
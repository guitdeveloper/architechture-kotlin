package br.com.gtb.features.product.presenter.ui.fragment

import androidtest.features.product.R
import br.com.gtb.features.product.presenter.viewmodel.ProductViewModel
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.junit.After
import org.koin.core.context.stopKoin
import br.com.gtb.features.product.presenter.ui.fragment.ProductFragment

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
internal class ProductFragmentTest : AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<ProductFragment>
    private val productViewModel: ProductViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module { viewModel { productViewModel } }
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
        onView(withId(R.id.text_product_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.list_products)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

}
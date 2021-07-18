package com.muabdz.simpleshop

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.muabdz.simpleshop.ui.login.LoginActivity
import com.muabdz.simpleshop.utils.DummyData
import com.muabdz.simpleshop.utils.GlobalIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

    private val dummyProducts = DummyData.generateDummyProducts()
    private lateinit var instrumentalContext: Context

    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setUp() {
        instrumentalContext = InstrumentationRegistry.getInstrumentation().targetContext
        IdlingRegistry.getInstance().register(GlobalIdlingResource.getIdlingResource())
    }

    @Test
    fun loadHomeData() {
        onView(withId(R.id.btn_sign_in)).perform(click())
        onView(withId(R.id.rv_categories)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_categories)).perform(swipeLeft())
        onView(withId(R.id.nsv_home)).perform(swipeUp())
    }

    @Test
    fun loadProductDetail() {
        onView(withId(R.id.btn_sign_in)).perform(click())
        onView(withId(R.id.search_bar)).perform(click())
        onView(withHint(instrumentalContext.getString(R.string.search))).check(matches(isDisplayed()))
        onView(withHint(instrumentalContext.getString(R.string.search))).perform(typeText("Nitendo"))
        onView(withId(R.id.rv_products)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_products)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_product_name)).check(matches(withText(dummyProducts[0].title)))
        onView(withId(R.id.tv_description)).check(matches(withText(dummyProducts[0].description)))
        onView(withId(R.id.tv_price)).check(matches(withText(dummyProducts[0].price)))
    }

    @Test
    fun loadPurchaseHistory() {
        onView(withId(R.id.btn_sign_in)).perform(click())
        onView(withId(R.id.rv_products)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_products)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withText(R.string.buy)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_buy)).perform(click())
        onView(withId(R.id.bn_profile)).check(matches(isDisplayed()))
        onView(withId(R.id.bn_profile)).perform(click())
        onView(withId(R.id.rv_products)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(GlobalIdlingResource.getIdlingResource())
    }
}
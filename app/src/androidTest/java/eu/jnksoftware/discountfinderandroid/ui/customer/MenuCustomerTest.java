package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MenuCustomerTest {

    @Rule
    public ActivityTestRule<MenuCustomer> menuCustomerActivityTestRule = new ActivityTestRule<>(MenuCustomer.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void menuCustomerComponentsDisplayed(){
        onView(withId(R.id.tvNewDiscounts)).check(matches(isDisplayed()));
        onView(withId(R.id.filtersBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.settingsBtn)).check(matches(isDisplayed()));
       // onView(withId(R.id.showDiscountsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.aboutBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.showShopsButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testShopsButtonClick(){
        //onView(withId(R.id.showDiscountsButton)).perform(click());
       // onView(withId(R.id.shopsList)).check(matches(isDisplayed()));
    }

    @Test
    public void testAboutButtonClick(){
        onView(withId(R.id.aboutBtn)).perform(click());
        onView(withText("Copyright Alright Reserved")).check(matches(isDisplayed()));
    }


    @After
    public void tearDown() throws Exception {
    }

}
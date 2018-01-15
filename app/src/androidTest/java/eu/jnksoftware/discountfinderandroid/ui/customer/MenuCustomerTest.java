package eu.jnksoftware.discountfinderandroid.ui.customer;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.general.Login;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MenuCustomerTest {

    @Rule
    public ActivityTestRule<Login> menuCustomerActivityTestRule = new ActivityTestRule<>(Login.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void menuCustomerComponentsDisplayed() throws InterruptedException {
        onView(withId(R.id.loginEMailField)).perform(typeText("user@jnksoftware.eu"),closeSoftKeyboard());
        onView(withId(R.id.loginPasswordField)).perform(typeText("myPassword"),closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.menuCustomerDiscountsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.menuCustomerPreferenceButton)).check(matches(isDisplayed()));
        onView(withId(R.id.menuCustomerAboutButton)).check(matches(isDisplayed()));
        onView(withId(R.id.tvMenuCustomerTopDiscounts)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }

}
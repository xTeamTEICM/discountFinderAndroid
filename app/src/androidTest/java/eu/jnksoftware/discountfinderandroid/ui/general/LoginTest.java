package eu.jnksoftware.discountfinderandroid.ui.general;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by kostas on 16/11/2017.
 */
public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<>(Login.class);

    private String email = "kostas@email.com";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testActivity(){
        onView(withId(R.id.eMailField)).check(matches(isDisplayed()));
        onView(withId(R.id.passwordField)).check(matches(isDisplayed()));
        onView(withId(R.id.welcomeTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.loginBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.registerBtn)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }

}
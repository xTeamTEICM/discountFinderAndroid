package eu.jnksoftware.discountfinderandroid;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences.UserUpdatePreferences;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by nikos on 13/1/2018.
 */

public class UserUpdatePreferencesTest {

    @Rule
    public ActivityTestRule<UserUpdatePreferences> userUpdatePreferencesTest = new ActivityTestRule<>(UserUpdatePreferences.class);

    @Test
    public void testActivityComponentsIfDisplayedUpdate(){
        //Test if all the components of the activity are displayed
        onView(withId(R.id.tvFindDiscount)).check(matches(isDisplayed()));
        onView(withId(R.id.tvCategory)).check(matches(isDisplayed()));
        onView(withId(R.id.spinnerCategory)).check(matches(isDisplayed()));
        onView(withId(R.id.tvPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.tvSeekBarValue)).check(matches(isDisplayed()));
        onView(withId(R.id.seekBarPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.btSavePreferences)).check(matches(isDisplayed()));
        onView(withId(R.id.idPrefText)).check(matches(isDisplayed()));
        onView(withId(R.id.tagPrefText)).check(matches(isDisplayed()));
        onView(withId(R.id.tagDesc)).check(matches(isDisplayed()));
        onView(withId(R.id.prefDescUpdate)).check(matches(isDisplayed()));




    }
}

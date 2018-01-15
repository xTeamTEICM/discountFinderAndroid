package eu.jnksoftware.discountfinderandroid;

/**
 * Created by nikos on 13/1/2018.
 */
import android.support.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences.UserPreferenceList;
import eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences.UserPreferences;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UserPreferencesTest {
    @Rule
    public ActivityTestRule<UserPreferences> userPreferencesTest = new ActivityTestRule<>(UserPreferences.class);

    @Test
    public void testActivityComponentsIfDisplayed(){
        //Test if all the components of the activity are displayed
        onView(withId(R.id.tvFindDiscount)).check(matches(isDisplayed()));
        onView(withId(R.id.tvCategory)).check(matches(isDisplayed()));
        onView(withId(R.id.spinnerCategory)).check(matches(isDisplayed()));
        onView(withId(R.id.tvPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.tvSeekBarValue)).check(matches(isDisplayed()));
        onView(withId(R.id.seekBarPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.btSavePreferences)).check(matches(isDisplayed()));
        onView(withId(R.id.tagsText)).check(matches(isDisplayed()));
        onView(withId(R.id.textTag)).check(matches(isDisplayed()));
        onView(withId(R.id.svFindDiscount)).check(matches(isDisplayed()));




    }

}

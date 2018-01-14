package eu.jnksoftware.discountfinderandroid;

import android.support.test.rule.ActivityTestRule;

import com.android21buttons.fragmenttestrule.FragmentTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;
import eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences.UserPreferenceList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class UserPreferenceListTest {
    @Rule
    public FragmentTestRule<?,UserPreferenceList> userPreferenceListFragmentTestRule= FragmentTestRule.create(UserPreferenceList.class);


    @Test
    public void clickAddButton() {
        onView(withId(R.id.userPreferencesBtn)).perform(click());
        onView(withId(R.id.tvFindDiscount)).check(matches(isDisplayed()));

    }
}

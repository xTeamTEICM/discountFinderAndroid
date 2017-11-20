package eu.jnksoftware.discountfinderandroid.ui.general;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> resgisterActivityTestRule = new ActivityTestRule<>(Register.class);

    private String firstName = "Tom";
    private String lastName = "Johnson";
    private String email = "tomJohnson@gmail.com";
    private String wrongEmail = "tomJohnson@gmail";
    private String password = "tom123";
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void checkBlankFirstName(){
        onView(withId(R.id.firstNameField)).perform(clearText(),typeText(""));
        onView(withId(R.id.lastNameField)).perform(clearText(),typeText(lastName));
        onView(withId(R.id.eMailField)).perform(clearText(),typeText(email));
        onView(withId(R.id.passwordField)).perform(clearText(),typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText("Invalid informations")).check(matches(isDisplayed()));
    }

    @Test
    public void checkBlankLastName(){
        onView(withId(R.id.firstNameField)).perform(clearText(),typeText(firstName));
        onView(withId(R.id.lastNameField)).perform(clearText(),typeText(""));
        onView(withId(R.id.eMailField)).perform(clearText(),typeText(email));
        onView(withId(R.id.passwordField)).perform(clearText(),typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText("Invalid informations")).check(matches(isDisplayed()));
    }

    @Test
    public void checkBlankEmail(){
        onView(withId(R.id.firstNameField)).perform(clearText(),typeText(firstName));
        onView(withId(R.id.lastNameField)).perform(clearText(),typeText(lastName));
        onView(withId(R.id.eMailField)).perform(clearText(),typeText(""));
        onView(withId(R.id.passwordField)).perform(clearText(),typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText("Invalid informations")).check(matches(isDisplayed()));
    }

    @Test
    public void checkBlankPassword(){
        onView(withId(R.id.firstNameField)).perform(clearText(),typeText(firstName));
        onView(withId(R.id.lastNameField)).perform(clearText(),typeText(lastName));
        onView(withId(R.id.eMailField)).perform(clearText(),typeText(email));
        onView(withId(R.id.passwordField)).perform(clearText(),typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText("Invalid informations")).check(matches(isDisplayed()));
    }
/*
    @Test
    public void rightEmailValidation(){
        onView(withId(R.id.firstNameField)).perform(clearText(),typeText(firstName));
        onView(withId(R.id.lastNameField)).perform(clearText(),typeText(lastName));
        onView(withId(R.id.eMailField)).perform(clearText(),typeText(email));
        onView(withId(R.id.passwordField)).perform(clearText(),typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withId(R.id.showShopsBtn)).check(matches(isDisplayed()));
    }
*/
    @Test
    public void wrongEmailValidation(){
        onView(withId(R.id.firstNameField)).perform(clearText(),typeText(firstName));
        onView(withId(R.id.lastNameField)).perform(clearText(),typeText(lastName));
        onView(withId(R.id.eMailField)).perform(clearText(),typeText(wrongEmail));
        onView(withId(R.id.passwordField)).perform(clearText(),typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.registerBtn)).perform(click());
        onView(withText("Invalid email")).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }

}
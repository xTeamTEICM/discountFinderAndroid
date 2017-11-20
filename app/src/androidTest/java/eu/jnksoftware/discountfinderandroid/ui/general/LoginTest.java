package eu.jnksoftware.discountfinderandroid.ui.general;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import eu.jnksoftware.discountfinderandroid.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



/**
 * Created by kostas on 16/11/2017.
 */

public class LoginTest {

    @Rule
    public ActivityTestRule<Login> loginActivityTestRule = new ActivityTestRule<>(Login.class);

    private String validEmail = "kostas@email.com";
    private String emptyEmail = "";
    private String password = "12345678";
    private String emptyPassword = "";
    //

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testActivityComponentsIfDisplayed(){
        //Test if all the components of the activity are displayed
        onView(withId(R.id.loginEMailField)).check(matches(isDisplayed()));
        onView(withId(R.id.loginPasswordField)).check(matches(isDisplayed()));
        onView(withId(R.id.loginWelcomeTxt)).check(matches(isDisplayed()));
        onView(withId(R.id.loginBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.loginRegisterBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testRegisterClick(){
        //click the register button
        onView(withId(R.id.loginRegisterBtn)).perform(click());
        //if welcomeText from register activity is displayed then
        //it is correctly launched
        onView(withId(R.id.welcomeTxt)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyEmail(){
        //Give empty email value
        onView(withId(R.id.loginEMailField)).perform(typeText(emptyEmail));
        //click login button
        onView(withId(R.id.loginBtn)).perform(click());
        //check if welcome Text is displayed,then we are still in Login Activity
        onView(withId(R.id.loginWelcomeTxt)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyPassword(){
        //Give empty password value
        onView(withId(R.id.loginPasswordField)).perform(typeText(emptyPassword));
        //click login button
        onView(withId(R.id.loginBtn)).perform(click());
        //check if welcome Text is displayed,then we are still in Login Activity
        onView(withId(R.id.loginWelcomeTxt)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyEmailEmptyPassword(){
        //Give empty email and password values
        onView(withId(R.id.loginEMailField)).perform(typeText(emptyEmail));
        onView(withId(R.id.loginPasswordField)).perform(typeText(emptyPassword));
        //click login button
        onView(withId(R.id.loginBtn)).perform(click());
        //check if welcome Text is displayed,then we are still in Login Activity
        onView(withId(R.id.loginWelcomeTxt)).check(matches(isDisplayed()));
    }

    @Test
    public void testEmptyEmailGivenPassword(){
        //Give empty email and a password
        onView(withId(R.id.loginEMailField)).perform(typeText(emptyEmail));
        onView(withId(R.id.loginPasswordField)).perform(typeText(password));
        //click login button
        onView(withId(R.id.loginBtn)).perform(click());
        //check if welcome Text is displayed,then we are still in Login Activity
        onView(withId(R.id.loginWelcomeTxt)).check(matches(isDisplayed()));
    }

    @Test
    public void testGivenEmailEmptyPassword(){
        //Give empty email and a password
        onView(withId(R.id.loginEMailField)).perform(typeText(validEmail));
        onView(withId(R.id.loginPasswordField)).perform(typeText(emptyPassword));
        //click login button
        onView(withId(R.id.loginBtn)).perform(click());
        //check if welcome Text is displayed,then we are still in Login Activity
        onView(withId(R.id.loginWelcomeTxt)).check(matches(isDisplayed()));
    }
/*
    @Test
    public void testGivenEmailAndPassword(){
        //Give email and password
        onView(withId(R.id.loginEMailField)).perform(typeText(validEmail));
        onView(withId(R.id.loginPasswordField)).perform(typeText(password));
        //click login button
        onView(withId(R.id.loginBtn)).perform(click());
        //if email and password are correct the customer menu will open
        //so i check the shops button in customer menu if it's displayed
        onView(withId(R.id.showShopsBtn)).check(matches(isDisplayed()));
    }
*/
    @After
    public void tearDown() throws Exception {
    }

}
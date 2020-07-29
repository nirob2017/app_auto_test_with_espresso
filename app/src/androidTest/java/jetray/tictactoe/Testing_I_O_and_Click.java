package jetray.tictactoe;
import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@LargeTest
@RunWith(AndroidJUnit4.class)

public class Testing_I_O_and_Click {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void A_FreeTest(){ testing_i_o_and_click(); }



    public void testing_i_o_and_click() {


        SystemClock.sleep(4000);


        onView(withId(R.id.playerone)).perform(replaceText("Nirob"), closeSoftKeyboard());
        onView(withId(R.id.playertwo)).perform(replaceText("Shanto"), closeSoftKeyboard());

        onView(withId(R.id.start)).perform(click());


    }

}




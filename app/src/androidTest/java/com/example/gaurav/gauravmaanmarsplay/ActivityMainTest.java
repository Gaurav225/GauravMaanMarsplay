package com.example.gaurav.gauravmaanmarsplay;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ActivityMainTest {

    @Rule
    public ActivityTestRule<ActivityMain> activityTestRule = new ActivityTestRule(ActivityMain.class);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.gaurav.gauravmaanmarsplay", appContext.getPackageName());
    }


    @Test
    public void checkIfButtonHasTheReqText() {
        Espresso.onView(allOf(withId(R.id.but_open_image_picker))).check(matches(withText("Upload Image")));
    }


}

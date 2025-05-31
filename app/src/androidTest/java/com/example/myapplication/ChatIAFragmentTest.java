package com.example.myapplication;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.myapplication.Views.IA.ChatIAFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ChatIAFragmentTest {

    private static final String requestTest = "Bonjour je veux une recette de lasagne végétale";
    @Before
    public void setUp() {
        FragmentScenario.launchInContainer(ChatIAFragment.class);
    }

    @Test
    public void testSendMessage() {
        // Entrer le texte dans l'input de chat
        onView(withId(R.id.inputchat))
                .perform(typeText(requestTest), closeSoftKeyboard());
        //Clic sur le bouton d'envoi
        onView(withId(R.id.sendmessage)).perform(click());
        // Vérifier que le message apparait sur l'interface utilisateur
        onView(withId(R.id.rvchatUser)).check(matches(withText(requestTest)));
    }
}

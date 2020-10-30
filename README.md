# app_auto_test_with_espresso

## Basic automation testing an android app using Espresso Testing Tools.

Tic_tac_toe is a simple android game app.
My task was testing this app's UI with Espresso automation testing tools. What was my objective, How I test the UI components, what problems I have faced and how I solved
them, everything is given below.


### Objectives:

My objective was to write Espresso automation testing code to launch the app, Inserting First and Second player name. After that I have to click the start button to begin
the game.


### Before Testing:

At first I had to went through all the documentation and tutorials about Espresso Automation Testing Tools and some basic knowledge about android app that I had found in
internet because Espresso and Automation testing were new to me.

From the internet I learnt Espresso is a testing framework for Android that makes it easy to write reliable UI tests for an app. which provides APIs for writing UI tests 
to simulate user interactions within the app, everything from clicking buttons and navigating views to selecting menu items and entering data.

Espresso has basically three components:

ViewMatchers - allows to find view in the current view hierarchy
ViewActions - allows to perform actions on the views
ViewAssertions - allows to assert state of a view

The case construct for Espresso tests is the following:
 ```java
onView(ViewMatcher)       
  .perform(ViewAction)     
    .check(ViewAssertion);    
 ```
https://developer.android.com/training/testing/espresso/basics

https://android.github.io/android-test/downloads/espresso-cheat-sheet-2.1.0.pdf



### Writing code for Testing:

For testing the app I had to import the "tic_toc_toe" project file in android studio. After importing the app I had to install all the dependency that was given on 
"build.gradle(:app)" file and rechecked the default config and dependency were given or not for Espresso Automation testing. For writing the UI testing code and method 
I had to open a class in java>jetray.tictoe in hierarchy named “Testing_I_O_and_click”.

In the class I found some imported classes that are needed to run testing scripts.
In the public class section I found @Rule section:
 ```java
PublicActivityTestRule<SplashScreen>mActivityTestRule=newActivityTestRule<>(SplashScreen.class);
```

Note that the @Rule annotation means that this is a JUnit4 test rule. JUnit4 test rules are run before and after every test method (annotated with @Test). Below this code
I defined a method and wrote espresso code in the method named "testing_i_o_and_click()"

As previously mentioned in the test case, the onView() method is to match one view within the current view hierarchy. For  that I went to the app layout section in 
Tools>Layout Inspector to find the two text fields and click the button's attributes. I found Ids for two text fields and for click button as follows “playerone”, 
“playertwo”, “start”.


#### For inserting text in the textfields i wrote code as follows:

 ```java
onView(withId(R.id.playerone)).perform(replaceText("Nirob"), closeSoftKeyboard());
onView(withId(R.id.playertwo)).perform(replaceText("Shanto"), closeSoftKeyboard());
 ```
onView() method is to match one view within the current view hierarchy. 
withId() matcher for narrowing down the view search of component id.

 ```java perform(replaceText("") ``` is a action to inserting text in the text field, here i could’ve used typeText() method instead of using replaceText("") method. I used 
replaceText("") due to there being some predefined text on the text field like “player 1 name”.

 ```java closeSoftKeyboard()``` method used for when inserting the name or text the keyboard covers the half screen of the view screen that's why i used this method.

For clicking the Start Button i wrote code as follows:

 ```java
onView(withId(R.id.start)).perform(click());
 ```
As previously I searched for  the Start button’s Id and then performed an action using click() function.

Then I run the script. The app was launching but the test was not passing, with an error message :
```error
“android.support.test.espresso.NoMatchingViewException:No views in hierarchy found matching: with id: jetray.tictactoe:id/playerone”
 ```

### Error Handling and Solution:

So I rechecked the espresso code and the full script there was no error on the code but from the error message I learnt it couldn’t find the id of the components.Then 
I ran the app on emulator I found that when launching the app there was picture on the view then after few second there were the text field and the button on the view.

 In the code I found after @Rule annotations:
```java
PublicActivityTestRule<SplashScreen>mActivityTestRule=newActivityTestRule<>(SplashScreen.class);
 ```
That means when I am running the test it's running on “SplashScreen” class.

In the academics I had some experience building “Hello World” types of android apps.
So I went for the java>jetray.tictoe>SplashScreen class. There I found that there were no variables or attributes like “playerone”, “playertwo”, “start”.  And found some 
code like “delaymills: 4000”. I also went to Afterstart and MainActivity classes where I found these variables but Afterstart was used for playing the game and MainActivity
class was used when Inserting name and for starting the game. So, I changed the code after @Rule annotations as follows: 
```java
PublicActivityTestRule<MainActivity>mActivityTestRule=newActivityTestRule<>(MainActivity.class);
 ```
I again ran the test script , and this time the test was successful.
But I was instructed and given that I had to run the test within the SplashScreen class. So i googled how to delay the testing. 

 https://www.semicolonworld.com/question/46470/espresso-thread-sleep.

As the app delays in the splash screen 4 seconds then it comes to MainActivity. And for testing with the unique id or for executing the espresso testing code using 
SplashScreen class,I wrote code as follows:  
```java
SystemClock.sleep(4000);
 ```
And after the @Rule annotation I changed the code as before:
```java
@Rule
public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);
 ```

Finally I ran the Testing_I_O_and_click and the automated test was successfully run both on android emulator and in the android studio.

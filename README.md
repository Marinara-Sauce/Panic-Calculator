## Inspiration

This project was inspired by a domestic violence case, where [a woman disguised a 911 call as a call for pizza] (https://www.youtube.com/watch?v=ZJL_8kNFmTI) to get help from the police without her abuser noticing. After learning about this case, we began to brainstorm ways people in a similar situation can get help while disguising their actions to their partner. We realized everyone has a calculator on their phone, and there are many circumstances where someone may need one (paying tip, calculating the total for a food order, etc.)

1 in 4 women and 1 in 9 men have experienced some form of severe abuse from their partner, whether through physical, sexual or from stalking. Abuse can carry heavy consequences for the victim, from injury, to PTSD. ([Source: NCADV](https://ncadv.org/STATISTICS#:~:text=1%20in%203%20women%20and,be%20considered%20%22domestic%20violence.%22&text=1%20in%207%20women%20and,injured%20by%20an%20intimate%20partner.))

With this in mind, we have created Panic Calculator to provide victims a way to discreetly get help without raising suspicion from their abuser. Panic Calculator allows people to text up to five others that they need help and their location at the push of the button, without anything suspicious appearing on the screen.

## What it does

Panic Calculator looks and behaves like a normal calculator. When opening the app, you are brought to a screen that resembles many Android default calculators. Basic operations and calculations are available.

On first boot, the app brings the user to the settings screen. Here, users enter their name, are able to customize the SOS message to send, and then add 1-5 phone numbers to contact after entering the distress code. Here they can also modify the SOS code and the code to access the settings if needed.

On the calculator screen, by entering "5555" by default and pressing "=" twice, the app will discreetly send a distress text to the phone numbers entered, asking for them to get you help, in addition to your current coordinates so that the receiver can take the necessary actions.

At any time users can access their settings by typing "1234" then hitting "+"

## How we built it

We built this project for the Android OS using Google's Android Studio. We split the team into various tasks to develop the program:

Dan Bliss developed the main app, focusing on integrating everything with the Android API and working on systems such as the SOS message, getting the location, and accessing/storing user settings.

Eric Bliss worked on the UI. He focused on creating a view that closely resembled the Android calculator to keep everything discrete.

Samuel Wyss-Duhamel worked on the actual calculator. It's important that the calculator works in case someone replaces their phone's calculator with this one, so Sam focused on providing all of it's functionality.

## Challenges we ran into

We have had experience with Java and working with IntelliJ before, however integrating that knowledge into the Android API was challenging as this was our first Android project. There were a lot of Google searches on various Android app elements such as what an Activity is, what the "View" is, etc.

Eric specifically ran into challenges working with the UI. Android has plenty of different devices, each with different screen sizes. Trying to design the UI for any screen size was tricky at times.

## Accomplishments that we're proud of

As a team we're all proud of being able to collaborate on a project for the first time. We're proud of how we were able to take up an equal amount of work for our skill, and therefore we were able to maximize our efficiency.

Eric is proud of being able to learn the UI editor for Android, and creating a close replica to the Android calculator from scratch, whereas Sam is proud of being able to develop the calculator component despite him having less than a year of Java experience. Dan is proud of learning the Android API and being able to utilize the various Android features to send text messages and fetch the user's location.

## What we learned

As mentioned above, Eric learned a lot about the UI designer by creating this app, allowing him to make the main UI. Sam learned more about working with objects. Most of his work was communicating with Dan's classes and data, parsing it, then sending it back to one of Dan's classes. By doing this, Sam learned more about working with other classes, object oriented design, and what it's like to work with multiple people to achieve a goal. Dan learned about working with the Android API. He learned more about how to use Android's services, such as location services, storage, and SMS. He learned about how Android runs it's application, and best practices when using these services.

## What's next for Panic Calculator

We want this program to be able to replace the default calculator to improve accessibility. Therefore, we would like to expand the functionality of the calculator to support more operations and mathematical functions, as well as get closer to the look and feel of the default calculator.

In addition Panic Calculator is only compatible with Android devices at the moment, which only reflects a minority of the phone market. In the future we would like to expand support to other versions of Android, as well as creating a version for iPhone that reflects the IOS calculator.
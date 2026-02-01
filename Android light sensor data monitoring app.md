---
subtitle: Introduction
title: Android light sensor data monitoring app
---

Light sensor is a hardware device that measures the amount of light. It
is a photo detector that is used to sense the amount of ambient light
present, and appropriately dim the device\'s screen to match it. The
standard international unit for the illuminance of ambient light is the
lux.

This is a simple Android app which is controlling the user interface and
threshold logic based on sensor values. Currently this app supports the
ambient light sensor.

Project Overview

Project Name: Android light sensor data monitoring app

Objective

The Ambient Light Sensor measures the light intensity or illumination of
the external environment which is expressed in lux(lx) unit. The system
continuously displays real time sensor readings on the screen.

This tutorial serves as an excellent guide for those who want

to get started building sensor-based application using Android studio
and Kotlin.

Technologies Used

\- Android Studio

\- Kotlin

\- Android Sensor Framework

\- ViewBinding

\- Material Design

Understanding Android Sensors

Android sensors are virtual devices that provide data coming from a set
of physical sensors: accelerometers, gyroscopes, magnetometers,
barometer, humidity, pressure, light, proximity and heart rate sensors.
The Android sensor framework lets us access many types of sensors. Some
of these sensors are hardware-based and some are software-based.\
SensorManager is the class whose framework creates an instance of the
sensor service. This class provides various methods for accessing and
listing sensors, registering and unregistering sensor event listeners,
and obtaining orientation information. This class also provides several
sensor constants that are used to report sensor accuracy, determine data
acquisition rates, and calibrate sensors.\
In sensors we can use this class to create an instance of a specific
sensor.\
[SensorEvent]{.underline} : This class provides various methods that let
us determine a sensor\'s capabilities.\
[SensorEventListener:]{.underline} The system uses this class to create
a sensor event object, which provides information about a sensor event.
A sensor event object includes the following information: the raw sensor
data, the type of sensor that generated the event, the accuracy of the
data, and the timestamp for the event.\
We can use this interface to create two callback methods that receive
notifications (sensor events) when sensor values change or when sensor
accuracy changes.

Development Stack

This project is developed using Android studio and Kotlin, utilizing
View Binding and the Android SensorManager API to monitor real-time in
environmental data. The sensor data is read automatically as input
through the sensor framework.

 Project Architecture

The project follows a clean architecture:

-   UI Layer: activity_main.xml using ConstraintLayout

-   Logic Layer: [MainActivity.kt](http://mainactivity.kt/)

-   System Service: SensorManager

-   Lifecycle Handling: Proper register and unregister of sensor
    listeners

[Expected Output]{.underline}

This TextView is positioned in the middle of the screen and is used to
display real-time data from the Light Sensor.

\<androidx.appcompat.widget.AppCompatTextView\
android:id=\"@+id/tv_light_sensor_value\"\
android:layout_width=\"wrap_content\"\
android:layout_height=\"wrap_content\"\
android:text=\"Reading\"\
app:layout_constraintBottom_toBottomOf=\"parent\"\
app:layout_constraintEnd_toEndOf=\"parent\"\
app:layout_constraintStart_toStartOf=\"parent\"\
app:layout_constraintTop_toTopOf=\"parent\" /\>

Operational Workflow

First, we connect to the device\'s built in Ambient Light Sensor using
android sensor framework . Using the SensorEventListener interface, our
app will measure the ambient light intensity (Lux) and automatically
change the interface\'s background color and status using the
ActivityMainBinding based on the received data. This process basically
shows how a smartphone app can directly react to its physical
environment.

Accessing Sensor Properties and Maximum Range 

1.  In Android Studio, an Android application project is created using
    Empty Views Activity with Kotlin. 

2.  To use the ambient light sensor, the required sensor feature is
    declared in the app\'s AndroidManifest.xml file as follows:

View Binding Enablement:

This configuration enables the View Binding feature, which automatically
generates a binding class for each XML layout file. It serves as a
modern, safer, and more efficient replacement for findViewById, ensuring
null safety and type safety while interacting with UI elements.

Initializing SensorManager

The Ambient Light Sensor detects light intensity without requiring
runtime permissions, as it is classified as a non-dangerous sensor; with
a minSdk of 24 (Android 7.0), the app accesses it directly without user
intervention. 

To access sensors, Android provides the SensorManager system service:

1.  Here we use SensorManager, which acts as the entry point to all
    sensors

2.  getDefaultSensor() retrieves the device's built-in light sensor

3.  A null check ensures the app does not crash on devices without the
    sensor

Implementing SensorEventListener

1.  To receive sensor updates, the activity implements
    SensorEventListener.

2.  This requires overriding two methods:

-   onSensorChanged()

-   onAccuracyChanged()

Initialization in onCreate() Method

-   Call super.onCreate() and set up view binding:

-   Check if the sensor is available:

> After this code executes, the sensor can be accessed directly using
> the SensorManager APIs, which provide real-time sensor [values.
> As](http://values.as/) a result, the application can read and display
> the sensor data every time it runs without showing any pop-up or
> asking the user for permission.

Reading Sensor Data

1.  Inside onSensorChanged(), we read the sensor value:

2.  values \[0\] represent the ambient light level in lux.

3.  Based on the lux value, the application categorizes the environment:

-   Dark → Low light

-   Semi-Dark → Moderate light

-   Light → Bright environment

The UI text and background color are updated dynamically to reflect
these conditions

Managing the Activity Lifecycle

> Proper lifecycle handling is critical for sensor-based apps to save
> battery and system resources.

**Summary**

In this article, we will see how to measure the amount of light used in
the ambient light sensor in an Android application. When the app is
launched, a SensorManager is created inside the onCreate() function,
which controls all the sensors of the phone. Then, the light sensor is
obtained using [getDefaultSensor.](http://getdefaultsensor.in/) If your
phone is on Android 7.0 (API 24) or later, it will work directly. The
app first checks whether the sensor is on the phone, then repeatedly
reads the light information received from the sensor and updates it on
the phone screen.

# Covid-19-Cases-Daily-Updates-Android-Application
It is an android application to keep track covid-19 daily cases in India daily wise and state wise.

## Features:
It is able to show covid-19 overall cases in India like Total cases, Total Recovered cases, total deaths, and total outcomes. It will also show daily corona virus cases in India state wise and day wise.And also keep track of daily corona cases in states daily wise.
#### Tools and components:
 1. Java
 2. XML
 3. Json
 4. Volley
 5. Adapter
 6. RecyclerView
 7. Covid-19 APIs
#### Java
 + Android is an open source software platform and Linux-based operating system for mobile devices. The Android platform allows developers to write managed code using Java to manage and control the Android device. Android applications can be developed by using the Java programming language and the Android SDK.
 + Java is 100% object oriented programming language and java can be used to create complete applications that may run on a single computer or distributed among severs and clients in a network.
 + Java is secured because java programs runs inside virtual machine sandbox to prevent any activity from untrusted source.
 + Java is system Independent and High performance progammming language.
#### XML
XML stands for Extensible Markup Language, which gives us a clue to what it does.

+ A markup language is slightly different from a programming language. Whereas a programming language (C#, C++, Java, Kotlin, Python, BASIC) will allow you to define behaviors, interactions, and conditions; a markup language is used more to describe data, and in this case, layouts. Programming languages create dynamic interactions, whereas markup languages generally handle things like static user interfaces.
+ XML is used anywhere that can benefit from adding context to data. It is used across the web to make search smarter and simplify data exchange. XML is actually based on the Standard Generalized Markup Language (SGML), which has been used by the publishing industry for decades.
+ XML performs the same function in Android app development: describing data and introducing elements.
+ To recap, XML describes the views in your activities, and Java tells them how to behave. To make changes to the layout of your app then, you have two main options.

+ The first is to use the Design view. Open up the activity_main.xml file in Android Studio and get your first introduction to XML. You’ll notice there are two tabs at the bottom of that window: Design and Text. The Text view will show you the actual XML code, but the Design view will let you manually edit the layout by dragging and dropping elements into the render of your activity.
+ XML files can also help store strings.
#### JSON
###### What is JSON?
JSON or JavaScript Object Notation is a format for structuring data.
###### What is it used for?
Like XML, it is one of the way of formatting the data. Such format of data is used by web applications to communicate with each other.
###### Characteristics of JSON
+ It is Human-readable and writable.
+ It is light weight text based data interchange format which means, it is simpler to read and write when compared to XML.
+ Though it is derived from a subset of JavaScript, yet it is Language independent. Thus, the code for generating and parsing JSON data can be written in any other programming language.
+ JSON syntax is derived from JavaScript object notation syntax:
#### JSON Vs XML
+ JSON is JavaScript Object Notation.	XML is Extensible markup language
+ JSON is based on JavaScript language.	XML is derived from SGML.
+ JSON is a way of representing objects.	XML is a markup language and uses tag structure to represent data items.
+ JSON does not provides any support for namespaces.	XML supports namespaces.
+ JSON supports array.	XML doesn’t supports array.
+ JSON files are very easy to read as compared to XML.	XML documents are comparatively difficult to read and interpret.
+ JSON doesn’t use end tag.	XML has start and end tags.
+ JSON is less secured.	XML is more secured than JSON.
+ JSON doesn’t supports comments.	XML supports comments.
+ JSON supports only UTF-8 encoding.	XML supports various encoding.
#### Volley
+ Volley library is used to parsing JSON data in android.
+ Volley is a great choice as it runs network operations asynchronously by default, and can handle request queuing and cancellation simply and effectively.
+ Volley is a networking library developed by Google and introduced during Google I/O 2013. It was developed because of the absence, in the Android SDK, of a networking class capable of working without interfering with the user experience.

+ Until the release of Volley, the canonical Java class java.net.HttpURLConnection and the Apache org.apache.http.client were the only tools available to Android programmers to develop a RESTful system between a client and a remote backend.

+ Putting aside for a moment the fact that these two classes aren't exempt from bugs, it should be noted how everything that went beyond a simple HTTP transaction had to be written ex novo. If you wanted to cache images or prioritize requests, you had to develop it from scratch.
+ Read full article Go to [blog...](https://code.tutsplus.com/tutorials/an-introduction-to-volley--cms-23800#:~:text=Volley%20is%20a%20networking%20library,the%20canonical%20Java%20class%20java./)

#### Adapter in Android
+ In Android, Adapter is a bridge between UI component and data source that helps us to fill data in UI component. It holds the data and send the data to an Adapter view then view can takes the data from the adapter view and shows the data on different views like as ListView, GridView, Spinner etc. For more customization in Views we uses the base adapter or custom adapters.

+ To fill data in a list or a grid we need to implement Adapter. Adapters acts like a bridge between UI component and data source. Here data source is the source from where we get the data and UI components are list or grid items in which we want to display that data.

+ [TODO] add image.

###### There are the some commonly used Adapter in Android used to fill the data in the UI components.
+ BaseAdapter – It is parent adapter for all other adapters.
+ ArrayAdapter – It is used whenever we have a list of single items which is backed by an array.
+ Custom ArrayAdapter – It is used whenever we need to display a custom list.
+ SimpleAdapter – It is an easy adapter to map static data to views defined in your XML file.
+ Custom SimpleAdapter – It is used whenever we need to display a customized list and needed to access the child items of the list or grid.
+ Read full article Go to [blog...](https://abhiandroid.com/ui/adapter#Adapters_In_Android/)
#### What is RecyclerView?
RecyclerView is a ViewGroup, which populates a list on a collection of data provided with the help of ViewHolder and draws it to the user on-screen.
###### Building components of RecyclerView
+ Adapter
+ ViewHolder
+ LayoutManager
+ ![Alt text](/Image-resources-screenshots/recyclerview.png?raw=true "Optional Title")
+ Read full article Go to [blog...](https://blog.mindorks.com/how-does-recyclerview-work-internally/)
#### Covid-19 APIs
+ In this project I am using two covid-19 APIs to keep track of corona cases in India.
1. [Go go official](https://github.com/amodm/api-covid19-in) OR [Go to Covid-19 APIs](https://api.rootnet.in/covid19-in/stats/history/)
2. POSTMAN [Go to Covid-19 APIs](https://api.covidindiatracker.com/total.json/)
## Apps-overview-screenshots.



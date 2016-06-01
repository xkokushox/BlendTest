In FreakyByte we are focused in creating experiences instead of simple apps, and for this test i would like you to notice this things that are in the code :


Min SDK is 16 -> This means that the 96 % of the devices will be compatible with our product 
 - http://developer.android.com/intl/es/about/dashboards/index.html

Material Design -> This is the last pattern to design in Android, thats why i used the Guidelines from google to create the UI in this project, including this views:
 - Toolbar
 - RecyclerView
 - CardView
 - Dialogs
 - TabLayout
 - Pull to Refresh
 - https://www.google.com/design/spec/material-design/introduction.html#introduction-goals

List -> I used reclicler views so it doesnt matter how many items you have or the changes that hast to be done on the fly, all the lists will feel smoothly.
 - I created a custom view to autofit the text in case they are big. 
 - In case the content in "notes" has a Url, you can tap it and the device will manage the URL, if the url is from youtube it will open the youtube app in case you have it install it.


Database -> I used SQLite to save the items in the device and use SQL commands to get and filter the data we need really fast. 

REST API -> In this case we used an AsyncHttpClient to retrieve the data and GSON to parse the data, we used Gson because the preformance is better with small files
 - http://blog.takipi.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/

Animation -> I created two animations to make the app looks and feels better:
 - Home Screen : The scroll in the list hide or show the toolbar
 - Categories Screen: I added an animation in the transition of the categories

Vectors -> I used vectors instead of png images to use in the app, with this the images will look good in small or bigger devices and keeps the size of the apk small

Styles -> I created styles to quickly change the look of the views that share that style  

Flavors -> I created two flavors (stage, production) in the app, that means that the app can  create different versions very easely. Each version can have their own images resources, package name, themes, styles, endpoints.
 - http://blog.robustastudio.com/mobile-development/android/building-multiple-editions-of-android-app-gradle/

General -> You can use the app offiline. you can add or remove categories and the app will show show all those categories on the fly.
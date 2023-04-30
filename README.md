# Java_OMDbAPI
 
    Java application to query movie information.

The API used is from OMDb. //Get your key here:
http://www.omdbapi.com/

# About 

    This project was developed as a need of mine to learn to developed and handle HTTP requisitions with Java.
    This is a Maven Java Project.
    Swing was used to build a user-friendly GUI.

    3 dependencies has been used:
        com.squareup.okhttp3 -- To get the API's response
        org.json -- To transform the API's Response into a JSON
        com.jayway.jsonpath -- To get values from the JSON

    A separate class was created (MyPrivateKey.java) to hide my API key that is used in Main.java


# Screenshots

## `Main page`
![all-text](https://github.com/bispo-daniel/Java_OMDbAPI/blob/main/src/Screenshots/MainPageScreenshot.png)

## `Query page` 
![all-text](https://github.com/bispo-daniel/Java_OMDbAPI/blob/main/src/Screenshots/QueryPageScreenshot.png)
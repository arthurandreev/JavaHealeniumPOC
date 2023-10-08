# MLPoweredSeleniumJavaPOC
Java Selenium project to showcase self healing code capability powered by healenium docker backend
This is a proof of concept project to showcase machine learning capability that can be integrated into a Selenium Java project. These capabilities are provided by an open source project called Healenium. Healenium is language agnostic and can be used with other languages such as C#. I have another demo project which I built with C# and dotnet technologies. You can find it in my pinned repositories.

# Project overview
This project has been created to showcase the capability of integrating Healenium library into a Maven Java Selenium project. 
# What is Healenium
Healenium is an open-source library that aims to reduce the maintenance efforts required for Selenium-based UI tests by providing self-healing capabilities. 
It identifies the elements that were not found during test execution and tries to locate them using alternative locators. 
Healenium then provides recommendations to update the locator strategy for the elements that were not found.
When a test fails due to an element not being found, Healenium will search for the element using different attributes or by comparing the element's structure to what it was in the past. 
If it finds a match, it will update the locator information at run time and proceed with running the test using the updated web locator. 
The test will pass if the element is found, and Healenium will log the updated locator information for future reference.
# Benefits of Healenium
The main benefit of using Healenium is that it helps reduce the time and effort spent on maintaining and updating UI tests when the application under test undergoes changes in its user interface. 
By automatically adapting to changes in the UI, Healenium can reduce the number of false-negative test results caused by outdated locators.
Healenium can be used as a plugin for Java-based Selenium projects and is available on GitHub. It can also be used with other programming languages such as C# and Javascript.
To use it, you need to add Healenium as a dependency in your project, add small codes changes to your web driver and make some configuration changes. 
More information and usage instructions can be found in the official documentation: https://healenium.io/

# My setup for this project
[Prerequisites]  
Docker, Angular, Selenium, Maven, Cucumber BDD framework  
The setup involves three things. A Selenium project, an Angular project to test against and Healenium library imported as a Maven dependency.
## Angular project
I created a starter project in Angular. For the purpose of the demo, I just need a simple web page where I can modify web elements to test the self healing capability.
I need to run my tests at least once against the Angular web page to have Healenium take a snapshot of all the web elements I interact with as part of the test. 
The snapshot will be saved to a PostgreSQL db which resides in a docker container. This snapshop will be used to find the closest mathing web element for any web locators that are throwing element not found exceptions.
I followed the steps outlined here to set up my angular application under test - https://angular.io/guide/setup-local

The only thing I added to the starter project provided is the following id which I will be using to locate a button to click on. The id can be found in the app.component.html class in the my-angular.app project.
```
 <a class="card" target="_blank" rel="noopener" href="https://material.angular.io" id="angular-material">
      <svg xmlns="http://www.w3.org/2000/svg" style="margin-right: 8px" width="21.813" height="23.453" viewBox="0 0 179.2 192.7"><path fill="#ffa726" d="M89.4 0 0 32l13.5 118.4 75.9 42.3 76-42.3L179.2 32 89.4 0z"/><path fill="#fb8c00" d="M89.4 0v192.7l76-42.3L179.2 32 89.4 0z"/><path fill="#ffe0b2" d="m102.9 146.3-63.3-30.5 36.3-22.4 63.7 30.6-36.7 22.3z"/><path fill="#fff3e0" d="M102.9 122.8 39.6 92.2l36.3-22.3 63.7 30.6-36.7 22.3z"/><path fill="#fff" d="M102.9 99.3 39.6 68.7l36.3-22.4 63.7 30.6-36.7 22.4z"/></svg>
      <span>Angular Material</span>
      <svg class="material-icons" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"/></svg>
    </a>
```

## Selenium Maven Java project
Just clone down the code here or create your own Selenium Maven Java project.

## Healenium library with Docker
For setup steps that I followed to set it up, check out these links below:

Maven dependency - https://github.com/healenium/healenium-web

Docker containers - https://github.com/healenium/healenium-backend

# DEMO 

To run the project, all you need to do is to clone down this repository. Then navigate to my-angular.app folder and run the following command to start Angular dev server:

```
ng serve --open
The command "ng serve --open" is used in Angular development to start a local development server and automatically open a web browser to preview your Angular application. Here's a brief explanation:

1. **ng serve:** This part of the command is used to start the Angular CLI's development server. It compiles your Angular application's code, bundles it, and makes it available for testing and development.

2. **--open:** This flag is an optional parameter that instructs the Angular CLI to automatically open a web browser. When you run "ng serve --open," it launches a default web browser, which then loads your Angular app. This makes it convenient for developers to quickly see the changes they make to the application in real-time as they code.

In essence, "ng serve --open" is a time-saving command in Angular development. It simplifies the process of starting the development server and immediately visualizing your application in a web browser, allowing developers to iterate and test their code efficiently.
```


[Use case]  
The id that my navigateToAngularMaterialPageTest is using to click on the button that takes me to a new tab has been changed and this will normally make my test fail with no element found exception. The web element itself hasn't changed but because the id has been changed it will make the tests that rely on it to fail resulting in false positive failed tests.  
My element under test
![image](https://user-images.githubusercontent.com/35194143/232344213-9ce1400a-9831-489c-a305-24fcf28f765c.png)  
![image](https://user-images.githubusercontent.com/35194143/232346948-a25e39e0-81d0-4cd2-acc1-e97a9772503b.png)  
![image](https://user-images.githubusercontent.com/35194143/232346966-d58c01fc-ac08-42be-934e-0a1c5397ed8c.png)  
My test case  
![image](https://user-images.githubusercontent.com/35194143/233724460-99fedcc9-0f56-4a95-a93e-c7f9add1064c.png)    
![image](https://user-images.githubusercontent.com/35194143/233773830-b9c1ab70-b8cc-4aa1-becd-1e56028aad6e.png)  

Test run 1 with matching locator(#angular-material) in the angular project and in the selenium project makes the test pass
![image](https://user-images.githubusercontent.com/35194143/232344657-25a1ea69-b5e4-473e-b17b-65767be4fca9.png)    
Angular  
![image](https://user-images.githubusercontent.com/35194143/232348107-a8fbc511-9e97-42f9-b75f-341498dee22a.png)    
Selenium   
![image](https://user-images.githubusercontent.com/35194143/232348144-aa9ecc7f-3f5f-4081-8018-e1102da6953b.png)  
Docker  
![image](https://user-images.githubusercontent.com/35194143/233727440-ba546c39-1daf-428f-b8cf-803c576a5b52.png)  

Test run 2 with a non matching locator in the angular project and in the selenium project and self healing capability switched off. This makes my test fail with no such element exception.
Id is changed in the Angular project from #angular-material to #react-material
![image](https://user-images.githubusercontent.com/35194143/232347461-86fe0044-51ea-424d-8f84-bfd24af25793.png)  
![image](https://user-images.githubusercontent.com/35194143/232347359-28ec0ceb-ca8c-41b5-b36f-61280a58d430.png)  
![image](https://user-images.githubusercontent.com/35194143/232347447-e66165c3-1cde-4b44-9466-6ddc1917f765.png)  
Test run 3 contains the same non matching locator(#angular-material vs #react-material) but has self healing capability switched on which makes my test pass. It does this by fetching the closest matching web locator from PostgreSQL db that contains the snapshot from test run 1 and finds correct web element for the web driver to click on. Healenium needs a healenium.properties file in your solution to configure its settings. Below is the healenium.properties file I have in my solution with explanation of each line.  
![image](https://user-images.githubusercontent.com/35194143/232717543-50ff96b5-bc21-43b6-995d-f5e124f1f4d1.png)
- recovery-tries=1: This line indicates the number of attempts Healenium should make to locate a missing element using its self-healing mechanism.  
- score-cap=.6: This sets the minimum similarity score (between 0 and 1) required for an alternative locator to be considered a match. The value of 0.6 means that Healenium will only consider locators with a similarity score of 60% or higher.  
- heal-enabled=true: This line enables or disables Healenium's self-healing feature. When set to true, Healenium's self-healing capabilities will be active.  
- serverHost=localhost: This line specifies the server host for the Healenium backend service. In this case, it is set to localhost, meaning the service will run on the same machine as the tests.  
- serverPort=7878: This line defines the port on which the Healenium backend service will listen. Here, it is set to port 7878.  
- imitatePort=8000: This line sets the port used for communication between the Healenium backend service and the client (Selenium tests). In this case, it is set to port 8000.  


Test passed!  
![image](https://user-images.githubusercontent.com/35194143/232345734-14672335-f6a0-4b5f-b808-c5fca9e2a825.png)  
Screenshot of the healed web element  
![image](https://user-images.githubusercontent.com/35194143/232347195-f5b458d7-eacc-45f2-83d8-95fc84a04fa4.png)  
Screenshots of all the healed elements can be found in this folder ..\SeleniumWithSelfHealingCodeGen\infra\screenshots

# Summary
This is just a small glimpse into the full capability of healenium. I highly recommend exploring it further and integrating it into your own selenium automation projects. It solves a major problem for automation engineers when it comes to fixing false negative failing tests and has the potential to dramatically reduce automation maintenance work that let's be honest none of us want to do. Let's focus on adding value where it matters...creating tests for new features instead of fixing regression tests that fail for silly reasons like a small change in web locators. 









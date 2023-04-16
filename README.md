# SeleniumWithSelfHealingCode
Maven Java Selenium project to showcase self healing code capability powered by healenium docker backend

# Project overview
This project has been created to showcase the capability of integrating healenium into a Maven Java Selenium project. 
# What is Healenium
Healenium is an open-source library that aims to reduce the maintenance efforts required for Selenium-based UI tests by providing self-healing capabilities. 
It identifies the elements that were not found during test execution and tries to locate them using alternative locators. 
Healenium then provides recommendations to update the locator strategy for the elements that were not found.
When a test fails due to an element not being found, Healenium will search for the element using different attributes or by comparing the element's structure to what it was in the past. 
If it finds a match, it will update the locator information and re-run the test. 
The test will pass if the element is found, and Healenium will log the updated locator information for future reference.
# Benefits of Healenium
The main benefit of using Healenium is that it helps reduce the time and effort spent on maintaining and updating UI tests when the application under test undergoes changes in its user interface. 
By automatically adapting to changes in the UI, Healenium can reduce the number of false-negative test results caused by outdated locators.
Healenium can be used as a plugin for Java-based Selenium projects and is available on GitHub. It can also be used with .Net projects.
To use it, you need to add Healenium as a dependency in your project and make some configuration changes. 
More information and usage instructions can be found in the official documentation: https://healenium.io/

# My setup for this project
The setup involves three things. A Selenium project, an Angular project to test against and healinium Maven dependency.
## Angular project
I created a starter project in Angular. For the purpose of the demo, I just need a simple web page where I can modify web elements to test the self healing capability.
I need to run my tests at least once against the Angular web page to have Healenium take a snapshot of all the web elements I interact with as part of the test. 
The snapshot will be saved to a PostgreSQL db which resides in a docker container. This snapshop will be used to find the closest mathing web element for any web locators that are throwing element not found exceptions.
To setup a starter angular project please follow the steps outlined here - https://angular.io/guide/setup-local

## Selenium Maven Java project
Just clone down the code here or create your own Selenium Maven Java project.

## Healenium Maven Dependency
For setup steps that I followed to set it up, please see this link - https://github.com/healenium/healenium-web

# DEMO 
Use case - the id that my navigateToAngularMaterialPageTest is using to click on the button that takes me to a new tab has been changed and this will normally make my test fail with no element found exception. The web element itself hasn't changed but because the id has been changed it will make the tests that rely on it to fail resulting in false positive failed tests.  
My element under test
![image](https://user-images.githubusercontent.com/35194143/232344213-9ce1400a-9831-489c-a305-24fcf28f765c.png)  
![image](https://user-images.githubusercontent.com/35194143/232346948-a25e39e0-81d0-4cd2-acc1-e97a9772503b.png)  
![image](https://user-images.githubusercontent.com/35194143/232346966-d58c01fc-ac08-42be-934e-0a1c5397ed8c.png)

Test run 1 with matching locator in the angular project and in the selenium project that makes the test pass
![image](https://user-images.githubusercontent.com/35194143/232344657-25a1ea69-b5e4-473e-b17b-65767be4fca9.png)

Test run 2 with a non matching locator in the angular project and in the selenium project and self healing capability switched off. This makes my test fail with element not found exception.
Id is changed in the Angular project
![image](https://user-images.githubusercontent.com/35194143/232344885-7fe9b3ae-cdf5-40a4-b165-7ca56f3442d3.png)  
![image](https://user-images.githubusercontent.com/35194143/232344913-b94013a9-1773-4b7b-ab25-244d0f254a26.png)  
![image](https://user-images.githubusercontent.com/35194143/232345348-2610e902-2993-4d53-b01d-63bf72407699.png)  
![image](https://user-images.githubusercontent.com/35194143/232345408-247cb75b-831d-4bc9-b9b7-57e14f653ea8.png)  

Test run 3 will trigger self healing capability of healenium which makes my test to pass by fetching the closest matching web locator from PostgreSQL db that contains the snapshot from test run 1  
The link text in the web locator in my selenium project still points to the original link text of "Angular Material".  
![image](https://user-images.githubusercontent.com/35194143/232345814-1f0898d0-5682-436f-a380-19598b1c28aa.png)  
The link text in the web locator in my angular project has been changed to "React Material"  
![image](https://user-images.githubusercontent.com/35194143/232345920-48adbd58-9fb6-43ce-b9a7-3a161f175e8a.png)  
![image](https://user-images.githubusercontent.com/35194143/232344885-7fe9b3ae-cdf5-40a4-b165-7ca56f3442d3.png)  

Test passed!  
![image](https://user-images.githubusercontent.com/35194143/232345734-14672335-f6a0-4b5f-b808-c5fca9e2a825.png)  
Screenshot of the healed web element  
![image](https://user-images.githubusercontent.com/35194143/232346211-f2e4bb23-62c3-42c3-823b-def528fa5708.png)  
Screenshots of all the healed elements can be found in this folder ..\SeleniumWithSelfHealingCodeGen\infra\screenshots









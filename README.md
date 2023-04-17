# SeleniumWithSelfHealingCode
Maven Java Selenium project to showcase self healing code capability powered by healenium docker backend

# Project overview
This project has been created to showcase the capability of integrating healenium library into a Maven Java Selenium project. 
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
Healenium can be used as a plugin for Java-based Selenium projects and is available on GitHub. It can also be used with other programming languages such as C# and Javascript.
To use it, you need to add Healenium as a dependency in your project, add small codes changes to your web driver and make some configuration changes. 
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
[Use case]  
The id that my navigateToAngularMaterialPageTest is using to click on the button that takes me to a new tab has been changed and this will normally make my test fail with no element found exception. The web element itself hasn't changed but because the id has been changed it will make the tests that rely on it to fail resulting in false positive failed tests.  
My element under test
![image](https://user-images.githubusercontent.com/35194143/232344213-9ce1400a-9831-489c-a305-24fcf28f765c.png)  
![image](https://user-images.githubusercontent.com/35194143/232346948-a25e39e0-81d0-4cd2-acc1-e97a9772503b.png)  
![image](https://user-images.githubusercontent.com/35194143/232346966-d58c01fc-ac08-42be-934e-0a1c5397ed8c.png)  
My test case  
![image](https://user-images.githubusercontent.com/35194143/232348050-ba46a18c-b1c5-4926-a6e6-ec939c1c648f.png)  

Test run 1 with matching locator(#angular-material) in the angular project and in the selenium project makes the test pass
![image](https://user-images.githubusercontent.com/35194143/232344657-25a1ea69-b5e4-473e-b17b-65767be4fca9.png)    
Angular  
![image](https://user-images.githubusercontent.com/35194143/232348107-a8fbc511-9e97-42f9-b75f-341498dee22a.png)    
Selenium   
![image](https://user-images.githubusercontent.com/35194143/232348144-aa9ecc7f-3f5f-4081-8018-e1102da6953b.png)    

Test run 2 with a non matching locator in the angular project and in the selenium project and self healing capability switched off. This makes my test fail with no such element exception.
Id is changed in the Angular project from #angular-material to #react=material
![image](https://user-images.githubusercontent.com/35194143/232347461-86fe0044-51ea-424d-8f84-bfd24af25793.png)  
![image](https://user-images.githubusercontent.com/35194143/232347359-28ec0ceb-ca8c-41b5-b36f-61280a58d430.png)  
![image](https://user-images.githubusercontent.com/35194143/232347447-e66165c3-1cde-4b44-9466-6ddc1917f765.png)  
Test run 3 will trigger self healing capability of healenium which makes my test to pass by fetching the closest matching web locator from PostgreSQL db that contains the snapshot from test run 1 and will find correct web element for the web driver to click on  

Test passed!  
![image](https://user-images.githubusercontent.com/35194143/232345734-14672335-f6a0-4b5f-b808-c5fca9e2a825.png)  
Screenshot of the healed web element  
![image](https://user-images.githubusercontent.com/35194143/232347195-f5b458d7-eacc-45f2-83d8-95fc84a04fa4.png)  
Screenshots of all the healed elements can be found in this folder ..\SeleniumWithSelfHealingCodeGen\infra\screenshots

# Summary
This is just a small glimpse into the full capability of healenium. I highly recommend exploring it further and integrating it into your own selenium automation projects. It solves a major problem for automation engineers when it comes to fixing false positive failing tests and has the potential to dramatically reduce automation maintenence work that let's be honest none of us want to do. Let's focus on adding value where it matters...creating tests for new features instead of fixing regression tests that fail for silly reasons like a small change in web locators. 









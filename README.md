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
#Benefits of Healenium
The main benefit of using Healenium is that it helps reduce the time and effort spent on maintaining and updating UI tests when the application under test undergoes changes in its user interface. 
By automatically adapting to changes in the UI, Healenium can reduce the number of false-negative test results caused by outdated locators.
Healenium can be used as a plugin for Java-based Selenium projects and is available on GitHub. It can also be used with .Net projects.
To use it, you need to add Healenium as a dependency in your project and make some configuration changes. 
More information and usage instructions can be found in the official documentation: https://healenium.io/

# My setup for this project
I created a starter project in Angular. For the purpose of the demo, I just need a simple web page where I can modify web elements to test the self healing capability.
I need to run my tests at least once against the Angular web page to have Healenium take a snapshot of all the web elements I interact with as part of the test. 
The snapshot will be saved to a PostgreSQL db which resides in a docker container. This snapshop will be used to find the closest mathing web element for any web locators that are throwing element not found exceptions.




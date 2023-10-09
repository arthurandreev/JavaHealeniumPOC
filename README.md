# MLPoweredSeleniumJavaPOC

# Intro
I have come across a machine learning powered library called Healenium that integrates seamlessly with Selemium and solves a major test automation headache of regression tests failling due to small changes in web locators. 

This is a proof of concept project to showcase machine learning capability that can be integrated into a Selenium Java project. These capabilities are provided by an open source project called Healenium. Healenium is language agnostic and can be used with other languages such as C#. I have another POC project which I built with C# and dotnet technologies. It is called MLPoweredSeleniumDotNetPOC and you can find it in my pinned repositories.

Below you will find information on how to build and run the project. You will also find explanations on key components and code blocks in the project. 

# Project overview
This project has been created to showcase the capability of integrating Healenium library into a Maven Java Selenium project. 
## What is Healenium
Healenium is an open-source library that aims to reduce the maintenance efforts required for Selenium-based UI tests by providing self-healing capabilities. 
It identifies the elements that were not found during test execution and tries to locate them using alternative locators. 
Healenium then provides recommendations to update the locator strategy for the elements that were not found.
When a test fails due to an element not being found, Healenium will search for the element using different attributes or by comparing the element's structure to what it was in the past. 
If it finds a match, it will update the locator information at run time and proceed with running the test using the updated web locator. 
The test will pass if the element is found, and Healenium will log the updated locator information for future reference.
## Benefits of Healenium
The main benefit of using Healenium is that it helps reduce the time and effort spent on maintaining and updating UI tests when the application under test undergoes changes in its user interface. 
By automatically adapting to changes in the UI, Healenium can reduce the number of false-negative test results caused by outdated locators.
Healenium can be used as a plugin for Java-based Selenium projects and is available on GitHub. It can also be used with other programming languages such as C# and Javascript.
To use it, you need to add Healenium as a dependency in your project, add small codes changes to your web driver and make some configuration changes. 
More information and usage instructions can be found in the official documentation: https://healenium.io/

## My setup for this project
[Prerequisites]  
Docker, Angular, Selenium, Maven, Cucumber BDD framework  
The setup involves three things. A Selenium project, an Angular project to test against and Healenium library imported as a Maven dependency.
## Angular project
I created a starter project in Angular. For the purpose of the demo, I just need a simple web page where I can modify web elements to test the self healing capability.
I need to run my tests at least once against the Angular web page to have Healenium take a snapshot of all the web elements I interact with as part of the test. 
The snapshot will be saved to a PostgreSQL db which resides in a docker container. This snapshop will be used to find the closest mathing web element for any web locators that are throwing element not found exceptions.
I followed the steps outlined here to set up my angular application under test - https://angular.io/guide/setup-local

The only thing I added to the starter project provided is the following id which I will be using to locate a button to click on. The id can be found in the app.component.html class in the my-angular.app project. In the use case below, I will be changing this id from angular-material to react-material in order to activate Healenium and heal my broken web locator dynamically by intercepting element not found exception and using machine learning algorithm to find the closest match from the baseline snapshot it took during the initial successful test run.
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

To build and run the project, you need to do the following steps:

Clone down the solution to your local machine. Note the solution contains two projects. Angular application and Selenium test project.

## Build and run Angular app

Navigate to my-angular.app folder and run the following command in the terminal to start Angular dev server on localhost http://localhost:4200

```
ng serve --open
The command "ng serve --open" is used in Angular development to start a local development server and automatically open a web browser to preview your Angular application. Here's a brief explanation:

1. **ng serve:** This part of the command is used to start the Angular CLI's development server. It compiles your Angular application's code, bundles it, and makes it available for testing and development.

2. **--open:** This flag is an optional parameter that instructs the Angular CLI to automatically open a web browser. When you run "ng serve --open," it launches a default web browser, which then loads your Angular app.
```

Upon successfull build of the Angualr application, you should see the following page on http://localhost:4200/
![image](https://github.com/arthurandreev/MLPoweredSeleniumJavaPOC/assets/35194143/1fd2a712-a8c9-47b8-8717-050ce99e3cbb)

## Download docker images and spin up docker containers required for Healenium

Navigate to infra folder in the JavaHealeniumPOC in the terminal and run the following command to download images and spin up containers

infra folder is found here and must contain docker compose file
![image](https://github.com/arthurandreev/MLPoweredSeleniumJavaPOC/assets/35194143/e5ad09d3-e96a-4f91-8ce5-9cb3ada9de49)

docker command to run 
```
docker-compose up -d

The Docker command "docker-compose up -d" is used to start containers defined in a Docker Compose configuration file in detached mode. Here's a brief explanation:

1. **docker-compose:** This command is part of Docker Compose, a tool for defining and running multi-container Docker applications. It reads the configuration from a "docker-compose.yml" file in your project directory.

2. **up:** The "up" command tells Docker Compose to create and start containers defined in the Compose file. It ensures that your defined services, networks, and volumes are launched and configured as specified in the Compose configuration.

3. **-d:** The "-d" flag stands for "detached" mode. When you use this flag, Docker Compose starts the containers in the background, and the command prompt is returned to you immediately. This means you can continue using the terminal for other tasks without being attached to the container's logs.
```
To verify that you have all the expected containers running successfuly you can run "docker ps" command in the terminal and see the following output
![image](https://github.com/arthurandreev/MLPoweredSeleniumJavaPOC/assets/35194143/68606a41-5e5f-4e3b-a4b4-d939fba45ef1)

Alternatily open docker desktop and navigate to containers where you should see the following list of running containers under infra
![image](https://github.com/arthurandreev/MLPoweredSeleniumJavaPOC/assets/35194143/82c0d1d3-966d-4e8e-abde-fd082ccf6a73)

### Use case
The id that my navigateToAngularMaterialPageTest is using to click on the button that takes me to a new tab has been changed and this will normally make my test fail with no element found exception. The web element itself hasn't changed but because the id has been changed it will make the tests that rely on it to fail resulting in false positive failed tests. 

To simulate this scenario, firstly you need to run the test at least once when it passes to enable Healenium to save a snapshot of the elements on the page to a Postgres DB. In this scenario the web locators in the Selenium project and the angular application must match. This baseline snapshot image will be used in subsequent test runs to heal elements that face element not found exceptions. 

Selenium project
```
public class HomePage {

    private SelfHealingDriver driver;

    @FindBy(id = "angular-material")
    public WebElement angularMaterial;

    public HomePage(SelfHealingDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
```

Angualr project
```
 <a class="card" target="_blank" rel="noopener" href="https://material.angular.io" id="angular-material">
      <svg xmlns="http://www.w3.org/2000/svg" style="margin-right: 8px" width="21.813" height="23.453" viewBox="0 0 179.2 192.7"><path fill="#ffa726" d="M89.4 0 0 32l13.5 118.4 75.9 42.3 76-42.3L179.2 32 89.4 0z"/><path fill="#fb8c00" d="M89.4 0v192.7l76-42.3L179.2 32 89.4 0z"/><path fill="#ffe0b2" d="m102.9 146.3-63.3-30.5 36.3-22.4 63.7 30.6-36.7 22.3z"/><path fill="#fff3e0" d="M102.9 122.8 39.6 92.2l36.3-22.3 63.7 30.6-36.7 22.3z"/><path fill="#fff" d="M102.9 99.3 39.6 68.7l36.3-22.4 63.7 30.6-36.7 22.4z"/></svg>
      <span>Angular Material</span>
      <svg class="material-icons" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"/></svg>
    </a>
```

To verify that the baseline snapshot of the elements that Selenium interacts with has been saved correctly, you can navigate to this url http://localhost:7878/healenium/selectors/ where you should see the following:


Secondly, you need to navigate to app.component.html class in my-angular-app, change the id for the element below from angular-material to react material. Save this code change. Angular normally does a live reload and your id change is reflected immediatly in [localhost](http://localhost:4200/). However it is a good idea to inspect the element in dev tools to make sure the change has been reflected in the localhost as expected before continuing. 

Before the change
```
 <a class="card" target="_blank" rel="noopener" href="https://material.angular.io" id="angular-material">
      <svg xmlns="http://www.w3.org/2000/svg" style="margin-right: 8px" width="21.813" height="23.453" viewBox="0 0 179.2 192.7"><path fill="#ffa726" d="M89.4 0 0 32l13.5 118.4 75.9 42.3 76-42.3L179.2 32 89.4 0z"/><path fill="#fb8c00" d="M89.4 0v192.7l76-42.3L179.2 32 89.4 0z"/><path fill="#ffe0b2" d="m102.9 146.3-63.3-30.5 36.3-22.4 63.7 30.6-36.7 22.3z"/><path fill="#fff3e0" d="M102.9 122.8 39.6 92.2l36.3-22.3 63.7 30.6-36.7 22.3z"/><path fill="#fff" d="M102.9 99.3 39.6 68.7l36.3-22.4 63.7 30.6-36.7 22.4z"/></svg>
      <span>Angular Material</span>
      <svg class="material-icons" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"/></svg>
    </a>
```
After the change
```
 <a class="card" target="_blank" rel="noopener" href="https://material.angular.io" id="react-material">
      <svg xmlns="http://www.w3.org/2000/svg" style="margin-right: 8px" width="21.813" height="23.453" viewBox="0 0 179.2 192.7"><path fill="#ffa726" d="M89.4 0 0 32l13.5 118.4 75.9 42.3 76-42.3L179.2 32 89.4 0z"/><path fill="#fb8c00" d="M89.4 0v192.7l76-42.3L179.2 32 89.4 0z"/><path fill="#ffe0b2" d="m102.9 146.3-63.3-30.5 36.3-22.4 63.7 30.6-36.7 22.3z"/><path fill="#fff3e0" d="M102.9 122.8 39.6 92.2l36.3-22.3 63.7 30.6-36.7 22.3z"/><path fill="#fff" d="M102.9 99.3 39.6 68.7l36.3-22.4 63.7 30.6-36.7 22.4z"/></svg>
      <span>Angular Material</span>
      <svg class="material-icons" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"/></svg>
    </a>
```

My element under test
![image](https://user-images.githubusercontent.com/35194143/232344213-9ce1400a-9831-489c-a305-24fcf28f765c.png)  
![image](https://user-images.githubusercontent.com/35194143/232346948-a25e39e0-81d0-4cd2-acc1-e97a9772503b.png)  
![image](https://user-images.githubusercontent.com/35194143/232346966-d58c01fc-ac08-42be-934e-0a1c5397ed8c.png)  
My test case  
![image](https://user-images.githubusercontent.com/35194143/233724460-99fedcc9-0f56-4a95-a93e-c7f9add1064c.png)    
![image](https://user-images.githubusercontent.com/35194143/233773830-b9c1ab70-b8cc-4aa1-becd-1e56028aad6e.png)  

### Test run 1 with matching locator(#angular-material) in the angular project and in the selenium project makes the test pass

![image](https://user-images.githubusercontent.com/35194143/232344657-25a1ea69-b5e4-473e-b17b-65767be4fca9.png)    
Angular  
![image](https://user-images.githubusercontent.com/35194143/232348107-a8fbc511-9e97-42f9-b75f-341498dee22a.png)    
Selenium   
![image](https://user-images.githubusercontent.com/35194143/232348144-aa9ecc7f-3f5f-4081-8018-e1102da6953b.png)  
Docker  
![image](https://user-images.githubusercontent.com/35194143/233727440-ba546c39-1daf-428f-b8cf-803c576a5b52.png)  

### Test run 2 with a non matching locator in the angular project and in the selenium project and self healing capability switched off. This makes my test fail with no such element exception.

Id is changed in the Angular project from #angular-material to #react-material
![image](https://user-images.githubusercontent.com/35194143/232347461-86fe0044-51ea-424d-8f84-bfd24af25793.png)  
![image](https://user-images.githubusercontent.com/35194143/232347359-28ec0ceb-ca8c-41b5-b36f-61280a58d430.png)  
![image](https://user-images.githubusercontent.com/35194143/232347447-e66165c3-1cde-4b44-9466-6ddc1917f765.png)  
### Test run 3 contains the same non matching locator(#angular-material vs #react-material) but has self healing capability switched on which makes my test pass. 

It does this by fetching the closest matching web locator from PostgreSQL db that contains the snapshot from test run 1 and finds correct web element for the web driver to click on. Healenium needs a healenium.properties file in your solution to configure its settings. Below is the healenium.properties file I have in my solution with explanation of each line.  
![image](https://user-images.githubusercontent.com/35194143/232717543-50ff96b5-bc21-43b6-995d-f5e124f1f4d1.png)
- recovery-tries=1: This line indicates the number of attempts Healenium should make to locate a missing element using its self-healing mechanism.  
- score-cap=.6: This sets the minimum similarity score (between 0 and 1) required for an alternative locator to be considered a match. The value of 0.6 means that Healenium will only consider locators with a similarity score of 60% or higher.  
- heal-enabled=true: This line enables or disables Healenium's self-healing feature. When set to true, Healenium's self-healing capabilities will be active.  
- serverHost=localhost: This line specifies the server host for the Healenium backend service. In this case, it is set to localhost, meaning the service will run on the same machine as the tests.  
- serverPort=7878: This line defines the port on which the Healenium backend service will listen. Here, it is set to port 7878.  
- imitatePort=8000: This line sets the port used for communication between the Healenium backend service and the client (Selenium tests). In this case, it is set to port 8000.  

### Test passed!  
![image](https://user-images.githubusercontent.com/35194143/232345734-14672335-f6a0-4b5f-b808-c5fca9e2a825.png)  
Screenshot of the healed web element  
![image](https://user-images.githubusercontent.com/35194143/232347195-f5b458d7-eacc-45f2-83d8-95fc84a04fa4.png)  
Screenshots of all the healed elements can be found in this folder ..\SeleniumWithSelfHealingCodeGen\infra\screenshots

Verify that the element has been healed successfully by navigating to http://localhost:7878/healenium/report/ where you will see the following report along with a screenshot of the healed web element
![image](https://github.com/arthurandreev/MLPoweredSeleniumJavaPOC/assets/35194143/03148051-ce83-4276-b6c7-3bbaf77297a8)


# Summary
This is just a small glimpse into the full capability of healenium. I highly recommend exploring it further and integrating it into your own selenium automation projects. It solves a major problem for automation engineers when it comes to fixing false negative failing tests and has the potential to dramatically reduce automation maintenance work that let's be honest none of us want to do. Let's focus on adding value where it matters...creating tests for new features instead of fixing regression tests that fail for silly reasons like a small change in web locators. 









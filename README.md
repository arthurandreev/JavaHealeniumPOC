# Project Title
**MLPoweredSeleniumJavaPOC**

This project demonstrates the integration of Healenium, a machine-learning-powered library, with Selenium for efficient regression testing by addressing issues caused by minor web locator changes.

# Project Description
This proof of concept project showcases the integration of Healenium into a Maven Java Selenium project. It highlights how machine learning capabilities can be integrated into Selenium, addressing the problem of test failures due to small changes in web locators. The project is designed to make regression testing more robust and less prone to false negatives.

## Key Points
- **Healenium**: Healenium is an open-source library that enhances Selenium-based UI tests by providing self-healing capabilities. It identifies elements that were not found during test execution and attempts to locate them using alternative locators.
- **Benefits**: Healenium reduces maintenance efforts required for Selenium UI tests and minimizes false-negative results due to outdated locators.
- **Setup**: The project combines a Selenium project, an Angular project to test, and Healenium as a Maven dependency.

# Table of Contents
- [How to Install and Run the Project](#how-to-install-and-run-the-project)
- [How to Use the Project - Demo](#how-to-use-the-project-demo)

# How to Install and Run the Project
To install and run the project, follow these steps:

1. Clone the project to your local machine, which includes both an Angular application and a Selenium test project with Healenium Maven package already installed. The project also contains a Docker compose file with all the configurations required to spin up the Healenium containers. More information on the Healenium can be found in the links below.

Maven dependency - https://github.com/healenium/healenium-web <br>
Docker containers - https://github.com/healenium/healenium-backend

## Build and Run Angular App
2. Navigate to the "my-angular.app" folder.
3. Run the following command in the terminal to start the Angular development server on localhost: [http://localhost:4200](http://localhost:4200).

```shell
ng serve --open
```

The `ng serve --open` command launches the Angular development server and automatically opens a web browser for previewing the Angular application.

## Download Docker Images and Start Containers
4. Use command prompt or a terminal inside your IDE, navigate to the "infra" folder within the "JavaHealeniumPOC" project. Docker compose file is located in the infra folder.
5. Run the following command in the terminal to download Docker images and spin up the required containers:

```
docker-compose up -d
```

This command starts Docker containers defined in the Docker Compose configuration file in detached mode, allowing the containers to run in the background.

# How to Use the Project - Demo
The demo demonstrates how Healenium can handle changes in web locators to prevent test failures.

1. Ensure that you have a baseline snapshot of the elements. To do this, run the test once to save a snapshot of the elements used during the test.

2. In the Angular project, change the ID of an element, simulating a locator change.

3. Run the test with the changed locator to observe how the self-healing capability works. With Healenium enabled, the test should pass by healing the web element locator.

4. You can verify the self-healing by visiting [http://localhost:7878/healenium/report/](http://localhost:7878/healenium/report/), where you'll find a report and a screenshot of the healed web element.

# Summary
Healenium offers a valuable solution for Selenium automation projects, significantly reducing maintenance work and addressing the common issue of failing regression tests due to minor web locator changes. It's a powerful tool for making your test automation more robust and efficient, allowing you to focus on creating tests for new features rather than constantly fixing failing regression tests. Explore Healenium further to harness its full capabilities and enhance your test automation processes.

# DEMO
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

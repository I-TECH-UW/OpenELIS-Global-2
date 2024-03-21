



# OpenElis Global 2 GSoC'24 guide .

![](https://s3.hedgedoc.org/demo/uploads/cf5e54fa-ec36-48a0-ba54-22fca1649df2.jpeg)

## Introduction
![](https://)

Getting started Guide for onbarding GSoC'24 prospective contributors in `openElis Global 2`.

We understand how painful it might be for new onboarding contributors to get started to `openElis Global 2` , As the project continues to evolve, it’s essential to ensure that our documentation remains up-to-date and accessible. We understand that navigating a new project can be challenging, especially for prospective contributors. Therefore, this documentation serves as a central hub for all information related to our project, making it easier for newcomers to get started.

## OpenELIS Global 2

This laboratory information management tool rewrite onto Java Spring, and with all new technology and features. Please see our [website](http://www.openelis-global.org/) for more information.

You can find more information on how to set up OpenELIS at our [docs page](http://docs.openelis-global.org/)

[![Build Status](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/ci.yml/badge.svg)](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/ci.yml)


[![Publish Docker Image Status](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/publish.yml/badge.svg)](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/publish.yml)


# Contributing

## Understanding Project  structure
`OpenElis uses ` uses a multirepo approach, the project is setup using [maven](https://maven.apache.org/) for both [backend](./src) and [legacy UI](./frontend) but the new version of imporoved UI is build on [React](https://react.dev/) .

OpenELIS Global 2 utilizes a multi-repository approach and leverages Maven for project management. Here's a breakdown of the key folders and their purposes:

* **Frontend**: Contains the new React-based user interface for administrative dashboards.

* **src**: Holds the core Java backend logic, including services, controllers, and data access objects.

* **Volumes**: Stores volume configurations for the database, NGINX server, and Tomcat.

* **Dater export**: (Git submodule) Contains the OpenELIS data store API.
* Below is the a brief explaination of needs/roles of different directories and files.

```
.
├── Dater export              // Git submodule which holds OpenElis data sotre api. 
|
├── Frontend                     // Contains new frontend  UI admin dashboards written in React.
|   |
│   ├── Cypress               // contains cypress configuration for frontend testing
│   ├── nginx                // contains nginx server configurations
│   ├── node_modules        // contains all dependencies needed to run and build the UI.
│   ├── public             // Contains public pages and assets for the project
|   ├── src               // Contains all UI source folders holding pags and react scripts of the project  
|   ├── Dockerfile          // Contains the commands and procedures to build docker images of the project.
|   ├── Dockerfile.prod     // contains neccessary commands and procedures to build base prod docker images for the project.
|   ├── package.json        // provide dependencies management , build scripts , project meta data dependency versions
    ├── package-lock.json
|    
|    
├──src     // this folder holds the actual java backend logic including services, controllers, DAOs , entities , tests,  etc.
|    |   
|    └── main 
|    |     └── Filtered resources   // This contains build.property file which specifies the project version
|    │     └── Java    // Contains packages which defines backend logic
|    |     └── resources     // Contains resources such as persistence , reports and static configurations.
|    |     └── webapp     // Contains formal Java server pages based UI which is being rewritten to React,js
|    │ 
|    ├── test    // This folder holds unit and  integration  tests of the logic.

|    |
|    |
|    |
|    |
├── Volumes       // This folder holds volumes with database, nginx and tomcat configurations.

└── Dockerfile    // contains neccessary commands and procedures to build base prod docker images for the project.
└── Docker-compose.yml  // contains neccessary commands and  steps to build multiple services.
└── .gitmodules  // contains different git modules such as data export
└── pom.xml    // contains project information < artifacts and group id of the project >, dependencies , plugins and build configurations
```

## Technologies and development tools.

### Technologies

OpenELIS Global 2 is built upon a robust technology stack:

* Backend: [Java 11](https://www.oracle.com/java/technologies/downloads/)
* Database: [PostgreSQL](https://www.postgresql.org/)
* Frontend: [JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript) and [React](https://legacy.reactjs.org/docs/getting-started.html)
* Framework: [Spring](https://spring.io/)


### Ensuring Code Quality


We prioritize maintaining high code quality through unit, integration, and end-to-end tests. OpenELIS utilizes various testing tools, including:

* JUnit 5 (https://junit.org/junit5/docs/current/user-guide/)
* Mockito (https://site.mockito.org/)
* TestNG (https://testng.org/)
* Cypress (https://www.cypress.io/)


It is high recommended to rebuild the application and make sure your test cases are passing before opening any pull request.

# Getting Started

## Prerequisites

Familiarity with Java 11 is recommended. If you're using a different version, download Java 11 and set your JAVA_HOME environment variable accordingly.
If you are familiar with these technologies, you are ready to go 🚀. And if not yet don't worry🧐, keep the consistency and make valuable contributions.

## Setting up the project environment.

OpenElis Grobal works well with Java 11  , if you have a different java version you can download the  [Java 11 ](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) and set your JAVA_HOME to point to 11.


## Running OpenELIS in Docker

#### Running docker compose With pre-released docker images
    docker-compose up -d

#### Running docker compose with docker images built directly from the source code
    docker-compose -f build.docker-compose.yml up -d --build

#### Running docker compose With locally compiled/built Artifacts (ie the War file and React code) For Developers
1. Fork the [OpenELIS-Global Repository](https://github.com/I-TECH-UW/OpenELIS-Global-2.git) and clone the forked repo. The `username` below is the `username` of your Github profile.

         git clone https://github.com/username/OpenELIS-Global-2.git 

2. innitialize and build sub modules

        cd OpenELIS-Global-2
        git submodule update --init --recursive
        cd dataexport
        mvn clean install -DskipTests

3.   Build the War file

            cd ..
            mvn clean install -DskipTests

4. Start the containers to mount the locally compiled artifacts

        docker-compose -f dev.docker-compose.yml up -d    

   Note : For Reflecting Local changes in the Running Containers ;
* Any Changes to the [Front-end](./frontend/) React Source Code  will be directly Hot Reloaded in the UI
* For changes to the [Back-end](./src/) Java Source code
    - Run the maven build again  to re-build the War file

           mvn clean install -DskipTests

    -  Recreate the Openelis webapp container

           docker-compose -f dev.docker-compose.yml up -d  --no-deps --force-recreate oe.openelis.org          

#### The Instaces can be accesed at

| Instance  |     URL       | credentials (user : password)|
|---------- |:-------------:|------:                       |
| Legacy UI   |  https://localhost/api/OpenELIS-Global/  | admin: adminADMIN! |
| New React UI  |    https://localhost/  |  admin: adminADMIN!

**Note:** If your browser indicates that the website is not secure after accessing any of these links, simply follow these steps:
1. Scroll down on the warning page.
2. Click on the "Advanced" button.
3. Finally, click on "Proceed to https://localhost" to access the development environment.


#### Thank you for your kind attention to this, Let's build some cool staff together.

If you have any issue, please head to [OpenElis talk forum](https://talk.openelis-global.org/), ask the community and get your desired solution promptly.


## What to checkout next
- [An Overview of OpenElis](https://www.youtube.com/watch?v=YZQMHHaHIcY)

## FAQs
- Why I get this exception when try to natively `Could not find artifact org.itech:dataexport-api:pom:0.0.0.8` trying to run my application with tomcat ?
    - Kindly make sure you have done the second setp well. You can run these commands to solve the issue.

          git submodule update --init --recursive
          cd dataexport
          mvn clean install

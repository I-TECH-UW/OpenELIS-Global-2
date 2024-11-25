# Kapsiki OpenELIS Global

### This is based on OpenELIS 3.1.0  stable release

### Requirements for running
1. Java 11
2. Maven 3.6 +

This is the OpenELIS rewrite onto Java Spring, and with all new technology and
features. Please vist our [website](http://www.openelis-global.org/) for more
information.

You can find more information on how to set up OpenELIS at our
[docs page](http://docs.openelis-global.org/)

### For Running kapsiki openelis global from Source Code

#### Running kapsiki openelis global using docker compose With published docker images on dockerhub

    docker-compose up -d

#### Running kapsiki openelis global using docker compose with docker images built directly from the source code

    docker-compose -f build.docker-compose.yml up -d --build

#### Running kapsiki openelis global using docker compose With locally compiled/built Artifacts (ie the War file and React code)

1.  Fork the
    [kapsiki-openelis-global Repository](https://github.com/kapsiki/kapsiki-openelis-global)
    and clone the forked repo.

         git clone https://github.com/kapsiki/kapsiki-openelis-global

1.  Build the War file

          mvn clean install -DskipTests

1.  Start the containers to mount the locally compiled artifacts

        docker-compose -f dev.docker-compose.yml up -d

    Note : For Reflecting Local changes in the Running Containers ;

- Any Changes to the [Front-end](./frontend/) React Source Code will be directly
  Hot Reloaded in the UI
- For changes to the [Back-end](./src/) Java Source code

  - Run the maven build again to re-build the War file

         mvn clean install -DskipTests

  - Recreate the Openelis webapp container

        docker-compose -f dev.docker-compose.yml up -d  --no-deps --force-recreate oe.openelis.org

#### The Instaces can be accesed at

| Instance     |                   URL                   | credentials (user : password) |
| ------------ | :-------------------------------------: | ----------------------------: |
| Legacy UI    | https://localhost/api/OpenELIS-Global/  |            admin: adminADMIN! |
| New React UI |           https://localhost/            |            admin: adminADMIN! |

**Note:** If your browser indicates that the website is not secure after
accessing any of these links, simply follow these steps:

1. Scroll down on the warning page.
2. Click on the "Advanced" button.
3. Finally, click on "Proceed to https://localhost" to access the development
   environment.

#### Formating the Source code after making changes

1.  After making UI changes to the [frontend](./frontend/) directory , run the
    formatter to properly format the Frontend code

        cd frontend
        npm run format

2.  After making changes to the [backend](./src/) directory, run the formatter
    to properly format the Java code

        mvn spotless:apply

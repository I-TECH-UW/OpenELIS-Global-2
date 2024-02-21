# OpenELIS Global 2
This is the OpenELIS rewrite onto Java Spring, and with all new technology and features. Please see our [website](http://www.openelis-global.org/) for more information. 

You can find more information on how to set up OpenELIS at our [docs page](http://docs.openelis-global.org/)

[![Build Status](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/ci.yml/badge.svg)](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/ci.yml)

[![Publish Docker Image Status](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/publish.yml/badge.svg)](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/publish.yml)

### Running OpenELIS in Docker
#### Running docker compose With pre-released docker images
    docker-compose up -d

#### Running docker compose with docker images built directly from the source code
    docker-compose -f build.docker-compose.yml up -d --build

#### Running docker compose With locally compiled/built Artifacts (ie the War file and React code) For Developers
1. Clone the Repository

         git clone https://github.com/I-TECH-UW/OpenELIS-Global-2.git 

2. innitialize and build sub modules

        git submodule update --init --recursive
        cd OpenELIS-Global-2/dataexport
        mvn clean install -DskipTests

3.   Build the War file

            cd OpenELIS-Global-2
            mvn clean install -DskipTests

4. Start the containers to mount the locally compiled artifacts

        docker-compose -f dev.docker-compose.yml up -d    

    Note : For Reflecting Local changes in the Running Containers ;
 * Any Changes to the [Front-end](./frontend/) React Source Code  will be directly Hot Reloaded in the UI
 * For changes to the [Backend](./src/) Java Source code  
   - Run the maven build again  to re-build the War file

          mvn clean install -DskipTests

   -  Recreate the Openelis webapp container

          docker-compose -f dev.docker-compose.yml up -d  --no-deps --force-recreate oe.openelis.org          

#### The Instaces can be accesed at 

| Instance  |     URL       | credentials (user : password)|
|---------- |:-------------:|------:                       |
| Legacy UI   |  https://localhost/api/OpenELIS-Global/  | admin: adminADMIN! |
| New React UI  |    https://localhost/  |  admin: adminADMIN!


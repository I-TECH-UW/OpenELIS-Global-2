# OpenELIS Global 2
This is the OpenELIS rewrite onto Java Spring, and with all new technology and features. Please see our [website](http://www.openelis-global.org/) for more information. 

You can find more information on how to set up OpenELIS at our [docs page](http://docs.openelis-global.org/)

[![Build Status](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/ci.yml/badge.svg)](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/ci.yml)

[![Publish Docker Image Status](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/publish.yml/badge.svg)](https://github.com/I-TECH-UW/OpenELIS-Global-2/actions/workflows/publish.yml)

### Running OpenELIS in Docker
#### Running with published docker images
    docker-compose up -d

#### Building the docker images directly from source code
    docker-compose -f build.docker-compose.yml up -d --build

#### Running the docker images With locally built Artifacts ie the War file and local frontend Source files
1. Clone the Repository

         git clone https://github.com/I-TECH-UW/OpenELIS-Global-2.git -b 2.8 

2. innitialize and build sub modules

        git submodule update --init --recursive
        cd OpenELIS-Global-2/dataexport
        mvn clean install 

3.   Build the War file

            cd OpenELIS-Global-2
            mvn clean install 

4. Start the containers to mount the locally compiled artifacts

        docker-compose -f dev.docker-compose.yml up -d    

#### The Instaces can be accesed at 

| Instance  |     URL       | credentials (user : password)|
|---------- |:-------------:|------:                       |
| Legacy UI   |  https://localhost/api/OpenELIS-Global/  | admin: adminADMIN! |
| New React UI  |    https://localhost/  |  admin: adminADMIN!


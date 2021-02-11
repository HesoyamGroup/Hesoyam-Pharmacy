# Hesoyam Pharmacy

[![Build Status](https://travis-ci.com/HesoyamGroup/Hesoyam-Pharmacy.svg?token=Aj8KAzqp4JDUk3DxK4c1&branch=develop)](https://travis-ci.com/HesoyamGroup/Hesoyam-Pharmacy)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=22434905a961c51b1d244289381f364488e90bcf&metric=alert_status)](https://sonarcloud.io/dashboard?id=22434905a961c51b1d244289381f364488e90bcf)

## Students

| Students | Team 2 | Student ID Num. |
|--------------|-------------|-------------|
| Student 1 | Aleksa Vučaj | RA-12-2017 |
| Student 2 | Gergelj Kiš | RA-6-2017 |
| Student 3 | Luka Kričković | RA-16-2017 |
| Student 4 | Nikola Dragić | RA-4-2017 |

## How to run

### Prerequisites

It is necessary that you have the following software installed:
- [Apache Maven](https://maven.apache.org/download.cgi)
  - [How to install Maven on Windows/Linux/Mac](https://www.baeldung.com/install-maven-on-windows-linux-mac)
- [Oracle JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
  - [How to set JDK version on Windows/Linux/Mac](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux)

> This method is tested with Maven 3.6.3 and JDK 11. 

#### **Important notes**

- Check out the [.env](.env) file and set your environment variables. List of environment variables:

    - `PORT` - Server port (eg. `55555`)  
    - `POSTGRES_HOST` - In Local Development `localhost`  
    - `POSTGRES_PORT` - Port Number (default is `5432`)  
    - `POSTGRES_DB` - Name of the Database Schema  
    - `POSTGRES_USERNAME` - Name of the Database User  
    - `POSTGRES_PASSWORD` - Password of the Database User  
    - `EMAIL_HOST` - Email SMTP server  
    - `EMAIL_PORT` - Email SMTP server port  
    - `EMAIL_USERNAME` - Email username (most often full email address)  
    - `EMAIL_PASSWORD` - Email password  
    - `FRONTEND_URL` - Frontend url (eg: `http://localhost:8080`)  
- Make sure you have a running instance of Postgres database

### [run.sh](run.sh) script

Run the shell script in terminal:

```
sh ./run.sh
```

**Note**: Windows users can run the script with [GitBash](https://gitforwindows.org/).

## Frontend project

Frontend project can be found at [https://github.com/HesoyamGroup/hesoyam-pharmacy-front](https://github.com/HesoyamGroup/hesoyam-pharmacy-front).

## Files

Object-Oriented Model Made With PowerDesigner - [PDF](assets/model.pdf)

## Technologies

| Technology | Description |
| :--------: | ----------- |
| ![Spring logo](assets/pictures/spring.png) | The [Spring Framework](https://spring.io/projects/spring-framework) is an application framework and inversion of control container for the Java platform.
| ![Maven logo](assets/pictures/maven.png) | [Maven](https://maven.apache.org/) is a build automation tool used primarily for Java projects.
| ![PostgreSQL logo](assets/pictures/postgresql.png) | [PostgreSQL](https://www.postgresql.org/) also known as Postgres, is a free and open-source relational database management system (RDBMS) emphasizing extensibility and SQL compliance. |
| ![TravisCI logo](assets/pictures/travis.png) | [Travis CI](https://travis-ci.com/) is a hosted continuous integration service used to build and test software projects hosted on GitHub and Bitbucket.|
| ![SonarCLoud logo](assets/pictures/sonarcloud.png) | [SonarCloud](https://sonarcloud.io) is a cloud-based code quality and security service and it is the leading online service to catch Bugs and Security Vulnerabilities in Pull Requests and throughout code repositories.
| ![Heroku logo](assets/pictures/heroku.png) | [Heroku](https://dashboard.heroku.com/) is a cloud platform as a service (PaaS) supporting several programming languages.|

## DevOps micro-flow

Git is used as a version control system along with Github as a hosting service. In the development process we complied with the Gitflow workflow, meaning the **develop** branch is protected while we push new code to feature branches. Every feature branch must be merged with the develop branch over a **pull request** where the CI check takes place.

We also used Github's [issue](https://github.com/HesoyamGroup/Hesoyam-Pharmacy/issues) feature with a semi-automated Github [project](https://github.com/orgs/HesoyamGroup/projects/1) combined with linked pull requests. 

### Backend CI/CD

A new pull request triggers the **Travis CI** build [pipeline](https://travis-ci.com/github/HesoyamGroup/Hesoyam-Pharmacy) which builds the code and runs **SonarCloud** for code [analysis](https://sonarcloud.io/dashboard?id=22434905a961c51b1d244289381f364488e90bcf). Build check is enabled on merging pull requests in order to ensure that the code on the develop branch doesn't contain build errors.

A pull request merge automatically triggers deployment to the Heroku platform and deploys the application to [**https://hesoyam-pharmacy.herokuapp.com/**](https://hesoyam-pharmacy.herokuapp.com/)

### Frontend CI/CD

A new pull request merge on the frontend repository triggers an Azure DevOps [CI pipeline](https://dev.azure.com/hesoyam-pharmacy/hesoyam-pharmacy/_build?definitionId=1) which creates a multi-stage built Docker image: the first stage builds the application using a Node image, while the second stage sets-up an Nginx image loaded with the build result and server configuration files. The resulting [image](https://hub.docker.com/r/gregvader/hesoyam-pharmacy-front) is pushed to DockerHub.

A new image push to DockerHub creates a new release on the [CD pipeline](https://dev.azure.com/hesoyam-pharmacy/hesoyam-pharmacy/_release) which deploys the application to [**https://hesoyam-pharmacy-front.herokuapp.com/**](https://hesoyam-pharmacy-front.herokuapp.com/)


## Commit Conventions
`Format: <type>: <commit subject>`

`<type>` - type of commit
`<commit subject>` - Short Description of Commit (less than 50 characters)

#### Types of commit: 
    fix - Bux Fix
    feat - Commit adds new Feature(s)
    refactor - Code refactoring
    style - Correction of Coding Style
    chore - Job that has nothing to do with Production Code (i.e. editing yml file)
    ci
    test
    docs

Put !: If Commit adds some Groundbreaking Changes

Example:

`refactor!: Some breaking change`

Other following information could be put in Commit Description

## Task Distribution*:

| Student 1 | Student 2 | Student 3 | Student 4 | 
|--------------|-------------|-------------|-------------|
| 3.1 View for Unauthenticated User| 3.4 Admin Profile | 3.5 Dermatologist/Pharmacist Profile| 3.2 User Registration and Login |
| 3.3 User Profile| 3.8 Pharmacy Profile | 3.10 Dermatologist Home-Page | 3.6 Sys-Admin Profile |
| 3.9 User Home-Page| 3.12 Admin - Free Appointments Modifying| 3.11 Pharmacist Home-Page| 3.7 Supplier Profile |
| 3.13 Patient - Dermatologist Appointment Reservation | 3.22 Promotions/Sales Defining | 3.14 Dermatologist - Appointment Reservation| 3.23 Promotions/Sales Subscription and Unsubscription |
| 3.15 Patient - Dermatologist Appointment Cancellation| 3.24 Admin - Purchase Order Defining| 3.17 Pharmacist - Counseling Reservation | 3.25 Supplier - Making an Offer|
| 3.16 Patient - Pharmacist Counseling Reservation | 3.26 Admin - Selection of Purchase Order| 3.27 Dermatologist - Appointment Report| 3.34  Medicine Search and Filters|
| 3.18 Patient - Pharmacist Counseling Cancellation | 3.29 Pricelist Modification | 3.28 Pharmacist - Counseling Report| 3.39 Writing Complaints and Replying to Complaints|
| 3.19 Medicine Reservation | 3.32 Dermatologist Search and Filters| 3.30 User Search| 3.42 Medicine and eRecipe Specification |
| 3.20 Reserved Medicine Cancellation | 3.33 Pharmacist Search and Filters | | |
| 3.31 Pharmacy Search and Filters| 3.38 Cancellation/Acceptance of Vaccation Requests | | |
| 3.41 Feedback| | | |

*for more details see - [PDF](assets/ISA-Specification.pdf)

## Grading Criteria

#### Grade: 7
| Criteria |
|--------------|
| Maps |
| Fines |
| Feedback Mechanism |
| Graphical Presentation of the Report |
| 3.21 Handing Out of Reserved Medicine |
| 3.36 Pharmacist - Work Calendar Display |
| 3.37 Dermatologist - Work Calendar Display |
| 3.35 Handing out and Seach eRecipe by QR Code |
| 3.40 Loyalty Program|

#### Grade: 8
|Criteria|
|-------------|
| 5.4 Concurrent Access to Database |

#### Grade: 9
|Criteria|
|-------------|
| DevOps Microflow |
| 5 Unit and 5 Integration Tests per Student |

#### Grade: 10
|Criteria|
|-------------|
| Scalability |

*the lowest passing grade is 6 and the highest is 10

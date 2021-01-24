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

## Environment variables

`POSTGRES_HOST` - In local development `localhost`  
`POSTGRES_PORT` - Port number (default is `5432`)  
`POSTGRES_DB` - Name of the database schema  
`POSTGRES_USERNAME` - Name of the database user  
`POSTGRES_PASSWORD` - Password of the database user

## Files

Object-Oriented model made with PowerDesigner - [PDF](assets/model.pdf)

## Commit Conventions
`Format: <type>: <commit subject>`

`<type>` - type of commit
`<commit subject>` - short description of commit (less than 50 characters)

#### Types of commit: 
    fix - bux fix
    feat - commit adds new feature(s)
    refactor - code refactoring
    style - correction of coding style
    chore - job that has nothing to do with production code (i.e. editing yml file)
    ci
    test
    docs

Put !: If commit adds some groundbreaking changes

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

*for more details about specification ask above mentioned students.

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

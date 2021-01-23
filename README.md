# Hesoyam Pharmacy

[![Build Status](https://travis-ci.com/HesoyamGroup/Hesoyam-Pharmacy.svg?token=Aj8KAzqp4JDUk3DxK4c1&branch=develop)](https://travis-ci.com/HesoyamGroup/Hesoyam-Pharmacy)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=22434905a961c51b1d244289381f364488e90bcf&metric=alert_status)](https://sonarcloud.io/dashboard?id=22434905a961c51b1d244289381f364488e90bcf)

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

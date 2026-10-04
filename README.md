# REST API Automation Framework

A Java-based REST API Automation Framework using Rest Assured, TestNG, Maven, Extent Reports, Apache POI, Java Faker, and Log4j2.

## Project Overview

This project is designed to automate REST API testing using the Swagger Petstore API.

The framework supports API test execution, test data management, reporting, logging, and CI/CD execution through GitHub Actions.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming Language |
| Rest Assured | REST API Automation |
| TestNG | Test Execution |
| Maven | Build & Dependency Management |
| Extent Reports | Test Reporting |
| Apache POI | Excel Test Data |
| Java Faker | Test Data Generation |
| Log4j2 | Logging |
| Git & GitHub | Version Control |
| GitHub Actions | CI/CD |

## API Under Test

Swagger Petstore API:

`https://petstore.swagger.io/v2`

## API Operations

The framework covers the following API operations:

- Create User
- Get User
- Update User
- Delete User

## Project Structure

```text
CSS_RestAPIAutomationFramework
│
├── .github
│   └── workflows
│       ├── rest-api-test.yml
│       └── publish-package.yml
│
├── Reports
│   └── PetStore-Reports-*.html
│
├── TestData
│   └── API test data
│
├── docs
├── logs
│
├── src
│   └── test
│       └── java
│
├── target
├── test-output
│
├── pom.xml
├── testng.xml
└── README.md
```

## Framework Features

- REST API automation using Rest Assured
- TestNG test execution
- Maven project management
- Excel-based test data
- Java Faker for dynamic test data
- Extent HTML reports
- Log4j2 logging
- Automated test execution using GitHub Actions
- GitHub Pages test report
- Maven package published to GitHub Packages

## How to Run

### Clone the Repository

```bash
git clone https://github.com/surajgupta03449-gif/CSS_RestAPIAutomationFramework.git
```

### Navigate to the Project

```bash
cd CSS_RestAPIAutomationFramework
```

### Run Tests

```bash
mvn clean test
```

### Create Maven Package

```bash
mvn clean package
```

## Test Results

The framework generates:

- TestNG results
- Extent HTML reports
- Execution logs
- Maven build artifacts

## GitHub Actions

The project uses GitHub Actions for automated test execution.

Workflow:

```text
GitHub Push
     ↓
GitHub Actions
     ↓
Maven
     ↓
REST API Tests
     ↓
TestNG
     ↓
Extent Report
     ↓
GitHub Pages
```

## GitHub Pages Report

The latest Extent Report is published through GitHub Pages.

[View Test Report](https://surajgupta03449-gif.github.io/CSS_RestAPIAutomationFramework/)

## Maven Package

The project is also published as a Maven package through GitHub Packages.

```text
com.suraj:restassured-api-framework:1.0.0
```

## Release

Current release:

**REST API Automation Framework v1.0.0**

The release contains the packaged JAR file and source code.

## Author

**Suraj Gupta**

QA Engineer Fresher | Software Testing | API Automation | Selenium | Playwright

GitHub:

https://github.com/surajgupta03449-gif

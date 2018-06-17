# TestCompanyApp

This application is a API for REST with examples for GET, POST and PUT, using the Spring framework.

## Getting Started

The project can be downloaded from Github from this URL:


### Prerequisites

JDK 1.8
Maven 3.0 or better
Postman for testing the REST API

### Installing

The installation instructions are for Eclipse IDE, but they will be similar for others IDEs as IntelliJ Idea, NetBeans or others.

Once cloned or downloaded the repository, import the project to your IDE as a Maven project (File > Import > Maven > Existing Maven Projects)
Once imported the project right click on it, and click Maven > Update project
Set a server for running the app. In the development Tomcat v7.0 hasa been the one used for running and testing.

When the app is running an HTML page will be displayed with the message TESTCOMPANYAPP WORKING!
The URLs and the request methods that can be used for trying out the application have this schema:

	http://<server>:<port>/TestCompanyApp/company								--- POST
	http://<server>:<port>/TestCompanyApp/company/{id}						--- GET, PUT
	http://<server>:<port>/TestCompanyApp/company/all							--- GET
	http://<server>:<port>/TestCompanyApp/company/beneficialowner/{id}	--- PUT


## Running the tests

For testing purposes, the integration tests are on the src/test/java folder, concretely in the package com.dro.springprojects.testcompany.integrationtests.
The class is CompanyControllerIT, so just running it with the junit suite, the 10 integration tests will be run.

The tests are these:

	postCompany_ShouldReturnCreatedStatusAndCorrectInfo --- This method tests that we insert a company, and then we receive that same company with its new id and a 201 CREATED

	postCompany_ShouldReturnForbiddenStatus --- This method tests that we insert an existing company, and when we do not get it we receive a 403 FORBIDDEN

	postCompany_ShouldReturnBadRequest --- This method returns a Bad Request, as the object is not valid
   
	getCompany_ShouldReturnCorrectInfo --- This method tests that we get the mocked initial company in our app when searching for it by id. The expected result must be a 200 OK

	getCompany_ShouldReturnNoInfoAsItDoesNotExists --- This method tries to get a non-existing company, so it returns a 404 NOT FOUND
  
	getCompanies_ShouldReturnCorrectInfo --- This method returns all the companies and a 200 OK

	updateCompany_ShouldReturnCorrectInfo --- This methods checks that the process of updating results in a 200 OK and the object updated
  
	updateCompany_ShouldReturnNotFoundStatus --- This methods checks that the process of updating results in a 404 NOT FOUND if the company does not exists
   
	updateCompanyWithBeneficialOwner_ShouldReturnCorrectInfo ---  This method checks that the process of updating results in a 200 OK and the object updated

	updateCompanyWithBeneficialOwner_ShouldReturnNotFoundStatus --- This methods checks that the process of updating results in a 404 NOT FOUND if the company does not exists


Additionally, the cURL commands that can be used with the REST API in the server are:

- Get the mocked company --- curl -H "Content-Type: application/json" -X GET http://localhost:8080/TestCompanyApp/company/1


## Deployment

A maven goal can be created for generating a war file in order to deploy it to a live server.

## Built With

* [Spring](https://spring.io/) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **David Rodriguez** --- [davidrodriguez1317](https://github.com/davidrodriguez1317)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments



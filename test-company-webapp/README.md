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
The URLs and the request methods that can be used for trying out the application in local have this schema:

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


- Get the mocked company with id 1 --- 

	curl -H "Content-Type: application/json" -X GET https://test-company-app.herokuapp.com/company/1

- Insert several companies (A result of 403 will be received if a company with the same name had been previuosly inserted) ---

	curl -X POST https://test-company-app.herokuapp.com/company -H "Content-Type: application/json" --data "{\"name\":\"Big Company v01\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"
	
	curl -X POST https://test-company-app.herokuapp.com/company -H "Content-Type: application/json" --data "{\"name\":\"Big Company v02\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"

	curl -X POST https://test-company-app.herokuapp.com/company -H "Content-Type: application/json" --data "{\"name\":\"Big Company v03\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"

- Get all the companies () --- 

	curl -H "Content-Type: application/json" -X GET https://test-company-app.herokuapp.com/company/all

- Try to insert a company that not validates, i.e name smaller than 2 characters ---

	curl -X POST https://test-company-app.herokuapp.com/company -H "Content-Type: application/json" --data "{\"name\":\"s\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"

- Modification of an existing company ---

	curl -X PUT https://test-company-app.herokuapp.com/company/1 -H "Content-Type: application/json" --data "{\"id\":1,\"name\":\"Big Company v11\",\"address\":\"Elm Street\",\"city\":\"New York\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"

- Modification of a non-existing company (A result of 404 will be recieved) ---

	curl -X PUT https://test-company-app.herokuapp.com/company/11 -H "Content-Type: application/json" --data "{\"id\":11,\"name\":\"Big Company v11\",\"address\":\"Elm Street\",\"city\":\"New York\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"

- Try to insert several beneficial owners to a company

	curl -X PUT https://test-company-app.herokuapp.com/company/beneficialowner/1 -H "Content-Type: application/json" --data "[{\"name\": \"David\",\"percentageOwnership\": 50.1},{\"name\":\"John\",\"percentageOwnership\":49.9}]"


## Deployment

A maven goal can be created for generating a war file in order to deploy it to a live server.

## Built With

* [Spring](https://spring.io/) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **David Rodriguez** --- [davidrodriguez1317](https://github.com/davidrodriguez1317)

## Authentication

The authentication for our REST API should be done using OAuth2 over https. For doing this, and as we are using Spring as our framework, Spring security is to be used.

I personally do not have experience with OAuth2, but I have been working with SAMLv2, and it is similar in several parts:
- Authorization server: known as Identity Provider in SAML, it has to check if the user is allowed to enter.
- Resource server: known as Service Provider in SAML, it is our application, where the resources (endpoints) are.
- Client: our browser

## Service redundancy

Nowadays the best option is the use of microservices, as it is by far the most flexible way of doing it. On the other hand, it increments the difficulty of the implementation, so it has to be dealt with extremely care. For big projects I would suggest this.

Balanced servers on the other hand is the most used historically, and the difficulty of implementation is smaller, so for a small team/application without any experience I would suggest this.

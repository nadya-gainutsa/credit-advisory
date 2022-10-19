The goal of this task is to create a simplified version of the Credit Advisory Service that allows processing credit applications.

**Domain model**

An advisor is a bank employee who works with credit applications. He/she has some personal data like first and last names, and a role (associate, partner, senior).  Each advisor has a list of applications that were assigned to them. 

An applicant is a person who applies for a credit. They must provide their first and last names, social security number, address (city, street, number, zip, apt), and a list of phone numbers (Each number should indicate if it’s a home, work, or mobile). 

An application is a document submitted by the applicant. It has an amount of money (USD), a status (new, assigned, on_hold, approved, canceled, declined), a timestamp of when it was created, and a timestamp of when it was assigned, and a timestamp of when it was resolved.  
The same person can apply multiple times, which will create multiple applications for the same applicant. So it stores a reference to the applicant. In case the application is assigned, it should store a reference to the advisor.

Any person who works with the system (advisor, applicant) is a system user. User management is implemented with AWS Cognito. (It’s a third-party system that is used for authorization and authentication, and you don’t need to care about it.) In our DB, for every user we need to store their email and a corresponding Cognito username. Which is unique, and it identifies the user.

**Features**

Given this domain model, please implement a server app with a single feature – Application Assignment. There should be an endpoint that accepts an advisor id, assigns an application to them, and provides a corresponding response. 
The business logic of this flow is the following:
* Check if the advisor already has an assigned application, if yes then respond with an error
* Load the oldest application with the status “NEW” and requested amount that fits the current advisor’s role
  * associate – under 10 000 USD
  * partner – 10 000 USD to 50 000 USD
  * senior – over 50 000 USD
* If no application found, then respond with a corresponding message
* Assign the application to the given advisor (create a relation)
* Change the application status to “ASSIGNED”
* Set the timestamp when it was assigned
* Return the application
  
**Technology Stack**

* Java 17 
* Spring Boot 2+
* Spring Data JPA + Hibernate
* Flyway
* PostgreSQL 

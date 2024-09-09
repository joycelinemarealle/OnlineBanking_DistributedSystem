
 SUMMARY: Collaborated on the development of an online banking system, implementing RESTful services to support customer registration, account creation, fund transfers, transaction history, and account management; integrated PostgreSQL for database persistence, utilized Docker for local deployment, Git for version control, and followed Agile practices for efficient teamwork and progress tracking.
This project should be completed in teams of three to four. In your teams, you will implement the systems needed to provide online banking services to customers.

PROGRAM DETAILS: From a functionality and domain perspective, your system should:

Enable a customer to register with the bank, providing at least their name. Once registered as a customer, they should be issued with a customer identification number.
The customer should be able to login to the system with their identification number, and see a list of their bank accounts. For each account the name of the account, account number, sort code (routing number) and current balance should be displayed and available.
Customers should be able to create new bank accounts; when they create a bank account they should pick a name for the account (e.g. My Savings Account), and they can set the opening balance. The account should be created with a unique account number, and account numbers should never be re-used.
If a customer has an account, they should be able to close the account. Doing so should return the funds to the customer in cash. Even if an account is closed, the account number associated with the account should never be re-usable.
The customer should be able to view all of the transaction details for their account, including the date/time of the transaction, the type of transaction and the value of the transaction.
The customer should be able to deposit funds into a bank account that they own.
The customer should be able to withdraw funds from a bank account they own. Optionally an account can have an overdraft facility.
The customer should be able to transfer funds from an account they own, to any account as long as they know the account number and sort code (routing number) for the account.
The front-end development team have already produced a web-based front end that supports these functions. To help test the front-end, the web development team have also developed a mock set of backend services. You can download and use these as follows:

docker pull docker-registry.matraining.com/mallon-bank-ui
docker pull docker-registry.matraining.com/mallon-bank-ws
docker run -d -p 8080:8080 docker-registry.matraining.com/mallon-bank-ws
docker run -d -p 80:80 docker-registry.matraining.com/mallon-bank-ui
From there, simply visit http://localhost in your web browser.

For this project your team will need to provide a full set of working RESTful services to support the UI. The application should support all the functionality listed above.

You should use good software engineering practices throughout; this means you should setup and use a Git repository across your team and you should have a JIRA to track progress in addition to consideration of clean code that is re-usable and fully tested. In addition every member of the team must keep a diary documenting what you have worked on, contributed, and any issues you have faced every day. This must be updated daily and kept in your personal git repository for instructors to review.

You will be required to prepare a presentation for the final day of the project; more details on this will be provided in the final week.

Finally, in addition to the core requirements set out above, there are optional extra features you can implement to get extra credit:

Provide persistence of customers, accounts and transactions into a database.
Develop a new and improved user interface using a web toolkit such as Angular or React.
Package your application up as a docker container so that it can be deployed more easily.
Work with another team to connect your banks together, so that customers can transfer funds from an account with your bank, to an account with another bank.
Any other features not asked for that might be useful for customers of your bank.
Minimum RESTful Interface Specification
To help, the front-end development team have provided the following RESTful specification that, if implemented in full, will support the existing user interface.

Hint to further help explore the RESTful interface, use the debugging tools in your browser to examine the request and responses sent by the provided web based user interface and the mock backend services.

General Bank Information
GET /sortCode
This should return the sort code for the bank. This can be used to uniquely identify the bank, and it can be used for routing transactions to other banks.

Example Request:

GET /sortCode
Example Response:

1234
Customers
GET /customer/{customerNumber}
This can be used to find a customer by their unique identifier.

Example Request:

 GET /customer/0
Example Response:

{
    "id":0,
    "fullName": "Mike Clarke",
    "accounts": [ 123456789, 987654321 ]
}
POST /customer
This can be used to register a new customer with the bank.

Example Request:

POST /customer
"Mr. Clarke"
Example Response:

{
    "id": 1,
    "fullName": "Mr. Clarke",
    "accounts": []    
}
DELETE /customer
This can be used to remove a customer from the bank.

Example Request:

 DELETE /customer/0 
Example Response:

123.99
Note the response is the total value of all funds the customer has in all of their accounts. All accounts associated with the customer should be closed.

Accounts
GET /account/{accountNumber}
This is used to retrieve information about an account.

Example Request:

 GET /account/123456789 
Example Response:

{
    "number": 123456789,
    "sortCode": 1234,
    "name": "Example Current Account",
    "openingBalance": 0.00,
    "transactions": [ ... see transaction response ... ],
    "balance": 123.99,
    "customer": 1
}
POST /account
This is used to open a new account.

Example Request:

POST /account
{
    "customerId": 1,
    "accountName": "Savings",
    "openingBalance": 100.00
}
Example Response:

{
    "number": 999999999,
    "sortCode": 1234,
    "name": "Savings",
    "openingBalance": 100.00,
    "transactions": [ ],
    "balance": 100.00,
    "customer": 1
}
DELETE /account/{accountNumber}
This is used to close an account.

Example Request:

 DELETE /account/999999999 
Example Response:

100.00
Note the response is the final balance of the account before it is closed and deleted.

Transactions
POST /transaction
This is used to cause a new transaction to execute.

Example Request (Deposit Funds):

POST /transaction
{
    "type": "DEPOSIT",
    "fromAccount": null,
    "fromAccountSortCode": null,
    "toAccount": 123456789,
    "toAccountSortCode": 1234,
    "amount": 10.00
}
Example Request (Withdraw Funds):

POST /transaction
{
    "type": "WITHDRAWAL",
    "fromAccount": 123456789,
    "fromAccountSortCode": 1234,
    "toAccount": null,
    "toAccountSortCode": null,
    "amount": 5.00
}
Example Request (Transfer Funds):

POST /transaction
{
    "type": "TRANSFER",
    "fromAccount": 123456789,
    "fromAccountSortCode": 1234,
    "toAccount": 987654321,
    "toAccountSortCode": 4444,
    "amount": 150.00
}
Example Response (All cases):

{
    "time": 02-11-2022-17:06:01PM,
    "type": "TRANSFER",
    "fromAccount": 123456789,
    "fromAccountSortCode": 1234,
    "toAccount": 987654321,
    "toAccountSortCode": 4444,
    "amount": 150.00
}
Pass FailSearch: 
Title	Passes	Fails	Completes
Download
Quiz ResultsSearch: 
Name	Date	Right	WrongPasses Fails
Download
Download
SummarySearch: 
Title	Views	Dependencies	Users
Download

The Knowledge Transfer Platform

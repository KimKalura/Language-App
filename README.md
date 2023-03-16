# Hi, I'm Raluca! 👋

And here you can find the documentation of the LanguageApp project
## 🚀 About Me
💻(Aspiring) back-end software developer | 👨‍💻Helping companies to build great back-ends | Java, Spring Boot | Passionate about solving problems using technology | 💼 Actively looking for a job |


## 🛠 Skills
Back-end development · Unit Testing · Java · Algorithms · OOP · Relational Databases · SQL · Git · HTML · CSS · Web services · REST APIs · Spring Boot · Spring Framework · MySQL · Data Structures · Web Development · Software Development


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://kimkalura.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/floriana-raluca-deftu/)


# LanguageApp

This application was created for learning and practicing a foreign language. The purpose is an educational one, of online cultural exchange, where polyglots and lovers of fiction and writing short prose/poetry will be able to interact.


## Features

As a user, I can:
- add a literary work(prose or poetry)
- add translation or romanization for a text
- view all prose
- view all poetry
- view a prose by language
- delete a literary work
- add like
- add literary work to favorite list
- view all favorite literary work by user
- delete from favorite list
- add a quote
- add a comment
- delete a comment

As an admin, I can:
- approve a quote
- view all approved quotes
- view all unapproved quotes


## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

You can view the demo here:

(insert link to video demo)


## API Reference

#### Add a literary work

```http
  POST /literaryWork/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of literary work to be added  |

Request body example:

```json
 {
    "literaryWorkType":"enum",
    "originalLanguage": "string",
    "title":"string",
    "text":"string",
    "translatedLanguage": "string",
    "translatedTitle": "string",
    "translatedText": "string",
    "romanizationText": "string"
  }
```  

Postman example:
![App Screenshot](https://i.imgur.com/AtCa7Fu.png)

#### Add translation or romanization for a text

```http
  POST /literaryWork/addtranslation
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of translation to be added  |

Request body example:

```json
  {
    "id": 0,
    "language": "string",
    "translatedTitle": "string",
    "translatedText": "string",
    "romanizationText": "string"
  }
```

Postman example:
![App Screenshot](https://i.imgur.com/845MgVM.png)


#### View all prose

```http
  GET /literaryWork/allProse
```

#### View all poetry

```http
  GET /literaryWork/allPoetry
```

#### View a prose by language

```http
  GET /literaryWork/${id}/{language}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. Id of a literary work(prose) to fetch  |

#### Delete a literary work

```http
  DELETE /literaryWork/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of literary work to delete |

#### Add like

```http
  POST /literaryWork/like/${literaryWorkId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of literary work to add like |


#### Add literary work to favorite list

```http
  POST /favoriteLiteraryWork/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of favorite literary work to be added  |

Request body example:

```json
  {
    "literaryWorkPostId": 0,
    "userId": 0
  }
```

#### View all favorite literary work by user

```http
  GET /favoriteLiteraryWork/${userId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of user to fetch |


#### Add quote

```http
  POST /quote/add/${userId}
```
| Parameter | Type     | Description                                       |
|:----------|:---------|:--------------------------------------------------|
| `id`      | `string` | **Required**. Id of user to fetch                 |
| `body`    | `json`   | **Required**. The properties of quote to be added |

Request body example:

```json
{
  "text": "string"
}
```

#### Add comment

```http
  POST /comment/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of comment to be added  |

Request body example:

```json
{
  "comment": "string",
  "literaryWorkPostId": 0,
  "photoId": 0,
  "quoteId": 0
}
```

#### Delete comment

```http
  DELETE /comment/delete/${commentId}
```

#### Approve a quote

```http
  GET /quote/${quoteId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of quote to fetch |


#### View all approved quotes

```http
  GET /quote/getAllApprovedQuotes
```


#### View all unapproved quotes

```http
  GET /quote/getAllUnapprovedQuotes
```



## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "password": "string",
  "username": "string"
}
```  

#### Register

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  "country": "string",
  "dateOfBirth": "2023-02-22T17:04:07.317Z",
  "email": "string",
  "nationality": "string",
  "nativeLanguage": "string",
  "password": "string",
  "practicedLanguage": "string",
  "username": "string"
}
``` 

![App Screenshot](https://i.imgur.com/2LxovtI.png)

After running the authenticate request, the client will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  

## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For deploying on Heroku you need:
- GIT
- Heroku CLI
## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.

## Installation

Clone the project

```bash
  git clone https://github.com/KimKalura/Language-App-Final-Project
```
Go to the project directory

```bash
  cd my-project
```

## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Environment Variables

You can deploy this project using Heroku or other providers as well, by specifying the following environment variables:

`PROFILE`

`MYSQL_URL`

`MYSQL_USER`

`MYSQL_PASS`



## Deployment

To deploy this project run

```bash
  git push heroku master
```


## Usage

You cand use demo version of the app, using SwaggerUI and following this link:

```bash
https://safe-wildwood-25661.herokuapp.com/swagger-ui/
```

First, obtain an access token by running the /authenticate endpoint with username "user" and password "pass", which will grant you admin rights in the application.

![App Screenshot](https://i.imgur.com/GX34Sdn.png)


After that, authorize yourself by clicking the authorize button and copy-pasteing the token from the response.

![App Screenshot](https://i.imgur.com/arTX2Ke_d.webp?maxwidth=760&fidelity=grand)

From now on, you can use all other available endpoints, as per swagger documentation.
## Roadmap

In the future, application can be extended with following:
- dictionary API - it will include an API with definitions, examples, audio pronunciation, and adding to the user's list of favorite words

- features for picture + API for the location where a picture was taken

- chat room - users can practice their language skills with other learners or native speakers

- recording voice tool -a user who is learning a language to record himself while reading a text in the target language, so that he can then receive feedback on the pronunciation from another user who knows that language


## Badges

![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)



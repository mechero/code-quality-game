# Code Quality Game [![Build Status](https://travis-ci.org/mechero/code-quality-game.svg?branch=master)](https://travis-ci.org/mechero/code-quality-game)

A simple web application to improve **code quality** using Gamification with SonarQube.

## Introduction

This is a very simple web page that shows a ranking of developers by how much technical debt they are fixing on SonarQube. It encourages a 'friendly competition' and tries to solve one of the main problems of fixing legacy code: **it's boring**.

You can find all the information about this game and the background of this project on [The Practical Developer site](https://thepracticaldeveloper.com/code-quality-game/)

This README file describes the technical part of the game: how to build and run the application.

## Requirements

* Java 10+
* Docker (recommended) or Maven/JDK10/Node.js
* A SonarQube (or SonarCloud) server to connect to. The application is tested up to version 7.1
  * *IMPORTANT*: If you use Java 10, you may need to update the SonarJava sensor so it's able to read the coverage reports.
  * You may need to create a user in SonarQube for this application to connect to (if your server doesn't provide anonymous access). See *Connection to the server* for more details.
  * This repository includes a `docker-compose-sonar.yml` file that you can use to deploy a SonarQube instance on Docker.
  * By default, this project connects to its own repository on SonarCloud.

## Running the app

The easiest way to build and run the app is via Docker Compose:

```
$ docker-compose -f docker-compose-game.yml up
```

Then, you can access the app by navigating with your browser to `http://localhost:3000`

You can also build the components and run them locally. This application has two parts:

- The backend side is a Spring Boot application. Normally, you need to create a distributable file (`.war` in this case) and run it using Java, but you can also run it directly from the `sonar-connector` folder by executing `mvn spring-boot:run`. Check the configuration section before running it, since you need to change the SonarQube connection settings.
- The frontend side is built with Angular. You can get it up and running by executing first `npm install` (to install the required dependencies) and then `npm start` (to run it in development mode) from the `frontend` folder.

If you don't have a SonarQube server available and want to test locally, there is a `docker-compose-sonar.yml` in the repository as well. If you execute `docker-compose -f docker-compose-sonar.yml up` within the root folder, you should get an instance running at `http://localhost:9000` (or the Docker VM IP if you're running Docker in a VirtualBox environment like boot2docker).

## Configuration

### Connection to the server

You can connect this application to your own SonarQube server or also to [SonarCloud.io](https://sonarcloud.io). Go to the `application.properties` file and set the URL of your server.

```
sonarUrl=https://sonarcloud.io
```

By default, the app tries to connect in anonymous mode (not authenticated) but, if your server is protected via login, you should create an API Token for a specific user in Sonar and set it to the property `sonarToken`.

The property `sonarOrganization` should be used if you connect to SonarCloud or you have several Organizations configured in your server and you want to discriminate. To do that, just set that property to the value displayed in your Sonar's settings.

### Adding users

You need to list the users that are going to participate in the game in the file `users.xml` (Yes, I know, XML...).

```xml
<user id="mechero@github" alias="Moises" team="Team TPD.IO" />
```

It's important that the `id` matches with the SonarQube username (you can double-check that in Sonar's member settings). The `alias` will be used when displaying the rankings and the `team` is used to aggregate score per teams (so make sure you repeat some values if you want to use it).

> Given that this application does not have persistence, it will use only the existing issues in Sonar. Take this into account so, if you're keeping history of resolved issues only for a given time, after that time the scores will change.

### Legacy Date

The main goal of this application is fixing *Legacy Code* (old, ugly, error-prone). To be able to customize that for every project the property `legacyDate` in the `application.properties` file allows you to set up from which day backwards you're considering your code *legacy*. If you're setting up a new environment with a new SonarQube server, you can just set the date to today.

## How to play

The application searches issues assigned to users, and it gives points when they get solved. Therefore, playing is easy: just assign the issue to the proper user in Sonar (the one who fixed it or it's planning to) and fix those annoying code pieces! If you want more information you can find it on the TPD blog.

## Feedback

This is a work in progress, and I'd appreciate if you give me some feedback. Do you have any ideas? Is anything not working for you? Questions? Please create an issue.

## Contribute

If you like this project please star it, that at least helps me get a boost of motivation. You can also [buy me a coffee](https://www.buymeacoffee.com/ZyLJNUR).

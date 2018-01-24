# Code Quality Game [![Build Status](https://travis-ci.org/mechero/code-quality-game.svg?branch=master)](https://travis-ci.org/mechero/code-quality-game)

A simple gamified web application to improve code quality using SonarQube.

## Introduction

This is a (micro) web page that shows a ranking of developers by how much technical debt they are fixing on SonarQube. It encourages a 'friendly competition' and tries to solve one of the main problems of fixing legacy code: **it's boring**.

You can find all the information about the goal and the background of this project on [my blog post](https://thepracticaldeveloper.com/2015/06/23/a-gamification-experiment-with-sonarqube/)

## Requirements

* Java 1.8+
* Maven: only needed to build the backend project, later you can run directly the jar file.
* Node.js: to run the frontend application.
* A SonarQube server to connect to. The application is tested up to version 6.7 (Jan 2018).
  * You may need to create a user in SonarQube for this application to connect to (if your server doesn't provide anonymous access).
* This repository includes a `docker-compose.yml` file that you can use to deploy a SonarQube instance on Docker. To do that, you need to install **Docker**.

## Running the app

This application has two parts:

- The backend side is a Spring Boot application. Normally, you need to create a distributable file (`.war` in this case) and run it using Java, but you can also run it directly from the `sonar-connector` folder by executing `mvn spring-boot:run`. Check the configuration section before running it, since you need to change the SonarQube connection settings.
- The frontend side is built with Angular. You can get it up and running by executing `npm start` (development mode) from the `frontend` folder.

If you don't have a SonarQube server available, there is a `docker-compose.yml` in the repository as well. If you execute `docker-compose up` within the root folder, you should get an instance running at `http://localhost:9000` (or the Docker VM IP if you're running Docker in a VirtualBox environment).

## Configuration

### Connection to the server

You can connect this application to your own SonarQube server or also to [SonarCloud.io](https://sonarcloud.io). Go to the `application.properties` file and set the URL of your server.

```
sonarUrl=https://sonarcloud.io
```

By default, the app tries to connect in anonymous mode (not authenticated) but, if your server is protected via login, you should create an API Token for a specific user in Sonar and set it to the property `sonarToken`.

The property `sonarOrganization` should be used if you connect to SonarCloud or you have several Organizations configured in your server and you want to discriminate. To do that, just set that property to the value displayed in your Sonar's settings.

### Adding users

You need to list the users that are going to participate in the game in the file `users.xml`.

```xml
<user id="mechero@github" alias="Moises" team="Team TPD.IO" />
```

It's important that the `id` matches with the SonarQube username (you can double-check that in Sonar's member settings). The `alias` will be used when displaying the rankings and the `team` is used to aggregate score per teams (so make sure you repeat some values if you want to use it).

> Given that this application does not have persistence, it will use only the existing issues in Sonar. Take this into account so, if you're keeping history of resolved issues only for a given time, after that time the scores will change.

### Legacy Date

The main goal of this application is fixing *Legacy Code* (old, ugly, error-prone). To be able to customize that for every project the property `legacyDate` in the `application.properties` file allows you to set up from which day backwards you're considering your code *legacy*. If you're setting up a new environment with a new SonarQube server, you can just set the date to today.

## How to play

The application searches issues assigned to users, and it gives points when they become solved. Therefore, playing is easy:

* Ensure that the code you're going to refactor is covered by Unit Tests. This is not really part of the game but just good software development practices, anyway it's good to keep it mind. Try to avoid changing code
* Select the issues you want to fix in SonarQube, and assign them to you.
* Fix the issues! Once the code is reviewed by Sonar, the issue will become Solved and you will obtain points for that.

## Badges

Right now there are two sample badges that you can win.

* **Early Bird**. Configure the `earlyBirdDate` in the properties file. Those players who solve issues before that date will get that badge. This is to promote a better adoption of the game.
* **Unit Tester Ranks**. These ones are related to the Unit Test Coverage and there are different ranges. If you assign to yourself issues about code coverage and fix them, you'll get badges as soon as you pass 1, 10, 25 and 50 issues. Explore the code to know more about them.

## Feedback

This is a work in progress, and I'd appreciate if you give me some feedback. Do you have any ideas? Is anything not working for you? Questions? Please open an issue in GitHub.

## Contribute

If you like this project please star it, that at least help with a boost of motivation. If you want to go further you can also [buy me a coffee](https://www.buymeacoffee.com/ZyLJNUR).

If you want to know more about the story behind this project, check [my blog post](https://thepracticaldeveloper.com/2015/06/23/a-gamification-experiment-with-sonarqube/).

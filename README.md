# Quboo - Code Quality Game [![Build Status](https://travis-ci.org/mechero/code-quality-game.svg?branch=master)](https://travis-ci.org/mechero/code-quality-game)

A simple web application to improve **code quality** using Gamification with SonarQube.

## Introduction

This is a very simple web page that shows a ranking of developers by how much technical debt they are fixing on SonarQube. It encourages a 'friendly competition' and tries to solve one of the main problems of fixing legacy code: **it's boring**.

You can find all the information about this game and the background of this project on [The Official Quboo website](https://quboo.tpd.io)

## Installation

Simple way: go to [Installation section](https://quboo.tpd.io/docs/installation) in the Quboo website and follow the instructions.

## Contribute

If you like this project you can [become a patron](https://www.patreon.com/quboo) and get some benefits (no banners, extra badges). You can also star it on GitHub.

In case you want to become a code contributor, you can submit your Pull Request. The best option for new functionalities is that you contact me in advance to see if I can integrate it. If it gets accepted, I'll include you in the list of contributors and you'll get a code.

## Building the project yourself

**From here onwards, this README file describes the game code. If you just want to install it and use it, you better continue reading the instructions on the [Quboo website](https://quboo.tpd.io).**

### Requirements

* Java 10+
* Docker (recommended) or Maven/JDK10/Node.js
* A SonarQube (or SonarCloud) server to connect to. The application is tested up to version 7.1
  * *IMPORTANT*: If you use Java 10, you may need to update the SonarJava sensor so it's able to read the coverage reports.
  * You may need to create a user in SonarQube for this application to connect to (if your server doesn't provide anonymous access). See *Connection to the server* for more details.
  * This repository includes a `docker-compose-sonar.yml` file that you can use to deploy a SonarQube instance on Docker.
  * By default, this project connects to its own repository on SonarCloud.

### Building and Running the app

The easiest way to build and run the app is via Docker Compose:

```
$ docker-compose -f docker-compose-game.yml up
```

**Note that you have to configure the game settings via environment variables, as described in the section Configuration on the [Quboo website](https://quboo.tpd.io).**

The command above will build the application inside docker containers the first time you run it, and will generate the corresponding Docker images.

After everything is built and the docker images are up and running, you can access the app by navigating with your browser to `http://localhost:1827`

You can also build the components and run them locally. This application has two parts:

- The backend side is a Spring Boot application. Normally, you need to create a distributable file (`.war` in this case) and run it using Java, but you can also run it directly from the `sonar-connector` folder by executing `mvn spring-boot:run`. Check the configuration section before running it, since you need to change the SonarQube connection settings.
- The frontend side is built with Angular. You can get it up and running by executing first `npm install` (to install the required dependencies) and then `npm start` (to run it in development mode) from the `web-client` folder.

If you don't have a SonarQube server available and want to test locally, there is a `docker-compose-sonar.yml` in the repository as well. If you execute `docker-compose -f docker-compose-sonar.yml up` within the root folder, you should get an instance running at `http://localhost:9000` (or the Docker VM IP if you're running Docker in a VirtualBox environment like boot2docker).

## Feedback

You can help making this game better. Just contact me or open an issue. Any feedback is appreciated!

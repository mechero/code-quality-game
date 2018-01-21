# Code Quality Game [![Build Status](https://travis-ci.org/mechero/code-quality-game.svg?branch=master)](https://travis-ci.org/mechero/code-quality-game)

A simple gamified web to improve code quality using SonarQube.

## Introduction

This is a (micro) web page that shows a ranking of developers by how much technical debt they are fixing on SonarQube. It encourages a 'friendly competition' and tries to solve one of the main problems of fixing legacy code: **it's boring**.

You can find all the information about the goal and the background of this project on [my blog post](https://thepracticaldeveloper.com/2015/06/23/a-gamification-experiment-with-sonarqube/)

## Requirements

* Java 1.8+
* Maven: only needed to build the project, later you can deploy directly the jar file.
* A SonarQube server to connect to. The application is tested with version 6.7.
  * You may need to create a user in SonarQube for this application to connect to (if your server doesn't provide anonymous access).
  * This repository includes a `docker-compose.yml` file that you can use to deploy a SonarQube instance on Docker. To do that, you need to install **Docker**.

## Configuration

First you'll need to list the users that are going to participate in the game. This is done through the `users.xml` file.
```xml
<user id="pauldark" alias="Paul Dark" team="Team Alpha" />
```
It's important that the `id` matches with the SonarQube username. The `alias` will be used for the rankings and the `team` is used for aggregated Ranking per teams. 

In case your Sonarqube server doesn't have anonymous access, you need to specify credentials for the applicaction to access the API. You can create an user with basic permissions for that.

```
# Sample credentials for accessing the API
sonarUser=cqgameUser
sonarPassword=cqgam3
```

Given that this application does not have persistence, it will use only the issues existing in Sonar. This is why I recommend you to extend the time window to delete old issues in Sonar (Project Settings -> General -> Database Cleaner -> 'Delete closed issues after' = 90). All the points/badges will be based on that period of time. This is also an advantage, since it  promotes a continuous participation.

### Legacy Date

The main goal of this application is fixing *Legacy Code* (old, ugly, error-prone). To avoid giving points for fixing recent issues you can configure the maximum date that you are considering for Legacy Code within the configuration (`legacyDate` in the `application.properties` file.

## How to play

The application searches issues assigned to users, and it gives points for resolving them. Therefore, playing is easy:

* Ensure that the code you're going to refactor is covered by Unit Tests (this is not really part of the game but just good software development practices, anyway it's good to always remember that).
* Select the issues you want to fix in SonarQube, and assign them to you.
* Fix the issues! Once the code is reviewed by Sonar the issue will become Resolved and you will obtain points for that.

## Badges

This is a work in progress, some things should be improved. Right now there are two sample badges that you can win. One is related with the date in which you start playing, if you are an early adopter you will obtain some extra points (obviously you will lose them after the SonarQube issue cleaner comes into play). The second one is related with the Unit Test Coverage and there are different ranges. Explore the code to know more about them.

If you want to know more, check [my blog post](https://thepracticaldeveloper.com/2015/06/23/a-gamification-experiment-with-sonarqube/).

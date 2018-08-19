package com.thepracticaldeveloper.devgame.modules.users.service;

import com.thepracticaldeveloper.devgame.modules.users.domain.User;

import java.util.stream.Stream;

public interface SonarUserService {
    Stream<User> getAllActiveUsers();
}

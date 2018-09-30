package com.thepracticaldeveloper.devgame.modules.users.service;

import com.thepracticaldeveloper.devgame.modules.users.domain.User;

import java.util.List;
import java.util.stream.Stream;

public interface SonarUserService {
    Stream<User> getAllActiveUsers();
    List<User> findUsersByTeam(String teamName);
}

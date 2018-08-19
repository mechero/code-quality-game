package com.thepracticaldeveloper.devgame.modules.users.service;

import com.thepracticaldeveloper.devgame.modules.users.dao.UserMongoRepository;
import com.thepracticaldeveloper.devgame.modules.users.domain.User;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SonarUserServiceImpl implements SonarUserService {

    private final UserMongoRepository userRepository;

    public SonarUserServiceImpl(final UserMongoRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Stream<User> getAllActiveUsers() {
        return userRepository.findAllUsersWithTeam();
    }
}

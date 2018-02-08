package com.thepracticaldeveloper.devgame.modules.users.domain;

import java.util.List;
import java.util.stream.Collectors;

public final class SonarUserFactory {

    private SonarUserFactory() {}

    public static List<SonarUser> buildFromIds(List<String> ids) {
        return ids.stream().map(SonarUser::new).collect(Collectors.toList());
    }
}

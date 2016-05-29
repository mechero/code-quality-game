package es.macero.cqgame.modules.users.domain;

import java.util.List;
import java.util.stream.Collectors;

public final class SonarUserFactory {

    public static List<SonarUser> buildFromIds(List<String> ids) {
        return ids.stream().map(s -> new SonarUser(s)).collect(Collectors.toList());
    }
}

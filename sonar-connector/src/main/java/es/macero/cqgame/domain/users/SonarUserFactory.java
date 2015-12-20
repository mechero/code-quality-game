package es.macero.cqgame.domain.users;

import java.util.List;
import java.util.stream.Collectors;

public final class SonarUserFactory {

    public static List<SonarUser> buildFromIds(List<String> ids) {
        return ids.stream().map(s -> new SonarUser(s)).collect(Collectors.toList());
    }
}

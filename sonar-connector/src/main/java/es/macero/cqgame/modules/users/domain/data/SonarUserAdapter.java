package es.macero.cqgame.modules.users.domain.data;

import es.macero.cqgame.modules.users.domain.SonarUser;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class SonarUserAdapter extends XmlAdapter<SonarUserXml, SonarUser> {

    @Override
    public SonarUserXml marshal(SonarUser user) throws Exception {
        return new SonarUserXml(user.getId(), user.getAlias(), user.getTeam());
    }

    @Override
    public SonarUser unmarshal(SonarUserXml userXml) throws Exception {
        return new SonarUser(userXml.getId(), userXml.getAlias(), userXml.getTeam());
    }

}

package es.macero.cqgame.domain.users.data;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import es.macero.cqgame.domain.users.SonarUser;

public class SonarUserAdapter extends XmlAdapter<SonarUserXml, SonarUser>
{

    @Override
    public SonarUserXml marshal(SonarUser user) throws Exception
    {
        return new SonarUserXml(user.getId(), user.getAlias(), user.getTeam());
    }

    @Override
    public SonarUser unmarshal(SonarUserXml userXml) throws Exception
    {
        return new SonarUser(userXml.getId(), userXml.getAlias(), userXml.getTeam());
    }

}

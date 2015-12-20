package es.macero.cqgame.domain.users;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class SonarUserList {
    private List<SonarUser> users;

    public SonarUserList() {
        users = new ArrayList<>();
    }

    @XmlElement(name = "user")
    public List<SonarUser> getUsers() {
        return users;
    }

    public void setUsers(List<SonarUser> users) {
        this.users = users;
    }

}

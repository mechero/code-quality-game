package es.macero.cqgame.domain.users.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class SonarUserXml {

    private String id;
    private String alias;
    private String team;

    public SonarUserXml(String id, String alias, String team) {
        super();
        this.id = id;
        this.alias = alias;
        this.team = team;
    }

    public SonarUserXml(String id) {
        this(id, null, null);
    }

    public SonarUserXml() {
        this(null, null, null);
    }

    @XmlAttribute(required = true)
    public String getId() {
        return id;
    }

    @XmlAttribute(required = false)
    public String getAlias() {
        return alias;
    }

    @XmlAttribute(required = false)
    public String getTeam() {
        return team;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alias == null) ? 0 : alias.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SonarUserXml other = (SonarUserXml) obj;
        if (alias == null) {
            if (other.alias != null)
                return false;
        } else if (!alias.equals(other.alias))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (team == null) {
            if (other.team != null)
                return false;
        } else if (!team.equals(other.team))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SonarUserXml [id=" + id + ", alias=" + alias + ", team=" + team + "]";
    }

}

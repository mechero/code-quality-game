package es.macero.cqgame.domain.users;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.macero.cqgame.domain.users.data.SonarUserAdapter;

@XmlJavaTypeAdapter(SonarUserAdapter.class)
public final class SonarUser
{

    private String id;
    private String alias;
    private String team;

    public SonarUser(String id, String alias, String team)
    {
        super();
        this.id = id;
        this.alias = alias;
        this.team = team;
    }

    public SonarUser(String id)
    {
        this(id, null, null);
    }

    public String getId()
    {
        return id;
    }

    public String getAlias()
    {
        return alias;
    }

    public String getTeam()
    {
        return team;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alias == null) ? 0 : alias.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((team == null) ? 0 : team.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SonarUser other = (SonarUser) obj;
        if (alias == null)
        {
            if (other.alias != null)
                return false;
        }
        else if (!alias.equals(other.alias))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (team == null)
        {
            if (other.team != null)
                return false;
        }
        else if (!team.equals(other.team))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "SonarUser [id=" + id + ", alias=" + alias + ", team=" + team + "]";
    }

}

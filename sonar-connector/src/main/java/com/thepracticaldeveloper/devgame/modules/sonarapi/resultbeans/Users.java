package com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "paging",
        "users"
})
public class Users {

    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("users")
    private List<User> users = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("paging")
    public Paging getPaging() {
        return paging;
    }

    @JsonProperty("paging")
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("paging", paging).append("users", users).append("additionalProperties", additionalProperties).toString();
    }

}


package es.macero.cqgame.resultbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "maxResultsReached",
    "paging",
    "issues",
    "components",
    "projects",
    "rules",
    "users"
})
public class Issues {

    @JsonProperty("maxResultsReached")
    private Boolean maxResultsReached;
    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("issues")
    private List<Issue> issues = new ArrayList<Issue>();
    @JsonProperty("components")
    private List<Component> components = new ArrayList<Component>();
    @JsonProperty("projects")
    private List<Project> projects = new ArrayList<Project>();
    @JsonProperty("rules")
    private List<Rule> rules = new ArrayList<Rule>();
    @JsonProperty("users")
    private List<User> users = new ArrayList<User>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The maxResultsReached
     */
    @JsonProperty("maxResultsReached")
    public Boolean getMaxResultsReached() {
        return maxResultsReached;
    }

    /**
     * 
     * @param maxResultsReached
     *     The maxResultsReached
     */
    @JsonProperty("maxResultsReached")
    public void setMaxResultsReached(Boolean maxResultsReached) {
        this.maxResultsReached = maxResultsReached;
    }

    public Issues withMaxResultsReached(Boolean maxResultsReached) {
        this.maxResultsReached = maxResultsReached;
        return this;
    }

    /**
     * 
     * @return
     *     The paging
     */
    @JsonProperty("paging")
    public Paging getPaging() {
        return paging;
    }

    /**
     * 
     * @param paging
     *     The paging
     */
    @JsonProperty("paging")
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Issues withPaging(Paging paging) {
        this.paging = paging;
        return this;
    }

    /**
     * 
     * @return
     *     The issues
     */
    @JsonProperty("issues")
    public List<Issue> getIssues() {
        return issues;
    }

    /**
     * 
     * @param issues
     *     The issues
     */
    @JsonProperty("issues")
    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Issues withIssues(List<Issue> issues) {
        this.issues = issues;
        return this;
    }

    /**
     * 
     * @return
     *     The components
     */
    @JsonProperty("components")
    public List<Component> getComponents() {
        return components;
    }

    /**
     * 
     * @param components
     *     The components
     */
    @JsonProperty("components")
    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public Issues withComponents(List<Component> components) {
        this.components = components;
        return this;
    }

    /**
     * 
     * @return
     *     The projects
     */
    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * 
     * @param projects
     *     The projects
     */
    @JsonProperty("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Issues withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    /**
     * 
     * @return
     *     The rules
     */
    @JsonProperty("rules")
    public List<Rule> getRules() {
        return rules;
    }

    /**
     * 
     * @param rules
     *     The rules
     */
    @JsonProperty("rules")
    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public Issues withRules(List<Rule> rules) {
        this.rules = rules;
        return this;
    }

    /**
     * 
     * @return
     *     The users
     */
    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users
     */
    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Issues withUsers(List<User> users) {
        this.users = users;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Issues withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

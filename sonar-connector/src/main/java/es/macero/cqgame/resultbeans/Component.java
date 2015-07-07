
package es.macero.cqgame.resultbeans;

import java.util.HashMap;
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
    "key",
    "id",
    "qualifier",
    "name",
    "longName",
    "path",
    "projectId",
    "subProjectId"
})
public class Component {

    @JsonProperty("key")
    private String key;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("qualifier")
    private String qualifier;
    @JsonProperty("name")
    private String name;
    @JsonProperty("longName")
    private String longName;
    @JsonProperty("path")
    private String path;
    @JsonProperty("projectId")
    private Integer projectId;
    @JsonProperty("subProjectId")
    private Integer subProjectId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The key
     */
    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    public Component withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Component withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The qualifier
     */
    @JsonProperty("qualifier")
    public String getQualifier() {
        return qualifier;
    }

    /**
     * 
     * @param qualifier
     *     The qualifier
     */
    @JsonProperty("qualifier")
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public Component withQualifier(String qualifier) {
        this.qualifier = qualifier;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Component withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The longName
     */
    @JsonProperty("longName")
    public String getLongName() {
        return longName;
    }

    /**
     * 
     * @param longName
     *     The longName
     */
    @JsonProperty("longName")
    public void setLongName(String longName) {
        this.longName = longName;
    }

    public Component withLongName(String longName) {
        this.longName = longName;
        return this;
    }

    /**
     * 
     * @return
     *     The path
     */
    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    /**
     * 
     * @param path
     *     The path
     */
    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    public Component withPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * 
     * @return
     *     The projectId
     */
    @JsonProperty("projectId")
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 
     * @param projectId
     *     The projectId
     */
    @JsonProperty("projectId")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Component withProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    /**
     * 
     * @return
     *     The subProjectId
     */
    @JsonProperty("subProjectId")
    public Integer getSubProjectId() {
        return subProjectId;
    }

    /**
     * 
     * @param subProjectId
     *     The subProjectId
     */
    @JsonProperty("subProjectId")
    public void setSubProjectId(Integer subProjectId) {
        this.subProjectId = subProjectId;
    }

    public Component withSubProjectId(Integer subProjectId) {
        this.subProjectId = subProjectId;
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

    public Component withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

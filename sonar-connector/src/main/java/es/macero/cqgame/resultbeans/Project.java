
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
    "longName"
})
public class Project {

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

    public Project withKey(String key) {
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

    public Project withId(Integer id) {
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

    public Project withQualifier(String qualifier) {
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

    public Project withName(String name) {
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

    public Project withLongName(String longName) {
        this.longName = longName;
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

    public Project withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

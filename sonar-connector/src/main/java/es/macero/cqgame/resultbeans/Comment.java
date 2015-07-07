
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
    "login",
    "htmlText",
    "createdAt"
})
public class Comment {

    @JsonProperty("key")
    private String key;
    @JsonProperty("login")
    private String login;
    @JsonProperty("htmlText")
    private String htmlText;
    @JsonProperty("createdAt")
    private String createdAt;
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

    public Comment withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 
     * @return
     *     The login
     */
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    /**
     * 
     * @param login
     *     The login
     */
    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    public Comment withLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * 
     * @return
     *     The htmlText
     */
    @JsonProperty("htmlText")
    public String getHtmlText() {
        return htmlText;
    }

    /**
     * 
     * @param htmlText
     *     The htmlText
     */
    @JsonProperty("htmlText")
    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public Comment withHtmlText(String htmlText) {
        this.htmlText = htmlText;
        return this;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The createdAt
     */
    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Comment withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Comment withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

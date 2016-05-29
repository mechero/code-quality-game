
package es.macero.cqgame.modules.sonarapi.resultbeans;

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
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "key",
    "rule",
    "severity",
    "component",
    "componentId",
    "project",
    "textRange",
    "flows",
    "resolution",
    "status",
    "message",
    "debt",
    "author",
    "tags",
    "creationDate",
    "updateDate",
    "closeDate",
    "line"
})
public class Issue {

    @JsonProperty("key")
    private String key;
    @JsonProperty("rule")
    private String rule;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("component")
    private String component;
    @JsonProperty("componentId")
    private Integer componentId;
    @JsonProperty("project")
    private String project;
    @JsonProperty("textRange")
    private TextRange textRange;
    @JsonProperty("flows")
    private List<Object> flows = new ArrayList<Object>();
    @JsonProperty("resolution")
    private String resolution;
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("debt")
    private String debt;
    @JsonProperty("author")
    private String author;
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("updateDate")
    private String updateDate;
    @JsonProperty("closeDate")
    private String closeDate;
    @JsonProperty("line")
    private Integer line;
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

    public Issue withKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 
     * @return
     *     The rule
     */
    @JsonProperty("rule")
    public String getRule() {
        return rule;
    }

    /**
     * 
     * @param rule
     *     The rule
     */
    @JsonProperty("rule")
    public void setRule(String rule) {
        this.rule = rule;
    }

    public Issue withRule(String rule) {
        this.rule = rule;
        return this;
    }

    /**
     * 
     * @return
     *     The severity
     */
    @JsonProperty("severity")
    public String getSeverity() {
        return severity;
    }

    /**
     * 
     * @param severity
     *     The severity
     */
    @JsonProperty("severity")
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public Issue withSeverity(String severity) {
        this.severity = severity;
        return this;
    }

    /**
     * 
     * @return
     *     The component
     */
    @JsonProperty("component")
    public String getComponent() {
        return component;
    }

    /**
     * 
     * @param component
     *     The component
     */
    @JsonProperty("component")
    public void setComponent(String component) {
        this.component = component;
    }

    public Issue withComponent(String component) {
        this.component = component;
        return this;
    }

    /**
     * 
     * @return
     *     The componentId
     */
    @JsonProperty("componentId")
    public Integer getComponentId() {
        return componentId;
    }

    /**
     * 
     * @param componentId
     *     The componentId
     */
    @JsonProperty("componentId")
    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public Issue withComponentId(Integer componentId) {
        this.componentId = componentId;
        return this;
    }

    /**
     * 
     * @return
     *     The project
     */
    @JsonProperty("project")
    public String getProject() {
        return project;
    }

    /**
     * 
     * @param project
     *     The project
     */
    @JsonProperty("project")
    public void setProject(String project) {
        this.project = project;
    }

    public Issue withProject(String project) {
        this.project = project;
        return this;
    }

    /**
     * 
     * @return
     *     The textRange
     */
    @JsonProperty("textRange")
    public TextRange getTextRange() {
        return textRange;
    }

    /**
     * 
     * @param textRange
     *     The textRange
     */
    @JsonProperty("textRange")
    public void setTextRange(TextRange textRange) {
        this.textRange = textRange;
    }

    public Issue withTextRange(TextRange textRange) {
        this.textRange = textRange;
        return this;
    }

    /**
     * 
     * @return
     *     The flows
     */
    @JsonProperty("flows")
    public List<Object> getFlows() {
        return flows;
    }

    /**
     * 
     * @param flows
     *     The flows
     */
    @JsonProperty("flows")
    public void setFlows(List<Object> flows) {
        this.flows = flows;
    }

    public Issue withFlows(List<Object> flows) {
        this.flows = flows;
        return this;
    }

    /**
     * 
     * @return
     *     The resolution
     */
    @JsonProperty("resolution")
    public String getResolution() {
        return resolution;
    }

    /**
     * 
     * @param resolution
     *     The resolution
     */
    @JsonProperty("resolution")
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Issue withResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public Issue withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * @return
     *     The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public Issue withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 
     * @return
     *     The debt
     */
    @JsonProperty("debt")
    public String getDebt() {
        return debt;
    }

    /**
     * 
     * @param debt
     *     The debt
     */
    @JsonProperty("debt")
    public void setDebt(String debt) {
        this.debt = debt;
    }

    public Issue withDebt(String debt) {
        this.debt = debt;
        return this;
    }

    /**
     * 
     * @return
     *     The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public Issue withAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * 
     * @return
     *     The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Issue withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * 
     * @return
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *     The creationDate
     */
    @JsonProperty("creationDate")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Issue withCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * 
     * @return
     *     The updateDate
     */
    @JsonProperty("updateDate")
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * 
     * @param updateDate
     *     The updateDate
     */
    @JsonProperty("updateDate")
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Issue withUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    /**
     * 
     * @return
     *     The closeDate
     */
    @JsonProperty("closeDate")
    public String getCloseDate() {
        return closeDate;
    }

    /**
     * 
     * @param closeDate
     *     The closeDate
     */
    @JsonProperty("closeDate")
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public Issue withCloseDate(String closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    /**
     * 
     * @return
     *     The line
     */
    @JsonProperty("line")
    public Integer getLine() {
        return line;
    }

    /**
     * 
     * @param line
     *     The line
     */
    @JsonProperty("line")
    public void setLine(Integer line) {
        this.line = line;
    }

    public Issue withLine(Integer line) {
        this.line = line;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Issue withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

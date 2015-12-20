
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
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "total",
    "p",
    "ps",
    "paging",
    "issues",
    "components"
})
public class Issues {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("p")
    private Integer p;
    @JsonProperty("ps")
    private Integer ps;
    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("issues")
    private List<Issue> issues = new ArrayList<Issue>();
    @JsonProperty("components")
    private List<Component> components = new ArrayList<Component>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The total
     */
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    public Issues withTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 
     * @return
     *     The p
     */
    @JsonProperty("p")
    public Integer getP() {
        return p;
    }

    /**
     * 
     * @param p
     *     The p
     */
    @JsonProperty("p")
    public void setP(Integer p) {
        this.p = p;
    }

    public Issues withP(Integer p) {
        this.p = p;
        return this;
    }

    /**
     * 
     * @return
     *     The ps
     */
    @JsonProperty("ps")
    public Integer getPs() {
        return ps;
    }

    /**
     * 
     * @param ps
     *     The ps
     */
    @JsonProperty("ps")
    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public Issues withPs(Integer ps) {
        this.ps = ps;
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

    public Issues withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

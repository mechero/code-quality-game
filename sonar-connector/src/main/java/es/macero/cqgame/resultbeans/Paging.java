
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
    "pageIndex",
    "pageSize",
    "total",
    "fTotal",
    "pages"
})
public class Paging {

    @JsonProperty("pageIndex")
    private Integer pageIndex;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("fTotal")
    private String fTotal;
    @JsonProperty("pages")
    private Integer pages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The pageIndex
     */
    @JsonProperty("pageIndex")
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * 
     * @param pageIndex
     *     The pageIndex
     */
    @JsonProperty("pageIndex")
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Paging withPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    /**
     * 
     * @return
     *     The pageSize
     */
    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 
     * @param pageSize
     *     The pageSize
     */
    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Paging withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

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

    public Paging withTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 
     * @return
     *     The fTotal
     */
    @JsonProperty("fTotal")
    public String getFTotal() {
        return fTotal;
    }

    /**
     * 
     * @param fTotal
     *     The fTotal
     */
    @JsonProperty("fTotal")
    public void setFTotal(String fTotal) {
        this.fTotal = fTotal;
    }

    public Paging withFTotal(String fTotal) {
        this.fTotal = fTotal;
        return this;
    }

    /**
     * 
     * @return
     *     The pages
     */
    @JsonProperty("pages")
    public Integer getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    @JsonProperty("pages")
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Paging withPages(Integer pages) {
        this.pages = pages;
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

    public Paging withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}


package com.thepracticaldeveloper.devgame.modules.sonarapi.resultbeans;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pageIndex",
    "pageSize",
    "total"
})
public class Paging {

    @JsonProperty("pageIndex")
    private Integer pageIndex;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("total")
    private Integer total;
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

    public Paging withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

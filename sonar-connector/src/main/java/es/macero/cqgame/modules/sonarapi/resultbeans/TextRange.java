
package es.macero.cqgame.modules.sonarapi.resultbeans;

import java.util.HashMap;
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
    "startLine",
    "endLine",
    "startOffset",
    "endOffset"
})
public class TextRange {

    @JsonProperty("startLine")
    private Integer startLine;
    @JsonProperty("endLine")
    private Integer endLine;
    @JsonProperty("startOffset")
    private Integer startOffset;
    @JsonProperty("endOffset")
    private Integer endOffset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The startLine
     */
    @JsonProperty("startLine")
    public Integer getStartLine() {
        return startLine;
    }

    /**
     * 
     * @param startLine
     *     The startLine
     */
    @JsonProperty("startLine")
    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public TextRange withStartLine(Integer startLine) {
        this.startLine = startLine;
        return this;
    }

    /**
     * 
     * @return
     *     The endLine
     */
    @JsonProperty("endLine")
    public Integer getEndLine() {
        return endLine;
    }

    /**
     * 
     * @param endLine
     *     The endLine
     */
    @JsonProperty("endLine")
    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    public TextRange withEndLine(Integer endLine) {
        this.endLine = endLine;
        return this;
    }

    /**
     * 
     * @return
     *     The startOffset
     */
    @JsonProperty("startOffset")
    public Integer getStartOffset() {
        return startOffset;
    }

    /**
     * 
     * @param startOffset
     *     The startOffset
     */
    @JsonProperty("startOffset")
    public void setStartOffset(Integer startOffset) {
        this.startOffset = startOffset;
    }

    public TextRange withStartOffset(Integer startOffset) {
        this.startOffset = startOffset;
        return this;
    }

    /**
     * 
     * @return
     *     The endOffset
     */
    @JsonProperty("endOffset")
    public Integer getEndOffset() {
        return endOffset;
    }

    /**
     * 
     * @param endOffset
     *     The endOffset
     */
    @JsonProperty("endOffset")
    public void setEndOffset(Integer endOffset) {
        this.endOffset = endOffset;
    }

    public TextRange withEndOffset(Integer endOffset) {
        this.endOffset = endOffset;
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

    public TextRange withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

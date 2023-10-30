package at.fhtw.swen3.paperless.services.dto;

import java.net.URI;
import java.util.Objects;
import at.fhtw.swen3.paperless.services.dto.GetDocumentTypes200ResponseResultsInner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GetDocumentTypes200Response
 */

@JsonTypeName("GetDocumentTypes_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-30T12:14:24.762781Z[Etc/UTC]")
public class GetDocumentTypes200Response {

  private Integer count;

  private Integer next;

  private Integer previous;

  @Valid
  private List<Integer> all = new ArrayList<>();

  @Valid
  private List<@Valid GetDocumentTypes200ResponseResultsInner> results = new ArrayList<>();

  public GetDocumentTypes200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetDocumentTypes200Response(Integer count, Integer next, Integer previous, List<Integer> all, List<@Valid GetDocumentTypes200ResponseResultsInner> results) {
    this.count = count;
    this.next = next;
    this.previous = previous;
    this.all = all;
    this.results = results;
  }

  public GetDocumentTypes200Response count(Integer count) {
    this.count = count;
    return this;
  }

  /**
   * Get count
   * @return count
  */
  @NotNull 
  @Schema(name = "count", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("count")
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public GetDocumentTypes200Response next(Integer next) {
    this.next = next;
    return this;
  }

  /**
   * Get next
   * @return next
  */
  @NotNull 
  @Schema(name = "next", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("next")
  public Integer getNext() {
    return next;
  }

  public void setNext(Integer next) {
    this.next = next;
  }

  public GetDocumentTypes200Response previous(Integer previous) {
    this.previous = previous;
    return this;
  }

  /**
   * Get previous
   * @return previous
  */
  @NotNull 
  @Schema(name = "previous", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("previous")
  public Integer getPrevious() {
    return previous;
  }

  public void setPrevious(Integer previous) {
    this.previous = previous;
  }

  public GetDocumentTypes200Response all(List<Integer> all) {
    this.all = all;
    return this;
  }

  public GetDocumentTypes200Response addAllItem(Integer allItem) {
    if (this.all == null) {
      this.all = new ArrayList<>();
    }
    this.all.add(allItem);
    return this;
  }

  /**
   * Get all
   * @return all
  */
  @NotNull 
  @Schema(name = "all", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("all")
  public List<Integer> getAll() {
    return all;
  }

  public void setAll(List<Integer> all) {
    this.all = all;
  }

  public GetDocumentTypes200Response results(List<@Valid GetDocumentTypes200ResponseResultsInner> results) {
    this.results = results;
    return this;
  }

  public GetDocumentTypes200Response addResultsItem(GetDocumentTypes200ResponseResultsInner resultsItem) {
    if (this.results == null) {
      this.results = new ArrayList<>();
    }
    this.results.add(resultsItem);
    return this;
  }

  /**
   * Get results
   * @return results
  */
  @NotNull @Valid 
  @Schema(name = "results", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("results")
  public List<@Valid GetDocumentTypes200ResponseResultsInner> getResults() {
    return results;
  }

  public void setResults(List<@Valid GetDocumentTypes200ResponseResultsInner> results) {
    this.results = results;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetDocumentTypes200Response getDocumentTypes200Response = (GetDocumentTypes200Response) o;
    return Objects.equals(this.count, getDocumentTypes200Response.count) &&
        Objects.equals(this.next, getDocumentTypes200Response.next) &&
        Objects.equals(this.previous, getDocumentTypes200Response.previous) &&
        Objects.equals(this.all, getDocumentTypes200Response.all) &&
        Objects.equals(this.results, getDocumentTypes200Response.results);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, next, previous, all, results);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetDocumentTypes200Response {\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    next: ").append(toIndentedString(next)).append("\n");
    sb.append("    previous: ").append(toIndentedString(previous)).append("\n");
    sb.append("    all: ").append(toIndentedString(all)).append("\n");
    sb.append("    results: ").append(toIndentedString(results)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


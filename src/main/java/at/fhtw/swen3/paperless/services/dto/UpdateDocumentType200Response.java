package at.fhtw.swen3.paperless.services.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * UpdateDocumentType200Response
 */

@JsonTypeName("UpdateDocumentType_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-30T12:14:24.762781Z[Etc/UTC]")
public class UpdateDocumentType200Response {

  private Integer id;

  private String slug;

  private String name;

  private String match;

  private Integer matchingAlgorithm;

  private Boolean isInsensitive;

  private Integer documentCount;

  private Integer owner;

  private Boolean userCanChange;

  public UpdateDocumentType200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateDocumentType200Response(Integer id, String slug, String name, String match, Integer matchingAlgorithm, Boolean isInsensitive, Integer documentCount, Integer owner, Boolean userCanChange) {
    this.id = id;
    this.slug = slug;
    this.name = name;
    this.match = match;
    this.matchingAlgorithm = matchingAlgorithm;
    this.isInsensitive = isInsensitive;
    this.documentCount = documentCount;
    this.owner = owner;
    this.userCanChange = userCanChange;
  }

  public UpdateDocumentType200Response id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public UpdateDocumentType200Response slug(String slug) {
    this.slug = slug;
    return this;
  }

  /**
   * Get slug
   * @return slug
  */
  @NotNull 
  @Schema(name = "slug", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("slug")
  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public UpdateDocumentType200Response name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UpdateDocumentType200Response match(String match) {
    this.match = match;
    return this;
  }

  /**
   * Get match
   * @return match
  */
  @NotNull 
  @Schema(name = "match", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("match")
  public String getMatch() {
    return match;
  }

  public void setMatch(String match) {
    this.match = match;
  }

  public UpdateDocumentType200Response matchingAlgorithm(Integer matchingAlgorithm) {
    this.matchingAlgorithm = matchingAlgorithm;
    return this;
  }

  /**
   * Get matchingAlgorithm
   * @return matchingAlgorithm
  */
  @NotNull 
  @Schema(name = "matching_algorithm", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("matching_algorithm")
  public Integer getMatchingAlgorithm() {
    return matchingAlgorithm;
  }

  public void setMatchingAlgorithm(Integer matchingAlgorithm) {
    this.matchingAlgorithm = matchingAlgorithm;
  }

  public UpdateDocumentType200Response isInsensitive(Boolean isInsensitive) {
    this.isInsensitive = isInsensitive;
    return this;
  }

  /**
   * Get isInsensitive
   * @return isInsensitive
  */
  @NotNull 
  @Schema(name = "is_insensitive", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("is_insensitive")
  public Boolean getIsInsensitive() {
    return isInsensitive;
  }

  public void setIsInsensitive(Boolean isInsensitive) {
    this.isInsensitive = isInsensitive;
  }

  public UpdateDocumentType200Response documentCount(Integer documentCount) {
    this.documentCount = documentCount;
    return this;
  }

  /**
   * Get documentCount
   * @return documentCount
  */
  @NotNull 
  @Schema(name = "document_count", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("document_count")
  public Integer getDocumentCount() {
    return documentCount;
  }

  public void setDocumentCount(Integer documentCount) {
    this.documentCount = documentCount;
  }

  public UpdateDocumentType200Response owner(Integer owner) {
    this.owner = owner;
    return this;
  }

  /**
   * Get owner
   * @return owner
  */
  @NotNull 
  @Schema(name = "owner", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("owner")
  public Integer getOwner() {
    return owner;
  }

  public void setOwner(Integer owner) {
    this.owner = owner;
  }

  public UpdateDocumentType200Response userCanChange(Boolean userCanChange) {
    this.userCanChange = userCanChange;
    return this;
  }

  /**
   * Get userCanChange
   * @return userCanChange
  */
  @NotNull 
  @Schema(name = "user_can_change", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("user_can_change")
  public Boolean getUserCanChange() {
    return userCanChange;
  }

  public void setUserCanChange(Boolean userCanChange) {
    this.userCanChange = userCanChange;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateDocumentType200Response updateDocumentType200Response = (UpdateDocumentType200Response) o;
    return Objects.equals(this.id, updateDocumentType200Response.id) &&
        Objects.equals(this.slug, updateDocumentType200Response.slug) &&
        Objects.equals(this.name, updateDocumentType200Response.name) &&
        Objects.equals(this.match, updateDocumentType200Response.match) &&
        Objects.equals(this.matchingAlgorithm, updateDocumentType200Response.matchingAlgorithm) &&
        Objects.equals(this.isInsensitive, updateDocumentType200Response.isInsensitive) &&
        Objects.equals(this.documentCount, updateDocumentType200Response.documentCount) &&
        Objects.equals(this.owner, updateDocumentType200Response.owner) &&
        Objects.equals(this.userCanChange, updateDocumentType200Response.userCanChange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, slug, name, match, matchingAlgorithm, isInsensitive, documentCount, owner, userCanChange);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateDocumentType200Response {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
    sb.append("    matchingAlgorithm: ").append(toIndentedString(matchingAlgorithm)).append("\n");
    sb.append("    isInsensitive: ").append(toIndentedString(isInsensitive)).append("\n");
    sb.append("    documentCount: ").append(toIndentedString(documentCount)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    userCanChange: ").append(toIndentedString(userCanChange)).append("\n");
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


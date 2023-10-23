package at.fhtw.swkom.paperless.services.dto;

import java.net.URI;
import java.util.Objects;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentRequestPermissions;
import at.fhtw.swkom.paperless.services.dto.UpdateDocumentTypeRequestPermissionsForm;
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
 * UpdateDocumentTypeRequest
 */

@JsonTypeName("UpdateDocumentType_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
public class UpdateDocumentTypeRequest {

  private Integer id;

  private String slug;

  private String name;

  private String match;

  private Integer matchingAlgorithm;

  private Boolean isInsensitive;

  private Integer documentCount;

  private Integer owner;

  private UpdateDocumentRequestPermissions permissions;

  private UpdateDocumentTypeRequestPermissionsForm permissionsForm;

  public UpdateDocumentTypeRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateDocumentTypeRequest(Integer id, String slug, String name, String match, Integer matchingAlgorithm, Boolean isInsensitive, Integer documentCount, Integer owner, UpdateDocumentRequestPermissions permissions) {
    this.id = id;
    this.slug = slug;
    this.name = name;
    this.match = match;
    this.matchingAlgorithm = matchingAlgorithm;
    this.isInsensitive = isInsensitive;
    this.documentCount = documentCount;
    this.owner = owner;
    this.permissions = permissions;
  }

  public UpdateDocumentTypeRequest id(Integer id) {
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

  public UpdateDocumentTypeRequest slug(String slug) {
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

  public UpdateDocumentTypeRequest name(String name) {
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

  public UpdateDocumentTypeRequest match(String match) {
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

  public UpdateDocumentTypeRequest matchingAlgorithm(Integer matchingAlgorithm) {
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

  public UpdateDocumentTypeRequest isInsensitive(Boolean isInsensitive) {
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

  public UpdateDocumentTypeRequest documentCount(Integer documentCount) {
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

  public UpdateDocumentTypeRequest owner(Integer owner) {
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

  public UpdateDocumentTypeRequest permissions(UpdateDocumentRequestPermissions permissions) {
    this.permissions = permissions;
    return this;
  }

  /**
   * Get permissions
   * @return permissions
  */
  @NotNull @Valid 
  @Schema(name = "permissions", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("permissions")
  public UpdateDocumentRequestPermissions getPermissions() {
    return permissions;
  }

  public void setPermissions(UpdateDocumentRequestPermissions permissions) {
    this.permissions = permissions;
  }

  public UpdateDocumentTypeRequest permissionsForm(UpdateDocumentTypeRequestPermissionsForm permissionsForm) {
    this.permissionsForm = permissionsForm;
    return this;
  }

  /**
   * Get permissionsForm
   * @return permissionsForm
  */
  @Valid 
  @Schema(name = "permissions_form", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("permissions_form")
  public UpdateDocumentTypeRequestPermissionsForm getPermissionsForm() {
    return permissionsForm;
  }

  public void setPermissionsForm(UpdateDocumentTypeRequestPermissionsForm permissionsForm) {
    this.permissionsForm = permissionsForm;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateDocumentTypeRequest updateDocumentTypeRequest = (UpdateDocumentTypeRequest) o;
    return Objects.equals(this.id, updateDocumentTypeRequest.id) &&
        Objects.equals(this.slug, updateDocumentTypeRequest.slug) &&
        Objects.equals(this.name, updateDocumentTypeRequest.name) &&
        Objects.equals(this.match, updateDocumentTypeRequest.match) &&
        Objects.equals(this.matchingAlgorithm, updateDocumentTypeRequest.matchingAlgorithm) &&
        Objects.equals(this.isInsensitive, updateDocumentTypeRequest.isInsensitive) &&
        Objects.equals(this.documentCount, updateDocumentTypeRequest.documentCount) &&
        Objects.equals(this.owner, updateDocumentTypeRequest.owner) &&
        Objects.equals(this.permissions, updateDocumentTypeRequest.permissions) &&
        Objects.equals(this.permissionsForm, updateDocumentTypeRequest.permissionsForm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, slug, name, match, matchingAlgorithm, isInsensitive, documentCount, owner, permissions, permissionsForm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateDocumentTypeRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    slug: ").append(toIndentedString(slug)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    match: ").append(toIndentedString(match)).append("\n");
    sb.append("    matchingAlgorithm: ").append(toIndentedString(matchingAlgorithm)).append("\n");
    sb.append("    isInsensitive: ").append(toIndentedString(isInsensitive)).append("\n");
    sb.append("    documentCount: ").append(toIndentedString(documentCount)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    permissionsForm: ").append(toIndentedString(permissionsForm)).append("\n");
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


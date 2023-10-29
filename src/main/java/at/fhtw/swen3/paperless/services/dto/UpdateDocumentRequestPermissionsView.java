package at.fhtw.swen3.paperless.services.dto;

import java.net.URI;
import java.util.Objects;
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
 * UpdateDocumentRequestPermissionsView
 */

@JsonTypeName("UpdateDocument_request_permissions_view")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T23:17:16.529780Z[Etc/UTC]")
public class UpdateDocumentRequestPermissionsView {

  @Valid
  private List<Object> users = new ArrayList<>();

  @Valid
  private List<Object> groups = new ArrayList<>();

  public UpdateDocumentRequestPermissionsView() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateDocumentRequestPermissionsView(List<Object> users, List<Object> groups) {
    this.users = users;
    this.groups = groups;
  }

  public UpdateDocumentRequestPermissionsView users(List<Object> users) {
    this.users = users;
    return this;
  }

  public UpdateDocumentRequestPermissionsView addUsersItem(Object usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
  */
  @NotNull 
  @Schema(name = "users", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("users")
  public List<Object> getUsers() {
    return users;
  }

  public void setUsers(List<Object> users) {
    this.users = users;
  }

  public UpdateDocumentRequestPermissionsView groups(List<Object> groups) {
    this.groups = groups;
    return this;
  }

  public UpdateDocumentRequestPermissionsView addGroupsItem(Object groupsItem) {
    if (this.groups == null) {
      this.groups = new ArrayList<>();
    }
    this.groups.add(groupsItem);
    return this;
  }

  /**
   * Get groups
   * @return groups
  */
  @NotNull 
  @Schema(name = "groups", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("groups")
  public List<Object> getGroups() {
    return groups;
  }

  public void setGroups(List<Object> groups) {
    this.groups = groups;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateDocumentRequestPermissionsView updateDocumentRequestPermissionsView = (UpdateDocumentRequestPermissionsView) o;
    return Objects.equals(this.users, updateDocumentRequestPermissionsView.users) &&
        Objects.equals(this.groups, updateDocumentRequestPermissionsView.groups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(users, groups);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateDocumentRequestPermissionsView {\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
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


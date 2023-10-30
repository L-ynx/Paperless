package at.fhtw.swen3.paperless.services.dto;

import java.net.URI;
import java.util.Objects;
import at.fhtw.swen3.paperless.services.dto.UpdateDocumentRequestPermissionsView;
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
 * UpdateDocumentRequestPermissions
 */

@JsonTypeName("UpdateDocument_request_permissions")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-30T12:14:24.762781Z[Etc/UTC]")
public class UpdateDocumentRequestPermissions {

  private UpdateDocumentRequestPermissionsView view;

  private UpdateDocumentRequestPermissionsView change;

  public UpdateDocumentRequestPermissions() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateDocumentRequestPermissions(UpdateDocumentRequestPermissionsView view, UpdateDocumentRequestPermissionsView change) {
    this.view = view;
    this.change = change;
  }

  public UpdateDocumentRequestPermissions view(UpdateDocumentRequestPermissionsView view) {
    this.view = view;
    return this;
  }

  /**
   * Get view
   * @return view
  */
  @NotNull @Valid 
  @Schema(name = "view", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("view")
  public UpdateDocumentRequestPermissionsView getView() {
    return view;
  }

  public void setView(UpdateDocumentRequestPermissionsView view) {
    this.view = view;
  }

  public UpdateDocumentRequestPermissions change(UpdateDocumentRequestPermissionsView change) {
    this.change = change;
    return this;
  }

  /**
   * Get change
   * @return change
  */
  @NotNull @Valid 
  @Schema(name = "change", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("change")
  public UpdateDocumentRequestPermissionsView getChange() {
    return change;
  }

  public void setChange(UpdateDocumentRequestPermissionsView change) {
    this.change = change;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateDocumentRequestPermissions updateDocumentRequestPermissions = (UpdateDocumentRequestPermissions) o;
    return Objects.equals(this.view, updateDocumentRequestPermissions.view) &&
        Objects.equals(this.change, updateDocumentRequestPermissions.change);
  }

  @Override
  public int hashCode() {
    return Objects.hash(view, change);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateDocumentRequestPermissions {\n");
    sb.append("    view: ").append(toIndentedString(view)).append("\n");
    sb.append("    change: ").append(toIndentedString(change)).append("\n");
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


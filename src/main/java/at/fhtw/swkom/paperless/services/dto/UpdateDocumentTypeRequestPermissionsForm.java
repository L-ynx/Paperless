package at.fhtw.swkom.paperless.services.dto;

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
 * UpdateDocumentTypeRequestPermissionsForm
 */

@JsonTypeName("UpdateDocumentType_request_permissions_form")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T11:25:43.450871Z[Etc/UTC]")
public class UpdateDocumentTypeRequestPermissionsForm {

  private Integer owner;

  public UpdateDocumentTypeRequestPermissionsForm() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public UpdateDocumentTypeRequestPermissionsForm(Integer owner) {
    this.owner = owner;
  }

  public UpdateDocumentTypeRequestPermissionsForm owner(Integer owner) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateDocumentTypeRequestPermissionsForm updateDocumentTypeRequestPermissionsForm = (UpdateDocumentTypeRequestPermissionsForm) o;
    return Objects.equals(this.owner, updateDocumentTypeRequestPermissionsForm.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateDocumentTypeRequestPermissionsForm {\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
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


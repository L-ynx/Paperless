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
 * CreateUISettingsRequestSettingsTheme
 */

@JsonTypeName("CreateUISettings_request_settings_theme")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-29T23:17:16.529780Z[Etc/UTC]")
public class CreateUISettingsRequestSettingsTheme {

  private String color;

  public CreateUISettingsRequestSettingsTheme() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateUISettingsRequestSettingsTheme(String color) {
    this.color = color;
  }

  public CreateUISettingsRequestSettingsTheme color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
  */
  @NotNull 
  @Schema(name = "color", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("color")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateUISettingsRequestSettingsTheme createUISettingsRequestSettingsTheme = (CreateUISettingsRequestSettingsTheme) o;
    return Objects.equals(this.color, createUISettingsRequestSettingsTheme.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateUISettingsRequestSettingsTheme {\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
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


package at.fhtw.swen3.paperless.services.dto;

import java.net.URI;
import java.util.Objects;
import at.fhtw.swen3.paperless.services.dto.GetDocument200ResponsePermissions;
import at.fhtw.swen3.paperless.services.dto.GetDocuments200ResponseResultsInnerNotesInner;
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
 * GetDocument200Response
 */

@JsonTypeName("GetDocument_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-23T14:33:06.577219Z[Etc/UTC]")
public class GetDocument200Response {

  private Integer id;

  private Integer correspondent;

  private Integer documentType;

  private Integer storagePath;

  private String title;

  private String content;

  @Valid
  private List<Integer> tags = new ArrayList<>();

  private String created;

  private String createdDate;

  private String modified;

  private String added;

  private Integer archiveSerialNumber;

  private String originalFileName;

  private String archivedFileName;

  private Integer owner;

  private GetDocument200ResponsePermissions permissions;

  @Valid
  private List<@Valid GetDocuments200ResponseResultsInnerNotesInner> notes = new ArrayList<>();

  public GetDocument200Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GetDocument200Response(Integer id, Integer correspondent, Integer documentType, Integer storagePath, String title, String content, List<Integer> tags, String created, String createdDate, String modified, String added, Integer archiveSerialNumber, String originalFileName, String archivedFileName, Integer owner, GetDocument200ResponsePermissions permissions, List<@Valid GetDocuments200ResponseResultsInnerNotesInner> notes) {
    this.id = id;
    this.correspondent = correspondent;
    this.documentType = documentType;
    this.storagePath = storagePath;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.created = created;
    this.createdDate = createdDate;
    this.modified = modified;
    this.added = added;
    this.archiveSerialNumber = archiveSerialNumber;
    this.originalFileName = originalFileName;
    this.archivedFileName = archivedFileName;
    this.owner = owner;
    this.permissions = permissions;
    this.notes = notes;
  }

  public GetDocument200Response id(Integer id) {
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

  public GetDocument200Response correspondent(Integer correspondent) {
    this.correspondent = correspondent;
    return this;
  }

  /**
   * Get correspondent
   * @return correspondent
  */
  @NotNull 
  @Schema(name = "correspondent", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("correspondent")
  public Integer getCorrespondent() {
    return correspondent;
  }

  public void setCorrespondent(Integer correspondent) {
    this.correspondent = correspondent;
  }

  public GetDocument200Response documentType(Integer documentType) {
    this.documentType = documentType;
    return this;
  }

  /**
   * Get documentType
   * @return documentType
  */
  @NotNull 
  @Schema(name = "document_type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("document_type")
  public Integer getDocumentType() {
    return documentType;
  }

  public void setDocumentType(Integer documentType) {
    this.documentType = documentType;
  }

  public GetDocument200Response storagePath(Integer storagePath) {
    this.storagePath = storagePath;
    return this;
  }

  /**
   * Get storagePath
   * @return storagePath
  */
  @NotNull 
  @Schema(name = "storage_path", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("storage_path")
  public Integer getStoragePath() {
    return storagePath;
  }

  public void setStoragePath(Integer storagePath) {
    this.storagePath = storagePath;
  }

  public GetDocument200Response title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @NotNull 
  @Schema(name = "title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public GetDocument200Response content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @NotNull 
  @Schema(name = "content", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("content")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public GetDocument200Response tags(List<Integer> tags) {
    this.tags = tags;
    return this;
  }

  public GetDocument200Response addTagsItem(Integer tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<>();
    }
    this.tags.add(tagsItem);
    return this;
  }

  /**
   * Get tags
   * @return tags
  */
  @NotNull 
  @Schema(name = "tags", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("tags")
  public List<Integer> getTags() {
    return tags;
  }

  public void setTags(List<Integer> tags) {
    this.tags = tags;
  }

  public GetDocument200Response created(String created) {
    this.created = created;
    return this;
  }

  /**
   * Get created
   * @return created
  */
  @NotNull 
  @Schema(name = "created", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created")
  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public GetDocument200Response createdDate(String createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
  */
  @NotNull 
  @Schema(name = "created_date", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_date")
  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public GetDocument200Response modified(String modified) {
    this.modified = modified;
    return this;
  }

  /**
   * Get modified
   * @return modified
  */
  @NotNull 
  @Schema(name = "modified", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("modified")
  public String getModified() {
    return modified;
  }

  public void setModified(String modified) {
    this.modified = modified;
  }

  public GetDocument200Response added(String added) {
    this.added = added;
    return this;
  }

  /**
   * Get added
   * @return added
  */
  @NotNull 
  @Schema(name = "added", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("added")
  public String getAdded() {
    return added;
  }

  public void setAdded(String added) {
    this.added = added;
  }

  public GetDocument200Response archiveSerialNumber(Integer archiveSerialNumber) {
    this.archiveSerialNumber = archiveSerialNumber;
    return this;
  }

  /**
   * Get archiveSerialNumber
   * @return archiveSerialNumber
  */
  @NotNull 
  @Schema(name = "archive_serial_number", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("archive_serial_number")
  public Integer getArchiveSerialNumber() {
    return archiveSerialNumber;
  }

  public void setArchiveSerialNumber(Integer archiveSerialNumber) {
    this.archiveSerialNumber = archiveSerialNumber;
  }

  public GetDocument200Response originalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
    return this;
  }

  /**
   * Get originalFileName
   * @return originalFileName
  */
  @NotNull 
  @Schema(name = "original_file_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("original_file_name")
  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public GetDocument200Response archivedFileName(String archivedFileName) {
    this.archivedFileName = archivedFileName;
    return this;
  }

  /**
   * Get archivedFileName
   * @return archivedFileName
  */
  @NotNull 
  @Schema(name = "archived_file_name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("archived_file_name")
  public String getArchivedFileName() {
    return archivedFileName;
  }

  public void setArchivedFileName(String archivedFileName) {
    this.archivedFileName = archivedFileName;
  }

  public GetDocument200Response owner(Integer owner) {
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

  public GetDocument200Response permissions(GetDocument200ResponsePermissions permissions) {
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
  public GetDocument200ResponsePermissions getPermissions() {
    return permissions;
  }

  public void setPermissions(GetDocument200ResponsePermissions permissions) {
    this.permissions = permissions;
  }

  public GetDocument200Response notes(List<@Valid GetDocuments200ResponseResultsInnerNotesInner> notes) {
    this.notes = notes;
    return this;
  }

  public GetDocument200Response addNotesItem(GetDocuments200ResponseResultsInnerNotesInner notesItem) {
    if (this.notes == null) {
      this.notes = new ArrayList<>();
    }
    this.notes.add(notesItem);
    return this;
  }

  /**
   * Get notes
   * @return notes
  */
  @NotNull @Valid 
  @Schema(name = "notes", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("notes")
  public List<@Valid GetDocuments200ResponseResultsInnerNotesInner> getNotes() {
    return notes;
  }

  public void setNotes(List<@Valid GetDocuments200ResponseResultsInnerNotesInner> notes) {
    this.notes = notes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetDocument200Response getDocument200Response = (GetDocument200Response) o;
    return Objects.equals(this.id, getDocument200Response.id) &&
        Objects.equals(this.correspondent, getDocument200Response.correspondent) &&
        Objects.equals(this.documentType, getDocument200Response.documentType) &&
        Objects.equals(this.storagePath, getDocument200Response.storagePath) &&
        Objects.equals(this.title, getDocument200Response.title) &&
        Objects.equals(this.content, getDocument200Response.content) &&
        Objects.equals(this.tags, getDocument200Response.tags) &&
        Objects.equals(this.created, getDocument200Response.created) &&
        Objects.equals(this.createdDate, getDocument200Response.createdDate) &&
        Objects.equals(this.modified, getDocument200Response.modified) &&
        Objects.equals(this.added, getDocument200Response.added) &&
        Objects.equals(this.archiveSerialNumber, getDocument200Response.archiveSerialNumber) &&
        Objects.equals(this.originalFileName, getDocument200Response.originalFileName) &&
        Objects.equals(this.archivedFileName, getDocument200Response.archivedFileName) &&
        Objects.equals(this.owner, getDocument200Response.owner) &&
        Objects.equals(this.permissions, getDocument200Response.permissions) &&
        Objects.equals(this.notes, getDocument200Response.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, correspondent, documentType, storagePath, title, content, tags, created, createdDate, modified, added, archiveSerialNumber, originalFileName, archivedFileName, owner, permissions, notes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetDocument200Response {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    correspondent: ").append(toIndentedString(correspondent)).append("\n");
    sb.append("    documentType: ").append(toIndentedString(documentType)).append("\n");
    sb.append("    storagePath: ").append(toIndentedString(storagePath)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    added: ").append(toIndentedString(added)).append("\n");
    sb.append("    archiveSerialNumber: ").append(toIndentedString(archiveSerialNumber)).append("\n");
    sb.append("    originalFileName: ").append(toIndentedString(originalFileName)).append("\n");
    sb.append("    archivedFileName: ").append(toIndentedString(archivedFileName)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
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


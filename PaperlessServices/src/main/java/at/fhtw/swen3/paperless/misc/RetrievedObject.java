package at.fhtw.swen3.paperless.misc;

import lombok.Getter;

import java.nio.file.Path;

@Getter
public record RetrievedObject(Path path, String objectId) {
}

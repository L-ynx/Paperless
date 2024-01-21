package at.fhtw.swen3.paperless.misc;

import java.nio.file.Path;

public record RetrievedObject(Path path, String objectId) {
}

package conceptnet.kb.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Node references for edges in conceptNet.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectedNode {

    @NonNull
    @SerializedName("@id")
    String id;

    @SerializedName("@type")
    String type;

    @SerializedName("label")
    String label;

    @SerializedName("language")
    String language;

    @SerializedName("term")
    String term;

    @SerializedName("sense_label")
    String senseLabel;
}
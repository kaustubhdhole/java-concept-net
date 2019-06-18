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
 * One of the 40 types of relation an edge holds between two particular nodes.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CnRelation {

    @NonNull
    @SerializedName("@id")
    String id;

    @SerializedName("@type")
    String type;

    @SerializedName("label")
    String label;

    public RelationType relationType() {
        return RelationType.fromUri(this.id);
    }
}

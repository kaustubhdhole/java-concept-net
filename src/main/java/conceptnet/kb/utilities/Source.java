package conceptnet.kb.utilities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Source of information: Wordnet RDF, Wikipedia, FreeBase, etc.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Source {

    @SerializedName("@id")
    String id;

    @SerializedName("@type")
    String type;

    @SerializedName("contributor")
    String contributor;
}

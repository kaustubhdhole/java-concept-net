package conceptnet.kb.utilities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Pagination properties.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationView {

    @SerializedName("@id")
    String id;

    @SerializedName("@type")
    String type;

    @SerializedName("comment")
    String comment;

    @SerializedName("firstPage")
    String firstPage;

    @SerializedName("nextPage")
    String nextPage;

    @SerializedName("paginatedProperty")
    String paginatedProperty;
}

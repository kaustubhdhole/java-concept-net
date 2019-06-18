package conceptnet.kb.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Model class for the related terms for a particular node.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelatedTerms extends CnObject {

    public RelatedTerms(){
        super();
    }

    @SerializedName("related")
    List<Term> terms;

    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Term {

        @SerializedName("@id")
        String id;

        float weight;
    }
}

package conceptnet.kb.graph;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import conceptnet.kb.graph.heavy.HeavyCnNode;
import conceptnet.kb.utilities.CnNodeApi;
import conceptnet.kb.utilities.ConceptNetApiException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

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
    String display;

    @SerializedName("language")
    String language;

    @SerializedName("term")
    String term;

    @SerializedName("sense_label")
    String senseLabel;

    /**
     * /c/en/bank_account
     *
     * @return bank account
     */
    public String label() {
        return this.term().substring(6).replace("_", " ");
    }


    public CnNode query() throws ConceptNetApiException {
        Optional<CnNode> optionalNode = CnNodeApi.query(label(), language);
        if (optionalNode.isPresent()) {
            return optionalNode.get();
        } else {
            throw new ConceptNetApiException("Missing node in the Edge");
        }
    }
}
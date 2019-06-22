package conceptnet.kb.graph.heavy;

import conceptnet.kb.graph.CnEdge;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
public class HeavyCnEdge {

    CnEdge lightEdge;

    public HeavyCnEdge(CnEdge cnEdge) {
        this.lightEdge = cnEdge;
    }

}

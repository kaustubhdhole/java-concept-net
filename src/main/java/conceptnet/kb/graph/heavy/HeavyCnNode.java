package conceptnet.kb.graph.heavy;

import conceptnet.kb.graph.CnNode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@NoArgsConstructor
public class HeavyCnNode {

    CnNode lightNode;

    public HeavyCnNode(CnNode cnNode) {
        this.lightNode = cnNode;
    }

}

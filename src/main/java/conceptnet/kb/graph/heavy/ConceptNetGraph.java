package conceptnet.kb.graph.heavy;

import com.google.common.graph.MutableNetwork;
import com.google.common.graph.NetworkBuilder;
import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.ConnectedNode;
import conceptnet.kb.utilities.ConceptNetApiException;
import lombok.Getter;

import java.util.List;

/**
 * A subset of the graph which loads nodes recursively up to a certain radius.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetGraph {

    @Getter
    MutableNetwork<HeavyCnNode, HeavyCnEdge> network = NetworkBuilder.directed()
            .allowsParallelEdges(true)
            .expectedNodeCount(1000)
            .expectedEdgeCount(100000)
            .build();

    public ConceptNetGraph(CnNode rootNode) {
        populate(rootNode, 3);
    }


    void populate(CnNode node, int depth) {
        if (depth == 0) {
            return;
        }
        final int currentDepth = depth;
        List<CnEdge> edges = node.edges();
        edges.stream()
                .forEach(e -> {
                    ConnectedNode thatConnection = (e.endNode().term().equalsIgnoreCase(node.id()) ? e.startNode() : e.endNode());
                    CnNode thatNode;
                    try {
                        thatNode = thatConnection.query();
                        network.addEdge(new HeavyCnNode(node), new HeavyCnNode(thatNode), new HeavyCnEdge(e));
                        populate(thatNode, currentDepth - 1);
                    } catch (ConceptNetApiException | IllegalArgumentException e1) {
                        e1.printStackTrace();
                    }
                });
    }

}

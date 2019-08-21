/*
 *
 *  * Copyright 2019 Kaustubh Dhole
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 *
 */

package conceptnet.kb.service;

import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.ConnectedNode;
import conceptnet.kb.graph.RelatedTerms;
import conceptnet.kb.graph.RelationType;
import conceptnet.kb.profanity.ProfanityFilter;
import conceptnet.kb.utilities.CnEdgeApi;
import conceptnet.kb.utilities.CnNodeApi;
import conceptnet.kb.utilities.CnRelatedTermsApi;
import conceptnet.kb.weight.CredibilityFilter;
import conceptnet.kb.wordnet.PosType;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Services to query ConceptNet API.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetService implements KnowledgeBaseService<CnNode, CnEdge, ConnectedNode> {

    private Predicate<ConnectedNode> profanityFilter = new ProfanityFilter();
    private Predicate<CnEdge> credibilityFilter = new CredibilityFilter(2, 1f);

    @Override
    public Optional<CnNode> query(String phrase) {
        try {
            Optional<CnNode> node = CnNodeApi.query(phrase);
            return node;
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<ConnectedNode> getCleanConnections(CnNode node) {
        return node.connectedNodes()
                .stream()
                .filter(profanityFilter)
                .collect(Collectors.toList());
    }

    @Override
    public CnNode cleanCopy(CnNode cnNode) {
        CnNode copy = SerializationUtils.clone(cnNode);
        copy.edges(getCleanEdges(cnNode));
        return copy;
    }

    @Override
    public List<CnEdge> getCleanEdges(CnNode node) {
        return node.edges()
                .stream()
                .filter(edge -> isCleanEdge(edge, node))
                .collect(Collectors.toList());
    }

    @Override
    public CnNode credibleCopy(CnNode cnNode) {
        CnNode copy = SerializationUtils.clone(cnNode);
        copy.edges(getCredibleEdges(cnNode));
        return copy;
    }

    @Override
    public List<CnEdge> getCredibleEdges(CnNode node) {
        return node.edges()
                .stream()
                .filter(credibilityFilter)
                .collect(Collectors.toList());
    }

    private boolean isCleanEdge(CnEdge e, CnNode thisNode) {
        ConnectedNode thatNode = (e.endNode().term().equalsIgnoreCase(thisNode.id()) ? e.startNode() : e.endNode());
        return profanityFilter.test(thatNode);
    }

    @Override
    public Optional<RelatedTerms> getRelatedTerms(String phrase) {
        try {
            Optional<RelatedTerms> relatedTerms = CnRelatedTermsApi.getRelatedTerms(phrase);
            return relatedTerms;
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<String> getHyponyms(String phrase) {
        Optional<CnNode> node = query(phrase);
        if (node.isPresent()) {
            CnNode n = node.get();
            return getHyponyms(n);
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getHyponyms(CnNode node) {
        return node.edges().stream()
                .filter(r -> r.relation().relationType().equals(RelationType.IsA) && r.endNode().term().equalsIgnoreCase(node.id()))
                .map(r -> r.startNode().label())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getHypernyms(String phrase) {
        Optional<CnNode> node = query(phrase);
        if (node.isPresent()) {
            CnNode n = node.get();
            return getHypernyms(n);
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getHypernyms(CnNode node) {
        return node.edges().stream()
                .filter(r -> r.relation().relationType().equals(RelationType.IsA) && r.startNode().term().equalsIgnoreCase(node.id()))
                .map(r -> r.endNode().label())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMeronyms(String phrase) {
        Optional<CnNode> node = query(phrase);
        if (node.isPresent()) {
            CnNode n = node.get();
            return n.edges().stream()
                    .filter(r -> r.relation().relationType().equals(RelationType.PartOf) && r.endNode().term().equalsIgnoreCase(n.id()))
                    .map(r -> r.startNode().label())
                    .distinct()
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRelations(String phrase, List<RelationType> relationTypes) {
        Optional<CnNode> node = query(phrase);
        if (node.isPresent()) {
            CnNode n = node.get();
            return getRelations(n, relationTypes, new ArrayList<>());
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRelations(CnNode cnNode, List<RelationType> relationTypes) {
        return getRelations(cnNode, relationTypes, new ArrayList<>());
    }

    @Override
    public List<String> getRelations(String phrase, List<RelationType> relationTypes, List<PosType> posTypes) {
        Optional<CnNode> node = query(phrase);
        if (node.isPresent()) {
            CnNode n = node.get();
            return getRelations(n, relationTypes, posTypes);
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRelations(CnNode node, List<RelationType> relationTypes, List<PosType> posTypes) {
        if (posTypes.isEmpty()) {
            return node.connectedObjectNodes(relationTypes);
        }
        return node.connectedObjectNodes(relationTypes, posTypes);
    }

    @Override
    public Optional<CnEdge> getEdge(RelationType relationType, String phrase1, String phrase2) {
        try {
            Optional<CnEdge> cnEdge = CnEdgeApi.query(relationType, phrase1, phrase2);
            return cnEdge;
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

}

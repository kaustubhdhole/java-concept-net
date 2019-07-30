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
import conceptnet.kb.graph.RelatedTerms;
import conceptnet.kb.graph.RelationType;
import conceptnet.kb.utilities.CnEdgeApi;
import conceptnet.kb.utilities.CnNodeApi;
import conceptnet.kb.utilities.CnRelatedTermsApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Services to query ConceptNet API.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetService implements KnowledgeBaseService<CnNode> {

    @Override
    public Optional<CnNode> query(String phrase) {
        return CnNodeApi.query(phrase);
    }

    @Override
    public Optional<RelatedTerms> getRelatedTerms(String phrase) {
        return CnRelatedTermsApi.getRelatedTerms(phrase);
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
            return getRelations(n, relationTypes);
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRelations(CnNode node, List<RelationType> relationTypes) {
        return node.connectedObjectNodes(relationTypes);
    }

    @Override
    public Optional<CnEdge> getEdge(RelationType relationType, String phrase1, String phrase2) {
        return CnEdgeApi.query(relationType, phrase1, phrase2);
    }

}

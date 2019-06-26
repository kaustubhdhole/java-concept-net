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

import java.util.List;
import java.util.Optional;

/**
 * Service that can interface with an external KB.
 *
 * @author kaustubhdholé.
 */
public interface KnowledgeBaseService {

    Optional<CnNode> query(String phrase);

    Optional<RelatedTerms> getRelatedTerms(String phrase);

    Optional<CnEdge> getEdge(String edgeUri);

    List<Integer> distanceBetween(CnNode node1, CnNode node2, List<RelationType> relationTypes);

    List<String> getHyponyms(String phrase);

    List<String> getHypernyms(String phrase);
}
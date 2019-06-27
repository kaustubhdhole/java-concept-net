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

    /**
     * Get the ConceptNet node associated with a word or a commonly occurring phrase.
     *
     * @param phrase "checking account"
     */
    Optional<CnNode> query(String phrase);

    /**
     * Retrieve terms similar in the ConceptNet embedding space to a word or a commonly occurring phrase.
     *
     * @param phrase "bambaiyya"
     */
    Optional<RelatedTerms> getRelatedTerms(String phrase);

    /**
     * Query if ConceptNet has a particular edge between two existing concepts
     *
     * @param relationType {@link RelationType}
     * @param phrase1      "bank account"
     * @param phrase2      "savings account"
     * @return "The edge if both the node exists and the edge exists in ConceptNet"
     */
    Optional<CnEdge> getEdge(RelationType relationType, String phrase1, String phrase2);

    /**
     * Get labels of "end" nodes (or "object" nodes) having a {@link RelationType IsA relationship} with the user's given "start" node {or the "subject" node}
     * (if there is a valid "start" node corresponding to the given phrase )
     *
     * @param phrase "bank account"
     * @return "checking account"
     */
    List<String> getHyponyms(String phrase);

    /**
     * Get labels of "start" nodes (or "subject" nodes) having a {@link RelationType IsA relationship} with the user's given "end" node {or the "object" node}
     * (if there is a valid "end" node corresponding to the given phrase)
     *
     * @param phrase "bank account"
     * @return "checking account"
     */
    List<String> getHypernyms(String phrase);

    /**
     * Get labels of "start" nodes (or "subject" nodes) having a {@link RelationType IsA relationship} with the user's given "end" node {or the "object" node}
     * (if there is a valid "end" node corresponding to the given phrase)
     *
     * @param phrase "car"
     * @return "wheel", "seat"
     */
    List<String> getMeronyms(String phrase);
}
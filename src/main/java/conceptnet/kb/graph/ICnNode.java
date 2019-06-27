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

package conceptnet.kb.graph;

import java.util.List;

/**
 * ConceptNet Node Service.
 *
 * @author kaustubhdholé.
 */
public interface ICnNode {

    /**
     * Return all nodes connected one-hop away.
     *
     * @return display term for each node
     */
    List<String> connectedNodes();

    /**
     * Return all the connected nodes in which this node appears as the object.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedSubjectNodes();

    /**
     * Return all the connected nodes in which this node appears as the subject.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedObjectNodes();

    /**
     * Return all the nodes connected with one of the relationTypes
     * in which this node appears as the object.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedSubjectNodes(List<RelationType> relationTypes);

    /**
     * Return all the nodes connected with one of the relationTypes
     * in which this node appears as the subject.
     *
     * @return list of phrases for each node.
     */
    List<String> connectedObjectNodes(List<RelationType> relationTypes);

    /**
     * If thisNode is connected to any of thoseNodes.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    boolean isConnectedToAnyOf(List<String> thoseNodes);

    /**
     * If thisNode is connected to any of thoseNodes.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    long numberOfConnections(List<String> thoseNodes);

    /**
     * If thisNode is loosely related to any of the mentioned thoseNodes.
     * Here, the node is checked not only for an exact match but even for a partial 1-gram overlap.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    boolean unigramOverlap(List<String> thoseNodes);

    /**
     * If thisNode has is related to any of thoseNodes.
     *
     * @param thoseNodes nodes we want to check for relation
     * @return true if there is at least one relation
     */
    boolean anyMatch(List<CnNode> thoseNodes);
}

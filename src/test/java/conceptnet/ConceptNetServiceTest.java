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

package conceptnet;

import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelationType;
import conceptnet.kb.service.ConceptNetService;
import conceptnet.kb.service.KnowledgeBaseService;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test cases for querying ConceptNet API.
 *
 * @author kaustubhdholé.
 */
public class ConceptNetServiceTest {

    private static KnowledgeBaseService knowledgeBaseService;

    static List<String> domainFilter = new ArrayList<>();

    static String searchWord = "current account";

    @BeforeClass
    public static void load() {
        knowledgeBaseService = new ConceptNetService();
        domainFilter.add("bank");
        domainFilter.add("banking");
        domainFilter.add("finance");
    }

    @Test
    public void testRelatedTerms() {
        Optional<CnNode> cnNodeOptional = knowledgeBaseService.query(searchWord);
        if (cnNodeOptional.isPresent()) {
            CnNode cnNode = cnNodeOptional.get();
            boolean isDirectlyConnected = cnNode.isConnectedTo(domainFilter);
            System.out.println(isDirectlyConnected);
            boolean unigramOverlap = cnNode.unigramOverlap(domainFilter);
            System.out.println(unigramOverlap);
        }
    }

    @Test
    public void testHyponyms() {
        List<String> cnNodeOptional = knowledgeBaseService.getHyponyms(searchWord);
        System.out.println("\nHyponyms\n");
        cnNodeOptional.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void domainFilteredHyponyms() {
        System.out.println(searchWord);
        List<String> hyponyms = knowledgeBaseService.getHyponyms(searchWord);
        System.out.println("\nDomain filtered Hyponyms\n");
        hyponyms.stream()
                .map(h -> knowledgeBaseService.query(h).get())
                .filter(h -> h.unigramOverlap(domainFilter))
                .forEach(h -> System.out.println(h.label()));
    }

    @Test
    public void testHypernyms() {
        List<String> cnNodeOptional = knowledgeBaseService.getHypernyms(searchWord);
        System.out.println("\nHypernyms\n");
        cnNodeOptional.stream().forEach(x -> System.out.println(x));
    }

    @Test
    public void testEdge() {
        Optional<CnEdge> edge = knowledgeBaseService.getEdge(RelationType.UsedFor,"example", "explain");
        System.out.println("\nEdges\n");
        edge.ifPresent(e -> System.out.println(e.relation().label() + "(" + e.startNode().label() + "," + e.endNode().label() + ")"));
    }
}

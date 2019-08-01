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

package conceptnet.kb.weight;

import conceptnet.kb.graph.CnEdge;
import lombok.AllArgsConstructor;

import java.util.function.Predicate;

/**
 * How credible a ConceptNet edge is --> Here,
 * <p>
 * credibility is assessed by the (number-of-contributors) and the (weight of the edge).
 *
 * @author kaustubhdholé.
 */
@AllArgsConstructor
public class CredibilityFilter implements Predicate<CnEdge> {

    private int minimumContributors;

    private float minimumWeight;

    @Override
    public boolean test(CnEdge cnEdge) {
        return cnEdge.sources().size() >= minimumContributors
                && cnEdge.weight() >= minimumWeight;
    }
}

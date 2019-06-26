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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;
import conceptnet.kb.utilities.Source;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * An edge, or assertion - unit of knowledge in ConceptNet. It tells us a particular relation between natural-language terms, according to a particular source.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CnEdge extends CnObject {

    public CnEdge() {
        super();
    }

    @SerializedName("dataset")
    String dataSet;

    /**
     * The second argument of the assertion.
     */
    @SerializedName("end")
    ConnectedNode endNode;

    @SerializedName("license")
    String license;

    /**
     * The predicate (relation) of this assertion.
     */
    @SerializedName("rel")
    CnRelation relation;

    @SerializedName("sources")
    List<Source> sources;

    /**
     * The first argument of the assertion.
     */
    @SerializedName("start")
    ConnectedNode startNode;

    /**
     * The original natural language text that expressed this statement.
     * May be null, because not every statement was derived from natural language input.
     * The locations of the start and end concepts will be marked by surrounding them with double brackets.
     * An example of a surfaceText is "a cat is an animal".
     */
    @SerializedName("surfaceText")
    String surfaceText;

    /**
     * The strength with which this edge expresses this assertion.
     * A typical weight is 1, but weights can be higher or lower. All weights are positive.
     */
    @SerializedName("weight")
    float weight;

    public ConnectedNode objectNode() {
        return endNode;
    }

    public ConnectedNode subjectNode() {
        return startNode;
    }
}
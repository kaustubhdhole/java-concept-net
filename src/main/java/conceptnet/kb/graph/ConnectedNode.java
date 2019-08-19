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
import conceptnet.kb.wordnet.PosType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Optional;

/**
 * Node references for edges in conceptNet.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConnectedNode implements Serializable {

    @NonNull
    @SerializedName("@id")
    String id;

    @SerializedName("@type")
    String type;

    @SerializedName("label")
    String display;

    @SerializedName("language")
    String language;

    @SerializedName("term")
    String term;

    @SerializedName("sense_label")
    String senseLabel;

    /**
     * Returns the WordNet syntactic category of the node if present in ConceptNet:
     *
     * @return (n)noun (v)verb (a)adjective and adverb(r)
     */
    public PosType posType() {
        if (null != senseLabel) {
            return PosType.fromString(senseLabel.split(",")[0].trim());
        } else return PosType.None;
    }

    /**
     * /c/en/bank_account
     *
     * @return bank account
     */
    public String label() {
        return this.term().substring(6).replace("_", " ");
    }
}
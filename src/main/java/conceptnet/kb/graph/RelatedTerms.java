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
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Model class for the related terms for a particular node.
 *
 * @author kaustubhdholé.
 */
@Data
@Accessors(fluent = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RelatedTerms extends CnObject {

    public RelatedTerms(){
        super();
    }

    @SerializedName("related")
    List<Term> terms;

    @Data
    @Accessors(fluent = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Term {

        @SerializedName("@id")
        String id;

        float weight;
    }
}

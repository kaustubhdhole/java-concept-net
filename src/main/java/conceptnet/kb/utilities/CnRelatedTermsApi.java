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

package conceptnet.kb.utilities;

import com.google.gson.Gson;
import conceptnet.kb.graph.RelatedTerms;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import lombok.var;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This API endpoint uses word embeddings built from ConceptNet and other inputs to find related terms.
 * The embeddings are a version of ConceptNet Numberbatch, with a reduced vocabulary that makes it more reasonable to load on the server.
 *
 * @author kaustubhdholé.
 */
@UtilityClass
public class CnRelatedTermsApi {

    public Optional<RelatedTerms> getRelatedTerms(String phrase) {
        return getRelatedTerms(phrase, "en");
    }

    public Optional<RelatedTerms> getRelatedTerms(String phrase, String languageCode) {
        return getRelatedTerms(phrase, languageCode, 0, 1000);
    }

    Optional<RelatedTerms> getRelatedTerms(String phrase, String languageCode, int offSet, int limit) {
        try {
            var url = new URL("http://api.conceptnet.io/related/c/" + languageCode + "/"
                    + phrase.replace(" ", "_").toLowerCase()
                    + "?offset=" + offSet + "&limit=" + limit);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() == 200) {
                @Cleanup BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String response = in.lines().collect(Collectors.joining());
                RelatedTerms relatedTerms = new Gson().fromJson(response, RelatedTerms.class);
                if (relatedTerms.error() == null) {
                    return Optional.of(relatedTerms);
                } else {
                    throw new ConceptNetApiException(relatedTerms.error().details());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }
}
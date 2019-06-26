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
import conceptnet.kb.graph.CnEdge;
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelationType;
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
 * API for querying ConceptNet edges. Note that edges are language agnostic as a ConceptNet edge can connect two nodes in different languages.
 *
 * @author kaustubhdholé.
 */
@UtilityClass
public class CnEdgeApi {

    public Optional<CnEdge> query(RelationType relationType, String phrase1, String phrase2) {
        return query(relationType, phrase1, phrase2, 0, 1000);
    }

    public Optional<CnEdge> query(RelationType relationType, String phrase1, String phrase2, int offSet, int limit) {
        try {
            Optional<CnNode> node1 = CnNodeApi.query(phrase1);
            Optional<CnNode> node2 = CnNodeApi.query(phrase2);
            if (!node1.isPresent() || !node2.isPresent()) {
                throw new ConceptNetApiException("No ConceptNet node for one of the nodes exists=.");
            }
            var url = new URL("http://api.conceptnet.io/a/"
                    + "%5B" + relationType.getUri() + "/,"
                    + node1.get().id() + "/,"
                    + node2.get().id() + "/" +"%5D"
                    + "?offset=" + offSet + "&limit=" + limit);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            if (con.getResponseCode() == 200) {
                @Cleanup BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String response = in.lines().collect(Collectors.joining());
                CnEdge cnEdge = new Gson().fromJson(response, CnEdge.class);
                if (cnEdge.error() == null) {
                    return Optional.of(cnEdge);
                } else {
                    throw new ConceptNetApiException(cnEdge.error().details());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

package conceptnet.kb.utilities;

import com.google.gson.Gson;
import conceptnet.kb.graph.CnEdge;
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

    public Optional<CnEdge> query(String phrase) {
        return query(phrase, 0, 1000);
    }

    //TODO: edge input can also accept 3 strings
    // http://api.conceptnet.io/a/%5B/r/UsedFor/,/c/en/example/,/c/en/explain/%5D
    public Optional<CnEdge> query(String phrase, int offSet, int limit) {
        try {
            var url = new URL("http://api.conceptnet.io/a/" + "/"
                    + phrase.replace(" ", "_").toLowerCase()
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
// Enum of all the sources of ConceptNet
//TODO: APIs to be added -->  all statements collected by Open Mind Common Sense about the Spanish word hablar
// http://conceptnet.io/query?source=/s/contributor/omcs&node=/c/es/hablar
//
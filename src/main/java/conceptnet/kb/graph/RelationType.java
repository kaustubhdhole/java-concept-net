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

import lombok.Getter;
import sun.jvm.hotspot.debugger.cdbg.Sym;

/**
 * ConceptNet relations that can apply to text in any language.
 * https://github.com/commonsense/conceptnet5/wiki/Relations
 * <p>
 * These generalized relations are similar in purpose to WordNet’s relations such as hyponym and meronym,
 * as well as to the qualia of the Generative Lex- icon theory (Pustejovsky 1991)
 *
 * @author kaustubhdholé.
 */
public enum RelationType {

    RelatedTo("/r/RelatedTo", "The most general relation. There is some positive relationship between A and B, but ConceptNet can't determine what that relationship is based on the data. This was called ConceptuallyRelatedTo in ConceptNet 2 through 4. Symmetric.", "learn ↔ erudition",
            Symmetry.SYMMETRIC),
    ExternalURL("/r/ExternalURL", "Points to a URL outside of ConceptNet, where further Linked Data about this term can be found. Similar to RDF's seeAlso relation.", "knowledge → http://dbpedia.org/page/Knowledge"),
    FormOf("/r/FormOf", "A is an inflected form of B; B is the root word of A.", "slept → sleep"),
    IsA("/r/IsA", "A is a subtype or a specific instance of B; every A is a B. This can include specific instances; the distinction between subtypes and instances is often blurry in language. This is the hyponym relation in WordNet.", "car → vehicle; Chicago → city"),
    PartOf("/r/PartOf", "A is a part of B. This is the part meronymrelation in WordNet.", "gearshift → car"),
    HasA("/r/HasA", "B belongs to A, either as an inherent part or due to a social construct of possession. HasA is often the reverse of PartOf.", "bird → wing; pen → ink"),
    UsedFor("/r/UsedFor", "A is used for B; the purpose of A is B.", "bridge → cross water"),
    CapableOf("/r/CapableOf", "Something that A can typically do is B.", "knife → cut"),
    AtLocation("/r/AtLocation", "A is a typical location for B, or A is the inherent location of B. Some instances of this would be considered meronyms in WordNet.", "butter → refrigerator; Boston → Massachusetts"),
    Causes("/r/Causes", "A and B are events, and it is typical for A to cause B.", "exercise → sweat"),
    HasSubevent("/r/HasSubevent", "A and B are events, and B happens as a subevent of A.", "eating → chewing"),
    HasFirstSubevent("/r/HasFirstSubevent", "A is an event that begins with subevent B.", "sleep → close eyes"),
    HasLastSubevent("/r/HasLastSubevent", "A is an event that concludes with subevent B.", "cook → clean up kitchen"),
    HasPrerequisite("/r/HasPrerequisite", "In order for A to happen, B needs to happen; B is a dependency of A.", "dream → sleep"),
    HasProperty("/r/HasProperty", "A has B as a property; A can be described as B.", "ice → cold"),
    MotivatedByGoal("/r/MotivatedByGoal", "Someone does A because they want result B; A is a step toward accomplishing the goal B.", "compete → win"),
    ObstructedBy("/r/ObstructedBy", "A is a goal that can be prevented by B; B is an obstacle in the way of A.", "sleep → noise"),
    Desires("/r/Desires", "A is a conscious entity that typically wants B. Many assertions of this type use the appropriate language's word for person as A.", "person → love"),
    CreatedBy("/r/CreatedBy", "B is a process or agent that creates A.", "cake → bake"),
    Synonym("/r/Synonym", "A and B have very similar meanings. They may be translations of each other in different languages. This is the synonym relation in WordNet as well. Symmetric.", "sunlight ↔ sunshine",
            Symmetry.SYMMETRIC),
    Antonym("/r/Antonym", "A and B are opposites in some relevant way, such as being opposite ends of a scale, or fundamentally similar things with a key difference between them. Counterintuitively, two concepts must be quite similar before people consider them antonyms. This is the antonym relation in WordNet as well. Symmetric.", "black ↔ white; hot ↔ cold",
            Symmetry.SYMMETRIC),
    DistinctFrom("/r/DistinctFrom", "A and B are distinct member of a set; something that is A is not B. Symmetric.", "red ↔ blue; August ↔ September"),
    DerivedFrom("/r/DerivedFrom", "A is a word or phrase that appears within B and contributes to B's meaning.", "pocketbook → book"),
    SymbolOf("/r/SymbolOf", "A symbolically represents B.", "red → fervor"),
    DefinedAs("/r/DefinedAs", "A and B overlap considerably in meaning, and B is a more explanatory version of A.", "peace → absence of war"),
    Entails("/r/Entails", "If A is happening, B is also happening. (This may be merged with HasPrerequisite in a later version.),", "run → move"),
    MannerOf("/r/MannerOf", "A is a specific way to do B. Similar to IsA, but for verbs.", "auction → sale"),
    LocatedNear("/r/LocatedNear", "A and B are typically found near each other. Symmetric.", "chair ↔ table", Symmetry.SYMMETRIC),
    HasContext("/r/HasContext", "A is a word used in the context of B, which could be a topic area, technical field, or regional dialect.", "astern → ship; arvo → Australia"),
    DBPedia("/r/dbpedia", "Some relations have been provisionally imported from DBPedia, and don't correspond to any of the existing relations. For now, these are in the /r/dbpedia/...namespace, such as /r/dbpedia/genre. The DBPedia relations represented this way are genre, influencedBy, knownFor, occupation, language, field, product, capital, and leader.", ""),
    SimilarTo("/r/SimilarTo", "A is similar to B. Symmetric.", "mixer ↔ food processor", Symmetry.SYMMETRIC),
    EtymologicallyRelatedTo("/r/EtymologicallyRelatedTo", "A and B have a common origin. Symmetric.", "folkmusiikki ↔ folk music", Symmetry.SYMMETRIC),
    EtymologicallyDerivedFrom("/r/EtymologicallyDerivedFrom", "A is derived from B.", "dejta → date"),
    CausesDesire("/r/CausesDesire", "A makes someone want B.", "having no food → go to a store"),
    MadeOf("/r/MadeOf", "A is made of B.", "bottle → plastic"),
    ReceivesAction("/r/ReceivesAction", "B can be done to A.", "button → push"),
    InstanceOf("/r/InstanceOf", "A is an example of B.", "meringue → dessert"),

    NotDesires("/r/NotDesires", "Negative relation of Desires", true),
    NotUsedFor("/r/NotUsedFor", "Negative relation of UsedFor", true),
    NotCapableOf("/r/NotCapableOf", "Negative relation of Capable of", true),
    NotHasProperty("/r/NotHasProperty", "Negative relation of HasProperty", true);

    @Getter
    String uri;
    String description;
    String example;
    @Getter
    boolean isNegative;
    Symmetry symmetric;

    RelationType(String uri, String description, String example) {
        this.uri = uri;
        this.description = description;
        this.example = example;
        this.isNegative = false;
        this.symmetric = Symmetry.ASYMMETRIC;
    }

    RelationType(String uri, String description, String example, Symmetry symmetric) {
        this.uri = uri;
        this.description = description;
        this.example = example;
        this.isNegative = false;
        this.symmetric = symmetric;
    }

    RelationType(String uri, String description, boolean isNegative) {
        this.uri = uri;
        this.description = description;
        this.isNegative = isNegative;
    }

    public static RelationType fromUri(String uri) {
        if (uri.startsWith("/r/dbpedia")) {
            return DBPedia;
        }
        return RelationType.valueOf(uri.substring(3));
    }

    public enum Symmetry {
        SYMMETRIC,
        ASYMMETRIC
    }

}

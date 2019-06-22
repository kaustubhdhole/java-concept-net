package conceptnet.kb.languages;

import lombok.AllArgsConstructor;

/**
 * The 10 core languages, where ConceptNet supports the language well, with a very large vocabulary,
 * lots of assertions expressed within that language, and varied sources of knowledge
 *
 * @author kaustubhdholé.
 */
@AllArgsConstructor
public enum Language {

    English("en"),
    French("fr"),
    Italian("it"),
    German("de"),
    Spanish("es"),
    Russian("ru"),
    Portuguese("pt"),
    Japanese("ja"),
    Dutch("nl"),
    Chinese("zh"),
    MultlLingual("mul");// this code is for nodes which are common across languages like "emojis"

    String code;
}

# JavaConceptNet

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) ![Build Status](https://img.shields.io/bitbucket/pipelines/kaustubhdhole/java-concept-net.svg)

An easy and flexible access to the ConceptNet knowledge graph via the ConceptNet API. 


- Java Client for ConceptNet API 
- And other ConceptNet Utility Functions.

### Requirements
* [Java 8](http://www.oracle.com/technetwork/java/javase/overview/index.html) 
* [Apache Maven 3.3+](https://maven.apache.org/)

This module contains:
### Core API calls:
+ **CnNodeApi** API for querying ConceptNet nodes
+ **CnEdgeApi** API for querying ConceptNet edges. (Note that edges are language agnostic as a ConceptNet edge can connect two nodes in different languages.) 
+ **CnRelatedTermsApi** This API endpoint uses word embeddings built from ConceptNet and other inputs to find related terms.
                     (The embeddings are a version of ConceptNet Numberbatch, with a reduced vocabulary that makes it more reasonable to load on the server.)
### Optional Filters to Clean ConceptNet                  
+ **CredibilityFilter**   Credibility of a ConceptNet edge is assessed by the (number-of-contributors) and the (weight of the edge).
+ **ProfanityFilter**  Determine if a concept in ConceptNet is profane.                 

The following shows a simple Java query with the phrase "money transfer"

```java
import conceptnet.kb.graph.CnNode;
import conceptnet.kb.graph.RelatedTerms;
import conceptnet.kb.graph.RelationType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JavaConceptNetDemo {

    public static void main(String[] args) {
        
        KnowledgeBaseService knowledgeBaseService = new ConceptNetService();
        
        String searchWord = "money transfer";
        
        Optional<CnNode> cnNodeOptional = knowledgeBaseService.query(searchWord);
        if (cnNodeOptional.isPresent()) {
            CnNode cnNode = cnNodeOptional.get();

            List<String> domainFilter = Arrays.asList(new String[]{"bank", "banking", "finance"});
            boolean isDirectlyConnected = cnNode.isConnectedTo(domainFilter);
            boolean unigramOverlap = cnNode.unigramOverlap(domainFilter);

            List<RelationType> queryRelations = Arrays.asList(new RelationType[]{RelationType.Synonym, RelationType.IsA});
            List<String> connectedSubjectNodes = cnNode.connectedSubjectNodes(queryRelations);
        }

        Optional<RelatedTerms> relatedTerms = knowledgeBaseService.getRelatedTerms(searchWord);
    }
}
```

To add a dependency on JavaConceptNet using Maven, clone the repository locally and build using:
```bash
mvn clean install
```
And then add the following to your POM project:

```xml
<dependency>
    <groupId>io.github.javanlp</groupId>
    <artifactId>java-concept-net</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Bugs and New Features
If you find bugs or would like to suggest or add new features, please let us know via a pull request or an issue.
                     
The main purpose of the repository is to build an easy and flexible to use Java client
for quick experimentation and integration without worrying about the hassle of setting up ConceptNet.
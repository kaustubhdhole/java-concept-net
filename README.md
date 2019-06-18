# Java-Concept-Net

- Java Client for ConceptNet API 
- And other ConceptNet Utility Functions.

Requirements:
+ Java 8
+ Maven 3.3+

This module contains:

+ **CnNodeApi** API for querying ConceptNet nodes
+ **CnEdgeApi** API for querying ConceptNet edges. (Note that edges are language agnostic as a ConceptNet edge can connect two nodes in different languages.) 
+ **CnRelatedTermsApi** This API endpoint uses word embeddings built from ConceptNet and other inputs to find related terms.
                     (The embeddings are a version of ConceptNet Numberbatch, with a reduced vocabulary that makes it more reasonable to load on the server.)
                     
                     
The main purpose of the repository is to build an easy and flexible to use Java client
for quick experimentation and integration without worrying about the hassle of setting up ConceptNet.
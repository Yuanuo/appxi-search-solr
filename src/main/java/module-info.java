module appxi.search.solr {
    requires transitive static appxi.search.solr.aio;

    requires transitive static spring.core;
    requires transitive static spring.context;
    requires transitive static spring.beans;
    requires transitive static spring.data.commons;
    requires transitive static spring.data.solr;
    requires transitive static spring.tx;
    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.jackson.dataformat.cbor;
    requires transitive com.fasterxml.jackson.dataformat.smile;
    requires transitive java.management;
    requires transitive java.naming;
    requires transitive jdk.management;

    requires transitive org.slf4j;
    requires transitive org.slf4j.simple;
    requires transitive org.apache.commons.logging;
    requires transitive org.apache.logging.log4j;
    requires transitive org.apache.logging.log4j.core;
    requires transitive org.apache.logging.log4j.slf4j.impl;

    exports org.appxi.search.solr;

    opens org.appxi.search.solr;
}
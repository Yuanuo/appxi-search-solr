module appxi.search.solr {
    requires static appxi.search.solr.aio;
    requires static spring.data.solr;
    requires static spring.data.commons;

    exports org.appxi.search.solr;

    opens org.appxi.search.solr;
}
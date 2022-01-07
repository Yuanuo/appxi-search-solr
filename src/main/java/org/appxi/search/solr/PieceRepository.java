package org.appxi.search.solr;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.PartialUpdate;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface PieceRepository extends SolrCrudRepository<Piece, String> {
    static String wrapWhitespace(String str) {
        return str.replace(" ", "\\ ");
    }

    void deleteAllByProvider(String provider);

    void deleteAllByPath(String path);

    void deleteAllByType(String type);

    void deleteAllByProjects(String project);

    void deleteAllByCategories(String category);

    default void updateInAtomicAdd(SolrTemplate solrTemplate, String collection,
                                   String id, Map.Entry<String, Object>... addValues) {
        PartialUpdate update = new PartialUpdate("id", id);
        List.of(addValues).forEach(entry -> update.addValueToField(entry.getKey(), entry.getValue()));
        solrTemplate.saveBean(collection, update);
        solrTemplate.softCommit(collection);
    }

    default void updateInAtomicsAdd(SolrTemplate solrTemplate, String collection,
                                    Map.Entry<String, Map.Entry<String, Object>[]>... addValues) {
        for (int i = 0; i < addValues.length; i++) {
            PartialUpdate update = new PartialUpdate("id", addValues[i].getKey());
            List.of(addValues[i].getValue()).forEach(entry -> update.addValueToField(entry.getKey(), entry.getValue()));
            solrTemplate.saveBean(collection, update);
            if (i > 0 && i % 500 == 0)
                solrTemplate.softCommit(collection);
        }
        solrTemplate.softCommit(collection);
    }

    default void updateInAtomicSet(SolrTemplate solrTemplate, String collection,
                                   String id, Map.Entry<String, Object>... setValues) {
        PartialUpdate update = new PartialUpdate("id", id);
        List.of(setValues).forEach(entry -> update.setValueOfField(entry.getKey(), entry.getValue()));
        solrTemplate.saveBean(collection, update);
        solrTemplate.softCommit(collection);
    }

    default void updateInAtomicsSet(SolrTemplate solrTemplate, String collection,
                                    Map.Entry<String, Map.Entry<String, Object>[]>... setValues) {
        for (int i = 0; i < setValues.length; i++) {
            PartialUpdate update = new PartialUpdate("id", setValues[i].getKey());
            List.of(setValues[i].getValue()).forEach(entry -> update.setValueOfField(entry.getKey(), entry.getValue()));
            solrTemplate.saveBean(collection, update);
            if (i > 0 && i % 500 == 0)
                solrTemplate.softCommit(collection);
        }
        solrTemplate.softCommit(collection);
    }
}

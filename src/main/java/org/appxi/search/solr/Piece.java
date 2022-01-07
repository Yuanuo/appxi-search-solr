package org.appxi.search.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.Score;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SolrDocument(collection = Piece.REPO)
public class Piece {
    public static final String REPO = "pieces";

    @org.springframework.data.annotation.Id
    @Indexed("id")
    public String id;

    @Indexed("_provider_s")
    public String provider;

    @Indexed("_version_s")
    public String version;

    @Field("project_ss")
    public Set<String> projects = new HashSet<>();

    @Indexed(value = "path_descendent_path")
    public String path;

    @Indexed("type_s")
    public String type;

    @Indexed("title_s")
    public String title;

    @Indexed("priority_d")
    public double priority = 5;

    @Field("category_ss")
    public Set<String> categories = new HashSet<>();

    // <field name="'field_' + mapEntry[0].key">mapEntry[0].value</field>
    // <field name="'field_' + mapEntry[1].key">mapEntry[1].value</field>
    @Dynamic
    @Field("field_*")
    public Map<String, String> fields = new HashMap<>();

    // <field name="mapEntry[0].key">mapEntry[0].value</field>
    // <field name="mapEntry[1].key">mapEntry[1].value</field>
    @Field("text_*")
    public Map<String, String> texts = new HashMap<>();

    // <field name="'extra_' + mapEntry[0].key">mapEntry[0].value</field>
    // <field name="'extra_' + mapEntry[1].key">mapEntry[1].value</field>
    @Dynamic
    @Field("extra_*")
    public Map<String, Object> extras = new HashMap<>();

    @Score
    public double score;

    @Override
    public String toString() {
        return this.title;
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    public Piece setId(String id) {
        this.id = id;
        return this;
    }

    public Piece setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public Piece setVersion(String version) {
        this.version = version;
        return this;
    }

    public Piece setPath(String path) {
        this.path = path;
        return this;
    }

    public Piece setType(String type) {
        this.type = type;
        return this;
    }

    public Piece setTitle(String title) {
        this.title = title;
        return this;
    }

    public Piece setPriority(double priority) {
        this.priority = priority;
        return this;
    }

    public Piece setProjects(Set<String> projects) {
        this.projects = projects;
        return this;
    }

    public Piece setCategories(Set<String> categories) {
        this.categories = categories;
        return this;
    }

    public Piece setFields(Map<String, String> fields) {
        this.fields = fields;
        return this;
    }

    public Piece setTexts(Map<String, String> texts) {
        this.texts = texts;
        return this;
    }

    public Piece setExtras(Map<String, Object> extras) {
        this.extras = extras;
        return this;
    }

    public String field(String shortKey) {
        return fields.get(shortKey);
    }

    public String text(String fullKey) {
        return texts.get(fullKey);
    }

    @SuppressWarnings("unchecked")
    public <T> T extra(String shortKey) {
        return (T) extras.get(shortKey);
    }

    public Piece field(String shortKey, String value) {
        fields.put(shortKey, value);
        return this;
    }

    public Piece text(String fullKey, String value) {
        texts.put(fullKey, value);
        return this;
    }

    public Piece extra(String shortKey, Object value) {
        extras.put(shortKey, value);
        return this;
    }

    public static Piece of() {
        return new Piece();
    }

    @Override
    public Piece clone() {
        return new Piece()
                .setId(id)
                .setVersion(version)
                .setProvider(provider)
                .setType(type)
                .setPath(path)
                .setTitle(title)
                .setPriority(priority)
                .setProjects(new HashSet<>(projects))
                .setCategories(new HashSet<>(categories))
                .setFields(new HashMap<>(fields))
                .setTexts(new HashMap<>(texts))
                .setExtras(new HashMap<>(extras))
                ;
    }
}


package ru.orchidaily.core.data;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import ru.orchidaily.data.product.Product;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/16/18 1:37 PM
 */

@SolrDocument(solrCoreName = "product")
public class SolrProduct implements Product<String> {
    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "name", type = "string")
    private String name;

    @Indexed(name = "description", type = "string")
    private String description;

    @Field("mappedField_*")
    private Map<String, List<String>> mappedFieldValues;

    @Dynamic
    @Field("dynamicMappedField_*")
    private Map<String, String> dynamicMappedFieldValues;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

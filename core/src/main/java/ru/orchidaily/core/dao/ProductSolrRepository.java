package ru.orchidaily.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.stereotype.Repository;
import ru.orchidaily.core.data.SolrProduct;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 10:43 PM
 */
@Repository
public class ProductSolrRepository extends SimpleSolrRepository<SolrProduct, String> {
    @Autowired
    public ProductSolrRepository(final @Qualifier("solrTemplate") SolrTemplate solrTemplate) {
        super(solrTemplate, SolrProduct.class);
    }
}

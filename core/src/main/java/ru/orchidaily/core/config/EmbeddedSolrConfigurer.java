package ru.orchidaily.core.config;

import org.apache.solr.client.solrj.SolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;

/**
 *
 * @author zera
 */
@Configuration
@EnableSolrRepositories(basePackages= {"ru.orchidaily.core.dao"})
//@Profile("embedded")
@PropertySource("classpath:application.properties")
class EmbeddedSolrConfigurer {
    private static final Logger log = LoggerFactory.getLogger(EmbeddedSolrConfigurer.class.getName());

    @Value("${embedded.solr.home}")
    private String solrHome;

    @Bean
    public SolrClient solrClient() throws Exception {
        log.warn("start configuration");
        EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(solrHome);
        return factory.getSolrClient();
    }

    @Bean
    @Qualifier("solrTemplate")
    public SolrTemplate solrTemplate() throws Exception {
        return new SolrTemplate(solrClient());
    }
}

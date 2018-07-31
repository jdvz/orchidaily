package ru.orchidaily.core.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrClientFactoryBean;

import static ru.orchidaily.core.constants.CoreConstants.SOLR_HOST;

/**
 *
 * @author zera
 */
@Configuration
@EnableSolrRepositories(basePackages= {"ru.orchidaily.data"})
@Profile("standalone")
@PropertySource("classpath:application.properties")
class StandaloneSolrConfigurer {
    @Value("${solr.host}")
    private String solrHost;

    @Bean
    public SolrClient solrClient() {
        HttpSolrClientFactoryBean factory = new HttpSolrClientFactoryBean();

        factory.setUrl(solrHost);

        return factory.getSolrClient();
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        return new SolrTemplate(solrClient());
    }
}

package com.zzaug.review.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

	@Value("${spring.data.elasticsearch.url}")
	private String url;

	@Value("${spring.data.elasticsearch.username}")
	private String username;

	@Value("${spring.data.elasticsearch.password}")
	private String password;

	@Override
	public RestHighLevelClient elasticsearchClient() {
		ClientConfiguration configuration =
				ClientConfiguration.builder().connectedTo(url).withBasicAuth(username, password).build();
		return RestClients.create(configuration).rest();
	}
}

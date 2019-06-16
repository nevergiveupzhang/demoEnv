package com.example.demo;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class ElasticsearchController {

	@RequestMapping("/search")
	@ResponseBody
	public String search() throws IOException {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		SearchRequest searchRequest = new SearchRequest("logstash-newgwhoisd-2019.06.13");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
//		searchSourceBuilder.query(QueryBuilders.termQuery("client", "218.241.97.205")); 
		searchSourceBuilder.query(QueryBuilders.boolQuery().should(QueryBuilders.termQuery("client", "218.241.97.205")));
		searchRequest.source(searchSourceBuilder); 
		SearchResponse searchResponse=client.search(searchRequest);
		return searchResponse.toString();
	}
}

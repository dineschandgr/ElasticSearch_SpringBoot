package io.elasticsearch.config;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import io.elasticsearch.model.Customer;

@Component
public class SearchQueryBuilder {

	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;
	
	//searches the text in firstName and lastName fields using queryStringQuery
	//Also using wildcard match
	public SearchHits<Customer> getSearchData(String text){
		
		QueryBuilder query = QueryBuilders.boolQuery()
								.should(QueryBuilders.queryStringQuery(text)
								.lenient(true)
								.field("firstName")
								.field("lastName")
								).should(QueryBuilders.queryStringQuery("*"+text+"*")
								  .lenient(true)	
									.field("firstName")
									.field("lastName")
								);
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
									.withQuery(query)
									.build();
		
		SearchHits<Customer> customerList = elasticsearchRestTemplate.search(nativeSearchQuery,Customer.class);
									
		return customerList;
	}
	
	//Searches for wildcard char in firstName field
	public SearchHits<Customer> getSearchDataWildCard(String text){
		
		String search =".*"+text+".*";	
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
								.withFilter(QueryBuilders.regexpQuery("firstName", search))
								.build();
		
		SearchHits<Customer> customerList = elasticsearchRestTemplate.search(nativeSearchQuery,Customer.class);
		
		return customerList;
	}
	
	//searches the text in firstName and lastName fields using multiMatchQuery
	//similar to 1st method
	//Need to provide exact text
	public SearchHits<Customer> getSearchDataMultiMatch(String text){
		
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
								.withQuery(QueryBuilders.multiMatchQuery(text)
								.field("firstName").field("lastName")
								.type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();
		
		SearchHits<Customer> customerList = elasticsearchRestTemplate.search(nativeSearchQuery,Customer.class);
		
		return customerList;
	}
	

	//searches 2 fields firstName and age and returns matching results even if one of the value is found
	public SearchHits<Customer> getSearchDataMultipleFields(String firstName, String age){
		
		QueryBuilder query = QueryBuilders.boolQuery()
								.should(QueryBuilders.matchQuery("firstName",firstName))
								.should(QueryBuilders.matchQuery("age",age));
		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
									.withQuery(query)
									.build();
		
		SearchHits<Customer> customerList = elasticsearchRestTemplate.search(nativeSearchQuery,Customer.class);
									
		return customerList;
	}
	
}

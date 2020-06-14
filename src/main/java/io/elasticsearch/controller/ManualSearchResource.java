package io.elasticsearch.controller;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.elasticsearch.config.SearchQueryBuilder;
import io.elasticsearch.model.Customer;

@RestController
@RequestMapping("/manual/search")
public class ManualSearchResource {

	@Autowired
	private SearchQueryBuilder searchQueryBuilder;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ManualSearchResource.class);
	
	@RequestMapping(value="/searchall/{text}")
	public SearchHits<Customer> getSearchData(@PathVariable String text){
		logger.info("inside DSL controller getSearchData "+new Date()+ " text is "+text);
		return searchQueryBuilder.getSearchData(text);
	}
	
	@RequestMapping(value="/wildcard/{text}")
	public SearchHits<Customer> getSearchDataWildCard(@PathVariable String text){
		logger.info("inside DSL controller getSearchDataWildCard "+new Date()+ " text is "+text);
		return searchQueryBuilder.getSearchDataWildCard(text);
	}
	
	@RequestMapping(value="/multimatch/{text}")
	public SearchHits<Customer> getSearchDataMultiMatch(@PathVariable String text){
		logger.info("inside DSL controller getSearchDataMultiMatch "+new Date()+ " text is "+text);
		return searchQueryBuilder.getSearchDataMultiMatch(text);
	}
	
	@RequestMapping(value="/multifields/{firstName}/{age}")
	public SearchHits<Customer> getSearchDataMultiFields(@PathVariable String firstName, @PathVariable String age){
		logger.info("inside DSL controller getSearchDataMultiFields "+new Date()+ " firstName is "+firstName+" age is "+age);
		return searchQueryBuilder.getSearchDataMultipleFields(firstName,age);
	}
}

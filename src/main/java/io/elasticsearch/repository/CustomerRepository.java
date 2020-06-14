package io.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import io.elasticsearch.model.Customer;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String>{

	List<Customer> findByFirstName(String firstName); 
	List<Customer> findByLastName(String lastName);
}

# ElasticSearch_SpringBoot
Elastic Search using Spring Boot

There are 2 resource flows in this project

1. CRUD Operation using ElasticsearchRepository
  
  - to create,update,delete,retrieve data from the indexes
  - The retrieve operation is limited
  
2. Query data from indexes using DSL (Domain Specific Language)
  - Retrieve data from index using criteria like multiple fields, wildcard etc
  

There are 2 use cases in this project and both use different indexes

1. CRUD operations and retrieval is performed in the index specified by the application i.e elasticsearch1
2. Logging the application logs by Logstash in a index named logstash*

Prerequisite

1. Download ElasticSearch zip and start ElasticSearch using elasticsearch.bat. Elastic search runs on port 9200
2. Download Kibana zip and specify ElasticSearch url localhost:9200. start Kibana.bat after that. Kibana runs on port 5601
3. Download logStash.zip and specify log file url and ElasticSearch url localhost:9200. 
4. Create logstash.conf file inside the bin folder to specify the input,filter and output stages. start logstash after that

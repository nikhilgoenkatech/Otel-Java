package com.dynatrace.sample.databases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElasticSearch extends Database implements CRUD{

	private static RestHighLevelClient restHighLevelClient;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final String INDEX = "persondata";
	private static final String TYPE = "person";

	public ElasticSearch() {
		super();
	}

	private static synchronized void makeConnection() {
		if(restHighLevelClient==null) {
			restHighLevelClient = new RestHighLevelClient(
					RestClient.builder(
							new HttpHost("elasticsearch", 9200, "http")));
		}
	}

	private static synchronized void closeConnection() throws IOException {
		restHighLevelClient.close();
		restHighLevelClient = null;
	}

	@Override
	public String delete() {
		String id = "1";
		DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
		DeleteResponse deleteResponse;
		try {
			deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
		} catch (java.io.IOException e){
			return e.getLocalizedMessage();
		}
		return (deleteResponse == null) ? "null" : deleteResponse.toString();
	}

	@Override
	public String create() {
		return "create not needed for elastic";
	}
	@Override
	public String insert() {
		Person person = new Person();
		person.setName("Bruce Wayne");
		person.setPersonId("1");
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("personId", person.getPersonId());
		dataMap.put("name", person.getName());
		IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, person.getPersonId()).source(dataMap);
		IndexResponse indexResponse;
		try {
			indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (ElasticsearchException elasticsearchException) {
			return elasticsearchException.getDetailedMessage();
		} catch (IOException ioException) {
			return ioException.getLocalizedMessage();
		}
		return indexResponse != null ? indexResponse.toString() : "null";
	}
	@Override
	public String update() {
		String id = "1";
		UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true);    // Fetch Object after its update
		Person person = new Person();
		person.setPersonId(id);
		person.setName("Bruce Banner");
		try {
			String personJson = objectMapper.writeValueAsString(person);
			updateRequest.doc(personJson, XContentType.JSON);
			UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
			return updateResponse != null ? updateResponse.toString() : "null";
		}catch (JsonProcessingException e){
			return e.getMessage();
		} catch (java.io.IOException e){
			return e.getLocalizedMessage();
		}
	}

	@Override
	public String read() {
		String id = "1";
		GetRequest getPersonRequest = new GetRequest(INDEX, TYPE, id);
		GetResponse getResponse;
		try {
			getResponse = restHighLevelClient.get(getPersonRequest, RequestOptions.DEFAULT);
		} catch (java.io.IOException e){
			return e.getLocalizedMessage();
		}
		return getResponse != null ? getResponse.toString() : "null";

	}

	@Override
	public String makeCRUDCalls() {
		makeConnection();
		String toBeReturned = "create: "+ create() +", insert: "+insert()+", read: "+read()+ ", update: "+update()+", delete: "+delete();
		try {
			closeConnection();
		} catch (IOException ioException) {
			toBeReturned = ioException.getLocalizedMessage();
		}
		return toBeReturned;
	}

	private static class Person {
		private String personId;
		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public void setPersonId(String personId) {
			this.personId = personId;
		}

		@Override
		public String toString() {
			return String.format("Person{personId='%s', name='%s'}",
					personId, name);
		}

		public String getName() {
			return name;
		}
		public String getPersonId() {
			return personId;
		}
	}
}

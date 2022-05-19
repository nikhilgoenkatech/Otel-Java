package com.dynatrace.sample.databases;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;


public class GraphQL extends Database implements CRUD{
	public GraphQL() {
		super();
	}

	private HttpRequest getRequest(String uri) {
		return HttpRequest.newBuilder(
						URI.create(uri))
				.header("accept", "application/json")
				.build();
	}

	private HttpClient getClient() {
		return HttpClient.newHttpClient();
	}

	@Override
	public String delete() throws URISyntaxException {
		HttpResponse<String> response = null;
		try {
			response = getClient()
					.send(getRequest("http://graphql-java:8080/graphiql?query=mutation%20%7B%0A%09delete(name%3A%22Bruce%20Wayne%22)%0A%20%20%0A%7D"), HttpResponse.BodyHandlers.ofString());
		} catch(IOException | InterruptedException ioex) {
			return ioex.getMessage();
		}
		return response.body();
	}

	@Override
	public String create() throws URISyntaxException {
		HttpResponse<String> response = null;
		try {
			response = getClient().send(getRequest("http://graphql-java:8080/graphiql?query=mutation%20%7B%0A%09addPerson(person%3A%20%7Bname%3A%22Bruce%20Wayne%22%7D)%20%7B%0A%09%09id%0A%09%7D%20%0A%7D"), HttpResponse.BodyHandlers.ofString());
		} catch(IOException | InterruptedException ioex) {
			return ioex.getMessage();
		}
		return response.body();
		}
	@Override
	public String insert() throws SQLException, URISyntaxException {
		HttpResponse<String> response = null;
		try {
			response = getClient().send(getRequest("http://graphql-java:8080/graphiql?query=mutation%20%7B%0A%09addPerson(person%3A%20%7Bname%3A%22Bruce%20Wayne%22%7D)%20%7B%0A%09%09id%0A%09%7D%20%0A%7D"), HttpResponse.BodyHandlers.ofString());
		} catch(IOException | InterruptedException ioex) {
			return ioex.getMessage();
		}
		return response.body();
	}

	@Override
	public String update() throws URISyntaxException {
		HttpResponse<String> response = null;
		try {
			response = getClient().send(getRequest("http://graphql-java:8080/graphiql?query=query%20%7B%0A%09persons%20%7B%0A%20%20%20%20name%0A%20%20%20%20id%0A%20%20%20%20githubAccount%0A%20%20%7D%0A%20%20%0A%7D"), HttpResponse.BodyHandlers.ofString());
		} catch(IOException | InterruptedException ioex) {
			return ioex.getMessage();
		}
		return response.body();
	}

	@Override
	public String read() throws URISyntaxException {
		HttpResponse<String> response = null;
		try {
			response = getClient().send(getRequest("http://graphql-java:8080/graphiql?query=query%20%7B%0A%09persons%20%7B%0A%20%20%20%20name%0A%20%20%20%20id%0A%20%20%7D%0A%20%20%0A%7D"), HttpResponse.BodyHandlers.ofString());
		} catch(IOException | InterruptedException ioex) {
			return ioex.getMessage();
		}
		return response.body();
	}

	@Override
	public String makeCRUDCalls() throws SQLException, URISyntaxException {
		return "create: "+ create() +", insert: "+insert()+", read: "+read()+ ", update: "+update()+", delete: "+delete();
	}
}

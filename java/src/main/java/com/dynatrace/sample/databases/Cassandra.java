package com.dynatrace.sample.databases;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class Cassandra extends Database implements CRUD{
	private CqlSession cqlSession;
	private final String localDataCenter = "datacenter1";
	private final String contactPoint = "cassandra";
	private final String keyspace = "system";
	public Cassandra() throws UnknownHostException {
		super();
		setPort(9042);
		setUrl("cassandra");
		cqlSession = CqlSession.builder()
				.withKeyspace(keyspace)
				.addContactPoint(new InetSocketAddress(
						InetAddress.getByName(contactPoint), getPort()))
				.withLocalDatacenter(localDataCenter)
				.build();
	}
	@Override
	public String delete() throws SQLException {
		if(cqlSession.execute("DELETE FROM people.person WHERE id=10;").wasApplied())
			return "delete successful, ";
		else
			return "delete failure, ";
	}

	@Override
	public String create() {
		if(isCreateCalled())
			return "create was already called";
		setCreateCalledTrue();
		cqlSession.execute("CREATE KEYSPACE IF NOT EXISTS people WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };");
		if(cqlSession.execute("CREATE TABLE IF NOT EXISTS people.person (id int PRIMARY KEY, firstname text, lastname text, email text, country text, birthday text);").wasApplied())
			return "success on create, ";
		else
			return "failure on create, ";
	}

	@Override
	public String update() {
		if(cqlSession.execute("UPDATE people.person SET lastname='Banner' WHERE id=10;").wasApplied())
			return "update successful, ";
		else
			return "update failure, ";
	}

	@Override
	public String insert() {
		if(cqlSession.execute("INSERT INTO people.person " +
				"(id, firstname, lastname, email, country, birthday) " +
				"VALUES (10, 'Bruce', 'Wayne', 'boss@wayneenterprises.com', 'Unite States of America', '1939-05-27');").wasApplied())
			return "insert successful, ";
		else
			return "insert failure, ";
	}

	@Override
	public String read() {
		if(cqlSession.execute("SELECT * FROM people.person;").wasApplied())
			return "read success, ";
		else
			return "read failure, ";
	}

	@Override
	public String makeCRUDCalls() throws SQLException {
		String toBeReturned = "create: "+ create() +", insert: "+insert()+", read: "+read()+ ", update: "+update()+", delete: "+delete();
		//conn.close();
		return toBeReturned;
	}
}

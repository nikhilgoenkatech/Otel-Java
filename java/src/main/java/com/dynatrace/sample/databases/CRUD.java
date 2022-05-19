package com.dynatrace.sample.databases;

import java.net.URISyntaxException;
import java.sql.SQLException;

public interface CRUD {
	String delete() throws SQLException, URISyntaxException;
	String create() throws SQLException, URISyntaxException;
	String update() throws SQLException, URISyntaxException;
	String insert() throws SQLException, URISyntaxException;
	String read() throws SQLException, URISyntaxException;
}


package com.dynatrace.sample.databases;

import java.net.UnknownHostException;

public class DbCrud {
	private static final String database = System.getenv("DATABASE").toLowerCase();
	public Database getDatabase() {
		try {
			switch (database) {
				case "mysql":
				case "postgres":
					return new SQL(database);
				case "redis":
					String driver = System.getenv("DB_DRIVER");
					return new RedisDB(driver);
				case "elasticsearch":
					return new ElasticSearch();
				case "cassandra":
					return new Cassandra();
				case "graphql":
					return new GraphQL();
				default:
					return null;
			}
		} catch(Exception ignore) {
			//LUL
			return new SQL("WHO'S YOUR DADDY");
		}
	}
}

package com.dynatrace.sample.databases;

import java.sql.*;
//class for postgresql and mysql
public class SQL extends Database implements CRUD{
	static Connection conn = null;
	static PreparedStatement preparedStatement = null;
	public SQL(String db) {
		super();
		if (db.equalsIgnoreCase("mysql")) {
			setDbName("my-db");
			setUrl("mysql");
			setPort(3306);
			setPassword("my-secret-pw");
			setUserName("my-user");
			setDriver("com.mysql.cj.jdbc.Driver");
		} else {
			setDbName(db);
			setUrl("postgresql");
			setPassword(db);
			setUserName(db);
			setPort(5432);
			setDriver("org.postgresql.Driver");
		}
		makeConnection(getDriver());
	}

	public void makeConnection(String driver) {
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		try {
			conn = DriverManager.getConnection("jdbc:"+getUrl()+"://"+getUrl()+":"+getPort()+"/"+getDbName(), getUserName(), getPassword());
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String delete() throws SQLException {
		preparedStatement = conn.prepareStatement("DELETE FROM person WHERE id=1");
		if(preparedStatement.executeUpdate() > 0)
			return "success on delete";
		else
			return "failure on delete";
	}

	@Override
	public String create() throws SQLException {
		if(isCreateCalled())
			return "create not called again";
		setCreateCalledTrue();
		String help = (this.getDbName().equalsIgnoreCase("postgres") ? "SERIAL" : "INT AUTO_INCREMENT");
		String statement = "CREATE TABLE IF NOT EXISTS person(id "+help+" PRIMARY KEY, " +
					"firstname VARCHAR(40) not null, " +
					"lastname VARCHAR(40) not null, " +
					"email VARCHAR(40) not null, " +
					"country VARCHAR(40) not null, " +
					"birthday VARCHAR(40) not null);";
		preparedStatement = conn.prepareStatement(statement);
		if(preparedStatement.execute())
			return "success on create";
		else
			return "failure on create";
	}

	@Override
	public String update() throws SQLException {
		preparedStatement = conn.prepareStatement("UPDATE person SET lastname='Banner' WHERE id=1");
		if(preparedStatement.executeUpdate() > 0)
			return "success on update";
		else
			return "failure on update";
	}

	@Override
	public String insert() throws SQLException {
		preparedStatement = conn.prepareStatement("INSERT INTO person (id, firstname, lastname, email, country, birthday) " +
				"VALUES ('1', 'Bruce', 'Wayne', 'boss@wayneenterprises.com', 'United States of America', '1939-05-27')");
		if(preparedStatement.executeUpdate() > 0)
			return "success on insert";
		else
			return "failure on insert";
	}

	@Override
	public String read() throws SQLException {
		preparedStatement = conn.prepareStatement("SELECT * FROM person");
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			return resultSet.getString(1);
		else
			return "nothing to be read";
	}

	@Override
	public String makeCRUDCalls() throws SQLException {
		String toBeReturned = "create: "+ create() +", insert: "+insert()+", read: "+read()+ ", update: "+update()+", delete: "+delete();
		//conn.close();
		return toBeReturned;
	}
}

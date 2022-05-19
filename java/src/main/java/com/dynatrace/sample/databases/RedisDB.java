package com.dynatrace.sample.databases;

import java.sql.SQLException;

public class RedisDB extends Database implements CRUD{
	DriverImplRedis driverImplRedis;
	public RedisDB(String driver) {
		super();
		setDriver(driver);
		driverImplRedis = new DriverImplRedis(driver);
	}
	@Override
	public String delete() throws SQLException {
		return driverImplRedis.delete();
	}

	@Override
	public String create() throws SQLException {
		return driverImplRedis.create();
	}

	@Override
	public String update() throws SQLException {
		return driverImplRedis.update();
	}
	@Override
	public String insert() throws SQLException {
		return driverImplRedis.insert();
	}
	@Override
	public String read() throws SQLException {
		return driverImplRedis.read();
	}

	@Override
	public String makeCRUDCalls() throws SQLException {
		String toBeReturned = "create: "+ create() +", insert: "+insert()+", read: "+read()+ ", update: "+update()+", delete: "+delete();
		//conn.close();
		return toBeReturned;
	}
}

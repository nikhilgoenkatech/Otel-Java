package com.dynatrace.sample.databases;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.async.RedisKeyAsyncCommands;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;

public class DriverImplRedis implements CRUD{
	private final String driver;
	private Jedis jedis;
	private RedissonClient redissonClient;
	private RedisStringAsyncCommands<String, String> asyncCommands;
	private RedisKeyAsyncCommands<String, String> keyAsyncCommands;
	private boolean wasCreateCalled;
	//TODO how to restart redissonClient without newly initiating
	public DriverImplRedis(String driver) {
		this.driver = driver;
		wasCreateCalled = false;
		int port = 6379;
		if(this.driver.equalsIgnoreCase("lettuce")) {
			RedisClient redisClient = RedisClient.create(RedisURI.create("redis://redis:" + port));
			asyncCommands = redisClient.connect().async();
			keyAsyncCommands = redisClient.connect().async();
		} else if (this.driver.equalsIgnoreCase("redisson")) {
			setRedissonClient();
		} else { //if empty or jedis -> if new drivers, care for if else!
			this.jedis = new Jedis("redis", port);
		}
	}
	private String getDriver() {
		return this.driver;
	}

	private void setWasCreateCalled() {
		this.wasCreateCalled = true;
	}

	private boolean getWasCreateCalled() {
		return this.wasCreateCalled;
	}

	private void setRedissonClient() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://redis:"+6379);
		RedisClient redisClient = RedisClient.create(RedisURI.create("redis://redis:" + 6379));
		redissonClient = Redisson.create(config);
		asyncCommands = redisClient.connect().async();
		keyAsyncCommands = redisClient.connect().async();
	}

	@Override
	public String delete() throws SQLException {
		if(getDriver().equalsIgnoreCase("lettuce")) {
			return "delete: " + keyAsyncCommands.del("person1").toString();
		}else if (getDriver().equalsIgnoreCase("redisson")) {
			String toBeReturned = (redissonClient.getBucket("person1").delete()) ? "delete successful, " : "failure on delete";
			redissonClient = null;
			asyncCommands=null;
			keyAsyncCommands = null;
			return toBeReturned;
		}else {//if empty or jedis -> if new drivers, care for if else!
			return "delete: " + jedis.del("person1");
		}
	}

	@Override
	public String create() throws SQLException {
		if(getDriver().equalsIgnoreCase("lettuce")) {
			return "create: "+asyncCommands.set("person1", "Bruce Wayne").toString();
		} else if (getDriver().equalsIgnoreCase("redisson")) {
			setRedissonClient();
			redissonClient.getBucket("person1").set("Bruce Wayne");
			return (redissonClient.getBucket("person1").get().toString().equalsIgnoreCase("bruce wayne")) ? "create successful," : "failure on create";
		} else { //if empty or jedis -> if new drivers, care for if else!
			if(getWasCreateCalled()) {
				setWasCreateCalled();
				return "was already created - skipped; ";
			}
			return "create: "+jedis.set("person1", "Bruce Wayne");
		}

	}

	@Override
	public String update() throws SQLException {
		if(getDriver().equalsIgnoreCase("lettuce")) {
			return "update: "+asyncCommands.set("person1", "Bruce Banner").toString();
		} else if (getDriver().equalsIgnoreCase("redisson")) {
			redissonClient.getBucket("person1").set("Bruce Banner");
			return (redissonClient.getBucket("person1").get().toString().equalsIgnoreCase("bruce banner")) ? "update successful," : "failure on update";
		} else { //if empty or jedis -> if new drivers, care for if else!
			return "update: "+jedis.set("person1", "Bruce Banner");
		}
	}

	@Override
	public String insert() throws SQLException {
		if(getDriver().equalsIgnoreCase("lettuce")) {
			return "insert: "+asyncCommands.set("person1", "Bruce Wayne").toString();
		} else if (getDriver().equalsIgnoreCase("redisson")) {
			redissonClient.getBucket("person1").set("Bruce Wayne");
			return (redissonClient.getBucket("person1").get().toString().equalsIgnoreCase("bruce wayne")) ? "insert successful," : "failure on insert";
		} else { //if empty or jedis -> if new drivers, care for if else!
			return "create: "+jedis.set("person1", "Bruce Wayne");
		}
	}

	@Override
	public String read() throws SQLException {
		if(getDriver().equalsIgnoreCase("lettuce"))
			return asyncCommands.get("person1").toString();
		else if (getDriver().equalsIgnoreCase("redisson"))
			return (redissonClient.getBucket("person1").get().toString().equalsIgnoreCase("bruce wayne")) ? "read successful," : "read on create";
		else  //if empty or jedis -> if new drivers, care for if else!
			return "read: "+jedis.get("person1");
	}
}

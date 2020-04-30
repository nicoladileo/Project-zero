package io.github.nicoladileo.dao;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DataSourceFactory {
	private Logger logger = LoggerFactory.getLogger(DataSourceFactory.class);
	private static DataSourceFactory singleton = new DataSourceFactory();
	private static HikariDataSource dataSource = null;
	private DataSourceFactory() {}
	
	public static DataSourceFactory getInstance() {
		return singleton;
	}
	
	public HikariDataSource getHikariDataSource(Properties properties) throws IOException  {
		if (dataSource == null)
			initHikari(properties);
		return dataSource;
	}
	
	private void initHikari(Properties properties) throws IOException  {
		logger.info("Inizio initHikari");
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(properties.getProperty("dataSource.jdbcUrl"));
		config.setUsername(properties.getProperty("dataSource.user"));
		config.setPassword(properties.getProperty("dataSource.password"));
		config.setDriverClassName(properties.getProperty("dataSource.driver"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setMaxLifetime(Integer.parseInt(properties.getProperty("dataSource.maxLifeTime")));
		config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("dataSource.maxPoolSize")));
		config.setConnectionTestQuery("SELECT 1");
		DataSourceFactory.dataSource = new HikariDataSource(config);
		logger.info("Fine initHikari");
	}
}
package com.zyme.config.dbconfig;



import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@Configuration
public class DataSourceConfig{

	private Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);
	
	@Value("${jdbc.driver.class}")
	private String DB_DRIVER;

	@Value("${datasource.url}")
	private String url;
	
	@Value("${datasource.username}")
	private String username;

	@Value("${datasource.jdbcIvparam}")
	private String IvParam;
	
	@Value("${datasource.jdbcPassphrase}")
	private String passphrase;
	
	@Value("${datasource.jdbcEncpassword}")
	private String encpassword;
	
	@Value("${spring.datasource.dbcp2.default-transaction-isolation}")
	private int defaultTransactionIsolation;

	@Value("${spring.datasource.dbcp2.max-wait-millis}")
	private long maxWait;

	@Value("${spring.datasource.dbcp2.validation-query}")
	private String validationQuery;

	@Value("${spring.datasource.dbcp2.test-on-return}")
	private boolean testOnReturn;

	@Value("${spring.datasource.dbcp2.remove-abandoned-on-maintenance}")
	private boolean removeAbandonedOnMaintenance;

	@Value("${spring.datasource.dbcp2.remove-abandoned-timeout}")
	private int removeAbandonedTimeout;

	@Value("${spring.datasource.dbcp2.test-while-idle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.dbcp2.time-between-eviction-runs-millis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.dbcp2.test-on-create}")
	private boolean testOnCreate;
	
	
	@Bean
    public DataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		
		 EncryptDataSource decrypt = new EncryptDataSource();
	      	decrypt.setIVParam(IvParam);
	      	decrypt.setPassPhrase(passphrase);
	      	
	    dataSource.setPassword(decrypt.setEncPassword(encpassword));
	    dataSource.setMaxWaitMillis(maxWait);
	    dataSource.setValidationQuery(validationQuery);
	    dataSource.setTestOnReturn(testOnReturn);
	    dataSource.setTestOnCreate(testOnCreate);
	    dataSource.setTestWhileIdle(testWhileIdle);
	    dataSource.setRemoveAbandonedOnMaintenance(removeAbandonedOnMaintenance);
	    dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
	    dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	    dataSource.setDefaultTransactionIsolation(defaultTransactionIsolation);
	    
		LOGGER.info("\nInitialSize->" + dataSource.getInitialSize() + "|  MaxTotal->" + dataSource.getMaxTotal()
				+ "|  MaxIdle->" + dataSource.getMaxIdle() + "\nMaxWaitMillis=>" + dataSource.getMaxWaitMillis()
				+ "|  MinIdle=>" + dataSource.getMinIdle() + "|  ValidationQuery=>" + dataSource.getValidationQuery()
				+ "|  TestOnBorrow=>" + dataSource.getTestOnBorrow() + "\nTestOnReturn=>"
				+ dataSource.getTestOnReturn() + "|  TestOnCreate=>" + dataSource.getTestOnCreate() + "| TestWhileIdle=>"
				+ dataSource.getTestWhileIdle() + "|  RemoveAbandonedOnMaintenance->"
				+ dataSource.getRemoveAbandonedOnMaintenance() + "\nRemoveAbandonedTimeout->"
				+ dataSource.getRemoveAbandonedTimeout() + "|  TimeBetweenEvictionRunsMillis->"
				+ dataSource.getTimeBetweenEvictionRunsMillis() + "|  DefaultTransactionIsolation=>"
				+ dataSource.getDefaultTransactionIsolation());

	    return dataSource;
	
    }

	 @Bean
	 public DataSourceTransactionManager transactionManager() {
	        return new DataSourceTransactionManager(getDataSource());
	 }
}

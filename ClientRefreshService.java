package com.zyme.config;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zyme.config.persistence.dao.api.ConfigDAO;
import com.zyme.config.persistence.model.entity.CustomerMaster;

@Component
@Order(101)
public class ClientRefreshService implements InitializingBean {

	private Logger LOGGER = LoggerFactory.getLogger(ClientRefreshService.class);

	@Autowired
	private ConfigDAO configDAO;

	@Override
	public void afterPropertiesSet() throws Exception {
		initializeClientMap();
	}

	public void initializeClientMap() {
		List<CustomerMaster> customerMasters= configDAO.getCustomerMaster(1);
		customerMasters.forEach(c-> AppStartUpConstants.configuredClients.add(c.getClientName()));
		LOGGER.info("Client master configuredClients:::{}",AppStartUpConstants.configuredClients);
	}

}

package com.trendyol.tr.shoppingcart.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.service.util.DozerMapperUtil;

@Singleton
@Startup
public class StartupService extends AbstractJpaService {
	
	private static final Logger logger = LoggerFactory.getLogger(StartupService.class);

	@PostConstruct
	public void init() {
		logger.debug("Initializing {}", this.getClass().getName());
		
		try {
			DozerMapperUtil.init();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}

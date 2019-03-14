package com.seed.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.entity.Log;
import com.seed.repository.LogRepo;
import com.seed.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	private static final Logger LOG= LoggerFactory.getLogger(LogServiceImpl.class);
	
	@Autowired
	private LogRepo logRepo;
	
	@Override
	public List<Log> showLogs() {
		// TODO Auto-generated method stub
		LOG.info("display log table");
		return logRepo.findAll();
	}

}

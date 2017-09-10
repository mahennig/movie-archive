/**
 * MonitoringManager.java
 *
 * Created on 17.08.2017
 *
 * Copyright 2017 Volkswagen AG, All Rights Reserved.
 *
 */

package de.hennig.moviearchive.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.hennig.moviearchive.repositories.QueryRepository;

/**
 * @author mhennig
 *
 */

@Component
@EnableScheduling
public class MonitoringManager {

	static Logger logger = Logger.getLogger(MonitoringManager.class);

	@Autowired
	Environment environment;

	@Autowired
	Session session;

	@Autowired
	QueryRepository queryRepository;

	String port;
	String remoteAddress;

	public String printServerAndPort() {
		String localHostAddress = "";
		String localHostName = "";
		String remoteHostAddress = "";
		String remoteHostName = "";
		try {
			localHostAddress = InetAddress.getLocalHost().getHostAddress();
			localHostName = InetAddress.getLocalHost().getHostName();
			remoteHostAddress = InetAddress.getLoopbackAddress().getHostAddress();
			remoteHostName = InetAddress.getLoopbackAddress().getHostName();
		} catch (UnknownHostException e) {
			logger.error("Unable to fetch local server address.");
			remoteAddress = "";
		}
		port = environment.getProperty("local.server.port");

		return String.format("Remote server %s:%s (%s:%s) is running on local address %s (%s)", remoteHostName, port,
				remoteHostAddress, port, localHostAddress, localHostName);
	}

	// Log neo4j status every 10 minutes
	@Scheduled(fixedDelay = 600000)
	public void getTransactionPool() {
		Integer nodeCount = queryRepository.getNodeCount();
		Integer relationshipCount = queryRepository.getRelationshipCount();
		logger.info(String.format("Currently %s nodes and %s relationships persisted in neo4j.", nodeCount,
				relationshipCount));

		logger.info(printServerAndPort());
	}
}

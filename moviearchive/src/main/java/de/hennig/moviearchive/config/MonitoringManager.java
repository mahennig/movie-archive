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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author mhennig
 *
 */

@Component
@EnableScheduling
@Slf4j
public class MonitoringManager {

	@Autowired
	Environment environment;


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
			log.error("Unable to fetch local server address.");
			remoteAddress = "";
		}
		port = environment.getProperty("local.server.port");

		return String.format("Remote server %s:%s (%s:%s) is running on local address %s (%s)", remoteHostName, port,
				remoteHostAddress, port, localHostAddress, localHostName);
	}

	@Scheduled(fixedDelay = 600000)
	public void getTransactionPool() {
		log.info(printServerAndPort());
	}
}

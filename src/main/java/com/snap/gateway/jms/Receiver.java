package com.snap.gateway.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.snap.gateway.service.DispatcherMessageService;

@Component
public class Receiver {
	private static final Logger log = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
	private DispatcherMessageService dispatcher;
	
	@JmsListener(destination = "${OrderRequest.Topic}", containerFactory = "connectionFactory")
    public void receiveQueue(String request) {
		dispatcher.processRequest(request);
    }
	
	@JmsListener(destination = "${Quote.Topic}", containerFactory = "connectionFactory")
    public void receiveTopic(String request) {
		//log.info(request);
		//dispatcher.processRequest(request);

		Runnable playerOne = new DispatcherMessageService();
		((DispatcherMessageService) playerOne).msg = request;
		new Thread(playerOne).start();
//		dispatcher.processQuote(request);
    }
}

package com.snap.gateway.jms;

import com.google.gson.Gson;
import com.snap.gateway.ShareObjectQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class Receiver {
	private static final Logger log = LoggerFactory.getLogger(Receiver.class);

//	@Autowired
//	BalanceRepository balanceRepository;

    @JmsListener(destination = "${Start.Topic}", containerFactory = "connectionFactory")
    public void receiveOrders(String request) {
    }

}

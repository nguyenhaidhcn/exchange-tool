package com.tool.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	private static final Logger log = LoggerFactory.getLogger(Receiver.class);

//	@Autowired
//	BalanceRepository balanceRepository;

    @JmsListener(destination = "${Start.Topic}", containerFactory = "connectionFactory")
    public void receiveOrders(String request) {
    }

}

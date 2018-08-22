package com.tool.jms;

import com.tool.ShareObjectQuote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	private static final Logger log = LoggerFactory.getLogger(Receiver.class);

	@Autowired
	Sender sender;

    @JmsListener(destination = "${ToolChart.Topic}", containerFactory = "connectionFactory")
    public void receiveOrders(String request) {
        ShareObjectQuote.sender = sender;
    }

}

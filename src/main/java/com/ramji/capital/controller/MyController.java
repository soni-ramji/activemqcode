/**
 * 
 */
package com.ramji.capital.controller;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HP
 *
 */

@RestController
public class MyController {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@GetMapping("/")
	public String sayHello(){
		
		jmsTemplate.send("primary", new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage("send");
			}
		});
		return "working";
	}
}

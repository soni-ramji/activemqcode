/**
 * 
 */
package com.ramji.capital.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import com.ramji.capital.listener.SampleListener;

/**
 * @author HP
 *
 */
@Configuration
public class JMSConfig {

	@Bean
	public CachingConnectionFactory getConnectionFactory(){
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		UserCredentialsConnectionFactoryAdapter ucf = new UserCredentialsConnectionFactoryAdapter();
		ucf.setUsername("admin");
		ucf.setPassword("admin");
		ucf.setTargetConnectionFactory(cf);
		cachingConnectionFactory.setTargetConnectionFactory(ucf);
		return cachingConnectionFactory;
	}

	/*@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}*/

	
	 @Bean
	  public MessageListenerContainer listenerContainer() {
	      DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
	      container.setConnectionFactory(getConnectionFactory());
	      container.setDestinationName("primary");
	      container.setMessageListener(new SampleListener());
	      return container;
	  }
	 
	@Bean
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setPubSubDomain(false);
		jmsTemplate.setConnectionFactory(getConnectionFactory());
		//jmsTemplate.setDefaultDestinationName("primary");
		
		return jmsTemplate;
	}

}

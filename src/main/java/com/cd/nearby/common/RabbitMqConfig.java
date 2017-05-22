/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cd.nearby.common;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("#{ @environment['rabbitmq.server'] ?: 'localhost' }")
	private String server;

	@Value("#{ @environment['rabbitmq.port'] ?: 5672 }")
	private int port;

	@Value("#{ @environment['rabbitmq.username'] ?: 'guest' }")
	private String username;

	@Value("#{ @environment['rabbitmq.password'] ?: 'guest' }")
	private String password;

	private static final String SIMPLE_MESSAGE_QUEUE = "USER_SERVICE";

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.server, this.port);
		connectionFactory.setUsername(this.username);
		connectionFactory.setPassword(this.password);
		connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
		return connectionFactory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		// template.setMessageConverter(jsonMessageConverter());
		return template;
	}

}

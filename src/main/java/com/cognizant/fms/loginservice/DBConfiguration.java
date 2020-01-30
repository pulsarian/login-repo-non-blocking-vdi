package com.cognizant.fms.loginservice;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.spi.ConnectionFactory;

public class DBConfiguration extends AbstractR2dbcConfiguration {

	@Override
	@Bean
	public ConnectionFactory connectionFactory() {
            String url ="mysql://admin:admin@localhost:3306/bookdb";
            return new JasyncConnectionFactory(new MySQLConnectionFactory(URLParser.INSTANCE.parseOrDie(url, StandardCharsets.UTF_8)));
}
	}

}

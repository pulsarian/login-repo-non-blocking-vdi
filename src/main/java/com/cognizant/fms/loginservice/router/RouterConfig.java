package com.cognizant.fms.loginservice.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cognizant.fms.loginservice.handler.LoginHandler;
import com.cognizant.fms.loginservice.handler.ResourceHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

//@Configuration
public class RouterConfig {

	@Bean
	public RouterFunction<ServerResponse> routes(LoginHandler loginHandler, ResourceHandler resourceHandler) {
		
		return RouterFunctions
				.route(POST("/login").and(accept(MediaType.APPLICATION_JSON)), loginHandler::doLogin)
				.andRoute(GET("/resource/user").and(accept(MediaType.APPLICATION_JSON)), resourceHandler::getUserResource)
				.andRoute(GET("/resource/admin").and(accept(MediaType.APPLICATION_JSON)), resourceHandler::getAdminResource);
				
	}
	
}

package com.cognizant.fms.loginservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.cognizant.fms.loginservice.model.Message;

import reactor.core.publisher.Mono;

public class ResourceHandler {
	
	public Mono<ServerResponse> getUserResource(ServerRequest serverRequest) {
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						ReactiveSecurityContextHolder.getContext().map(context -> { if(context.getAuthentication().getAuthorities().contains("ROLE_USER")) {
							return ResponseEntity.ok(new Message("Content for user"));
						} else {
							return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
						}})
						.defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()), Message.class);
	}
	
	public Mono<ServerResponse> getAdminResource(ServerRequest serverRequest) {
		
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						ReactiveSecurityContextHolder.getContext().map(context -> { if(context.getAuthentication().getAuthorities().contains("ROLE_ADMIN")) {
							return ResponseEntity.ok(new Message("Content for admin"));
						} else {
							return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
						}})
						.defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()), Message.class);
	}
	
}

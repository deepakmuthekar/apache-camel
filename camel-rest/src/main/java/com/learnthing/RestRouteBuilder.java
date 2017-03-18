package com.learnthing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.component("servlet")
			.contextPath("/camel")
			.bindingMode(RestBindingMode.auto);
		
		rest("/rest")
			.get("/ping")
				.consumes("application/json")
				.produces("application/json")
			.to("direct:getPing")
			
			.post("/bye").to("mock:update");

		from("direct:getPing")
        .log("Ping request received!")
        .to("bean:messageBean?method=ping");

	}

}

package com.learnthing;


import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(Exception.class)
        .handled(true)
       /* setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setBody().constant(exceptionMessage());*/
        .process(new Processor() {
		 @Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("Inside the processor......");
				exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
	            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
	            List<String> errors= Arrays.asList("SEcurity ID required");
	            APIError error = new APIError("400", "INVALID_INPUT", errors); 
	         exchange.getOut().setBody(error);
			}
		});
		
		restConfiguration()
			.component("servlet")
			.contextPath("/camel")
			.bindingMode(RestBindingMode.auto)
			.skipBindingOnErrorCode(false);
		
		rest("/rest")
			.get("/ping")
				.consumes("application/json")
				.produces("application/json")
			.route().from("direct:myendpoint")
			.to("direct:getPing");
		
		rest("/rest")
			.post("/bye").to("mock:update");

		from("direct:getPing")
        	.log("Ping request received!")
        .to("bean:messageBean?method=ping");
        
        

	}

}

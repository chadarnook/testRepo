package com.accenture.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ServiceRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration().component("servlet").host("localhost").contextPath("rest-dsl-camel-example/rest").port(8080);
		rest("/test")
			.get("/route")
			.to("direct:bye");
			
			from("direct:bye")
			.process(new Processor() {

				public void process(Exchange arg0) throws Exception {
					System.out.println("processing rest service...");
					for(String key : arg0.getIn().getHeaders().keySet()) {
						System.out.println(key + " ------ " + arg0.getIn().getHeaders().get(key));
					}
				}
				
			});
			System.out.println("routes loaded");
	}

}

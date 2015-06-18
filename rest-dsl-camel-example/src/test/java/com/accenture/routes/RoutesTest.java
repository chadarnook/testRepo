package com.accenture.routes;

import javax.ws.rs.core.Response;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.cxf.jaxrs.client.WebClient;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoutesTest extends CamelTestSupport{
	
	private Server server;
	
	@Before
	public void startServer() throws Exception {
		server = new Server(8080);
		server.setStopAtShutdown(true);
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/rest-dsl-camel-example");
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setClassLoader(getClass().getClassLoader());
		server.setHandler(webAppContext);
		server.start();
	}
	
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

//	@Override
//	protected AbstractApplicationContext createApplicationContext() {
//		return new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
//	}
	
//	@Override
//	protected RouteBuilder createRouteBuilder() {
//		return new RouteBuilder() {
//
//			@Override
//			public void configure() throws Exception {
//				restConfiguration().component("restlet").host("localhost").contextPath("rest-dsl-camel-example/rest").port(8080);
//				rest("/test")
//					.get("/route")
//					.to("direct:bye");
//					
//					from("direct:bye")
//					.process(new Processor() {
//
//						public void process(Exchange arg0) throws Exception {
//							System.out.println("processing rest service...");
//						}
//					});
//					System.out.println("routes loaded");
//			}
//			
//		};
//	}
	@Test
	public void testRoutes() throws InterruptedException {
//		WebClient client = WebClient.create("http://localhost:8080/test/route");
		WebClient client = WebClient.create("http://localhost:8080/rest-dsl-camel-example/rest/test/route").header("hotelId", 1);
		Response res = client.get();
		Assert.assertEquals(200, res.getStatus());
//		String res = template.requestBody("http://localhost:8080/test/route", null, String.class);
//		Assert.assertEquals("200", res);
	}
	

}

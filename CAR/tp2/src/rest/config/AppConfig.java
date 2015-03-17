package rest.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import rest.rs.FTPLoginService;
import rest.rs.FTPRestService;
import rest.rs.JaxRsApiApplication;
import rest.services.FTPService;

@Configuration
public class AppConfig {
	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}
	
	private String applicationPath;

	@Bean
	@DependsOn("cxf")
	public Server jaxRsServer() throws IOException {
		JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance()
				.createEndpoint(jaxRsApiApplication(),
						JAXRSServerFactoryBean.class);

		List<Object> serviceBeans = new ArrayList<Object>();
		serviceBeans.add(ftpRestService());
		serviceBeans.add(new FTPLoginService());

		factory.setServiceBeans(serviceBeans);
		factory.setAddress("/" + factory.getAddress());
		factory.setProviders(Arrays.<Object> asList(jsonProvider()));
		return factory.create();
	}

	@Bean
	public JaxRsApiApplication jaxRsApiApplication() {
		final JaxRsApiApplication application = new JaxRsApiApplication();
		applicationPath = application.getClass().getAnnotation(ApplicationPath.class).value();
		return application;
	}
	
	@Bean
	public FTPService ftpService() throws IOException {
		return new FTPService();
	}
	
	@Bean
	public FTPRestService ftpRestService() throws IOException {
		return new FTPRestService(ftpService(), applicationPath);
	}

	@Bean
	public JacksonJsonProvider jsonProvider() {
		return new JacksonJsonProvider();
	}
}

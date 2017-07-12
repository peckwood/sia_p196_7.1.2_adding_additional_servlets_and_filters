package spittr.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import spittr.web.MyFilter;
import spittr.web.MyServlet;

public class SpittrWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext webApplicationContext = getContext();
		// ContextLoaderListener is added to ServletContext –
		// the purpose of this is to 'glue' WebApplicationContext
		// to the lifecycle of ServletContext
		servletContext.addListener(new ContextLoaderListener(webApplicationContext));
		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("DispatcherServlet",
				new DispatcherServlet(webApplicationContext));
		dispatcherServlet.setLoadOnStartup(1);
		//original project used "/*" and didn't work for "/" requests
		dispatcherServlet.addMapping("/");
		Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
		myServlet.addMapping("/myservlet");
		
		javax.servlet.FilterRegistration.Dynamic myFilter = 
				servletContext.addFilter("myFilter",  MyFilter.class);
		
		//myFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), false, "myServlet");
		myFilter.addMappingForUrlPatterns(null, false, "/myservlet");
	}

	private AnnotationConfigWebApplicationContext getContext() {
		/*
		 * AnnotationConfigWebApplicationContext is created. It's
		 * WebApplicationContext implementation that looks for Spring
		 * configuration in classes annotated with @Configuration annotation.
		 * setConfigLocation() method gets hint in which package(s) to look for
		 * them.
		 */
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("spittr.config");
		return context;
	}

}

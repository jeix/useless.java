package s.dic;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class FilterConfigurator
 *
 */
@WebListener
public class FilterConfigurator implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event)  { 
         
    	ServletContext context = event.getServletContext();
    	
    	FilterRegistration.Dynamic registration = context.addFilter("authenticationFilter", new AuthenticationFilter());
    	registration.setAsyncSupported(true);
    	registration.addMappingForUrlPatterns(null, false,
    			"/find/*",
				"/read/*",
				"/edit/*",
				"/save/*",
				"/signout",
				"/upload",
				"/download");
    }

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
	
}

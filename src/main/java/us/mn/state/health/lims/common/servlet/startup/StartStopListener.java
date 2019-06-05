package us.mn.state.health.lims.common.servlet.startup;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import us.mn.state.health.lims.plugin.PluginLoader;
import us.mn.state.health.lims.scheduler.IndependentThreadStarter;
import us.mn.state.health.lims.scheduler.LateStartScheduler;

@WebListener
public final class StartStopListener implements ServletContextListener {

	private ServletContext context = null;

	@Autowired
	private IndependentThreadStarter threadStarter;
	@Autowired
	private LateStartScheduler lateStartScheduler;

	public StartStopListener() {

	}

	// This method is invoked when the Web Application
	// has been removed and is no longer able to accept
	// requests

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		context = null;
		if (threadStarter != null) {
			threadStarter.stopThreads();
		}
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		try {
			lateStartScheduler.shutdown();
		} catch (Exception e) {
			if (e instanceof SchedulerException) {
				e.printStackTrace();
			}
			// rethrow as a runtime exception
			throw new IllegalStateException(e);
		}

		System.out.println("\nShutting down context\n");
	}

	// This method is invoked when the Web Application
	// is ready to service requests

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();

		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		try {
			lateStartScheduler.checkAndStartScheduler();
		} catch (Exception e) {
			// rethrow as a runtime exception
			throw new IllegalStateException(e);
		}

		System.out.println("Scheduler started");

		threadStarter.startThreads();

		System.out.println("Threads started");
		new PluginLoader(event).load();
		System.out.println("Plugins loaded");
	}
}

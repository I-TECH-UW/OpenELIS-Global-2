package us.mn.state.health.lims.common.servlet.startup;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;

import us.mn.state.health.lims.plugin.PluginLoader;
import us.mn.state.health.lims.scheduler.IndependentThreadStarter;
import us.mn.state.health.lims.scheduler.LateStartScheduler;

public final class StartStopListener implements ServletContextListener {

	private ServletContext context = null;
	private LateStartScheduler scheduler;
	private IndependentThreadStarter threadStarter;

	public StartStopListener() {

	}

	// This method is invoked when the Web Application
	// has been removed and is no longer able to accept
	// requests

	public void contextDestroyed(ServletContextEvent event) {

		this.context = null;
		if (threadStarter != null) {
			threadStarter.stopThreads();
		}

		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

		System.out.println("\nShutting down context\n");
	}

	// This method is invoked when the Web Application
	// is ready to service requests

	public void contextInitialized(ServletContextEvent event) {
		this.context = event.getServletContext();

		scheduler = new LateStartScheduler();
		scheduler.checkAndStartScheduler();

		System.out.println("Scheduler started");

		threadStarter = new IndependentThreadStarter();
		threadStarter.startThreads();

		System.out.println("Threads started");
		new PluginLoader(event).load();
		System.out.println("Plugins loaded");
	}
}

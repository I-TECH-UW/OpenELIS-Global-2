package us.mn.state.health.lims.scheduler.independentthreads;

public interface IMalariaResultExporter {

	void setSleepInMins(long period);

	void start();

}

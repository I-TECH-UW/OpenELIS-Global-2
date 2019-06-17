package us.mn.state.health.lims.scheduler.independentthreads;

public interface IResultExporter {

	void setSleepInMin(long period);

	void start();

	void stopExports();

}

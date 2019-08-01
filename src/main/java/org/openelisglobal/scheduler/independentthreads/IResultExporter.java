package org.openelisglobal.scheduler.independentthreads;

public interface IResultExporter {

    void setSleepInMin(long period);

    void start();

    void stopExports();

}

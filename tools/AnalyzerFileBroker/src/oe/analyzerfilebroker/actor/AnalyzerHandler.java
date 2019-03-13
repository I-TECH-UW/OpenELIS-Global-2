package oe.analyzerfilebroker.actor;

import oe.analyzerfilebroker.StringLocalization;
import oe.analyzerfilebroker.config.AnalyzerConfig;

import java.io.File;
import java.util.List;

public class AnalyzerHandler extends Thread {
    private final static String timeFileNamePostfix = "_DO_NOT_DELETE";
    public final static String[] specialQueueFiles = {timeFileNamePostfix, "ping"};
    private final long sleepPeriod;
    private final String name;
    private final String sourceDirName;
    private final File sourceDir;
    private final String prefix;
    private final String timeFileName;
    private volatile boolean run = true;

    public AnalyzerHandler(AnalyzerConfig config){
        sleepPeriod = config.getPeriod() * 1000L;
        name = config.getName();
        sourceDirName = config.getSourceDir();
        sourceDir = new File(sourceDirName);
        prefix = config.getPrefix() == null ? "" : config.getPrefix() + "_";
        timeFileName = config.getName() + timeFileNamePostfix;

    }

    public boolean validateSource(){
        return sourceDir.exists() && sourceDir.isDirectory() && sourceDir.canRead();
    }

    public String getAnalyzerName() {
        return name;
    }

    public String getSourceDirName() {
        return sourceDirName;
    }

    @Override
    public void run() {
        while( run ){
            synchronized (this) {
                File timestampFile = PathManager.getFileFromQueue( timeFileName );
                if(timestampFile == null) {
                    PathManager.createFileInQueue( timeFileName );
                }else{
                    boolean newFile = false;
                    long timestamp = timestampFile.lastModified();
                    List<File> analyzerFiles = PathManager.getFilesFromAnalyzer( sourceDirName );

                    for( File file: analyzerFiles){
                        if( file.lastModified() > timestamp){
                            PathManager.copyToQueue( file, prefix + file.getName());
                            newFile = true;
                        }
                    }

                    if( newFile) {
                        PathManager.touchFile(timestampFile);
                        PathManager.flushQueue(specialQueueFiles);
                    }
                }
            }
            try {
                Thread.sleep(sleepPeriod);
            } catch (InterruptedException e) {
                //left blank on purpose
             }
        }

        System.out.println(name + StringLocalization.instance().getStringForKey("analyzer.stopped"));
    }

    public void kill() {
        run = false;
        synchronized (this) {
            this.interrupt();
        }
    }
}

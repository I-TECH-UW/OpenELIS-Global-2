package oe.analyzerfilebroker.config;

public class AnalyzerConfig {

    private String name;
    private String sourceDir;
    private int period;
    private String prefix = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = (prefix == null || prefix.length() == 0) ? null : prefix;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }
}

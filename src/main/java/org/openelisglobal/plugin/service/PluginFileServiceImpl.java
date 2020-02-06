package org.openelisglobal.plugin.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.openelisglobal.common.dao.BaseDAO;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.plugin.dao.PluginFileDAO;
import org.openelisglobal.plugin.valueholder.PluginFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PluginFileServiceImpl extends BaseObjectServiceImpl<PluginFile, Long> implements PluginFileService {

    private static final String PLUGIN_ANALYZER = "/plugin" + File.separator;

    private PluginFileDAO pluginFileDAO;

    public PluginFileServiceImpl(PluginFileDAO pluginFileDAO) {
        super(PluginFile.class);
        this.pluginFileDAO = pluginFileDAO;
    }

    @Override
    protected BaseDAO<PluginFile, Long> getBaseObjectDAO() {
        return pluginFileDAO;
    }

    @Override
    public void savePluginsDirectory() {
        ClassLoader classLoader = getClass().getClassLoader();
        String pluginsDirPath;
        try {
            pluginsDirPath = URLDecoder.decode(classLoader.getResource(PLUGIN_ANALYZER).getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogEvent.logDebug(e);
            throw new LIMSRuntimeException(e);
        }
        File pluginDir = new File(pluginsDirPath);
        saveDirectory(pluginDir);
    }

    @Override
    public void saveFileAsPlugin(File file, boolean force) {
        Optional<PluginFile> dbPluginFile = this.getMatch("name", file.getName());
        if (force || dbPluginFile.isPresent()) {
            try {
                PluginFile pluginFile = dbPluginFile.orElse(new PluginFile());
                pluginFile.setName(file.getName());
                pluginFile.setJarFile(IOUtils.toByteArray(new FileInputStream(file)));
                this.save(pluginFile);
            } catch (IOException e) {
                LogEvent.logError(e);
            }
        } else {
            LogEvent.logError(this.getClass().getName(), "saveFileAsPlugin",
                    "could not save a plugin jar as a jar with the same name already exists in the database");
        }
    }

    private void saveDirectory(File pluginDir) {
        List<String> pluginList = new ArrayList<>();

        File[] files = pluginDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith("jar")) {
                    saveFileAsPlugin(file, false);
                    pluginList.add(file.getName());
                } else if (file.isDirectory()) {
                    LogEvent.logInfo(this.getClass().getName(), "method unkown",
                            "Checking plugin subfolder: " + file.getName());
                    saveDirectory(file);
                }
            }
        }
    }

}

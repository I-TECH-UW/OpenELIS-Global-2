package org.openelisglobal.plugin.service;

import java.io.File;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.plugin.valueholder.PluginFile;

public interface PluginFileService extends BaseObjectService<PluginFile, Long> {

    void savePluginsDirectory();

    void saveFileAsPlugin(File file, boolean force);

}

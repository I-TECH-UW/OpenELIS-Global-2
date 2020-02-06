package org.openelisglobal.plugin.dao;

import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.plugin.valueholder.PluginFile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PluginFileDAOImpl extends BaseDAOImpl<PluginFile, Long> implements PluginFileDAO {

    public PluginFileDAOImpl() {
        super(PluginFile.class);
    }

}

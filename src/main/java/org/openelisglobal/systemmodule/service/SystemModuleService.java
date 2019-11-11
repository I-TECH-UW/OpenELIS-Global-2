package org.openelisglobal.systemmodule.service;

import java.util.List;

import org.openelisglobal.common.service.BaseObjectService;
import org.openelisglobal.systemmodule.valueholder.SystemModule;

public interface SystemModuleService extends BaseObjectService<SystemModule, String> {

    void getData(SystemModule systemModule);

    Integer getTotalSystemModuleCount();

    List getPageOfSystemModules(int startingRecNo);



    List getAllSystemModules();



    SystemModule getSystemModuleByName(String name);

}

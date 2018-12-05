/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is OpenELIS code.
 *
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.common.services;

import org.hibernate.Transaction;

import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.role.dao.RoleDAO;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.systemmodule.dao.SystemModuleDAO;
import us.mn.state.health.lims.systemmodule.daoimpl.SystemModuleDAOImpl;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemusermodule.daoimpl.RoleModuleDAOImpl;
import us.mn.state.health.lims.systemusermodule.valueholder.RoleModule;

/**
 */
public class PluginPermissionService{
    private static final SystemModuleDAO moduleDAO = new SystemModuleDAOImpl();
    private static final RoleDAO roleDAO = new RoleDAOImpl();
    private static final RoleModuleDAOImpl roleModuleDAOImpl = new RoleModuleDAOImpl();

    public SystemModule getOrCreateSystemModule( String action, String description){
        return getOrCreateSystemModuleByName( action, description );
    }

    public SystemModule getOrCreateSystemModule( String action, String type, String description){
        return getOrCreateSystemModuleByName( action + ":" + type, description );
    }

    private SystemModule getOrCreateSystemModuleByName(String name, String description){
        SystemModule module = moduleDAO.getSystemModuleByName( name );

        if( module == null || module.getId() == null){
            module = new SystemModule();
            module.setSystemModuleName( name );
            module.setDescription( description );
            module.setHasAddFlag( "Y" );
            module.setHasDeleteFlag( "Y" );
            module.setHasSelectFlag( "Y" );
            module.setHasUpdateFlag( "Y" );
            module.setSysUserId( "1" );
        }
        return module;
    }

    public Role getSystemRole(String name){
        return roleDAO.getRoleByName( name );
    }

    public boolean bindRoleToModule( Role role, SystemModule module){
        if( role == null || module == null){
            return false;
        }
        Transaction tx = HibernateUtil.getSession().beginTransaction();

        try {
            if (role.getId() == null) {
                role.setActive( true );
                roleDAO.insertData( role );
            }else if( !role.isActive()){
                role.setActive( true );
                roleDAO.updateData( role );
            }

            if( module.getId() == null){
                moduleDAO.insertData( module );
            }

            RoleModule roleModule = roleModuleDAOImpl.getRoleModuleByRoleAndModuleId( role.getId(), module.getId() );

            if( roleModule.getId() == null){
                roleModule.setRole( role );
                roleModule.setSystemModule( module );
                roleModule.setSysUserId( "1" );
                roleModule.setHasAdd( "Y" );
                roleModule.setHasDelete( "Y" );
                roleModule.setHasSelect( "Y" );
                roleModule.setHasUpdate( "Y" );
                roleModuleDAOImpl.insertData( roleModule );
            }

            tx.commit();

        } catch (LIMSRuntimeException lre) {
            tx.rollback();
            return false;
        }
        return true;
    }
}

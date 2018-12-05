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

package us.mn.state.health.lims.reports.action.implementation;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.IdValuePair;

/**
 * Represents a list for report specification
 */
public class ReportSpecificationList implements Serializable{


    private final String label;
    private final List<IdValuePair> list;
    private String selection;

    public ReportSpecificationList( List<IdValuePair> list, String label){
        this.label = label;
        this.list = list;
    }

    public void setRequestParameters( BaseActionForm dynaForm ){
        try{
            PropertyUtils.setProperty( dynaForm, "selectList", this );
        }catch( IllegalAccessException e ){
            e.printStackTrace();
        }catch( InvocationTargetException e ){
            e.printStackTrace();
        }catch( NoSuchMethodException e ){
            e.printStackTrace();
        }
    }
    public String getLabel(){
        return label;
    }

    public List<IdValuePair> getList(){
        return list;
    }

    public String getSelection(){
        return selection;
    }

    public void setSelection( String selection ){
        this.selection = selection;
    }

    public String getSelectionAsName(){
        String selection = getSelection();

        for( IdValuePair pair : getList()){
            if( selection.equals( pair.getId() )){
                return pair.getValue();
            }
        }

        return "";
    }
}

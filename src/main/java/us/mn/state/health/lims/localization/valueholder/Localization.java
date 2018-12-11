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

package us.mn.state.health.lims.localization.valueholder;

import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 */
public class Localization extends BaseObject{

    String id;
    String description;
    String english;
    String french;

    public String getId(){
        return id;
    }

    public void setId( String id ){
        this.id = id;
    }

    public String getFrench(){
        return french;
    }

    public void setFrench( String french ){
        this.french = french;
    }

    public String getEnglish(){
        return english;
    }

    public void setEnglish( String english ){
        this.english = english;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription( String description ){
        this.description = description;
    }
}

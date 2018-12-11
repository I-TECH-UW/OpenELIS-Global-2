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

package us.mn.state.health.lims.image.valueholder;

import java.sql.Blob;

import us.mn.state.health.lims.common.valueholder.BaseObject;

/**
 */
public class Image extends BaseObject{
    public static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private String id;
    private String description;
    private Blob image;

    public String getId(){
        return id;
    }

    public void setId( String id ){
        this.id = id;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription( String description ){
        this.description = description;
    }

    public Blob getImage(){
        return image;
    }

    public void setImage( Blob image ){
        this.image = image;
    }
}

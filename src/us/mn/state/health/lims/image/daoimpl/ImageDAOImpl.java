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
package us.mn.state.health.lims.image.daoimpl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.HibernateException;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.image.dao.ImageDAO;
import us.mn.state.health.lims.image.valueholder.Image;


public class ImageDAOImpl extends BaseDAOImpl implements ImageDAO{

    @Override
    public String saveImage( Image image ) throws LIMSRuntimeException{
        String id = image.getId();
        try{
            if( id == null ){
                id = ( String ) HibernateUtil.getSession().save( image );
                closeSession();
            }else{  //this part does not seem to work so we are deleting and then adding as a work around
                HibernateUtil.getSession().merge( image );
                closeSession();
                HibernateUtil.getSession().evict( image );
                HibernateUtil.getSession().refresh( image );
            }
        }catch( HibernateException e ){
            handleException( e, "saveImage" );
        }

        return id;
    }

    @Override
    public Image getImage( String imageId ) throws LIMSRuntimeException{
        try{
            Image image = ( Image ) HibernateUtil.getSession().get( Image.class, imageId );
            closeSession();
            return image;
        }catch( HibernateException e ){
            handleException( e, "getImage" );
        }

        return null;
    }

    @Override
    public void deleteImage( Image image ) throws LIMSRuntimeException{
        try{
            HibernateUtil.getSession().delete( image );
            closeSession();
        }catch( Exception e ){
            handleException( e, "deleteImage" );
        }
    }

    @Override
    public ByteArrayOutputStream retrieveImageOutputStream( String id ) throws LIMSRuntimeException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( Image.MAX_MEMORY_SIZE );
        try{
            Image image = getImage( id );

            byte[] bindata = new byte[ Image.MAX_MEMORY_SIZE ];
            int bytesread;
            BufferedInputStream bufferedInputStream = new BufferedInputStream( image.getImage().getBinaryStream() );
            if( ( bytesread = bufferedInputStream.read( bindata, 0, bindata.length ) ) != -1 ){
                outputStream.write( bindata, 0, bytesread );
            }

        }catch( HibernateException e ){
            handleException( e, "retrieveImageOutputStream" );
        }catch( SQLException e ){
            handleException( e, "retrieveImageOutputStream" );
        }catch( IOException e ){
            handleException( e, "retrieveImageOutputStream" );
        }

        return outputStream;
    }

    @Override
    public ByteArrayInputStream retrieveImageInputStream( String id ) throws LIMSRuntimeException{
        return new ByteArrayInputStream( retrieveImageOutputStream( id ).toByteArray() );
    }

    public Image readImage(String idString) {
        try {
            Image image = (Image) HibernateUtil.getSession().get(Image.class, idString);
            HibernateUtil.getSession().flush();
            HibernateUtil.getSession().clear();
            return image;
        } catch (Exception e) {
            handleException( e, "readImage" );
        }

        return null;
    }
}

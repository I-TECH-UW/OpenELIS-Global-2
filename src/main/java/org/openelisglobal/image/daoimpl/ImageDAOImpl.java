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
package org.openelisglobal.image.daoimpl;

import org.hibernate.Session;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.image.dao.ImageDAO;
import org.openelisglobal.image.valueholder.Image;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ImageDAOImpl extends BaseDAOImpl<Image, String> implements ImageDAO {

    public ImageDAOImpl() {
        super(Image.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Image getImageByDescription(String imageDescription) {

        try {
            String sql = "from Image i where i.description = :imageDescription";

            org.hibernate.Query query = entityManager.unwrap(Session.class).createQuery(sql);
            query.setParameter("imageDescription", imageDescription);
            return (Image) query.uniqueResult();
        } catch (RuntimeException e) {
            LogEvent.logError(e.toString(), e);
            throw new LIMSRuntimeException("Error in Image getImageByDescription()", e);
        }
    }

//	@Override
//	public String saveImage(Image image) throws LIMSRuntimeException {
//		String id = image.getId();
//		try {
//			if (id == null) {
//				id = (String) entityManager.unwrap(Session.class).save(image);
//				// closeSession(); // CSL remove old
//			} else { // this part does not seem to work so we are deleting and then adding as a work
//						// around
//				entityManager.unwrap(Session.class).merge(image);
//				// closeSession(); // CSL remove old
//				// entityManager.unwrap(Session.class).evict // CSL remove old(image);
//				// entityManager.unwrap(Session.class).refresh // CSL remove old(image);
//			}
//		} catch (HibernateException e) {
//			handleException(e, "saveImage");
//		}
//
//		return id;
//	}

//	@Override
//	public void deleteImage(Image image) throws LIMSRuntimeException {
//		try {
//			entityManager.unwrap(Session.class).delete(image);
//			// closeSession(); // CSL remove old
//		} catch (RuntimeException e) {
//			handleException(e, "deleteImage");
//		}
//	}

//	public Image readImage(String idString) {
//		try {
//			Image image = entityManager.unwrap(Session.class).get(Image.class, idString);
//			// entityManager.unwrap(Session.class).flush(); // CSL remove old
//			// entityManager.unwrap(Session.class).clear(); // CSL remove old
//			return image;
//		} catch (RuntimeException e) {
//			handleException(e, "readImage");
//		}
//
//		return null;
//	}
}

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

package us.mn.state.health.lims.testdictionary.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.testdictionary.dao.TestDictionaryDAO;
import us.mn.state.health.lims.testdictionary.valueholder.TestDictionary;

public class TestDictionaryDAOImpl extends BaseDAOImpl implements TestDictionaryDAO{

    @Override
    public TestDictionary getTestDictionaryForTestId( String testId ) throws LIMSRuntimeException{
        String sql = "FROM TestDictionary td where td.testId = :testId";
        try{
            Query query = HibernateUtil.getSession().createQuery( sql );
            query.setInteger( "testId", Integer.parseInt( testId ) );
            TestDictionary testDictionary = ( TestDictionary )query.uniqueResult();
            closeSession();
            return testDictionary;
        }catch( HibernateException e ){
            handleException( e, "getTestDictionaryForTestId" );
        }
        return null;
    }
}
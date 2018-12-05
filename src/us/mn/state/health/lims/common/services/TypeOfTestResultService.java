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

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.typeoftestresult.daoimpl.TypeOfTestResultDAOImpl;


public class TypeOfTestResultService {
    public enum ResultType{
        REMARK( "R"),
        DICTIONARY( "D"),
        TITER( "T"),
        NUMERIC( "N" ),
        ALPHA( "A"),
        MULTISELECT( "M"),
        CASCADING_MULTISELECT( "C");

        String DBValue;
        String id;

        ResultType(String dbValue){
            DBValue = dbValue;
            id = new TypeOfTestResultDAOImpl().getTypeOfTestResultByType(dbValue).getId();
        }

        public String getCharacterValue(){ return DBValue;}
        public String getId(){return id;}
        public boolean matches( String type){ return DBValue.equals( type );}
        public static boolean isDictionaryVariant( String type ){ return !GenericValidator.isBlankOrNull(type) && "DMC".contains( type );}
        public static boolean isMultiSelectVariant( String type ){ return !GenericValidator.isBlankOrNull( type ) && "MC".contains( type );}
        public static boolean isTextOnlyVariant(String type){ return !GenericValidator.isBlankOrNull(type) && "AR".contains( type );}
        public static boolean isTextOnlyVariant(ResultType type){ return "AR".contains( type.getCharacterValue() );}

        public static boolean isNumericById(String resultTypeId) {
            return NUMERIC.getId().equals(resultTypeId);
        }

        public static boolean isNumeric(ResultType type) {
            return "N".equals( type.getCharacterValue());
        }

        public static boolean isDictionaryVarientById( String resultTypeId){
            return DICTIONARY.getId().equals( resultTypeId) ||
                    MULTISELECT.getId().equals( resultTypeId) ||
                    CASCADING_MULTISELECT.getId().equals( resultTypeId);
        }
    }

    public static ResultType getResultTypeById( String id){
        for( ResultType type : ResultType.values()){
            if( type.getId().equals(id) ){
                return type;
            }
        }

        return null;
    }
}

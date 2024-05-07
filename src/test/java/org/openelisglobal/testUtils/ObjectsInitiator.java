package org.openelisglobal.testUtils;

import org.openelisglobal.referencetables.valueholder.ReferenceTables;

public class ObjectsInitiator {
    private static ReferenceTables expected;
    private static ReferenceTables result;
    public static  ReferenceTables createReferenceTable(String tableName, String isHl7Encoded, String keepHistory){
        expected = new ReferenceTables();
        expected.setTableName(tableName);
        expected.setIsHl7Encoded(isHl7Encoded);
        expected.setKeepHistory(keepHistory);
        return expected;
    }
}
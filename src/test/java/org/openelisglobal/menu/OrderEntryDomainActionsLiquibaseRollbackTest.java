package org.openelisglobal.menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;

public class OrderEntryDomainActionsLiquibaseRollbackTest {

    private static final String CHANGE_LOG = "src/main/resources/liquibase/3.5.x.x/089-order-entry-domain-actions.xml";

    @Test
    public void eachDomainActionMovesDirectlyUnderTheOrderMenuAndTheGenericParentIsRetired() throws Exception {
        String changeLog = Files.readString(Path.of(CHANGE_LOG));
        String forward = changeLog.substring(0, changeLog.indexOf("<rollback>"));

        for (String action : new String[] { "menu_clinical_workflow", "menu_environmental_workflow",
                "menu_vector_workflow" }) {
            int where = forward.indexOf("<where>element_id = '" + action + "'</where>");
            assertTrue(action + " must be re-parented", where > 0);
            String update = forward.substring(forward.lastIndexOf("<update", where), where);
            assertTrue(action + " must move under the Order menu",
                    update.contains("WHERE element_id = 'menu_sample')"));
        }
        assertTrue("the generic parent must be retired", forward.contains("valueBoolean=\"false\"") && forward
                .indexOf("<where>element_id = 'menu_add_order'</where>") > forward.indexOf("valueBoolean=\"false\""));
    }

    @Test
    public void rollbackReturnsEveryActionToTheGenericParentAndReactivatesIt() throws Exception {
        String changeLog = Files.readString(Path.of(CHANGE_LOG));
        String rollback = changeLog.substring(changeLog.indexOf("<rollback>"), changeLog.indexOf("</rollback>"));

        assertEquals("every action returns to the generic parent", 3,
                countOf(rollback, "WHERE element_id = 'menu_add_order')\"/>"));
        assertTrue("the generic parent is reactivated before its children return",
                rollback.indexOf("valueBoolean=\"true\"") < rollback.indexOf("menu_clinical_workflow"));
        for (String sibling : new String[] { "menu_sample_add", "menu_sample_create", "menu_sample_edit",
                "menu_sample_eorder" }) {
            assertTrue(sibling + " gets its order back",
                    rollback.contains("<where>element_id = '" + sibling + "'</where>"));
        }
    }

    private static int countOf(String text, String needle) {
        int count = 0;
        for (int at = text.indexOf(needle); at >= 0; at = text.indexOf(needle, at + needle.length())) {
            count++;
        }
        return count;
    }
}

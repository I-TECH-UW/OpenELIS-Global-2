package org.openelisglobal.panel;

import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.common.exception.LIMSDuplicateRecordException;
import org.openelisglobal.localization.valueholder.Localization;
import org.openelisglobal.panel.service.PanelService;
import org.openelisglobal.panel.valueholder.Panel;
import org.springframework.beans.factory.annotation.Autowired;

public class PanelServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    PanelService panelService;

    @Before
    public void init() {
        // Initialize resources before each test
    }

    @After
    public void tearDown() {
        // Clean up resources after each test
    }

    // Method to create a Panel object
    private Panel createPanel(String panelName, String description) {
        Panel panel = new Panel();
        panel.setPanelName(panelName);
        panel.setDescription(description);
        panel.setId(String.valueOf(new Random().nextInt(1000))); // Ensure the panel has a numeric ID as String
        return panel;
    }

    @Test
    @Transactional
    public void insert_shouldCreateNewPanel() throws Exception {
        String panelName = "Test Panel";
        String description = "This is a test panel.";

        Panel panel = createPanel(panelName, description);
        String panelId = panelService.insert(panel);
        Panel savedPanel = panelService.getPanelById(panelId);

        Assert.assertNotNull(savedPanel);
        Assert.assertEquals(panelName, savedPanel.getPanelName());
        Assert.assertEquals(description, savedPanel.getDescription());
    }

    @Test(expected = LIMSDuplicateRecordException.class)
    @Transactional
    public void insert_shouldThrowExceptionForDuplicatePanelName() throws Exception {
        String panelName = "Duplicate Panel";
        String description = "This is a duplicate panel.";

        // Insert the first panel
        Panel panel1 = createPanel(panelName, description);
        panelService.insert(panel1);

        // Attempt to insert a duplicate panel
        Panel panel2 = createPanel(panelName, "Another description");
        panelService.insert(panel2); // Should throw LIMSDuplicateRecordException
    }

    @Test(expected = LIMSDuplicateRecordException.class)
    @Transactional
    public void insert_shouldThrowExceptionForDuplicatePanelDescription() throws Exception {
        String panelName1 = "Panel 1";
        String description = "Duplicate description";

        // Insert the first panel
        Panel panel1 = createPanel(panelName1, description);
        panelService.insert(panel1);

        // Attempt to insert a panel with a duplicate description
        Panel panel2 = createPanel("Panel 2", description);
        panelService.insert(panel2); // Should throw LIMSDuplicateRecordException
    }

    @Test
    @Transactional
    public void update_shouldUpdatePanelInformation() throws Exception {
        String panelName = "Panel to Update";
        String description = "Initial description.";

        Panel panel = createPanel(panelName, description);
        String panelId = panelService.insert(panel);
        Panel savedPanel = panelService.getPanelById(panelId);

        savedPanel.setDescription("Updated description.");
        Panel updatedPanel = panelService.update(savedPanel);

        Assert.assertEquals("Updated description.", updatedPanel.getDescription());
    }

    @Test(expected = LIMSDuplicateRecordException.class)
    @Transactional
    public void update_shouldThrowExceptionForDuplicatePanel() throws Exception {
        String panelName1 = "Panel 1";
        String description1 = "Description 1";

        // Insert first panel
        Panel panel1 = createPanel(panelName1, description1);
        panelService.insert(panel1);

        String panelName2 = "Panel 2";
        String description2 = "Description 2";

        // Insert second panel
        Panel panel2 = createPanel(panelName2, description2);
        panelService.insert(panel2);

        // Attempt to update panel2 with panel1's name
        panel2.setPanelName(panelName1);
        panelService.update(panel2); // Should throw LIMSDuplicateRecordException
    }

    @Test
    @Transactional
    public void delete_shouldRemovePanelFromDatabase() throws Exception {
        String panelName = "Panel to Delete";
        String description = "This panel will be deleted.";

        Panel panel = createPanel(panelName, description);
        String panelId = panelService.insert(panel);

        panelService.delete(panelService.getPanelById(panelId));

        Panel deletedPanel = panelService.getPanelById(panelId);

        Assert.assertNull(deletedPanel);
    }

    @Test
    @Transactional
    public void getPanelByName_shouldReturnCorrectPanel() throws Exception {
        String panelName = "Panel by Name";
        String description = "Panel description.";

        Panel panel = createPanel(panelName, description);
        panelService.insert(panel);

        Panel retrievedPanel = panelService.getPanelByName(panelName);

        Assert.assertNotNull(retrievedPanel);
        Assert.assertEquals(panelName, retrievedPanel.getPanelName());
        Assert.assertEquals(description, retrievedPanel.getDescription());
    }

    @Test
    @Transactional
    public void getTotalPanelCount_shouldReturnCorrectCount() throws Exception {
        String panelName1 = "Panel 1";
        String panelName2 = "Panel 2";

        Panel panel1 = createPanel(panelName1, "Description for panel 1.");
        Panel panel2 = createPanel(panelName2, "Description for panel 2.");

        panelService.insert(panel1);
        panelService.insert(panel2);

        Integer totalPanelCount = panelService.getTotalPanelCount();

        Assert.assertEquals(Integer.valueOf(2), totalPanelCount);
    }

    @Test
    @Transactional
    public void getAllActivePanels_shouldReturnActivePanels() throws Exception {
        String panelName = "Active Panel";
        String description = "This panel is active.";

        Panel panel = createPanel(panelName, description);
        panelService.insert(panel);

        List<Panel> activePanels = panelService.getAllActivePanels();

        Assert.assertFalse(activePanels.isEmpty());
        Assert.assertTrue(activePanels.stream().anyMatch(p -> p.getPanelName().equals(panelName)));
    }

    @Test
    @Transactional
    public void getLocalizationForPanel_shouldReturnLocalization() throws Exception {
        String panelName = "Localized Panel";
        String description = "This panel has localization.";

        Panel panel = createPanel(panelName, description);
        panelService.insert(panel);

        Localization localization = panelService.getLocalizationForPanel(panel.getId());

        Assert.assertNotNull(localization);
        // Additional assertions depending on the structure of Localization
    }
}

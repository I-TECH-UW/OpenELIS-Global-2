package org.openelisglobal.region;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openelisglobal.BaseWebContextSensitiveTest;
import org.openelisglobal.region.service.RegionService;
import org.openelisglobal.region.valueholder.Region;
import org.springframework.beans.factory.annotation.Autowired;

public class RegionServiceTest extends BaseWebContextSensitiveTest {

    @Autowired
    RegionService regionService;

    @Before
    public void init() throws Exception {
        regionService.deleteAll(regionService.getAll());
    }

    @After
    public void tearDown() {
        regionService.deleteAll(regionService.getAll());
    }

    @Test
    public void createRegion_shouldCreateNewRegion() throws Exception {
        String regionName = "Midwest";
        String regionId = "1";

        Region region = createRegion(regionName, regionId);

        Assert.assertEquals(0, regionService.getAll().size());
        String savedRegionId = regionService.insert(region);
        Region savedRegion = regionService.get(savedRegionId);

        Assert.assertEquals(1, regionService.getAll().size());
        Assert.assertEquals(regionName, savedRegion.getRegion());
        Assert.assertNotNull(savedRegion.getId());
    }

    @Test
    public void updateRegion_shouldUpdateRegionInformation() throws Exception {
        String regionName = "Northeast";
        String regionId = "2";

        Region region = createRegion(regionName, regionId);
        String savedRegionId = regionService.insert(region);
        Region savedRegion = regionService.get(savedRegionId);

        savedRegion.setRegion("Southeast");
        regionService.update(savedRegion);

        Region updatedRegion = regionService.get(savedRegionId);

        Assert.assertEquals("Southeast", updatedRegion.getRegion());
    }

    @Test
    public void getAllRegions_shouldReturnAllRegions() throws Exception {
        Region region = new Region();
        region.setRegion("Southwest");
        regionService.insert(region);

        Assert.assertEquals(1, regionService.getAll().size());
    }

    @Test
    public void deleteRegion_shouldRemoveRegion() throws Exception {
        Region region = new Region();
        region.setRegion("Northwest");
        regionService.insert(region);

        Assert.assertEquals(1, regionService.getAll().size());

        regionService.delete(region);

        Assert.assertEquals(0, regionService.getAll().size());
    }

    private Region createRegion(String regionName, String regionId) {
        Region region = new Region();
        region.setRegion(regionName);
        region.setId(regionId);
        return region;
    }
}
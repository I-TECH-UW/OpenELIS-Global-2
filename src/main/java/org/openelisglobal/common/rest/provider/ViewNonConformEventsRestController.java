package org.openelisglobal.common.rest.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openelisglobal.common.services.DisplayListService;
import org.openelisglobal.common.util.DateUtil;
import org.openelisglobal.qaevent.service.NCEventService;
import org.openelisglobal.qaevent.service.NceCategoryService;
import org.openelisglobal.qaevent.service.NceSpecimenService;
import org.openelisglobal.qaevent.service.NceTypeService;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;
import org.openelisglobal.sampleitem.service.SampleItemService;
import org.openelisglobal.sampleitem.valueholder.SampleItem;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewNonConformEventsRestController {

  @Autowired
  private NCEventService ncEventService;

  @Autowired
  private NceCategoryService nceCategoryService;

  @Autowired
  private NceTypeService nceTypeService;

  @Autowired
  private NceSpecimenService nceSpecimenService;

  @Autowired
  private SampleItemService sampleItemService;

  @GetMapping(
    value = "/rest/viewNonConformEvents",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<?> getNceNumber(
    @RequestParam(required = false) String labNumber,
    @RequestParam(required = false) String nceNumber,
    @RequestParam(required = false) String status
  ) {
    Map<String, Object> searchParameters = new HashMap<>();
    searchParameters.put("status", status);
    List<NcEvent> searchResults = new ArrayList<>();
    if (!"".equalsIgnoreCase(labNumber)) {
      searchParameters.put("labOrderNumber", labNumber);
    } else if (!"".equalsIgnoreCase(nceNumber)) {
      searchParameters.put("nceNumber", nceNumber);
    }

    searchResults = ncEventService.getAllMatching(searchParameters);

    if (searchResults.size() == 0) {
      return ResponseEntity.ok().body("No results found for search criteria.");
    }

    String newNceNumber = searchResults.get(0).getNceNumber();

    System.out.println("newNCENum"+newNceNumber);

    NcEvent event = ncEventService.getMatch("nceNumber", newNceNumber).get();

    Map<String, Object> res = new HashMap<>();

    res.put("res", searchResults);

    res.put("nceCat", nceCategoryService.getAllNceCategories());
    res.put("nceTypes", nceTypeService.getAllNceTypes());

    res.put(
      "labComponentList",
      DisplayListService.getInstance()
        .getList(DisplayListService.ListType.LABORATORY_COMPONENT)
    );

    res.put(
      "severityConsequenceList",
      DisplayListService.getInstance()
        .getList(DisplayListService.ListType.SEVERITY_CONSEQUENCES_LIST)
    );

    res.put(
      "reportingUnits",
      DisplayListService.getInstance()
        .getList(DisplayListService.ListType.TEST_SECTION_ACTIVE)
    );

    res.put(
      "severityRecurs",
      DisplayListService.getInstance()
        .getList(DisplayListService.ListType.SEVERITY_RECURRENCE_LIST)
    );

    res.put("repoUnit", event.getReportingUnitId());

    List<NceSpecimen> specimenList = nceSpecimenService.getAllMatching(
      "nceId",
      event.getId()
    );

    List<SampleItem> sampleItems = new ArrayList<>();

    for (NceSpecimen specimen : specimenList) {
      SampleItem si = sampleItemService.getData(
        specimen.getSampleItemId() + ""
      );
      sampleItems.add(si);
    }

    res.put("specimen", sampleItems);

    res.put("reportDate", DateUtil.formatDateAsText(event.getReportDate()));
    res.put("dateOfEvent", DateUtil.formatDateAsText(event.getDateOfEvent()));

    return ResponseEntity.ok().body(res);
  }
}

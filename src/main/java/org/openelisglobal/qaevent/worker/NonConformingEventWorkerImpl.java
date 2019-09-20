package org.openelisglobal.qaevent.worker;

import org.openelisglobal.qaevent.form.NonConformingEventForm;
import org.openelisglobal.qaevent.service.NCEventService;
import org.openelisglobal.qaevent.service.NceSpecimenService;
import org.openelisglobal.qaevent.valueholder.NcEvent;
import org.openelisglobal.qaevent.valueholder.NceSpecimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@Service
public class NonConformingEventWorkerImpl implements NonConformingEventWorker {

    @Autowired
    private NCEventService ncEventService;
    @Autowired
    private NceSpecimenService nceSpecimenService;

    @Override
    public NcEvent create(String labOrderId, List<String> specimens, String sysUserId, String nceNumber) {
        NcEvent event = new NcEvent();
        event.setSysUserId(sysUserId);
        event.setNceNumber(nceNumber);
        event.setLabOrderNumber(labOrderId);
        event = ncEventService.save(event);
        for(String specimenId: specimens) {
            NceSpecimen specimen = new NceSpecimen();
            specimen.setNceId(Integer.parseInt(event.getId()));
            specimen.setSampleItemId(Integer.parseInt(specimenId));
            specimen.setSysUserId(sysUserId);
            nceSpecimenService.save(specimen);
        }
        return event;
    }

    @Override
    public boolean update(NonConformingEventForm form) {
        NcEvent ncEvent = ncEventService.get(form.getId());
        if (ncEvent != null) {
            ncEvent.setSysUserId(form.getCurrentUserId());
            // date from input field come in this pattern
            Date dateOfEvent = getDate(form.getDateOfEvent(), "yyyy-MM-dd");
            Date reportDate = getDate(form.getReportDate(), "dd/MM/yyyy");

            // ncEvent.setStatus("Pending");
            ncEvent.setReportDate(reportDate);
            ncEvent.setDateOfEvent(dateOfEvent);
            ncEvent.setName(form.getName());
            ncEvent.setNameOfReporter(form.getReporterName());
            ncEvent.setPrescriberName(form.getPrescriberName());
            ncEvent.setSite(form.getSite());
            ncEvent.setDescription(form.getDescription());
            ncEvent.setSuspectedCauses(form.getSuspectedCauses());
            ncEvent.setProposedAction(form.getProposedAction());

            ncEventService.update(ncEvent);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFollowUp(NonConformingEventForm form) {
        NcEvent ncEvent = ncEventService.get(form.getId());
        if (ncEvent != null) {
            ncEvent.setLaboratoryComponent(form.getLaboratoryComponent());
            ncEvent.setNceCategoryId(Integer.parseInt(form.getNceCategory()));
            ncEvent.setNceTypeId(Integer.parseInt(form.getNceType()));
            ncEvent.setConsequenceId(form.getConsequences());
            ncEvent.setRecurrenceId(form.getRecurrence());
            ncEvent.setSeverityScore(form.getSeverityScore());
            ncEvent.setCorrectiveAction(form.getCorrectiveAction());
            ncEvent.setControlAction(form.getControlAction());
            ncEvent.setComments(form.getComments());
            ncEventService.update(ncEvent);
            return true;
        }
        return false;
    }

    /**
     *  Given a String value, returns the equivalent {@link Date} object.
     * @param value
     * @return {@link Date} object or null if there is a parse error.
     */
    private Date getDate(String value, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return new Date(format.parse(value).getTime());
        } catch (ParseException pe) {}
        return null;
    }
}

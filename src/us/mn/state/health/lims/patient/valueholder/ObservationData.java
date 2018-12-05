/**
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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.patient.valueholder;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;

import us.mn.state.health.lims.dictionary.ObservationHistoryList;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;

/**
 * Value object for transfer of observation history (demographic survey questions) from the study (project) based patient entry form.
 * This is one object for all studies.
 * @author pahill
 * @since 2010-04-16
 */
public class ObservationData implements Serializable {
	private static final long serialVersionUID = 2L;

	public ObservationData() {
		super();
	}

	/**
	 * General tag which identifies which set of questions have been answered to enter/accession this person and sample into the system.
	 */
	private String projectFormName;

	private String educationLevel;
	private String maritalStatus;
	private String nationality;
	private String nationalityOther;
	private String legalResidence;
	private String nameOfDoctor;
	private String arvProphylaxisBenefit;
	private String arvProphylaxis;

	/**
	 * Used for both
	 * Initial ARV CLI 04 "currently under ARV treatment" and the slightly different question Follow-up ARV SUI 20 "... on going ARV treatment ..."
	 */
	private String currentARVTreatment;
	private String priorARVTreatment;
	private String interruptedARVTreatment;
	private String aidsStage;
	private String hivStatus;

    private String anyPriorDiseases;
	private String priorDiseases;      // drop down value yes, no
    private String priorDiseasesValue; // actual string containing other Diseases which is answer to "please specify"

	private String anyCurrentDiseases;
	private String currentDiseases;
	private String currentDiseasesValue; // actual string containing other Diseases which is answer to "please specify"
	
	private List<NameValuePair> priorDiseasesList;
    private List<NameValuePair> currentDiseasesList;

    private List<NameValuePair> rtnPriorDiseasesList;
    private List<NameValuePair> rtnCurrentDiseasesList;
    
	private List<String> priorARVTreatmentINNs  = Arrays.asList(new String[] {null, null, null, null});
	private List<String> futureARVTreatmentINNs = Arrays.asList(new String[] {null, null, null, null});
	private List<String> currentARVTreatmentINNs= Arrays.asList(new String[] {null, null, null, null});
	//private List<String> initialSampleConditionINNs= Arrays.asList(new String[] {null, null, null, null});

	/**
	 * OI = opportunistic infection
	 */
	private String currentOITreatment;
	private String cotrimoxazoleTreatment;
	private String patientWeight;
	private String karnofskyScore;

	private String cd4Count;
	private String cd4Percent;
	private String initcd4Count;
	private String initcd4Percent;
	private String demandcd4Count;
	private String demandcd4Percent;
	private String priorCd4Date;
	private String antiTbTreatment;
	private String arvTreatmentAnyAdverseEffects;

	private List<AdverseEffect> arvTreatmentAdverseEffects =
		Arrays.asList(new AdverseEffect[] { new AdverseEffect(), new AdverseEffect(), new AdverseEffect(), new AdverseEffect()});

	private String arvTreatmentChange;
	private String arvTreatmentNew;
	private String arvTreatmentRegime;
	private String cotrimoxazoleTreatmentAnyAdverseEffects;


	private List<AdverseEffect> cotrimoxazoleTreatmentAdverseEffects =
		Arrays.asList(new AdverseEffect[] { new AdverseEffect(), new AdverseEffect(), new AdverseEffect(), new AdverseEffect()});

	private String hospital;
	private String service;
	private String hospitalPatient;

	// Sample Forms
	private String whichPCR;
	private String reasonForSecondPCRTest;
	private String indFirstTestName;
	private String indSecondTestName;
	private String indFirstTestDate;
	private String indSecondTestDate;
	private String indFirstTestResult;
	private String indSecondTestResult;
	private String indSiteFinalResult;
	private String reasonForRequest;
	
	// VL Patient form
	private String arvTreatmentInitDate;
	private String vlReasonForRequest;
	private String vlOtherReasonForRequest;
	private String initcd4Date;
	private String demandcd4Date;
	private String vlBenefit;
	private String vlPregnancy;
	private String vlSuckle;
	private String priorVLLab;
	private String priorVLValue;
	private String priorVLDate;
	
	// EID Patient form
	private String eidInfantPTME;
	private String eidTypeOfClinic;
	private String eidTypeOfClinicOther;
	private String eidHowChildFed;
	private String eidStoppedBreastfeeding;
	private String eidInfantSymptomatic;
	private String eidMothersHIVStatus;
	private String eidMothersARV;
	private String eidInfantsARV;
	private String eidInfantCotrimoxazole;

	private String nameOfRequestor;

    private String nameOfSampler;

	/**
	 * Yes/No
	 */
	private String anySecondaryTreatment;

	/**
	 * Actual treatment
	 */
	private String secondaryTreatment;

	private String clinicVisits;
	/**
	 * Reason for test submital
	 */
	private String reason;
	
    private String underInvestigation;
    
    private String CTBPul;
    private String CTBExpul;
    private String CCrblToxo;
    private String CCryptoMen;
    private String CGenPrurigo;
    private String CIST;
    private String CCervCancer;
    private String COpharCand;
    private String CKaposiSarc;
    private String CShingles;
    private String CDiarrheaC;
    private String PTBPul;
    private String PTBExpul;
    private String PCrblToxo;
    private String PCryptoMen;
    private String PGenPrurigo;
    private String PIST;
    private String PCervCancer;
    private String POpharCand;
    private String PKaposiSarc;
    private String PShingles;
    private String PDiarrheaC;
    private String weightLoss;
    private String diarrhea;
    private String fever;
    private String cough;
    private String pulTB;
    private String expulTB;
    private String swallPaint;
    private String cryptoMen;
    private String recPneumon;
    private String sespis;
    private String recInfect;
    private String curvixC;
    private String matHIV;
    private String cachexie;
    private String thrush;
    private String dermPruip;
    private String herpes;
    private String zona;
    private String sarcKapo;
    private String xIngPadenp;
    private String HIVDement;    
    
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNationalityOther() {
		return nationalityOther;
	}
	public void setNationalityOther(String nationalityOther) {
		this.nationalityOther = nationalityOther;
	}
	public String getLegalResidence() {
		return legalResidence;
	}
	public void setLegalResidence(String legalResidence) {
		this.legalResidence = legalResidence;
	}
	public String getNameOfDoctor() {
		return nameOfDoctor;
	}
	public void setNameOfDoctor(String nameOfDoctor) {
		this.nameOfDoctor = nameOfDoctor;
	}

    public void setAnyPriorDiseases(String anyPriorDiseases) {
        this.anyPriorDiseases = anyPriorDiseases;
    }
    public String getAnyPriorDiseases() {
        return anyPriorDiseases;
    }
    public void setPriorDiseases(String priorDiseases) {
        this.priorDiseases = priorDiseases;
    }
    public String getPriorDiseases() {
        return priorDiseases;
    }

    public String getArvProphylaxisBenefit() {
		return arvProphylaxisBenefit;
	}
	public void setArvProphylaxisBenefit(String arvProphylaxisBenefit) {
		this.arvProphylaxisBenefit = arvProphylaxisBenefit;
	}

	public String getArvProphylaxis() {
		return arvProphylaxis;
	}
	public void setArvProphylaxis(String arvProphylaxis) {
		this.arvProphylaxis = arvProphylaxis;
	}

	public void setCurrentARVTreatment(String currentARVTreatment) {
		this.currentARVTreatment = currentARVTreatment;
	}
	public String getCurrentARVTreatment() {
		return currentARVTreatment;
	}

	public void setPriorARVTreatment(String priorARVTreatment) {
		this.priorARVTreatment = priorARVTreatment;
	}
	public String getPriorARVTreatment() {
		return priorARVTreatment;
	}

	public void setCotrimoxazoleTreatment(String cotrimoxazoleTreatment) {
		this.cotrimoxazoleTreatment = cotrimoxazoleTreatment;
	}
	public String getCotrimoxazoleTreatment() {
		return cotrimoxazoleTreatment;
	}

	public void setAidsStage(String aidsStage) {
		this.aidsStage = aidsStage;
	}
	public String getAidsStage() {
		return aidsStage;
	}

    public void setAnyCurrentDiseases(String anyCurrentDiseases) {
        this.anyCurrentDiseases = anyCurrentDiseases;
    }
    public String getAnyCurrentDiseases() {
        return anyCurrentDiseases;
    }

	public void setCurrentDiseases(String currentDiseases) {
	    this.currentDiseases = currentDiseases;
	}

	public String getCurrentDiseases() {
	    return currentDiseases;
	}

	public void setCurrentOITreatment(String currentTreatment) {
		this.currentOITreatment = currentTreatment;
	}
	public String getCurrentOITreatment() {
		return currentOITreatment;
	}

	public String getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public String getKarnofskyScore() {
		return karnofskyScore;
	}
	public void setKarnofskyScore(String karnofskyScore) {
		this.karnofskyScore = karnofskyScore;
	}
	public String getHivStatus() {
		return hivStatus;
	}
	public void setHivStatus(String hivStatus) {
		this.hivStatus = hivStatus;
	}
	public String getCd4Count() {
		return cd4Count;
	}
	public void setCd4Count(String cd4Count) {
		this.cd4Count = cd4Count;
	}
	public String getInitcd4Count() {
		return initcd4Count;
	}
	public void setInitcd4Count(String initcd4Count) {
		this.initcd4Count = initcd4Count;
	}
	public String getDemandcd4Count() {
		return demandcd4Count;
	}
	public void setDemandcd4Count(String demandcd4Count) {
		this.demandcd4Count = demandcd4Count;
	}
	public String getInitcd4Date() {
		return initcd4Date;
	}
	public void setInitcd4Date(String initcd4Date) {
		this.initcd4Date = initcd4Date;
	}
	public String getDemandcd4Date() {
		return demandcd4Date;
	}
	public void setDemandcd4Date(String demandcd4Date) {
		this.demandcd4Date = demandcd4Date;
	}
	public String getCd4Percent() {
		return cd4Percent;
	}
	public void setCd4Percent(String cd4Percent) {
		this.cd4Percent = cd4Percent;
	}
	public String getInitcd4Percent() {
		return initcd4Percent;
	}
	public void setInitcd4Percent(String initcd4Percent) {
		this.initcd4Percent = initcd4Percent;
	}
	public String getDemandcd4Percent() {
		return demandcd4Percent;
	}
	public void setDemandcd4Percent(String demandcd4Percent) {
		this.demandcd4Percent = demandcd4Percent;
	}
	public String getPriorCd4Date() {
		return priorCd4Date;
	}
	public void setPriorCd4Date(String priorCd4Date) {
		this.priorCd4Date = priorCd4Date;
	}
	public String getAntiTbTreatment() {
		return antiTbTreatment;
	}
	public void setAntiTbTreatment(String antiTbTreatment) {
		this.antiTbTreatment = antiTbTreatment;
	}

	public void setInterruptedARVTreatment(String interruptedARV) {
		this.interruptedARVTreatment = interruptedARV;
	}
	public String getInterruptedARVTreatment() {
		return interruptedARVTreatment;
	}

	public String getPriorARVTreatmentINNs(int index) {
	    return priorARVTreatmentINNs.get(index);
	}

    public void setPriorARVTreatmentINNs(int index, String value) {
        priorARVTreatmentINNs.set(index, value);
    }

	public List<String> getPriorARVTreatmentINNsList() {
		return priorARVTreatmentINNs;
	}
	
	public String getCurrentARVTreatmentINNs(int index) {
	    return currentARVTreatmentINNs.get(index);
	}

    public void setCurrentARVTreatmentINNs(int index, String value) {
        currentARVTreatmentINNs.set(index, value);
    }
    
	public List<String> getCurrentARVTreatmentINNsList() {
		return currentARVTreatmentINNs;
	}	
	
	/*public String getInitialSampleConditionINNs(int index) {
	    return initialSampleConditionINNs.get(index);
	}

    public void setInitialSampleConditionINNs(int index, String value) {
    	initialSampleConditionINNs.set(index, value);
    }

    public List<String> getInitialSampleConditionINNsList() {
		return initialSampleConditionINNs;
	}*/
    
	public String getFutureARVTreatmentINNs(int index) {
        return futureARVTreatmentINNs.get(index);
    }

    public void setFutureARVTreatmentINNs(int index, String value) {
        futureARVTreatmentINNs.set(index, value);
    }

	public List<String> getFutureARVTreatmentINNsList() {
		return futureARVTreatmentINNs;
	}

	public void setArvTreatmentAnyAdverseEffects(
			String arvTreatmentAnyAdverseEffects) {
		this.arvTreatmentAnyAdverseEffects = arvTreatmentAnyAdverseEffects;
	}
	public String getArvTreatmentAnyAdverseEffects() {
		return arvTreatmentAnyAdverseEffects;
	}
	public List<AdverseEffect> getArvTreatmentAdverseEffects() {
		return arvTreatmentAdverseEffects;
	}

	public void setArvTreatmentChange(String arvTreatmentChange) {
		this.arvTreatmentChange = arvTreatmentChange;
	}
	public String getArvTreatmentChange() {
		return arvTreatmentChange;
	}
	public void setArvTreatmentNew(String arvTreatmentNew) {
		this.arvTreatmentNew = arvTreatmentNew;
	}
	public String getArvTreatmentNew() {
		return arvTreatmentNew;
	}
	public void setArvTreatmentRegime(String arvTreatmentRegime) {
		this.arvTreatmentRegime = arvTreatmentRegime;
	}
	public String getArvTreatmentRegime() {
		return arvTreatmentRegime;
	}

	public void setCotrimoxazoleTreatmentAnyAdverseEffects(
			String cotrimoxazoleTreatmentAnyAdverseEffects) {
		this.cotrimoxazoleTreatmentAnyAdverseEffects = cotrimoxazoleTreatmentAnyAdverseEffects;
	}

	public String getCotrimoxazoleTreatmentAnyAdverseEffects() {
		return cotrimoxazoleTreatmentAnyAdverseEffects;
	}

	public List<AdverseEffect> getCotrimoxazoleTreatmentAdverseEffects() {
		return cotrimoxazoleTreatmentAdverseEffects;
	}
	public void setAnySecondaryTreatment(String anySecondaryTreatment) {
		this.anySecondaryTreatment = anySecondaryTreatment;
	}
	public String getAnySecondaryTreatment() {
		return anySecondaryTreatment;
	}

	public void setSecondaryTreatment(String secondaryTreatment) {
		this.secondaryTreatment = secondaryTreatment;
	}
	public String getSecondaryTreatment() {
		return secondaryTreatment;
	}

	public void setClinicVisits(String clinicVisits) {
		this.clinicVisits = clinicVisits;
	}
	public String getClinicVisits() {
		return clinicVisits;
	}

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    public String getHospital() {
        return hospital;
    }

    public void setService(String service) {
        this.service = service;
    }
    public String getService() {
        return service;
    }

    public void setHospitalPatient(String hospitalPatient) {
        this.hospitalPatient = hospitalPatient;
    }
    public String getHospitalPatient() {
        return hospitalPatient;
    }

    public String getWhichPCR() {
		return whichPCR;
	}
	public void setWhichPCR(String whichPCR) {
		this.whichPCR = whichPCR;
	}
	public String getReasonForSecondPCRTest() {
		return reasonForSecondPCRTest;
	}
	public void setReasonForSecondPCRTest(String reasonForSecondPCRTest) {
		this.reasonForSecondPCRTest = reasonForSecondPCRTest;
	}
	public String getIndFirstTestName() {
		return indFirstTestName;
	}
	public void setIndFirstTestName(String indFirstTestName) {
		this.indFirstTestName = indFirstTestName;
	}
	public String getIndSecondTestName() {
		return indSecondTestName;
	}
	public void setIndSecondTestName(String indSecondTestName) {
		this.indSecondTestName = indSecondTestName;
	}
	public String getIndFirstTestDate() {
		return indFirstTestDate;
	}
	public void setIndFirstTestDate(String indFirstTestDate) {
		this.indFirstTestDate = indFirstTestDate;
	}
	public String getIndSecondTestDate() {
		return indSecondTestDate;
	}
	public void setIndSecondTestDate(String indSecondTestDate) {
		this.indSecondTestDate = indSecondTestDate;
	}
	public String getIndFirstTestResult() {
		return indFirstTestResult;
	}
	public void setIndFirstTestResult(String indFirstTestResult) {
		this.indFirstTestResult = indFirstTestResult;
	}
	public String getIndSecondTestResult() {
		return indSecondTestResult;
	}
	public void setIndSecondTestResult(String indSecondTestResult) {
		this.indSecondTestResult = indSecondTestResult;
	}
	public String getIndSiteFinalResult() {
		return indSiteFinalResult;
	}
	public void setIndSiteFinalResult(String indSiteFinalResult) {
		this.indSiteFinalResult = indSiteFinalResult;
	}

	public void setReasonForRequest(String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}
	public String getReasonForRequest() {
		return reasonForRequest;
	}
	public String getEidInfantPTME() {
		return eidInfantPTME;
	}
	public void setEidInfantPTME(String eidInfantPTME) {
		this.eidInfantPTME = eidInfantPTME;
	}
	public String getEidTypeOfClinic() {
		return eidTypeOfClinic;
	}
	public void setEidTypeOfClinic(String eidTypeOfClinic) {
		this.eidTypeOfClinic = eidTypeOfClinic;
	}
	public String getEidHowChildFed() {
		return eidHowChildFed;
	}
	public void setEidHowChildFed(String eidHowChildFed) {
		this.eidHowChildFed = eidHowChildFed;
	}
	public String getEidInfantSymptomatic() {
		return eidInfantSymptomatic;
	}
	public void setEidInfantSymptomatic(String eidInfantSymptomatic) {
		this.eidInfantSymptomatic = eidInfantSymptomatic;
	}
	public String getEidMothersHIVStatus() {
		return eidMothersHIVStatus;
	}
	public void setEidMothersHIVStatus(String eidMothersHIVStatus) {
		this.eidMothersHIVStatus = eidMothersHIVStatus;
	}
	public String getEidMothersARV() {
		return eidMothersARV;
	}
	public void setEidMothersARV(String eidMothersARV) {
		this.eidMothersARV = eidMothersARV;
	}
	public String getEidInfantsARV() {
		return eidInfantsARV;
	}
	public void setEidInfantsARV(String eidInfantsARV) {
		this.eidInfantsARV = eidInfantsARV;
	}
	public String getEidInfantCotrimoxazole() {
		return eidInfantCotrimoxazole;
	}
	public void setEidInfantCotrimoxazole(String eidInfantCotrimoxazole) {
		this.eidInfantCotrimoxazole = eidInfantCotrimoxazole;
	}

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

    public String getProjectFormName() {
        return projectFormName;
    }
    public void setProjectFormName(String projectFormName) {
        this.projectFormName = projectFormName;
    }
    public String getNameOfRequestor() {
        return nameOfRequestor;
    }
    public void setNameOfRequestor(String nameOfRequestor) {
        this.nameOfRequestor = nameOfRequestor;
    }
    public String getNameOfSampler() {
        return nameOfSampler;
    }
    public void setNameOfSampler(String nameOfSampler) {
        this.nameOfSampler = nameOfSampler;
    }
 
    public void setUnderInvestigation(String underInvestigation) {
        this.underInvestigation = underInvestigation;
    }
    public String getUnderInvestigation() {
        return underInvestigation;
    }
    public void setEidTypeOfClinicOther(String eidTypeOfClinicOther) {
        this.eidTypeOfClinicOther = eidTypeOfClinicOther;
    }
    public String getEidTypeOfClinicOther() {
        return eidTypeOfClinicOther;
    }
    public void setEidStoppedBreastfeeding(String eidStoppedBreastfeeding) {
        this.eidStoppedBreastfeeding = eidStoppedBreastfeeding;
    }
    public String getEidStoppedBreastfeeding() {
        return eidStoppedBreastfeeding;
    }
    public List<String> getFutureARVTreatmentINNs() {
        return futureARVTreatmentINNs;
    }
    public void setFutureARVTreatmentINNs(List<String> futureARVTreatmentINNs) {
        this.futureARVTreatmentINNs = futureARVTreatmentINNs;
    }
    public String getCTBPul() {
        return CTBPul;
    }
    public void setCTBPul(String cTBPul) {
        CTBPul = cTBPul;
    }
    public String getCTBExpul() {
        return CTBExpul;
    }
    public void setCTBExpul(String cTBExpul) {
        CTBExpul = cTBExpul;
    }
    public String getCCrblToxo() {
        return CCrblToxo;
    }
    public void setCCrblToxo(String cCrblToxo) {
        CCrblToxo = cCrblToxo;
    }
    public String getCCryptoMen() {
        return CCryptoMen;
    }
    public void setCCryptoMen(String cCryptoMen) {
        CCryptoMen = cCryptoMen;
    }
    public String getCGenPrurigo() {
        return CGenPrurigo;
    }
    public void setCGenPrurigo(String cGenPrurigo) {
        CGenPrurigo = cGenPrurigo;
    }
    public String getCIST() {
        return CIST;
    }
    public void setCIST(String cIST) {
        CIST = cIST;
    }
    public String getCCervCancer() {
        return CCervCancer;
    }
    public void setCCervCancer(String cCervCancer) {
        CCervCancer = cCervCancer;
    }
    public String getCOpharCand() {
        return COpharCand;
    }
    public void setCOpharCand(String cOpharCand) {
        COpharCand = cOpharCand;
    }
    public String getCKaposiSarc() {
        return CKaposiSarc;
    }
    public void setCKaposiSarc(String cKaposiSarc) {
        CKaposiSarc = cKaposiSarc;
    }
    public String getCShingles() {
        return CShingles;
    }
    public void setCShingles(String cShingles) {
        CShingles = cShingles;
    }
    public String getCDiarrheaC() {
        return CDiarrheaC;
    }
    public void setCDiarrheaC(String cDiarrheaC) {
        CDiarrheaC = cDiarrheaC;
    }
    public String getPTBPul() {
        return PTBPul;
    }
    public void setPTBPul(String pTBPul) {
        PTBPul = pTBPul;
    }
    public String getPTBExpul() {
        return PTBExpul;
    }
    public void setPTBExpul(String pTBExpul) {
        PTBExpul = pTBExpul;
    }
    public String getPCrblToxo() {
        return PCrblToxo;
    }
    public void setPCrblToxo(String pCrblToxo) {
        PCrblToxo = pCrblToxo;
    }
    public String getPCryptoMen() {
        return PCryptoMen;
    }
    public void setPCryptoMen(String pCryptoMen) {
        PCryptoMen = pCryptoMen;
    }
    public String getPGenPrurigo() {
        return PGenPrurigo;
    }
    public void setPGenPrurigo(String pGenPrurigo) {
        PGenPrurigo = pGenPrurigo;
    }
    public String getPIST() {
        return PIST;
    }
    public void setPIST(String pIST) {
        PIST = pIST;
    }
    public String getPCervCancer() {
        return PCervCancer;
    }
    public void setPCervCancer(String pCervCancer) {
        PCervCancer = pCervCancer;
    }
    public String getPOpharCand() {
        return POpharCand;
    }
    public void setPOpharCand(String pOpharCand) {
        POpharCand = pOpharCand;
    }
    public String getPKaposiSarc() {
        return PKaposiSarc;
    }
    public void setPKaposiSarc(String pKaposiSarc) {
        PKaposiSarc = pKaposiSarc;
    }
    public String getPShingles() {
        return PShingles;
    }
    public void setPShingles(String pShingles) {
        PShingles = pShingles;
    }
    public String getPDiarrheaC() {
        return PDiarrheaC;
    }
    public void setPDiarrheaC(String pDiarrheaC) {
        PDiarrheaC = pDiarrheaC;
    }
    public String getWeightLoss() {
        return weightLoss;
    }
    public void setWeightLoss(String weightLoss) {
        this.weightLoss = weightLoss;
    }
    public String getDiarrhea() {
        return diarrhea;
    }
    public void setDiarrhea(String diarrhea) {
        this.diarrhea = diarrhea;
    }
    public String getFever() {
        return fever;
    }
    public void setFever(String fever) {
        this.fever = fever;
    }
    public String getCough() {
        return cough;
    }
    public void setCough(String cough) {
        this.cough = cough;
    }
    public String getPulTB() {
        return pulTB;
    }
    public void setPulTB(String pulTB) {
        this.pulTB = pulTB;
    }
    public String getExpulTB() {
        return expulTB;
    }
    public void setExpulTB(String expulTB) {
        this.expulTB = expulTB;
    }
    public String getSwallPaint() {
        return swallPaint;
    }
    public void setSwallPaint(String swallPaint) {
        this.swallPaint = swallPaint;
    }
    public String getCryptoMen() {
        return cryptoMen;
    }
    public void setCryptoMen(String cryptoMen) {
        this.cryptoMen = cryptoMen;
    }
    public String getRecPneumon() {
        return recPneumon;
    }
    public void setRecPneumon(String recPneumon) {
        this.recPneumon = recPneumon;
    }
    public String getSespis() {
        return sespis;
    }
    public void setSespis(String sespis) {
        this.sespis = sespis;
    }
    public String getRecInfect() {
        return recInfect;
    }
    public void setRecInfect(String recInfect) {
        this.recInfect = recInfect;
    }
    public String getCurvixC() {
        return curvixC;
    }
    public void setCurvixC(String curvixC) {
        this.curvixC = curvixC;
    }
    public String getMatHIV() {
        return matHIV;
    }
    public void setMatHIV(String matHIV) {
        this.matHIV = matHIV;
    }
    public String getCachexie() {
        return cachexie;
    }
    public void setCachexie(String cachexie) {
        this.cachexie = cachexie;
    }
    public String getThrush() {
        return thrush;
    }
    public void setThrush(String thrush) {
        this.thrush = thrush;
    }
    public String getDermPruip() {
        return dermPruip;
    }
    public void setDermPruip(String dermPruip) {
        this.dermPruip = dermPruip;
    }
    public String getHerpes() {
        return herpes;
    }
    public void setHerpes(String herpes) {
        this.herpes = herpes;
    }
    public String getZona() {
        return zona;
    }
    public void setZona(String zona) {
        this.zona = zona;
    }
    public String getSarcKapo() {
        return sarcKapo;
    }
    public void setSarcKapo(String sarcKapo) {
        this.sarcKapo = sarcKapo;
    }
    public String getxIngPadenp() {
        return xIngPadenp;
    }
    public void setxIngPadenp(String xIngPadenp) {
        this.xIngPadenp = xIngPadenp;
    }
    public String getHIVDement() {
        return HIVDement;
    }
    public void setHIVDement(String hIVDement) {
        HIVDement = hIVDement;
    }
    public String getPriorDiseasesValue() {
        return priorDiseasesValue;
    }
    public void setPriorDiseasesValue(String priorDiseasesValue) {
        this.priorDiseasesValue = priorDiseasesValue;
    }
    public String getCurrentDiseasesValue() {
        return currentDiseasesValue;
    }
    public void setCurrentDiseasesValue(String currentDiseasesValue) {
        this.currentDiseasesValue = currentDiseasesValue;
    }
    public String getArvTreatmentInitDate() {
        return arvTreatmentInitDate;
    }
    public void setArvTreatmentInitDate(String arvTreatmentInitDate) {
        this.arvTreatmentInitDate = arvTreatmentInitDate;
    }    
    public String getVlReasonForRequest() {
        return vlReasonForRequest;
    }
    public void setVlReasonForRequest(String vlReasonForRequest) {
        this.vlReasonForRequest = vlReasonForRequest;
    }
    public String getVlOtherReasonForRequest() {
        return vlOtherReasonForRequest;
    }
    public void setVlOtherReasonForRequest(String vlOtherReasonForRequest) {
        this.vlOtherReasonForRequest = vlOtherReasonForRequest;
    }
    public String getVlBenefit() {
        return vlBenefit;
    }
    public void setVlBenefit(String vlBenefit) {
        this.vlBenefit = vlBenefit;
    }

 //-----------   
    public String getVlPregnancy() {
        return vlPregnancy;
    }
    public void setVlPregnancy(String vlPregnancy) {
        this.vlPregnancy = vlPregnancy;
    }
    public String getVlSuckle() {
        return vlSuckle;
    }
    public void setVlSuckle(String vlSuckle) {
        this.vlSuckle = vlSuckle;
    }
//-------------    
    
    public String getPriorVLLab() {
        return priorVLLab;
    }
    public void setPriorVLLab(String priorVLLab) {
        this.priorVLLab = priorVLLab;
    }
    public String getPriorVLValue() {
        return priorVLValue;
    }
    public void setPriorVLValue(String priorVLValue) {
        this.priorVLValue = priorVLValue;
    }
    public String getPriorVLDate() {
        return priorVLDate;
    }
    public void setPriorVLDate(String priorVLDate) {
        this.priorVLDate = priorVLDate;
    }
    public List<NameValuePair> getPriorDiseasesList() {
        if (priorDiseasesList == null) {
            priorDiseasesList = makeDiseaseList("P", ObservationHistoryList.ARV_DISEASES.getList());
        }
        return priorDiseasesList;
    }
    
    private List<NameValuePair> makeDiseaseList(String prefix, List<Dictionary> dictionaryList) {
        List<NameValuePair> nvList = new ArrayList<NameValuePair>();
        for (Dictionary dictionary : dictionaryList) {
            nvList.add(new NameValuePair(prefix+ dictionary.getLocalAbbreviation(), dictionary.getLocalizedName()));
        }
        return nvList;
    }
    
    public NameValuePair getPriorDiseases(int index) {
        return getPriorDiseasesList().get(index);
    }
    
    public List<NameValuePair> getCurrentDiseasesList() {
        if (currentDiseasesList == null) {
            currentDiseasesList = makeDiseaseList("C", ObservationHistoryList.ARV_DISEASES_SHORT.getList());
        }
        return currentDiseasesList;
    }
    
    public NameValuePair getCurrentDiseases(int index) {
        return getCurrentDiseasesList().get(index);
    }
    
    public List<NameValuePair> getRtnPriorDiseasesList() {
        if (rtnPriorDiseasesList == null) {
            rtnPriorDiseasesList = makeDiseaseList("", ObservationHistoryList.RTN_DISEASES.getList());
        }
        return rtnPriorDiseasesList;
    }
    
    public List<NameValuePair> getRtnCurrentDiseasesList() {
        if (rtnCurrentDiseasesList == null) {
            rtnCurrentDiseasesList = makeDiseaseList("", ObservationHistoryList.RTN_EXAM_DISEASES.getList());
        }
        return rtnCurrentDiseasesList;
    }    
}

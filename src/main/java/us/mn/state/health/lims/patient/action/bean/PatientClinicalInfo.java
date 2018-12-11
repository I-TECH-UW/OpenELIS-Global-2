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
package us.mn.state.health.lims.patient.action.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import us.mn.state.health.lims.common.util.IdValuePair;

public class PatientClinicalInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tbExtraPulmanary;
	private String tbCerebral;
	private String tbMenigitis;
	private String tbPrurigol;
	private String tbDiarrhae;
	private String stdColonCancer;
	private String stdCandidiasis;
	private String stdKaposi;
	private String stdZona;
	private String stdOther;
	private String arvProphyaxixReceiving;
	private String arvProphyaxixType;
	private String arvTreatmentReceiving;
	private String arvTreatmentRemembered;
	private String arvTreatment1;
	private String arvTreatment2;
	private String arvTreatment3;
	private String arvTreatment4;
	private String cotrimoxazoleReceiving;
	private String cotrimoxazoleType;
	private String infectionExtraPulmanary;
	private String infectionCerebral;
	private String infectionMeningitis;
	private String infectionPrurigol;
	private String infectionOther;
	private String stdInfectionColon;
	private String stdInfectionCandidiasis;
	private String stdInfectionKaposi;
	private String stdInfectionZona;
	private String infectionUnderTreatment;
	private String weight;
	private String karnofskyScore;
	private List<IdValuePair> prophyaxixTypes;
	private List<IdValuePair> arvStages;
	

	public String getTbExtraPulmanary() {
		return tbExtraPulmanary;
	}
	public void setTbExtraPulmanary(String tbExtraPulmanary) {
		this.tbExtraPulmanary = tbExtraPulmanary;
	}
	public String getTbCerebral() {
		return tbCerebral;
	}
	public void setTbCerebral(String tbCerebral) {
		this.tbCerebral = tbCerebral;
	}
	public String getTbMenigitis() {
		return tbMenigitis;
	}
	public void setTbMenigitis(String tbMenigitis) {
		this.tbMenigitis = tbMenigitis;
	}
	public String getTbPrurigol() {
		return tbPrurigol;
	}
	public void setTbPrurigol(String tbPrurigol) {
		this.tbPrurigol = tbPrurigol;
	}
	public String getTbDiarrhae() {
		return tbDiarrhae;
	}
	public void setTbDiarrhae(String tbDiarrhae) {
		this.tbDiarrhae = tbDiarrhae;
	}
	public String getStdColonCancer() {
		return stdColonCancer;
	}
	public void setStdColonCancer(String stdColonCancer) {
		this.stdColonCancer = stdColonCancer;
	}
	public String getStdCandidiasis() {
		return stdCandidiasis;
	}
	public void setStdCandidiasis(String stdCandidiasis) {
		this.stdCandidiasis = stdCandidiasis;
	}
	public String getStdKaposi() {
		return stdKaposi;
	}
	public void setStdKaposi(String stdKaposi) {
		this.stdKaposi = stdKaposi;
	}
	public String getStdZona() {
		return stdZona;
	}
	public void setStdZona(String stdZona) {
		this.stdZona = stdZona;
	}
	public String getStdOther() {
		return stdOther;
	}
	public void setStdOther(String stdOther) {
		this.stdOther = stdOther;
	}
	public String getArvProphyaxixReceiving() {
		return arvProphyaxixReceiving;
	}
	public void setArvProphyaxixReceiving(String arvProphyaxixReceiving) {
		this.arvProphyaxixReceiving = arvProphyaxixReceiving;
	}
	public String getArvProphyaxixType() {
		return arvProphyaxixType;
	}
	public void setArvProphyaxixType(String arvProphyaxixType) {
		this.arvProphyaxixType = arvProphyaxixType;
	}
	public String getArvTreatmentReceiving() {
		return arvTreatmentReceiving;
	}
	public void setArvTreatmentReceiving(String arvTreatmentReceiving) {
		this.arvTreatmentReceiving = arvTreatmentReceiving;
	}
	public String getArvTreatmentRemembered() {
		return arvTreatmentRemembered;
	}
	public void setArvTreatmentRemembered(String arvTreatmentRemembered) {
		this.arvTreatmentRemembered = arvTreatmentRemembered;
	}
	public String getArvTreatment1() {
		return arvTreatment1;
	}
	public void setArvTreatment1(String arvTreatment1) {
		this.arvTreatment1 = arvTreatment1;
	}
	public String getArvTreatment2() {
		return arvTreatment2;
	}
	public void setArvTreatment2(String arvTreatment2) {
		this.arvTreatment2 = arvTreatment2;
	}
	public String getArvTreatment3() {
		return arvTreatment3;
	}
	public void setArvTreatment3(String arvTreatment3) {
		this.arvTreatment3 = arvTreatment3;
	}
	public String getArvTreatment4() {
		return arvTreatment4;
	}
	public void setArvTreatment4(String arvTreatment4) {
		this.arvTreatment4 = arvTreatment4;
	}
	public String getCotrimoxazoleReceiving() {
		return cotrimoxazoleReceiving;
	}
	public void setCotrimoxazoleReceiving(String cotrimoxazoleReceiving) {
		this.cotrimoxazoleReceiving = cotrimoxazoleReceiving;
	}
	public String getCotrimoxazoleType() {
		return cotrimoxazoleType;
	}
	public void setCotrimoxazoleType(String cotrimoxazoleType) {
		this.cotrimoxazoleType = cotrimoxazoleType;
	}
	
	public String getInfectionExtraPulmanary() {
		return infectionExtraPulmanary;
	}
	public void setInfectionExtraPulmanary(String infectionExtraPulmanary) {
		this.infectionExtraPulmanary = infectionExtraPulmanary;
	}
	public String getInfectionMeningitis() {
		return infectionMeningitis;
	}
	public void setInfectionMeningitis(String infectionMenigitis) {
		this.infectionMeningitis = infectionMenigitis;
	}
	public String getInfectionPrurigol() {
		return infectionPrurigol;
	}
	public void setInfectionPrurigol(String infectionPrurigol) {
		this.infectionPrurigol = infectionPrurigol;
	}
	public String getInfectionOther() {
		return infectionOther;
	}
	public void setInfectionOther(String infectionOther) {
		this.infectionOther = infectionOther;
	}

	public String getStdInfectionColon() {
		return stdInfectionColon;
	}
	public void setStdInfectionColon(String stdInfectionColon) {
		this.stdInfectionColon = stdInfectionColon;
	}
	public String getStdInfectionCandidiasis() {
		return stdInfectionCandidiasis;
	}
	public void setStdInfectionCandidiasis(String stdInfectionCandidiasis) {
		this.stdInfectionCandidiasis = stdInfectionCandidiasis;
	}
	public String getStdInfectionKaposi() {
		return stdInfectionKaposi;
	}
	public void setStdInfectionKaposi(String stdInfectionKaposi) {
		this.stdInfectionKaposi = stdInfectionKaposi;
	}
	public String getStdInfectionZona() {
		return stdInfectionZona;
	}
	public void setStdInfectionZona(String stdInfectionZona) {
		this.stdInfectionZona = stdInfectionZona;
	}
	public String getInfectionUnderTreatment() {
		return infectionUnderTreatment;
	}
	public void setInfectionUnderTreatment(String infectionUnderTreatment) {
		this.infectionUnderTreatment = infectionUnderTreatment;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getKarnofskyScore() {
		return karnofskyScore;
	}
	public void setKarnofskyScore(String karnofskyScore) {
		this.karnofskyScore = karnofskyScore;
	}
	public List<IdValuePair> getProphyaxixTypes() {
		if( prophyaxixTypes == null){
			prophyaxixTypes = new ArrayList<IdValuePair>();
		}
		return prophyaxixTypes;
	}
	public void setProphyaxixTypes(List<IdValuePair> prophyaxixTypes) {
		this.prophyaxixTypes = prophyaxixTypes;
	}
	public List<IdValuePair> getArvStages() {
		if( arvStages == null){
			arvStages = new ArrayList<IdValuePair>();
		}
		return arvStages;
	}
	public void setArvStages(List<IdValuePair> arvStages) {
		this.arvStages = arvStages;
	}
	public String getInfectionCerebral() {
		return infectionCerebral;
	}
	public void setInfectionCerebral(String infectionCerebral) {
		this.infectionCerebral = infectionCerebral;
	}	
}

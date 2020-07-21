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
package org.openelisglobal.analyzerimport.analyzerreaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.plugin.AnalyzerImporterPlugin;

public class AnalyzerLineReader extends AnalyzerReader {

    private static final String COBAS_INDICATOR = "COBAS INTEGRA400";
    private static final CharSequence SYSMEX_XT_INDICATOR = "XT-2000";
    private static final CharSequence FACSCALIBUR_INDICATOR = "MultiSET";
    private static final CharSequence EVOLIS_INTEGRAL_INDICATOR = "DBehring Enzygnost HIV integral II";
    private static final CharSequence EVOLIS_INTEGRAL_DBS_INDICATOR = "DBehring Enzygnost HIV integral IIDBS";
    private static final CharSequence EVOLIS_MUREX_INDICATOR = "Murex HIV 1_2";
    private static final CharSequence EVOLIS_MUREX_DBS_INDICATOR = "Murex HIV 1_2 DBS";
    private static final CharSequence COBAS_TAQMAN_INDICATOR = "HIV-HPS";
    private static final CharSequence COBAS_TAQMAN_INDICATOR_2 = "HIVHP2";
    private static final CharSequence COBAS_TAQMAN_INDICATOR_3 = "HI2CAP48";
    private static final CharSequence FACSCANTO_INDICATOR = "BD FACSCanto II";
    private static final CharSequence COBAS_TAQMAN_DBS_INDICATOR = "AMPLIPREP";
    private static final CharSequence COBAS_C311_INDICATOR = "R_Type1";
    private static final CharSequence MAURITUIS_INDICATOR = "R_Type1";

    private List<String> lines;
    private AnalyzerLineInserter inserter;
    private String error;
    private static ArrayList<AnalyzerImporterPlugin> analyzerPlugins = new ArrayList<>();

    public static void registerAnalyzerPlugin(AnalyzerImporterPlugin plugin) {
        analyzerPlugins.add(plugin);
    }

    @Override
    public boolean readStream(InputStreamReader reader) {
        error = null;
        inserter = null;
        lines = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(reader);

        try {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.add(line);
            }
        } catch (IOException e) {
            error = "Unable to read file";
            return false;
        }

        if (!lines.isEmpty()) {
            setInserter();
            if (inserter == null) {
                error = "Unable to understand which analyzer sent the file";
                return false;
            }
            return true;
        } else {
            error = "Empty file";
            return false;
        }

    }

    private void setInserter() {

        for (AnalyzerImporterPlugin plugin : analyzerPlugins) {
            if (plugin.isTargetAnalyzer(lines)) {
                inserter = plugin.getAnalyzerLineInserter();
                return;
            }
        }
        // This is going to be highly customized based on the characteristics of the
        // file
        // being sent

        if (lines.get(0).contains(COBAS_INDICATOR)) { // Cobas is found on the first line
            inserter = new CobasReader();
        } else if (lines.get(0).contains(EVOLIS_INTEGRAL_INDICATOR)
                || lines.get(0).contains(EVOLIS_INTEGRAL_DBS_INDICATOR) || lines.get(0).contains(EVOLIS_MUREX_INDICATOR)
                || lines.get(0).contains(EVOLIS_MUREX_DBS_INDICATOR)) { // Evolis is found on the first line
            inserter = new EvolisReader();
        } else if (lines.get(1) != null && lines.get(1).contains(SYSMEX_XT_INDICATOR)) { // Sysmex model found on data
                                                                                         // line
            inserter = new SysmexReader();
        } else if (lines.get(1) != null && lines.get(1).contains(FACSCALIBUR_INDICATOR)) { // Fascalibur software found
                                                                                           // on data line
            inserter = new FacscaliburReader();
        } else if (lines.get(1) != null
                && (lines.get(1).contains(COBAS_TAQMAN_INDICATOR) || lines.get(1).contains(COBAS_TAQMAN_INDICATOR_2)
                        || lines.get(1).contains(COBAS_TAQMAN_INDICATOR_3))) {
            inserter = new CobasTaqmanReader();
        } else if (lines.get(1) != null && lines.get(1).contains(FACSCANTO_INDICATOR)) {
            inserter = new FACSCantoReader();
        } else if (lines.get(1) != null && lines.get(1).toUpperCase().contains(COBAS_TAQMAN_DBS_INDICATOR)) {
            inserter = new CobasTaqmanDBSReader();
        } else {
            // we're into squishy territory. It could be be TAQMAN with no test on first row
            for (String line : lines) {
                if (line.contains(COBAS_TAQMAN_INDICATOR) || line.contains(COBAS_TAQMAN_INDICATOR_2)) {
                    inserter = new CobasTaqmanReader();
                }
            }
            if (inserter == null && lines.get(3).contains(COBAS_C311_INDICATOR)) {
                inserter = new CobasC311Reader();
            }
        }
    }

    /*
     * For testing purposes only
     */
    public void insertTestLines(List<String> testLines) {
        lines = testLines;
    }

    @Override
    public boolean insertAnalyzerData(String systemUserId) {
        if (inserter == null) {
            error = "Unable to understand which analyzer sent the file";
            return false;
        } else {
            boolean success = inserter.insert(lines, systemUserId);
            if (!success) {
                error = inserter.getError();
            }

            return success;
        }

    }

    @Override
    public String getError() {
        return error;
    }
}

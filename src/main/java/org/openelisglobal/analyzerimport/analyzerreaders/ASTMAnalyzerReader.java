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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.plugin.AnalyzerImporterPlugin;

import com.ibm.icu.text.CharsetDetector;

public class ASTMAnalyzerReader extends AnalyzerReader {

	private List<String> lines;
	private AnalyzerLineInserter inserter;
	private String error;
	private static ArrayList<AnalyzerImporterPlugin> analyzerPlugins = new ArrayList<>();

	public static void registerAnalyzerPlugin(AnalyzerImporterPlugin plugin) {
		analyzerPlugins.add(plugin);
	}

	@Override
	public boolean readStream(InputStream stream) {
		error = null;
		inserter = null;
		lines = new ArrayList<>();
		BufferedInputStream bis = new BufferedInputStream(stream);
		CharsetDetector detector = new CharsetDetector();
		try {
			detector.setText(bis);
			String charsetName = detector.detect().getName();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bis, charsetName));

			try {
				for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
					lines.add(line);
				}
			} catch (IOException e) {
				error = "Unable to read input stream";
				LogEvent.logError(e);
				return false;
			}
		} catch (IOException e) {
			error = "Unable to determine message encoding";
			LogEvent.logError(e);
			LogEvent.logError("an error occured detecting the encoding of the analyzer message", e);
			return false;
		}

		if (!lines.isEmpty()) {
			setInserter();
			if (inserter == null) {
				error = "Unable to understand which analyzer sent the message";
				return false;
			}
			return true;
		} else {
			error = "Empty message";
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
	}

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

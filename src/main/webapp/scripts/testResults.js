/*
 * resultBox -- the text box into which the results were entered
 * row -- the row number of the test results
 * lowerNormal -- the low end of the normal range
 * upperNormal -- the high end of the normal range
 * lowerAbnormal -- the low end of the range in which the test is considered valid
 * highAbnormal -- the high end of the range in which the test is considered valid
 * significantDigits -- how many significant digits are allowed
 *
 *  N.B.  If the upper and lower ranges of the normal and abnormal settings are the same then those ranges will
 *  be ignored.
 */
var outOfValidRangeMsg = null;

function validateNumberFormat( resultBox, row, significantDigits){
    if(resultBox.value.blank()){
        resultBox.title = "";
        resultBox.style.background = "#ffffff";
        $("valid_" + row).value = false;
        return true;
    }

    if( resultBox.value.trim() == "."){
        resultBox.value = "0.0";
    }

    if(isNaN(resultBox.value)){
        $("valid_" + row).value = false;
        return false;
    }

    if( !isNaN(significantDigits)){
        resultBox.value = round( resultBox.value, significantDigits);
    }

    return true;
}

function /*void*/ validateResults( resultBox, row, lowerNormal, upperNormal, lowerAbnormal, upperAbnormal, significantDigits, specialCase ){
    var isSpecialCase = specialCase == resultBox.value.toUpperCase();
    var validFormat = validateNumberFormat( resultBox,row, significantDigits);

    resultBox.style.borderColor = validFormat ? "" : "red";


	if( isSpecialCase ){
		resultBox.title = "";
		resultBox.value = resultBox.value.toUpperCase();
		resultBox.style.borderColor = "";
		resultBox.style.background = "#ffffff";
		$("valid_" + row).value = true;
		return;
	}

	if( lowerAbnormal != upperAbnormal &&
	   (resultBox.value < lowerAbnormal || resultBox.value > upperAbnormal) ){
		resultBox.style.background = "#ffa0a0";
		resultBox.title = "En dehors de la plage valide"; //FIXME: Uses hardcoded French labels. Switch to refer to resource file.
		$("valid_" + row).value = false;

		if( outOfValidRangeMsg ){
			alert( outOfValidRangeMsg);
		}
	}else if( lowerNormal != upperNormal &&
		(resultBox.value < lowerNormal || resultBox.value > upperNormal) ){
		resultBox.style.background = "#ffffa0";
		resultBox.title = "En dehors de la plage normale"; //FIXME: Uses hardcoded French labels. Switch to refer to resource file.
		$("valid_" + row).value = true;
	}else{
		resultBox.style.background = "#ffffff";
		resultBox.title = "";
		$("valid_" + row).value = true;
	}
}

function checkNumberFormat( resultBox, row, significantDigits){
    var validFormat = validateNumberFormat( resultBox, row, significantDigits);

    resultBox.style.borderColor = validFormat ? "" : "red";
}


if (!document.getElementById) {
    document.getElementById = function() { return null; }
}

function initMenu() {
//alert("I am in initMenu()");
    var menu = document.getElementById("menuList");
    if (menu != null) { //diane added this check for null (for pages that don't have menu at top: Add, Edit etc.)
    var items = menu.getElementsByTagName("li");
    for (var i=0; i < items.length; i++) {
        items[i].firstChild.myIndex = i;
        // retain any existing onclick handlers from menu-config.xml
        if (items[i].firstChild.onclick) {
            items[i].firstChild.onclick=function() { 
                eval(items[this.myIndex].firstChild.getAttribute("onclick"));
                setCookie("menuSelected", this.myIndex); 
                };
        } else {
            items[i].firstChild.onclick=function() { 
                setCookie("menuSelected", this.myIndex); 
            };
        }
    }
    activateMenu();
    }
}

function activateMenu() { 
//alert("I am in activateMenu()");
    var menu = document.getElementById("menuList");
    var items = menu.getElementsByTagName("li");
    
    //diane added this: check to see which menu (which of the menu tabs) we are on based on left side submenu
    var masterListsSubMenu = document.getElementById("masterListsSubMenu");
    var qaEventManagementSubMenu = document.getElementById("qaEventManagementSubMenu");
    var requestFormEntrySubMenu = document.getElementById("requestFormEntrySubMenu");
    var resultManagementSubMenu = document.getElementById("resultManagementSubMenu");
    var reportsSubMenu = document.getElementById("reportsSubMenu");
    var masterListsItems;
    var qaEventManagementItems;
    var requestFormEntryItems;
    var resultManagementItems;
    var reportsItems;
    if (masterListsSubMenu != null) {
      masterListsItems = masterListsSubMenu.getElementsByTagName("li");
    }
    if (qaEventManagementSubMenu != null) {
      qaEventManagementItems = qaEventManagementSubMenu.getElementsByTagName("li");
    }
    if (requestFormEntrySubMenu != null) {
      requestFormEntryItems = requestFormEntrySubMenu.getElementsByTagName("li");
    }
    if (resultManagementSubMenu != null) {
      resultManagementItems = resultManagementSubMenu.getElementsByTagName("li");
    }
    if (reportsSubMenu != null) {
      reportsItems = reportsSubMenu.getElementsByTagName("li");
    }
    //end of diane's addition
    
    var activeMenu;
    var found = 0;
    
 	
    for (var i=0; i < items.length; i++) {
        var url = items[i].firstChild.getAttribute("href");
        //alert("url " + url);
        //alert("current " + document.location.toString());
        var current = document.location.toString();
        
 
        
        if (current.indexOf(url) != -1) {
        //alert("Adding 1 to found");
            found++;
        } 
  
        
    }
    
 
     
    // more than one found, use cookies
    if (found > 1 && getCookie("menuSelected") != null) {  //diane added check for != null to fix bug
        var menuSelected = getCookie("menuSelected"); 
        //alert("menuSelected " + getCookie("menuSelected"));
        if (items[menuSelected].parentNode.className == "submenu") {
            items[menuSelected].firstChild.className="selected";
            items[menuSelected].parentNode.parentNode.className="selected";
        } else {            
            items[menuSelected].className+="selected";
        }
    } else {
        // only one found, match on URL
        var current = document.location.toString();
	
	var foundInUpperSubMenu = false; //diane added this
	var masterListsItem;//diane added this
    var qaEventManagementItem;//diane added this
	var requestFormEntryItem;//diane added this
    var resultManagementItem;//diane added this
    var reportsItem;//diane added this
	for (var i=0; i < items.length; i++) {
            var url = items[i].firstChild.getAttribute("href");
            //diane added this: store masterListsItem
            if (url.indexOf('MasterListsPage') != -1) {
                masterListsItem = items[i];
            }
            if (url.indexOf('QaEventManagementPage') != -1) {
                qaEventManagementItem = items[i];
            }
            if (url.indexOf('RequestFormEntryPage') != -1) {
                requestFormEntryItem = items[i];
            }
            if (url.indexOf('ResultManagementPage') != -1) {
                resultManagementItem = items[i];
            }
             if (url.indexOf('ReportsPage') != -1) {
                reportsItem = items[i];
            }
            //end of diane's addition
            if (current.indexOf(url) != -1) {
                if (items[i].parentNode.className == "submenu") {
                    items[i].firstChild.className="selected";
                    items[i].parentNode.parentNode.className="selected";
                    foundInUpperSubMenu = true; //diane added this
                } else {            
                    items[i].className+="selected";
                    foundInUpperSubMenu = true; //diane added this
                }
            } 
         }
            //diane added this: we need to now look at the additional (left-side) submenu
             if (!foundInUpperSubMenu && masterListsItems != null) {
	         masterListsItem.className+="selected";
             }
             if (!foundInUpperSubMenu && qaEventManagementItems != null) {
	         qaEventManagementItem.className+="selected";
             }
             if (!foundInUpperSubMenu && requestFormEntryItems != null) {
	         requestFormEntryItem.className+="selected";
             }
             if (!foundInUpperSubMenu && resultManagementItems != null) {
	         resultManagementItem.className+="selected";
             }
             if (!foundInUpperSubMenu && reportsItems != null) {
	         reportsItem.className+="selected";
             }
            //end of diane's addition
    }
}

// Select the menu that matches the URL when the page loads
window.onload=initMenu;


// =========================================================================
//                          Cookie functions 
// =========================================================================
/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "=" 
	var start = document.cookie.indexOf(prefix) 

	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length) 
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end) 
	return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}


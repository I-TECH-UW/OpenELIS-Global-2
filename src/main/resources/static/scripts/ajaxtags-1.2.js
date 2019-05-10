/**
 * Copyright 2005 Darren L. Spurgeon
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var AjaxJspTag = {
  Version: '1.2'
}

/**
 * Global Variables
 */
AJAX_DEFAULT_PARAMETER = "ajaxParameter";
AJAX_PORTLET_MAX = 1;
AJAX_PORTLET_MIN = 2;
AJAX_PORTLET_CLOSE = 3;
AJAX_CALLOUT_OVERLIB_DEFAULT = "STICKY,"
      + "CLOSECLICK,"
      + "DELAY,250,"
      + "TIMEOUT,5000,"
      + "VAUTO,WRAPMAX,240,"
      + "CSSCLASS,FGCLASS,'olfg',BGCLASS,'olbg',CGCLASS,'olcg',"
      + "CAPTIONFONTCLASS,'olcap',CLOSEFONTCLASS,'olclo',TEXTFONTCLASS,'oltxt'";


/**
 * Type Detection
 */
function isAlien(a) {
  return isObject(a) && typeof a.constructor != 'function';
}

function isArray(a) {
  return isObject(a) && a.constructor == Array;
}

function isBoolean(a) {
  return typeof a == 'boolean';
}

function isEmpty(o) {
  var i, v;
  if (isObject(o)) {
    for (i in o) {
      v = o[i];
      if (isUndefined(v) && isFunction(v)) {
        return false;
      }
    }
  }
  return true;
}

function isFunction(a) {
  return typeof a == 'function';
}

function isNull(a) {
  return typeof a == 'object' && !a;
}

function isNumber(a) {
  return typeof a == 'number' && isFinite(a);
}

function isObject(a) {
  return (a && typeof a == 'object') || isFunction(a);
}

function isString(a) {
  return typeof a == 'string';
}

function isUndefined(a) {
  return typeof a == 'undefined';
}


/**
 * Utility Functions
 */

function addOnLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}

/*
 * Extract querystring from a URL
 */
function extractQueryString(url) {
  var ret = (url.indexOf('?') >= 0 && url.indexOf('?') < (url.length-1))
    ? url.substr(url.indexOf('?')+1)
    : '';
  return ret;
}

/*
 * Trim the querystring from a URL
 */
function trimQueryString(url) {
  var ret = url.indexOf('?') >= 0
    ? url.substring(0, url.indexOf('?'))
    : url;
  return ret;
}

function delimitQueryString(qs) {
  var ret = '';
  if (qs.length > 0) {
    var params = qs.split('&');
    for (var i=0; i<params.length; i++) {
      if (i > 0) ret += ',';
      ret += params[i];
    }
  }
  return ret;
}

function buildParameterString(parameterList) {
  var returnString = '';
  var params = (parameterList || '').split(',');
  if (params != null) {
    for (var p=0; p<params.length; p++) {
      var pair = params[p].split('=');
      var key = pair[0];
      var val = pair[1];
      // if val is not null and it contains a match for a variable, then proceed
      if (!isEmpty(val) || isString(val)) {
        var varList = val.match( new RegExp("\\{[\\w\\.\\(\\)\\[\\]]*\\}", 'g') );
        if (!isNull(varList)) {
          var field = $(varList[0].substring(1, varList[0].length-1));
          switch (field.type) {
            case 'checkbox':
            case 'radio':
            case 'text':
            case 'textarea':
            case 'password':
            case 'hidden':
            case 'select-one':
              returnString += '&' + key + '=' + encodeURIComponent(field.value);
              break;
            case 'select-multiple':
              var fieldValue = $F(varList[0].substring(1, varList[0].length-1));
              for (var i=0; i<fieldValue.length; i++) {
                returnString += '&' + key + '=' + encodeURIComponent(fieldValue[i]);
              }
              break;
            default:
              returnString += '&' + key + '=' + encodeURIComponent(field.innerHTML);
              break;
          }
        } else {
          // just add back the pair
          returnString += '&' + key + '=' + encodeURIComponent(val);
        }
      }
    }
  }

  if (returnString.charAt(0) == '&') {
    returnString = returnString.substr(1);
  }
  return returnString;
}

function evalBoolean(value, defaultValue) {
  if (!isNull(value) && isString(value)) {
    return ("true" == value.toLowerCase() || "yes" == value.toLowerCase()) ? "true" : "false";
  } else {
    return defaultValue == true ? "true" : "false";
  }
}


/* ---------------------------------------------------------------------- */
/* Example File From "_JavaScript and DHTML Cookbook"
   Published by O'Reilly & Associates
   Copyright 2003 Danny Goodman
*/

// utility function to retrieve a future expiration date in proper format;
// pass three integer parameters for the number of days, hours,
// and minutes from now you want the cookie to expire; all three
// parameters required, so use zeros where appropriate
function getExpDate(days, hours, minutes) {
  var expDate = new Date();
  if (typeof days == "number" && typeof hours == "number" && typeof hours == "number") {
    expDate.setDate(expDate.getDate() + parseInt(days));
    expDate.setHours(expDate.getHours() + parseInt(hours));
    expDate.setMinutes(expDate.getMinutes() + parseInt(minutes));
    return expDate.toGMTString();
  }
}

// utility function called by getCookie()
function getCookieVal(offset) {
  var endstr = document.cookie.indexOf (";", offset);
  if (endstr == -1) {
    endstr = document.cookie.length;
  }
  return unescape(document.cookie.substring(offset, endstr));
}

// primary function to retrieve cookie by name
function getCookie(name) {
  var arg = name + "=";
  var alen = arg.length;
  var clen = document.cookie.length;
  var i = 0;
  while (i < clen) {
    var j = i + alen;
    if (document.cookie.substring(i, j) == arg) {
      return getCookieVal(j);
    }
    i = document.cookie.indexOf(" ", i) + 1;
    if (i == 0) break;
  }
  return null;
}

// store cookie value with optional details as needed
function setCookie(name, value, expires, path, domain, secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") +
    ((secure) ? "; secure" : "");
}

// remove the cookie by setting ancient expiration date
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}
/* ---------------------------------------------------------------------- */
/* End Copyright 2003 Danny Goodman */


/**
 * Response Parsers
 */
AbstractResponseParser = function() {};

ResponseTextParser = Class.create();
ResponseTextParser.prototype = Object.extend(new AbstractResponseParser(), {
  initialize: function() {
    this.type = "text";
  },

  load: function(request) {
    this.content = request.responseText;
    this.split();
  },

  split: function() {
    this.itemList = new Array();
    var lines = this.content.split('\n');
    for (var i=0; i<lines.length; i++) {
      this.itemList.push(lines[i].split(','));
    }
  }
});

ResponseXmlParser = Class.create();
ResponseXmlParser.prototype = Object.extend(new AbstractResponseParser(), {
  initialize: function() {
    this.type = "xml";
  },

  load: function(request) {
    this.content = request.responseXML;
    this.parse();
  },

  parse: function() {
    var root = this.content.documentElement;
    var responseNodes = root.getElementsByTagName("response");
    this.itemList = new Array();
    if (responseNodes.length > 0) {
      var responseNode = responseNodes[0];
      var itemNodes = responseNode.getElementsByTagName("item");
      for (var i=0; i<itemNodes.length; i++) {
        var nameNodes = itemNodes[i].getElementsByTagName("name");
        var valueNodes = itemNodes[i].getElementsByTagName("value");
        if (nameNodes.length > 0 && valueNodes.length > 0) {
          var name = nameNodes[0].firstChild.nodeValue;
          var value = valueNodes[0].firstChild.nodeValue;
          this.itemList.push(new Array(name, value));
        }
      }
    }
  }
});

ResponseHtmlParser = Class.create();
ResponseHtmlParser.prototype = Object.extend(new AbstractResponseParser(), {
  initialize: function() {
    this.type = "html";
  },

  load: function(request) {
    this.content = request.responseText;
  }
});

ResponseXmlToHtmlParser = Class.create();
ResponseXmlToHtmlParser.prototype = Object.extend(new AbstractResponseParser(), {
  initialize: function() {
    this.type = "xmltohtml";
  },

  load: function(request) {
    this.xml = request.responseXML;
    this.parse();
  },

  parse: function() {
    this.content = '';
    var root = this.xml.documentElement;
    var responseNodes = root.getElementsByTagName("response");
    if (responseNodes.length > 0) {
      var responseNode = responseNodes[0];
      var itemNodes = responseNode.getElementsByTagName("item");
      for (var i=0; i<itemNodes.length; i++) {
        var nameNodes = itemNodes[i].getElementsByTagName("name");
        var valueNodes = itemNodes[i].getElementsByTagName("value");
        if (nameNodes.length > 0 && valueNodes.length > 0) {
          var name = nameNodes[0].firstChild.nodeValue;
          var value = valueNodes[0].firstChild.nodeValue;
          this.content += '<h1>' + name + '</h1>';
          this.content += '<div>' + value + '</div>';
        }
      }
    }
  }
});

ResponseXmlToHtmlListParser = Class.create();
ResponseXmlToHtmlListParser.prototype = Object.extend(new AbstractResponseParser(), {
  initialize: function() {
    this.type = "xmltohtmllist";
  },

  load: function(request) {
    this.xml = request.responseXML;
    this.parse();
  },

  parse: function() {
    var ul = '<ul>';
    var root = this.xml.documentElement;

    var responseNodes = root.getElementsByTagName("response");
    if (responseNodes.length > 0) {
      var responseNode = responseNodes[0];
      var itemNodes = responseNode.getElementsByTagName("item");
      for (var i=0; i<itemNodes.length; i++) {
        var nameNodes = itemNodes[i].getElementsByTagName("name");
        var valueNodes = itemNodes[i].getElementsByTagName("value");
        if (nameNodes.length > 0 && valueNodes.length > 0) {
          var name = nameNodes[0].firstChild.nodeValue;
          var value = valueNodes[0].firstChild.nodeValue;
          ul += '<li id="' + value + '">' + name + '</li>';
        }
      }
    }
    ul += '</ul>';
    this.content = ul;
  }
});

ResponseXmlToHtmlLinkListParser = Class.create();
ResponseXmlToHtmlLinkListParser.prototype = Object.extend(new AbstractResponseParser(), {
  initialize: function() {
    this.type = "xmltohtmllinklist";
  },

  load: function(request) {
    this.xml = request.responseXML;
    this.collapsedClass = request.collapsedClass;
    this.treeClass = request.treeClass;
    this.nodeClass = request.nodeClass;
    this.expandedNodes = new Array();
    this.parse();
  },

  parse: function() {
    var ul = document.createElement('ul');
    ul.className = this.treeClass;
    var root = this.xml.documentElement;

    var responseNodes = root.getElementsByTagName("response");
    if (responseNodes.length > 0) {
      var responseNode = responseNodes[0];
      var itemNodes = responseNode.getElementsByTagName("item");
      for (var i=0; i<itemNodes.length; i++) {
        var nameNodes = itemNodes[i].getElementsByTagName("name");
        var valueNodes = itemNodes[i].getElementsByTagName("value");
        var urlNodes = itemNodes[i].getElementsByTagName("url");
        var collapsedNodes = itemNodes[i].getElementsByTagName("collapsed");
        
        if (nameNodes.length > 0 && valueNodes.length > 0) {
          var name = nameNodes[0].firstChild.nodeValue;
          var value = valueNodes[0].firstChild.nodeValue;
          var url = urlNodes[0].firstChild.nodeValue;
          var collapsed = collapsedNodes[0].firstChild.nodeValue.toLowerCase() == "true";
          
          var li = document.createElement('li');
          ul.appendChild(li);
          
          var img = document.createElement('img');
          li.appendChild(img);
          img.id = 'img_' + value;
          img.className = this.collapsedClass;
          
          var link  = document.createElement('a');
          li.appendChild(link);
          link.href = url;
          link.className = this.nodeClass;
          link.appendChild(document.createTextNode(name));
          
          var div = document.createElement('div');
          li.appendChild(div);
          div.id = value;
          
          if(!collapsed)
            this.expandedNodes.push(value);
        }
      }
    }
    this.content = ul;
  }
});


/**
 * AjaxTags
 */

AjaxJspTag.Base = function() {};
AjaxJspTag.Base.prototype = {

  resolveParameters: function() {
    // Strip URL of querystring and append it to parameters
    var qs = delimitQueryString(extractQueryString(this.url));
    if (this.options.parameters) {
      this.options.parameters += ',' + qs;
    } else {
      this.options.parameters = qs;
    }
    this.url = trimQueryString(this.url);
    if (this.options.parameters.length > 0
        && this.options.parameters.charAt(this.options.parameters.length-1) == ',') {
      this.options.parameters = this.options.parameters.substr(0,this.options.parameters.length-1);
    }
  }

}


/**
 * UPDATEFIELD TAG
 */
AjaxJspTag.UpdateField = Class.create();
AjaxJspTag.UpdateField.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.setListeners();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      eventType: options.eventType ? options.eventType : "click",
      parser: options.parser ? options.parser : new ResponseTextParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
  },

  setListeners: function() {
    eval("$(this.options.action).on"+this.options.eventType+" = this.execute.bindAsEventListener(this)");
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    // parse parameters and do replacements
    var params = buildParameterString(this.options.parameters);

    // parse targets
    var targetList = this.options.target.split(',');

    var obj = this; // required because 'this' conflict with Ajax.Request
    var setFunc = this.setField;
    var aj = new Ajax.Request(this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
        obj.options.parser.load(request);
        var results = obj.options.parser.itemList;
        obj.options.handler(request, {targets: targetList, items: results});
      },
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  },

  handler: function(request, options) {
    for (var i=0; i<options.targets.length && i<options.items.length; i++) {
      $(options.targets[i]).value = options.items[i][1];
    }
  }

});


/**
 * SELECT TAG
 */
AjaxJspTag.Select = Class.create();
AjaxJspTag.Select.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.setListeners();

    if (this.options.executeOnLoad == "true") {
      this.execute();
    }
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      eventType: options.eventType ? options.eventType : "change",
      parser: options.parser ? options.parser : new ResponseXmlParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
  },

  setListeners: function() {
    Event.observe($(this.options.source),
      this.options.eventType,
      this.execute.bindAsEventListener(this),
      false);
    eval("$(this.options.source).on"+this.options.eventType+" = function(){return false;};");
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    // parse parameters and do replacements
    var params = buildParameterString(this.options.parameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.Request(this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
        obj.options.parser.load(request);
        var results = obj.options.parser.itemList;
        obj.options.handler(request, {target: obj.options.target,
                                      items: results,
                                      defaultOptions: obj.options.defaultOptions});
      },
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  },

  handler: function(request, options) {
    // build an array of option values to be set as selected
    var defaultSelectedValues = (options.defaultOptions || '').split(",");

    $(options.target).options.length = 0;
    $(options.target).disabled = false;
    for (var i=0; i<options.items.length; i++) {
      var newOption = new Option(options.items[i][0], options.items[i][1]);
      //$(options.target).options[i] = new Option(options.items[i][0], options.items[i][1]);
      // set the option as selected if it is in the default list
      for (j=0; j<defaultSelectedValues.length && newOption.selected == false; j++) {
        if (defaultSelectedValues[j] == options.items[i][1]) {
          newOption.selected = true;
        }
      }
      $(options.target).options[i] = newOption;
    }
  }

});


/**
 * HTMLCONTENT TAG
 */
AjaxJspTag.HtmlContent = Class.create();
AjaxJspTag.HtmlContent.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.setListeners();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameterName: options.parameterName ? options.parameterName : AJAX_DEFAULT_PARAMETER,
      parameters: options.parameters || '',
      eventType: options.eventType ? options.eventType : "click",
      parser: options.parser ? options.parser : new ResponseHtmlParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
  },

  setListeners: function() {
    if (this.options.source) {
      eval("$(this.options.source).on"+this.options.eventType+" = this.execute.bindAsEventListener(this)");
    } else if (this.options.sourceClass) {
      var elementArray = document.getElementsByClassName(this.options.sourceClass);
      for (var i=0; i<elementArray.length; i++) {
        eval("elementArray[i].on"+this.options.eventType+" = this.execute.bindAsEventListener(this)");
      }
    }
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    // replace default parameter with value/content of source element selected
    var ajaxParameters = this.options.parameters;
    if (this.options.sourceClass) {
      var re = new RegExp("(\\{"+this.options.parameterName+"\\})", 'g');
      var elem = Event.element(e);
      if (elem.type) {
        ajaxParameters = ajaxParameters.replace(re, $F(elem));
      } else {
        ajaxParameters = ajaxParameters.replace(re, elem.innerHTML);
      }
    }

    // parse parameters and do replacements
    var params = buildParameterString(ajaxParameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.Updater(this.options.target, this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  }

});

/**
 * TREE TAG
 */
AjaxJspTag.Tree = Class.create();
AjaxJspTag.Tree.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.execute();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      eventType: options.eventType ? options.eventType : "click",
      parser: options.parser ? options.parser : new ResponseXmlToHtmlLinkListParser(),
      handler: options.handler ? options.handler : this.handler,
      collapsedClass: options.collapsedClass ? options.collapsedClass : "collapsedNode",
      expandedClass: options.expandedClass ? options.expandedClass : "expandedNode",
      treeClass: options.treeClass ? options.treeClass : "tree",
      nodeClass: options.nodeClass || ''
    }, options || {});
    this.calloutParameter = AJAX_DEFAULT_PARAMETER;
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    //if the node is expanded, just collapse it
    if(this.options.target != null) {
      var imgElem = $("img_" + this.options.target);
      if(imgElem != null) {
        var expanded = this.toggle(imgElem);
        if(!expanded) {
          $(this.options.target).innerHTML = "";
          return;
        }
      }
    }
    
    // parse parameters and do replacements
    var ajaxParameters = this.options.parameters || '';
    var re = new RegExp("(\\{"+this.calloutParameter+"\\})", 'g');
    ajaxParameters = ajaxParameters.replace(re, this.options.target);
    
    var params = buildParameterString(ajaxParameters);
      
    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.Request(this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
         obj.options.parser.load(Object.extend(request, {
                                                   collapsedClass: obj.options.collapsedClass,
                                                   treeClass:      obj.options.treeClass,
                                                   nodeClass:      obj.options.nodeClass}));
         var results = obj.options.parser.itemList;
         obj.options.handler(request, {target:    obj.options.target,
                                       parser:    obj.options.parser,
                                       eventType: obj.options.eventType,
                                       url:       obj.url});
      },
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  },
  
  toggle: function (e) {
    var expanded =  e.className == this.options.expandedClass;
    e.className =  expanded ? this.options.collapsedClass : this.options.expandedClass;
    return !expanded;
  },
  
  handler: function(request, options) {
    var parser = options.parser;
    var target = $(options.target);
    target.appendChild(parser.content);   
    var images = target.getElementsByTagName("img");
    for (var i=0; i<images.length; i++) {
      //get id
      var fullId = images[i].id;
      var id = fullId.substring(fullId.indexOf('_') + 1);
      var toggleFunction = "function() {toggleTreeNode('" +  id + "', '" + options.url + "', null);}";
      eval("images[i].on" + options.eventType + "=" + toggleFunction); 
    }
  
    //toggle the one that must be expanded
    var expandedNodes = parser.expandedNodes;
    for (var i=0; i<expandedNodes.length; i++) {
       toggleTreeNode(expandedNodes[i], options.url, null);
    }
  }

});

/**
 * TABPANEL TAG
 */
AjaxJspTag.TabPanel = Class.create();
AjaxJspTag.TabPanel.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.execute();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      eventType: options.eventType ? options.eventType : "click",
      parser: options.parser ? options.parser : new ResponseHtmlParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    // parse parameters and do replacements
    this.resolveParameters();
    var params = buildParameterString(this.options.parameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.Updater(this.options.target, this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
        var src;
        if (obj.options.source) {
          src = obj.options.source;
        } else {
          src = document.getElementsByClassName(obj.options.currentStyleClass,
                                                $(obj.options.panelId))[0];
        }
        obj.options.handler(request, {source: src,
                                      panelStyleId: obj.options.panelId,
                                      currentStyleClass: obj.options.currentStyleClass});
      },
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  },

  handler: function(request, options) {
    // find current anchor
    var cur = document.getElementsByClassName(options.currentStyleClass, $(options.panelStyleId));
    // remove class
    if(cur.length > 0)
        cur[0].className = '';
    // add class to selected tab
    options.source.className = options.currentStyleClass;
  }

});


/**
 * PORTLET TAG
 */
AjaxJspTag.Portlet = Class.create();
AjaxJspTag.Portlet.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.setListeners();
    if (this.options.executeOnLoad == "true") {
      this.execute();
    }
    if (this.preserveState) this.checkCookie();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      target: options.source+"Content",
      close: options.source+"Close",
      refresh: options.source+"Refresh",
      toggle: options.source+"Size",
      isMaximized: true,
      expireDays: options.expireDays || "0",
      expireHours: options.expireHours || "0",
      expireMinutes: options.expireMinutes || "0",
      executeOnLoad: evalBoolean(options.executeOnLoad, true),
      refreshPeriod: options.refreshPeriod || null,
      eventType: options.eventType ? options.eventType : "click",
      parser: options.parser ? options.parser : new ResponseHtmlParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});

    if (parseInt(this.options.expireDays) > 0
        || parseInt(this.options.expireHours) > 0
        || parseInt(this.options.expireMinutes) > 0) {
      this.preserveState = true;
      this.options.expireDate = getExpDate(
        parseInt(this.options.expireDays),
        parseInt(this.options.expireHours),
        parseInt(this.options.expireMinutes));
    }

    this.isAutoRefreshSet = false;
  },

  setListeners: function() {
    if (this.options.imageClose) {
      eval("$(this.options.close).on"+this.options.eventType+" = this.closePortlet.bindAsEventListener(this)");
    }
    if (this.options.imageRefresh) {
      eval("$(this.options.refresh).on"+this.options.eventType+" = this.refreshPortlet.bindAsEventListener(this)");
    }
    if (this.options.imageMaximize && this.options.imageMinimize) {
      eval("$(this.options.toggle).on"+this.options.eventType+" = this.togglePortlet.bindAsEventListener(this)");
    }
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    // parse parameters and do replacements
    this.resolveParameters();
    var params = buildParameterString(this.options.parameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    if (this.options.refreshPeriod && this.isAutoRefreshSet == false) {
      // periodic updater
      var freq = this.options.refreshPeriod;
      this.ajaxPeriodicalUpdater = new Ajax.PeriodicalUpdater(this.options.target, this.url, {
        asynchronous: true,
        method: 'get',
        evalScripts: true,
        parameters: params,
        frequency: freq,
        onFailure: function(request) {
          if (obj.options.errorFunction != null) obj.options.errorFunction(request);
        },
        onComplete: function(request) {},
        onSuccess: function(request) {
          if (obj.options.postFunction != null) obj.options.postFunction(request);
        }
      });

      this.isAutoRefreshSet = true;
    } else {
      // normal updater
      this.ajaxUpdater = new Ajax.Updater(this.options.target, this.url, {
        asynchronous: true,
        method: 'get',
        parameters: params,
        evalScripts: true,
        onFailure: function(request) {
          if (obj.options.errorFunction != null) obj.options.errorFunction(request);
        },
        onComplete: function(request) {
          if (obj.options.postFunction != null) obj.options.postFunction(request);
        }
      });
    }
  },

  checkCookie: function() {
    // Check cookie for save state
    var cVal = getCookie("AjaxJspTag.Portlet."+this.options.source);
    if (cVal != null) {
      if (cVal == AJAX_PORTLET_MIN) {
        this.togglePortlet();
      } else if (cVal == AJAX_PORTLET_CLOSE) {
        this.closePortlet();
      }
    }
  },

  stopAutoRefresh: function() {
    // stop auto-update if present
    if (this.ajaxPeriodicalUpdater != null
        && this.options.refreshPeriod
        && this.isAutoRefreshSet == true) {
      this.ajaxPeriodicalUpdater.stop();
    }
  },

  startAutoRefresh: function() {
    // stop auto-update if present
    if (this.ajaxPeriodicalUpdater != null && this.options.refreshPeriod) {
      this.ajaxPeriodicalUpdater.start();
    }
  },

  refreshPortlet: function(e) {
    // clear existing updater
    this.stopAutoRefresh();
    if (this.ajaxPeriodicalUpdater != null) {
      this.startAutoRefresh();
    } else {
      this.execute();
    }
  },

  closePortlet: function(e) {
    this.stopAutoRefresh();
    Element.remove(this.options.source);
    // Save state in cookie
    if (this.preserveState) {
      setCookie("AjaxJspTag.Portlet."+this.options.source,
        AJAX_PORTLET_CLOSE,
        this.options.expireDate);
    }
  },

  togglePortlet: function(e) {
    Element.toggle(this.options.target);
    if (this.options.isMaximized) {
      $(this.options.toggle).src = this.options.imageMaximize;
      this.stopAutoRefresh();
    } else {
      $(this.options.toggle).src = this.options.imageMinimize;
      this.startAutoRefresh();
    }
    this.options.isMaximized = !this.options.isMaximized;
    // Save state in cookie
    if (this.preserveState) {
      setCookie("AjaxJspTag.Portlet."+this.options.source,
        (this.options.isMaximized == true ? AJAX_PORTLET_MAX : AJAX_PORTLET_MIN),
        this.options.expireDate);
    }
  }

});


/**
 * AUTOCOMPLETE TAG
 */
Ajax.XmlToHtmlAutocompleter = Class.create();
Object.extend(Object.extend(Ajax.XmlToHtmlAutocompleter.prototype, Autocompleter.Base.prototype), {
  initialize: function(element, update, url, options) {
    this.baseInitialize(element, update, options);
    this.options.asynchronous  = true;
    this.options.onComplete    = this.onComplete.bind(this);
    this.options.defaultParams = this.options.parameters || null;
    this.url                   = url;
  },

  getUpdatedChoices: function() {
    entry = encodeURIComponent(this.options.paramName) + '=' + 
      encodeURIComponent(this.getToken());

    this.options.parameters = this.options.callback ?
      this.options.callback(this.element, entry) : entry;

    // parse parameters and do replacements
    var params = buildParameterString(this.options.defaultParams);
    if (!isEmpty(params) || (isString(params) && params.length > 0)) {
      this.options.parameters += '&' + params;
    }

    new Ajax.Request(this.url, this.options);
  },

  onComplete: function(request) {
    var parser = new ResponseXmlToHtmlListParser();
    parser.load(request);
    this.updateChoices(parser.content);
  }

});


AjaxJspTag.Autocomplete = Class.create();
AjaxJspTag.Autocomplete.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    $(this.options.source).setAttribute("autocomplete", "off");

    // create DIV
    new Insertion.After(this.options.source, '<div id="' + this.options.divElement + '" class="' + this.options.className + '"></div>');

    this.execute();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      divElement: "ajaxAuto_" + options.source,
      indicator: options.indicator || '',
      parameters: options.parameters || '',
      parser: options.parser ? options.parser : new ResponseHtmlParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.XmlToHtmlAutocompleter(
                     this.options.source,
                     this.options.divElement,
                     this.url, {minChars: obj.options.minimumCharacters,
                                tokens: obj.options.appendSeparator,
                                indicator: obj.options.indicator,
                                parameters: obj.options.parameters,
                                evalScripts: true,
                                afterUpdateElement: function(inputField, selectedItem) {
                                  obj.options.handler(null, {
                                    selectedItem: selectedItem,
                                    tokens: obj.options.appendSeparator,
                                    target: obj.options.target,
                                    inputField: inputField,
                                    postFunction: obj.options.postFunction
                                    }
                                  );
                                }
                               }
             );
  },

  handler: function(request, options) {
    if (options.target) {
      if (options.tokens) {
        if ($(options.target).value.length > 0) {
          $(options.target).value += options.tokens;
        }
        $(options.target).value += options.selectedItem.id;
      } else {
        $(options.target).value = options.selectedItem.id;
      }
    }

    if (options.postFunction != null) {
      //Disable onupdate event handler of input field
      //because, postFunction can change the content of
      //input field and get into eternal loop.
      var onupdateHandler = $(options.inputField).onupdate;
      $(options.inputField).onupdate = '';
      options.postFunction(request);
      //Enable onupdate event handler of input field
      $(options.inputField).onupdate = onupdateHandler;
    }
  }

});


/**
 * TOGGLE TAG
 */
AjaxJspTag.Toggle = Class.create();
AjaxJspTag.Toggle.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);

    // create message DIV
    if (this.options.messageClass) {
      this.messageContainer = new Insertion.Top($(this.options.source),
        '<div id="'+ this.options.source +'_message" class="' + this.options.messageClass +'"></div>');
    }

    this.setListeners();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || 'rating={ajaxParameter}',
      parser: options.parser ? options.parser : new ResponseTextParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
    this.ratingParameter = AJAX_DEFAULT_PARAMETER;
  },

  setListeners: function() {
    // attach events to anchors
    var elements = $(this.options.source).getElementsByTagName('a');
    for (var j=0; j<elements.length; j++) {
      elements[j].onmouseover = this.raterMouseOver.bindAsEventListener(this);
      elements[j].onmouseout = this.raterMouseOut.bindAsEventListener(this);
      elements[j].onclick = this.raterClick.bindAsEventListener(this);
    }
  },

  getCurrentRating: function(list) {
    var selectedIndex = -1;
    for (var i=0; i<list.length; i++) {
      if (Element.hasClassName(list[i], this.options.selectedClass)) {
        selectedIndex = i;
      }
    }
    return selectedIndex;
  },

  getCurrentIndex: function(list, elem) {
    var currentIndex = 0;
    for (var i=0; i<list.length; i++) {
      if (elem == list[i]) {
        currentIndex = i;
      }
    }
    return currentIndex;
  },

  raterMouseOver: function (e) {
    // get containing div
    var container = Event.findElement(e, 'div');

    // get list of all anchors
    var elements = container.getElementsByTagName('a');

    // find the current rating
    var selectedIndex = this.getCurrentRating(elements);

    // find the index of the 'hovered' element
    var currentIndex = this.getCurrentIndex(elements, Event.element(e));

    // set message
    if (this.options.messageClass) {
      $(container.id+'_message').innerHTML = Event.element(e).title;
    }

    // iterate over each anchor and apply styles
    for (var i=0; i<elements.length; i++) {
      if (selectedIndex > -1) {
        if (i <= selectedIndex && i <= currentIndex)
          Element.addClassName(elements[i], this.options.selectedOverClass);
        else if (i <= selectedIndex && i > currentIndex)
          Element.addClassName(elements[i], this.options.selectedLessClass);
        else if (i > selectedIndex && i <= currentIndex)
          Element.addClassName(elements[i], this.options.overClass);
      } else {
        if (i <= currentIndex) Element.addClassName(elements[i], this.options.overClass);
      }
    }
  },

  raterMouseOut: function (e) {
    // get containing div
    var container = Event.findElement(e, 'div');

    // get list of all anchors
    var elements = container.getElementsByTagName('a');

    // clear message
    if (this.options.messageClass) {
      $(container.id+'_message').innerHTML = '';
    }

    // iterate over each anchor and apply styles
    for (var i=0; i<elements.length; i++) {
      Element.removeClassName(elements[i], this.options.selectedOverClass);
      Element.removeClassName(elements[i], this.options.selectedLessClass);
      Element.removeClassName(elements[i], this.options.overClass);
    }
  },

  raterClick: function (e) {
    // get containing div
    var container = Event.findElement(e, 'div');

    // get list of all anchors
    var elements = container.getElementsByTagName('a');

    // find the index of the 'hovered' element
    var currentIndex = this.getCurrentIndex(elements, Event.element(e));

    // update styles
    for (var i=0; i<elements.length; i++) {
      Element.removeClassName(elements[i], this.options.selectedOverClass);
      Element.removeClassName(elements[i], this.options.selectedLessClass);
      Element.removeClassName(elements[i], this.options.overClass);
      if (i <= currentIndex) {
        if (Element.hasClassName(container, 'onoff')
              && Element.hasClassName(elements[i], this.options.selectedClass)) {
          Element.removeClassName(elements[i], this.options.selectedClass);
        } else {
          Element.addClassName(elements[i], this.options.selectedClass);
        }
      } else if (i > currentIndex) {
        Element.removeClassName(elements[i], this.options.selectedClass);
      }
    }

    // send AJAX
    var ratingToSend = elements[currentIndex].title;
    if (Element.hasClassName(container, 'onoff')) {
      // send opposite of what was selected
      var ratings = this.options.ratings.split(',');
      if (ratings[0] == ratingToSend) ratingToSend = ratings[1];
      else ratingToSend = ratings[0];
    }
    this.execute(ratingToSend);

    // set field (if defined)
    if (this.options.state) {
      $(this.options.state).value = ratingToSend;
    }
  },

  execute: function(ratingValue) {
    if (this.options.preFunction != null) this.options.preFunction();

    // parse parameters and do replacements
    var ajaxParameters = this.options.parameters || '';
    var re = new RegExp("(\\{"+this.ratingParameter+"\\})", 'g');
    ajaxParameters = ajaxParameters.replace(re, ratingValue);
    var params = buildParameterString(ajaxParameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    var toggleStateFunc = this.getToggleStateValue;
    var aj = new Ajax.Request(this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
        obj.options.parser.load(request);
        var results = obj.options.parser.itemList;
        obj.options.handler(request, {items: results});
      },
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  },

  handler: function(request, options) {
    // TODO: anything?
  },

  getToggleStateValue: function(name, results) {
    for (var i=0; i<results.length; i++) {
      if (results[i][0] == name) return results[i][1];
    }
    return "";
  }

});


/**
 * CALLOUT TAG
 */
AjaxJspTag.Callout = Class.create();
AjaxJspTag.Callout.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.setListeners();
  },

  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      overlib: options.overlib || AJAX_CALLOUT_OVERLIB_DEFAULT,
      parser: options.parser ? options.parser : new ResponseXmlToHtmlParser(),
      handler: options.handler ? options.handler : this.handler,
      openEvent: options.openEvent ? options.openEvent : "mouseover",
      closeEvent: options.closeEvent ? options.closeEvent : "mouseout"
    }, options || {});
    this.calloutParameter = AJAX_DEFAULT_PARAMETER;
  },

  setListeners: function() {
    if (this.options.sourceClass) {
      var elemList = document.getElementsByClassName(this.options.sourceClass);
      for (var i=0; i<elemList.length; i++) {
        eval("elemList[i].on"+this.options.openEvent+" = this.calloutOpen.bindAsEventListener(this)");
        eval("elemList[i].on"+this.options.closeEvent+" = this.calloutClose.bindAsEventListener(this)");
      }
    }
  },

  calloutOpen: function(e) {
    this.execute(e);
  },

  calloutClose: function(e) {
    nd();
  },

  execute: function(e) {
    if (this.options.preFunction != null) this.options.preFunction();

    // parse parameters and do replacements
    var ajaxParameters = this.options.parameters || '';
    var re = new RegExp("(\\{"+this.calloutParameter+"\\})", 'g');
    var elem = Event.element(e);
    if (elem.type) {
      ajaxParameters = ajaxParameters.replace(re, $F(elem));
    } else {
      ajaxParameters = ajaxParameters.replace(re, elem.innerHTML);
    }
    var params = buildParameterString(ajaxParameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.Request(this.url, {
      asynchronous: true,
      method: 'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
        obj.options.parser.load(request);
        obj.options.handler(obj.options.parser.content, {title: obj.options.title,
                                                         overlib: obj.options.overlib});
      },
      onFailure: function(request) {
        if (obj.options.errorFunction != null) obj.options.errorFunction(request);
      },
      onComplete: function(request) {
        if (obj.options.postFunction != null) obj.options.postFunction(request);
      }
    });
  },

  handler: function(content, options) {
    if (options.overlib) {
      if (options.title) {
        return eval("overlib(content,CAPTION,options.title,"+options.overlib+")");
      } else {
        return eval("overlib(content,"+options.overlib+")");
      }
    } else {
      if (options.title) {
        return overlib(content,CAPTION,options.title);
      } else {
        return overlib(content);
      }
    }
  }

});

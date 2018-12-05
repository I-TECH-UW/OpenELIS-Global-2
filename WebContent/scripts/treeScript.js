/*
 * Javascript which handles tree display and navigation
 */


var trees             = {};       // a hash table which represents the trees

var delimeter = "\u0007";           // a bell delimeter
var pathDelimeter = ".";            // the delimeter for the node path.
var currentNode = null;             // currently hovered node
var popupNode = null;               // the node on which a popup is displayed
var popup2show = null;              // the popup to show
var timerId = null;                 // the timer for the popups
var timeOutvalue = 500;             // the timeout value in milliseconds
var popupX = 0;                     // the global x co-ordinate to display the popup
var popupY = 0;                     // the global y co-ordinate to display the popup

var mouseOverColor = "#E0E0E0";     // the mouseover color
//bugzilla 1742
var linkBgColor = "#f7f7e5";        // background color of the popup
var popupTextColor = "#000000";     // the text of the popup
var popupRolloverColor = '#347EDA'; // roll over color of the popup
var selectedNodeColor = '#C0DBF7';  // the color of the selected node
var selectedNodeFontWeight = "bold";// the font weight of the selected node
var selectedNodeStyle = "background-color: " + selectedNodeColor + "; font-weight: " + selectedNodeFontWeight + ";";         // the style for the selected node

var MOVE_FORWARD = "1";             // paginated node constant indicating user has hit next - same as in TreeStateManager
var MOVE_BACK = "2";                // paginated node constant indicating user has hit prev - same as in TreeStateManager

var textNode = "node";              // i18n Text for node
var textCollapse = "Collapse";      // i18n Text
var textCollapsed = "Collapsed";    // i18n Text
var textExpand = "Expand";          // i18n Text
var textExpanded = "Expanded";      // i18n Text
var textLevel = "Level";            // i18n Text
var textOf = "of";                  // i18n Text
var textSelected = "Selected";      // i18n Text
var textNext = "Next";              // i18n Text
var textPrev = "Prev";              // i18n Text

var treeFunctions = new TreeFunctions();  // the tree function object

/**
 * Represents a tree object in javascript
 */
function Tree(name, rootNode, baseDir, isLocal, selectedNodePath)
{
    this.name = name;
    this.baseDir = baseDir;
    this.isLocal = isLocal;
    this.rootNode = rootNode;
    this.icons = new Array(6);              // the tree icons
    this.openNodes = {};                    // used to store all open nodes (if isLocal is true).
    // each tree can optionally have one highlighted node
    this.highlightNodePath = treeFunctions.escapeQuotes(selectedNodePath);
    this.popups = {};                       // all the defined popups for the tree.
    this.recursedNodes = new Array();       // temporary variable used in tree generation

    this.preLoadIcons =
       function preLoadIcons()
       {
	      this.icons[0] = new Image();
          this.icons[0].src = baseDir + "plus.gif";
          this.icons[1] = new Image();
          this.icons[1].src = baseDir + "plusbottom.gif";
          this.icons[2] = new Image();
          this.icons[2].src = baseDir + "minus.gif";
          this.icons[3] = new Image();
          this.icons[3].src = baseDir + "minusbottom.gif";
          this.icons[4] = new Image();
          this.icons[4].src = baseDir + "folder.gif";
          this.icons[5] = new Image();
          this.icons[5].src = baseDir + "folderopen.gif";
       }

    /**
     * Recursive function which finds a given node in the tree.
     * @param parent The starting point of the search. If not specified, default is the root node.
     * @param nodePath The path to search.
     * @return node The obtained node at the given path, or null if not found
     */
    this.getNode =
       function getNode(parent, nodePath)
       {
           if(parent == null) parent = this.rootNode;

           if(parent.path == nodePath) {
              return parent;
           }

           for(var i=0; i<parent.children.length; i++)
           {
              var n = this.getNode(parent.children[i], nodePath);
              if(n != null)
                 return n;
           }

           return null;
       }

    /**
     * Recursive function which finds a given node in the tree.
     * @param parent The starting point of the search. Should be null when called.
     * @param nodePath The path (constructed by using display names) to search.
     * @param parentDisplayPath The display path of the parent. Should be set to null when called.
     * @return node The obtained node at the given path, or null if not found
     */
    this.getNodeByDisplayPath =
       function getNodeByDisplayPath(parent, nodePath, parentDisplayPath)
       {
           if(parent == null)
              parent = this.rootNode;

           var pd = null;
           if(parent.displayName == undefined)
              pd = parent.name;
           else
              pd = parent.displayName;

           if(parentDisplayPath == null)  // only the first time should it be null
              parentDisplayPath = pd;

           if(parentDisplayPath == nodePath) // things match up, so return
              return parent;

           for(var i=0; i<parent.children.length; i++)
           {
              // set the display name path
              if(parent.children[i].displayName == undefined)
                 pd = parent.children[i].name;
              else
                 pd = parent.children[i].displayName;

              var pdp = parentDisplayPath + pathDelimeter + pd;

              var n = getNodeByDisplayPath(parent.children[i], nodePath, pdp);
              if(n != null)
                 return n;
           }

           return null;
       }

	/*
	 * This method returns a boolean indicating whether the nodePath is present or not in that particular tree.
	 * It takes in a nodePath, separated by the pathSeparator character. (The default value is ".", but it is generally
	 * over-ridden by most of the applications to be the "\t" character. Note that this method will
	 * return true only if the node is present in the hierarchy of the tree, it does not have to be visible.
	 *
	 */
	this.isNodePathPresent =
	   function isNodePathPresent(nodePath)
       {
		   var node = this.getNode(this.rootNode, nodePath);

		   if(node == null)
		      return false;
		   else
		      return true;
	    }

	/*
	 * This method returns a boolean indicating whether the displayNodePath is present or not in that particular tree.
	 * It takes in a nodePath, separated by the pathSeparator character. (The default value is ".", but it is generally
	 * over-ridden by most of the applications to be the "\t" character. Note that this method will
	 * return true only if the node is present in the hierarchy of the tree, it does not have to be visible.
	 *
	 */
	this.isDisplayNodePathPresent =
	   function isDisplayNodePathPresent(nodePath)
       {
		   var node = this.getNodeByDisplayPath(null, nodePath, null);

		   if(node == null)
		      return false;
		   else
		      return true;
	    }

	/**
	 *  Programmatically Expand or collapse a node identified by its nodePath - Used by the HTTPUnit test classes
	 *  This will return an exception if the node identified by node path is not found, or if the node does not have
	 *  expand/collapse action associated with it (i.e. it is a leaf node)
	 */
    this.expandCollapseNodeByPath =
       function expandCollapseNodeByPath(nodePath)
	   {
		   var node = this.getNode(this.rootNode, nodePath);

	       if(node == null)
	          throw "Node path " + nodePath + " does not exist";

	       // if the parent of this node is null (it is a root node) or if the hasChildren attribute is false,
	       // throw an exception
	       if(node.parent == null || !node.hasChildren)
	       	  throw "Node identified by path " + nodePath + " does not have any expand, collapse action";

		   // used only in local mode.
	       if(this.isLocal)
	           throw "ExpandCollapseNodeByPath works only for non-local trees";
	       else
	           return treeFunctions.ocClientServerTest(this.name, nodePath);
       }

	/**
	 *  Programmatically Expand or collapse a node identified by its displayNodePath - Used by the HTTPUnit test classes
	 *  This will return an exception if the node identified by node path is not found, or if the node does not have
	 *  expand/collapse action associated with it (i.e. it is a leaf node)
	 */
    this.expandCollapseNodeByDisplayPath =
       function expandCollapseNodeByPath(nodePath)
	   {
		   var node = this.getNodeByDisplayPath(null, nodePath, null);

           return this.expandCollapseNodeByPath(node.path);
       }


    // get a particular popup for the tree
    this.getPopup =
       function getPopup(popupName)
       {
          // if it has already been created, return it.
          var popup = document.getElementById(this.name+"_popups_"+popupName);
          if(popup != null)
             return popup;

          // let us see if the popup is in the popups hashtable
          popup = this.popups[popupName]
          if(popup == null)
             return null;   // does not exist, so just return a null.

          var popupPlaceholder = document.getElementById(this.name+'popupPlaceholder');

          // create the table for the popup
          var table = document.createElement('table');
          table.setAttribute('id', this.name+"_popups_"+popupName);
          table.setAttribute('cellpadding', '1');
          table.setAttribute('cellspacing', '0');
          table.setAttribute('class', 'tree_popup');
          table.setAttribute('onMouseOver', 'javascript:treeFunctions.popupCancelhide("'+this.name+'");');
          table.setAttribute('onMouseOut', 'javascript:treeFunctions.popupHide("'+this.name+'");');

          var tbody = document.createElement('tbody');

          for(var i=0; i<popup.length; i++)
          {
             treePopup = popup[i];
             row = document.createElement('tr');
             column = document.createElement('td');
             columnClass = (i == popup.length-1 ? 'tree_popup_td_bottom' : 'tree_popup_td');

             column.setAttribute('nowrap', true);
             column.setAttribute('class', columnClass);
             column.setAttribute('onMouseOver', 'javascript:treeFunctions.popupRollOver(this);');
             column.setAttribute('onMouseOut', 'javascript:treeFunctions.popupRollOut(this);');
             column.setAttribute('onClick', treePopup.url);
			 column.setAttribute('valign', 'top');

             // the popup has an image associated with it. let us show it.
			 var elem = document.createTextNode(' ');
             if(treePopup.imageSrc != null && treePopup.imageSrc != undefined)
			 {
                 elem = document.createElement('img');
				 elem.setAttribute('src', treePopup.imageSrc);
			 }

             if(document.all)
                column.setAttribute('style', 'cursor:hand;');
             else
                column.setAttribute('style', 'cursor:pointer;');

             spanE = document.createElement('span');
			 spanE.setAttribute('class', 'tree_popup_text');
             text = document.createTextNode(' ' + treePopup.text);
			 spanE.appendChild(text)

             column.appendChild(elem);
             column.appendChild(spanE);

             row.appendChild(column);
             tbody.appendChild(row);
          }
          table.appendChild(tbody);

          if(document.all) // bug in IE requires this code.....
             popupPlaceholder.innerHTML += table.outerHTML;
          else
             popupPlaceholder.appendChild(table);

          return table;
       }

    /**
     * Render the given tree
     *
     */
    this.renderTree =
       function renderTree()
       {
           this.preLoadIcons();

           var node = this.rootNode;

           if(node == null)
              return; // nothing can be displayed in the tree.

           // if setLocal=true, load all the openNodes from cookie
           if(this.isLocal)
              treeFunctions.retrieveOpenNodesFromCookie(name);

           treeFunctions.writeTreeNode(node, node.iconOpen, (tree.highlightNodePath == node.name ? true : false), node.name);
           document.write('<br />');

           this.addNode(node);

           //bugzilla 1803 attempt to get highlighted node from cookie
           if (tree.highlightNodePath == null) {
              if (getCookie(name+"highlightNodePath") != null) {
                 tree.highlightNodePath = getCookie(name+"highlightNodePath");
              }
           }
           //end bugzilla 1803

           if(tree.highlightNodePath != null)
              setTimeout("treeFunctions.scrollToElement('"+name+"','"+ tree.highlightNodePath +"')", 300);

       }

    // recursive function to keep adding nodes to tree
    this.addNode =
       function addNode(parentNode)
       {
          for (var n=0; n<parentNode.children.length; n++)
          {
             var node = parentNode.children[n];  // find the node

             var id = node.treeName + delimeter + node.ePath;
             var ls = (n==parentNode.children.length-1 ? true : false);
             var ino = node.isExpanded;
             var ish = (this.highlightNodePath == node.ePath);

             // then let us see if that node exists in the openNodes hashTable.
             if(isLocal)
             {
                if(tree.openNodes[node.path] != null) {
	                ino = true;
	                node.isExpanded = true;
	            } else {
	                ino = false;
	                node.isExpanded = false;
	            }
             }

             // Write out line & empty icons
             for (g=0; g<this.recursedNodes.length; g++)
             {
               if (this.recursedNodes[g] == 1)
                 document.write('<img alt="" border=0 src="' + this.baseDir + 'line.gif" align="absbottom" />');
               else
                 document.write('<img alt="" border=0 src="' + this.baseDir + 'empty.gif" align="absbottom" />');
             }

             // put in array line & empty icons

             if (ls)
               this.recursedNodes.push(0);
             else
               this.recursedNodes.push(1);

             // Write out join icons
             if (node.hasChildren)
             {
                 // generate links based on local variable or not
                 var callFunction = '';
                 if(this.isLocal == true)
                 {
                   callFunction ="treeFunctions.ocLocal('"+node.treeName+"','";
                 } else {
                   callFunction ="treeFunctions.oc('"+node.treeName+"','";
                 }

                 var titleText = " " + textNode + " " + node.name;
                 var temp = '';

                 if (ls)
                   temp = 'bottom.gif';
                 else
                   temp = '.gif';

                 document.write('<a href="javascript:' + callFunction + node.ePath + '\',1);"><img border=0 id="join' + id + '" src="');

                 if (ino) {
                   document.write(this.baseDir + 'minus');
                   titleText = textCollapse + titleText;
                 } else {
                   document.write(this.baseDir + 'plus');
                   titleText = textExpand + titleText;
                 }

                 document.write(temp+'" align="absbottom" title="' + titleText + '" alt="' + titleText + '" /></a>');

             } else {
                 if (ls)
                   document.write('<img alt="" border=0 src="' + this.baseDir + 'join.gif" align="absbottom" />');
                 else
                   document.write('<img alt="" border=0 src="' + this.baseDir + 'joinbottom.gif" align="absbottom" />');
             }

             // Write out folder & page icons
             var imgsrc = "";
             if (node.hasChildren)
             {
               if(ino)
                  imgsrc = node.iconOpen;
               else
                  imgsrc = node.iconClosed;
             } else {
               imgsrc = node.iconClosed;
             }

             // write out the node alternate information for accessibility purpose
             var altInfo = textLevel + " " + this.recursedNodes.length + ", ";
             if(node.hasChildren)
             {
                if(ino)
                  altInfo += textExpanded + ", ";
                else
                  altInfo += textCollapsed + ", ";
             }
             altInfo += node.name + ", " + (n+1) + " " + textOf + " " + parentNode.children.length;

             if(ish)
               altInfo += ", " + textSelected;

             treeFunctions.writeTreeNode(node, imgsrc, ish, altInfo);
             document.write("<br />");

             // If node has children go deeper and write divs
             if (node.hasChildren)
             {
                document.write('<div id="div' + id + '"');
                if (!ino) document.write(' style="display: none;"');
                document.write('>');
                if(ino || isLocal)
                   this.addNode(node);

                document.write('</div>');
             }

             // remove last line or empty icon
             this.recursedNodes.pop();
	      } // for
	   } // end function

    // add the tree to the hashtable
    trees[name] = this;
}

/**
 * Javascript representation of the TreeNode object. Each tree can contain one or more TreeNode objects.
 * TreeNode objects cannot be share across trees.
 *
 * @param path            : The path of the tree node
 * @param name            : The name of the node
 * @param nodeAction      : The action (url / formName) associated with the node
 * @param iconOpen        : The icon to be shown when node is open
 * @param iconCLosed      : The icon to be shown when node is closed
 * @param popupText       : The mouse over text displayed when mouse is hovered over the node icon
 * @param popupId         : The id of the TreePopup associated with this node.
 * @param isExpanded      : boolean indicating whether or not this node is expanded
 * @param hasChildren     : boolean indicating whether or not this node has children or not
 * @param treeName        : The name of the tree this node is associated with
 * @param preSubmitAction : The presubmit action associated with the node.
 */
function JSTreeNode(name, nodeAction, iconOpen, iconClosed, popupText, popupId, isExpanded, hasChildren, treeName, preSubmitAction)
{
    this.path = name;
    this.name = name;                      this.ePath = treeFunctions.escapeQuotes(this.path);
    this.nodeAction = nodeAction;

    if(nodeAction != null && nodeAction != undefined && nodeAction.indexOf("http") == 0)
      this.isFormSubmit = false;
    else
      this.isFormSubmit = true;

    this.action = "javascript:treeFunctions.selectNodeAction('"+treeName + "','"+ this.ePath + "');";
    this.iconOpen = iconOpen;
    this.iconClosed = iconClosed;
    this.popupText = popupText;
    this.popupId = popupId;
    this.parent = null;
    this.isExpanded = isExpanded;
    this.hasChildren = hasChildren;
    this.treeName = treeName;
    this.data = null;

	if(preSubmitAction != undefined && preSubmitAction != null)
	{
	   this.preSubmitScript = preSubmitAction;
	}

    this.children = new Array();

    this.addChild =
       function addChild(name, nodeAction, iconOpen, iconClosed, popupText, popupId, isExpanded, hasChildren, treeName, preSubmitAction)
       {
          var child = new JSTreeNode(name, nodeAction, iconOpen, iconClosed, popupText, popupId, isExpanded, hasChildren, treeName, preSubmitAction);

          child.parent = this;
          child.path = this.path + pathDelimeter + name;
          child.ePath = treeFunctions.escapeQuotes(child.path);
          child.action = "javascript:treeFunctions.selectNodeAction('"+treeName + "','"+ child.ePath + "');";

          this.children.push(child);

          return child;
       }

    // show some text within the placeholder of the node
    this.showPlaceholder =
        function showPlaceholder(str)
    {
		   var id = this.treeName + delimeter + this.ePath;
		   if(str == null)
		     treeFunctions.getPlaceholderElement(id).innerHTML = "";
		   else
		     treeFunctions.getPlaceholderElement(id).innerHTML = str;
    }
}

/**
 * The TreePopup Object which holds each popup item. These items are then copied into the popup array
 *
 * @param text The text to be displayed on the popup
 * @param url The url to be executed for the popup
 * @param imageSrc The imageSrc for the popup
 */
function JSTreePopup(text, url, imageSrc)
{
   this.text = text;
   this.url = url;
   this.imageSrc = imageSrc;
}

/**
 * A wrapper class to contain all functions of the tree
 */
function TreeFunctions()
{

	/**
	 * Write an invidividual node out using document.write. The reason it is separated is so that
	 * if someone wants to use treenodes other than a Tree (ie in a list) and still get the same
	 * functionality, they can just call this method
	 *
	 * @param node      The TreeNode object to be written
	 * @param icon      Optional - The icon source to be displayed along with the node. If icon is null, no icon
	 *                  will be displayed next to the node.
	 * @param highlight Optional parameter, set to true if we want the node to be highlighted
	 * @param accessibility  The alternate text to be used for accessibility. If not specified, a blank will be used
	 */
	this.writeTreeNode =
		function writeTreeNode(node, icon, highlighted, accessibility )
		{
		   var id = node.treeName + delimeter + node.ePath;
		   var output = '';

		   // highlight the open node
		   if(highlighted) {
			  popupNode = node;  // if it is highlighted, the popup node is going to be this guy.
		   }

		   var mouseOverString   = ' onMouseOver="javascript:treeFunctions.mouseOverCall(\''+ node.treeName + '\',\'' + node.ePath + '\',\'' +  node.popupId + '\');"';
		   var mouseOutString    = ' onMouseOut="javascript:treeFunctions.mouseOutCall(\''+ node.treeName + '\',\'' + node.ePath + '\');"  ';
		   var contextMenuString = ' oncontextmenu="return treeFunctions.startDisplayPopup(\''+ node.treeName + '\',\'' + node.ePath +'\',\'' +  node.popupId + '\', event);"  ';
		   var titleString       = (node.popupText != null ? ' title="' + this.escapeQuotes(node.popupText) + '"' : '');

		   // used in the href
		   var altTextString     = ' title="' + (accessibility != null ? this.escapeQuotes(accessibility) : '') + '"';

		   if(icon != null)
		   {
		       output += '<a ' + altTextString + ' href="' + node.action + '">';
			   output += '<img id="icon' + id + '" src="' + icon + '" align="absbottom"  alt="" border="0" ' + mouseOverString + mouseOutString + contextMenuString + titleString + '/>';
		       output += '</a>';
		   }

		   // Start link - include an id for highlighting
		   output += '<a class="jstree_link" ' + (highlighted ? 'style="'+selectedNodeStyle+'" ' : '')  + 'id="link' + id + '"';

		   // the selectNodeAction method will submit the form.
		   output += ' href="' + node.action + '"';

		   // add remaining stuff
		   output+= ' onKeyPress="treeFunctions.checkKeyStroke(\''+ node.treeName + '\',event);" ' + mouseOverString +  mouseOutString  + contextMenuString + '/>';


		   if(node._prefix != undefined)
		      output += node._prefix;

		   if(node.displayName != undefined)
		      output += this.htmlChars(node.displayName);
		   else
		      output += this.htmlChars(node.name);

		   if(node._suffix != undefined)
		      output += node._suffix;

		   // End link
		   output += '</a>';

		   // we want to show the previous and next links
		   if(node.isExpanded && node.isPaginated)
		   {
		      var begin, end;
		      begin = node.pageRangeBegin;
		      end = node.pageRangeBegin + node.pageSize;

		      if(begin >= node.pageSize)     // show the prev links
		      {
			     var prevBegin = begin - node.pageSize;
			     var prevEnd = prevBegin + node.pageSize;

			     // display strings
			     var urlArgs = '\'' + node.treeName + '\',\'' + node.ePath + '\',\'' + (prevBegin) + '\',\'' + MOVE_BACK + '\'';
			     var prevUrl = '<a href="javascript:treeFunctions.paginatedNodeSubmit('+ urlArgs +');" class="pagenode_link">  &lt;&lt; [' + (prevBegin+1) + '-' + prevEnd +'] ' + textPrev + '</a>';
			     output += prevUrl;
		      }

		      if(end < node.paginatedNodeChildCount)   // show the next links
		      {
			     var nextBegin = end;
			     var nextEnd = nextBegin + node.pageSize;

			     if(nextEnd > node.paginatedNodeChildCount)  // our window probably ends in this range.
			        nextEnd = node.paginatedNodeChildCount;

			     var urlArgs = '\'' + node.treeName + '\',\'' + node.ePath + '\',\'' + (nextBegin) + '\',\'' + MOVE_FORWARD + '\'';
			     var nextUrl = '<span class="jstree_link" >   |   </span><a href="javascript:treeFunctions.paginatedNodeSubmit('+ urlArgs +');" class="pagenode_link">' + textNext + ' [' + (nextBegin+1) + '-' + nextEnd +'] &gt;&gt;</a>';
			     output += nextUrl;

			     showNext = true;
		      }
		   }

		   //This span is an empty placeholder for users to do an 'innerHTML()' call on
		   // to add things directly after the node (ie selection images)
		   output += '&nbsp;<span id="placeholder' + id + '"></span>';

		   document.write(output);
		}

	/**
	 * Submites a paginated node action
	 */
	this.paginatedNodeSubmit =
		function paginatedNodeSubmit(treeName, nodePath, beginIndex, pageAction)
		{
			var formName = treeName+"ManagementForm";
			var formSubmit = "document." + formName + ".submit()";

		    // let us get the first and the last children
		    var tree = trees[treeName];
		    var node = tree.getNode(tree.rootNode, nodePath);

		    var firstChildNode = node.children[0];
		    var lastChildNode = node.children[node.children.length-1];

		    this.addParameterToForm(formName, treeName + "_pageRequestNodePath", nodePath);
		    this.addParameterToForm(formName, treeName + "_pageBegin", beginIndex);
		    this.addParameterToForm(formName, treeName + "_pageAction", pageAction);
		    this.addParameterToForm(formName, treeName + "_pageSize", node.pageSize);
		    this.addParameterToForm(formName, treeName + "_firstChildName", firstChildNode.name);
		    this.addParameterToForm(formName, treeName + "_firstChildPath", firstChildNode.path);
		    this.addParameterToForm(formName, treeName + "_firstChildData", firstChildNode.data);
		    this.addParameterToForm(formName, treeName + "_lastChildName", lastChildNode.name);
		    this.addParameterToForm(formName, treeName + "_lastChildPath", lastChildNode.path);
		    this.addParameterToForm(formName, treeName + "_lastChildData", lastChildNode.data);

		    eval(formSubmit);
		}

	/**
	 * Test method to simulate select node action. Only valid for nodes which submit forms.
	 * @return the node to be selected. The test class will then submit the form of the node
	 * manually
	 */
	this.testSelectNodeAction =
		function testSelectNodeAction(treeName, nodePath, isDisplayPath)
		{
		    var tree = trees[treeName];
		    var node = null;

		    if(isDisplayPath)
		       node = tree.getNodeByDisplayPath(null, nodePath, null);
		    else
		       node = tree.getNode(tree.rootNode, nodePath);

		    if(node.nodeAction == null)
		        return null;

		    // the current node should be the node
		    currentNode = node;

			// if the tree has a presubmit action, we want to evaluate it also
			if(node.preSubmitScript != null && node.preSubmitScript != undefined)
			{
			    var retVal = eval(node.preSubmitScript);
				if(!retVal)
					return;    // preSubmitScript returned false, so can't do anything.
		    }

		    return node;
		}


	/**
	 * Executes a node form action after a user clicks on a tree node.
	 */
	this.selectNodeAction =
		function selectNodeAction(treeName, nodePath)
		{
		    var tree = trees[treeName];
		    var node = tree.getNode(tree.rootNode, nodePath);

		    if(node.nodeAction == null)
		        return;

			// if the tree has a presubmit action, we want to evaluate it also
			if(node.preSubmitScript != null && node.preSubmitScript != undefined)
			{
			    var retVal = eval(node.preSubmitScript);
				if(!retVal)
					return;    // preSubmitScript returned false, so can't do anything.
		    }


		    // we want to add some extra parameters to the form / url
		    this.addParameter(node, treeName + "highlightNodePath", nodePath);   // we want to select that node
		    this.addParameter(node, treeName + "highlightNodeData", node.data);  // also get it's data field.
		    this.addParameter(node, treeName + "highlightNodeName", node.name);  // also get it's name.

		    // if the node had popup items, we need to submit those also
		    if(node.popupId != null)
		    {
		        var popup = tree.popups[node.popupId]
		        if(popup != null)
		        {
		            this.addParameter(node, treeName + "popupName", node.popupId);
		            for(var i=0; i<popup.length; i++)
		            {
		               var treePopup = popup[i];
		               var formElement = treeName + "popup" + i;
		               this.addParameter(node, formElement + ".action", treePopup.url);
		               this.addParameter(node, formElement + ".text", treePopup.text);
		               if(treePopup.imageSrc != null && treePopup.imageSrc != undefined)
		                  this.addParameter(node, formElement + ".imageSrc", treePopup.imageSrc);
		            }
		        }
		    }


		    /**
		     * Execute Action for hiting the url or submiting the form
		     */

		    // Step 1 : set highlighting for the node.

		    // remove highlighting for previous highlighted node.
		    var id, element;
		    if(tree.highlightNodePath != null)
		    {
		       id = node.treeName + delimeter + tree.highlightNodePath;
		       element = this.getLinkElement(id);

		       // for paginated nodes, this might be null - but otherwise this shouldn't be null.
		       if(element != null) {
		          element.style.fontWeight="normal";
			      element.style.backgroundColor="";
			   }
		    }

		    // highlight node.
		    id = node.treeName + delimeter + node.ePath;
		    element = this.getLinkElement(id);
		    element.style.backgroundColor = selectedNodeColor;
		    element.style.fontWeight = selectedNodeFontWeight;
		    tree.highlightNodePath = node.ePath;
		    
		    //bugzilla 1803
		    setCookie(treeName+"highlightNodePath", tree.highlightNodePath, null);

		    // Step 2 : Submit the action.
		    if(node.isFormSubmit)
		    {
		       var theForm = null;
		       eval(" theForm = document."+node.nodeAction);

		       if(theForm == undefined) {
		       	  alert("Cannot Find : " + node.nodeAction + " in the page.");
		       	  return;
		       }
		       theForm.submit();
			}
			else // we can just update the url values
			{
			    element.setAttribute("href", node.nodeAction);
			    if(node._target != null && node._target != undefined)
			    	element.setAttribute("target", node._target);
//bugzilla 1742 modified from original
			    //window.open(element, element.getAttribute("target"));
			    window.location.replace(element, element.getAttribute("target"));
			    return;
			}
		}


	/**
	 * javascript called when the mouse is over a node.
	 * @param treeName The name of the tree
	 * @param nodePath The nodePath
	 * @popupShowId Optional parameter. If specified, it will show the
	 *              popup with the specified id, else the default will
	 *              be shown. Default is 'popup'
	 */
	this.mouseOverCall =
		function mouseOverCall(treeName, nodePath, popupShowId)
		{
		    var tree = trees[treeName];
		    var node = tree.getNode(tree.rootNode, nodePath);

		    // hide any earlier popup's
		    this.displayPopup(treeName, 'hidden');

		    if(popupShowId != null)
		    {
		       if(popupShowId != popup2show)
		       {
		          currentNode = node;
		       }
		       popup2show = popupShowId;
		    }

			currentNode = node;

			// color the link to give the appearance of being in focus.
		    var id = node.treeName + delimeter + node.ePath;
			var element = this.getLinkElement(id);

		    if(tree.highlightNodePath != node.ePath)
		  	    element.style.backgroundColor = mouseOverColor;
		  	else
		        element.style.backgroundColor = selectedNodeColor;
		}

	// javascript called when the mouse moves outside a node
	this.mouseOutCall =
		function mouseOutCall(treeName, nodePath)
		{
		   this.popupHide(treeName);

		   var escapedAgainPath = this.escapeQuotes(nodePath);
		   var id = treeName + delimeter + escapedAgainPath;
		   var tree = trees[treeName];

		   // This will color the link back to it's original background color, making it look out of focus
		   var element = this.getLinkElement(id);
		   if(tree.highlightNodePath != escapedAgainPath)
		      element.style.backgroundColor = linkBgColor;
		   else
		      element.style.backgroundColor = selectedNodeColor;
		}

	// function which is going to display the popup
	this.startDisplayPopup =
		function startDisplayPopup(treeName, nodePath, popupShowId, event)
		{
		   // if popupShowId is null, we want to show the default popup menu, so return true.
		   if(popupShowId == null || popupShowId == 'null')
		      return true;

		   if(document.all)
		   {
		      popupX = event.x + document.body.scrollLeft;
		      popupY = event.y + document.body.scrollTop;
		   } else {
		      // try the standard
		      popupX = event.pageX;
		      popupY = event.pageY;
		   }

		   this.clearTimer();

		   // call the mouseOverCall - just in case
		   this.mouseOverCall(treeName, nodePath, popupShowId);

		   // let us set this guy before we display the popup, so that it is available to the popup action url's.
		   popupNode = currentNode;

		   // no need to wait, just display the popup
		   this.displayPopup(treeName, 'visible');

		   var escapedAgainId = this.escapeQuotes(nodePath);
		   var id = treeName + delimeter + escapedAgainId;
		   var element = this.getLinkElement(id);

		   var tree = trees[treeName];
		   if(tree.highlightNodePath != nodePath)
		   {
		       element.style.backgroundColor = mouseOverColor ;
		   }

		   // since we do not want to display the context menu.
		   return false;
		}

	/**
	 * function which starts the timer that is going to display or hide the popup.
	 * @param treeName the name of the tree
	 * @param flag - can be 'visible' or 'hidden';
	 */
	this.displayPopup =
		function displayPopup(treeName, flag)
		{
		   var popupObject = null;

		   // even though multiple trees can be displayed on a page, only one popup menu is visible.
		   if(popup2show == null)
		   {
		      return;
		   }

		   var tree = trees[treeName];

		   popupObject = tree.getPopup(popup2show);
		   if(popupObject == null)
		       return;

		   popupObject.style.left = popupX;
		   popupObject.style.top  = popupY;
		   popupObject.style.visibility = flag;
		}

	// function called from the popup to hide it
	this.popupHide =
		function popupHide(treeName)
		{
		    if(currentNode != null)
		    {
		       var id = treeName + delimeter + currentNode.ePath;
		       var element = this.getLinkElement(id);
		       element.style.backgroundColor = linkBgColor;
		    }

		    this.clearTimer();
		    var fStr = "treeFunctions.displayPopup('"+treeName+"','hidden')";
		    timerId = setTimeout(fStr, timeOutvalue);
		}

	// function called from the popup to show it, basically kills the timer meant to hide the popup.
	this.popupCancelhide =
		function popupCancelhide(treeName)
		{
		    if(currentNode != null)
		    {
		       var id = treeName + delimeter + currentNode.ePath;
		       var element = this.getLinkElement(id);
		       element.style.backgroundColor = mouseOverColor;
		    }
			this.clearTimer();
		}

	// rollover function for the popup td
	this.popupRollOver =
		function popupRollOver(td)
		{
		   td.style.backgroundColor=popupRolloverColor;
		   td.style.color=linkBgColor;
		}

	this.popupRollOut =
		function popupRollOut(td)
		{
		   td.style.backgroundColor = mouseOverColor;
		   td.style.color=popupTextColor;
		}

	// clear any prior timer if it exists
	this.clearTimer =
		function clearTimer()
		{
			if(timerId != null)
			{
		    	   clearTimeout(timerId);
		    	   timerId = null;
		    }
		}

	/** Scroll to the selected element in the tree */
	this.scrollToElement =
		function scrollToElement(treeName, nodePath)
		{
		  // after creating the tree, we need to go give the specified selected node
		  var sElement = this.getLinkElement(treeName + delimeter + this.escapeQuotes(nodePath));
		  if(sElement != null)
		    sElement.focus();

		}

	// Opens or closes a node by submitting a form
	this.oc =
		function oc(treeName, nodePath)
		{
		    var escapedPath = this.escapeQuotes(nodePath);
		    var id = treeName + delimeter + escapedPath;

		    // the image div
		    var theJoin = document.getElementById('join' + id);

			var formName = treeName+"ManagementForm";
			var formSubmit = "document." + formName + ".submit()";

		    if (theJoin.src.indexOf('plus.gif') != -1 || theJoin.src.indexOf('plusbottom.gif') != -1) // node is to be opened
			{
			    this.addParameterToForm(formName, treeName + "expandNode", nodePath);
			    eval(formSubmit);
			} else  // node is closed
			{
		        var tree = trees[treeName];
		        var node = tree.getNode(tree.rootNode,nodePath);

			    // if a node below the current node is selected, we have to automatically select this node after collapsing it
			    // the last test condition node.nodeAction != null is to handle the case where the node
			    // is disabled but it's child is still selected. So we cannot select the node, but we
			    // still collapse it instead.
			    if(tree.highlightNodePath != null && tree.highlightNodePath.indexOf(escapedPath) == 0 && node.nodeAction != null)
			    {
			        // we have to click on this node and submit its form
		            this.addParameter(node, treeName + "collapseNode",nodePath);        // collapse the node
		            eval(node.action);
			    } else
			    {
		            // just collapse the node without submitting it
			        this.addParameterToForm(formName, treeName + "collapseNode", nodePath);
		            eval(formSubmit);
			    }
			}
		}

	// Test function for submitting a client server tree. This does not do the submit
	// but returns a string to the test function
	// The string is :
	// a) Mode_Expand - to expand a node
	// b) Mode_Collapse - to collapse a node
	// c) Mode_Collapse_Select - to collapse and select the node
	this.ocClientServerTest =
		function ocClientServerTest(treeName, nodePath)
		{
		    var id = treeName + delimeter + this.escapeQuotes(nodePath);

		    // the image div
		    var theJoin = document.getElementById('join' + id);

			var formName = treeName+"ManagementForm";
			var formSubmit = "document." + formName + ".submit()";

		    if (theJoin.src.indexOf('plus.gif') != -1 || theJoin.src.indexOf('plusbottom.gif') != -1) // node is to be opened
			{
				return "Mode_Expand";
			} else  // node is closed
			{

			    // if a node below the current node is selected, we have to automatically select this node after collapsing it
			    if(tree.highlightNodePath != null && tree.highlightNodePath.indexOf(nodePath) == 0 && node.nodeAction != null)
			    {
			        // we have to click on this node and submit its form
			        return "Mode_Collapse_Select";
			    } else
			    {
		            // just collapse the node without submitting it
		            return "Mode_Collapse";
			    }
			}
		}

	// opens or closes a node locally
	this.ocLocal =
		function ocLocal(treeName, nodePath, bottom)
		{
		    var id = treeName + delimeter + nodePath;
			var theDiv  = document.getElementById('div' + id);
			var theJoin = document.getElementById('join' + id);
			var theIcon = document.getElementById('icon' + id);
			var uId = null;
			var tree = trees[treeName];
			var node = tree.getNode(tree.rootNode, nodePath);


			if(theDiv == null)
			{
			    uId = this.escapeQuotes(id);
			    theDiv = document.getElementById('div' + uId);
				if(theJoin == null) theJoin	= document.getElementById('join' + uId);
				if(theIcon == null) theIcon = document.getElementById('icon' + uId);
		    }

			if(theDiv == null)      // could be due to a double quote
			{
			    uId = this.replaceDoubleQuotes(uId);
				theDiv = document.getElementById('div' + uId);
		        if(theJoin == null) theJoin	= document.getElementById('join' + uId);
				if(theIcon == null) theIcon = document.getElementById('icon' + uId);
			}


		    // if theJoin.src contains plus.gif, it means that the node is asked to be expanded
			// if theJoin.src contains minus.gif, it means that the node is asked to be collapsed
		    if(theJoin.src.indexOf("plus.gif") != -1 || theJoin.src.indexOf('plusbottom.gif') != -1)
			   tree.openNodes[nodePath] = "true";
			else
			   tree.openNodes[nodePath] = null;

		    this.storeOpenNodesInCookie(treeName);

			if (theJoin.src.indexOf('plus.gif') != -1 || theJoin.src.indexOf('plusbottom.gif') != -1) // node is to be opened
			{
				if (bottom==1) theJoin.src = tree.icons[3].src;
				else theJoin.src = tree.icons[2].src;
				if(theIcon != null) theIcon.src = tree.getNode(tree.rootNode,nodePath).iconOpen;
				if(theDiv.style != undefined)
				    theDiv.style.display = '';
				else
				    theDiv.setAttribute("style", "display='inline'");
				node.isExpanded = true;
			} else
			{
				if (bottom==1) theJoin.src = tree.icons[1].src;
				else theJoin.src = tree.icons[0].src;
				if(theIcon != null) theIcon.src = tree.getNode(tree.rootNode,nodePath).iconClosed;
				if(theDiv.style != undefined)
				   theDiv.style.display = 'none';
				else
				   theDiv.setAttribute("style", "display='none'");
				node.isExpanded = false;
			}

		}


	// store the openNodes in the cookie for the tree.
	// @param name The name of the tree
	this.storeOpenNodesInCookie =
		function storeOpenNodesInCookie(name)
		{
		   var tree = trees[name];
		   var cookieName = name + "openNodes";
		   var cookieValue = "";

		   for(var n in tree.openNodes)
		   {
		      if(tree.openNodes[n] != null)
		         cookieValue += n + delimeter;
		   }

		   document.cookie = cookieName + "=" + escape(cookieValue);
		}

	// add extra parameter to the node action variable.
	this.addParameter =
		function addParameter(node, param, value)
		{
		  if(node == null || param == null || value == null || param == "" || value == "" || value == 'null')
		     return;

		  if(node.isFormSubmit)
		     this.addParameterToForm(node.nodeAction, param, value);
		  else
		     node.nodeAction = this.addParameterToUrl(node.nodeAction, param, value);
		     }

	// this method is used to add extra parameter to a url. It returns the correct url.
	this.addParameterToUrl =
		function addParameterToUrl(url, param, value)
		{
		   var newUrl = "";
		   var appendChar = url.indexOf("?") == -1 ? "?" : "&";

		   newUrl = url + appendChar + escape(param) + "=" + escape(value);

		   return newUrl;
		}

	// this method is used to add extra parameter to a specified form.
	this.addParameterToForm =
		function addParameterToForm(formName, param, value)
		{
		   if(formName == null || formName == 'null' || param == null || value == null || param == "" || value == "" || value == 'null')
		   {
		      return;
		   }

		   var theForm = null;
		   eval(" theForm = document."+formName);

		   if(theForm == undefined) {
		      throw "Cannot find the form : " + formName;
		 	  return;
		   }

		   var hiddenNode = document.createElement('input');
		   hiddenNode.setAttribute('type','hidden');
		   hiddenNode.setAttribute('name',param);
		   if(value.value != undefined)
		      hiddenNode.setAttribute('value',value.value);        // so that users can put in text field as values
		   else
		      hiddenNode.setAttribute('value',value);

		   theForm.appendChild(hiddenNode);
		}

	// retrieve openNodes from cookie
	// @param name The name of the tree
	this.retrieveOpenNodesFromCookie =
		function retrieveOpenNodesFromCookie(name)
		{
		   var tree = trees[name];
		   var arr = document.cookie.split(";");

		   if(arr == null || arr.length == 0)
		      return;

		   for(i=0; i<arr.length; i++)
		   {
		      if(arr[i].indexOf(name+"openNodes") != -1)
		      {
		          var j = arr[i].indexOf("=");
		          if(j != -1)
		          {
		             var cookieValue = unescape(arr[i].substring(j+1));

		             // now let us create an split this thing...
		             var valueArray = cookieValue.split(delimeter);

		             if(valueArray == null || valueArray.length == 0)
		                return;

		             for(k=0; k<valueArray.length; k++)
		             {
		                 if(valueArray[k].length > 0) {
		                    tree.openNodes[valueArray[k]] = "true";
		                 }
		             } // end for
		          } // endif j != -1
		      } // endif arr[i].indexOf(openNodes)
		   } // endfor
		}

    // call back handler which will iterate through the nodes in a tree
    // @param treeName The name of the tree
    // @param callbackFunction The name of the callbackFunction which will be called for each node.
    //        do not add parenthesis to the function. Example -  "showPageHandles"
    this.treeCallbackHandler =
        function treeCallbackHandler(treeName, callbackFunction)
    {
        var tree = trees[treeName];
        if(tree == null)
            throw "Cannot find any tree with the name :" + treeName;

        treeFunctions.callbackInternal(tree.rootNode, callbackFunction);

    }

    // private internal function.
    this.callbackInternal =
       function callbackInternal(node, callbackFunction)
    {
        var cbs = callbackFunction + "(node)";

        eval(cbs);

        for(var i=0; i<node.children.length; i++) {
           callbackInternal(node.children[i], callbackFunction);
        }
    }

	/**
	 * Utility functions for the script
	 */

	// get the link represented by the id
	this.getLinkElement =
		function getLinkElement(id)
		{
		   var lid = "link" + id;
		   var element = document.getElementById(lid);

		   if(element != null)
		       return element;

		   // there must be a double quote in there,
		   var decode = lid.replace(/&quot;/g, '"');
		   element = document.getElementById(decode);
		   return element;

		}

    // get the placeholder represented by the id
	this.getPlaceholderElement =
		function getPlaceholderElement(id)
		{
		   var lid = "placeholder" + id;
		   var element = document.getElementById(lid);

		   if(element != null)
		       return element;

		   // there must be a double quote in there,
		   var decode = lid.replace(/&quot;/g, '"');
		   element = document.getElementById(decode);
		   return element;

		}

	// destroys the popup if escape pressed
	this.checkKeyStroke =
		function checkKeyStroke(treeName, event)
		{
		  if(event.keyCode == 27)
		     this.displayPopup(treeName, 'hidden');
		}

	// escape the single quotes out of the id....
	this.escapeQuotes =
		function escapeQuotes(id)
		{
		    if(id == null)
			   return id;

		    var n = id.replace(/'/g, '\\\u0027');
		    n = n.replace(/\t/g, '\\u0009');
			n = this.escapeDoubleQuotes(n);
			return n;
		}

	this.escapeDoubleQuotes =
		function escapeDoubleQuotes(id)
		{
		    return id.replace(/"/g, '&quot;');
		}

	this.replaceDoubleQuotes =
		function replaceDoubleQuotes(id)
		{
		    return id.replace(/&quot;/g, '"');
		}

	this.replaceQuotes =
		function replaceQuotes(id)
		{
		    var n = id.replace(/\\u0027/g, "'");
			n = n.replace(/\\u0022/g, '"');
		    return n;
		}

	this.reverseQuotesToUnicode =
		function reverseQuotesToUnicode(id)
		{
		    var n = id.replace(/'/g, "\\u0027");
			n = n.replace(/"/g, '\\u0022');
		    return n;
		}

	this.htmlChars =
		function htmlChars(source)
		{
		   if(source == null)
		       return source;

		   var target = source;

		   target = target.replace(/&/g, "&amp;");
		   target = target.replace(/</g, "&lt;");
		   target = target.replace(/>/g, "&gt;");

		   return target;
		}
}


/**
 * A simple form object allowing users to manipulate various form elements in the page.
 * This is not related to the tree, but meant as a helper object.
 */
function FormObject(formName)
{
   this.formName = formName;
   eval("this.theForm = document."+formName);

   if(this.theForm == undefined) {
   	  alert("Cannot Find : " + formName + " in the page.");
   	  return;
   }

   // Add an extra parameter to the form
   this.addParameter =
      function addParameter(name, value)
      {
         new TreeFunctions().addParameterToForm(this.formName, name, value);
      }

   // return the actual html form object
   this.getForm =
      function getForm()
      {
         return this.theForm;
      }

}
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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.common.valueholder.tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Class which allows users to create trees on the client browser, 
 * hiding all the javascript implementation details while presenting the developer 
 * with an easy to use javax.swing.Jtree like interface to work with.
 *
 * @author jalpesh
 *
 */
public class Tree
{
    private boolean useCookies;                       // in local mode, if a cookies should be used or not
    private String baseDirectory;                     // base directory from where the images are loaded
    private boolean isLocal;                          // whether the tree is a local tree or not
    private TreeNode rootNode;                        // the root node of the tree
    private String name;                              // the name of the tree
    private String highlightNodePath;                 // Optional parameter to set highlighted nodes
    private List popups;                              // keep track of popups of the tree. this is used when rendering the tree

    private boolean instrumentation;                  // put in instrumentation code
    
    /** Variables used for i18n actions */
    private String textNode;
    private String textCollapse;
    private String textCollapsed;
    private String textExpand;
    private String textExpanded;
    private Object textLevel;
    private String textOf;
    private String textSelected;
    private String textNext;
    private String textPrev;
    
    private long startTime;
    private int totalNodes;
    
    /**
     * Create a tree with a given name. Please note that the name of the tree should be unique in a given user session.
     * So for example if your site has five trees in different pages, all the trees should have a unique name 
     * @param name The name of the tree. Please note that the name of the tree should not contain any of the following
     * characters - single/doublequotes (',"), forward/backward slash (/,\) or the period (.)
     */
    public Tree(String name)
    {
        this(name, null, false);
    }

    /**
     * Create a tree with a given name. Please note that the name of the tree should be unique in a given user session.
     * So for example if your site has five trees in different pages, all the trees should have a unique name 
     * @param name The name of the tree
     * @param rootNode The root node of the tree
     * @param isLocal Whether or not the tree is in local mode or a client/server mode.
     */
    public Tree(String name, TreeNode rootNode, boolean isLocal)
    {
        this.name = name;
        this.baseDirectory = "images/tree_images/";
        this.useCookies = true;
        this.isLocal = isLocal;
        this.rootNode = rootNode;
        this.popups = new ArrayList();       
    }
    
    /**
     * Each tree can optionally have one node which is highlighted. This method returns the highlighted node path for that tree.
     * If this parameter is not set, upon rendering the tree will try to find a highlighted node in the TreeStateManager if it
     * exists, and then use it instead.
     * @return Returns the highlightNodePath.
     */
    public String getHighlightNodePath()
    {
        return highlightNodePath;
    }

    /**
     * Each tree can optionally have one node which is highlighted. This method sets the highlighted node path for that tree.
     * If this parameter is not set, upon rendering the tree will try to find a highlighted node in the TreeStateManager if it
     * exists, and then use it instead.
     * @param highlightNodePath The highlightNodePath to set.
     */
    public void setHighlightNodePath(String highlightNodePath)
    {
        this.highlightNodePath = highlightNodePath;
    }

    /**
     * @return Returns the baseDirectory from where the tree images whill be loaded. 
     * This defaults to "images/tree_images/" which will look for the images/tree_images directory inside your current directory.
     */
    public String getBaseDirectory()
    {
        return baseDirectory;
    }

    /**
     * @param baseDirectory Set the base directory where the tree generator will look for images of the tree.
     * Defaults to "images/tree_images/" if not set
     */
    public void setBaseDirectory(String baseDirectory)
    {
        this.baseDirectory = baseDirectory;
    }

    /**
     * @return Returns whether or not the tree is in a local mode or not. A tree in a local mode will not
     * hit the server in case of an expand or a collapse action.
     */
    public boolean isLocal()
    {
        return isLocal;
    }

    /**
     * @param isLocal Sets the local mode of the tree. By default it is false. 
     * Local mode determines whether the entire tree is generated on the client or, the tree is generated on a need basis only.
     */
    public void setLocal(boolean isLocal)
    {
        this.isLocal = isLocal;
    }

    /**
     * @return Returns the name of the tree. Each tree should have a unique name to avoid conflicts.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name Set the name of the tree.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the rootNode of the tree.
     */
    public TreeNode getRootNode()
    {
        return rootNode;
    }

    /**
     * @param rootNode Each tree should at least have one root node. This is set using this method.
     */
    public void setRootNode(TreeNode rootNode)
    {
        this.rootNode = rootNode;
        if(rootNode != null)
            rootNode.setTree(this);
    }

    /**
     * @return Returns true or false indicating whether or not the client is to use cookies if the tree
     * is in a local mode (ie setLocal is true)
     */
    public boolean isUseCookies()
    {
        return useCookies;
    }

    /**
     * @param useCookies Indicate the tree whether or not to use cookies in case of a local mode.
     */
    public void setUseCookies(boolean useCookies)
    {
        this.useCookies = useCookies;
    }
    
    /**
     * Set the values of the displayed text messages for this tree. This is useful for localization issues. 
     * If you tree is only going to be used in US English local you can use the default values. However it is advisable
     * to set these values if you want the tree to be fully localized.
     * 
     * You will need to set the following values :
     * 
     * @param textNode       Default is "node"
     * @param textCollapse   Default is "Collapse"
     * @param textCollapsed  Default is "Collapsed"
     * @param textExpand     Default is "Expand"
     * @param textExpanded   Default is "Expanded"
     * @param textLevel      Default is "Level" 
     * @param textOf         Default is "of"
     * @param textSelected   Default is "Selected"
     * @param textNext       Default is "Next"
     * @param textPrev       Default is "Prev"
     */
    public void setI18nText(String textNode, String textCollapse, String textCollapsed, String textExpand, String textExpanded, String textLevel, String textOf, String textSelected, String textNext, String textPrev)
    {
        this.textNode = textNode;
        this.textCollapse = textCollapse;
        this.textCollapsed = textCollapsed;
        this.textExpand = textExpand;
        this.textExpanded = textExpanded;
        this.textLevel = textLevel;
        this.textOf = textOf;
        this.textSelected = textSelected;
        this.textNext = textNext;
        this.textPrev = textPrev;
    }
    
    // return the javascript definition of the tree
    protected String getJavascriptDefinition(HttpServletRequest request)
    {
        String highlightNodePath = null;

        TreeStateManager treeStateManager = TreeStateManager.getInstance(request.getSession(), name);

        // user has not set a highlighted node, so let us get it from the state manager
        if(getHighlightNodePath() == null)
            highlightNodePath = treeStateManager.getHighlightedNodePath();
        
        // we need name, root node, base directory, isLocal, and optionally the highlight node
        // root node will always be defined as _a
        String script = "   tree = new Tree('"+TreeNode.escapeQuotes(getName())+"',_a,'"+getBaseDirectory()+"',"+isLocal()+ (highlightNodePath != null ? ",'"+TreeNode.escapeQuotes(highlightNodePath)+"'":",null") + ");\n";
        script +=       "   tree.renderTree();\n";
        return script;
    }
    
    /**
     * Renders the tree.
     * @param request The HttpServletRequest object
     * @return The javascript representation of the tree
     */
    public String renderTree(HttpServletRequest request)
    {
        String treeScript = "";
       
        // mozilla does not work very well with overflow: style, especially if there is a vertical scrollbar in the page. Whenever you click
        // on a link, it will jump to the middle of the div. So we are eliminating the style for every browser other than IE -->

        if(!isLocal)
        {    
            treeScript += "<form name='"+name+"ManagementForm' method='get'>\n";
            treeScript += "</form>\n";
        }
        treeScript += "<span id='"+name+"popupPlaceholder'></span>\n";
        treeScript += "<script language='javascript'>\n";
        treeScript += "if(document.all) {\n";
        treeScript += "   document.write('<div style=\"overflow:visible; width:265px; white-space:nowrap;\">');\n";
        treeScript += "} else {\n";
        treeScript += "   document.write('<table width=\"250px;\"><tr><td nowrap>');\n";
        treeScript += "}\n";

        treeScript += "{\n";       

        if(hasInstrumentation())
            treeScript += startTimer();
        
        // if i18n variables are initialized, let us display them
        if(textNode != null)
        {
            treeScript += "textNode = '" + textNode + "';\n";
            treeScript += "textCollapse = '" + textCollapse + "';\n";
            treeScript += "textCollapsed = '" + textCollapsed + "';\n";
            treeScript += "textExpand = '" + textExpand + "';\n";
            treeScript += "textExpanded = '" + textExpanded + "';\n";
            treeScript += "textLevel = '" + textLevel + "';\n";
            treeScript += "textOf = '" + textOf + "';\n";
            treeScript += "textSelected = '" + textSelected + "';\n";
            treeScript += "textNext = '" + textNext + "';\n";
            treeScript += "textPrev = '" + textPrev + "';\n";
        }
    
        // let us print the path delimeter for the tree nodes
        treeScript += "pathDelimeter = '" + TreeNode.escapeQuotes(new String(new char[] {TreeNode.getPathDelimeter()})) + "';\n"; 
        
        // generate the tree nodes first....
        treeScript += generateNodeScript(rootNode, null);
        
        // now generate the tree....
        treeScript += getJavascriptDefinition(request);
        
        treeScript += "}\n";

        treeScript += "if(document.all) {\n";
        treeScript += "   document.write('<p>&nbsp;&nbsp;</p>');\n";     // extra space required for ie...
        treeScript += "   document.write('</div>');\n";
        treeScript += "} else {\n";
        treeScript += "   document.write('</td></tr></table>');\n";
        treeScript += "}\n";
        
        if(hasInstrumentation())
            treeScript += stopTimer();
        
        treeScript += "</script>\n";
    
        // finally generate the popups. Order of generation is important - first generate the nodes, then the tree and finally
        // the popups.
        for(int i=0; i<popups.size(); i++)
            treeScript += ((TreePopup)popups.get(i)).getJavascriptDefinition();
        
        return treeScript;
    }

    /**
     * @param parent The start point of generation of the tree
     * @return
     */
    private String generateNodeScript(TreeNode node, String parentVarName)
    {
        // each node should know about the tree it belongs to. Call this before generating the script definition
        node.setTree(this);                  

        String script = node.getJavascriptDefinition(parentVarName);          totalNodes++;  // keep track of nodes in the system.
        
        // create a list of popups for the tree
        TreePopup p = node.getPopup(); 
        if(p != null && !popups.contains(p)) {
            p.setTreeName(getName());
            popups.add(p);
        }
        
        for(int i=0; i<node.getChildren().size(); i++)
        {
            TreeNode n = (TreeNode)node.getChildren().get(i);
            script += generateNodeScript(n, node.getVarName());
        }
        
        return script;
    }
    
    /**
     * Returns whether or not the tree has instrumentation code turned on or off
     * @return Returns the instrumentation.
     */
    public boolean hasInstrumentation()
    {
        return instrumentation;
    }

    /**
     * Sets the instrumentation for the tree to on or off. When instrumentation is turned on,
     * the tree displays time taken to render the tree to the user. 
     * @param instrumentation The instrumentation to set.
     */
    public void setInstrumentation(boolean instrumentation)
    {
        this.instrumentation = instrumentation;
    }


    /** Instrumentation code to start measuring time */
    public String startTimer()
    {
        startTime = new Date().getTime();
        return ("\tvar __sTime = new Date().getTime();\n");
    }

    public String stopTimer()
    {
        long diff = new Date().getTime() - startTime;
        String s = "";
        s += "\tvar __tt = new Date().getTime()-__sTime;\n";
        s += "\tvar __stt = " + diff + ";\n";
        s += "\tvar __total = __tt + " + diff + "\n";
        s += "\tvar s0 = 'Total Nodes  : ' + " + totalNodes + ";\n";
        s += "\tvar s1 = 'Client Side Time Taken (ms) : ' + __tt;\n";
        s += "\tvar s2 = 'Server Side Time Taken (ms) : ' + " + diff + ";\n";
        s += "\tvar s3 = 'Client/Server ratio : ' + Math.round(100*__tt/__total) + '% :: ' + " + " Math.round(100*__stt/__total) + '%'; \n";

        s += "\talert(s0 + '\\n' + s1 + '\\n' + s2 + '\\n' + s3);\n";
        return s;
    }
    
}

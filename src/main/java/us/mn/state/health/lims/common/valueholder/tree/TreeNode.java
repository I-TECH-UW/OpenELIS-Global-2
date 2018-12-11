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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import us.mn.state.health.lims.common.log.LogEvent;

/**
 * This class represents each node in the tree. A TreeNode is the basic building node in a Tree. Each 
 * TreeNode can exist only in one Tree at a time and cannot be shared across different trees.
 * <br>
 * For each TreeNode object, an equivalent JSTreeNode javascript object is created on the client. The following
 * javascript attributes will be available for the treeNode on the client :
 * <br><br>
 * node.name          - the name of the tree <br>
 * node.path          - the path to the tree <br>
 * node.parent        - the parent node to the tree node <br>
 * node.treeName      - the name of the tree the treenode belongs to <br>
 * node.isExpanded    - whether or not the node is expanded or not <br>
 * node.hasChildren   - whether or not the node has any children or not <br>
 * node.data          - the data field assocaited with the node <br>
 * node.preSubmitAction- Optional presubmit action associated with the node <br>
 * 
 * @author jalpesh
 *
 */
public class TreeNode
{
    private String name;                  // the name of the node
    private String displayName;           // the display name of the node
    private String iconOpen;              // the open image icon src
    private String iconClosed;            // the closed image icon src
    private TreePopup popup;              // the popup associated with the node
    private String popupText;             // the popup text associated when user hovers over the node image
    private Properties props;             // if the user wants to set extra properties on the TreeNode.
    private String data;                  // the data field associated with the node.
    private String url;                   // the url associated with the node
    private String urlTargetName;         // optional target name associated with the node.
    private String submitFormName;        // the form to be submitted when user clicks on the node
    private String preSubmitScript;       // optional javascript script to be executed before submitting the form
    private List children;                // a list of children of the tree node
    private boolean isExpanded;           // whether or not a node is expanded or not
    private boolean hasChildren;          // whether or not a node has children
    private Tree tree;                    // the tree this node belongs to
    protected String nodePath;              // the path to the node
    protected TreeNode parent;              // keep an internal reference to the parent of the tree node
    
    /** pagination support for the node. */
    private boolean isPaginated;          // whether or not a node supports pagination for its children
    private int pageSize = 10;            // the default page size.
    private int paginatedNodeChildCount;  // the total children of this paginated node.
    private int pageRangeBegin=-1;        // the begin index of the page range
    
    private String varName;         // the variable name of the parent javascript object of this tree node
      

    // The delimeter for the node path - do not change this value here
    // use the setter to change it if you want a different one
    private static char pathDelimeter = '.';

    // the constant used for the javascript variable
    protected static String VAR_NAME_CONSTANT  = "_a";
    protected static String VAR_NAME_INCREMENT = "a";
    
    /**
     * Create a tree node with a given name
     * @param name The name of the tree node. Note that the name should not contain the path delimeter.
     */
    public TreeNode(String name)
    {
        // if the name contains the path delimeter, let us throw an exception
        if(name.indexOf(pathDelimeter) != -1)
            throw new IllegalArgumentException("The name of the TreeNode should not contain the path Delimeter - '" + pathDelimeter + "'");
        
        this.name = name;
        this.nodePath = name;
        this.children = new ArrayList();
        this.props = new Properties();
        this.varName = VAR_NAME_CONSTANT;
    }

    /**
     * Method used by the addChild methods which construct a new tree node. It uses reflection to create the subclass of TreeNode 
     * (if any) instead of a tree node.
     *  
     * @param name The name of the tree node.
     * @return An instance which is the subclass of the tree node.
     */
    private TreeNode createTreeNode(String name)
    {
            
            // System.out.println(">>> The class calling addChild is :" +this.getClass().getName());
            
            try
            {
                Constructor constructor = this.getClass().getConstructor(new Class[] { String.class });
                return (TreeNode)constructor.newInstance((Object[])new String[] {name});
            } catch (IllegalArgumentException e)
            {
			    //bugzilla 2154
			    LogEvent.logError("TreeNode","createTreeNode()",e.toString());                
                throw e;
            } catch (InstantiationException e)
            {
                //bugzilla 2154
			    LogEvent.logError("TreeNode","createTreeNode()",e.toString()); 
                throw new IllegalArgumentException(e.getMessage());
            } catch (IllegalAccessException e)
            {
                //bugzilla 2154
			    LogEvent.logError("TreeNode","createTreeNode()",e.toString()); 
                throw new IllegalArgumentException(e.getMessage());
            } catch (InvocationTargetException e)
            {
                //bugzilla 2154
			    LogEvent.logError("TreeNode","createTreeNode()",e.toString());
                throw new IllegalArgumentException(e.getMessage());
            } catch (SecurityException e)
            {
                //bugzilla 2154
			    LogEvent.logError("TreeNode","createTreeNode()",e.toString());
                throw new IllegalArgumentException(e.getMessage());
            } catch (NoSuchMethodException e)
            {
                //bugzilla 2154
			    LogEvent.logError("TreeNode","createTreeNode()","Your subclass should have a constructor which takes in a string argument: " + e.toString()); 
                throw new IllegalArgumentException("Your subclass should have a constructor which takes in a string argument.");
            }
    }
    
    /**
     * @return Returns the tree associated with the node. This is set automatically by the framework when the tree is generated
     */
    protected Tree getTree()
    {
        return tree;
    }

    /**
     * @param tree the tree which is associated with this node.
     */
    protected void setTree(Tree tree)
    {
        this.tree = tree;
    }

    /**
     * Add a child node to the current node
     * @param name The name of the node. Note that the name should not contain the path delimeter character in it.
     * @return The newly added node. Note that if you have extended the TreeNode, the addChild method will return an 
     * instance of your subclass. So you can cast down to the subclass if desired.
     */
    public TreeNode addChild(String name)
    {
        return addChild(name, null, null);
    }

    /**
     * Add a child node to the current node.
     * @param name  The name of the node. Note that the name should not contain the path delimeter character in it.
     * @param action The action associated with the node. This could be a url or a form name which should be submitted.
     * @param icon The icon src which will be used to display the tree node. The same icon will be used to display 
     * expanded/collapsed tree.
     * @return The newly added node. Note that if you have extended the TreeNode, the addChild method will return an 
     * instance of your subclass. So you can cast down to the subclass if desired.
     */
    public TreeNode addChild(String name, String action, String iconSrcOpen, String iconSrcClosed)
    {
        TreeNode tn = addChild(name, action, iconSrcOpen);
        if(tn == null)
           return null;

        // we should set the closed icon also.
        tn.setIconSrcClosed(iconSrcClosed);
        return tn;
    }

    /**
     * Add a tree node as a child to the current node. This will create new child node
     * out of the current node and add it to the child list of the current node.
     * @param node The child node
     * @return The added child node. Note that if you have extended the TreeNode, the addChild method will return an 
     * instance of your subclass. So you can cast down to the subclass if desired.
     */
    public TreeNode addChild(TreeNode node)
    {
        TreeNode tn = addChild(node.getName());
        
        // copy required properties.
        copyNode(node, tn);
        
        // now copy all the children
        for(int i=0; i<node.children.size(); i++)
          tn.addChild((TreeNode)node.children.get(i));
        
        return tn;
    }
    
    /**
     * Add a child node to the current node.
     * @param name The name of the node. Note that the name should not contain the path delimeter character in it.
     * @param action The action associated with the node. This could be a url or a form name which should be submitted.
     * @param icon The icon src which will be used to display the tree node. The same icon will be used to display 
     * expanded/collapsed tree.
     * @return The newly added node. Note that if you have extended the TreeNode, the addChild method will return an 
     * instance of your subclass. So you can cast down to the subclass if desired.
     */
    public TreeNode addChild(String name, String action, String icon)
    {
        if(name == null)
            return null;
        
        TreeNode tn = createTreeNode(name);
        
        // action can either be url or a form name to submit
        if(action == null)
            tn.setUrl(null, null);
        else if(action.startsWith("http"))
            tn.setUrl(action, null);
        else
            tn.setSubmitFormName(action);
        
        tn.nodePath = this.nodePath + pathDelimeter + name;
        tn.setIconSrcOpen(icon);
        tn.setIconSrcClosed(icon);
        tn.parent = this;
        tn.varName = varName + VAR_NAME_INCREMENT;
        
        children.add(tn);
        this.hasChildren = true;
        return tn;
    }

   
    private void copyNode(TreeNode original, TreeNode target)
    {
        target.name          = new String(original.name);
        
        target.hasChildren   = original.hasChildren;
        target.isExpanded    = original.isExpanded;
        target.isPaginated   = original.isPaginated;
        
        target.pageRangeBegin= original.pageRangeBegin;
        target.pageSize      = original.pageSize;
        target.paginatedNodeChildCount = original.paginatedNodeChildCount;
        
        if(original.data != null)                   target.data = new String(original.data);
        if(original.displayName != null)            target.displayName = new String(original.displayName);
        if(original.iconClosed != null)             target.iconClosed = new String(original.iconClosed);
        if(original.iconOpen != null)               target.iconOpen = new String(original.iconOpen);
        if(original.popup != null)                  target.popup = original.popup.clonePopup();
        if(original.popupText != null)              target.popupText = new String(original.popupText);                      
        if(original.preSubmitScript != null)        target.preSubmitScript = new String(original.preSubmitScript);       
        if(original.props != null)                  target.props = copyProps(original.props);
        if(original.submitFormName != null)         target.submitFormName = new String(original.submitFormName);         
        if(original.url != null)                    target.url = new String(original.url);            
        if(original.urlTargetName != null)          target.urlTargetName = new String(original.urlTargetName);
        
        // ignored variables in copy.
        // path, tree, parent, varName;
    }
    
    private Properties copyProps(Properties props)
    {
        Properties newProps = new Properties();
        Enumeration keys = props.keys();
        while(keys.hasMoreElements()) {
            Object key = keys.nextElement();
            newProps.put(key, props.get(key));
        }
        
        return newProps;
    }
    
    /**
     * Return whether or not a node has children or not
     * @return
     */
    public boolean hasChildren()
    {
        return hasChildren;
    }
    
    /**
     * Over-write whether or not a node has children or not.
     * @param hasChildren
     */
    public void setHasChildren(boolean hasChildren)
    {
        this.hasChildren = hasChildren;
    }
    
    
    /**
     * Reference to the parent object of the tree node
     * @return
     */
    public TreeNode getParent()
    {
        return parent;
    }
    
    /**
     * Return true if the node is expanded.
     * @return Returns whether or not the node is expanded or not
     */
    public boolean isExpanded()
    {
        return isExpanded;
    }

    /**
     * If true, sets the state of the node to be expanded when displayed in the tree 
     * @param isExpanded The expanded state to be set.
     */
    public void setExpanded(boolean isExpanded)
    {
        this.isExpanded = isExpanded;
    }

    /**
     * Return the path associated with the node. This is essentially the path separated by the node delimeter.
     * @return Returns the nodePath.
     */
    public String getNodePath()
    {
        return nodePath;
    }

    /**
     * @return Returns the pathDelimeter.
     */
    public static char getPathDelimeter()
    {
        return pathDelimeter;
    }

    /**
     * @param pathDelimeter The pathDelimeter to set.
     */
    public static void setPathDelimeter(char pathDelimeter)
    {
        TreeNode.pathDelimeter = pathDelimeter;
    }

    /**
     * @return Returns the displayed name of the TreeNode. 
     * This is an optional field and if it is not set, the tree will be displayed with the name
     */
    public String getDisplayName()
    {
        return displayName;
    }

    /**
     * Sets the displayed name of the TreeNode. If not set, the node will be displayed with the original name
     * @param displayName The displayed name of the tree node.
     */
    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    /**
     * Return the javascript variable name associated with the object
     * @return String
     */
    protected String getVarName()
    {
        return varName;
    }
    
    /**
     * This is an optional method on the TreeNode which sets the preSubmitAction. If you want some 
     * javascript to be executed before the form associated with the node is submitted, 
     * you can use the setPreSubmitAction method. Please ensure that the javascript specified by this method does not submit a 
     * form, or else the tree will be in an inconsistent state. 
     * 
     * @return Returns the preSubmitScript.
     */
    public String getPreSubmitScript()
    {
        return preSubmitScript;
    }

    /**
     * This is an optional method on the TreeNode which sets the preSubmitAction. If you want some 
     * javascript to be executed before the form associated with the node is submitted when the node is clicked, 
     * you can use the setPreSubmitScript method. Please ensure that the javascript specified by this method does not submit a 
     * form, or else the tree will be in an inconsistent state.
     *
     * Eg: node.setPreSubmitScript("javascript:askMeFirst(currentNode)");
     * 
     * @param preSubmitScript The preSubmitScript to set. Please make sure that the preSubmitScript returns a true or false.
     * If the script returns false, the node submit form / url will not be submitted/executed.
     */
    public void setPreSubmitScript(String preSubmitAction)
    {
        this.preSubmitScript = preSubmitAction;
    }

    /**
     * @return Returns the submitForm.
     */
    public String getSubmitFormName()
    {
        return submitFormName;
    }

    /**
     * Sets the form to be submitted when this node is clicked. Each node can either have a url or a form name 
     * associated with it. If you want to set a url, use the setUrl method. If you want to set 
     * the form name, use setSubmitForm method.
     * 
     * @param submitForm The name of the form to be submitted. The form must exist on the page, or else an error will be thrown
     */
    public void setSubmitFormName(String submitFormName)
    {
        this.submitFormName = submitFormName;
    }

    /**
     * @return Returns the url associated with the node.
     */
    public String getUrl()
    {
        return url;
    }
    
    /**
     * @return Returns the target of the url, associated with the node. The urlTargetName can 
     * optionally be set when you set the url for the node.
     */
    public String getUrlTargetName()
    {
        return urlTargetName;
    }

    /**
     * Sets the url associated with the node. Each node can either have a url or a form name associated 
     * with it. If you want to set a url, use the setUrl method. If you want to set the form name, use setSubmitForm method.
     * 
     * @param url The url to set.
     * @param urlTargetName If you want the url to open in a separate frame or a window, please specify the name here. 
     * If not specified, when the user clicks on the node, the url will replace the window's contents. 
     * 
     * Typically this is used when you are displaying the tree in a webpage with frames. 
     * You would want to specify the frame name as this parameter where you want the contents to be displayed.
     * 
     */
    public void setUrl(String url, String urlTargetName)
    {
        // note that if the user creates a url, we still will submit a form, after creating a new form  
        // whose name is going to be the nodePath, separated by the underscore character - "_".
        this.url = url;
        this.urlTargetName = urlTargetName;
    }

    /**
     * @return Returns the icon source for the image shown when the node is closed. Should be a 16x16 pixel image
     */
    public String getIconSrcClosed()
    {
        return iconClosed;
    }

    /**
     * Set the icon source for the image shown when the node is closed. Should be a 16x16 pixel image
     * @param iconClosed The iconClosed to set.
     */
    public void setIconSrcClosed(String iconClosed)
    {
        this.iconClosed = iconClosed;
    }

    /**
     * @return Returns the icon source for the image shown when the node is expanded. Should be a 16x16 pixel image
     */
    public String getIconSrcOpen()
    {
        return iconOpen;
    }

    /**
     * Returns the icon source for the image shown when the node is expanded. Should be a 16x16 pixel image
     * @param iconOpen The iconOpen to set.
     */
    public void setIconSrcOpen(String iconOpen)
    {
        this.iconOpen = iconOpen;
    }

    /**
     * @return Returns the name of the node
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name Set the name of the node
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Return a list of all the children TreeNode objects of this tree node.
     * @return An list containing children of the tree node.
     */
    public List getChildren()
    {
        return children;
    }

    /**
     * @return Returns the popup associated with the node, which is invoked when the user right clicks on the node.
     */
    public TreePopup getPopup()
    {
        return popup;
    }

    /**
     * @param popup Set the popup associated with the node.
     */
    public void setPopup(TreePopup popup)
    {
        this.popup = popup;
    }

    /**
     * @return Returns the text which is displayed when the user hovers the mouse over the node image 
     */
    public String getPopupText()
    {
        return popupText;
    }

    /**
     * @param popupText Sets the text which is displayed when the user hovers the mouse over the node image.
     */
    public void setPopupText(String popupText)
    {
        this.popupText = popupText;
    }
    
    /**
     * @return Returns the data field of the tree node. Each tree node can have an string 
     * data field, which can have any arbitrary string data. This can be accessed in the javascript by using the node.data modifier.
     */
    public String getData()
    {
        return data;
    }

    /**
     * Set the data field of the tree node. Each TreeNode can optionally have an abitrary 
     * data field if required. User can then access this field in the page javascript if required
     * @param data The data to set.
     */
    public void setData(String data)
    {
        this.data = data;
    }


    /**
     * Return whether or not this node supports pagination for its children.
     * @return Returns boolean indicating pagination support. Default is false.
     */
    public boolean isPaginated()
    {
        return isPaginated;
    }

    /**
     * Sets up a node to support pagination for its children. By default a node does not
     * support pagination for the children. If pagination support is enabled, the user is
     * required to fill in the paginatedNodeChildCount variable also. This is set using the 
     * setPaginatedNodeChildCount method.
     * 
     * @param isPaginated The isPaginated to set.
     */
    public void setPaginated(boolean isPaginated)
    {
        this.isPaginated = isPaginated;
    }

    /**
     * Returns the pageSize of the paginated node. The default page size of a paginated node is 10.
     * @return the page size
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * Set the page size of a paginated node. Default is 10, which means that if a node has more than
     * 10 children, the node will be displayed with the prev,next links.
     * @param pageSize The pageSize to set.
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * Return the number of children a paginated node has.
     * @return Returns the paginatedNodeChildCount.
     */
    public int getPaginatedNodeChildCount()
    {
        return paginatedNodeChildCount;
    }

    /**
     * Sets the number of children a paginated node has. This is required to be set if a node is designated to be paginated.
     * @param paginatedNodeChildCount The paginatedNodeChildCount to set.
     */
    public void setPaginatedNodeChildCount(int paginatedNodeChildCount)
    {
        this.paginatedNodeChildCount = paginatedNodeChildCount;
    }

    /**
     * Sets the page range for the node. This indicates the tree node renderer what is the current child range 
     * being displayed
     * in the browser.
     * @param begin  The begin index of the child being displayed.
     */
    public void setPageRangeBegin(int begin)
    {
        this.pageRangeBegin = begin;
    }
    
    /**
     * Return the begin page range of the node. The page range of a paginated node
     * is set by using the setPageRange method.
     * @return The begin page range. For a non-paginated node, the default is -1.
     */
    public int getPageRangeBegin() { return pageRangeBegin; }
    
    /**
     * Add an extra property on the tree node. This can then be accessed in the page by using the node.<property name> method.
     * @param name
     * @param value
     */
    public void addProperty(String name, String value)
    {
        if(name == null) 
            return;
        
        props.put(name, value);
    }
    
    /**
     * Get a named property for the node, which has been added by the addProperty method.
     * @param name
     * @return The value of the property, or null if property does not exist.
     */
    public String getProperty(String name)
    {
        return (String)props.get(name);
    }

    /**
     * Convert some characters into html specific chars.
     * '<' becomes &lt;
     * '>' becomes &gt;
     * '&' becomes &amp;
     * '"' becomes &quot;
     * "'" becomes &#39;
     * @param source
     * @return String
     */
    public static String htmlChars(String source)
    {
        if(source == null)
            return source;

        String target = source;

        target = target.replaceAll("&", "&amp;");
        target = target.replaceAll("<", "&lt;");
        target = target.replaceAll(">", "&gt;");
        // target = target.replaceAll("\"", "&quot;");
        // target = target.replaceAll("'", "&#39;");
        return target;
    }

    /**
     * Escape quotes from the passed parameter
     * @return String
     */
    public static String escapeQuotes(String source)
    {
        String retVal = source;

         if(source == null)
              return retVal;

         retVal = retVal.replaceAll("'", "\\\\u0027");
         retVal = retVal.replaceAll("\"", "\\\\u0022");
         retVal = retVal.replaceAll("\\n", "\\\\u000A");
         retVal = retVal.replaceAll("\\t", "\\\\u0009");
         
         return retVal;
    }

    /**
     * Remove the unicode characters from the string and replace them with real quotes
     * @param source
     * @return String
     */
    public static String unescapeQuotes(String source)
    {
        if(source == null)
            return source;

        source = source.replaceAll("\\\\u0027", "'");
        source = source.replaceAll("\\\\u0022", "\"");

        return source;
    }

    /**
     * Get the JavascriptDefinition of the TreeNode
     * @param parent - This is the javascript representation of the parent node. Should be null for the root node
     * @return String
     */
    protected String getJavascriptDefinition(String parentVarName)
    {
        StringBuffer scriptBuffer = new StringBuffer(100);
        String script = "";

        if(parentVarName == null)
            scriptBuffer.append("    " + getVarName() +  " = " + "new JSTreeNode(");
        else
            scriptBuffer.append("    " + getVarName() +  " = " + parentVarName + ".addChild(");

        scriptBuffer.append("'" + escapeQuotes(getName()) + "'");
            
        if(getUrl() != null)
        {
            scriptBuffer.append(", '" + getUrl() + "'");
        } 
        else if(getSubmitFormName() != null) 
        {
            scriptBuffer.append(", '" + getSubmitFormName() + "'");
        } 
        else 
        {
            scriptBuffer.append(", null");
        }            

        if(getIconSrcOpen() != null) {
            scriptBuffer.append(", '" + getIconSrcOpen() + "'");
        } else {
            scriptBuffer.append(", null");
        }

        if(getIconSrcClosed() != null) {
            scriptBuffer.append(", '" + getIconSrcClosed() + "'");
        } else {
            scriptBuffer.append(", null");
        }

        if(getPopupText() != null) {
            scriptBuffer.append(", '" + escapeQuotes(getPopupText()) + "'");
        } else {
            scriptBuffer.append(", null");
        }

        if(getPopup() != null) {
            scriptBuffer.append(", '" + getPopup().getName() + "'");
        } else {
            scriptBuffer.append(", null");
        }

        if(isExpanded || parentVarName == null) {  // the root is always expanded
            scriptBuffer.append(", true");
        } else {
            scriptBuffer.append(", false");
        }

        if(hasChildren) {
            scriptBuffer.append(", true");
        } else {
            scriptBuffer.append(", false");
        }

        if(tree == null || tree.getName() == null)
            throw new IllegalArgumentException("Each node should have a tree defined. Either the tree is null, or the name of tree is null.");

        scriptBuffer.append(", '" + escapeQuotes(tree.getName()) + "'");
       
        if(preSubmitScript != null)
        {
            scriptBuffer.append(", '" + preSubmitScript + "'");
        }
        
        
        scriptBuffer.append(");\n");

        /** Display name should be set here */
        if(getDisplayName() != null)
            scriptBuffer.append("    " + getVarName() + ".displayName = '" + getDisplayName() + "';\n");
        
        /** Set the data field */
        if(getData() != null)
            scriptBuffer.append("    " + getVarName() + ".data = '" + getData() + "';\n");
        
        /** Let us generate pagination code for a node which supports pagination */
        if(isPaginated())
        {
            if(getPaginatedNodeChildCount() == 0)
                throw new IllegalArgumentException("Please set the paginated node child count for the node. (using the setPaginatedNodeChildCount() method.)");
            
            scriptBuffer.append("    " + getVarName() + ".isPaginated=true;\n");
            scriptBuffer.append("    " + getVarName() + ".paginatedNodeChildCount="+ getPaginatedNodeChildCount()+";\n");
            scriptBuffer.append("    " + getVarName() + ".pageSize="+ getPageSize()+";\n");
            scriptBuffer.append("    " + getVarName() + ".pageRangeBegin=" + (getPageRangeBegin() == -1 ? "0" : String.valueOf(getPageRangeBegin())) +";\n");
        }
        
        // we need to set the target for the url if it is specified.
        if(getUrl() != null && getUrlTargetName() != null)
            scriptBuffer.append("    " + getVarName() + "._target = '" + getUrlTargetName() + "';\n");
        
        /** since the tree could have extra properties, let us generate those.... */
        Enumeration enumVar = props.propertyNames();
        while (enumVar.hasMoreElements())
        {
            String element = (String) enumVar.nextElement();
            scriptBuffer.append("    " + getVarName() + "." + element + "='" + escapeQuotes(props.getProperty(element)) + "';\n");
        }

        return scriptBuffer.toString();

    }

    /**
     * The equals method is over-ridden for the TreeNode. If the passed in object is a TreeNode,
     * the equality test is based on the node path of the tree nodes.
     */
    public boolean equals(Object obj)
    {
        if(obj == null || !(obj instanceof TreeNode))
            return false;
        
        return getNodePath().equals(((TreeNode)obj).getNodePath());
    }

    public String toString()
    {
        return "TreeNode: name="+name + ", path="+nodePath+", submitFormName="+submitFormName+", data="+data+", displayName="+displayName;
    }
}

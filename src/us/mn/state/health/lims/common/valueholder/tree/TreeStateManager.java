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

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class which stores the state of the tree in the user session. The state of the tree can include 
 * expanded nodes, highlighted node, paginated node etc.
 * 
 * @author jalpesh
 *
 */
public class TreeStateManager 
   implements java.io.Serializable
{
    /** The object which stores all the expanded nodes */
    private Hashtable expandedNodes = new Hashtable();
    
    /** 
     * Hashtable to keep track of the paginated nodes by the user. It will store
     * keys in form of <nodePath> and the value will be the PageNodeData class.
     */
    private Hashtable paginatedNodes = new Hashtable();
    
    /**
     * Hashtable which can store user data for the tree. 
     */
    private Hashtable userData = new Hashtable();
    
    private static final String prefix = "TreeState";
    private String name;                  // name of the tree
    private String expandNodesFromPath;   // indicates that all nodes below this node are to be expanded
    private String highlightedNodePath;   // indicate the path to the highlighted node
    private String highlightedNodeData;   // the data field associated with the highlighted node
    private String highlightedNodeName;   // the name of the highlighted node
    private TreePopup highlightedNodePopup;  // the popup item associated with the selected node
    
    /**
     * PaginatedNodeData
     * 
     * Contains data related to the paginated node
     * 
     */
    public class PaginatedNodeData
        implements Serializable
    {
        protected String nodePath;
        protected String pageBegin = null;

        protected int pageSize = -1;
        protected int pageAction = -1;
        
        protected String firstChildName;
        protected String firstChildData;
        protected String firstChildPath;
        
        protected String lastChildName;
        protected String lastChildData;
        protected String lastChildPath;
        
        public static final int MOVE_FORWARD = 1;
        public static final int MOVE_BACK = 2;
        
        /**
         * Private method used by the TreeStateManager
         * @param request
         */
        protected PaginatedNodeData(HttpServletRequest request)
        {
            if(request == null)
                return;
            
            nodePath = request.getParameter(name + "_pageRequestNodePath");
            pageBegin = request.getParameter(name + "_pageBegin");

            if(!isEmpty(request, name + "_pageAction"))
               pageAction = Integer.parseInt(request.getParameter(name + "_pageAction"));
            
            if(!isEmpty(request, name + "_pageSize"))
                pageSize = Integer.parseInt(request.getParameter(name + "_pageSize"));
            
            firstChildName = request.getParameter(name + "_firstChildName");
            firstChildPath = request.getParameter(name + "_firstChildPath");
            firstChildData = request.getParameter(name + "_firstChildData");
            lastChildName  = request.getParameter(name + "_lastChildName");
            lastChildPath  = request.getParameter(name + "_lastChildPath");
            lastChildData  = request.getParameter(name + "_lastChildData");
        }
        
        /** 
         * Return the page range begin for this paginated tree node
         * @return
         */
        public int getPageRangeBegin()
        {
            if(pageBegin == null)
                return -1;
            else
                return Integer.parseInt(pageBegin);
        }
        
        /**
         * Returns the action performed by the user on the paginated node.
         * 
         * @return An integer which could be MOVE_FORWARD, if the user had hit the next button,
         * or MOVE_BACKWARD if the user had hit the previous button. If no action has been performed
         * it will return -1
         */
        public int getPageAction()
        {
            return pageAction;
        }
        
        /**
         * Return the data field of the first child in the current window of the paginated node.
         * 
         * @return Returns the firstChildData field.
         */
        public String getFirstChildData()
        {
            return firstChildData;
        }

        /**
         * Return the name of the first child in the current window of the paginated node.
         * 
         * @return Returns the firstChildName.
         */
        public String getFirstChildName()
        {
            return firstChildName;
        }

        /**
         * Return the node path of the first child in the current window of the paginated node.
         * 
         * @return Returns the firstChildPath.
         */
        public String getFirstChildPath()
        {
            return firstChildPath;
        }

        /**
         * Return the data field of the last child in the current window of the paginated node.
         * 
         * @return Returns the lastChildData.
         */
        public String getLastChildData()
        {
            return lastChildData;
        }

        /**
         * Return the name of the last child in the current window of the paginated node.
         * 
         * @return Returns the lastChildName.
         */
        public String getLastChildName()
        {
            return lastChildName;
        }

        /**
         * Return the node path of the last child in the current window of the paginated node.
         * 
         * @return Returns the lastChildPath.
         */
        public String getLastChildPath()
        {
            return lastChildPath;
        }

        /**
         * @return Returns the pageSize of the paginated node.
         */
        public int getPageSize()
        {
            return pageSize;
        }

        /**
         * Sample toString implementation for debugging.
         */
        public String toString()
        {
            return "Paginated Node is :" + nodePath + "\n" +
                   "   PageAction     :" + pageAction + "\n" +
                   "   PageBegin      :" + pageBegin + "\n" +
                   "   firstChildName :" + firstChildName + "\n" +
                   "   firstChildData :" + firstChildData + "\n" +
                   "   firstChildPath :" + firstChildPath + "\n" +
                   "   lastChildName  :" + lastChildName + "\n" +
                   "   lastChildData  :" + lastChildData + "\n" +
                   "   lastChildPath  :" + lastChildPath + "\n";
        }
        
    }
    
    
    protected TreeStateManager(String name)
    {
        this.name = name;      // name of the tree
    }

    /**
     * Return an instance of the TreeStateManager for the tree with the given name. If the object does not exist
     * in the session, it creates a new one and saves it in the user session.
     * @param session The session object
     * @param name The name of the tree
     * @return TreeStateManager instance.
     */
    public static TreeStateManager getInstance(HttpSession session, String name)
    {
        TreeStateManager tsm = (TreeStateManager)session.getAttribute(prefix+name);
        if(tsm == null)
        {
            tsm = new TreeStateManager(name);
            session.setAttribute(prefix+name, tsm);
        }
        
        return tsm;
    }
    
    /**
     * @return Returns the selected popup item of the tree node.
     */
    public TreePopup getHighlightedNodePopup()
    {
        return highlightedNodePopup;
    }

    /**
     * @return Returns the highlightedNodeName.
     */
    public String getHighlightedNodeName()
    {
        return highlightedNodeName;
    }

    /**
     * @param highlightedNodeName The highlightedNodeName to set.
     */
    public void setHighlightedNodeName(String highlightedNodeName)
    {
        this.highlightedNodeName = highlightedNodeName;
    }

    /**
     * @return Returns the highlightedNodeData.
     */
    public String getHighlightedNodeData()
    {
        return highlightedNodeData;
    }

    /**
     * @param highlightedNodeData The highlightedNodeData to set.
     */
    public void setHighlightedNodeData(String highlightedNodeData)
    {
        this.highlightedNodeData = highlightedNodeData;
    }

    /**
     * @return Returns the highlightedNodePath.
     */
    public String getHighlightedNodePath()
    {
        return highlightedNodePath;
    }

    /**
     * @param highlightedNodePath The highlightedNodePath to set.
     */
    public void setHighlightedNodePath(String highlightedNodePath)
    {
        this.highlightedNodePath = highlightedNodePath;
    }

    /**
     * Remove the highlighted node information from the manager
     *
     */
    public void removeHighlightedNodeData()
    {
        this.highlightedNodeData  = null;
        this.highlightedNodePath  = null;
        this.highlightedNodeData  = null;
        this.highlightedNodePopup = null;
    }

    /**
     * Process the state of the tree after a user has performed either of the following actions in the tree -  
     * expanded a node, collapsed a node, zoomed in or out, paged through a window or selected a node.
     * @param request The request object which would contain this information
     */
    public void processState(HttpServletRequest request)
    {
        // check to ensure that expand all nodes (below a nodePath) is not called. If it is not invoked,
        // the value will be null.
        setExpandNodesFromPath(request.getParameter(name+"expandNodesFromPath"));
        
        // if expand action was called on a node path, execute that
        addNodeAsExpanded(request.getParameter(name+"expandNode"));
        
        // if collapse action was called on a node, remove that node as expanded
        removeNodeAsExpanded(request.getParameter(name+"collapseNode"));

        // if a node was selected, mark it has highlighted, and also store the data field
        // we only do this if there is a request to highlight a certain node. So the net effect
        // is that a highlighted node is always sticky, it remains highlighted until somebody specifically
        // unhighlights it, or over-writes it with a different highlighted node
        if(!isEmpty(request, name+"highlightNodePath"))
        {
            setHighlightedNodePath(request.getParameter(name+"highlightNodePath"));
            setHighlightedNodeData(request.getParameter(name+"highlightNodeData"));
            setHighlightedNodeName(request.getParameter(name+"highlightNodeName"));
        }
        
        // set the popup item if they exists...
        setPopupItems(request);
        
        // if node is paginated, figure retrieve the associated data
        retrievePaginationData(request);
    }
    
    /**
     * Retrieve the popup items from the request.
     * @param request
     */
    private void setPopupItems(HttpServletRequest request)
    {
        if(!isEmpty(request, name + "popupName"))
        {
            highlightedNodePopup = new TreePopup(name + "popupName");
            int i=0;
            while(!isEmpty(request, name + "popup" + i + ".text"))
            {
                String text = request.getParameter(name + "popup" + i + ".text");
                String action = request.getParameter(name + "popup" + i + ".action");
                String imageSrc = request.getParameter(name + "popup" + i + ".imageSrc");

                highlightedNodePopup.addPopupItem(text, action, imageSrc);
                i++;
            } 
            
        } else
        {
            highlightedNodePopup = null;
        }
        
    }

    /** Retrieve pagination information from the request */
    private void retrievePaginationData(HttpServletRequest request)
    {
        if(!isEmpty(request, name + "_pageRequestNodePath"))
        {           
            PaginatedNodeData png = new PaginatedNodeData(request);
            
            // store this object in the hashtable.
            paginatedNodes.put(request.getParameter(name + "_pageRequestNodePath"), png);
        }
    }
    
    /**
     * Return whether or not the given node path is paginated or not.
     * @param nodePath The path of the node
     * @return true or false depending whether the node is paginated or not.
     */
    public boolean isNodePathPaginated(String nodePath)
    {
        return paginatedNodes.get(nodePath) != null;
    }
    
    /**
     * Force a particular node path to be paginated, if it is not already paginated.
     * @param nodePath
     * @return
     */
    public void addNodePathAsPaginated(String nodePath)
    {
        if(!isNodePathPaginated(nodePath))
            paginatedNodes.put(nodePath, new PaginatedNodeData(null));
    }

    /**
     * Remove the given node as being paginated.
     * @param node The tree node object
     */
    public void removeNodePathAsPaginated(String nodePath)
    {
        if(isNodePathPaginated(nodePath))
            paginatedNodes.remove(nodePath);
    }
    
     
    /**
     * Return the data associated with the paginated node
     * @param node The path of the tree node
     * @return The paginated node data for the given tree node
     */
    public PaginatedNodeData getPaginatedNodeData(String nodePath)
    {
        PaginatedNodeData pageNodeData = (PaginatedNodeData)paginatedNodes.get(nodePath);
        return pageNodeData;
    }
    
    /**
     * Marks all nodes below the given path to be expanded.
     * @param nodePath The path to the tree node.
     */
    public void setExpandNodesFromPath(String nodePath)
    {
        this.expandNodesFromPath = nodePath;
    }
    
    public String getExpandNodesFromPath()
    {
        return expandNodesFromPath;
    }
    
    /**
     * Add a particular node, identified by its path as expanded in the tree.
     * @param nodePath
     */
    public void addNodeAsExpanded(String nodePath)
    {
        if(nodePath == null || nodePath.length() == 0)
            return;

        expandedNodes.put(nodePath, "true"); 
    }
    
    /**
     * Indicate whether or not the given node, identified by its path is expanded or not
     * @param nodePath The node path
     * @return
     */
    public boolean isNodeExpanded(String nodePath)
    {
        if(expandedNodes.get(nodePath) != null) 
        {
            return true;
        }
        else if(getExpandNodesFromPath() != null && nodePath.startsWith(getExpandNodesFromPath()) )
        {
            addNodeAsExpanded(nodePath);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Return the Enumeration of the expanded nodes 
     * @return Enumeration of the expanded nodes containing key values.
     */
    public Enumeration getExpandedNodes()
    {
        return expandedNodes.keys();
    }
    
    /**
     * Reset the tree state manager to its default state - removing any expanded nodes, highlighted nodes,
     * paginated nodes or user data.
     * 
     */
    public void reset()
    {
        expandedNodes = new Hashtable();
        paginatedNodes = new Hashtable();
        userData = new Hashtable();
        expandNodesFromPath = null;
        highlightedNodePath = null;
        highlightedNodeData = null;   // the data field associated with the highlighted node
        highlightedNodeName = null;   // the name of the highlighted node
        highlightedNodePopup = null;  // the popup item associated with the selected node
    }
    
    /**
     * Removes the node from the expanded node list in the tree (ie marks it as collapsed).
     * @param nodePath
     */
    public void removeNodeAsExpanded(String nodePath)
    {
        if(nodePath == null || nodePath.length() == 0)
            return;
        
        expandedNodes.remove(nodePath);
    }
    
    // helper method to make sure that the parameter in the request is not empty
    private boolean isEmpty(HttpServletRequest request, String param)
    {
        return (request.getParameter(param) == null || request.getParameter(param).length() == 0);
    }
    
    /**
     * Allow a user to store a custom object in the TreeStateManager. Please note that the key and the value
     * objects should both be serializable.
     * @param key   The key to lookup the value ob
     * @param value
     */
    public void put(Object key, Object value)
    {
        userData.put(key, value);
    }

    /**
     * Allow a user to remove a custom object in the TreeStateManager.
     * @param key   The key to remove 
     */
    public Object remove(Object key)
    {
        return userData.remove(key);
    }
    
    /**
     * Retrieve data which was stored using the put method.
     * @param key
     * @return
     */
    public Object get(Object key)
    {
        return userData.get(key);
    }
}

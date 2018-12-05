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
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a PopupMenu which is displayed when a user right clicks on a node. 
 * Tree popup are unique to each tree and each node can have one TreePopup associated with it. When creating a TreePopup object, 
 * care should be taken that the name of the TreePopup is unique within that tree.
 * 
 * @author jalpesh
 */
public class TreePopup implements Serializable
{
    private String name;         // the name of the popup
    private List popupItems;     // the items in the popup
    private int maxSize;
    private String treeName;     // each popup should know what tree it belongs to

    /**
     * Each popup item in the popup
     */ 
    public class PopupItem implements Serializable
    {
        private String text;
        private String imageSrc;      // the image associated with the popup
        private String action;        // the action associated with the popup

        public PopupItem(String text, String action) {
            this(text, action, null);
        }
        
        public PopupItem(String text, String action, String imageSrc) {
            this.text = text;
            this.action = action;
            this.imageSrc = imageSrc;
        }
        
        /**
         * Return the text associated with the popup
         * @return
         */
        public String getText()      { return text; }
        
        /**
         * Return the Image Src associated with the popup.
         * @return
         */
        public String getImageSrc()  { return imageSrc; }
        
        /**
         * Return the action associated with the popup
         * @return
         */
        public String getAction()    { return action; }
    }
    
    /**
     * Create a popup with the specified name. The tree popup object should be unique within a tree.
     */
    public TreePopup(String name)
    {
        this.name = name;
        this.popupItems = new ArrayList();       
    }

    /**
     * Create a copy of this tree popup object
     * @return new TreePopup object
     */
    public TreePopup clonePopup()
    {
        TreePopup newPopup = new TreePopup(this.name);
        newPopup.maxSize = maxSize;
        
        for(int i=0; i<popupItems.size(); i++)
        {
            PopupItem item = (PopupItem)popupItems.get(i);
            newPopup.addPopupItem(item.getText(), item.getAction(), item.getImageSrc());
        }
        
        return newPopup;
    }
    
    public List getPopupItems()
    {
        return popupItems;
    }
    
    /**
     * @return Returns the tree associated with the popup
     */
    protected String getTreeName()
    {
        return treeName;
    }

    /**
     * @param tree sets the tree to which this popup is associated
     */
    protected void setTreeName(String treeName)
    {
        this.treeName = treeName;
    }

    /**
     * Return the name of the popup.
     * @return popup name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Addd a popup item to the given popup
     * @param text The text to be displayed for the popup item
     * @param url The url to be executed for the popup item.
     */
    public void addPopupItem(String text, String action)
    {
        addPopupItem(text, action, null);
    }

    /**
     * Addd a popup item to the given popup
     * @param text The text to be displayed for the popup item
     * @param action The url or javascript action to be executed for the popup item.
     */
    public void addPopupItem(String text, String action, String imageSrc)
    {
        PopupItem item = new PopupItem(text, action, imageSrc);
        popupItems.add(item);

        maxSize = (int)Math.max((double)maxSize, (double)text.length());
        
    }
    
    /**
     * Get the script definition for this popup.
     * @return String
     */
    protected String getJavascriptDefinition()
    {
        String script = "";
        script += "<script language='javascript'>\n";
        script += "   var " + name + " = new Array;\n";

        for (int i = 0; i < popupItems.size(); i++)
        {
            PopupItem item = (PopupItem)popupItems.get(i);
            String text = item.text;

            // now pad the text with the string buffer.
            int extraSpace = maxSize - text.length();
            StringBuffer sb = new StringBuffer();
            for(int j=0; j<extraSpace; j++)
                sb.append(" ");

            text += sb.toString();

            script += "   " + name + ".push(new JSTreePopup('" + text + "','" + item.action + "'," + (item.imageSrc == null ? "null" : "'" + item.imageSrc + "'") + "));\n";
        }

        // store the array into the popup hashtable
        script += "    trees['"+treeName+"'].popups['" + name + "'] = " + name + ";\n";
        script += "</script>\n";
        return script;
    }
}

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
package us.mn.state.health.lims.common.servlet.selectdropdown;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Helper class to build valid XML typically returned in a response to the client.
 *
 *
 * author: benzd1
 * bugzilla 1844: extending AjaxXmlBuilder for testsection->test select
 * making extended version of AjaxJspTag.Select sortable (toggle between 
 * sorting by 2 different properties, toggling label value between 2 
 * different values according to sort
 */
public class AjaxXmlBuilderForSortableTests extends org.ajaxtags.helpers.AjaxXmlBuilder{

  private String encoding = "UTF-8";
  private List items = new ArrayList();

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  /**
   * Add item to XML.
   *
   * @param name The name of the item
   * @param value The value of the item
   * @return
   */
  public AjaxXmlBuilderForSortableTests addItem(String name, String value, String sortFieldA, String sortFieldB, String alternateLabel) {
    items.add(new SortableTestItem(name, value, sortFieldA, sortFieldB, alternateLabel, false));
    return this;
  }

  /**
   * Add item wrapped with inside a CDATA element.
   *
   * @param name The name of the item
   * @param value The value of the item
   * @return
   */
  public AjaxXmlBuilderForSortableTests addItemAsCData(String name, String value, String sortFieldA, String sortFieldB, String alternateLabel) {
    items.add(new SortableTestItem(name, value, sortFieldA, sortFieldB, alternateLabel, true));
    return this;
  }

  /**
   * Add items from a collection.
   *
   * @param collection
   * @param nameProperty
   * @param valueProperty
   * @return
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   */
  public AjaxXmlBuilderForSortableTests addItems(Collection collection, String nameProperty, String valueProperty, String sortFieldAProperty, String sortFieldBProperty, String alternateLabelProperty)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    return addItems(collection, nameProperty, valueProperty, sortFieldAProperty, sortFieldBProperty, alternateLabelProperty, false);
  }

  /**
   * Add items from a collection.
   *
   * @param collection
   * @param nameProperty
   * @param valueProperty
   * @return
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   */
  public AjaxXmlBuilderForSortableTests addItems(
      Collection collection, String nameProperty, String valueProperty, String sortFieldAProperty, String sortFieldBProperty, String alternateLabelProperty, boolean asCData)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    for (Iterator iter = collection.iterator(); iter.hasNext();) {
      Object element = (Object) iter.next();
      String name = BeanUtils.getProperty(element, nameProperty);
      String value = BeanUtils.getProperty(element, valueProperty);
      String sortFieldA = BeanUtils.getProperty(element, sortFieldAProperty);
      String sortFieldB = BeanUtils.getProperty(element, sortFieldBProperty);
      String alternateLabel = BeanUtils.getProperty(element, alternateLabelProperty);
      if (asCData) {
        items.add(new SortableTestItem(name, value, sortFieldA, sortFieldB, alternateLabel, false));
      } else {
        items.add(new SortableTestItem(name, value, sortFieldA, sortFieldB, alternateLabel, true));

      }
    }
    return this;
  }

  /**
   * Add items from a collection as CDATA element.
   *
   * @param collection
   * @param nameProperty
   * @param valueProperty
   * @return
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   */
  public AjaxXmlBuilderForSortableTests addItemsAsCData(
      Collection collection, String nameProperty, String valueProperty, String sortFieldAProperty, String sortFieldBProperty, String alternateLabelProperty)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    return addItems(collection, nameProperty, valueProperty, sortFieldAProperty, sortFieldBProperty, alternateLabelProperty, true);
  }

  /**
   * @see java.lang.Object#toString()
   */
  public String toString() {
    StringBuffer xml = new StringBuffer().append("<?xml version=\"1.0\"");
    if (encoding != null) {
      xml.append(" encoding=\"");
      xml.append(encoding);
      xml.append("\"");
    }
    xml.append(" ?>");

    xml.append("<ajax-response>");
    xml.append("<response>");
    for (Iterator iter = items.iterator(); iter.hasNext();) {
    	SortableTestItem item = (SortableTestItem) iter.next();
      xml.append("<item>");
      xml.append("<name>");
      if (item.isAsCData()) {
        xml.append("<![CDATA[");
      }
      xml.append(item.getName());
      if (item.isAsCData()) {
        xml.append("]]>");
      }
      xml.append("</name>");
      xml.append("<value>");
      if (item.isAsCData()) {
        xml.append("<![CDATA[");
      }
      xml.append(item.getValue());
      if (item.isAsCData()) {
        xml.append("]]>");
      }
      xml.append("</value>");
      xml.append("<sortFieldA>");
      if (item.isAsCData()) {
        xml.append("<![CDATA[");
      }
      xml.append(item.getSortFieldA());
      if (item.isAsCData()) {
        xml.append("]]>");
      }
      xml.append("</sortFieldA>");
      xml.append("<sortFieldB>");
      if (item.isAsCData()) {
        xml.append("<![CDATA[");
      }
      xml.append(item.getSortFieldB());
      if (item.isAsCData()) {
        xml.append("]]>");
      }
      xml.append("</sortFieldB>");
      
      xml.append("<alternateLabel>");
      if (item.isAsCData()) {
        xml.append("<![CDATA[");
      }
      xml.append(item.getAlternateLabel());
      if (item.isAsCData()) {
        xml.append("]]>");
      }
      xml.append("</alternateLabel>");
      
      xml.append("</item>");
    }
    xml.append("</response>");
    xml.append("</ajax-response>");

    return xml.toString();

  }

}

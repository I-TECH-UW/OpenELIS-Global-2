/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at http://www.mozilla.org/MPL/
 *
 * <p>Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF
 * ANY KIND, either express or implied. See the License for the specific language governing rights
 * and limitations under the License.
 *
 * <p>The Original Code is OpenELIS code.
 *
 * <p>Copyright (C) The Minnesota Department of Health. All Rights Reserved.
 */
package org.openelisglobal.common.servlet.selectdropdown;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.openelisglobal.common.util.XMLUtil;
import org.owasp.encoder.Encode;

/**
 * Helper class to build valid XML typically returned in a response to the
 * client.
 *
 * <p>
 * author: benzd1 bugzilla 1844: extending AjaxXmlBuilder for testsection->test
 * select making extended version of AjaxJspTag.Select sortable (toggle between
 * sorting by 2 different properties, toggling label value between 2 different
 * values according to sort
 */
public class AjaxXmlBuilderForSortableTests extends org.ajaxtags.helpers.AjaxXmlBuilder {

    private String encoding = "UTF-8";
    private List items = new ArrayList();

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * Add item to XML.
     *
     * @param name  The name of the item
     * @param value The value of the item
     * @return
     */
    public AjaxXmlBuilderForSortableTests addItem(String name, String value, String sortFieldA, String sortFieldB,
            String alternateLabel) {
        items.add(new SortableTestItem(name, value, sortFieldA, sortFieldB, alternateLabel, false));
        return this;
    }

    /**
     * Add item wrapped with inside a CDATA element.
     *
     * @param name  The name of the item
     * @param value The value of the item
     * @return
     */
    public AjaxXmlBuilderForSortableTests addItemAsCData(String name, String value, String sortFieldA,
            String sortFieldB, String alternateLabel) {
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
    public AjaxXmlBuilderForSortableTests addItems(Collection collection, String nameProperty, String valueProperty,
            String sortFieldAProperty, String sortFieldBProperty, String alternateLabelProperty)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return addItems(collection, nameProperty, valueProperty, sortFieldAProperty, sortFieldBProperty,
                alternateLabelProperty, false);
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
    public AjaxXmlBuilderForSortableTests addItems(Collection collection, String nameProperty, String valueProperty,
            String sortFieldAProperty, String sortFieldBProperty, String alternateLabelProperty, boolean asCData)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            Object element = iter.next();
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
    public AjaxXmlBuilderForSortableTests addItemsAsCData(Collection collection, String nameProperty,
            String valueProperty, String sortFieldAProperty, String sortFieldBProperty, String alternateLabelProperty)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return addItems(collection, nameProperty, valueProperty, sortFieldAProperty, sortFieldBProperty,
                alternateLabelProperty, true);
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder xml = new StringBuilder().append("<?xml version=\"1.0\"");
        if (encoding != null) {
            XMLUtil.appendAttributeKeyValue("encoding", encoding, xml);
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
                xml.append(Encode.forCDATA(item.getName()));
                xml.append("]]>");
            } else {
                xml.append(Encode.forXmlContent(item.getName()));
            }
            xml.append("</name>");

            xml.append("<value>");
            if (item.isAsCData()) {
                xml.append("<![CDATA[");
                xml.append(Encode.forCDATA(item.getValue()));
                xml.append("]]>");
            } else {
                xml.append(Encode.forXmlContent(item.getValue()));
            }
            xml.append("</value>");

            xml.append("<sortFieldA>");
            if (item.isAsCData()) {
                xml.append("<![CDATA[");
                xml.append(Encode.forCDATA(item.getSortFieldA()));
                xml.append("]]>");
            } else {
                xml.append(item.getSortFieldA());
            }
            xml.append("</sortFieldA>");

            xml.append("<sortFieldB>");
            if (item.isAsCData()) {
                xml.append("<![CDATA[");
                xml.append(Encode.forCDATA(item.getSortFieldB()));
                xml.append("]]>");
            } else {
                xml.append(Encode.forXmlContent(item.getSortFieldB()));
            }
            xml.append("</sortFieldB>");

            xml.append("<alternateLabel>");
            if (item.isAsCData()) {
                xml.append("<![CDATA[");
                xml.append(Encode.forCDATA(item.getAlternateLabel()));
                xml.append("]]>");
            } else {
                xml.append(Encode.forXmlContent(item.getAlternateLabel()));
            }
            xml.append("</alternateLabel>");

            xml.append("</item>");
        }
        xml.append("</response>");
        xml.append("</ajax-response>");

        return xml.toString();
    }
}

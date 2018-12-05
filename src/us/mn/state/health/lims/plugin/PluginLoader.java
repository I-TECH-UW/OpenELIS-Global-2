/*
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
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 */

package us.mn.state.health.lims.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.InstantiationException;

import us.mn.state.health.lims.common.exception.LIMSException;
import us.mn.state.health.lims.common.log.LogEvent;

public class PluginLoader {
    private static final String PLUGIN_ANALYZER = "/plugin" + File.separator;
    private static final String VERSION = "version";
    private static final String SUPPORTED_VERSION = "1.0";
    private static final String PATH = "path";
    private static final String ANALYZER_IMPORTER = "analyzerImporter";
    private static final String MENU = "menu";
    private static final String PERMISSION = "permission";
    private static final String EXTENSION_POINT = "extension_point";
    private static final String EXTENSION = "extension";
    private static final String DESCRIPTION = "description";
    private static final String VALUE = "value";
    private int JDK_VERSION_MAJOR;
    private int JDK_VERSION_MINOR;
    private ServletContext context;

    private static List<String> currentPlugins;
    
    private static void registerPluginNames(List<String> listOfPlugins) {
    	if (currentPlugins == null) {
    		currentPlugins = listOfPlugins;
    	}
    }
    
    public PluginLoader(ServletContextEvent event) {
        context = event.getServletContext();
    }

    public void load() {
        File pluginDir = new File(context.getRealPath(PLUGIN_ANALYZER));
        loadDirectory( pluginDir );
    }

    private void loadDirectory( File pluginDir ){
        String[] version = System.getProperty("java.version").split("\\.");
        JDK_VERSION_MAJOR = Integer.parseInt(version[0]);
        JDK_VERSION_MINOR = Integer.parseInt(version[1]);
        List<String> pluginList = new ArrayList<String>();

        File[] files = pluginDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith("jar")) {
                    loadPlugin(file);
                    pluginList.add(file.getName());
                }else if(file.isDirectory()){
                    System.out.println("Checking plugin subfolder: " + file.getName());
                    loadDirectory( file );
                }
            }
        }
        registerPluginNames(pluginList);
    }

    private void loadPlugin(File pluginFile) {

        try {
            JarFile jar = new JarFile(pluginFile);

            if (!checkJDKVersions(pluginFile.getName(), jar)) return;

            final Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                final JarEntry entry = entries.nextElement();
                if (entry.getName().contains(".xml")) {
                    boolean valid = loadFromXML(jar, entry);
                    if (valid) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkJDKVersions(String fileName, JarFile jar) throws IOException {
        Manifest manifest = jar.getManifest();
        if( manifest == null){
            LogEvent.logError("PluginLoader", "check jdk version", "Manifest file not in jar file, unable to check jdk versions");
            System.out.println("Manifest file not in jar file, unable to check jdk versions");
            return true;
        }

        String createdBy = manifest.getMainAttributes().getValue("Created-By");
        if( createdBy == null){
            LogEvent.logError("PluginLoader", "check jdk version", "JDK version not found in manifest file, unable to check jdk versions");
            System.out.println("JDK version not found in manifest file, unable to check jdk versions");
            return true;
        }

        String[] jarVersion = createdBy.split("\\.");
        int jarVersionMajor = Integer.parseInt(jarVersion[0]);
        int jarVersionMinor = Integer.parseInt( jarVersion[1]);
        if( jarVersionMajor > JDK_VERSION_MAJOR || ( jarVersionMajor == JDK_VERSION_MAJOR && jarVersionMinor > JDK_VERSION_MINOR )){
            LogEvent.logError("PluginLoader", "check jdk version", "The plugin " + fileName + " was compiled with a higher JDK version (" + getVersion(jarVersionMajor, jarVersionMinor) + ") than the runtime JDK (" + getVersion(JDK_VERSION_MAJOR, JDK_VERSION_MINOR) + ")");
            System.out.println("The plugin " + fileName + " was compiled with a higher JDK version (" + getVersion(jarVersionMajor, jarVersionMinor) + ") than the runtime JDK (" + getVersion(JDK_VERSION_MAJOR, JDK_VERSION_MINOR) + ")");
            return false;
        }
        return true;
    }

    private String getVersion(int major, int minor) {
        return major + "." + minor;
    }

    private boolean loadFromXML(JarFile jar, JarEntry entry) {
        Attribute description = null;
        try {
            URL url = new URL("jar:file:///" + jar.getName() + "!/");
            InputStream input = jar.getInputStream(entry);

            String xml = IOUtils.toString(input, "UTF-8");

            //System.out.println(xml);

            Document doc = DocumentHelper.parseText(xml);

            Element versionElement = doc.getRootElement().element(VERSION);

            if( versionElement == null){
                LogEvent.logError("PluginLoader", "load", "Missing version number in plugin");
                System.out.println("Missing version number in plugin");
                return false;
            }
            if (!SUPPORTED_VERSION.equals(versionElement.getData())) {
                LogEvent.logError("PluginLoader", "load", "Unsupported version number.  Expected " + SUPPORTED_VERSION + " got " + versionElement.getData());
                System.out.println("Unsupported version number.  Expected " + SUPPORTED_VERSION + " got " + versionElement.getData());
                return false;
            }

            Element analyzerImporter = doc.getRootElement().element(ANALYZER_IMPORTER);

            if (analyzerImporter != null) {
                description = analyzerImporter.element(EXTENSION_POINT).element(DESCRIPTION).attribute(VALUE);
                Attribute path = analyzerImporter.element(EXTENSION_POINT).element(EXTENSION).attribute(PATH);
                loadActualPlugin(url, path.getValue());
                System.out.println("Loaded: " + description.getValue());
            }

            Element menu = doc.getRootElement().element(MENU);

            if (menu != null) {
                description = menu.element(EXTENSION_POINT).element(DESCRIPTION).attribute(VALUE);
                Attribute path = menu.element(EXTENSION_POINT).element(EXTENSION).attribute(PATH);
                loadActualPlugin(url, path.getValue());
                System.out.println("Loaded: " + description.getValue());
            }

            Element permissions = doc.getRootElement().element(PERMISSION);

            if (permissions != null) {
                description = permissions.element(EXTENSION_POINT).element(DESCRIPTION).attribute(VALUE);
                Attribute path = permissions.element(EXTENSION_POINT).element(EXTENSION).attribute(PATH);
                loadActualPlugin(url, path.getValue());
                System.out.println( "Loaded: " + description.getValue());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        } catch (LIMSException e){
            if( description != null) {
                LogEvent.logError("PluginLoader", "load", "Failed Loading: " + description.getValue());
                System.out.println("Failed Loading: " + description.getValue());
            }
            return false;
        }


        return true;
    }


    @SuppressWarnings("unchecked")
    private void loadActualPlugin(URL url, String classPath) throws LIMSException {
        try {
            URL[] urls = {url};
            ClassLoader classLoader = new URLClassLoader(urls, this.getClass().getClassLoader());

            Class<APlugin> aClass = (Class<APlugin>) classLoader.loadClass(classPath);
            APlugin instance = aClass.newInstance();
            instance.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new LIMSException("See previous stack trace");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new LIMSException("See previous stack trace");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new LIMSException("See previous stack trace");
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
            throw new LIMSException("See previous stack trace");
		}

    }
    
    public static List<String> getCurrentPlugins() {
    	return currentPlugins;
    }
}

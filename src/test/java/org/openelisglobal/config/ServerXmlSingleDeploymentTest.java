package org.openelisglobal.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A WAR deploys twice when a Host's appBase scan ({@code autoDeploy} and
 * {@code deployOnStartup}, true while absent) covers a WAR an explicit
 * {@code <Context>} also declares, or when two {@code <Context>} children share
 * a {@code docBase}: the second context brings a second Spring root context and
 * scheduler, firing every {@code @Scheduled} method again.
 *
 * <p>
 * Tomcat 10.1's Host documentation says to "turn off automatic application
 * deployment" where contexts are defined explicitly in server.xml, or "the web
 * applications will each be deployed twice".
 */
public class ServerXmlSingleDeploymentTest {

    @Test
    public void hostsDeclaringAnInAppBaseContextDoNotAlsoScanAppBase() throws Exception {
        List<String> violations = new ArrayList<>();
        for (Path config : serverXmlConfigs()) {
            for (Element host : elementsNamed(parse(config), "Host")) {
                String appBase = host.getAttribute("appBase");
                long inAppBase = childElements(host, "Context").stream()
                        .filter(context -> resolvesInside(context.getAttribute("docBase"), appBase)).count();
                if (inAppBase == 0) {
                    continue;
                }
                for (String attribute : List.of("autoDeploy", "deployOnStartup")) {
                    // An absent deployOnStartup defaults to true and scans appBase anyway.
                    if (!(host.hasAttribute(attribute) && !Boolean.parseBoolean(host.getAttribute(attribute)))) {
                        violations.add(describe(config, host) + " declares " + inAppBase
                                + " in-appBase <Context> element(s) but " + attribute + " is "
                                + (host.hasAttribute(attribute) ? "\"" + host.getAttribute(attribute) + "\"" : "absent")
                                + " rather than \"false\", so that WAR deploys twice");
                    }
                }
            }
        }
        Assert.assertEquals(String.join("\n", violations), List.of(), violations);
    }

    @Test
    public void noTwoContextsOfOneHostShareADocBaseOrAPath() throws Exception {
        List<String> violations = new ArrayList<>();
        for (Path config : serverXmlConfigs()) {
            for (Element host : elementsNamed(parse(config), "Host")) {
                violations.addAll(repeatedAttribute(config, host, "docBase", "so that WAR deploys twice"));
                violations.addAll(
                        repeatedAttribute(config, host, "path", "so two web applications claim one context path"));
            }
        }
        Assert.assertEquals(String.join("\n", violations), List.of(), violations);
    }

    private static List<String> repeatedAttribute(Path config, Element host, String attribute, String consequence) {
        List<String> violations = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (Element context : childElements(host, "Context")) {
            String value = withoutTrailingSlash(context.getAttribute(attribute));
            if (!value.isEmpty() && !seen.add(value)) {
                violations.add(describe(config, host) + " declares more than one <Context> with " + attribute + "=\""
                        + value + "\", " + consequence);
            }
        }
        return violations;
    }

    private static String describe(Path config, Element host) {
        return config + ": <Host name=\"" + host.getAttribute("name") + "\" appBase=\"" + host.getAttribute("appBase")
                + "\">";
    }

    /**
     * A relative {@code docBase} resolves against {@code appBase}; an absolute one
     * is matched textually, since the file describes a container's layout.
     */
    private static boolean resolvesInside(String docBase, String appBase) {
        List<String> base = segments(appBase);
        List<String> doc = segments(docBase);
        if (base.isEmpty() || doc.isEmpty()) {
            return false;
        }
        if (!docBase.startsWith("/")) {
            return true;
        }
        for (int i = 0; i + base.size() < doc.size(); i++) {
            if (doc.subList(i, i + base.size()).equals(base)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> segments(String path) {
        List<String> segments = new ArrayList<>();
        for (String segment : Arrays.asList(path.split("/"))) {
            if (!segment.isEmpty() && !".".equals(segment)) {
                segments.add(segment);
            }
        }
        return segments;
    }

    private static String withoutTrailingSlash(String value) {
        return value.length() > 1 && value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    private static List<Path> serverXmlConfigs() throws IOException, InterruptedException {
        List<Path> configs = trackedFilesMatching("server.xml");
        Assert.assertFalse("found no tracked Tomcat server.xml to check; the search itself is broken",
                configs.isEmpty());
        return configs;
    }

    /**
     * Git's index rather than the filesystem: an untracked stray server.xml in
     * somebody's checkout is not this repo's configuration.
     */
    private static List<Path> trackedFilesMatching(String nameSuffix) throws IOException, InterruptedException {
        Process git = new ProcessBuilder("git", "ls-files", "-z").redirectErrorStream(true).start();
        String listing = new String(git.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        Assert.assertEquals("git ls-files failed: " + listing, 0, git.waitFor());
        List<Path> matches = new ArrayList<>();
        for (String entry : listing.split("\0")) {
            if (!entry.isEmpty() && Path.of(entry).getFileName().toString().endsWith(nameSuffix)) {
                matches.add(Path.of(entry));
            }
        }
        matches.sort(null);
        return matches;
    }

    private static Document parse(Path path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        return factory.newDocumentBuilder().parse(path.toFile());
    }

    private static List<Element> elementsNamed(Document document, String tagName) {
        List<Element> matches = new ArrayList<>();
        NodeList found = document.getElementsByTagName(tagName);
        for (int i = 0; i < found.getLength(); i++) {
            matches.add((Element) found.item(i));
        }
        return matches;
    }

    private static List<Element> childElements(Element parent, String tagName) {
        List<Element> matches = new ArrayList<>();
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE && tagName.equals(child.getNodeName())) {
                matches.add((Element) child);
            }
        }
        return matches;
    }
}

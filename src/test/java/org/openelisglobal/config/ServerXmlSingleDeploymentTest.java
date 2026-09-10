package org.openelisglobal.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
 * A Tomcat {@code <Host>} deploys a web application from two independent
 * sources: its own deployer, which scans {@code appBase} when
 * {@code deployOnStartup} is true — the default when the attribute is absent —
 * and any explicitly configured {@code <Context>} children. When a
 * {@code <Context>}'s {@code docBase} resolves inside that same Host's
 * {@code appBase}, one WAR is the target of both and deploys twice: once at the
 * filename-derived path and once at the declared one. Two web application
 * contexts mean two Spring root contexts and two of every singleton in them,
 * including two schedulers, so every {@code @Scheduled} method fires twice.
 *
 * <p>
 * The Tomcat 10.1 Host documentation names the remedy this pins: "if you are
 * defining contexts explicitly in server.xml, you should probably turn off
 * automatic application deployment ... Otherwise, the web applications will
 * each be deployed twice".
 *
 * <p>
 * This reads the shipped configuration as data; nothing about the application
 * is arranged to make it observable.
 */
public class ServerXmlSingleDeploymentTest {

    @Test
    public void hostsDeclaringAnInAppBaseContextDoNotAlsoScanAppBase() throws Exception {
        List<String> violations = new ArrayList<>();
        List<Path> configs = trackedFilesMatching("server.xml");
        Assert.assertFalse("found no tracked Tomcat server.xml to check; the search itself is broken",
                configs.isEmpty());

        for (Path config : configs) {
            for (Element host : elementsNamed(parse(config), "Host")) {
                String appBase = host.getAttribute("appBase");
                long inAppBase = childElements(host, "Context").stream()
                        .filter(context -> resolvesInside(context.getAttribute("docBase"), appBase)).count();
                if (inAppBase == 0) {
                    continue;
                }
                // Absence is the defect, not merely a value of "true": an absent
                // deployOnStartup defaults to true and deploys appBase anyway.
                for (String attribute : List.of("autoDeploy", "deployOnStartup")) {
                    if (!"false".equals(host.getAttribute(attribute))) {
                        violations.add(config + ": <Host appBase=\"" + appBase + "\"> declares " + inAppBase
                                + " in-appBase <Context> element(s) but " + attribute + " is "
                                + (host.hasAttribute(attribute) ? "\"" + host.getAttribute(attribute) + "\"" : "absent")
                                + " rather than \"false\", so that WAR deploys twice");
                    }
                }
            }
        }

        Assert.assertEquals(String.join("\n", violations), List.of(), violations);
    }

    /**
     * Tomcat resolves a relative {@code docBase} against the Host's
     * {@code appBase}, and an absolute one that points back inside {@code appBase}
     * double-deploys just the same.
     */
    private static boolean resolvesInside(String docBase, String appBase) {
        if (docBase.isEmpty()) {
            return false;
        }
        Path path = Path.of(docBase);
        return !path.isAbsolute() || (!appBase.isEmpty() && path.startsWith(Path.of(appBase).toAbsolutePath()));
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

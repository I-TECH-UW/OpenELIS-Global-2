package org.openelisglobal.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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
 * {@code appBase}, one WAR is the target of both, so it deploys twice: once at
 * the filename-derived path and once at the declared one. Two web application
 * contexts mean two Spring root contexts and two of every singleton in them,
 * including two schedulers, so every {@code @Scheduled} method fires twice.
 *
 * <p>
 * Both assertions here read the shipped configuration as data; nothing about
 * the application is arranged to make them observable.
 */
public class ServerXmlSingleDeploymentTest {

    /**
     * The bridge posts analyzer results straight to the app, bypassing the proxy.
     */
    private static final String BRIDGE_ENV_KEY = "ORG_ITECH_AHB_FORWARD_HTTP_SERVER_URI";

    private static final String DEPLOYED_CONTEXT_PATH = "/api/OpenELIS-Global/";

    private static final List<String> PRUNED_DIRECTORIES = List.of("node_modules", "target", ".git");

    @Test
    public void hostsDeclaringAnInAppBaseContextDoNotAlsoScanAppBase() throws Exception {
        List<String> violations = new ArrayList<>();
        List<Path> configs = filesMatching(name -> name.endsWith("server.xml"));
        Assert.assertFalse("found no Tomcat server.xml to check; the search itself is broken", configs.isEmpty());

        for (Path config : configs) {
            for (Element host : elementsNamed(parse(config), "Host")) {
                String appBase = host.getAttribute("appBase");
                List<Element> inAppBase = new ArrayList<>();
                for (Element context : childElements(host, "Context")) {
                    if (resolvesInside(context.getAttribute("docBase"), appBase)) {
                        inAppBase.add(context);
                    }
                }
                if (inAppBase.isEmpty()) {
                    continue;
                }
                // Absence is the defect, not merely a value of "true": an absent
                // deployOnStartup defaults to true and deploys appBase anyway.
                for (String attribute : List.of("autoDeploy", "deployOnStartup")) {
                    if (!"false".equals(host.getAttribute(attribute))) {
                        violations.add(config + ": <Host appBase=\"" + appBase + "\"> declares " + inAppBase.size()
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
    public void analyzerBridgeForwardsToTheContextThisRepoActuallyDeploys() throws Exception {
        List<String> violations = new ArrayList<>();
        List<String> uris = new ArrayList<>();

        for (Path config : filesMatching(name -> name.endsWith(".yml") || name.endsWith(".yaml"))) {
            for (String line : Files.readAllLines(config, StandardCharsets.UTF_8)) {
                String statement = line.strip();
                if (statement.startsWith("#")) {
                    continue;
                }
                String uri = bridgeForwardUri(statement);
                if (uri == null) {
                    continue;
                }
                uris.add(uri);
                if (!uri.contains(DEPLOYED_CONTEXT_PATH)) {
                    violations.add(config + ": bridge forwards to " + uri + ", but the only OpenELIS context this"
                            + " repo deploys is " + DEPLOYED_CONTEXT_PATH
                            + "; the filename-derived /OpenELIS-Global existed only while the WAR deployed twice");
                }
            }
        }

        Assert.assertFalse("found no analyzer bridge forward URI to check; the search itself is broken",
                uris.isEmpty());
        Assert.assertEquals(String.join("\n", violations), List.of(), violations);
    }

    /**
     * The bridge reads its target from the
     * {@code ORG_ITECH_AHB_FORWARD_HTTP_SERVER_URI} environment variable, or from
     * {@code openelis.url} in its own YAML config.
     */
    private static String bridgeForwardUri(String statement) {
        int assignment = statement.indexOf(BRIDGE_ENV_KEY + "=");
        if (assignment >= 0) {
            return statement.substring(assignment + BRIDGE_ENV_KEY.length() + 1).strip();
        }
        if (statement.startsWith("url:") && statement.contains("/analyzer")) {
            return statement.substring("url:".length()).strip();
        }
        return null;
    }

    /**
     * Tomcat resolves a relative {@code docBase} against the Host's
     * {@code appBase}, and an absolute one that happens to point back inside
     * {@code appBase} double-deploys just the same.
     */
    private static boolean resolvesInside(String docBase, String appBase) {
        if (docBase.isEmpty()) {
            return false;
        }
        Path path = Path.of(docBase);
        return !path.isAbsolute() || (!appBase.isEmpty() && path.startsWith(Path.of(appBase).toAbsolutePath()));
    }

    private static List<Path> filesMatching(java.util.function.Predicate<String> nameMatches) throws IOException {
        List<Path> matches = new ArrayList<>();
        Files.walkFileTree(Path.of("."), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) {
                return PRUNED_DIRECTORIES.contains(directory.getFileName().toString()) ? FileVisitResult.SKIP_SUBTREE
                        : FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
                if (nameMatches.test(file.getFileName().toString())) {
                    matches.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        matches.sort(null);
        return matches;
    }

    private static List<Element> elementsNamed(Document document, String tagName) {
        List<Element> matches = new ArrayList<>();
        NodeList found = document.getElementsByTagName(tagName);
        for (int i = 0; i < found.getLength(); i++) {
            matches.add((Element) found.item(i));
        }
        return matches;
    }

    private static Document parse(Path path) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false);
        return factory.newDocumentBuilder().parse(path.toFile());
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

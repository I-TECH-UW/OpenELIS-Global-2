package org.openelisglobal.config.task;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.hl7.fhir.instance.model.api.IAnyResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.Bundle.BundleEntryRequestComponent;
import org.hl7.fhir.r4.model.Bundle.BundleType;
import org.hl7.fhir.r4.model.Bundle.HTTPVerb;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Subscription;
import org.hl7.fhir.r4.model.Subscription.SubscriptionChannelComponent;
import org.hl7.fhir.r4.model.Subscription.SubscriptionChannelType;
import org.hl7.fhir.r4.model.Subscription.SubscriptionStatus;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

@Component
public class RegisterFhirSubscriberTask {

    @Value("${org.openelisglobal.fhir.subscriber}")
    private Optional<String> fhirSubscriber;

    @Value("${org.openelisglobal.fhir.subscriber.resources}")
    private String[] fhirSubscriptionResources;

    @Value("${org.openelisglobal.fhirstore.uri}")
    private String localFhirStorePath;

    private static String fhirSubscriptionIdPrefix = "consolidatedServerSubscription";

    @Autowired
    FhirContext fhirContext;

    @PostConstruct
    public void startTask() {
        if (!fhirSubscriber.isPresent()) {
            return;
        }
        if (fhirSubscriber.get().startsWith("http://")) {
            fhirSubscriber = Optional.of("https://" + fhirSubscriber.get().substring("http://".length()));
        }
        if (!fhirSubscriber.get().startsWith("https://")) {
            fhirSubscriber = Optional.of("https://" + fhirSubscriber.get());
        }

        IGenericClient fhirClient = fhirContext
                .newRestfulGenericClient(localFhirStorePath);

        removeOldSubscription();

        Bundle subscriptionBundle = new Bundle();
        subscriptionBundle.setType(BundleType.TRANSACTION);

        for (String fhirSubscriptionResource : fhirSubscriptionResources) {
            ResourceType resourceType = ResourceType.fromCode(fhirSubscriptionResource);
            Subscription subscription = createSubscriptionForResource(resourceType);
            BundleEntryComponent bundleEntry = new BundleEntryComponent();
            bundleEntry.setResource(subscription);
            bundleEntry.setRequest(
                    new BundleEntryRequestComponent().setMethod(HTTPVerb.PUT).setUrl(ResourceType.Subscription.name()
                            + "/" + createSubscriptionIdForResourceType(resourceType)));

            subscriptionBundle.addEntry(bundleEntry);

        }
        try {
            Bundle returnedBundle = fhirClient.transaction().withBundle(subscriptionBundle).encodedJson().execute();
            LogEvent.logDebug(this.getClass().getName(), "startTask", "subscription bundle returned:\n"
                    + fhirContext.newJsonParser().encodeResourceToString(returnedBundle));
        } catch (UnprocessableEntityException | DataFormatException e) {
            LogEvent.logError("error while communicating subscription bundle to " + localFhirStorePath + " for "
                    + fhirSubscriber.get(), e);
        }

    }

    private void removeOldSubscription() {
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(localFhirStorePath);

        Bundle deleteTransactionBundle = new Bundle();
        deleteTransactionBundle.setType(BundleType.TRANSACTION);
        for (ResourceType resourceType : ResourceType.values()) {
            Bundle responseBundle = (Bundle) fhirClient.search().forResource(Subscription.class)
                    .where(IAnyResource.RES_ID.exactly().code(createSubscriptionIdForResourceType(resourceType)))
                    .execute();
            if (responseBundle.hasEntry()) {
                BundleEntryComponent bundleEntry = new BundleEntryComponent();
                bundleEntry.setRequest(new BundleEntryRequestComponent().setMethod(HTTPVerb.DELETE).setUrl(
                        ResourceType.Subscription.name() + "/" + createSubscriptionIdForResourceType(resourceType)));

                deleteTransactionBundle.addEntry(bundleEntry);
            }
        }
        Bundle returnedBundle = fhirClient.transaction().withBundle(deleteTransactionBundle).encodedJson().execute();
        LogEvent.logDebug(this.getClass().getName(), "removeOldSubscription",
                "delete old bundle returned:\n" + fhirContext.newJsonParser().encodeResourceToString(returnedBundle));

    }

    private String createSubscriptionIdForResourceType(ResourceType resourceType) {
        return fhirSubscriptionIdPrefix + resourceType.toString();
    }

    private Subscription createSubscriptionForResource(ResourceType resourceType) {
        Subscription subscription = new Subscription();
        subscription.setText(new Narrative().setStatus(NarrativeStatus.GENERATED).setDiv(new XhtmlNode(NodeType.Text)));
        subscription.setStatus(SubscriptionStatus.REQUESTED);
        subscription
                .setReason("bulk subscription to detect any Creates or Updates to resources of type " + resourceType);

        SubscriptionChannelComponent channel = new SubscriptionChannelComponent();
        channel.setType(SubscriptionChannelType.RESTHOOK)
                .setEndpoint(fhirSubscriber.get());
        channel.addHeader("Server-Name: " + ConfigurationProperties.getInstance().getPropertyValue(Property.SiteName));
        channel.addHeader("Server-Code: " + ConfigurationProperties.getInstance().getPropertyValue(Property.SiteCode));
        channel.setPayload("application/fhir+json");
        subscription.setChannel(channel);

        subscription.setCriteria(createCriteriaString(resourceType));
        return subscription;
    }

    private String createCriteriaString(ResourceType resourceType) {
        return resourceType.name() + "?";
    }

}

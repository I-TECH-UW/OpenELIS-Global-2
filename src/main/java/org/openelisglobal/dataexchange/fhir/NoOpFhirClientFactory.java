package org.openelisglobal.dataexchange.fhir;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory class for creating a No-Operation (NoOp) implementation of the
 * IGenericClient interface. This implementation is useful for scenarios where a
 * functional FHIR client is not available or not needed.
 */
public class NoOpFhirClientFactory {

    private static final Logger logger = LoggerFactory.getLogger(NoOpFhirClientFactory.class);

    /**
     * Creates a No-Operation (NoOp) instance of the IGenericClient interface.
     *
     * @return an instance of IGenericClient that performs no operations.
     */
    public static IGenericClient create() {
        return (IGenericClient) Proxy.newProxyInstance(IGenericClient.class.getClassLoader(),
                new Class[] { IGenericClient.class }, new NoOpFhirClientInvocationHandler());
    }

    /**
     * InvocationHandler implementation for the No-Operation (NoOp) IGenericClient.
     * This handler logs method calls and performs no operations.
     */
    private static class NoOpFhirClientInvocationHandler implements InvocationHandler {

        /**
         * Handles the method calls on the proxy instance.
         *
         * @param proxy  the proxy instance
         * @param method the method being called
         * @param args   the arguments of the method call
         * @return null for all method calls, or appropriate values for equals,
         *         hashCode, and toString methods
         * @throws Throwable if an error occurs during method invocation
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();

            if ("equals".equals(methodName)) {
                return proxy == args[0];
            } else if ("hashCode".equals(methodName)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(methodName)) {
                return "NoOpFhirClient";
            }
            logger.warn("NoOpFhirClient called: {}", method.getName());
            return null;
        }
    }
}

/**
 * Copyright 2014 isandlaTech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cohorte.ecf.provider.jabsorb.host;

import java.util.Map;

import org.cohorte.ecf.provider.jabsorb.Utilities;
import org.cohorte.ecf.provider.jabsorb.identity.JabsorbNamespace;
import org.eclipse.ecf.remoteservice.AbstractRSAContainer;
import org.eclipse.ecf.remoteservice.RSARemoteServiceContainerAdapter.RSARemoteServiceRegistration;

/**
 * Jabsorb ECF host container: registers services to the Jabsorb bridge
 * 
 * @author Thomas Calmant
 */
public class JabsorbHostContainer extends AbstractRSAContainer {

    /** The Jabsorb bridge */
    private JabsorbHttpServiceComponent pBridge;
    
    /**
     * @param aId
     *            The host container ID, as generated by the
     *            JabsorbContainerInstantiator
     */
    public JabsorbHostContainer(final String idString) {
        super(JabsorbNamespace.getInstance()
				.createInstance(new Object[] { idString }));
        pBridge = JabsorbHttpServiceComponent
                .getInstance();
    }

	@Override
	protected Map<String, Object> exportRemoteService(RSARemoteServiceRegistration registration) {
		synchronized (this) {
			// Register the service to the bridge
			pBridge.registerEndpoint(String.valueOf(registration.getServiceId()), registration.getService());
		}
        // No need for extra properties
		return null;
	}

	@Override
	protected void unexportRemoteService(RSARemoteServiceRegistration registration) {
		synchronized (this) {
			String endpointId = String.valueOf(registration.getServiceId());
	        // Unregister the endpoint from Jabsorb
	        pBridge.unregisterEndpoint(endpointId);

	        Utilities.traceDebug("unregisterService", getClass(),
	                "Service at endpoint " + endpointId + " unregistered");
		}		
	}

    @Override
    public void dispose() {
        // Clean up
        pBridge = null;
        super.dispose();
    }

}

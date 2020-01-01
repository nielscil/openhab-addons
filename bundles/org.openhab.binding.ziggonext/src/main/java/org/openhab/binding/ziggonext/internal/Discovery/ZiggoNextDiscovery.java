/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.ziggonext.internal.Discovery;

import org.openhab.binding.ziggonext.internal.Handlers.ZiggoAccountBridgeHandler;
import org.openhab.core.config.discovery.AbstractDiscoveryService;
import org.openhab.core.thing.ThingUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link ZiggoNextDiscovery} is responsible for device discovery. It creates a thing in the inbox for each Ziggo
 * Next device
 *
 * @author Niels Bouma - Initial contribution
 */
public class ZiggoNextDiscovery extends AbstractDiscoveryService {

    private final Logger logger = LoggerFactory.getLogger(ZiggoNextDiscovery.class);
    private static final int TIMEOUT = 10;

    private ZiggoAccountBridgeHandler bridgeHandler;
    private ThingUID bridgeUID;

    public ZiggoNextDiscovery(ZiggoAccountBridgeHandler bridgeHandler) {
        super(TIMEOUT);
        this.bridgeHandler = bridgeHandler;
        this.bridgeUID = bridgeHandler.getThing().getUID();
    }

    @Override
    protected void startScan() {
        // TODO Auto-generated method stub

    }
}

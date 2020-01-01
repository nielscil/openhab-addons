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
package org.openhab.binding.ziggonext.internal.Handlers;

import org.openhab.binding.ziggonext.internal.ZiggoAccountConnection;
import org.openhab.binding.ziggonext.internal.Configurations.ZiggoAccountConfiguration;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.ThingStatusDetail;
import org.openhab.core.thing.binding.BaseBridgeHandler;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link ZiggoAccountBridgeHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Niels Bouma - Initial contribution
 */
public class ZiggoAccountBridgeHandler extends BaseBridgeHandler {

    private final Logger logger = LoggerFactory.getLogger(ZiggoAccountBridgeHandler.class);

    private ZiggoAccountConfiguration configuration;
    private ZiggoAccountConnection connection;

    public ZiggoAccountBridgeHandler(Bridge bridge) {
        super(bridge);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {

        logger.debug("Command '{}' received for channel '{}'", command, channelUID);
    }

    @Override
    public void initialize() {
        logger.debug(String.format("Start initializing Ziggo Next Binding with Bridge: {0}", getThing().getLabel()));
        configuration = getConfigAs(ZiggoAccountConfiguration.class);
        updateStatus(ThingStatus.UNKNOWN);
        scheduler.execute(() -> {
            initializeBridge();
            logger.debug(String.format("Finished initializing Ziggo Next Binding with Thing: {0}!", thing.getLabel()));
        });
    }

    private void initializeBridge() {

        try {
            connection = new ZiggoAccountConnection(configuration);
            connection.connect();
        } catch (Exception e) {
            logger.error("Error while initializing bridge: '{}'", e.getMessage());
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, e.getMessage());
        }
        // Get a session by doing http post request to: SESSION_URL with body:
        // {
        // username: ziggoUsername,
        // password: ziggoPassword
        // }
        // On Error:
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR, message);

        // Get token (result.oespToken) and household id (result.customer.householdId) from result json.

        // use the household id and token to connect to mqqt
    }

}

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

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.ziggonext.internal.Configurations.ZiggoNextConfiguration;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.ThingStatusDetail;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.Command;
import org.openhab.core.types.RefreshType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link ZiggoNextHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Niels Bouma - Initial contribution
 */
@NonNullByDefault
public class ZiggoNextHandler extends BaseThingHandler {

    private final Logger logger = LoggerFactory.getLogger(ZiggoNextHandler.class);

    private @Nullable ZiggoNextConfiguration config;

    public ZiggoNextHandler(Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        // if (CHANNEL_1.equals(channelUID.getId())) {
        if (command instanceof RefreshType) {
            // TODO: handle data refresh
        }

        // TODO: handle command

        // Note: if communication with thing fails for some reason,
        // indicate that by setting the status with detail information:
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
        // "Could not control device at IP address x.x.x.x");
        // }
    }

    @Override
    public void initialize() {
        logger.debug(String.format("Start initializing Ziggo Next Binding with Thing: {0}", thing.getLabel()));

        config = getConfigAs(ZiggoNextConfiguration.class);
        // TODO: Initialize the handler.
        // The framework requires you to return from this method quickly. Also, before leaving this method a thing
        // status from one of ONLINE, OFFLINE or UNKNOWN must be set. This might already be the real thing status in
        // case you can decide it directly.
        // In case you can not decide the thing status directly (e.g. for long running connection handshake using WAN
        // access or similar) you should set status UNKNOWN here and then decide the real status asynchronously in the
        // background.

        // set the thing status to UNKNOWN temporarily and let the background task decide for the real status.
        // the framework is then able to reuse the resources from the thing handler initialization.
        // we set this upfront to reliably check status updates in unit tests.
        updateStatus(ThingStatus.UNKNOWN);

        // Example for background initialization:
        scheduler.execute(() -> {
            initializeThing();
            logger.debug(String.format("Finished initializing Ziggo Next Binding with Thing: {0}!", thing.getLabel()));
        });

        // logger.debug("Finished initializing!");

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }

    private ThingStatus initializeThing() {

        if (config == null || config.ziggoAccountUsername.isBlank() || config.ziggoAccountPassword.isBlank()) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR, "No configuration is set");
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
        return ThingStatus.UNKNOWN;
    }
}

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
package org.openhab.binding.ziggonext.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link ZiggoNextBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Niels Bouma - Initial contribution
 */
@NonNullByDefault
public class ZiggoNextBindingConstants {

    private static final String BINDING_ID = "ziggonext";

    // List of all Thing Type UIDs
    public static final String THING_TYPE_ZIGGO_ACCOUNT_ID = "Account";
    public static final String THING_TYPE_ZIGGO_NEXT_ID = "STB";

    public static final ThingTypeUID THING_TYPE_ZIGGO_ACCOUNT = new ThingTypeUID(BINDING_ID,
            THING_TYPE_ZIGGO_ACCOUNT_ID);
    public static final ThingTypeUID THING_TYPE_ZIGGO_NEXT = new ThingTypeUID(BINDING_ID, THING_TYPE_ZIGGO_NEXT_ID);

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = new HashSet<ThingTypeUID>(
            Arrays.asList(THING_TYPE_ZIGGO_ACCOUNT, THING_TYPE_ZIGGO_NEXT));

    // List of all Channel ids
    // public static final String CHANNEL_1 = "channel1";

    // URL's
    public static final String SESSION_URL = "https://web-api-prod-obo.horizon.tv/oesp/v3/NL/nld/web/session";
    public static final String JWT_URL = "https://web-api-prod-obo.horizon.tv/oesp/v3/NL/nld/web/tokens/jwt";
    public static final String CHANNELS_URL = "https://web-api-prod-obo.horizon.tv/oesp/v3/NL/nld/web/channels";
    public static final String MQTT_URL = "wss://obomsg.prod.nl.horizon.tv:443/mqtt";
}

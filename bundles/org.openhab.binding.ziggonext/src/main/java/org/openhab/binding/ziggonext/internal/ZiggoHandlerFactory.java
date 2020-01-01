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

import static org.openhab.binding.ziggonext.internal.ZiggoNextBindingConstants.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.ziggonext.internal.Discovery.ZiggoNextDiscovery;
import org.openhab.binding.ziggonext.internal.Handlers.ZiggoAccountBridgeHandler;
import org.openhab.binding.ziggonext.internal.Handlers.ZiggoNextHandler;
import org.openhab.core.config.discovery.DiscoveryService;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.ThingUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Component;

/**
 * The {@link ZiggoHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Niels Bouma - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.ziggonext", service = ThingHandlerFactory.class)
public class ZiggoHandlerFactory extends BaseThingHandlerFactory {

    private final Map<ThingUID, @Nullable ServiceRegistration<?>> discoveryServiceRegistrations = new HashMap<>();

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_ZIGGO_ACCOUNT.equals(thingTypeUID)) {
            ZiggoAccountBridgeHandler bridgeHandler = new ZiggoAccountBridgeHandler((Bridge) thing);
            registerDeviceDiscoveryService(bridgeHandler);
            return bridgeHandler;
        }

        if (THING_TYPE_ZIGGO_NEXT.equals(thingTypeUID)) {
            return new ZiggoNextHandler(thing);
        }

        return null;
    }

    @Override
    protected void removeHandler(ThingHandler thingHandler) {
        if (thingHandler instanceof ZiggoAccountBridgeHandler) {
            unregisterDeviceDiscoveryService((ZiggoAccountBridgeHandler) thingHandler);
        }
    }

    private synchronized void registerDeviceDiscoveryService(ZiggoAccountBridgeHandler bridgeHandler) {
        ZiggoNextDiscovery discoveryService = new ZiggoNextDiscovery(bridgeHandler);
        // discoveryService.activate();
        this.discoveryServiceRegistrations.put(bridgeHandler.getThing().getUID(), bundleContext
                .registerService(DiscoveryService.class.getName(), discoveryService, new Hashtable<String, Object>()));
    }

    private synchronized void unregisterDeviceDiscoveryService(ZiggoAccountBridgeHandler bridgeHandler) {
        ServiceRegistration<?> serviceRegistration = this.discoveryServiceRegistrations
                .get(bridgeHandler.getThing().getUID());
        if (serviceRegistration != null) {
            ZiggoNextDiscovery discoveryService = (ZiggoNextDiscovery) bundleContext
                    .getService(serviceRegistration.getReference());
            if (discoveryService != null) {
                // discoveryService.deactivate();
            }
            serviceRegistration.unregister();
            discoveryServiceRegistrations.remove(bridgeHandler.getThing().getUID());
        }
    }
}

package org.openhab.binding.ziggonext.internal;

import org.openhab.binding.ziggonext.internal.Configurations.ZiggoAccountConfiguration;
import org.openhab.binding.ziggonext.internal.DataModels.JwtToken;
import org.openhab.binding.ziggonext.internal.DataModels.SessionRequestBody;
import org.openhab.binding.ziggonext.internal.DataModels.SessionResponseBody;
import org.openhab.binding.ziggonext.internal.ZiggoNextBindingConstants;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.eclipse.smarthome.io.net.http.HttpRequestBuilder;
import org.eclipse.smarthome.io.transport.mqtt.MqttBrokerConnection;

public class ZiggoAccountConnection {

    private MqttBrokerConnection mqttConnection;
    private ZiggoAccountConfiguration configuration;
    private final Gson gson = new GsonBuilder().create();

    public ZiggoAccountConnection(ZiggoAccountConfiguration configuration) {
        this.configuration = configuration;
    }

    public void connect() throws IOException {
        SessionResponseBody session = getSession();
        JwtToken token = getJwtToken(session);
        setupMqttConnection(session, token);
    }

    private SessionResponseBody getSession() throws IOException {
        String jsonResult = HttpRequestBuilder
        .postTo(ZiggoNextBindingConstants.SESSION_URL)
        //.withHeader("charset", "utf-8")
        .withHeader("Content-Type", "application/json")
        .withContent(gson.toJson(new SessionRequestBody(configuration.username, configuration.password))).getContentAsString();

        return gson.fromJson(jsonResult, SessionResponseBody.class);
    }

    private JwtToken getJwtToken(SessionResponseBody session) throws IOException {
        String jsonResult = HttpRequestBuilder
        .getFrom(ZiggoNextBindingConstants.JWT_URL)
        .withHeader("X-OESP-Token", session.oespToken)
        .withHeader("X-OESP-Username", configuration.username)
        .getContentAsString();

        return gson.fromJson(jsonResult, JwtToken.class);
    }

    private void setupMqttConnection(SessionResponseBody session, JwtToken token){
        //TODO: make mqtt connection
    }
}

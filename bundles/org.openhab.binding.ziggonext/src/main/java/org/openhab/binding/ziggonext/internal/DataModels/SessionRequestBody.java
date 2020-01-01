package org.openhab.binding.ziggonext.internal.DataModels;

public class SessionRequestBody {

    public String username;
    public String password;

    public SessionRequestBody() {

    }

    public SessionRequestBody(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

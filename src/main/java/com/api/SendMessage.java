package com.api;

import com.dataBase.DataBase;
import com.server.Server;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Map;

public class SendMessage implements Command {
    private DataBase dataBase;

    public SendMessage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public JsonObject execute(Map<String, String> params) throws Exception {
        int recipient = dataBase.getIDbyMail(params.get("recipient"));
        int from_whom = dataBase.getIDForToken(params.get("from_whom"));
        String header = params.get("header");
        String text = params.get("text");

        boolean result = dataBase.sendMessage(recipient, from_whom, text, header);
        Server.sendPushNotifications(Arrays.asList(new String[]{Server.tokenTest}), header, text);

        JsonObject json = new JsonObject();
        json.addProperty("result", result);

        return json;
    }
}

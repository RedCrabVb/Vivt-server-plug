package API.Command;

import DataBase.ClientInfo;
import Server.Server;
import com.google.gson.JsonObject;

import java.util.Map;

public class News implements Command {

    @Override
    public JsonObject execute(ClientInfo client, Map<String, String> params) {
        try {
            return Server.dataBase.news();
        } catch (Exception e) {
            return new JsonObject();
        }
    }
}

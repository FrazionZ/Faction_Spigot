package org.bukkit.craftbukkit.util;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class FzUtils {

    public static UUID getFZUUID(String username){
        //https://api.frazionz.net/users?username=RacconFlow
        HTTPReply reply = HTTPUtils.sendGet("https://api.frazionz.net/users?username="+username);

        if(reply.getStatusCode() == 200)
        {
            Gson gson = new Gson();
            FzUser objs = gson.fromJson(reply.getBody(), FzUser.class);
            return UUID.fromString(objs.getUuid());
        }
        System.out.println("Error get Fz UUID, generate from username");
        return UUID.nameUUIDFromBytes( ( "OfflinePlayer:" + username).getBytes( StandardCharsets.UTF_8 ) );
    }

    public class FzUser {

        public int fzid;
        public String name;
        public String uuid;
        public String id;

        public String getUuid() {
            return uuid;
        }
    }
}

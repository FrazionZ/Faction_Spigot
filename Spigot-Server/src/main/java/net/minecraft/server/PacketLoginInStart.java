package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class PacketLoginInStart implements Packet<PacketLoginInListener> {

    private GameProfile a;
    private String token;

    public PacketLoginInStart() {}

    public PacketLoginInStart(GameProfile gameprofile, String token) {
        this.a = gameprofile;
        this.token = token;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.a = new GameProfile((UUID) null, packetdataserializer.e(16));
        this.token = packetdataserializer.e(256);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.a.getName());
        packetdataserializer.a(this.token);
    }

    public void a(PacketLoginInListener packetlogininlistener) {
        packetlogininlistener.a(this);
    }

    public GameProfile a() {
        return this.a;
    }
    
    public String getToken() {
		return token;
	}
}

package net.minecraft.server.frazionz.packets.server;

import java.io.IOException;

import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayOut;

public class PacketPlayOutUpdateData implements Packet<PacketListenerPlayOut> {

    private String key;
    private String valueType;
    private String table;
    private Object value;

    public PacketPlayOutUpdateData() {}

    public PacketPlayOutUpdateData(String key, String valueType, String table, Object value) {
         this.key = key;
         this.value = value;
         this.valueType = valueType;
         this.table = table;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.key);
        packetdataserializer.a(this.valueType);
        packetdataserializer.a(this.table);
        switch(this.valueType) {
        	case "boolean":
        		packetdataserializer.writeBoolean((boolean) this.value);
        		break;
        	case "String":
        		packetdataserializer.a((String) this.value);
        		break;
        	case "float":
        		packetdataserializer.writeFloat((float) this.value);
        		break;
        	case "double":
        		packetdataserializer.writeDouble((double) this.value);
        		break;
        }
    }

    @Override
    public void a(PacketListenerPlayOut packetIn) {
        packetIn.a(this);
    }
}

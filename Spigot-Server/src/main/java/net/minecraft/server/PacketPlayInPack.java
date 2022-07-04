package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInPack implements Packet<PacketListenerPlayIn> {

    private String string;

    public PacketPlayInPack() {
    }

    public PacketPlayInPack(String s) {
    	this.string = s;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    	
    	this.string = packetdataserializer.e(1028);
    	
    }
    

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
    	
    	packetdataserializer.a(this.string);
    	
    }
    
    public void a(final PacketListenerPlayIn packetlistenerplayin) {
    	
    	System.out.println(string);
    	
    }
    
}

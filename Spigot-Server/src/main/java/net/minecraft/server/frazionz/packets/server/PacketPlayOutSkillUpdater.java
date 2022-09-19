package net.minecraft.server.frazionz.packets.server;

import java.io.IOException;
import java.util.Arrays;

import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayOut;

public class PacketPlayOutSkillUpdater implements Packet<PacketListenerPlayOut> {

	private SkillUpdaterCategory category;
	private int skillId;
	private Object skillInfoUpdate;

    public PacketPlayOutSkillUpdater() {}

    public PacketPlayOutSkillUpdater(SkillUpdaterCategory category, int skillId, Object skillInfoUpdate) {
    	this.category = category;
    	this.skillId = skillId;
    	this.skillInfoUpdate = skillInfoUpdate;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
    	packetdataserializer.writeInt(this.category.getId());
    	packetdataserializer.writeInt(this.skillId);
		switch(this.category) {
			case WIN_EXP:
			case LEVEL_UPDATE:
				packetdataserializer.writeInt((int) this.skillInfoUpdate);
				break;
			default:
				break;
		}
    }

    @Override
    public void a(PacketListenerPlayOut packetIn) {
        packetIn.a(this);
    }
    
	public enum SkillUpdaterCategory {
		
		UNKNOWN(0),
		WIN_EXP(1),
		LEVEL_UPDATE(2),
		;
		
		private int id;
		
		SkillUpdaterCategory(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
		
		public static SkillUpdaterCategory valueOf(int id) {
			return Arrays.stream(values()).filter(category -> category.id == id).findFirst().orElse(UNKNOWN);
		}
	}
}

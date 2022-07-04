package net.minecraft.server.frazionz.packets.server;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.frazionz.FzServerSwitchEvent;
import org.bukkit.event.frazionz.packets.FzClientSkillClaimReward;

import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayIn;

public class PacketPlayInSkillClaimButton extends PacketPlayInClickButtonTransmitter {

	private int skillType;
	private int level;
	private int rewardId;

    public PacketPlayInSkillClaimButton() {
	}
    
    public PacketPlayInSkillClaimButton(UUID playerUUID, int skillType, int level, int rewardId) {
		super(playerUUID, ButtonType.SKILL_CLAIM_REWARD);
		this.skillType = skillType;
		this.level = level;
		this.rewardId = rewardId;
	}

    public void a(PacketDataSerializer buf) throws IOException {
    	super.a(buf);
    	this.skillType = buf.readInt();
    	this.level = buf.readInt();
    	this.rewardId = buf.readInt();
    }
    
    public void a(PacketListenerPlayIn packetlistenerplayin) {
    	Bukkit.getServer().getPluginManager().callEvent(new FzClientSkillClaimReward(Bukkit.getPlayer(playerUUID), this.skillType, this.level, this.rewardId));
    }
}

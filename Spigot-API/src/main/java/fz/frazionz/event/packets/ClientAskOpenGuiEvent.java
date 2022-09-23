package fz.frazionz.event.packets;

import fz.frazionz.enums.EnumGui;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.util.UUID;

public class ClientAskOpenGuiEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private EnumGui gui;
    private int info;

    public ClientAskOpenGuiEvent(Player player, EnumGui gui, int info) {
        super(player);
        this.gui = gui;
        this.info = info;
    }

    public EnumGui getGui() {
        return gui;
    }

    public int getInfo() {
        return info;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}

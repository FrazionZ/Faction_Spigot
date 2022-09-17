package org.bukkit.event.frazionz.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.frazionz.CancellableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class MachineCraftEvent extends CancellableEvent {
    
	private Player player;
	private InventoryView view;
	private ItemStack itemToCraft;
	private MachineType type;
	private MachineAction action;
	
    public MachineCraftEvent(Player player, ItemStack itemToCraft, InventoryView view, MachineType type, MachineAction action) {
    	this.player = player;
    	this.view = view;
    	this.itemToCraft = itemToCraft;
    	this.type = type;
    	this.action = action;
    }
    
    public Player getPlayer() {
		return player;
	}
    
    public ItemStack getItemToCraft() {
		return itemToCraft;
	}
    
    public InventoryView getView() {
		return view;
	}
    
    public MachineType getType() {
		return type;
	}
    
    public MachineAction getAction() {
		return action;
	}
    
    public Inventory getMachineInventory() {
    	return view.getTopInventory();
    }
    
    public enum MachineType {
    	
    	AMELIORATOR,
    	TROPHY_FORGE,
		ITEM_CRUSHER,
    	;
    	
    }
    
    public enum MachineAction {
    	
    	START_CRAFTING,
    	CRAFTING,
    	CRAFT_END,
    	;
    	
    }

}

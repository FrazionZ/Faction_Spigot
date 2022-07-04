package net.minecraft.server;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import net.minecraft.server.frazionz.items.ItemBottleXP;

public class CommandBottleXP extends CommandAbstract {

    public CommandBottleXP() {}

    public String getCommand() {
        return "bottlexp";
    }

    public int a() {
        return 0;
    }

    public String getUsage(ICommandListener icommandlistener) {
        return "commands.bottlexp.usage";
    }

    public void execute(MinecraftServer server, ICommandListener sender, String[] args) throws CommandException {
        EntityPlayer entityplayer = a(sender);
    	if (args.length <= 0)
        {
            int level = entityplayer.getExpLevel();
        	ItemStack item = new ItemStack(Items.BOTTLEXP, level, true);
        	ItemBottleXP bottleXp = (ItemBottleXP) item.getItem();
        	
            if(entityplayer.inventory.getFirstEmptySlotIndex() != -1 || entityplayer.inventory.containsAtLeast(item, 1)) {
            	
				if(level < bottleXp.getMinLevel()) {
					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez avoir \u00A76" + bottleXp.getMinLevel() + " \u00A7eniveaux Minimum."));
				}
				else if(level > bottleXp.getMaxLevel() ) {
					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eLa bottle ne peut pas avoir que \u00A76" + bottleXp.getMaxLevel() + " \u00A7eniveaux Maximum. Essayez avec \u00A76\"/bottlexp 100\"\u00A7e."));
				}
				
				else {
					entityplayer.inventory.pickup(item);
					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous venez de mettre en bouteille \u00A76" + level + " \u00A7elevels."));
					entityplayer.a(SoundEffects.fI, 0.5F, 1.0F);
					entityplayer.addExperienceLevel(-level, true);
					
					entityplayer.defaultContainer.b();
				}
            }
            else {
				entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez avoir une place dans votre Inventaire."));
            }
        }
        else
        {
        	if(StringUtils.isNumeric(args[0])) {
            	int bottleLevel = Integer.parseInt(args[0]);
            	ItemStack item = new ItemStack(Items.BOTTLEXP, bottleLevel, true);
            	ItemBottleXP bottleXp = (ItemBottleXP) item.getItem();
                if(entityplayer.inventory.getFirstEmptySlotIndex() != -1 || entityplayer.inventory.containsAtLeast(item, 1)) {
                	
    				if(Integer.parseInt(args[0]) < bottleXp.getMinLevel()) {
    					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eLa bottle doit être de \u00A76" + bottleXp.getMinLevel() + " \u00A7eniveaux Minimum."));
    				}
    				else if(Integer.parseInt(args[0]) > bottleXp.getMaxLevel() ) {
    					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eLa bottle ne peut pas avoir que \u00A76" + bottleXp.getMaxLevel() + " \u00A7eniveaux Maximum. Essayez avec \u00A76\"/bottlexp 100\"\u00A7e."));
    				}
    				
    				else if(Integer.parseInt(args[0]) > entityplayer.getExpLevel()) {
    					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous n'avez pas assez de niveaux."));
    				}
    				
    				else {
    					entityplayer.inventory.pickup(item);
    					entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous venez de mettre en bouteille \u00A76" + bottleLevel + " \u00A7elevels."));
    					entityplayer.a(SoundEffects.fI, 0.5F, 1.0F);
    					entityplayer.addExperienceLevel(-bottleLevel, true);
    					
    					entityplayer.defaultContainer.b();
    				}
                }
                else {
    				entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez avoir une place dans votre Inventaire."));
                }
        	}
        	else {
        		entityplayer.sendMessage(new ChatMessage("\u00A76[ \u00A7eBottleXP \u00A76] \u00A7eVous devez mettre un nombre précis."));
        	}
        }
    }

    @Override
    public int compareTo(ICommand o) {
        return a((ICommand) o);
    }
}

package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class DataConverterTileEntity implements IDataConverter {

    private static final Map<String, String> a = Maps.newHashMap();

    public DataConverterTileEntity() {}

    public int a() {
        return 704;
    }

    public NBTTagCompound a(NBTTagCompound nbttagcompound) {
        String s = (String) DataConverterTileEntity.a.get(nbttagcompound.getString("id"));

        if (s != null) {
            nbttagcompound.setString("id", s);
        }

        return nbttagcompound;
    }

    static {
        DataConverterTileEntity.a.put("Airportal", "minecraft:end_portal");
        DataConverterTileEntity.a.put("Banner", "minecraft:banner");
        DataConverterTileEntity.a.put("Beacon", "minecraft:beacon");
        DataConverterTileEntity.a.put("Cauldron", "minecraft:brewing_stand");
        DataConverterTileEntity.a.put("Chest", "minecraft:chest");
        DataConverterTileEntity.a.put("Comparator", "minecraft:comparator");
        DataConverterTileEntity.a.put("Control", "minecraft:command_block");
        DataConverterTileEntity.a.put("DLDetector", "minecraft:daylight_detector");
        DataConverterTileEntity.a.put("Dropper", "minecraft:dropper");
        DataConverterTileEntity.a.put("EnchantTable", "minecraft:enchanting_table");
        DataConverterTileEntity.a.put("EndGateway", "minecraft:end_gateway");
        DataConverterTileEntity.a.put("EnderChest", "minecraft:ender_chest");
        DataConverterTileEntity.a.put("FlowerPot", "minecraft:flower_pot");
        DataConverterTileEntity.a.put("Furnace", "minecraft:furnace");
        DataConverterTileEntity.a.put("Hopper", "minecraft:hopper");
        DataConverterTileEntity.a.put("MobSpawner", "minecraft:mob_spawner");
        DataConverterTileEntity.a.put("Music", "minecraft:noteblock");
        DataConverterTileEntity.a.put("Piston", "minecraft:piston");
        DataConverterTileEntity.a.put("RecordPlayer", "minecraft:jukebox");
        DataConverterTileEntity.a.put("Sign", "minecraft:sign");
        DataConverterTileEntity.a.put("Skull", "minecraft:skull");
        DataConverterTileEntity.a.put("Structure", "minecraft:structure_block");
        DataConverterTileEntity.a.put("Trap", "minecraft:dispenser");
        DataConverterTileEntity.a.put("dirt_chest", "minecraft:dirt_chest");
        DataConverterTileEntity.a.put("yellite_chest", "minecraft:yellite_chest");
        DataConverterTileEntity.a.put("bauxite_chest", "minecraft:bauxite_chest");
        DataConverterTileEntity.a.put("frazion_chest", "minecraft:frazion_chest");
        DataConverterTileEntity.a.put("onyx_chest", "minecraft:onyx_chest");
        DataConverterTileEntity.a.put("hdv_chest", "minecraft:hdv_chest");
        DataConverterTileEntity.a.put("spawner_inventory", "minecraft:spawner_inventory");
        DataConverterTileEntity.a.put("z_hopper", "minecraft:z_hopper");
        DataConverterTileEntity.a.put("yellite_furnace", "minecraft:yellite_furnace");
        DataConverterTileEntity.a.put("bauxite_furnace", "minecraft:bauxite_furnace");
        DataConverterTileEntity.a.put("onyx_furnace", "minecraft:onyx_furnace");
        DataConverterTileEntity.a.put("frazion_furnace", "minecraft:frazion_furnace");
        DataConverterTileEntity.a.put("ameliorator", "minecraft:ameliorator");
        DataConverterTileEntity.a.put("trophy_forge", "minecraft:trophy_forge");
    }
}

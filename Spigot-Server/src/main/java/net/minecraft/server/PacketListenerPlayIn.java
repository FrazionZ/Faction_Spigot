package net.minecraft.server;

import net.minecraft.server.frazionz.packets.client.PacketPlayInGuiOpener;
import net.minecraft.server.frazionz.packets.client.PacketPlayInShopTrade;

public interface PacketListenerPlayIn extends PacketListener {

    void a(PacketPlayInArmAnimation packetplayinarmanimation);

    void a(PacketPlayInChat packetplayinchat);

    void a(PacketPlayInTabComplete packetplayintabcomplete);

    void a(PacketPlayInClientCommand packetplayinclientcommand);

    void a(PacketPlayInSettings packetplayinsettings);

    void a(PacketPlayInTransaction packetplayintransaction);

    void a(PacketPlayInEnchantItem packetplayinenchantitem);

    void a(PacketPlayInWindowClick packetplayinwindowclick);

    void a(PacketPlayInAutoRecipe packetplayinautorecipe);

    void a(PacketPlayInCloseWindow packetplayinclosewindow);

    void a(PacketPlayInCustomPayload packetplayincustompayload);

    void a(PacketPlayInUseEntity packetplayinuseentity);

    void a(PacketPlayInKeepAlive packetplayinkeepalive);

    void a(PacketPlayInFlying packetplayinflying);

    void a(PacketPlayInAbilities packetplayinabilities);

    void a(PacketPlayInBlockDig packetplayinblockdig);

    void a(PacketPlayInEntityAction packetplayinentityaction);

    void a(PacketPlayInSteerVehicle packetplayinsteervehicle);

    void a(PacketPlayInHeldItemSlot packetplayinhelditemslot);

    void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot);

    void a(PacketPlayInUpdateSign packetplayinupdatesign);

    void a(PacketPlayInUseItem packetplayinuseitem);

    void a(PacketPlayInBlockPlace packetplayinblockplace);

    void a(PacketPlayInSpectate packetplayinspectate);

    void a(PacketPlayInResourcePackStatus packetplayinresourcepackstatus);

    void a(PacketPlayInBoatMove packetplayinboatmove);

    void a(PacketPlayInVehicleMove packetplayinvehiclemove);

    void a(PacketPlayInTeleportAccept packetplayinteleportaccept);

    void a(PacketPlayInRecipeDisplayed packetplayinrecipedisplayed);

    void a(PacketPlayInAdvancements packetplayinadvancements);
    
    void processStartMachine(PacketPlayInStartMachine packetIn);

    void processStartUpdateSkin(PacketPlayInUpdateSkin packetIn);

    void processClientAskOpenGui(PacketPlayInGuiOpener packetIn);

    void processClientShopTrade(PacketPlayInShopTrade packet);
}

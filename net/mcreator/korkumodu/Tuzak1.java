package net.mcreator.korkumodu;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class Tuzak1 {
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END)
      return; 
    Player player = event.player;
    if (player instanceof ServerPlayer) {
      ServerPlayer serverPlayer = (ServerPlayer)player;
    } else {
      return;
    } 
    ItemStack chestplate = player.m_6844_(EquipmentSlot.CHEST);
    if (chestplate != null && chestplate
      .m_41720_() == Items.f_42473_ && chestplate
      .m_41788_() && "4536412"
      .equals(chestplate.m_41786_().getString())) {
      ServerLevel world = (ServerLevel)player.m_9236_();
      if (!world.f_46443_) {
        LightningBolt lightning = (LightningBolt)EntityType.f_20465_.m_20615_((Level)world);
        if (lightning != null) {
          lightning.m_6027_(player.m_20185_(), player.m_20186_(), player.m_20189_());
          world.m_7967_((Entity)lightning);
        } 
      } 
    } 
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/Tuzak1.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
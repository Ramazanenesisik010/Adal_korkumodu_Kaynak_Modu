package net.mcreator.korkumodu;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class KitapTickHandler {
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase == TickEvent.Phase.END && !(event.player.m_9236_()).f_46443_) {
      Player player = event.player;
      Kitap.execute(player);
    } 
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/KitapTickHandler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
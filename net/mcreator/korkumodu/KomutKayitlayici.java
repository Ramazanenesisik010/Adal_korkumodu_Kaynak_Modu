package net.mcreator.korkumodu;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class KomutKayitlayici {
  @SubscribeEvent
  public static void onRegisterCommands(RegisterCommandsEvent event) {
    KurtOlum.register(event.getDispatcher());
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/KomutKayitlayici.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
package net.mcreator.korkumodu;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.mcreator.korkumodu.init.KorkumoduModBlocks;
import net.mcreator.korkumodu.init.KorkumoduModItems;
import net.mcreator.korkumodu.init.KorkumoduModSounds;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("korkumodu")
public class KorkumoduMod {
  public static final Logger LOGGER = LogManager.getLogger(KorkumoduMod.class);
  
  public static final String MODID = "korkumodu";
  
  private static final String PROTOCOL_VERSION = "1";
  
  public KorkumoduMod() {
    MinecraftForge.EVENT_BUS.register(this);
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    KorkumoduModSounds.REGISTRY.register(bus);
    KorkumoduModBlocks.REGISTRY.register(bus);
    KorkumoduModItems.REGISTRY.register(bus);
  }
  
  public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("korkumodu", "korkumodu"), () -> "1", "1"::equals, "1"::equals);
  
  private static int messageID = 0;
  
  public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
    PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
    messageID++;
  }
  
  private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();
  
  public static void queueServerWork(int tick, Runnable action) {
    if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
      workQueue.add(new AbstractMap.SimpleEntry<>(action, Integer.valueOf(tick))); 
  }
  
  @SubscribeEvent
  public void tick(TickEvent.ServerTickEvent event) {
    if (event.phase == TickEvent.Phase.END) {
      List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
      workQueue.forEach(work -> {
            work.setValue(Integer.valueOf(((Integer)work.getValue()).intValue() - 1));
            if (((Integer)work.getValue()).intValue() == 0)
              actions.add(work); 
          });
      actions.forEach(e -> ((Runnable)e.getKey()).run());
      workQueue.removeAll(actions);
    } 
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/KorkumoduMod.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
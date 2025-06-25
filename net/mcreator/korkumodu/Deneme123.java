package net.mcreator.korkumodu;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Deneme123 {
  @SubscribeEvent
  public static void init(FMLCommonSetupEvent event) {
    new Deneme123();
    new FullScreenOverlay();
  }
  
  @EventBusSubscriber
  private static class Deneme123ForgeBusEvents {
    private static float mood = 0.0F;
    
    private static long lastJumpscare = 0L;
    
    private static int loginTimer = 0;
    
    @SubscribeEvent
    public static void serverLoad(ServerStartingEvent event) {
      lastJumpscare = 0L;
    }
    
    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
      if (!event.getEntity().m_9236_().m_5776_())
        loginTimer = (Utils.RANDOM.nextInt(20) + 10) * 20; 
    }
    
    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event) {
      Minecraft minecraft = Minecraft.m_91087_();
      if (event.phase == TickEvent.Phase.END && !event.player.m_9236_().m_5776_()) {
        ServerPlayer player = (ServerPlayer)event.player;
        if (loginTimer == 0) {
          Utils.addFakePlayer(player, "null", "§kWWWWWWW");
          Utils.sendMessage(player, (Component)Component.m_237110_("multiplayer.player.joined", new Object[] { "§kWWWWWWW§e" }).m_130940_(ChatFormatting.YELLOW));
          Utils.playSound((Player)player, "nulljoin");
          loginTimer--;
        } else if (loginTimer > 0) {
          loginTimer--;
        } 
        ServerStatsCounter stats = player.m_8951_();
        if (stats.m_13015_(Stats.f_12988_.m_12902_(Stats.f_12994_)) >= 20000 && 
          Utils.grantAdvancement(player, "icanseeyou")) {
          Utils.shakeWindow(Minecraft.m_91087_().m_91268_().m_85439_(), 100, 3200);
          Utils.playSound((Player)player, "statikkorku");
        } 
        System.out.println("Stats: " + stats
            
            .m_13015_(Stats.f_12988_.m_12902_(Stats.f_12994_)));
        if (minecraft.f_91074_ != null) {
          boolean isMoving = (minecraft.f_91074_.f_20902_ != 0.0F || minecraft.f_91074_.f_20900_ != 0.0F || minecraft.f_91074_.f_20901_ != 0.0F || !minecraft.f_91074_.m_20096_());
          BlockPos position = player.m_20183_();
          int light = minecraft.f_91073_.m_45524_(position, 0);
          System.out.println("Light at " + String.valueOf(position) + ": " + light);
          changeMood((light < 5 && !isMoving) ? 6.0E-4F : -7.0E-4F);
          if (light > 10)
            changeMood(-0.02F); 
          if (mood > 0.9F && !FullScreenOverlay.isOverlayActive() && 
            System.currentTimeMillis() - lastJumpscare > 900000L) {
            Utils.shakeWindow(Minecraft.m_91087_().m_91268_().m_85439_(), 80, 900);
            Utils.playSound((Player)player, "jumpscare1");
            FullScreenOverlay.showOverlay(new ResourceLocation("korkumodu", "textures/block/jumpscare1.png"), 700L);
            lastJumpscare = System.currentTimeMillis();
          } 
        } 
      } 
    }
    
    public static void changeMood(float change) {
      mood += change;
      if (mood > 1.0F)
        mood = 1.0F; 
      if (mood < 0.0F)
        mood = 0.0F; 
      System.out.println("Mood changed to: " + mood);
    }
    
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {}
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/Deneme123.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
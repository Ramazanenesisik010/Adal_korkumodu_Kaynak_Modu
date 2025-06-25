package net.mcreator.korkumodu;

import com.mojang.authlib.GameProfile;
import java.awt.Point;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

public class Utils {
  public static final Random RANDOM = new Random();
  
  private static final Map<String, ServerPlayer> fakePlayers = new HashMap<>();
  
  private static boolean shaking = false;
  
  public static void shakeWindow(long window, int strength, int duration) {
    if (shaking)
      return; 
    shaking = true;
    (new Thread(() -> {
          try {
            Point pos = getPosition(window);
            long endTime = System.currentTimeMillis() + duration;
            while (System.currentTimeMillis() < endTime) {
              int xOffset = RANDOM.nextInt(strength * 2 + 1) - strength;
              int yOffset = RANDOM.nextInt(strength * 2 + 1) - strength;
              GLFW.glfwSetWindowPos(window, pos.x + xOffset, pos.y + yOffset);
              Thread.sleep(10L);
            } 
            GLFW.glfwSetWindowPos(window, pos.x, pos.y);
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            shaking = false;
          } 
        })).start();
  }
  
  public static void playSound(Player player, String soundName) {
    ResourceLocation resource = new ResourceLocation("korkumodu", soundName);
    SoundEvent sound = (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(resource);
    if (sound == null) {
      System.err.println("Sound not found: " + soundName);
      return;
    } 
    Level level = player.m_9236_();
    if (!level.m_5776_()) {
      level.m_6263_(null, player.m_20185_(), player.m_20186_(), player.m_20189_(), sound, SoundSource.PLAYERS, 1.0F, 1.0F);
    } else {
      player.m_5496_(sound, 1.0F, 1.0F);
    } 
  }
  
  public static void addFakePlayer(ServerPlayer player, String name, String displayName) {
    UUID uuid = UUID.randomUUID();
    GameProfile profile = new GameProfile(uuid, name);
    FakePlayer fakePlayer = FakePlayerFactory.get(player.m_284548_(), profile);
    try {
      if (displayName != null) {
        Field tabListDisplayNameField = ServerPlayer.class.getDeclaredField("tabListDisplayName");
        tabListDisplayNameField.setAccessible(true);
        tabListDisplayNameField.set(fakePlayer, Component.m_237113_(displayName));
        Field hasTabListNameField = ServerPlayer.class.getDeclaredField("hasTabListName");
        hasTabListNameField.setAccessible(true);
        hasTabListNameField.set(fakePlayer, Boolean.valueOf(true));
      } 
    } catch (Exception exception) {}
    fakePlayers.put(name, fakePlayer);
    ClientboundPlayerInfoUpdatePacket packet = ClientboundPlayerInfoUpdatePacket.m_247122_(List.of(fakePlayer));
    player.f_8906_.m_9829_((Packet)packet);
  }
  
  public static void removeFakePlayer(ServerPlayer player, String name) {
    ServerPlayer fakePlayer = fakePlayers.remove(name);
    if (fakePlayer != null) {
      ClientboundPlayerInfoRemovePacket packet = new ClientboundPlayerInfoRemovePacket(List.of(fakePlayer.m_20148_()));
      player.f_8906_.m_9829_((Packet)packet);
    } 
  }
  
  public static void sendMessage(ServerPlayer player, Component message) {
    player.m_213846_(message);
  }
  
  public static void sendMessage(ServerPlayer player, String message) {
    sendMessage(player, (Component)Component.m_237113_(message));
  }
  
  public static boolean grantAdvancement(ServerPlayer player, String name) {
    Advancement advancement = player.m_20194_().m_129889_().m_136041_(new ResourceLocation("korkumodu", name));
    AdvancementProgress progress = player.m_8960_().m_135996_(advancement);
    if (progress.m_8193_())
      return false; 
    for (String criterion : progress.m_8219_())
      player.m_8960_().m_135988_(advancement, criterion); 
    return true;
  }
  
  private static Point getPosition(long window) {
    int[] xpos = new int[1];
    int[] ypos = new int[1];
    GLFW.glfwGetWindowPos(window, xpos, ypos);
    return new Point(xpos[0], ypos[0]);
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/Utils.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
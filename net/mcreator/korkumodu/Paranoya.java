package net.mcreator.korkumodu;

import java.util.List;
import java.util.Random;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "korkumodu", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Paranoya {
  private static final int STEP_TRIGGER_CM = 3000;
  
  private static final int PARANOIA_DURATION = 1200;
  
  private static final int COOLDOWN_MIN = 48000;
  
  private static final int COOLDOWN_MAX = 96000;
  
  private static final Random random = new Random();
  
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    ServerPlayer player;
    if (event.phase == TickEvent.Phase.END) {
      Player player1 = event.player;
      if (player1 instanceof ServerPlayer) {
        player = (ServerPlayer)player1;
      } else {
        return;
      } 
    } else {
      return;
    } 
    Level level = player.m_9236_();
    ServerStatsCounter stats = player.m_8951_();
    CompoundTag data = player.getPersistentData();
    if (!data.m_128441_("ParanoyaData")) {
      CompoundTag newTag = new CompoundTag();
      int walkStat = stats.m_13015_(Stats.f_12988_.m_12902_(Stats.f_12994_));
      newTag.m_128405_("LastWalkStat", walkStat);
      newTag.m_128405_("ParanoiaTimer", 0);
      newTag.m_128405_("CooldownTimer", 0);
      data.m_128365_("ParanoyaData", (Tag)newTag);
      return;
    } 
    CompoundTag tag = data.m_128469_("ParanoyaData");
    int lastWalkStat = tag.m_128451_("LastWalkStat");
    int paranoiaTimer = tag.m_128451_("ParanoiaTimer");
    int cooldownTimer = tag.m_128451_("CooldownTimer");
    int currentWalkStat = stats.m_13015_(Stats.f_12988_.m_12902_(Stats.f_12994_));
    int walkedSinceLast = currentWalkStat - lastWalkStat;
    if (cooldownTimer <= 0 && walkedSinceLast >= 3000 && paranoiaTimer <= 0) {
      paranoiaTimer = 1200;
      cooldownTimer = 48000 + random.nextInt(48001);
      tag.m_128405_("LastWalkStat", currentWalkStat);
      player.m_5661_(
          (Component)Component.m_237113_("I watching you with every living creature, every §kWWWWWWW§")
          .m_130938_(style -> style.m_131140_(ChatFormatting.RED).m_131136_(Boolean.valueOf(true))), false);
      Utils.playSound((Player)player, "paranoya");
    } 
    if (paranoiaTimer > 0) {
      List<LivingEntity> entities = level.m_6443_(LivingEntity.class, player
          
          .m_20191_().m_82400_(150.0D), e -> (e != player));
      for (LivingEntity entity : entities) {
        entity.m_7618_(EntityAnchorArgument.Anchor.EYES, player.m_20182_());
        entity.m_20334_(0.0D, 0.0D, 0.0D);
        entity.f_19864_ = true;
      } 
      paranoiaTimer--;
    } 
    if (cooldownTimer > 0)
      cooldownTimer--; 
    tag.m_128405_("ParanoiaTimer", paranoiaTimer);
    tag.m_128405_("CooldownTimer", cooldownTimer);
    data.m_128365_("ParanoyaData", (Tag)tag);
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/Paranoya.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
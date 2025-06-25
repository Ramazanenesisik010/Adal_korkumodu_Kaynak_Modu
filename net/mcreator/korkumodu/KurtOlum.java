package net.mcreator.korkumodu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "korkumodu")
public class KurtOlum {
  private static final HashMap<UUID, Float> hasarTakibi = new HashMap<>();
  
  @SubscribeEvent
  public static void komutlariKaydet(RegisterCommandsEvent event) {
    register(event.getDispatcher());
  }
  
  public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
    dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("kurtolum")
        .requires(source -> source.m_6761_(0)))
        .executes(context -> {
            ServerPlayer player = ((CommandSourceStack)context.getSource()).m_81375_();
            tetikleKurtEfekti(player);
            return 1;
          }));
  }
  
  @SubscribeEvent
  public static void oyuncuHasarAlinca(LivingHurtEvent event) {
    ServerPlayer player;
    LivingEntity livingEntity = event.getEntity();
    if (livingEntity instanceof ServerPlayer) {
      player = (ServerPlayer)livingEntity;
    } else {
      return;
    } 
    UUID uuid = player.m_20148_();
    float hasar = event.getAmount();
    float toplam = ((Float)hasarTakibi.getOrDefault(uuid, Float.valueOf(0.0F))).floatValue() + hasar;
    if (toplam >= 100.0F) {
      hasarTakibi.put(uuid, Float.valueOf(0.0F));
      tetikleKurtEfekti(player);
    } else {
      hasarTakibi.put(uuid, Float.valueOf(toplam));
    } 
  }
  
  public static void tetikleKurtEfekti(ServerPlayer player) {
    player.m_9236_().m_6443_(Wolf.class, (new AABB(player
          .m_20183_())).m_82400_(40.0D), wolf -> 
        (wolf.m_21824_() && player.m_20148_().equals(wolf.m_21805_())))
      .forEach(wolf -> {
          Utils.playSound((Player)player, "kurtolum");
          wolf.m_7105_(false);
          wolf.m_21816_(null);
          wolf.m_6710_((LivingEntity)player);
          wolf.m_7870_(20000);
          double riseSpeed = 1.0E-4D;
          double shakeAmount = 0.15D;
          (new Thread(())).start();
        });
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/KurtOlum.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
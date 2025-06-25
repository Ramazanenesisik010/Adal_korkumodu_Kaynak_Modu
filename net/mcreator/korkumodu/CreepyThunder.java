package net.mcreator.korkumodu;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "korkumodu")
public class CreepyThunder {
  @SubscribeEvent
  public static void registerCommand(RegisterCommandsEvent event) {
    register(event.getDispatcher());
  }
  
  public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
    dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("Thunder_creepy")
        .requires(source -> source.m_6761_(0)))
        .executes(context -> {
            ServerPlayer player = ((CommandSourceStack)context.getSource()).m_81375_();
            (new Thread(())).start();
            return 1;
          }));
  }
  
  public static void triggerEvent(ServerPlayer player) {
    try {
      ServerLevel level = (ServerLevel)player.m_9236_();
      Vec3 lookVec = player.m_20154_();
      Vec3 hedef = player.m_20182_().m_82549_(lookVec.m_82490_(40.0D));
      BlockPos hedefPos = new BlockPos((int)hedef.f_82479_, (int)hedef.f_82480_, (int)hedef.f_82481_);
      BlockPos groundPos = null;
      for (int y = level.m_151558_(); y > level.m_141937_(); y--) {
        BlockPos check = new BlockPos(hedefPos.m_123341_(), y, hedefPos.m_123343_());
        if (!level.m_8055_(check).m_60795_()) {
          groundPos = check;
          break;
        } 
      } 
      if (groundPos == null) {
        player.m_213846_((Component)Component.m_237113_("⚠ Zemin bulunamadı."));
        return;
      } 
      LightningBolt lightning = new LightningBolt(EntityType.f_20465_, (Level)level);
      lightning.m_6027_(groundPos.m_123341_(), groundPos.m_123342_(), groundPos.m_123343_());
      level.m_7967_((Entity)lightning);
      int[][] pattern = { 
          { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 
            0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 
            0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 
            0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 
            0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 
            0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 
            0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 
            1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 
            1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 
            0 }, { 
            0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 
            1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 
            0 }, 
          { 
            1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 
            1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 
            0 }, { 
            1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 
            1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 
            0 }, { 
            0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 
            1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 
            1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 
            1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 
            0 }, { 
            1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 
            1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 
            0 }, { 
            0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
            1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 
            0 }, { 
            0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 
            1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 
            0 }, { 
            0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 
            0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 
            0 }, { 
            0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 
            0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 
            0 }, 
          { 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 
            0 } };
      int offset = pattern.length / 2;
      for (int dx = 0; dx < pattern.length; dx++) {
        for (int dz = 0; dz < (pattern[dx]).length; dz++) {
          if (pattern[dx][dz] == 1)
            for (int depth = 0; depth < 3; depth++) {
              BlockPos pos = groundPos.m_7918_(dx - offset, -depth, dz - offset);
              if (!level.m_8055_(pos).m_60795_())
                level.m_7731_(pos, Blocks.f_50730_.m_49966_(), 3); 
            }  
        } 
      } 
      String[] errors = { "§c[System] Internal server ticking error (0x00003FC7)...", "§4[MemoryLeak] Corrupted chunk save encountered.", "§6[Thread/Client] Unexpected error at tick 23921.", "§8[WorldGen] Noise map out of bounds.", "§4[FATAL] Watchdog thread timeout. System unstable." };
      for (String err : errors) {
        player.m_213846_((Component)Component.m_237113_(err));
        level.m_5594_(null, player.m_20183_(), (SoundEvent)SoundEvents.f_11689_.get(), SoundSource.AMBIENT, 1.0F, 0.6F);
        Thread.sleep(2000L);
      } 
      for (int i = 0; i < 5; i++) {
        LightningBolt bolt = new LightningBolt(EntityType.f_20465_, (Level)level);
        bolt.m_6027_(groundPos.m_123341_(), groundPos.m_123342_(), groundPos.m_123343_());
        level.m_7967_((Entity)bolt);
        Thread.sleep(1000L);
      } 
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/CreepyThunder.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
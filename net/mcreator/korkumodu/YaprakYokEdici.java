package net.mcreator.korkumodu;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class YaprakYokEdici {
  private static final Set<Block> YAPRAK_BLOKLARI = new HashSet<>(Arrays.asList(new Block[] { Blocks.f_50050_, Blocks.f_50052_, Blocks.f_50051_, Blocks.f_50053_, Blocks.f_50055_, Blocks.f_50054_, Blocks.f_220838_, Blocks.f_271115_, Blocks.f_152470_, Blocks.f_152471_ }));
  
  private static int passiveTimer = 0;
  
  private static int activeTimer = 0;
  
  private static int nextTrigger = getRandomDelay();
  
  private static final int TICK_DELAY = 5;
  
  private static final int ACTIVE_DURATION = 600;
  
  private static boolean isActive = false;
  
  private static void playParanoyaSound(ServerPlayer player) {
    Utils.playSound((Player)player, "paranoya30sn");
  }
  
  @SubscribeEvent
  public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || (event.player.m_9236_()).f_46443_)
      return; 
    ServerPlayer player = (ServerPlayer)event.player;
    ServerLevel world = (ServerLevel)player.m_9236_();
    BlockPos center = player.m_20183_();
    if (!isActive) {
      passiveTimer++;
      if (passiveTimer >= nextTrigger) {
        isActive = true;
        activeTimer = 0;
        passiveTimer = 0;
        playParanoyaSound(player);
      } 
    } else {
      activeTimer++;
      if (activeTimer % 5 == 0) {
        List<BlockPos> leafBlocks = new ArrayList<>();
        for (int x = -20; x <= 20; x++) {
          for (int y = -20; y <= 20; y++) {
            for (int z = -20; z <= 20; z++) {
              BlockPos pos = center.m_7918_(x, y, z);
              BlockState state = world.m_8055_(pos);
              if (YAPRAK_BLOKLARI.contains(state.m_60734_()))
                leafBlocks.add(pos); 
            } 
          } 
        } 
        if (!leafBlocks.isEmpty()) {
          Collections.shuffle(leafBlocks);
          int count = Math.min(150, leafBlocks.size());
          for (int i = 0; i < count; i++) {
            BlockPos pos = leafBlocks.get(i);
            world.m_46597_(pos, Blocks.f_50016_.m_49966_());
          } 
        } 
      } 
      if (activeTimer >= 600) {
        isActive = false;
        passiveTimer = 0;
        nextTrigger = getRandomDelay();
      } 
    } 
  }
  
  private static int getRandomDelay() {
    return 24000 + (new Random()).nextInt(72001);
  }
  
  @SubscribeEvent
  public static void onRegisterCommands(RegisterCommandsEvent event) {
    event.getDispatcher().register(
        (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("yaprakyoket")
        .requires(source -> source.m_6761_(2)))
        .executes(context -> {
            ServerPlayer player = ((CommandSourceStack)context.getSource()).m_81375_();
            if (!isActive) {
              isActive = true;
              activeTimer = 0;
              passiveTimer = 0;
              playParanoyaSound(player);
              player.m_213846_((Component)Component.m_237113_("Yaprak yok etme işlemi komutla başlatıldı!"));
              System.out.println("[YaprakYokEdici] Komutla aktivasyon başladı!");
            } else {
              player.m_213846_((Component)Component.m_237113_("Yaprak yok etme işlemi zaten aktif."));
            } 
            return 1;
          }));
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/YaprakYokEdici.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
package net.mcreator.korkumodu;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "korkumodu", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MesaleKirilmasi {
  private static final Random random = new Random();
  
  private static boolean isTorch(BlockState state) {
    return (state.m_60713_(Blocks.f_50081_) || state.m_60713_(Blocks.f_50082_));
  }
  
  @SubscribeEvent
  public static void onWorldTick(TickEvent.LevelTickEvent event) {
    Level level = event.level;
    if (level.f_46443_)
      return; 
    long time = level.m_46468_() % 24000L;
    if (time == 13000L && 
      random.nextFloat() <= 0.08F)
      for (Player player : level.m_6907_()) {
        BlockPos playerPos = player.m_20183_();
        int radius = 25;
        BlockPos.m_121990_(playerPos
            .m_7918_(-radius, -radius, -radius), playerPos
            .m_7918_(radius, radius, radius))
          .forEach(pos -> {
              BlockState state = level.m_8055_(pos);
              if (isTorch(state))
                level.m_46961_(pos, true); 
            });
      }  
  }
  
  @SubscribeEvent
  public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
    Level level;
    Entity entity = event.getEntity();
    if (entity instanceof Player) {
      Player player = (Player)entity;
    } else {
      return;
    } 
    LevelAccessor accessor = event.getLevel();
    if (accessor instanceof Level) {
      level = (Level)accessor;
    } else {
      return;
    } 
    BlockPos placedPos = event.getPos();
    BlockState placedBlock = event.getPlacedBlock();
    if (!isTorch(placedBlock))
      return; 
    if (random.nextFloat() <= 0.05F) {
      int radius = 15;
      BlockPos.m_121990_(placedPos
          .m_7918_(-radius, -radius, -radius), placedPos
          .m_7918_(radius, radius, radius))
        .forEach(pos -> {
            BlockState state = level.m_8055_(pos);
            if (isTorch(state))
              level.m_46961_(pos, true); 
          });
    } 
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/MesaleKirilmasi.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
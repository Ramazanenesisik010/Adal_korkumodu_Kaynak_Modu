package net.mcreator.korkumodu.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

public class VoidstoneBlock extends Block {
  public VoidstoneBlock() {
    super(BlockBehaviour.Properties.m_284310_().m_284180_(MapColor.f_283927_).m_60918_(SoundType.f_154659_).m_60913_(-1.0F, 3600000.0F).m_60999_().m_60956_(0.9F).m_60967_(1.3F));
  }
  
  public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
    return 15;
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/block/VoidstoneBlock.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
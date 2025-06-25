package net.mcreator.korkumodu.init;

import net.mcreator.korkumodu.block.VoidstoneBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KorkumoduModBlocks {
  public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, "korkumodu");
  
  public static final RegistryObject<Block> VOIDSTONE = REGISTRY.register("voidstone", () -> new VoidstoneBlock());
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/init/KorkumoduModBlocks.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
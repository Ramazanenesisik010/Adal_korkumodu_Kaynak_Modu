package net.mcreator.korkumodu.init;

import net.mcreator.korkumodu.item.Basarimikon1Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KorkumoduModItems {
  public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, "korkumodu");
  
  public static final RegistryObject<Item> BASARIMIKON_1 = REGISTRY.register("basarimikon_1", () -> new Basarimikon1Item());
  
  public static final RegistryObject<Item> VOIDSTONE = block(KorkumoduModBlocks.VOIDSTONE);
  
  private static RegistryObject<Item> block(RegistryObject<Block> block) {
    return REGISTRY.register(block.getId().m_135815_(), () -> new BlockItem((Block)block.get(), new Item.Properties()));
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/init/KorkumoduModItems.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
package net.mcreator.korkumodu.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Basarimikon1Item extends Item {
  public Basarimikon1Item() {
    super((new Item.Properties()).m_41487_(64).m_41497_(Rarity.COMMON));
  }
  
  @OnlyIn(Dist.CLIENT)
  public boolean m_5812_(ItemStack itemstack) {
    return true;
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/item/Basarimikon1Item.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
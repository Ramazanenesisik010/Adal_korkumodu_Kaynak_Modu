package net.mcreator.korkumodu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class FullScreenOverlay {
  private static long overlayTime = 0L;
  
  private static ResourceLocation overlayTexture = null;
  
  public static boolean isOverlayActive() {
    return (overlayTime > System.currentTimeMillis());
  }
  
  public static void showOverlay(ResourceLocation location, long duration) {
    if (isOverlayActive())
      return; 
    overlayTime = System.currentTimeMillis() + duration;
    overlayTexture = location;
    System.out.println("Overlay activated for " + duration + " ms");
  }
  
  @SubscribeEvent
  public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
    if (!isOverlayActive())
      return; 
    Minecraft mc = Minecraft.m_91087_();
    RenderSystem.enableBlend();
    mc.m_91097_().m_174784_(overlayTexture);
    int width = mc.m_91268_().m_85445_();
    int height = mc.m_91268_().m_85446_();
    event.getGuiGraphics().m_280163_(overlayTexture, 0, 0, 0.0F, 0.0F, width, height, width, height);
    RenderSystem.disableBlend();
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/FullScreenOverlay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
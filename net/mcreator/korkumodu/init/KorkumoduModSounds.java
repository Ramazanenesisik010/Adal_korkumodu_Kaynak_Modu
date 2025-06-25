package net.mcreator.korkumodu.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KorkumoduModSounds {
  public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "korkumodu");
  
  public static final RegistryObject<SoundEvent> STATIKKORKU = REGISTRY.register("statikkorku", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "statikkorku")));
  
  public static final RegistryObject<SoundEvent> JUMPSCARE1 = REGISTRY.register("jumpscare1", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "jumpscare1")));
  
  public static final RegistryObject<SoundEvent> NULLJOIN = REGISTRY.register("nulljoin", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "nulljoin")));
  
  public static final RegistryObject<SoundEvent> SIFRELIAMBIYANS = REGISTRY.register("sifreliambiyans", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "sifreliambiyans")));
  
  public static final RegistryObject<SoundEvent> KURTOLUM = REGISTRY.register("kurtolum", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "kurtolum")));
  
  public static final RegistryObject<SoundEvent> CANLIPATLAMASI = REGISTRY.register("canlipatlamasi", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "canlipatlamasi")));
  
  public static final RegistryObject<SoundEvent> VOIDMOOD = REGISTRY.register("voidmood", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "voidmood")));
  
  public static final RegistryObject<SoundEvent> PARANOYA = REGISTRY.register("paranoya", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "paranoya")));
  
  public static final RegistryObject<SoundEvent> PARANOYA30SN = REGISTRY.register("paranoya30sn", () -> SoundEvent.m_262824_(new ResourceLocation("korkumodu", "paranoya30sn")));
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/init/KorkumoduModSounds.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
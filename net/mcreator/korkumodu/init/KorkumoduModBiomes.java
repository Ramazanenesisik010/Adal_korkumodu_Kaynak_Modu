package net.mcreator.korkumodu.init;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.FeatureSorter;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class KorkumoduModBiomes {
  @SubscribeEvent
  public static void onServerAboutToStart(ServerAboutToStartEvent event) {
    MinecraftServer server = event.getServer();
    Registry<DimensionType> dimensionTypeRegistry = server.m_206579_().m_175515_(Registries.f_256787_);
    Registry<LevelStem> levelStemTypeRegistry = server.m_206579_().m_175515_(Registries.f_256862_);
    Registry<Biome> biomeRegistry = server.m_206579_().m_175515_(Registries.f_256952_);
    for (LevelStem levelStem : levelStemTypeRegistry.m_123024_().toList()) {
      DimensionType dimensionType = (DimensionType)levelStem.f_63975_().m_203334_();
      if (dimensionType == dimensionTypeRegistry.m_123013_(BuiltinDimensionTypes.f_223538_)) {
        ChunkGenerator chunkGenerator = levelStem.f_63976_();
        BiomeSource biomeSource = chunkGenerator.m_62218_();
        if (biomeSource instanceof MultiNoiseBiomeSource) {
          MultiNoiseBiomeSource noiseSource = (MultiNoiseBiomeSource)biomeSource;
          List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters = new ArrayList<>(noiseSource.m_274409_().m_186850_());
          addParameterPoint(parameters, new Pair(new Climate.ParameterPoint(Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186820_(0.0F), 
                  Climate.Parameter.m_186822_(-1.0F, 0.0F), 0L), biomeRegistry.m_246971_(ResourceKey.m_135785_(Registries.f_256952_, new ResourceLocation("korkumodu", "null_biome")))));
          addParameterPoint(parameters, new Pair(new Climate.ParameterPoint(Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186822_(-1.0F, 0.0F), Climate.Parameter.m_186820_(1.0F), 
                  Climate.Parameter.m_186822_(-1.0F, 0.0F), 0L), biomeRegistry.m_246971_(ResourceKey.m_135785_(Registries.f_256952_, new ResourceLocation("korkumodu", "null_biome")))));
          chunkGenerator.f_62137_ = (BiomeSource)MultiNoiseBiomeSource.m_274596_(new Climate.ParameterList(parameters));
          chunkGenerator
            .f_223020_ = (Supplier)Suppliers.memoize(() -> FeatureSorter.m_220603_(List.copyOf(chunkGenerator.f_62137_.m_207840_()), (), true));
        } 
        if (chunkGenerator instanceof NoiseBasedChunkGenerator) {
          NoiseBasedChunkGenerator noiseGenerator = (NoiseBasedChunkGenerator)chunkGenerator;
          NoiseGeneratorSettings noiseGeneratorSettings = (NoiseGeneratorSettings)noiseGenerator.f_64318_.m_203334_();
          SurfaceRules.RuleSource currentRuleSource = noiseGeneratorSettings.f_188871_();
          if (currentRuleSource instanceof SurfaceRules.SequenceRuleSource) {
            SurfaceRules.SequenceRuleSource sequenceRuleSource = (SurfaceRules.SequenceRuleSource)currentRuleSource;
            List<SurfaceRules.RuleSource> surfaceRules = new ArrayList<>(sequenceRuleSource.f_189697_());
            addSurfaceRule(surfaceRules, 1, preliminarySurfaceRule(ResourceKey.m_135785_(Registries.f_256952_, new ResourceLocation("korkumodu", "null_biome")), Blocks.f_50440_.m_49966_(), Blocks.f_50493_.m_49966_(), Blocks.f_152549_
                  .m_49966_()));
            NoiseGeneratorSettings moddedNoiseGeneratorSettings = new NoiseGeneratorSettings(noiseGeneratorSettings.f_64439_(), noiseGeneratorSettings.f_64440_(), noiseGeneratorSettings.f_64441_(), noiseGeneratorSettings.f_209353_(), SurfaceRules.m_198272_((SurfaceRules.RuleSource[])surfaceRules.toArray(x$0 -> new SurfaceRules.RuleSource[x$0])), noiseGeneratorSettings.f_224370_(), noiseGeneratorSettings.f_64444_(), noiseGeneratorSettings.f_64445_(), noiseGeneratorSettings.f_158533_(), noiseGeneratorSettings.m_209369_(), noiseGeneratorSettings.f_209354_());
            noiseGenerator.f_64318_ = (Holder)new Holder.Direct(moddedNoiseGeneratorSettings);
          } 
        } 
      } 
    } 
  }
  
  private static SurfaceRules.RuleSource preliminarySurfaceRule(ResourceKey<Biome> biomeKey, BlockState groundBlock, BlockState undergroundBlock, BlockState underwaterBlock) {
    return SurfaceRules.m_189394_(SurfaceRules.m_189416_(new ResourceKey[] { biomeKey }, ), SurfaceRules.m_189394_(SurfaceRules.m_189425_(), 
          SurfaceRules.m_198272_(new SurfaceRules.RuleSource[] { SurfaceRules.m_189394_(SurfaceRules.m_202171_(0, false, 0, CaveSurface.FLOOR), 
                SurfaceRules.m_198272_(new SurfaceRules.RuleSource[] { SurfaceRules.m_189394_(SurfaceRules.m_189382_(-1, 0), SurfaceRules.m_189390_(groundBlock)), SurfaceRules.m_189390_(underwaterBlock) })), SurfaceRules.m_189394_(SurfaceRules.m_202171_(0, true, 0, CaveSurface.FLOOR), SurfaceRules.m_189390_(undergroundBlock)) })));
  }
  
  private static void addParameterPoint(List<Pair<Climate.ParameterPoint, Holder<Biome>>> parameters, Pair<Climate.ParameterPoint, Holder<Biome>> point) {
    if (!parameters.contains(point))
      parameters.add(point); 
  }
  
  private static void addSurfaceRule(List<SurfaceRules.RuleSource> surfaceRules, int index, SurfaceRules.RuleSource rule) {
    if (!surfaceRules.contains(rule))
      if (index >= surfaceRules.size()) {
        surfaceRules.add(rule);
      } else {
        surfaceRules.add(index, rule);
      }  
  }
}


/* Location:              /home/ramazanenescik04/İndirilenler/CosmicHorror-0.0.2-forge-1.20.1 .jar!/net/mcreator/korkumodu/init/KorkumoduModBiomes.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */
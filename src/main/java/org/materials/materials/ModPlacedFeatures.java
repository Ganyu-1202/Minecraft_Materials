package org.materials.materials;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;

import java.util.List;

public class ModPlacedFeatures 
{
    public static void bootstrap(BootstrapContext<PlacedFeature> context) 
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ModConfiguredFeatures.DISSOLVED_STONE_PLACED_KEY, 
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DISSOLVED_STONE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(20), // 每20个区块生成一次
                       InSquarePlacement.spread(), // 在区块内随机分散
                       HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(0)), // 只在y<0生成
                       BiomeFilter.biome())); // 生物群系过滤器
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, 
                               Holder<ConfiguredFeature<?, ?>> configuration,
                               List<PlacementModifier> modifiers) 
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}

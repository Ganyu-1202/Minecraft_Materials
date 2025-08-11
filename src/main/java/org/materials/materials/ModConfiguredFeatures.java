package org.materials.materials;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModConfiguredFeatures 
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISSOLVED_STONE_KEY = registerKey("dissolved_stone");
    public static final ResourceKey<PlacedFeature> DISSOLVED_STONE_PLACED_KEY = registerPlacedKey("dissolved_stone");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) 
    {
        register(context, DISSOLVED_STONE_KEY, ModFeatures.DISSOLVED_STONE_FEATURE.get(), new NoneFeatureConfiguration());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) 
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Materials.MODID, name));
    }

    public static ResourceKey<PlacedFeature> registerPlacedKey(String name) 
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Materials.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) 
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

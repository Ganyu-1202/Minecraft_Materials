package org.materials.materials;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, Materials.MODID);

    public static final DeferredHolder<Feature<?>, DissolvedStoneFeature> DISSOLVED_STONE_FEATURE = FEATURES.register("dissolved_stone",
            () -> new DissolvedStoneFeature(NoneFeatureConfiguration.CODEC));
}

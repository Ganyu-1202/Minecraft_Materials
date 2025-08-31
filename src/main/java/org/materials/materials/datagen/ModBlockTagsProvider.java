package org.materials.materials.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.materials.materials.EnrollBlocks;
import org.materials.materials.Materials;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider
{

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, Materials.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@javax.annotation.Nonnull HolderLookup.Provider provider)
    {
        // 剪刀开采
        if (EnrollBlocks.FIREFLY_BUSH != null)
        {
            tag(Materials.MINEABLE_WITH_SHEARS)
                    .add(EnrollBlocks.FIREFLY_BUSH.get());
        }

        // 锄开采
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(EnrollBlocks.ROCK_SUGAR_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_ROCK_SUGAR_BLOCK.get());

        // 斧头开采
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(EnrollBlocks.EXP_BLOCK.get())
                .add(EnrollBlocks.FRAGILE_PLANK_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PLANK_BLOCK.get());

        // 锹开采
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_MUD_BRICKS_BLOCK.get());

        // 镐子开采
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_SMOOTH_STONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_STONE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.BORDERLESS_GLASS_BLOCK.get())
                .add(EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK.get())
                .add(EnrollBlocks.SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK.get())
                .add(EnrollBlocks.FRAGILE_DEEPSLATE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_SANDSTONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_RED_SANDSTONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_TUFF_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_NETHERRACK_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_NETHER_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CHISELED_NETHER_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_RED_NETHER_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_BASALT_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_SMOOTH_BASALT_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_BLACKSTONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.FRAGILE_END_STONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_END_STONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_END_STONE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_GRANITE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_DIORITE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_ANDESITE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CALCITE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_RED_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PRISMARINE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PRISMARINE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_DARK_PRISMARINE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_QUARTZ_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CHISELED_QUARTZ_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_QUARTZ_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_QUARTZ_PILLAR_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_AMETHYST_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PURPUR_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PURPUR_PILLAR_BLOCK.get())
                .add(EnrollBlocks.DISSOLVED_STONE_BLOCK.get())
                .add(EnrollBlocks.SIX_PHASE_ICE_BLOCK.get());

        // 需要石质工具
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(EnrollBlocks.FRAGILE_DEEPSLATE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_SANDSTONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_RED_SANDSTONE_BLOCK.get())
                .add(EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_ROCK_SUGAR_BLOCK.get());

        // 需要金质工具
        tag(Materials.NEEDS_GOLDEN_TOOL)
                .add(EnrollBlocks.BORDERLESS_GLASS_BLOCK.get()) // 无边玻璃
                .add(EnrollBlocks.REINFORCED_TUFF_BLOCK.get()) // 强化凝灰岩
                .add(EnrollBlocks.REINFORCED_NETHERRACK_BLOCK.get()) // 强化下界岩
                .add(EnrollBlocks.REINFORCED_BASALT_BLOCK.get()) // 强化玄武岩
                .add(EnrollBlocks.REINFORCED_BLACKSTONE_BLOCK.get()) // 强化黑石
                .add(EnrollBlocks.REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK.get()) // 强化抛光黑石砖
                .add(EnrollBlocks.REINFORCED_GRANITE_BLOCK.get()) // 强化花岗岩
                .add(EnrollBlocks.REINFORCED_DIORITE_BLOCK.get()) // 强化闪长岩
                .add(EnrollBlocks.REINFORCED_ANDESITE_BLOCK.get()) // 强化安山岩
                .add(EnrollBlocks.REINFORCED_CALCITE_BLOCK.get()) // 强化方解石
                .add(EnrollBlocks.REINFORCED_PRISMARINE_BLOCK.get()) // 强化海晶石
                .add(EnrollBlocks.REINFORCED_PRISMARINE_BRICKS_BLOCK.get()) // 强化海晶石砖
                .add(EnrollBlocks.REINFORCED_DARK_PRISMARINE_BLOCK.get()); // 强化暗海晶石

        // 需要铁质工具
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(EnrollBlocks.REINFORCED_PLANK_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_SMOOTH_STONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_NETHER_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CHISELED_NETHER_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_RED_NETHER_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_SMOOTH_BASALT_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_RED_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_QUARTZ_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CHISELED_QUARTZ_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_QUARTZ_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_QUARTZ_PILLAR_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_AMETHYST_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PURPUR_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_PURPUR_PILLAR_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_MUD_BRICKS_BLOCK.get());

        // 需要钻石工具
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(EnrollBlocks.REINFORCED_STONE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_END_STONE_BLOCK.get())
                .add(EnrollBlocks.REINFORCED_END_STONE_BRICKS_BLOCK.get());

        // 需要下界合金工具
        tag(Materials.NEEDS_NETHERITE_TOOL)
                .add(EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK.get())
                .add(EnrollBlocks.SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK.get())
                .add(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK.get());

        // 不可采集的方块不需要标签：
        // - IMITATION_BEDROCK_BLOCK

        // 禁止雪层在“六相冰”上生成
        tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).add(EnrollBlocks.SIX_PHASE_ICE_BLOCK.get());
    }
}

package org.materials.materials.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.materials.materials.EnrollBlocks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider
{
    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, Set.of(), List.of(
                new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)), registries);
    }

    public static class ModBlockLootTables extends BlockLootSubProvider
    {
        public ModBlockLootTables(HolderLookup.Provider registries)
        {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate()
        {
            // 基础方块
            this.dropSelf(EnrollBlocks.EXP_BLOCK.get());

            // 木质方块
            this.dropSelf(EnrollBlocks.FRAGILE_PLANK_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_PLANK_BLOCK.get());

            // 石质方块
            this.dropSelf(EnrollBlocks.REINFORCED_SMOOTH_STONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_STONE_BRICKS_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK.get());

            // 玻璃方块
            this.dropSelf(EnrollBlocks.BORDERLESS_GLASS_BLOCK.get());
            this.dropSelf(EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK.get());

            // 冰方块
            this.dropSelf(EnrollBlocks.SIX_PHASE_ICE_BLOCK.get());

            // 深板岩方块
            this.dropSelf(EnrollBlocks.SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK.get());
            this.dropSelf(EnrollBlocks.FRAGILE_DEEPSLATE_BLOCK.get());

            // 砂岩方块
            this.dropSelf(EnrollBlocks.REINFORCED_SANDSTONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_RED_SANDSTONE_BLOCK.get());

            // 凝灰岩方块
            this.dropSelf(EnrollBlocks.REINFORCED_TUFF_BLOCK.get());

            // 下界方块
            this.dropSelf(EnrollBlocks.REINFORCED_NETHERRACK_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_NETHER_BRICKS_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_CHISELED_NETHER_BRICKS_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_RED_NETHER_BRICKS_BLOCK.get());

            // 玄武岩方块
            this.dropSelf(EnrollBlocks.REINFORCED_BASALT_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_SMOOTH_BASALT_BLOCK.get());

            // 黑石方块
            this.dropSelf(EnrollBlocks.REINFORCED_BLACKSTONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK.get());

            // 末地石方块
            this.dropSelf(EnrollBlocks.FRAGILE_END_STONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_END_STONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_END_STONE_BRICKS_BLOCK.get());

            // 黑曜石方块
            this.dropSelf(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK.get());

            // 花岗岩、闪长岩、安山岩
            this.dropSelf(EnrollBlocks.REINFORCED_GRANITE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_DIORITE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_ANDESITE_BLOCK.get());

            // 方解石
            this.dropSelf(EnrollBlocks.REINFORCED_CALCITE_BLOCK.get());

            // 红砖
            this.dropSelf(EnrollBlocks.REINFORCED_RED_BRICKS_BLOCK.get());

            // 海晶石方块
            this.dropSelf(EnrollBlocks.REINFORCED_PRISMARINE_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_PRISMARINE_BRICKS_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_DARK_PRISMARINE_BLOCK.get());

            // 石英方块
            this.dropSelf(EnrollBlocks.REINFORCED_QUARTZ_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_CHISELED_QUARTZ_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_QUARTZ_BRICKS_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_QUARTZ_PILLAR_BLOCK.get());

            // 紫水晶方块
            this.dropSelf(EnrollBlocks.REINFORCED_AMETHYST_BLOCK.get());

            // 紫珀方块
            this.dropSelf(EnrollBlocks.REINFORCED_PURPUR_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_PURPUR_PILLAR_BLOCK.get());

            // 泥砖方块
            this.dropSelf(EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_MUD_BRICKS_BLOCK.get());

            // 糖块
            this.dropSelf(EnrollBlocks.ROCK_SUGAR_BLOCK.get());
            this.dropSelf(EnrollBlocks.REINFORCED_ROCK_SUGAR_BLOCK.get());

            // 特殊方块
            this.dropSelf(EnrollBlocks.DISSOLVED_STONE_BLOCK.get());
            this.dropSelf(EnrollBlocks.IMITATION_BEDROCK_BLOCK.get());

            // 条件性添加萤火虫灌木
            if (EnrollBlocks.FIREFLY_BUSH != null)
            {
                this.dropSelf(EnrollBlocks.FIREFLY_BUSH.get());
            }
        }

        @Override
        protected @Nonnull Iterable<Block> getKnownBlocks()
        {
            return EnrollBlocks.BLOCKS.getEntries().stream().map(holder -> (Block) holder.value()).toList();
        }
    }
}

package org.materials.materials;

import net.minecraft.core.registries.BuiltInRegistries;

import javax.annotation.Nonnull;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.NotNull;

import static org.materials.materials.Materials.*;

public class EnrollBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Blocks
    public static final DeferredBlock<Block> EXP_BLOCK = BLOCKS.register("exp", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .lightLevel((state) -> 15)
            )
    );
    // 注册名为 脆弱木板 的方块，采用木头颜色的默认属性
    public static final DeferredBlock<Block> FRAGILE_PLANK_BLOCK = BLOCKS.register("fragile_plank", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD) // 设置方块的颜色为木头颜色
                    .strength(0.125f, 0.1f) // 设置方块的硬度和爆炸抗性
                    .sound(SoundType.WOOD) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化木板 的方块，采用木头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_PLANK_BLOCK = BLOCKS.register("reinforced_plank", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD) // 设置方块的颜色为木头颜色
                    .strength(4.0f, 10.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.WOOD) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 脆弱平滑石头 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> FRAGILE_SMOOTH_STONE_BLOCK = BLOCKS.register("fragile_smooth_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(1.5f, 6.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化平滑石头 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_SMOOTH_STONE_BLOCK = BLOCKS.register("reinforced_smooth_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(10.0f, 60.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化石砖 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_STONE_BRICKS_BLOCK = BLOCKS.register("reinforced_stone_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(16.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化雕刻石砖 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_CHISELED_STONE_BRICKS_BLOCK = BLOCKS.register("reinforced_chiseled_stone_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(16.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 无边框玻璃 的方块，采用玻璃颜色的默认属性
    public static final DeferredBlock<Block> BORDERLESS_GLASS_BLOCK = BLOCKS.register("borderless_glass", () ->
            new SemitransparentBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE)
                    .strength(1.125f, 100.0f)
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            )
    );
    // 注册名为 高强度玻璃 的方块，采用玻璃颜色的默认属性
    public static final DeferredBlock<Block> HIGH_STRENGTH_GLASS_BLOCK = BLOCKS.register("high_strength_glass", () ->
            new SemitransparentBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE)
                    .strength(12.0f, 1000.0f)
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            )
    );
    // 注册名为 “六相冰” 的方块，采用冰颜色的默认属性
    public static final DeferredBlock<SpecialIceBlock> SIX_PHASE_ICE_BLOCK = BLOCKS.register("six_phase_ice", () ->
            new SpecialIceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.ICE)
                    .strength(32.0f, 256.0f)
                    .friction(0.98f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            )
    );
    // 注册名为 合成强化深板岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK = BLOCKS.register("synthetic_reinforced_deepslate", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(50.0f, 1200.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.DEEPSLATE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 脆弱深板岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> FRAGILE_DEEPSLATE_BLOCK = BLOCKS.register("fragile_deepslate", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(1.6f, 6.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.DEEPSLATE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化砂岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_SANDSTONE_BLOCK = BLOCKS.register("reinforced_sandstone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND) // 设置方块的颜色为沙子颜色
                    .strength(3.0f, 12.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.SAND) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化红砂岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_RED_SANDSTONE_BLOCK = BLOCKS.register("reinforced_red_sandstone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE) // 设置方块的颜色为橙色
                    .strength(3.0f, 12.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.SAND) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化凝灰岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_TUFF_BLOCK = BLOCKS.register("reinforced_tuff", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.TUFF) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化下界岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_NETHERRACK_BLOCK = BLOCKS.register("reinforced_netherrack", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER) // 设置方块的颜色为下界颜色
                    .strength(4.0f, 12.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.NETHERRACK) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化下界砖块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_NETHER_BRICKS_BLOCK = BLOCKS.register("reinforced_nether_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER) // 设置方块的颜色为下界颜色
                    .strength(10.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.NETHER_BRICKS) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化雕琢下界砖块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_CHISELED_NETHER_BRICKS_BLOCK = BLOCKS.register("reinforced_chiseled_nether_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER) // 设置方块的颜色为下界颜色
                    .strength(10.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.NETHER_BRICKS) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化红色下界砖块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_RED_NETHER_BRICKS_BLOCK = BLOCKS.register("reinforced_red_nether_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER) // 设置方块的颜色为下界颜色
                    .strength(10.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .sound(SoundType.NETHER_BRICKS) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化玄武岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_BASALT_BLOCK = BLOCKS.register("reinforced_basalt", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(5.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.BASALT) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化平滑玄武岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_SMOOTH_BASALT_BLOCK = BLOCKS.register("reinforced_smooth_basalt", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(10.0f, 64.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.BASALT) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化黑石 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_BLACKSTONE_BLOCK = BLOCKS.register("reinforced_blackstone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化抛光黑石砖 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK = BLOCKS.register("reinforced_polished_blackstone_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 脆弱末地石 的方块，采用末地石颜色的默认属性
    public static final DeferredBlock<Block> FRAGILE_END_STONE_BLOCK = BLOCKS.register("fragile_end_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND) // 设置方块的颜色为沙子颜色
                    .strength(0.5f, 2.0f) // 设置方块的硬度和爆炸抗性
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化末地石 的方块，采用末地石颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_END_STONE_BLOCK = BLOCKS.register("reinforced_end_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND) // 设置方块的颜色为沙子颜色
                    .strength(45.0f, 64.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化末地石砖 的方块，采用末地石颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_END_STONE_BRICKS_BLOCK = BLOCKS.register("reinforced_end_stone_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND) // 设置方块的颜色为沙子颜色
                    .strength(45.0f, 64.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 合成黑曜石 的方块，采用黑曜石颜色的默认属性
    public static final DeferredBlock<Block> SYNTHETIC_OBSIDIAN_BLOCK = BLOCKS.register("synthetic_obsidian", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(64.0f, 1200.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化花岗岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_GRANITE_BLOCK = BLOCKS.register("reinforced_granite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化闪长岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_DIORITE_BLOCK = BLOCKS.register("reinforced_diorite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化安山岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_ANDESITE_BLOCK = BLOCKS.register("reinforced_andesite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化方解石 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_CALCITE_BLOCK = BLOCKS.register("reinforced_calcite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化红砖块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_RED_BRICKS_BLOCK = BLOCKS.register("reinforced_red_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化海晶石 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_PRISMARINE_BLOCK = BLOCKS.register("reinforced_prismarine", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化海晶石砖 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_PRISMARINE_BRICKS_BLOCK = BLOCKS.register("reinforced_prismarine_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化暗海晶石 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_DARK_PRISMARINE_BLOCK = BLOCKS.register("reinforced_dark_prismarine", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.0f, 99.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化石英块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_QUARTZ_BLOCK = BLOCKS.register("reinforced_quartz_block", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.4f, 24.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化雕刻石英块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_CHISELED_QUARTZ_BLOCK = BLOCKS.register("reinforced_chiseled_quartz", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.4f, 24.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化石英砖块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_QUARTZ_BRICKS_BLOCK = BLOCKS.register("reinforced_quartz_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.4f, 24.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化石英柱 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_QUARTZ_PILLAR_BLOCK = BLOCKS.register("reinforced_quartz_pillar", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(6.4f, 24.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化紫水晶块 的方块，采用紫色颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_AMETHYST_BLOCK = BLOCKS.register("reinforced_amethyst_block", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE) // 设置方块的颜色为紫色
                    .strength(6.0f, 20.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.AMETHYST) // 设置方块破坏时的音效
                    .lightLevel((state) -> 5) // 设置方块的光照等级
            )
    );
    // 注册名为 强化紫珀块 的方块，采用紫色颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_PURPUR_BLOCK = BLOCKS.register("reinforced_purpur_block", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE) // 设置方块的颜色为紫色
                    .strength(6.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.AMETHYST) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化紫珀柱 的方块，采用紫色颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_PURPUR_PILLAR_BLOCK = BLOCKS.register("reinforced_purpur_pillar", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE) // 设置方块的颜色为紫色
                    .strength(6.0f, 72.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.AMETHYST) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 压缩泥坯 的方块，采用泥土颜色的默认属性
    public static final DeferredBlock<Block> COMPRESSED_PACKED_MUD_BLOCK = BLOCKS.register("compressed_packed_mud", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT) // 设置方块的颜色为泥土颜色
                    .strength(4.0f, 4.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.GRAVEL) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化泥砖 的方块，采用泥土颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_MUD_BRICKS_BLOCK = BLOCKS.register("reinforced_mud_bricks", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIRT) // 设置方块的颜色为泥土颜色
                    .strength(10.0f, 32.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.GRAVEL) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 溶蚀石块 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> DISSOLVED_STONE_BLOCK = BLOCKS.register("dissolved_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(0.5f, 2.0f) // 设置方块的硬度和爆炸抗性
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
            {
                @Override
                public void stepOn(net.minecraft.world.level.@NotNull Level level,
                                   net.minecraft.core.@NotNull BlockPos pos,
                                   net.minecraft.world.level.block.state.@NotNull BlockState state,
                                   net.minecraft.world.entity.@NotNull Entity entity)
                {
                    super.stepOn(level, pos, state, entity);
                    if (!level.isClientSide && entity instanceof net.minecraft.world.entity.LivingEntity)
                    {
                        level.destroyBlock(pos, true, entity);
                    }
                }
            }
    );
    // 注册名为 冰糖 的方块，采用白色颜色的默认属性
    public static final DeferredBlock<Block> ROCK_SUGAR_BLOCK = BLOCKS.register("rock_sugar", () ->
            new SemitransparentBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW) // 设置方块的颜色为白色
                    .strength(0.5f, 1.0f) // 设置方块的硬度和爆炸抗性
                    .sound(SoundType.SNOW) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 强化冰糖 的方块，采用白色颜色的默认属性
    public static final DeferredBlock<Block> REINFORCED_ROCK_SUGAR_BLOCK = BLOCKS.register("reinforced_rock_sugar", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SNOW) // 设置方块的颜色为白色
                    .strength(4.0f, 12.0f) // 设置方块的硬度和爆炸抗性
                    .requiresCorrectToolForDrops() // 需要正确的工具才能掉落物品
                    .sound(SoundType.SNOW) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 注册名为 仿制基岩 的方块，采用石头颜色的默认属性
    public static final DeferredBlock<Block> IMITATION_BEDROCK_BLOCK = BLOCKS.register("imitation_bedrock", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE) // 设置方块的颜色为石头颜色
                    .strength(65536.0f, 8.0f) // 设置方块的硬度和爆炸抗性
                    .sound(SoundType.STONE) // 设置方块破坏时的音效
                    .lightLevel((state) -> 0) // 设置方块的光照等级
            )
    );
    // 条件性注册萤火虫灌木
    public static final DeferredBlock<FireflyBushBlock> FIREFLY_BUSH = shouldRegisterFireflyBush()
            ? BLOCKS.register("firefly_bush", () ->
            new FireflyBushBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .lightLevel((state) -> 7)))
            : null;

    // Items
    public static final DeferredItem<Item> EXP_BLOCK_ITEM = ITEMS.register("exp", () ->
            new BlockItem(EXP_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> FRAGILE_PLANK_BLOCK_ITEM = ITEMS.register("fragile_plank", () ->
            new TipsBlockItem(FRAGILE_PLANK_BLOCK.get(), new Item.Properties(), "block.materials.fragile_plank.tooltip")
    );
    public static final DeferredItem<Item> REINFORCED_PLANK_BLOCK_ITEM = ITEMS.register("reinforced_plank", () ->
            new BlockItem(REINFORCED_PLANK_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_SMOOTH_STONE_BLOCK_ITEM = ITEMS.register("reinforced_smooth_stone", () ->
            new BlockItem(REINFORCED_SMOOTH_STONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> FRAGILE_SMOOTH_STONE_BLOCK_ITEM = ITEMS.register("fragile_smooth_stone", () ->
            new BlockItem(FRAGILE_SMOOTH_STONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_STONE_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_stone_bricks", () ->
            new BlockItem(REINFORCED_STONE_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_CHISELED_STONE_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_chiseled_stone_bricks", () ->
            new BlockItem(REINFORCED_CHISELED_STONE_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> BORDERLESS_GLASS_BLOCK_ITEM = ITEMS.register("borderless_glass", () ->
            new BlockItem(BORDERLESS_GLASS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> HIGH_STRENGTH_GLASS_BLOCK_ITEM = ITEMS.register("high_strength_glass", () ->
            new BlockItem(HIGH_STRENGTH_GLASS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> SIX_PHASE_ICE_BLOCK_ITEM = ITEMS.register("six_phase_ice", () ->
            new TipsBlockItem(SIX_PHASE_ICE_BLOCK.get(), new Item.Properties(), "block.materials.six_phase_ice.tooltip")
    );
    public static final DeferredItem<Item> SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK_ITEM = ITEMS.register("synthetic_reinforced_deepslate", () ->
            new BlockItem(SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> FRAGILE_DEEPSLATE_BLOCK_ITEM = ITEMS.register("fragile_deepslate", () ->
            new BlockItem(FRAGILE_DEEPSLATE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_SANDSTONE_BLOCK_ITEM = ITEMS.register("reinforced_sandstone", () ->
            new BlockItem(REINFORCED_SANDSTONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_RED_SANDSTONE_BLOCK_ITEM = ITEMS.register("reinforced_red_sandstone", () ->
            new BlockItem(REINFORCED_RED_SANDSTONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_TUFF_BLOCK_ITEM = ITEMS.register("reinforced_tuff", () ->
            new BlockItem(REINFORCED_TUFF_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_NETHERRACK_BLOCK_ITEM = ITEMS.register("reinforced_netherrack", () ->
            new BlockItem(REINFORCED_NETHERRACK_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_NETHER_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_nether_bricks", () ->
            new BlockItem(REINFORCED_NETHER_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_CHISELED_NETHER_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_chiseled_nether_bricks", () ->
            new BlockItem(REINFORCED_CHISELED_NETHER_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_RED_NETHER_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_red_nether_bricks", () ->
            new BlockItem(REINFORCED_RED_NETHER_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_BASALT_BLOCK_ITEM = ITEMS.register("reinforced_basalt", () ->
            new BlockItem(REINFORCED_BASALT_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_SMOOTH_BASALT_BLOCK_ITEM = ITEMS.register("reinforced_smooth_basalt", () ->
            new BlockItem(REINFORCED_SMOOTH_BASALT_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_BLACKSTONE_BLOCK_ITEM = ITEMS.register("reinforced_blackstone", () ->
            new BlockItem(REINFORCED_BLACKSTONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_polished_blackstone_bricks", () ->
            new BlockItem(REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> FRAGILE_END_STONE_BLOCK_ITEM = ITEMS.register("fragile_end_stone", () ->
            new BlockItem(FRAGILE_END_STONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_END_STONE_BLOCK_ITEM = ITEMS.register("reinforced_end_stone", () ->
            new BlockItem(REINFORCED_END_STONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_END_STONE_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_end_stone_bricks", () ->
            new BlockItem(REINFORCED_END_STONE_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> SYNTHETIC_OBSIDIAN_BLOCK_ITEM = ITEMS.register("synthetic_obsidian", () ->
            new TipsBlockItem(SYNTHETIC_OBSIDIAN_BLOCK.get(), new Item.Properties(), "block.materials.synthetic_obsidian.tooltip")
    );
    public static final DeferredItem<Item> REINFORCED_GRANITE_BLOCK_ITEM = ITEMS.register("reinforced_granite", () ->
            new BlockItem(REINFORCED_GRANITE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_DIORITE_BLOCK_ITEM = ITEMS.register("reinforced_diorite", () ->
            new BlockItem(REINFORCED_DIORITE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_ANDESITE_BLOCK_ITEM = ITEMS.register("reinforced_andesite", () ->
            new BlockItem(REINFORCED_ANDESITE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_CALCITE_BLOCK_ITEM = ITEMS.register("reinforced_calcite", () ->
            new BlockItem(REINFORCED_CALCITE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_RED_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_red_bricks", () ->
            new BlockItem(REINFORCED_RED_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_PRISMARINE_BLOCK_ITEM = ITEMS.register("reinforced_prismarine", () ->
            new BlockItem(REINFORCED_PRISMARINE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_PRISMARINE_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_prismarine_bricks", () ->
            new BlockItem(REINFORCED_PRISMARINE_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_DARK_PRISMARINE_BLOCK_ITEM = ITEMS.register("reinforced_dark_prismarine", () ->
            new BlockItem(REINFORCED_DARK_PRISMARINE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_QUARTZ_BLOCK_ITEM = ITEMS.register("reinforced_quartz_block", () ->
            new BlockItem(REINFORCED_QUARTZ_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_CHISELED_QUARTZ_BLOCK_ITEM = ITEMS.register("reinforced_chiseled_quartz", () ->
            new BlockItem(REINFORCED_CHISELED_QUARTZ_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_QUARTZ_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_quartz_bricks", () ->
            new BlockItem(REINFORCED_QUARTZ_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_QUARTZ_PILLAR_BLOCK_ITEM = ITEMS.register("reinforced_quartz_pillar", () ->
            new BlockItem(REINFORCED_QUARTZ_PILLAR_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_AMETHYST_BLOCK_ITEM = ITEMS.register("reinforced_amethyst_block", () ->
            new BlockItem(REINFORCED_AMETHYST_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_PURPUR_BLOCK_ITEM = ITEMS.register("reinforced_purpur_block", () ->
            new BlockItem(REINFORCED_PURPUR_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_PURPUR_PILLAR_BLOCK_ITEM = ITEMS.register("reinforced_purpur_pillar", () ->
            new BlockItem(REINFORCED_PURPUR_PILLAR_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> COMPRESSED_PACKED_MUD_BLOCK_ITEM = ITEMS.register("compressed_packed_mud", () ->
            new BlockItem(COMPRESSED_PACKED_MUD_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_MUD_BRICKS_BLOCK_ITEM = ITEMS.register("reinforced_mud_bricks", () ->
            new BlockItem(REINFORCED_MUD_BRICKS_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> DISSOLVED_STONE_BLOCK_ITEM = ITEMS.register("dissolved_stone", () ->
            new TipsBlockItem(DISSOLVED_STONE_BLOCK.get(), new Item.Properties(), "block.materials.dissolved_stone.tooltip")
    );
    public static final DeferredItem<Item> ROCK_SUGAR_BLOCK_ITEM = ITEMS.register("rock_sugar", () ->
            new BlockItem(ROCK_SUGAR_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_ROCK_SUGAR_BLOCK_ITEM = ITEMS.register("reinforced_rock_sugar", () ->
            new BlockItem(REINFORCED_ROCK_SUGAR_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> IMITATION_BEDROCK_BLOCK_ITEM = ITEMS.register("imitation_bedrock", () ->
            new TipsBlockItem(IMITATION_BEDROCK_BLOCK.get(), new Item.Properties(), "block.materials.imitation_bedrock.tooltip")
    );
    public static final DeferredItem<Item> FIREFLY_BUSH_ITEM = FIREFLY_BUSH != null
            ? ITEMS.register("firefly_bush", () ->
            new TipsBlockItem(FIREFLY_BUSH.get(), new Item.Properties(), "block.materials.firefly_bush.tooltip"))
            : null;

    static void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        LOGGER.info("{}{}", Config.magicNumberIntroduction, Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

        // 运行时可改为如下检查：
        LOGGER.info("EXP_BLOCK in mineable/axe: {}", EXP_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_AXE));
        LOGGER.info("EXP_BLOCK in needs_iron_tool: {}", EXP_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_IRON_TOOL));
        LOGGER.info("FRAGILE_PLANK_BLOCK in mineable/axe: {}", FRAGILE_PLANK_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_AXE));
        LOGGER.info("REINFORCED_PLANK_BLOCK in mineable/axe: {}", REINFORCED_PLANK_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_AXE));
        LOGGER.info("REINFORCED_PLANK_BLOCK in needs_golden_tool: {}", REINFORCED_PLANK_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("REINFORCED_SMOOTH_STONE_BLOCK in mineable/pickaxe: {}", REINFORCED_SMOOTH_STONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_SMOOTH_STONE_BLOCK in needs_iron_tool: {}", REINFORCED_SMOOTH_STONE_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_IRON_TOOL));
        LOGGER.info("FRAGILE_SMOOTH_STONE_BLOCK in mineable/pickaxe: {}", FRAGILE_SMOOTH_STONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("BORDERLESS_GLASS_BLOCK in mineable/pickaxe: {}", BORDERLESS_GLASS_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("BORDERLESS_GLASS_BLOCK in needs_golden_tool: {}", BORDERLESS_GLASS_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("HIGH_STRENGTH_GLASS_BLOCK in mineable/pickaxe: {}", HIGH_STRENGTH_GLASS_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("HIGH_STRENGTH_GLASS_BLOCK in needs_netherite_tool: {}", HIGH_STRENGTH_GLASS_BLOCK.get().defaultBlockState().is(NEEDS_NETHERITE_TOOL));
        LOGGER.info("SIX_PHASE_ICE_BLOCK in mineable/pickaxe: {}", SIX_PHASE_ICE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK in mineable/pickaxe: {}", SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK in needs_netherite_tool: {}", SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK.get().defaultBlockState().is(NEEDS_NETHERITE_TOOL));
        LOGGER.info("FRAGILE_DEEPSLATE_BLOCK in mineable/pickaxe: {}", FRAGILE_DEEPSLATE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("FRAGILE_DEEPSLATE_BLOCK in needs_stone_tool: {}", FRAGILE_DEEPSLATE_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL));
        LOGGER.info("REINFORCED_SANDSTONE_BLOCK in mineable/pickaxe: {}", REINFORCED_SANDSTONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_SANDSTONE_BLOCK in needs_stone_tool: {}", REINFORCED_SANDSTONE_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL));
        LOGGER.info("REINFORCED_RED_SANDSTONE_BLOCK in mineable/pickaxe: {}", REINFORCED_RED_SANDSTONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_RED_SANDSTONE_BLOCK in needs_stone_tool: {}", REINFORCED_RED_SANDSTONE_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL));
        LOGGER.info("REINFORCED_TUFF_BLOCK in mineable/pickaxe: {}", REINFORCED_TUFF_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_TUFF_BLOCK in needs_golden_tool: {}", REINFORCED_TUFF_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("REINFORCED_NETHERRACK_BLOCK in mineable/pickaxe: {}", REINFORCED_NETHERRACK_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_NETHERRACK_BLOCK in needs_golden_tool: {}", REINFORCED_NETHERRACK_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("REINFORCED_BASALT_BLOCK in mineable/pickaxe: {}", REINFORCED_BASALT_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_BASALT_BLOCK in needs_golden_tool: {}", REINFORCED_BASALT_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("REINFORCED_BLACKSTONE_BLOCK in mineable/pickaxe: {}", REINFORCED_BLACKSTONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_BLACKSTONE_BLOCK in needs_golden_tool: {}", REINFORCED_BLACKSTONE_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("FRAGILE_END_STONE_BLOCK in mineable/pickaxe: {}", FRAGILE_END_STONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_END_STONE_BLOCK in mineable/pickaxe: {}", REINFORCED_END_STONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_END_STONE_BLOCK in needs_diamond_tool: {}", REINFORCED_END_STONE_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_DIAMOND_TOOL));
        LOGGER.info("SYNTHETIC_OBSIDIAN_BLOCK in mineable/pickaxe: {}", SYNTHETIC_OBSIDIAN_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("SYNTHETIC_OBSIDIAN_BLOCK in needs_netherite_tool: {}", SYNTHETIC_OBSIDIAN_BLOCK.get().defaultBlockState().is(NEEDS_NETHERITE_TOOL));
        LOGGER.info("REINFORCED_GRANITE_BLOCK in mineable/pickaxe: {}", REINFORCED_GRANITE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_GRANITE_BLOCK in needs_golden_tool: {}", REINFORCED_GRANITE_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("REINFORCED_DIORITE_BLOCK in mineable/pickaxe: {}", REINFORCED_DIORITE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_DIORITE_BLOCK in needs_golden_tool: {}", REINFORCED_DIORITE_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("REINFORCED_ANDESITE_BLOCK in mineable/pickaxe: {}", REINFORCED_ANDESITE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_ANDESITE_BLOCK in needs_golden_tool: {}", REINFORCED_ANDESITE_BLOCK.get().defaultBlockState().is(NEEDS_GOLDEN_TOOL));
        LOGGER.info("DISSOLVED_STONE_BLOCK in mineable/pickaxe: {}", DISSOLVED_STONE_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("ROCK_SUGAR_BLOCK in mineable/hoe: {}", ROCK_SUGAR_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_ROCK_SUGAR_BLOCK in mineable/hoe: {}", REINFORCED_ROCK_SUGAR_BLOCK.get().defaultBlockState().is(BlockTags.MINEABLE_WITH_PICKAXE));
        LOGGER.info("REINFORCED_ROCK_SUGAR_BLOCK in needs_stone_tool: {}", REINFORCED_ROCK_SUGAR_BLOCK.get().defaultBlockState().is(BlockTags.NEEDS_STONE_TOOL));
        if (FIREFLY_BUSH != null)
        {
            LOGGER.info("FIREFLY_BUSH in mineable/shears: {}", FIREFLY_BUSH.get().defaultBlockState().is(MINEABLE_WITH_SHEARS));
        }
    }

    private static boolean shouldRegisterFireflyBush()
    {
        // 检测 Minecraft 版本，1.21.5+ 不注册自定义萤火虫灌木
        String mcVersion = FMLLoader.versionInfo().mcVersion();
        String[] versionParts = mcVersion.split("\\.");

        if (versionParts.length >= 3)
        {
            int major = Integer.parseInt(versionParts[0]);
            int minor = Integer.parseInt(versionParts[1]);
            int patch = Integer.parseInt(versionParts[2]);

            // 1.21.5 及以后版本返回 false
            if (major > 1 || (major == 1 && minor > 21) ||
                    (major == 1 && minor == 21 && patch >= 5))
            {
                return false;
            }
        }
        return true;
    }
}

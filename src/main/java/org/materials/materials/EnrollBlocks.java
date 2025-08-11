package org.materials.materials;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import static org.materials.materials.Materials.*;

public class EnrollBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // 便于在事件中比较的创造标签 Key
    public static final ResourceKey<CreativeModeTab> MATERIALS_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MODID, "example_tab"));

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
    public static final DeferredBlock<Block> FRAGILE_PLANK_BLOCK = BLOCKS.register("fragile_plank", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(0.125f, 0.1f)
                    .sound(SoundType.WOOD)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_PLANK_BLOCK = BLOCKS.register("reinforced_plank", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(4.0f, 10.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> FRAGILE_SMOOTH_STONE_BLOCK = BLOCKS.register("fragile_smooth_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.5f, 6.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_SMOOTH_STONE_BLOCK = BLOCKS.register("reinforced_smooth_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(10.0f, 60.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> BORDERLESS_GLASS_BLOCK = BLOCKS.register("borderless_glass", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE)
                    .strength(1.125f, 100.0f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            )
            {
                @Override
                public int getLightBlock(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos)
                {
                    return 0;
                }
                @Override
                public float getShadeBrightness(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos)
                {
                    return 1.0F;
                }
            }
    );
    public static final DeferredBlock<Block> HIGH_STRENGTH_GLASS_BLOCK = BLOCKS.register("high_strength_glass", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NONE)
                    .strength(12.0f, 1000.0f)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
                    .isSuffocating((state, level, pos) -> false)
                    .isViewBlocking((state, level, pos) -> false)
            )
            {
                @Override
                public int getLightBlock(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos)
                {
                    return 0;
                }
                @Override
                public float getShadeBrightness(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter level, net.minecraft.core.BlockPos pos)
                {
                    return 1.0F;
                }
            }
    );
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
    public static final DeferredBlock<Block> SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK = BLOCKS.register("synthetic_reinforced_deepslate", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(50.0f, 1200.0f)
                    .sound(SoundType.DEEPSLATE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> FRAGILE_DEEPSLATE_BLOCK = BLOCKS.register("fragile_deepslate", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(1.6f, 6.0f)
                    .sound(SoundType.DEEPSLATE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_SANDSTONE_BLOCK = BLOCKS.register("reinforced_sandstone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .strength(3.0f, 12.0f)
                    .sound(SoundType.SAND)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_RED_SANDSTONE_BLOCK = BLOCKS.register("reinforced_red_sandstone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(3.0f, 12.0f)
                    .sound(SoundType.SAND)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_TUFF_BLOCK = BLOCKS.register("reinforced_tuff", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6.0f, 20.0f)
                    .sound(SoundType.TUFF)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_NETHERRACK_BLOCK = BLOCKS.register("reinforced_netherrack", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.NETHER)
                    .strength(4.0f, 12.0f)
                    .sound(SoundType.NETHERRACK)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_BASALT_BLOCK = BLOCKS.register("reinforced_basalt", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(5.0f, 20.0f)
                    .sound(SoundType.BASALT)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_BLACKSTONE_BLOCK = BLOCKS.register("reinforced_blackstone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6.0f, 20.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> FRAGILE_END_STONE_BLOCK = BLOCKS.register("fragile_end_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .strength(0.5f, 2.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_END_STONE_BLOCK = BLOCKS.register("reinforced_end_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND)
                    .strength(45.0f, 64.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> SYNTHETIC_OBSIDIAN_BLOCK = BLOCKS.register("synthetic_obsidian", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(64.0f, 1200.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_GRANITE_BLOCK = BLOCKS.register("reinforced_granite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6.0f, 20.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_DIORITE_BLOCK = BLOCKS.register("reinforced_diorite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6.0f, 20.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> REINFORCED_ANDESITE_BLOCK = BLOCKS.register("reinforced_andesite", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6.0f, 20.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );
    public static final DeferredBlock<Block> DISSOLVED_STONE_BLOCK = BLOCKS.register("dissolved_stone", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(0.5f, 2.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
            {
                @Override
                public void stepOn(net.minecraft.world.level.Level level, net.minecraft.core.BlockPos pos, net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.entity.Entity entity)
                {
                    super.stepOn(level, pos, state, entity);
                    if (!level.isClientSide && entity instanceof net.minecraft.world.entity.LivingEntity)
                    {
                        level.destroyBlock(pos, true, entity);
                    }
                }
            }
    );
    public static final DeferredBlock<Block> IMITATION_BEDROCK_BLOCK = BLOCKS.register("imitation_bedrock", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(65536.0f, 8.0f)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
            )
    );

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
    public static final DeferredItem<Item> REINFORCED_BASALT_BLOCK_ITEM = ITEMS.register("reinforced_basalt", () ->
            new BlockItem(REINFORCED_BASALT_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_BLACKSTONE_BLOCK_ITEM = ITEMS.register("reinforced_blackstone", () ->
            new BlockItem(REINFORCED_BLACKSTONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> FRAGILE_END_STONE_BLOCK_ITEM = ITEMS.register("fragile_end_stone", () ->
            new BlockItem(FRAGILE_END_STONE_BLOCK.get(), new Item.Properties())
    );
    public static final DeferredItem<Item> REINFORCED_END_STONE_BLOCK_ITEM = ITEMS.register("reinforced_end_stone", () ->
            new BlockItem(REINFORCED_END_STONE_BLOCK.get(), new Item.Properties())
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
    public static final DeferredItem<Item> DISSOLVED_STONE_BLOCK_ITEM = ITEMS.register("dissolved_stone", () ->
            new TipsBlockItem(DISSOLVED_STONE_BLOCK.get(), new Item.Properties(), "block.materials.dissolved_stone.tooltip")
    );
    public static final DeferredItem<Item> IMITATION_BEDROCK_BLOCK_ITEM = ITEMS.register("imitation_bedrock", () ->
            new TipsBlockItem(IMITATION_BEDROCK_BLOCK.get(), new Item.Properties(), "block.materials.imitation_bedrock.tooltip")
    );

//    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () ->
//            new Item(new Item.Properties().food(new FoodProperties.Builder()
//                    .alwaysEat().nutrition(1).saturationMod(2f).build()))
//    );

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIALS_TAB = CREATIVE_MODE_TABS.register("example_tab", () ->
            CreativeModeTab.builder().title(Component.translatable("creative_tab.materials.example_tab"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> REINFORCED_SMOOTH_STONE_BLOCK_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) ->
                    {
//                        output.accept(EXAMPLE_ITEM.get());
                        output.accept(EXP_BLOCK_ITEM.get());
                        output.accept(FRAGILE_PLANK_BLOCK_ITEM.get());
                        output.accept(REINFORCED_PLANK_BLOCK_ITEM.get());
                        output.accept(REINFORCED_SMOOTH_STONE_BLOCK_ITEM.get());
                        output.accept(FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get());
                        output.accept(BORDERLESS_GLASS_BLOCK_ITEM.get());
                        output.accept(HIGH_STRENGTH_GLASS_BLOCK_ITEM.get());
                        output.accept(SIX_PHASE_ICE_BLOCK_ITEM.get());
                        output.accept(SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK_ITEM.get());
                        output.accept(FRAGILE_DEEPSLATE_BLOCK_ITEM.get());
                        output.accept(REINFORCED_SANDSTONE_BLOCK_ITEM.get());
                        output.accept(REINFORCED_RED_SANDSTONE_BLOCK_ITEM.get());
                        output.accept(REINFORCED_TUFF_BLOCK_ITEM.get());
                        output.accept(REINFORCED_NETHERRACK_BLOCK_ITEM.get());
                        output.accept(REINFORCED_BASALT_BLOCK_ITEM.get());
                        output.accept(REINFORCED_BLACKSTONE_BLOCK_ITEM.get());
                        output.accept(FRAGILE_END_STONE_BLOCK_ITEM.get());
                        output.accept(REINFORCED_END_STONE_BLOCK_ITEM.get());
                        output.accept(SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get());
                        output.accept(REINFORCED_GRANITE_BLOCK_ITEM.get());
                        output.accept(REINFORCED_DIORITE_BLOCK_ITEM.get());
                        output.accept(REINFORCED_ANDESITE_BLOCK_ITEM.get());
                        output.accept(DISSOLVED_STONE_BLOCK_ITEM.get());
                        output.accept(IMITATION_BEDROCK_BLOCK_ITEM.get());
                    })
                    .build());

    static void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // 在 BuildCreativeModeTabContentsEvent 事件中添加物品到对应标签
    static void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey().equals(MATERIALS_TAB_KEY))
        {
//            event.accept(EXAMPLE_ITEM.get());
            event.accept(EXP_BLOCK_ITEM.get());
            event.accept(FRAGILE_PLANK_BLOCK_ITEM.get());
            event.accept(REINFORCED_PLANK_BLOCK_ITEM.get());
            event.accept(REINFORCED_SMOOTH_STONE_BLOCK_ITEM.get());
            event.accept(FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get());
            event.accept(BORDERLESS_GLASS_BLOCK_ITEM.get());
            event.accept(HIGH_STRENGTH_GLASS_BLOCK_ITEM.get());
            event.accept(SIX_PHASE_ICE_BLOCK_ITEM.get());
            event.accept(SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK_ITEM.get());
            event.accept(FRAGILE_DEEPSLATE_BLOCK_ITEM.get());
            event.accept(REINFORCED_SANDSTONE_BLOCK_ITEM.get());
            event.accept(REINFORCED_RED_SANDSTONE_BLOCK_ITEM.get());
            event.accept(REINFORCED_TUFF_BLOCK_ITEM.get());
            event.accept(REINFORCED_NETHERRACK_BLOCK_ITEM.get());
            event.accept(REINFORCED_BASALT_BLOCK_ITEM.get());
            event.accept(REINFORCED_BLACKSTONE_BLOCK_ITEM.get());
            event.accept(FRAGILE_END_STONE_BLOCK_ITEM.get());
            event.accept(REINFORCED_END_STONE_BLOCK_ITEM.get());
            event.accept(SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get());
            event.accept(REINFORCED_GRANITE_BLOCK_ITEM.get());
            event.accept(REINFORCED_DIORITE_BLOCK_ITEM.get());
            event.accept(REINFORCED_ANDESITE_BLOCK_ITEM.get());
            event.accept(DISSOLVED_STONE_BLOCK_ITEM.get());
            event.accept(IMITATION_BEDROCK_BLOCK_ITEM.get());
        }
    }
}

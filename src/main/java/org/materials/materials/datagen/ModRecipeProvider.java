package org.materials.materials.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.materials.materials.EnrollBlocks;
import org.materials.materials.EnrollItems;
import org.materials.materials.Materials;
import javax.annotation.Nonnull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public Ingredient CONCRETE;

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries)
    {
        super(pOutput, pRegistries);
        this.CONCRETE = Ingredient.of(
                Items.WHITE_CONCRETE, Items.ORANGE_CONCRETE, Items.MAGENTA_CONCRETE, Items.LIGHT_BLUE_CONCRETE,
                Items.YELLOW_CONCRETE, Items.LIME_CONCRETE, Items.PINK_CONCRETE, Items.GRAY_CONCRETE,
                Items.LIGHT_GRAY_CONCRETE, Items.CYAN_CONCRETE, Items.PURPLE_CONCRETE, Items.BLUE_CONCRETE,
                Items.BROWN_CONCRETE, Items.GREEN_CONCRETE, Items.RED_CONCRETE, Items.BLACK_CONCRETE);
    }

    @Override
    protected void buildRecipes(@Nonnull RecipeOutput output)
    {
        net.minecraft.world.level.ItemLike fireflyBush;
        if (EnrollBlocks.FIREFLY_BUSH != null)
            fireflyBush = EnrollBlocks.FIREFLY_BUSH_ITEM.get();
        else
            fireflyBush = null;

        if (fireflyBush != null)
        {
            // 无序合成配方 - 荧石粉 (用萤火虫灌木丛制作)
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GLOWSTONE_DUST, 4)
                    .requires(fireflyBush, 2)
                    .unlockedBy(getHasName(fireflyBush), has(fireflyBush))
                    .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "firefly_bush_to_glowstone_dust"));
        }

        // 合成配方 - 体质强化饮料
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EnrollItems.PHYSIQUE_STRENGTHENING_BEVERAGE_ITEM.get(), 1)
                .requires(Items.SUGAR, 3)
                .requires(Items.LAPIS_LAZULI, 3)
                .requires(Items.NETHER_WART, 2) // 需要下界疣
                .requires(Items.POTION, 1)  // 额外需要药水作为容器
                .unlockedBy("has_nether_wart", has(Items.NETHER_WART))  // 用下界疣解锁
                .save(output, Materials.MODID + ":physique_strengthening_beverage_recipe");

        // 合成配方 - 强制镇静药水 (任意药水*1 + 蜘蛛眼*4 + 糖*4)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, EnrollItems.FORCED_SEDATION_POTION.get(), 1)
                .requires(Items.SPIDER_EYE, 4)
                .requires(Items.SUGAR, 4)
                .requires(Items.POTION, 1)  // 额外需要药水作为容器
                .unlockedBy("has_spider_eye", has(Items.SPIDER_EYE))  // 用蜘蛛眼解锁
                .save(output, Materials.MODID + ":forced_sedative_potion_recipe");

        // 有序合成配方 - 脆弱木板 (用普通木板制作)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get(), 4)
                .pattern("W ")
                .pattern(" W")
                .define('W', ItemTags.PLANKS)  // 任意木板
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(ItemTags.PLANKS))
                .save(output, Materials.MODID + ":fragile_plank_block");
        // 脆弱木板可以分解成木棍
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STICK, 1)
                .requires(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get())
                .unlockedBy(getHasName(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get()), has(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get()))
                .save(output, Materials.MODID + ":fragile_plank_to_sticks");
        // 强化木板配方 - 用大量木头制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_PLANK_BLOCK_ITEM.get(), 1)
                .pattern("LLL")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.OAK_LOG), has(ItemTags.LOGS))
                .save(output, Materials.MODID + ":logs_to_reinforced_plank_block");

        // 强化平滑石头 - 用铁锭和混凝土强化
        var reinforcedSmoothStoneBuilder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_SMOOTH_STONE_BLOCK_ITEM.get(), 1)
                .pattern("SSS")
                .pattern("ICI")
                .pattern("SSS")
                .define('S', Items.SMOOTH_STONE)
                .define('I', Items.IRON_INGOT)  // 用铁锭强化
                .define('C', this.CONCRETE);
        // 添加多个解锁条件 - 任意一种混凝土都可以解锁配方
        reinforcedSmoothStoneBuilder.unlockedBy("has_white_concrete", has(Items.WHITE_CONCRETE))
                .unlockedBy("has_orange_concrete", has(Items.ORANGE_CONCRETE))
                .unlockedBy("has_magenta_concrete", has(Items.MAGENTA_CONCRETE))
                .unlockedBy("has_light_blue_concrete", has(Items.LIGHT_BLUE_CONCRETE))
                .unlockedBy("has_yellow_concrete", has(Items.YELLOW_CONCRETE))
                .unlockedBy("has_lime_concrete", has(Items.LIME_CONCRETE))
                .unlockedBy("has_pink_concrete", has(Items.PINK_CONCRETE))
                .unlockedBy("has_gray_concrete", has(Items.GRAY_CONCRETE))
                .unlockedBy("has_light_gray_concrete", has(Items.LIGHT_GRAY_CONCRETE))
                .unlockedBy("has_cyan_concrete", has(Items.CYAN_CONCRETE))
                .unlockedBy("has_purple_concrete", has(Items.PURPLE_CONCRETE))
                .unlockedBy("has_blue_concrete", has(Items.BLUE_CONCRETE))
                .unlockedBy("has_brown_concrete", has(Items.BROWN_CONCRETE))
                .unlockedBy("has_green_concrete", has(Items.GREEN_CONCRETE))
                .unlockedBy("has_red_concrete", has(Items.RED_CONCRETE))
                .unlockedBy("has_black_concrete", has(Items.BLACK_CONCRETE))
                .save(output, Materials.MODID + ":reinforced_smooth_stone_block");
        // 脆弱平滑石头 - 用燧石压制
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get(), 2)
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.FLINT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                .save(output);
        // 脆弱平滑石头可以分解成燧石
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.FLINT, 2)
                .requires(EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get())
                .unlockedBy(getHasName(EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get()), has(EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get()))
                .save(output, Materials.MODID + ":fragile_smooth_stone_to_flint");
        // 强化石砖 - 用铁锭和混凝土强化
        var reinforcedStoneBricksBuilder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_STONE_BRICKS_BLOCK_ITEM.get(), 1)
                .pattern("SSS")
                .pattern("ICI")
                .pattern("SSS")
                .define('S', Items.STONE_BRICKS)
                .define('I', Items.IRON_INGOT)  // 用铁锭强化
                .define('C', this.CONCRETE);
        // 添加多个解锁条件 - 任意一种混凝土都可以解锁配方
        reinforcedStoneBricksBuilder.unlockedBy("has_white_concrete", has(Items.WHITE_CONCRETE))
                .unlockedBy("has_orange_concrete", has(Items.ORANGE_CONCRETE))
                .unlockedBy("has_magenta_concrete", has(Items.MAGENTA_CONCRETE))
                .unlockedBy("has_light_blue_concrete", has(Items.LIGHT_BLUE_CONCRETE))
                .unlockedBy("has_yellow_concrete", has(Items.YELLOW_CONCRETE))
                .unlockedBy("has_lime_concrete", has(Items.LIME_CONCRETE))
                .unlockedBy("has_pink_concrete", has(Items.PINK_CONCRETE))
                .unlockedBy("has_gray_concrete", has(Items.GRAY_CONCRETE))
                .unlockedBy("has_light_gray_concrete", has(Items.LIGHT_GRAY_CONCRETE))
                .unlockedBy("has_cyan_concrete", has(Items.CYAN_CONCRETE))
                .unlockedBy("has_purple_concrete", has(Items.PURPLE_CONCRETE))
                .unlockedBy("has_blue_concrete", has(Items.BLUE_CONCRETE))
                .unlockedBy("has_brown_concrete", has(Items.BROWN_CONCRETE))
                .unlockedBy("has_green_concrete", has(Items.GREEN_CONCRETE))
                .unlockedBy("has_red_concrete", has(Items.RED_CONCRETE))
                .unlockedBy("has_black_concrete", has(Items.BLACK_CONCRETE))
                .save(output, Materials.MODID + ":reinforced_stone_bricks_block");
        // 强化雕刻石砖 - 用铁锭和混凝土强化
        var reinforcedChiseledStoneBricksBuilder = ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK_ITEM.get(), 1)
                .pattern("SSS")
                .pattern("ICI")
                .pattern("SSS")
                .define('S', Items.CHISELED_STONE_BRICKS)
                .define('I', Items.IRON_INGOT)  // 用铁锭强化
                .define('C', this.CONCRETE);
        // 添加多个解锁条件 - 任意一种混凝土都可以解锁配方
        reinforcedChiseledStoneBricksBuilder.unlockedBy("has_white_concrete", has(Items.WHITE_CONCRETE))
                .unlockedBy("has_orange_concrete", has(Items.ORANGE_CONCRETE))
                .unlockedBy("has_magenta_concrete", has(Items.MAGENTA_CONCRETE))
                .unlockedBy("has_light_blue_concrete", has(Items.LIGHT_BLUE_CONCRETE))
                .unlockedBy("has_yellow_concrete", has(Items.YELLOW_CONCRETE))
                .unlockedBy("has_lime_concrete", has(Items.LIME_CONCRETE))
                .unlockedBy("has_pink_concrete", has(Items.PINK_CONCRETE))
                .unlockedBy("has_gray_concrete", has(Items.GRAY_CONCRETE))
                .unlockedBy("has_light_gray_concrete", has(Items.LIGHT_GRAY_CONCRETE))
                .unlockedBy("has_cyan_concrete", has(Items.CYAN_CONCRETE))
                .unlockedBy("has_purple_concrete", has(Items.PURPLE_CONCRETE))
                .unlockedBy("has_blue_concrete", has(Items.BLUE_CONCRETE))
                .unlockedBy("has_brown_concrete", has(Items.BROWN_CONCRETE))
                .unlockedBy("has_green_concrete", has(Items.GREEN_CONCRETE))
                .unlockedBy("has_red_concrete", has(Items.RED_CONCRETE))
                .unlockedBy("has_black_concrete", has(Items.BLACK_CONCRETE))
                .unlockedBy(getHasName(EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK_ITEM.get()), has(EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK_ITEM.get()))
                .save(output, Materials.MODID + ":reinforced_chiseled_stone_bricks_block");

        // 熔炉配方 - 无边玻璃
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.GLASS),
                        RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get(),
                        2.0f, 400)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(output, "borderless_glass_from_smelting");
        // 高炉配方 - 无边玻璃
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.GLASS),
                        RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get(),
                        2.0f, 200)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(output, "borderless_glass_from_blasting");

        // 高强度玻璃 - 用无边玻璃和钻石制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK_ITEM.get(), 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("GGG")
                .define('G', EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get())
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get()), has(EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get()))
                .save(output, Materials.MODID + ":high_strength_glass_block");

        // 合成强化深板岩 - 用深板岩和下界合金块制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK_ITEM.get(), 1)
                .pattern("DDD")
                .pattern("DID")
                .pattern("DDD")
                .define('D', Items.DEEPSLATE)
                .define('I', Items.NETHERITE_BLOCK)
                .unlockedBy(getHasName(Items.DEEPSLATE), has(Items.DEEPSLATE))
                .save(output, Materials.MODID + ":synthetic_reinforced_deepslate_block");
        // 脆弱深板岩 - 用深板岩圆石制作
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_DEEPSLATE_BLOCK_ITEM.get(), 2)
                .requires(Items.COBBLED_DEEPSLATE, 1)
                .unlockedBy(getHasName(Items.COBBLED_DEEPSLATE), has(Items.COBBLED_DEEPSLATE))
                .save(output, Materials.MODID + ":fragile_deepslate_block");

        // 强化砂岩 - 用砂岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_SANDSTONE_BLOCK_ITEM.get(), 2)
                .pattern("SSS")
                .pattern("SIS")
                .pattern("SSS")
                .define('S', Items.SANDSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.SANDSTONE), has(Items.SANDSTONE))
                .save(output, Materials.MODID + ":reinforced_sandstone_block");

        // 强化红砂岩 - 用红砂岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_RED_SANDSTONE_BLOCK_ITEM.get(), 2)
                .pattern("RRR")
                .pattern("RIR")
                .pattern("RRR")
                .define('R', Items.RED_SANDSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.RED_SANDSTONE), has(Items.RED_SANDSTONE))
                .save(output, Materials.MODID + ":reinforced_red_sandstone_block");

        // 强化凝灰岩 - 用凝灰岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_TUFF_BLOCK_ITEM.get(), 2)
                .pattern("TTT")
                .pattern("TIT")
                .pattern("TTT")
                .define('T', Items.TUFF)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.TUFF), has(Items.TUFF))
                .save(output, Materials.MODID + ":reinforced_tuff_block");

        // 强化下界岩 - 用下界岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_NETHERRACK_BLOCK_ITEM.get(), 2)
                .pattern("NNN")
                .pattern("NIN")
                .pattern("NNN")
                .define('N', Items.NETHERRACK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(output, Materials.MODID + ":reinforced_netherrack_block");

        // 强化下界砖块 - 用下界砖块和金锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_NETHER_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("NNN")
                .pattern("NGN")
                .pattern("NNN")
                .define('N', Items.NETHER_BRICKS)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.NETHER_BRICKS), has(Items.NETHER_BRICKS))
                .save(output, Materials.MODID + ":reinforced_nether_bricks_block");

        // 强化雕刻下界砖块 - 用雕刻下界砖块和金锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_CHISELED_NETHER_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("NNN")
                .pattern("NGN")
                .pattern("NNN")
                .define('N', Items.CHISELED_NETHER_BRICKS)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.CHISELED_NETHER_BRICKS), has(Items.CHISELED_NETHER_BRICKS))
                .save(output, Materials.MODID + ":reinforced_chiseled_nether_bricks_block");

        // 强化红色下界砖块 - 用红色下界砖块和金锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_RED_NETHER_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("NNN")
                .pattern("NGN")
                .pattern("NNN")
                .define('N', Items.RED_NETHER_BRICKS)
                .define('G', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.RED_NETHER_BRICKS), has(Items.RED_NETHER_BRICKS))
                .save(output, Materials.MODID + ":reinforced_red_nether_bricks_block");

        // 强化玄武岩 - 用玄武岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_BASALT_BLOCK_ITEM.get(), 2)
                .pattern("BBB")
                .pattern("BIB")
                .pattern("BBB")
                .define('B', Items.BASALT)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.BASALT), has(Items.BASALT))
                .save(output, Materials.MODID + ":reinforced_basalt_block");

        // 强化平滑玄武岩 - 用平滑玄武岩和下界合金锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_SMOOTH_BASALT_BLOCK_ITEM.get(), 2)
                .pattern("BBB")
                .pattern("BNB")
                .pattern("BBB")
                .define('B', Items.SMOOTH_BASALT)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.SMOOTH_BASALT), has(Items.SMOOTH_BASALT))
                .save(output, Materials.MODID + ":reinforced_smooth_basalt_block");

        // 强化黑石 - 用黑石和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_BLACKSTONE_BLOCK_ITEM.get(), 2)
                .pattern("BBB")
                .pattern("BIB")
                .pattern("BBB")
                .define('B', Items.BLACKSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.BLACKSTONE), has(Items.BLACKSTONE))
                .save(output, Materials.MODID + ":reinforced_blackstone_block");
        // 强化抛光黑石砖 - 用抛光黑石砖和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("BBB")
                .pattern("BIB")
                .pattern("BBB")
                .define('B', Items.POLISHED_BLACKSTONE_BRICKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.POLISHED_BLACKSTONE_BRICKS), has(Items.POLISHED_BLACKSTONE_BRICKS))
                .save(output, Materials.MODID + ":reinforced_polished_blackstone_bricks_block");

        // 脆弱末地石 - 用末地石制作
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get(), 2)
                .requires(Items.END_STONE, 1)
                .unlockedBy(getHasName(Items.END_STONE), has(Items.END_STONE))
                .save(output, Materials.MODID + ":fragile_end_stone_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.END_STONE, 1)
                .requires(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get(), 2)
                .unlockedBy(getHasName(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get()), has(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get()))
                .save(output, Materials.MODID + ":fragile_end_stone_to_end_stone");
        // 强化末地石 - 用末地石和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_END_STONE_BLOCK_ITEM.get(), 2)
                .pattern("EEE")
                .pattern("EIE")
                .pattern("EEE")
                .define('E', Items.END_STONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.END_STONE), has(Items.END_STONE))
                .save(output, Materials.MODID + ":reinforced_end_stone_block");
        // 强化末地石砖 - 用末地石砖和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_END_STONE_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("EEE")
                .pattern("EIE")
                .pattern("EEE")
                .define('E', Items.END_STONE_BRICKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.END_STONE_BRICKS), has(Items.END_STONE_BRICKS))
                .save(output, Materials.MODID + ":reinforced_end_stone_bricks_block");

        // 合成黑曜石 - 用钻石块和黑曜石制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get(), 1)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .define('O', Items.OBSIDIAN)
                .define('I', Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(output, Materials.MODID + ":synthetic_obsidian_block");

        // 强化花岗岩 - 用花岗岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_GRANITE_BLOCK_ITEM.get(), 2)
                .pattern("GGG")
                .pattern("GIG")
                .pattern("GGG")
                .define('G', Items.GRANITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.GRANITE), has(Items.GRANITE))
                .save(output, Materials.MODID + ":reinforced_granite_block");

        // 强化闪长岩 - 用闪长岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_DIORITE_BLOCK_ITEM.get(), 2)
                .pattern("DDD")
                .pattern("DID")
                .pattern("DDD")
                .define('D', Items.DIORITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.DIORITE), has(Items.DIORITE))
                .save(output, Materials.MODID + ":reinforced_diorite_block");

        // 强化安山岩 - 用安山岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_ANDESITE_BLOCK_ITEM.get(), 2)
                .pattern("AAA")
                .pattern("AIA")
                .pattern("AAA")
                .define('A', Items.ANDESITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.ANDESITE), has(Items.ANDESITE))
                .save(output, Materials.MODID + ":reinforced_andesite_block");

        // 强化方解石 - 用方解石和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_CALCITE_BLOCK_ITEM.get(), 2)
                .pattern("CCC")
                .pattern("CIC")
                .pattern("CCC")
                .define('C', Items.CALCITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.CALCITE), has(Items.CALCITE))
                .save(output, Materials.MODID + ":reinforced_calcite_block");

        // 强化红砖块 - 用红砖块和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_RED_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("BBB")
                .pattern("BIB")
                .pattern("BBB")
                .define('B', Items.BRICKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.BRICKS), has(Items.BRICKS))
                .save(output, Materials.MODID + ":reinforced_red_bricks_block");

        // 强化海晶石 - 用海晶石和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_PRISMARINE_BLOCK_ITEM.get(), 2)
                .pattern("PPP")
                .pattern("PIP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.PRISMARINE), has(Items.PRISMARINE))
                .save(output, Materials.MODID + ":reinforced_prismarine_block");

        // 强化海晶石砖 - 用海晶石砖和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_PRISMARINE_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("PPP")
                .pattern("PIP")
                .pattern("PPP")
                .define('P', Items.PRISMARINE_BRICKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.PRISMARINE_BRICKS), has(Items.PRISMARINE_BRICKS))
                .save(output, Materials.MODID + ":reinforced_prismarine_bricks_block");

        // 强化暗海晶石 - 用暗海晶石和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_DARK_PRISMARINE_BLOCK_ITEM.get(), 2)
                .pattern("PPP")
                .pattern("PIP")
                .pattern("PPP")
                .define('P', Items.DARK_PRISMARINE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.DARK_PRISMARINE), has(Items.DARK_PRISMARINE))
                .save(output, Materials.MODID + ":reinforced_dark_prismarine_block");

        // 强化石英块 - 用石英块和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_QUARTZ_BLOCK_ITEM.get(), 2)
                .pattern("QQQ")
                .pattern("QIQ")
                .pattern("QQQ")
                .define('Q', Items.QUARTZ_BLOCK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.QUARTZ_BLOCK), has(Items.QUARTZ_BLOCK))
                .save(output, Materials.MODID + ":reinforced_quartz_block_recipe");

        // 强化雕刻石英块 - 用雕刻石英块和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_CHISELED_QUARTZ_BLOCK_ITEM.get(), 2)
                .pattern("QQQ")
                .pattern("QIQ")
                .pattern("QQQ")
                .define('Q', Items.CHISELED_QUARTZ_BLOCK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.CHISELED_QUARTZ_BLOCK), has(Items.CHISELED_QUARTZ_BLOCK))
                .save(output, Materials.MODID + ":reinforced_chiseled_quartz_block");

        // 强化石英砖块 - 用石英砖块和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_QUARTZ_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("QQQ")
                .pattern("QIQ")
                .pattern("QQQ")
                .define('Q', Items.QUARTZ_BRICKS)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.QUARTZ_BRICKS), has(Items.QUARTZ_BRICKS))
                .save(output, Materials.MODID + ":reinforced_quartz_bricks_block");

        // 强化石英柱 - 用石英柱和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_QUARTZ_PILLAR_BLOCK_ITEM.get(), 2)
                .pattern("QQQ")
                .pattern("QIQ")
                .pattern("QQQ")
                .define('Q', Items.QUARTZ_PILLAR)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.QUARTZ_PILLAR), has(Items.QUARTZ_PILLAR))
                .save(output, Materials.MODID + ":reinforced_quartz_pillar_block");

        // 强化紫水晶块 - 用紫水晶块和钻石制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_AMETHYST_BLOCK_ITEM.get(), 2)
                .pattern("AAA")
                .pattern("ADA")
                .pattern("AAA")
                .define('A', Items.AMETHYST_BLOCK)
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.AMETHYST_BLOCK), has(Items.AMETHYST_BLOCK))
                .save(output, Materials.MODID + ":reinforced_amethyst_block_recipe");

        // 强化紫珀块 - 用紫珀块和下界合金锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_PURPUR_BLOCK_ITEM.get(), 2)
                .pattern("PPP")
                .pattern("PNP")
                .pattern("PPP")
                .define('P', Items.PURPUR_BLOCK)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.PURPUR_BLOCK), has(Items.PURPUR_BLOCK))
                .save(output, Materials.MODID + ":reinforced_purpur_block_recipe");
        // 强化紫珀柱 - 用紫珀柱和下界合金锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_PURPUR_PILLAR_BLOCK_ITEM.get(), 2)
                .pattern("PPP")
                .pattern("PNP")
                .pattern("PPP")
                .define('P', Items.PURPUR_PILLAR)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.PURPUR_PILLAR), has(Items.PURPUR_PILLAR))
                .save(output, Materials.MODID + ":reinforced_purpur_pillar_block_recipe");

        // 压缩泥坯 - 用泥坯或泥土压制
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK_ITEM.get(), 1)
                .requires(Items.PACKED_MUD, 4)
                .unlockedBy(getHasName(Items.PACKED_MUD), has(Items.PACKED_MUD))
                .save(output, Materials.MODID + ":compressed_packed_mud_from_packed");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK_ITEM.get(), 2)
                .requires(Items.MUD, 8)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(output, Materials.MODID + ":compressed_packed_mud_from_mud");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK_ITEM.get(), 2)
                .requires(Items.DIRT, 8)
                .unlockedBy(getHasName(Items.DIRT), has(Items.DIRT))
                .save(output, Materials.MODID + ":compressed_packed_mud_from_dirt");

        // 强化泥砖 - 用泥砖、铜锭和铁锭制作 或者 用泥巴、铜锭和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_MUD_BRICKS_BLOCK_ITEM.get(), 2)
                .pattern("MMM")
                .pattern("CIC")
                .pattern("MMM")
                .define('M', Items.MUD_BRICKS)
                .define('C', Items.COPPER_INGOT)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.MUD_BRICKS), has(Items.MUD_BRICKS))
                .save(output, Materials.MODID + ":reinforced_mud_bricks_block");
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK_ITEM.get(), 1)
                .pattern("MMM")
                .pattern("CIC")
                .pattern("MMM")
                .define('M', Items.MUD)
                .define('C', Items.COPPER_INGOT)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.MUD), has(Items.MUD))
                .save(output, Materials.MODID + ":mud_to_reinforced_mud_bricks_block");

        // 溶蚀石头 - 用滴水石和水桶 或者 用石头和水桶 制作
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.DISSOLVED_STONE_BLOCK_ITEM.get(), 1)
                .requires(Items.DRIPSTONE_BLOCK, 1)
                .requires(Items.WATER_BUCKET, 1)
                .unlockedBy(getHasName(Items.DRIPSTONE_BLOCK), has(Items.DRIPSTONE_BLOCK))
                .save(output, Materials.MODID + ":dissolved_stone_block");
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get(), 1)
                .pattern("W")
                .pattern("W")
                .pattern("S")
                .define('S', Items.STONE)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.STONE), has(Items.STONE))
                .save(output, Materials.MODID + ":dissolved_stone_block_from_stone");
        // 滴水石可以分解为滴水石锥 (补充Minecraft原版没有的配方)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.POINTED_DRIPSTONE, 4)
                .requires(Items.DRIPSTONE_BLOCK, 1)
                .unlockedBy(getHasName(Items.DRIPSTONE_BLOCK), has(Items.DRIPSTONE_BLOCK))
                .save(output, Materials.MODID + ":dripstone_to_pointed_dripstone");

        // 冰糖 - 用糖制作
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.ROCK_SUGAR_BLOCK.get(), 1)
                .requires(Items.SUGAR, 9)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(output, Materials.MODID + ":rock_sugar_block_from_sugar");
        // 冰糖可以分解成糖
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.SUGAR, 9)
                .requires(EnrollBlocks.ROCK_SUGAR_BLOCK.get())
                .unlockedBy(getHasName(EnrollBlocks.ROCK_SUGAR_BLOCK.get()), has(EnrollBlocks.ROCK_SUGAR_BLOCK.get()))
                .save(output, Materials.MODID + ":rock_sugar_block_to_sugar");
        // 强化冰糖 - 用冰糖和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_ROCK_SUGAR_BLOCK.get(), 1)
                .pattern("SSS")
                .pattern("SIS")
                .pattern("SSS")
                .define('S', EnrollBlocks.ROCK_SUGAR_BLOCK.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(EnrollBlocks.ROCK_SUGAR_BLOCK.get()), has(EnrollBlocks.ROCK_SUGAR_BLOCK.get()))
                .save(output, Materials.MODID + ":reinforced_rock_sugar_block");

        // 仿制基岩 - 用合成黑曜石和下界合金块制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.IMITATION_BEDROCK_BLOCK_ITEM.get(), 1)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .define('O', EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get())
                .define('I', Items.NETHERITE_BLOCK)
                .unlockedBy(getHasName(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get()), has(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get()))
                .save(output, Materials.MODID + ":imitation_bedrock_block");
    }
}
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
import org.materials.materials.Materials;

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
    protected void buildRecipes(RecipeOutput output)
    {
        // 有序合成配方 - 脆弱木板 (用普通木板制作)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get(), 4)
                .pattern("W ")
                .pattern(" W")
                .define('W', ItemTags.PLANKS)  // 任意木板
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(ItemTags.PLANKS))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "fragile_plank_block"));
        // 脆弱木板可以分解成木棍
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.STICK, 1)
                .requires(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get())
                .unlockedBy(getHasName(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get()), has(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "fragile_plank_to_sticks"));
        // 强化木板配方 - 用大量木头制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_PLANK_BLOCK_ITEM.get(), 1)
                .pattern("LLL")
                .pattern("LLL")
                .pattern("LLL")
                .define('L', ItemTags.LOGS)
                .unlockedBy(getHasName(Items.OAK_LOG), has(ItemTags.LOGS))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "logs_to_reinforced_plank_block"));

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
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_smooth_stone_block"));
        // 脆弱平滑石头 - 用燧石压制
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get(), 2)
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.FLINT)
                .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                .save(output);

        // 熔炉配方 - 无边玻璃
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.GLASS),
                        RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get(),
                        2.0f, 400)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "borderless_glass_from_smelting"));
        // 高炉配方 - 无边玻璃
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.GLASS),
                        RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get(),
                        2.0f, 200)
                .unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "borderless_glass_from_blasting"));

        // 高强度玻璃 - 用无边玻璃和钻石制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK_ITEM.get(), 1)
                .pattern("GGG")
                .pattern("GDG")
                .pattern("GGG")
                .define('G', EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get())
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get()), has(EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "high_strength_glass_block"));

        // 合成强化深板岩 - 用深板岩和下界合金块制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK_ITEM.get(), 1)
                .pattern("DDD")
                .pattern("DID")
                .pattern("DDD")
                .define('D', Items.DEEPSLATE)
                .define('I', Items.NETHERITE_BLOCK)
                .unlockedBy(getHasName(Items.DEEPSLATE), has(Items.DEEPSLATE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "synthetic_reinforced_deepslate_block"));
        // 脆弱深板岩 - 用深板岩圆石制作
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_DEEPSLATE_BLOCK_ITEM.get(), 2)
                .requires(Items.COBBLED_DEEPSLATE, 1)
                .unlockedBy(getHasName(Items.COBBLED_DEEPSLATE), has(Items.COBBLED_DEEPSLATE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "fragile_deepslate_block"));

        // 强化砂岩 - 用砂岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_SANDSTONE_BLOCK_ITEM.get(), 2)
                .pattern("SSS")
                .pattern("SIS")
                .pattern("SSS")
                .define('S', Items.SANDSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.SANDSTONE), has(Items.SANDSTONE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_sandstone_block"));

        // 强化红砂岩 - 用红砂岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_RED_SANDSTONE_BLOCK_ITEM.get(), 2)
                .pattern("RRR")
                .pattern("RIR")
                .pattern("RRR")
                .define('R', Items.RED_SANDSTONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.RED_SANDSTONE), has(Items.RED_SANDSTONE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_red_sandstone_block"));

        // 强化凝灰岩 - 用凝灰岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_TUFF_BLOCK_ITEM.get(), 2)
                .pattern("TTT")
                .pattern("TIT")
                .pattern("TTT")
                .define('T', Items.TUFF)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.TUFF), has(Items.TUFF))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_tuff_block"));

        // 强化下界岩 - 用下界岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_NETHERRACK_BLOCK_ITEM.get(), 2)
                .pattern("NNN")
                .pattern("NIN")
                .pattern("NNN")
                .define('N', Items.NETHERRACK)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_nether_brick_block"));

        // 强化玄武岩 - 用玄武岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_BASALT_BLOCK_ITEM.get(), 2)
                .pattern("BBB")
                .pattern("BIB")
                .pattern("BBB")
                .define('B', Items.BASALT)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.BASALT), has(Items.BASALT))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_basalt_block"));

        // 脆弱末地石 - 用末地石制作
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get(), 2)
                .requires(Items.END_STONE, 1)
                .unlockedBy(getHasName(Items.END_STONE), has(Items.END_STONE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "fragile_end_stone_block"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Items.END_STONE, 1)
                .requires(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get(), 2)
                .unlockedBy(getHasName(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get()), has(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "fragile_end_stone_to_end_stone"));
        // 强化末地石 - 用末地石和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_END_STONE_BLOCK_ITEM.get(), 2)
                .pattern("EEE")
                .pattern("EIE")
                .pattern("EEE")
                .define('E', Items.END_STONE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.END_STONE), has(Items.END_STONE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_end_stone_block"));

        // 合成黑曜石 - 用钻石块和黑曜石制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get(), 1)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .define('O', Items.OBSIDIAN)
                .define('I', Items.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "synthetic_obsidian_block"));

        // 强化花岗岩 - 用花岗岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_GRANITE_BLOCK_ITEM.get(), 2)
                .pattern("GGG")
                .pattern("GIG")
                .pattern("GGG")
                .define('G', Items.GRANITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.GRANITE), has(Items.GRANITE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_granite_block"));

        // 强化闪长岩 - 用闪长岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_DIORITE_BLOCK_ITEM.get(), 2)
                .pattern("DDD")
                .pattern("DID")
                .pattern("DDD")
                .define('D', Items.DIORITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.DIORITE), has(Items.DIORITE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_diorite_block"));

        // 强化安山岩 - 用安山岩和铁锭制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.REINFORCED_ANDESITE_BLOCK_ITEM.get(), 2)
                .pattern("AAA")
                .pattern("AIA")
                .pattern("AAA")
                .define('A', Items.ANDESITE)
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.ANDESITE), has(Items.ANDESITE))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "reinforced_andesite_block"));

        // 溶蚀石头 - 用滴水石和水桶制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.DISSOLVED_STONE_BLOCK_ITEM.get(), 1)
                .pattern("W")
                .pattern("S")
                .define('S', Items.DRIPSTONE_BLOCK)
                .define('W', Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.DRIPSTONE_BLOCK), has(Items.DRIPSTONE_BLOCK))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "dissolved_stone_block"));

        // 仿制基岩 - 用合成黑曜石和下界合金块制作
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, EnrollBlocks.IMITATION_BEDROCK_BLOCK_ITEM.get(), 1)
                .pattern("OOO")
                .pattern("OIO")
                .pattern("OOO")
                .define('O', EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get())
                .define('I', Items.NETHERITE_BLOCK)
                .unlockedBy(getHasName(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get()), has(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get()))
                .save(output, ResourceLocation.fromNamespaceAndPath(Materials.MODID, "imitation_bedrock_block"));
    }
}
package org.materials.materials;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.Objects;

import static org.materials.materials.Materials.*;

public class EnrollItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // 注册名为 体质强化饮料 的物品
    public static final DeferredItem<Item> PHYSIQUE_STRENGTHENING_BEVERAGE_ITEM = ITEMS.register("physique_strengthening_beverage", () ->
            new TipsItem(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(2)  // 提供2饥饿值 (2个营养点)
                            .saturationModifier(0.33f)  // 饱和度修正值 (1.21.1中方法名变更)
                            .alwaysEdible()  // 即使饱食时也能使用 (1.21.1中方法名变更)
                            .build()),
                    "item.materials.physique_strengthening_beverage"
            )
            {
                @Override
                public @Nonnull ItemStack finishUsingItem(@Nonnull ItemStack stack, @Nonnull Level level, @Nonnull LivingEntity entity)
                {
                    if (!level.isClientSide && entity instanceof Player player)
                    {
                        // 饮用效果
                        // 立即给予玩家 16 秒的反胃效果
                        player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 320, 0, false, true, true));

                        // 16 秒后给予玩家 2 分钟的急迫 III 和 2 分钟的速度 III 效果
                        Objects.requireNonNull(level.getServer()).tell(new net.minecraft.server.TickTask(320, () ->
                        {
                            if (player.isAlive() && !player.isRemoved())
                            {
                                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 2)); // 2分钟急迫III
                                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 2)); // 2分钟速度III
                            }
                        }));

                        // 给玩家一个玻璃瓶（如果物品栏满了会掉落）
                        if (!player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE)))
                        {
                            player.drop(new ItemStack(Items.GLASS_BOTTLE), false);
                        }
                    }

                    // 减少堆叠数量并返回剩余的堆叠
                    stack.shrink(1);
                    return stack.isEmpty() ? ItemStack.EMPTY : stack;
                }

                @Override
                public @Nonnull UseAnim getUseAnimation(@Nonnull ItemStack stack)
                {
                    return UseAnim.DRINK;  // 饮用动画
                }
            });

    // 处理酿造台配方事件类
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.GAME)
    public static class BrewingRecipeHandler
    {
        @SubscribeEvent
        public static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent event)
        {
            Materials.LOGGER.info("Registering brewing recipes...");

            // 体质强化饮料酿造配方
            // 迅捷药水 + 青金石 = 体质强化饮料
            event.getBuilder().addRecipe(new net.neoforged.neoforge.common.brewing.IBrewingRecipe()
            {
                @Override
                public boolean isInput(@Nonnull ItemStack input)
                {
                    // 检查是否为迅捷药水
                    if (!input.is(Items.POTION))
                        return false;
                    PotionContents contents = input.get(net.minecraft.core.component.DataComponents.POTION_CONTENTS);
                    return contents != null && contents.potion().isPresent() &&
                            contents.potion().get().equals(Potions.SWIFTNESS);
                }

                @Override
                public boolean isIngredient(@Nonnull ItemStack ingredient)
                {
                    // 检查是否为青金石
                    return ingredient.is(Items.LAPIS_LAZULI);
                }

                @Override
                public @Nonnull ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient)
                {
                    if (isInput(input) && isIngredient(ingredient))
                    {
                        return PHYSIQUE_STRENGTHENING_BEVERAGE_ITEM.get().getDefaultInstance();
                    }
                    return ItemStack.EMPTY;
                }
            });

            Materials.LOGGER.info("Brewing recipe registered successfully!");
        }
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MATERIALS_TAB = CREATIVE_MODE_TABS.register("example_tab", () ->
            CreativeModeTab.builder().title(Component.translatable("creative_tab.materials.example_tab"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> EnrollBlocks.REINFORCED_SMOOTH_STONE_BLOCK_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) ->
                    {
                        output.accept(PHYSIQUE_STRENGTHENING_BEVERAGE_ITEM.get());
                        output.accept(EnrollBlocks.EXP_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.FRAGILE_PLANK_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_PLANK_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_SMOOTH_STONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.FRAGILE_SMOOTH_STONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_STONE_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_CHISELED_STONE_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.BORDERLESS_GLASS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.SIX_PHASE_ICE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.SYNTHETIC_REINFORCED_DEEPSLATE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.FRAGILE_DEEPSLATE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_SANDSTONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_RED_SANDSTONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_TUFF_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_NETHERRACK_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_NETHER_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_CHISELED_NETHER_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_RED_NETHER_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_BASALT_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_SMOOTH_BASALT_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_BLACKSTONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_POLISHED_BLACKSTONE_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.FRAGILE_END_STONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_END_STONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_END_STONE_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.SYNTHETIC_OBSIDIAN_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_GRANITE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_DIORITE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_ANDESITE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_CALCITE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_RED_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_PRISMARINE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_PRISMARINE_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_DARK_PRISMARINE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_QUARTZ_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_CHISELED_QUARTZ_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_QUARTZ_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_QUARTZ_PILLAR_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_AMETHYST_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_PURPUR_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_PURPUR_PILLAR_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.COMPRESSED_PACKED_MUD_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_MUD_BRICKS_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.DISSOLVED_STONE_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.ROCK_SUGAR_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.REINFORCED_ROCK_SUGAR_BLOCK_ITEM.get());
                        output.accept(EnrollBlocks.IMITATION_BEDROCK_BLOCK_ITEM.get());
                        // 条件性添加萤火虫灌木
                        if (EnrollBlocks.FIREFLY_BUSH_ITEM != null)
                        {
                            output.accept(EnrollBlocks.FIREFLY_BUSH_ITEM.get());
                        }
                    })
                    .build());
}

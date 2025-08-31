package org.materials.materials;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

@Mod(Materials.MODID)
public class Materials
{
    public static final String MODID = "materials";
    static final Logger LOGGER = LogUtils.getLogger();

    // 注册方块的注册表
    public static final TagKey<Block> NEEDS_GOLDEN_TOOL = BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "needs_golden_tool"));
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "needs_netherite_tool"));
    public static final TagKey<Block> MINEABLE_WITH_SHEARS = BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "mineable/shears"));

    public Materials(IEventBus modEventBus, ModContainer modContainer)
    {
        // 使用构造函数注入的 modEventBus
        // 注册自己的注册表
        EnrollBlocks.BLOCKS.register(modEventBus);
        EnrollBlocks.ITEMS.register(modEventBus);
        EnrollItems.ITEMS.register(modEventBus);
        EnrollItems.CREATIVE_MODE_TABS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(EnrollBlocks::commonSetup);

        // 自定义创造标签已通过 CREATIVE_MODE_TABS 的 displayItems 填充，此处不再通过事件重复添加
        // 数据生成监听已由 org.materials.materials.datagen.DataGenerators 处理

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");

        // 验证方块注册
        ResourceLocation testBlock = ResourceLocation.fromNamespaceAndPath(MODID, "exp");
        if (BuiltInRegistries.BLOCK.containsKey(testBlock))
        {
            LOGGER.info("Server test PASSED: Blocks registered successfully");
        }
        else
        {
            LOGGER.error("Server test FAILED: Blocks not registered");
        }
    }

    // 自定义开采层级校验：金及以上、下界合金及以上
    @SubscribeEvent
    public void onHarvestCheck(PlayerEvent.HarvestCheck event)
    {
        BlockState state = event.getTargetBlock();
        if (state == null)
            return;

        // 仅当方块被标记到自定义标签时介入
        Tier required;
        if (state.is(NEEDS_NETHERITE_TOOL))
        {
            required = Tiers.NETHERITE;
        }
        else if (state.is(NEEDS_GOLDEN_TOOL))
        {
            required = Tiers.GOLD;
        }
        else
        {
            return; // 未标记则不干预默认判定
        }

        if (event.getEntity() != null && event.getEntity().isCreative())
            return; // 创造模式不拦截

        ItemStack stack = event.getEntity() != null ? event.getEntity().getMainHandItem() : ItemStack.EMPTY;
        boolean allowed = (required == Tiers.NETHERITE) ? isAllowedForNetherite(stack) : isAllowedForGolden(stack);
        if (!allowed)
        {
            event.setCanHarvest(false);
        }
    }

    // 金及以上：允许 金/铁/钻石/下界合金
    private static boolean isAllowedForGolden(ItemStack stack)
    {
        if (!(stack.getItem() instanceof TieredItem tiered))
            return false; // 非分层工具不允许

        Tier actual = tiered.getTier();
        return actual == Tiers.GOLD || actual == Tiers.IRON || actual == Tiers.DIAMOND || actual == Tiers.NETHERITE;
    }

    // 下界合金及以上：仅允许 下界合金
    private static boolean isAllowedForNetherite(ItemStack stack)
    {
        if (!(stack.getItem() instanceof TieredItem tiered))
            return false;

        Tier actual = tiered.getTier();
        return actual == Tiers.NETHERITE;
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        @SuppressWarnings("deprecation")
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            if (net.neoforged.fml.ModList.get().isLoaded("extendedblocks"))
            {
                // 执行联动代码
                LOGGER.info("Mod extendedblocks is loaded.");
            }

            // 渲染层注册：使用旧 API，添加抑制以避免弃用告警阻塞编译
            event.enqueueWork(() ->
            {
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.BORDERLESS_GLASS_BLOCK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.SIX_PHASE_ICE_BLOCK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.ROCK_SUGAR_BLOCK.get(), RenderType.translucent());
                // 条件性设置萤火虫灌木渲染层
                if (EnrollBlocks.FIREFLY_BUSH != null)
                {
                    ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.FIREFLY_BUSH.get(), RenderType.cutout());
                }
            });
        }
    }
}

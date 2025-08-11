package org.materials.materials;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(Materials.MODID)
public class Materials
{
    public static final String MODID = "materials";
    static final Logger LOGGER = LogUtils.getLogger();

    // 注册方块的注册表
    public static final TagKey<Block> NEEDS_GOLDEN_TOOL = BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "needs_golden_tool"));
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = BlockTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "needs_netherite_tool"));

    public Materials(IEventBus modEventBus, ModContainer modContainer)
    {
        // 使用构造函数注入的 modEventBus
        // 注册自己的注册表
        EnrollBlocks.BLOCKS.register(modEventBus);
        EnrollBlocks.ITEMS.register(modEventBus);
        EnrollBlocks.CREATIVE_MODE_TABS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(EnrollBlocks::commonSetup);
        modEventBus.addListener(EnrollBlocks::addCreative);
        // 数据生成监听已由 org.materials.materials.datagen.DataGenerators 处理

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            if (net.neoforged.fml.ModList.get().isLoaded("extendedblocks"))
            {
                // 执行联动代码
                LOGGER.info("Mod extendedblocks is loaded.");
            }

            event.enqueueWork(() ->
            {
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.BORDERLESS_GLASS_BLOCK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.HIGH_STRENGTH_GLASS_BLOCK.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(EnrollBlocks.SIX_PHASE_ICE_BLOCK.get(), RenderType.translucent());
            });
        }
    }
}
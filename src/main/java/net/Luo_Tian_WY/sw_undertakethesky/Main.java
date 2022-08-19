package net.Luo_Tian_WY.sw_undertakethesky;

import net.Luo_Tian_WY.sw_undertakethesky.commands.StatementsAndRegisters;
import net.Luo_Tian_WY.sw_undertakethesky.commands.SWUSClientMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.AshParticle;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sw_undertakethesky");

	@Override
	public void onInitialize() {
		StatementsAndRegisters register = new StatementsAndRegisters();//调用注册类

		//cutout 不透明或全透明, Translucent 半透明
		BlockRenderLayerMap.INSTANCE.putBlock(StatementsAndRegisters.PRUNUS_PERSICA_SAPLING, RenderLayer.getCutout());//所以这才是起作用的渲染器
		BlockRenderLayerMap.INSTANCE.putBlock(StatementsAndRegisters.FLOWERING_PRUNUS_PERSICA_LEAVES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(StatementsAndRegisters.PRUNUS_PERSICA_DOOR, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(StatementsAndRegisters.PRUNUS_PERSICA_TRAPDOOR, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(StatementsAndRegisters.DECAYED_GRASS_AND_FIREFLY, RenderLayer.getCutout());

		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(new Identifier("sw_undertakethesky", "particle/firefly"));
		}));
		ParticleFactoryRegistry.getInstance().register(StatementsAndRegisters.FIREFLY, EndRodParticle.Factory::new);

		SWUSClientMod render = new SWUSClientMod();//调用渲染器
		//我 tm 怎么知道这句为啥没用

		LOGGER.info("Hello the second world!");
	}
}

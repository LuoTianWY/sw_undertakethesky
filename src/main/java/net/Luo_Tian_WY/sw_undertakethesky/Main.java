package net.Luo_Tian_WY.sw_undertakethesky;

import net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica;
import net.Luo_Tian_WY.sw_undertakethesky.commands.Registers;
import net.Luo_Tian_WY.sw_undertakethesky.commands.SWUSClientMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sw_undertakethesky");

	@Override
	public void onInitialize() {
		Registers register = new Registers();//调用注册类

		BlockRenderLayerMap.INSTANCE.putBlock(PrunusPersica.PRUNUS_PERSICA_SAPLING, RenderLayer.getCutout());//所以这才是起作用的渲染器
		SWUSClientMod render = new SWUSClientMod();//调用渲染器
		//我 tm 怎么知道这玩意为啥没用

		LOGGER.info("Hello the second world!");
	}
}

package net.Luo_Tian_WY.sw_undertakethesky.commands;

import net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class SWUSClientMod implements ClientModInitializer {
    //客户端初始化类，用于在初始化时为渲染器提供需要进行渲染的方块进行烘焙
    //cutout 为不透明或全透明
    @Override
    public void onInitializeClient(){
        BlockRenderLayerMap.INSTANCE.putBlock(PrunusPersica.PRUNUS_PERSICA_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PrunusPersica.PRUNUS_PERSICA_LEAVES, RenderLayer.getCutout());
    }
}

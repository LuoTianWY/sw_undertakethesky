package net.Luo_Tian_WY.sw_undertakethesky.commands;

import net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ItemGroup {
    //创建新物品组
    public static final net.minecraft.item.ItemGroup SW_GROUP = FabricItemGroupBuilder.create(
            new Identifier("sw_undertakethesky", "general"))
            .icon(() -> new ItemStack(PrunusPersica.PRUNUS_PERSICA_LOG))
            .build();
}

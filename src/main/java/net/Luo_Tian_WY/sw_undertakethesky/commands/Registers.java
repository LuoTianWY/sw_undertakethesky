package net.Luo_Tian_WY.sw_undertakethesky.commands;

import com.google.common.base.StandardSystemProperty;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.impl.client.rendering.ColorProviderRegistryImpl;
import net.fabricmc.fabric.mixin.client.rendering.MixinBlockColorMap;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.level.ColorResolver;
import org.jetbrains.annotations.Nullable;

import static net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica.PRUNUS_PERSICA_LEAVES;
import static net.Luo_Tian_WY.sw_undertakethesky.blocks.PrunusPersica.PRUNUS_PERSICA_LOG;

public class Registers{
    //初始化方法，以下注册物品
    public Registers(){
        Registry.register(
                Registry.BLOCK,
                new Identifier("sw_undertakethesky", "prunus_persica_log"),
                PRUNUS_PERSICA_LOG
        );//注册桃木原木
        Registry.register(
                Registry.ITEM,
                new Identifier("sw_undertakethesky", "prunus_persica_log"),
                new BlockItem(
                        PRUNUS_PERSICA_LOG,
                        new Item.Settings().group(ItemGroup.SW_GROUP)
                )
        );//注册桃木原木的物品形式

        Registry.register(
                Registry.BLOCK,
                new Identifier("sw_undertakethesky", "prunus_persica_leaves"),
                PRUNUS_PERSICA_LEAVES
        );
        Registry.register(
                Registry.ITEM,
                new Identifier("sw_undertakethesky", "prunus_persica_leaves"),
                new BlockItem(
                        PRUNUS_PERSICA_LEAVES,
                        new Item.Settings().group(ItemGroup.SW_GROUP)
                )
        );

        //以下进行燃料注册
        FuelRegistry.INSTANCE.add(PRUNUS_PERSICA_LOG.asItem(), 300);

        //以下进行 ColorProvider 注册，用以实现 ColorMap 渲染
        ColorProviderRegistry.ITEM.register((((stack, tintIndex) -> 0x48b518)), PRUNUS_PERSICA_LEAVES);//注册物品栏中桃树树叶的颜色，硬编码为与橡树树叶相同
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            assert world != null;
            return BiomeColors.getFoliageColor(world, pos);
        }, PRUNUS_PERSICA_LEAVES);//注册作为方块摆放时的桃树树叶颜色，调用 FoliageColor（树叶用） 根据 Biome 渲染
    }
}

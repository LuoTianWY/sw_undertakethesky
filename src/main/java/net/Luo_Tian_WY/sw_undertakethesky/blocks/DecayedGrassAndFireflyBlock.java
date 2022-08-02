package net.Luo_Tian_WY.sw_undertakethesky.blocks;

import net.Luo_Tian_WY.sw_undertakethesky.commands.StatementsAndRegisters;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.block.CropBlock.AGE;

public class DecayedGrassAndFireflyBlock extends Block {
    public DecayedGrassAndFireflyBlock(Settings settings){
        super(settings);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack){
        super.onPlaced(world, pos, state, placer, itemStack);
    }
    /**
     * 本方法写入腐草萤虫在每一 game tick 执行的操作
     * 因为在原版与模组本身中，调用的 AGE 状态值只有 3 与 7，故而并未特意写出扩展用方法，如有需要可自行添加
     * 实现的功能：检查以自身为中心 5 * 5 正方形范围内带有 crops 标签的方块的 age 值，若不为满，则 +1
     * @param state
     * @param world
     * @param pos pos 位置即腐草萤虫方块所在的位置
     * @param random
     */
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        float x_offset = random.nextFloat() * 4;//x 轴随机偏移，连带自身一共五格
        float z_offset = random.nextFloat() * 4;//z 轴随机偏移，连带自身一共五格
        /*
        获得随机出的方位值
        0.5 用以调整执行位置至方块中心
        -2 用以将执行位置调整至腐草萤虫方块 x, z 轴负方向 2 格位置（正负号不论，任意组合都可以实现）
        offset 用以随机取得执行位置
         */
        double pos_x = pos.getX() + 0.5 - 2 + x_offset;
        double pos_y = pos.getY() + 0.5;
        double pos_z = pos.getZ() + 0.5 - 2 + z_offset;
        BlockPos nowBlockPos = combineBlockPos(pos_x, pos_y, pos_z);//作物方块所在的位置，此处其实可以使用自带的 add 方法，但不知道为何会出错
        BlockPos beneathBlockPos = combineBlockPos(pos_x, pos_y - 1, pos_z);//作物下方一个方块所在的位置

        //判断 pos 处的方块是否为腐草萤虫，同时判断 nowBlockPos 处的方块是否带有 crops 标签（实际上第一个条件没有什么具体的作用）
        if (world.getBlockState(pos).getBlock() == StatementsAndRegisters.DECAYED_GRASS_AND_FIREFLY && world.getBlockState(nowBlockPos).isIn(BlockTags.CROPS)) {
            //AGE 系列标签对应游戏中作物的生长进度，即状态值 age。其后跟随的数字代表这个作物的 age 值的最大值
            if(world.getBlockState(nowBlockPos).getBlock().getStateManager().getProperties().contains(Properties.AGE_3)){//第一个分支，判定 nowBlockPos 处的方块是否拥有 AGE_3 标签
                int i = world.getBlockState(nowBlockPos).get(Properties.AGE_3);//获取当前位置 crop 方块的 age 值
                if (i < 3) {//限制条件 1, 保证当前 age 值一定小于 3，否则会产生溢出
                    if (world.getBlockState(beneathBlockPos).getBlock() == Blocks.FARMLAND && world.getBlockState(beneathBlockPos).get(FarmlandBlock.MOISTURE) > 0) {
                        //上为限制条件 2, 判定当前位置 crop 方块下方一个方块处是否为 Farmland 且是否已经润湿
                        world.setBlockState(nowBlockPos, world.getBlockState(nowBlockPos).with(Properties.AGE_3, i + 1));//使在 nowBlockPos 处的作物方块 age 值 +1
                        world.addParticle(ParticleTypes.COMPOSTER, pos_x, pos_y, pos_z, 0.0, 0.05, 0.0);//生成粒子效果，COMPOSTER 为堆肥桶堆肥成功的粒子
                    }
                }
            } else if (world.getBlockState(nowBlockPos).getBlock().getStateManager().getProperties().contains(Properties.AGE_7)) {//第二个分支，判定 nowBlockPos 处的方块是否拥有 AGE_7 标签
                //以下与上一个分支相同，除去数据更改
                int i = world.getBlockState(nowBlockPos).get(Properties.AGE_7);
                if (i < 7) {
                    if (world.getBlockState(beneathBlockPos).getBlock() == Blocks.FARMLAND && world.getBlockState(beneathBlockPos).get(FarmlandBlock.MOISTURE) > 0) {
                        world.setBlockState(nowBlockPos, world.getBlockState(nowBlockPos).with(Properties.AGE_7, i + 1));
                        world.addParticle(ParticleTypes.COMPOSTER, pos_x, pos_y, pos_z, 0.0, 0.05, 0.0);
                    }
                }
            }
        }
    }

    public BlockPos combineBlockPos(double x, double y, double z){
      return new BlockPos(x, y, z);
    }
}

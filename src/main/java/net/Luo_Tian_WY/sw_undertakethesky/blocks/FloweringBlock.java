package net.Luo_Tian_WY.sw_undertakethesky.blocks;

import net.Luo_Tian_WY.sw_undertakethesky.commands.StatementsAndRegisters;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 本类运行中会出现警告报错，不影响正常运行，但具体原因仍待详细测试
 * 怀疑与客户端——服务端同步有关
 */
public class FloweringBlock extends Block {
    //定义新方块状态 flowering
    public static final BooleanProperty FLOWERING = BooleanProperty.of("flowering");
    //初始化方法，并设置默认的方块状态
    public FloweringBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FLOWERING, true));
    }
    /**
     * 为方块添加方块状态
     * 注意：若本类继承的类不为 Block 或在创建方块状态后未实现 appendProperties 方法，可能导致方块状态被错误配置到其他的方块中导致游戏崩溃
    */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager){
        stateManager.add(FLOWERING);
    }

    /**
     * 覆写 onUse 方法（在 AbstractBlock 类中）
     * @param state 当前方块状态
     * @param world
     * @param pos 当前方块位置
     * @param player 执行者
     * @param hand 执行者手部
     * @param hit
     * @return
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getStackInHand(hand).getItem() == Items.SHEARS)//判断执行者主手是否为剪刀
        {//若是
            world.setBlockState(pos, state.with(FLOWERING, false));//将该方块 flowering 设置为 false
            player.playSound(SoundEvents.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f);//播放声音
            world.setBlockState(pos, StatementsAndRegisters.PRUNUS_PERSICA_LEAVES.getDefaultState());//通过 setBlockState 方法更换执行位置的方块
            player.dropItem(StatementsAndRegisters.PEACH_BLOSSOM);//给予玩家对应物品
        }

        return ActionResult.SUCCESS;
    }
}

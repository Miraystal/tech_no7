package github.m1raystal.tech_no7.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class TryEnchantingTableMixin {
    @Inject(method = "createBlockEntity", at = @At("HEAD"))
    private void onJump(BlockPos pos, BlockState state, CallbackInfoReturnable<BlockEntity> cir) {
        //Tech_no7.LOGGER.info("EnchantingTable BlockEntity created");
    }
}

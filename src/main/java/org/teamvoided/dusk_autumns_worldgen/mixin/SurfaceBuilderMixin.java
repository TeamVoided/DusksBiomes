package org.teamvoided.dusk_autumns_worldgen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Holder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.BlockColumn;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.dusk_autumns_worldgen.util.DebugInfo;

//import static org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.log;
@Debug(export = true)
@Mixin(SurfaceBuilder.class)
public abstract class SurfaceBuilderMixin {
    @Final
    @Shadow
    private DoublePerlinNoiseSampler badlandsSurfaceNoise;
    @Final
    @Shadow
    private DoublePerlinNoiseSampler badlandsPillarNoise;
    @Final
    @Shadow
    private DoublePerlinNoiseSampler badlandsPillarRootNoise;
    @Final
    @Shadow
    private BlockState defaultBlock;
    @Final
    @Shadow
    private int seaLevel;


    @Redirect(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Holder;isRegistryKey(Lnet/minecraft/registry/RegistryKey;)Z"))
    private <T> boolean buildSurface(Holder instance, RegistryKey<T> tRegistryKey) {
        if (tRegistryKey == Biomes.ERODED_BADLANDS) return true;//instance.isIn(BiomeTags.OVERWORLD);
        else return instance.isRegistryKey(tRegistryKey);

    }

//    @Inject(method = "buildSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/BiomeAccess;getBiome(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/registry/Holder;"))
//    private void changeHeight(CallbackInfo ci, @Local(ordinal = 6) LocalIntRef o, @Local(ordinal = 2) int k, @Local(ordinal = 3) int l, @Local(ordinal = 1) int z, @Local(ordinal = 0) Chunk chunk) {
//        o.set(chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, k, l) + 1);
//    }

    /*@ModifyVariable(method = "buildErodedBadlandsSpecificSurface", at = @At("STORE"), ordinal = 3)
    private int injected(int i, @Local(ordinal = 2) int y) {
        int q = 64 - y;
        DebugRenderer.INSTANCE.addToRenderer(q, "Difference");
        return (q > 0) ? i - q : q;
    }


    @Redirect(method = "buildErodedBadlandsSpecificSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean isWaterCheck(BlockState instance, Block block) {
        if (block == Blocks.WATER) return false;
        else return instance.isOf(block);
    }

    @Redirect(method = "buildErodedBadlandsSpecificSurface", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isAir()Z"))
    private boolean isAirCheck(BlockState block) {
        return block.isAir() || block.isLiquid();
    }*/


    @Inject(method = "buildErodedBadlandsSpecificSurface", at = @At("HEAD"), cancellable = true)
    private void replaceVanilla(BlockColumn chunkBlockColumn, int x, int z, int surfaceY, HeightLimitView chunk, CallbackInfo ci) {
        DebugInfo.INSTANCE.feedNoise(this.badlandsSurfaceNoise, this.badlandsPillarNoise, this.badlandsPillarRootNoise);

        int y = ((Chunk) chunk).sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, x % 16, z % 16) + 1;
        double sn = this.badlandsSurfaceNoise.sample(x, 0.0, z);
        double pn = this.badlandsPillarNoise.sample(x * 0.2, 0.0, z * 0.2);
        double e = Math.min(Math.abs(sn * 8.25), pn * 15.0);
        if (!(e <= 0.0)) {
            double h = Math.abs(this.badlandsPillarRootNoise.sample((double) x * 0.75, 0.0, (double) z * 0.75) * 1.5);
            int j = MathHelper
                    .floor(64.0 + Math.min(e * e * 2.5, Math.ceil(h * 50.0) + 24.0)) - Math.max(seaLevel - y, 0);
            if (y <= j) {
                for (int k = j; k >= chunk.getBottomY() && chunkBlockColumn.getState(k).isIn(BlockTags.REPLACEABLE); --k) {
                    chunkBlockColumn.setState(k, this.defaultBlock);
                }
            }
        }
        ci.cancel();
    }
}
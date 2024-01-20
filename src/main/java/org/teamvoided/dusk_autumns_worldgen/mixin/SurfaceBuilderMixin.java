package org.teamvoided.dusk_autumns_worldgen.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Holder;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import  static org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.log;
@Mixin(SurfaceBuilder.class)
public class SurfaceBuilderMixin {
    @Redirect(method = "buildSurface", at=@At(value = "INVOKE", target = "Lnet/minecraft/registry/Holder;isRegistryKey(Lnet/minecraft/registry/RegistryKey;)Z") )
    private <T>boolean buildSurface(Holder instance, RegistryKey<T> tRegistryKey){
        if (tRegistryKey == Biomes.ERODED_BADLANDS) {
            return instance.isIn(BiomeTags.OVERWORLD);
        }
        else {
            return instance.isRegistryKey(tRegistryKey);
        }
    }
    @Inject(method = "buildSurface", at=@At(value = "INVOKE", target = "Lnet/minecraft/world/gen/surfacebuilder/SurfaceBuilder;buildErodedBadlandsSpecificSurface(Lnet/minecraft/world/gen/chunk/BlockColumn;IIILnet/minecraft/world/HeightLimitView;)V"))
    private void changeHeight(CallbackInfo ci, @Local(ordinal = 0) LocalIntRef o, @Local(ordinal = 2) int k, @Local(ordinal = 3) int l, @Local(ordinal = 1) int z, @Local(ordinal = 4) int zz, @Local(ordinal = 5) int zzz, @Local(ordinal = 6) int zzzz, @Local(ordinal = 0) Chunk chunk){
        log.info("{},{},{},{},{},{},{}", o.get(), z, k, l, zz, zzz, zzzz);
        o.set(chunk.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR_WG, k, l) + 1);
    }

    @Redirect(method = "buildErodedBadlandsSpecificSurface", at=@At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean buildErodedBadlandsSpecificSurface(BlockState instance, Block block){
        if (block == Blocks.WATER) {
            return false;
        }
        else {
            return instance.isOf(block);
        }
    }
}
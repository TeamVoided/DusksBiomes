package org.teamvoided.dusks_biomes.mixin;


import net.minecraft.sound.MusicSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.OverworldBiomeCreator;
import net.minecraft.world.biome.SpawnSettings;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomeCreator.class)
public interface OverworldBiomeCreatorAccessor {


    @Invoker("createBiome")
    static Biome db_invokeCreate(
            boolean bl,
            float temperature,
            float f,
            int i,
            int j,
            @Nullable Integer integer,
            @Nullable Integer integer2,
            @Nullable Integer integer3,
            SpawnSettings.Builder builder,
            GenerationSettings.LookupBackedBuilder builder2,
            @Nullable MusicSound value
    ) {
        throw new IllegalStateException();
    }

    @Invoker("addBasicFeatures")
    static void db_invokerAddBasicFeatures(GenerationSettings.LookupBackedBuilder lookupBackedBuilder) {
        throw new IllegalStateException();
    }
}

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

    @Invoker("create")
    static Biome db_invokeCreate(
            boolean hasPrecipitation,
            float temperate,
            float downfall,
            SpawnSettings.Builder spawnSettings,
            GenerationSettings.Builder generationSettings,
            @Nullable MusicSound music
    ) {
        throw new IllegalStateException();
    }

    @Invoker("method_39151")
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
            GenerationSettings.Builder builder2,
            @Nullable MusicSound value
    ) {
        throw new IllegalStateException();
    }

    @Invoker("addBasicFeatures")
    static void db_invokerAddBasicFeatures(GenerationSettings.Builder generationSettings) {
        throw new IllegalStateException();
    }
}

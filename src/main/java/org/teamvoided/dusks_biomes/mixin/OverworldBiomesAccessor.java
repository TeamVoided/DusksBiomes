package org.teamvoided.dusks_biomes.mixin;


import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.sounds.Music;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomes.class)
public interface OverworldBiomesAccessor {

    @Invoker("biome")
    static Biome db_invokeBiome(
            boolean bl,
            float temperature,
            float f,
            int i,
            int j,
            @Nullable Integer integer,
            @Nullable Integer integer2,
            @Nullable Integer integer3,
            MobSpawnSettings.Builder builder,
            BiomeGenerationSettings.Builder builder2,
            @Nullable Music value
    ) {
        throw new IllegalStateException();
    }

    @Invoker("globalOverworldGeneration")
    static void db_invokerGlobalOverworldGeneration(BiomeGenerationSettings.Builder lookupBackedBuilder) {
        throw new IllegalStateException();
    }
}

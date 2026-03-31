package org.teamvoided.dusks_biomes.mixin;


import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(OverworldBiomes.class)
public interface OverworldBiomesAccessor {

    @Invoker("globalOverworldGeneration")
    static void db_invokerGlobalOverworldGeneration(BiomeGenerationSettings.Builder lookupBackedBuilder) {
        throw new IllegalStateException();
    }
}

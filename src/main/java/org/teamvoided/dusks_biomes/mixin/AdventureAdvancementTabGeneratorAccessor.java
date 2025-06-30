package org.teamvoided.dusks_biomes.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.data.server.advancement.AdventureAdvancementTabGenerator;
import net.minecraft.registry.HolderLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(AdventureAdvancementTabGenerator.class)
public interface AdventureAdvancementTabGeneratorAccessor {
    @Invoker("appendEnterAllBiomesCriterion")
    public static Advancement.Builder db_invokeAppendEnterAllBiomesCriterion(Advancement.Builder task, HolderLookup.Provider biomeProvider, List<RegistryKey<Biome>> biomes) {
        throw new IllegalStateException();

    }
}

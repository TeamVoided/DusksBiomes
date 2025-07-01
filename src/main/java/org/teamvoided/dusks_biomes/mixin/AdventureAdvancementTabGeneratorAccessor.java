package org.teamvoided.dusks_biomes.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.data.advancement.vanilla.VanillaAdventureTabAdvancementGenerator;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(VanillaAdventureTabAdvancementGenerator.class)
public interface AdventureAdvancementTabGeneratorAccessor {
    @Invoker("requireListedBiomesVisited")
    public static Advancement.Builder db_invokeAppendEnterAllBiomesCriterion(Advancement.Builder task, RegistryWrapper.WrapperLookup biomeProvider, List<RegistryKey<Biome>> biomes) {
        throw new IllegalStateException();

    }
}

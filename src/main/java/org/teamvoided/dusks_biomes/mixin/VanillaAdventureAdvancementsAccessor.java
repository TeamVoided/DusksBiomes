package org.teamvoided.dusks_biomes.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.packs.VanillaAdventureAdvancements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(VanillaAdventureAdvancements.class)
public interface VanillaAdventureAdvancementsAccessor {
    @Invoker("addBiomes")
    static Advancement.Builder db_invokeAddBiomes(Advancement.Builder builder, HolderLookup.Provider provider, List<ResourceKey<Biome>> list) {
        throw new IllegalStateException();
    }
}

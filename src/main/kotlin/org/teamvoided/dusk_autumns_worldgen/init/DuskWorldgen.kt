package org.teamvoided.dusk_autumns_worldgen.init

import com.mojang.serialization.Codec
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.worldgen.treedecorator.FixedLeavesVineTreeDecorator

object DuskWorldgen {
    val FIXED_LEAVE_VINE  = register("fixed_leave_vine", FixedLeavesVineTreeDecorator.CODEC)
    fun init() {
        DuskBiomes.init()
        DuskConfiguredFeatures.init()
        DuskPlacedFeatures.init()
        DuskStructures.init()
    }


    private fun <P : TreeDecorator> register(id: String, codec: Codec<P>): TreeDecoratorType<P> {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, id(id), TreeDecoratorType(codec))
    }
}
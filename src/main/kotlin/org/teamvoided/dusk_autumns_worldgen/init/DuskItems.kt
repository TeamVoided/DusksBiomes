package org.teamvoided.dusk_autumns_worldgen.init

import eu.pb4.factorytools.api.item.FactoryBlockItem
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskItems {

    val SUSPICIOUS_RED_SAND =
        register("suspicious_red_sand", FactoryBlockItem(DuskBlocks.SUSPICIOUS_RED_SAND, FabricItemSettings(), Items.RED_SAND))

    fun init() {}

    private fun register(id: String, item: Item): Item = Registry.register(Registries.ITEM, id(id), item)

}
package org.teamvoided.dusks_biomes

import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.init.DuskStructureProcessorTypes


@Suppress("unused")
object DusksBiomesMod {
    const val MODID = "dusks_biomes"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DusksBiomesMod::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")

        DuskBiomes.init()
        DuskStructureProcessorTypes.init()
    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun id(path: String) = id(MODID, path)
    fun mc(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
}

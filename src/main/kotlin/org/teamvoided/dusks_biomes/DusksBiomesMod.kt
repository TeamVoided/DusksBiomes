package org.teamvoided.dusks_biomes

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusks_biomes.init.DuskBiomes


@Suppress("unused")
object DusksBiomesMod {
    const val MODID = "dusks_biomes"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DusksBiomesMod::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")

        DuskBiomes.init()
    }

    fun id(path: String) = Identifier.method_60655(MODID, path)
    fun id(namespace: String, path: String) = Identifier.method_60655(namespace, path)
    fun mc(path: String) = Identifier.method_60656(path)
}

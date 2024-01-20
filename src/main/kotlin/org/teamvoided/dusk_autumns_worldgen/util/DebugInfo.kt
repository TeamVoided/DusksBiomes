package org.teamvoided.dusk_autumns_worldgen.util

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import org.teamvoided.dusk_autumns_worldgen.util.DebugRenderer.addToRenderer
import org.teamvoided.dusk_autumns_worldgen.util.DebugRenderer.getCords
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

object DebugInfo {
    var badlandsSurfaceNoise: DoublePerlinNoiseSampler? = null
    var badlandsPillarNoise: DoublePerlinNoiseSampler? = null
    var badlandsPillarRootNoise: DoublePerlinNoiseSampler? = null


    val bar = "======="
    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register { _ ->
            DebugRenderer.clear()
            populateErosionData()
            cords()
        }
    }

    fun feedNoise(
        badlandsSurfaceNoise1: DoublePerlinNoiseSampler,
        badlandsPillarNoise1: DoublePerlinNoiseSampler,
        badlandsPillarRootNoise1: DoublePerlinNoiseSampler
    ) {
        badlandsSurfaceNoise = badlandsSurfaceNoise1
        badlandsPillarNoise = badlandsPillarNoise1
        badlandsPillarRootNoise = badlandsPillarRootNoise1
    }

    fun cords() {
        val cords = getCords() ?: return
        "x=${cords.x},y=${cords.y},z=${cords.z}".addToRenderer("Cords")
    }

    fun populateErosionData() {
        val cords = getCords() ?: return
        if (badlandsSurfaceNoise == null || badlandsPillarNoise == null || badlandsPillarRootNoise == null) {
            "No noises cached".addToRenderer("STATUS")
            return
        }

        val x = cords.x
        val z = cords.z
        val y = cords.y

        bar.addToRenderer("Badlands")
        badlandsSurfaceNoise!!.sample(x.toDouble(), 0.0, z.toDouble()).addToRenderer("Surface Noise")
        badlandsPillarNoise!!.sample(x * 0.2, 0.0, z * 0.2).addToRenderer("Pillar Noise")
        val e = min(
            abs(badlandsSurfaceNoise!!.sample(x.toDouble(), 0.0, z.toDouble()) * 8.25),
            badlandsPillarNoise!!.sample(x * 0.2, 0.0, z * 0.2) * 15.0
        )
        e.addToRenderer("Should Generate")
        (!(e <= 0.0)).addToRenderer("Should bool")
        bar.addToRenderer("====")
        badlandsPillarRootNoise!!.sample(x.toDouble() * 0.75, 0.0, z.toDouble() * 0.75)
            .addToRenderer("Pillar Root Noise")
        val h = abs(
            badlandsPillarRootNoise!!.sample(x.toDouble() * 0.75, 0.0, z.toDouble() * 0.75) * 1.5
        )
        h.addToRenderer("Processed PRN")
        bar.addToRenderer("=====")
        (MathHelper.floor(64.0 + min(e * e * 2.5, ceil(h * 50.0) + 24.0)) - max(63 - y, 0)).addToRenderer("Final Value")
    }

}
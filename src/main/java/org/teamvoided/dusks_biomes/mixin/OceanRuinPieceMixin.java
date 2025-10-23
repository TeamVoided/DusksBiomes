package org.teamvoided.dusks_biomes.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.worldgen.lithostitched.config.ConfigHandler;
import dev.worldgen.lithostitched.worldgen.processor.UnboundReferenceProcessor;
import net.minecraft.world.level.levelgen.structure.structures.OceanRuinPieces;
import net.minecraft.world.level.levelgen.structure.structures.OceanRuinStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({OceanRuinPieces.OceanRuinPiece.class})
public class OceanRuinPieceMixin {
    @ModifyReturnValue(method = "makeSettings", at = @At("RETURN"))
    private static StructurePlaceSettings addShipwreckProcessor(StructurePlaceSettings settings, @Local(argsOnly = true) OceanRuinStructure.Type type) {
        var proc = UnboundReferenceProcessor.of(type == OceanRuinStructure.Type.COLD ? "cold_ruins" : "warm_ruins");
        return ConfigHandler.getConfig().breaksSeedParity() ? settings.addProcessor(proc) : settings;
    }
}


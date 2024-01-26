package org.teamvoided.dusk_autumns_worldgen.mixin;

import com.mojang.serialization.Lifecycle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.server.integrated.IntegratedServerLoader;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(IntegratedServerLoader.class)
public class IntegratedServerLoaderMixin {

    @Inject(method = "tryLoad", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V"), cancellable = true)
    private static void enderLib$cancelExperimental(MinecraftClient client, CreateWorldScreen parentScreen, Lifecycle dynamicRegistryLifecycle, Runnable successCallback, boolean bl, CallbackInfo ci) {
        if (dynamicRegistryLifecycle == Lifecycle.experimental()) {
            client.setScreen(parentScreen);
            ci.cancel();
        }
    }
}

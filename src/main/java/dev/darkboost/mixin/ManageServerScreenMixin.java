package dev.darkboost.mixin;

import dev.darkboost.PackSpooferConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ManageServerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ManageServerScreen.class)
public abstract class ManageServerScreenMixin {
    @Shadow @Final
    private ServerData serverData;

    @Inject(method = "init", at = @At("TAIL"))
    private void packspoofer$addFakePackToggle(CallbackInfo ci) {
        boolean enabled = PackSpooferConfig.isEnabledFor(this.serverData.ip);
        Button toggle = Button.builder(label(enabled), button -> {
            boolean nowEnabled = PackSpooferConfig.toggle(this.serverData.ip);
            button.setMessage(label(nowEnabled));
        }).bounds(((ManageServerScreen)(Object)this).width / 2 - 100,
                ((ManageServerScreen)(Object)this).height / 2 + 24, 200, 20).build();
        ((ManageServerScreen)(Object)this).addRenderableWidget(toggle);
    }

    private static Component label(boolean enabled) {
        return Component.literal("Fake resource-pack download: " + (enabled ? "ON" : "OFF"));
    }
}

package dev.darkboost.mixin;

import com.google.common.hash.HashCode;
import dev.darkboost.PackSpooferConfig;
import net.minecraft.client.resources.server.PackLoadFeedback;
import net.minecraft.client.resources.server.ServerPackManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.URL;
import java.util.UUID;

@Mixin(ServerPackManager.class)
public abstract class ServerPackManagerMixin {
    @Shadow @Final
    private PackLoadFeedback packLoadFeedback;

    @Inject(method = "pushPack", at = @At("HEAD"), cancellable = true)
    private void packspoofer$pretendPackWasApplied(UUID id, URL url, HashCode hash, CallbackInfo ci) {
        if (!PackSpooferConfig.isEnabledForCurrentServer()) return;
        this.packLoadFeedback.reportUpdate(id, PackLoadFeedback.Update.ACCEPTED);
        this.packLoadFeedback.reportFinalResult(id, PackLoadFeedback.FinalResult.APPLIED);
        ci.cancel();
    }
}

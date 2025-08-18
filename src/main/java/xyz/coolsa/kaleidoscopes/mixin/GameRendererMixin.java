package xyz.coolsa.kaleidoscopes.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.coolsa.kaleidoscopes.Kaleidoscopes;

@Mixin( GameRenderer.class )
abstract class GameRendererMixin
{
    @Inject(
        method = "renderWorld(Lnet/minecraft/client/render/RenderTickCounter;)V",
        at = @At( value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameOverlayRenderer;renderOverlays(BF)V" ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void test( CallbackInfo ci, RenderTickCounter renderTickCounter )
    {

//                Kaleidoscopes.LOGGER.info( "AAAAAA MIXIN!!!" );
    }
}

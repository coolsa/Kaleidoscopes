package xyz.coolsa.kaleidoscopes.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin( GameRenderer.class )
abstract class GameRendererMixin
{
    @Inject( method = "tick()V", at = @At( value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;updateEyeHeight()V" ) )
    private void test( CallbackInfo ci )
    {
//        Kaleidoscopes.LOGGER.info( "AAAAAA MIXIN!!!" );
    }


}

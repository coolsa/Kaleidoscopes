package xyz.coolsa.kaleidoscopes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.coolsa.kaleidoscopes.Kaleidoscopes;
import xyz.coolsa.kaleidoscopes.KaleidoscopesConstants;

@Mixin( InGameOverlayRenderer.class )
abstract class GameRendererMixin
{
    @Shadow
    protected VertexConsumerProvider vertexConsumers;
    @Shadow
    protected MinecraftClient client;

    @Inject(
        method = "renderOverlays(ZF)V",
        at = @At( value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z", shift = At.Shift.BEFORE )
    )
    private void test( CallbackInfo ci, @Local LocalRef<MatrixStack> matrixStack )
    {
        if( this.client.player.isUsingItem() && this.client.player.getActiveItem().isOf( KaleidoscopesConstants.KALEIDOSCOPE_ITEM))
        {
//            BlockPos blockPos = BlockPos.ofFloored( client.player.getX(), client.player.getEyeY(), client.player.getZ() );
//            float f = LightmapTextureManager.getBrightness( client.player.getWorld().getDimension(), client.player.getWorld().getLightLevel( blockPos ) );
//            int i = ColorHelper.fromFloats( 1.0F, f, f, f );
//            float g = 4.0F;
//            float h = -1.0F;
//            float j = 1.0F;
//            float k = -1.0F;
//            float l = 1.0F;
//            float m = -0.5F;
//            float n = -client.player.getYaw() / 64.0F;
//            float o = client.player.getPitch() / 64.0F;
//            Matrix4f matrix4f = matrixStack.get().peek().getPositionMatrix();
//            VertexConsumer vertexConsumer = vertexConsumers.getBuffer( RenderLayer.getBlockScreenEffect( Identifier.of( Kaleidoscopes.ID, "textures/kaleidoscope_background/kaleidoscope_item.png" ) ) );
//            vertexConsumer.vertex( matrix4f, -1.0F, -1.0F, -0.5F ).texture( 4.0F + n, 4.0F + o ).color( i );
//            vertexConsumer.vertex( matrix4f, 1.0F, -1.0F, -0.5F ).texture( 0.0F + n, 4.0F + o ).color( i );
//            vertexConsumer.vertex( matrix4f, 1.0F, 1.0F, -0.5F ).texture( 0.0F + n, 0.0F + o ).color( i );
//            vertexConsumer.vertex( matrix4f, -1.0F, 1.0F, -0.5F ).texture( 4.0F + n, 0.0F + o ).color( i );
        }
    }
}

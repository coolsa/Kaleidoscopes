package xyz.coolsa.kaleidoscopes.client;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;
import xyz.coolsa.kaleidoscopes.Kaleidoscopes;
import xyz.coolsa.kaleidoscopes.KaleidoscopesConstants;

import java.util.function.UnaryOperator;

public class KaleidoscopeHud implements HudElement
{
    private float kaleidoscopeScale;
    private MinecraftClient client;

    public KaleidoscopeHud()
    {
        this.client = MinecraftClient.getInstance();
    }

    @Override
    public void render( DrawContext context, RenderTickCounter tickCounter )
    {
        ClientPlayerEntity clientPlayerEntity = this.client.player;
        float f = tickCounter.getDynamicDeltaTicks();
        this.kaleidoscopeScale = MathHelper.lerp( 0.5F * f, this.kaleidoscopeScale, 1.125F );
        if( this.client.options.getPerspective().isFirstPerson() )
        {
            if( clientPlayerEntity.isUsingItem() && clientPlayerEntity.getActiveItem().isOf( KaleidoscopesConstants.KALEIDOSCOPE_ITEM ) )
            {
                this.setPostEffect( Identifier.of( Kaleidoscopes.ID, "kaleidoscope" ) );
                this.renderKaleidoscopeOverlay( context, this.kaleidoscopeScale );
            }
            else
            {
                this.clearPostEffect();
                this.kaleidoscopeScale = 0.5F;

                for( EquipmentSlot equipmentSlot : EquipmentSlot.values() )
                {
                    ItemStack itemStack = clientPlayerEntity.getEquippedStack( equipmentSlot );
                    EquippableComponent equippableComponent = itemStack.get( DataComponentTypes.EQUIPPABLE );
                    if( equippableComponent != null && equippableComponent.slot() == equipmentSlot && equippableComponent.cameraOverlay().isPresent() )
                    {
                        this.renderOverlay(
                            context,
                            ((Identifier) equippableComponent.cameraOverlay().get()).withPath( (UnaryOperator<String>) (overlayTexture -> "textures/" + overlayTexture + ".png") ),
                            1.0F
                        );
                    }
                }
            }
        }
    }

    private void renderOverlay( DrawContext context, Identifier texture, float opacity )
    {
        int i = ColorHelper.getWhite( opacity );
        context.drawTexture(
            RenderPipelines.GUI_TEXTURED,
            KaleidoscopesConstants.KALEIDOSCOPE_TEXTURE,
            0,
            0,
            0.0F,
            0.0F,
            context.getScaledWindowWidth(),
            context.getScaledWindowHeight(),
            context.getScaledWindowWidth(),
            context.getScaledWindowHeight(),
            i
        );
    }

    private void renderKaleidoscopeOverlay( DrawContext context, float scale )
    {
        float f = Math.min( context.getScaledWindowWidth(), context.getScaledWindowHeight() );
        float h = Math.min( context.getScaledWindowWidth() / f, context.getScaledWindowHeight() / f ) * scale;
        int i = MathHelper.floor( f * h );
        int j = MathHelper.floor( f * h );
        int k = (context.getScaledWindowWidth() - i) / 2;
        int l = (context.getScaledWindowHeight() - j) / 2;
        int m = k + i;
        int n = l + j;
        context.drawTexture( RenderPipelines.GUI_TEXTURED, KaleidoscopesConstants.KALEIDOSCOPE_TEXTURE, k, l, 0.0F, 0.0F, i, j, i, j );
        //        context.drawTexture( RenderPipelines.GUI_TEXTURED, KaleidoscopesConstants.KALEIDOSCOPE_SCOPE, k, l, 0.0F, 0.0F, i, j, i, j );
        context.fill( RenderPipelines.GUI, 0, n, context.getScaledWindowWidth(), context.getScaledWindowHeight(), -16777216 );
        context.fill( RenderPipelines.GUI, 0, 0, context.getScaledWindowWidth(), l, -16777216 );
        context.fill( RenderPipelines.GUI, 0, l, k, n, -16777216 );
        context.fill( RenderPipelines.GUI, m, l, context.getScaledWindowWidth(), n, -16777216 );
    }

    public void setPostEffect( Identifier id )
    {
        MinecraftClient.getInstance().gameRenderer.setPostProcessor( id );
    }

    public void clearPostEffect()
    {
        MinecraftClient.getInstance().gameRenderer.setPostProcessor( null );
        MinecraftClient.getInstance().gameRenderer.togglePostProcessorEnabled();
    }
    // Referenced from InGameOverlayRenderer
    public static void renderFireOverlay( MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        Sprite sprite = ModelBaker.FIRE_1.getSprite();
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer( RenderLayer.getFireScreenEffect(sprite.getAtlasId()));
        float f = sprite.getMinU();
        float g = sprite.getMaxU();
        float h = (f + g) / 2.0F;
        float i = sprite.getMinV();
        float j = sprite.getMaxV();
        float k = (i + j) / 2.0F;
        float l = sprite.getUvScaleDelta();
        float m = MathHelper.lerp(l, f, h);
        float n = MathHelper.lerp(l, g, h);
        float o = MathHelper.lerp(l, i, k);
        float p = MathHelper.lerp(l, j, k);
        float q = 1.0F;

        for (int r = 0; r < 2; r++) {
            matrices.push();
            float s = -0.5F;
            float t = 0.5F;
            float u = -0.5F;
            float v = 0.5F;
            float w = -0.5F;
            matrices.translate(-(r * 2 - 1) * 0.24F, -0.3F, 0.0F);
            matrices.multiply( RotationAxis.POSITIVE_Y.rotationDegrees((r * 2 - 1) * 10.0F));
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();
            vertexConsumer.vertex(matrix4f, -0.5F, -0.5F, -0.5F).texture(n, p).color(1.0F, 1.0F, 1.0F, 0.9F);
            vertexConsumer.vertex(matrix4f, 0.5F, -0.5F, -0.5F).texture(m, p).color(1.0F, 1.0F, 1.0F, 0.9F);
            vertexConsumer.vertex(matrix4f, 0.5F, 0.5F, -0.5F).texture(m, o).color(1.0F, 1.0F, 1.0F, 0.9F);
            vertexConsumer.vertex(matrix4f, -0.5F, 0.5F, -0.5F).texture(n, o).color(1.0F, 1.0F, 1.0F, 0.9F);
            matrices.pop();
        }
    }
}

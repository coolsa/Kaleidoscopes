package xyz.coolsa.kaleidoscopes.client;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import xyz.coolsa.kaleidoscopes.Kaleidoscopes;

public class KaleidoscopeClient implements ClientModInitializer
{
    public static final Identifier KALEIDOSCOPE_HUD = Identifier.of( Kaleidoscopes.ID, "hud" );
    public static final RenderPipeline KALEIDOSCOPE_RENDERING = RenderPipelines.register(
        RenderPipeline.builder(RenderPipelines.POSITION_TEX_COLOR_SNIPPET)
            .withLocation( Identifier.of(Kaleidoscopes.ID, "pipeline/kaleidoscope_textured"))
            .withVertexShader(Identifier.of(Kaleidoscopes.ID, "core/kaleidoscope_textured"))
            .withFragmentShader(Identifier.of(Kaleidoscopes.ID, "core/kaleidoscope_textured"))
            .withSampler("Sampler0")
            .withBlend( BlendFunction.TRANSLUCENT)
            .withDepthTestFunction( DepthTestFunction.NO_DEPTH_TEST)
            .withVertexFormat(VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS)
            .withDepthWrite(false)
            .build()
    );

    @Override
    public void onInitializeClient()
    {
        this.initializeClient();
    }
    public static void initializeClient()
    {
        HudElementRegistry.attachElementAfter( VanillaHudElements.MISC_OVERLAYS, KALEIDOSCOPE_HUD, new KaleidoscopeHud() );
    }

}

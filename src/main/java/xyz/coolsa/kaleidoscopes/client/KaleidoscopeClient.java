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
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import xyz.coolsa.kaleidoscopes.Kaleidoscopes;
import xyz.coolsa.kaleidoscopes.KaleidoscopesConstants;
import xyz.coolsa.kaleidoscopes.Utilities;

public class KaleidoscopeClient implements ClientModInitializer
{
    public static final Identifier KALEIDOSCOPE_HUD = Identifier.of( Kaleidoscopes.ID, "hud" );
    public static final SoundEvent KALEIDOSCOPE_START_USING = registerSound( "kaleidoscope_start_sound" );
    public static final SoundEvent KALEIDOSCOPE_STOP_USING = registerSound( "kaleidoscope_stop_sound" );
    public static final RenderPipeline POSITION_SKY = RenderPipelines.register(
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

    public static SoundEvent registerSound( String name )
    {
        SoundEvent sound = SoundEvent.of( Identifier.of( Kaleidoscopes.ID, name ) );
        Registry.register( Registries.SOUND_EVENT, sound.id(), sound );
        return sound;
    }
}

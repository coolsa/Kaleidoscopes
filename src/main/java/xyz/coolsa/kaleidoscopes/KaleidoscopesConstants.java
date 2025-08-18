package xyz.coolsa.kaleidoscopes;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import xyz.coolsa.kaleidoscopes.client.KaleidoscopeHud;
import xyz.coolsa.kaleidoscopes.items.KaleidoscopeItem;

public class KaleidoscopesConstants
{
    public static final Item KALEIDOSCOPE_ITEM = Utilities.registerItem( "kaleidoscope_item", KaleidoscopeItem::new, new KaleidoscopeItem.Settings() );
    public static final SoundEvent KALEIDOSCOPE_START_USING = Utilities.registerSound( "kaleidoscope_start_sound" );
    public static final SoundEvent KALEIDOSCOPE_STOP_USING = Utilities.registerSound( "kaleidoscope_stop_sound" );
    public static final Identifier KALEIDOSCOPE_SCOPE = Identifier.ofVanilla("textures/misc/spyglass_scope.png");
    public static final Identifier KALEIDOSCOPE_TEXTURE = Identifier.ofVanilla("textures/misc/spyglass_scope.png");
    public static final Identifier KALEIDOSCOPE_HUD = Identifier.of( Kaleidoscopes.ID, "hud" );
    public static final RenderPipeline GUI_TEXTURED = RenderPipelines.register( RenderPipeline.builder(
            RenderPipelines.TRANSFORMS_AND_PROJECTION_SNIPPET )
        .withVertexShader( "core/testing" )
        .withFragmentShader( "core/testing" )
        .withSampler("Sampler0")
        .withBlend( BlendFunction.TRANSLUCENT)
        .withVertexFormat( VertexFormats.POSITION_TEXTURE_COLOR, VertexFormat.DrawMode.QUADS)
        .withLocation( "pipeline/fire_screen_effect" )
        .build()
    );
    public static final RenderPipeline KALEIDOSCOPE_SHADER = RenderPipelines.register( RenderPipeline.builder(
            RenderPipelines.POSITION_TEX_COLOR_SNIPPET )
        .withVertexShader( "core/kaleidoscope" )
        .withFragmentShader( "core/kaleidoscope" )
        .withLocation( "pipeline/fire_screen_effect" ).build()
    );

    public static void initialize()
    {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.TOOLS ).register((itemGroup) -> itemGroup.add( KaleidoscopesConstants.KALEIDOSCOPE_ITEM));
        Kaleidoscopes.LOGGER.info( "Kaleidoscopes initialized" );
    }

    public static void initializeClient()
    {
        HudElementRegistry.attachElementAfter( VanillaHudElements.MISC_OVERLAYS, KALEIDOSCOPE_HUD, new KaleidoscopeHud() );
        Kaleidoscopes.LOGGER.info( "" + GUI_TEXTURED );
    }
}

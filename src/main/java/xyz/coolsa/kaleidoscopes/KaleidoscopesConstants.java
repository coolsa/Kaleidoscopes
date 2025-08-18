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
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import xyz.coolsa.kaleidoscopes.client.KaleidoscopeHud;
import xyz.coolsa.kaleidoscopes.items.KaleidoscopeItem;

public class KaleidoscopesConstants
{
    public static final Item KALEIDOSCOPE_ITEM = Utilities.registerItem( "kaleidoscope_item", KaleidoscopeItem::new, new KaleidoscopeItem.Settings() );
    public static final Identifier KALEIDOSCOPE_TEXTURE = Identifier.of(Kaleidoscopes.ID, "textures/misc/spyglass_scope.png");


    public static void initialize()
    {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.TOOLS ).register((itemGroup) -> itemGroup.add( KaleidoscopesConstants.KALEIDOSCOPE_ITEM));
        Kaleidoscopes.LOGGER.info( "Kaleidoscopes initialized" );
    }

}

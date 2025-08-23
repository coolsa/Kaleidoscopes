package xyz.coolsa.kaleidoscopes;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import xyz.coolsa.kaleidoscopes.items.KaleidoscopeItem;

public class KaleidoscopesConstants
{
    public static final Item KALEIDOSCOPE_ITEM = Utilities.registerItem( "kaleidoscope_item", KaleidoscopeItem::new, new KaleidoscopeItem.Settings() );
    public static final Identifier KALEIDOSCOPE_TEXTURE = Identifier.of( Kaleidoscopes.ID, "textures/misc/spyglass_scope.png" );
    public static final SoundEvent KALEIDOSCOPE_START_USING = Utilities.registerSound( "kaleidoscope_start_sound" );
    public static final SoundEvent KALEIDOSCOPE_STOP_USING = Utilities.registerSound( "kaleidoscope_stop_sound" );
    public static final ComponentType<NbtComponent> KALEIDOSCOPE_OVERLAY_COMPONENT = Utilities.registerComponent(
        "kaleidoscope_overlay",
        builder -> builder.codec( NbtComponent.CODEC ).packetCodec( NbtComponent.PACKET_CODEC )
    );


    public static void initialize()
    {
        ItemGroupEvents.modifyEntriesEvent( ItemGroups.TOOLS )
            .register( ( itemGroup ) -> itemGroup.add( KaleidoscopesConstants.KALEIDOSCOPE_ITEM ) );
        Kaleidoscopes.LOGGER.info( "Kaleidoscopes initialized" );
    }

}

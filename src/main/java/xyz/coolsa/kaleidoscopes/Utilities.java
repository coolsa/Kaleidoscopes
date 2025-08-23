package xyz.coolsa.kaleidoscopes;

import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Utilities
{

    public static Item registerItem( String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings )
    {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of( RegistryKeys.ITEM, Identifier.of( Kaleidoscopes.ID, name ) );

        // Create the item instance.
        Item item = itemFactory.apply( settings.registryKey( itemKey ) );

        // Register the item.
        Registry.register( Registries.ITEM, itemKey, item );

        return item;
    }

    public static SoundEvent registerSound( String name )
    {
        SoundEvent sound = SoundEvent.of( Identifier.of( Kaleidoscopes.ID, name ) );
        Registry.register( Registries.SOUND_EVENT, sound.id(), sound );
        return sound;
    }

    public static <T> ComponentType<T> registerComponent( String id, UnaryOperator<ComponentType.Builder<T>> builderOperator )
    {

        return Registry.register( Registries.DATA_COMPONENT_TYPE, Identifier.of( Kaleidoscopes.ID, id), ((ComponentType.Builder) builderOperator.apply( ComponentType.builder() )).build() );
    }
}

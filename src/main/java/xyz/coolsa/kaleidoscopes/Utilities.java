package xyz.coolsa.kaleidoscopes;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Function;

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
}

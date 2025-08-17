package xyz.coolsa.kaleidoscopes.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import xyz.coolsa.kaleidoscopes.KaleidoscopesConstants;

public class Client implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        KaleidoscopesConstants.initializeClient();
    }
}

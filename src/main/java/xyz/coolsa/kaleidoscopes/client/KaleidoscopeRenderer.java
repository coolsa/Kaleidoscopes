package xyz.coolsa.kaleidoscopes.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class KaleidoscopeRenderer
{
    // TODO: Add post-effect item type.
    public static void setPostEffect(){
        MinecraftClient.getInstance().gameRenderer.setPostProcessor( Identifier.ofVanilla("spider"));
    }
    // TODO: Stop using item clears the post effect.

}

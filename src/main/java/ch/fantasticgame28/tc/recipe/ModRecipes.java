package ch.fantasticgame28.tc.recipe;

import ch.fantasticgame28.tc.TechnicalCreation;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {

    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(TechnicalCreation.MOD_ID, HeaterRecipe.Serializer.ID),
                HeaterRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(TechnicalCreation.MOD_ID, HeaterRecipe.Type.ID),
                HeaterRecipe.Type.INSTANCE);
    }
}

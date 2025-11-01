package site.scalarstudios.shh.recipe;

import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.server.ReloadableServerResources;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.List;

@SuppressWarnings("null")
public class SHHRecipeDisabler {
    @SubscribeEvent
    public void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new SimplePreparableReloadListener<>() {
            @SuppressWarnings("null")
            @Override
            protected Object prepare(ResourceManager manager, ProfilerFiller profiler) {
                return new Object();
            }

            @SuppressWarnings("null")
            @Override
            protected void apply(Object unused, ResourceManager manager, ProfilerFiller profiler) {
                ReloadableServerResources resources = event.getServerResources();
                RecipeManager recipeManager = resources.getRecipeManager();

                // Remove only the listed recipes (exact ids).
                String[] targets = new String[] {
                    "actuallyadditions:advanced_coil",
                    "actuallyadditions:empowerer",
                    "actuallyadditions:iron_casing",
                    "actuallyadditions:wood_casing",
                    "enderio:alloy_smelting/dark_steel_ingot",
                    "enderio:alloy_smelting/end_steel_ingot",
                    "enderio:alloy_smelting/energetic_alloy_ingot",
                    "enderio:alloy_smelting/vibrant_alloy_ingot",
                    "enderio:basic_capacitor",
                    "enderio:dark_steel_ingot_with_coal",
                    "enderio:ensouled_chassis",
                    "enderio:void_chassis"
                };

                List<RecipeHolder<?>> kept = recipeManager.getRecipes().stream()
                    .filter(holder -> {
                        ResourceLocation id = holder.id();
                        String full = id.toString();
                        if (!full.startsWith("enderio:") && !full.startsWith("actuallyadditions:")) return true; // Keep recipes from other mods
                        for (String t : targets) {
                            if (full.equals(t)) {
                                System.out.println("Removing Recipe: " + full);
                                return false;
                            }
                        }
                        return true;
                    })
                    .toList();

                recipeManager.replaceRecipes(kept);
            }
        });
    }
}

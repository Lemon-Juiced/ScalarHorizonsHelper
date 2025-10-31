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

                // Remove ONLY these two Ender IO alloy smelter recipes:
                // data/enderio/recipe/alloy_smelting/dark_steel_ingot.json
                // data/enderio/recipe/dark_steel_ingot_with_coal.json
                ResourceLocation dark1 = ResourceLocation.tryParse("enderio:alloy_smelting/dark_steel_ingot");
                ResourceLocation dark2 = ResourceLocation.tryParse("enderio:dark_steel_ingot_with_coal");

                List<RecipeHolder<?>> kept = recipeManager.getRecipes().stream()
                    .filter(holder -> {
                        ResourceLocation id = holder.id();
                        if (!"enderio".equals(id.getNamespace())) return true; // keep non-EnderIO recipes
                        boolean isTarget = (dark1 != null && dark1.equals(id)) || (dark2 != null && dark2.equals(id));
                        if (isTarget) {
                            System.out.println("[SHH] Removing EnderIO alloy-smelter recipe: " + id);
                            return false;
                        }
                        return true;
                    })
                    .toList();

                recipeManager.replaceRecipes(kept);
            }
        });
    }
}

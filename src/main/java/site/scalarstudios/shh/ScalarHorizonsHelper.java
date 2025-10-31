package site.scalarstudios.shh;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import site.scalarstudios.shh.creativetab.SHHCreativeTab;
import site.scalarstudios.shh.item.SHHItems;
import site.scalarstudios.shh.recipe.SHHRecipeDisabler;

@Mod(ScalarHorizonsHelper.MODID)
public class ScalarHorizonsHelper {
    public static final String MODID = "shh";

    public ScalarHorizonsHelper(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register Items
        SHHItems.register(modEventBus);

        // Register Creative Tab
        SHHCreativeTab.register(modEventBus);
        modEventBus.addListener(SHHCreativeTab::registerTabs);

        // Register recipe disabler to listen for reload events
        NeoForge.EVENT_BUS.register(new SHHRecipeDisabler());

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {}

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}

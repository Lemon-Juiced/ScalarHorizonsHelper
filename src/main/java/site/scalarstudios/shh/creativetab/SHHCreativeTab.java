package site.scalarstudios.shh.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.shh.ScalarHorizonsHelper;
import site.scalarstudios.shh.item.SHHItems;

public class SHHCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ScalarHorizonsHelper.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SHH_TAB = CREATIVE_MODE_TABS.register("shh_items", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.shh.items"))
            .icon(() -> new ItemStack(SHHItems.AURIC_CRYSTAL.get()))
            .build());


    public static void registerTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == SHH_TAB.get()) {
            event.accept(SHHItems.AURIC_CRYSTAL.get());
            event.accept(SHHItems.CUPRIC_CRYSTAL.get());
            event.accept(SHHItems.SAWDUST.get());
            event.accept(SHHItems.TINY_AURIC_CRYSTAL.get());
            event.accept(SHHItems.VOID_CRYSTAL_DUST.get());
        }
    }

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

package site.scalarstudios.shh.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import site.scalarstudios.shh.ScalarHorizonsHelper;

import java.util.function.Supplier;

public class SHHItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(ScalarHorizonsHelper.MODID);

    // Crafting Items
    public static final Supplier<Item> AURIC_CRYSTAL = ITEMS.register("auric_crystal", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> CUPRIC_CRYSTAL = ITEMS.register("cupric_crystal", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> PROTOENDER_ALLOY_INGOT = ITEMS.register("protoender_alloy_ingot", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> SAWDUST = ITEMS.register("sawdust", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> TINY_AURIC_CRYSTAL = ITEMS.register("tiny_auric_crystal", () -> new Item(new Item.Properties()));
    public static final Supplier<Item> VOID_CRYSTAL_DUST = ITEMS.register("void_crystal_dust", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

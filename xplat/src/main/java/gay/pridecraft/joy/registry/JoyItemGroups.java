package gay.pridecraft.joy.registry;

import gay.pridecraft.joy.Pivot;
import gay.pridecraft.joy.block.BlahajBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.function.Supplier;

import static gay.pridecraft.joy.block.BlahajBlocks.BLAHAJ_ITEM;
import static gay.pridecraft.joy.block.BlahajBlocks.PRIDE_ITEMS;

public final class JoyItemGroups {
    public static final ItemGroup JOY_BEDS = register("beds",
        JoyBlocks.GAY_BED.asItem()::getDefaultStack,
        (displayContext, entries) -> {
            entries.add(JoyBlocks.GAY_BED);
            entries.add(JoyBlocks.ENBY_BED);
            entries.add(JoyBlocks.INTERSEX_BED);
            entries.add(JoyBlocks.AGENDER_BED);
            entries.add(JoyBlocks.BIGENDER_BED);
            entries.add(JoyBlocks.BISEXUAL_BED);
            entries.add(JoyBlocks.MLM_BED);
            entries.add(JoyBlocks.ACE_BED);
            entries.add(JoyBlocks.ARO_BED);
            entries.add(JoyBlocks.APLATONIC_BED);
            entries.add(JoyBlocks.GENDERFLUID_BED);
            entries.add(JoyBlocks.PAN_BED);
            entries.add(JoyBlocks.TRANS_BED);
            entries.add(JoyBlocks.AROACE_BED);
            entries.add(JoyBlocks.LESBIAN_BED);
            entries.add(JoyBlocks.PROGRESS_BED);
        });

    public static final ItemGroup JOY_CANDLES = register("candles",
        JoyBlocks.GAY_CANDLE.asItem()::getDefaultStack,
        (displayContext, entries) -> {

            entries.add(JoyBlocks.QUEER_CANDLE);
            entries.add(JoyBlocks.GAY_CANDLE);
            entries.add(JoyBlocks.ENBY_CANDLE);
            entries.add(JoyBlocks.INTERSEX_CANDLE);
            entries.add(JoyBlocks.AGENDER_CANDLE);
            entries.add(JoyBlocks.BIGENDER_CANDLE);
            entries.add(JoyBlocks.BISEXUAL_CANDLE);
            entries.add(JoyBlocks.MLM_CANDLE);
            entries.add(JoyBlocks.ACE_CANDLE);
            entries.add(JoyBlocks.ARO_CANDLE);
            entries.add(JoyBlocks.APLATONIC_CANDLE);
            entries.add(JoyBlocks.GENDERFLUID_CANDLE);
            entries.add(JoyBlocks.PAN_CANDLE);
            entries.add(JoyBlocks.TRANS_CANDLE);
            entries.add(JoyBlocks.AROACE_CANDLE);
            entries.add(JoyBlocks.LESBIAN_CANDLE);
            entries.add(JoyBlocks.PROGRESS_CANDLE);

        });
    public static final ItemGroup JOY_ENTITIES = register("entities",
        Items.BEEHIVE::getDefaultStack,
        (displayContext, entries) -> {

            entries.add(JoyItems.SOCK_FOX_SPAWN_EGG);
            entries.add(JoyItems.BII_SPAWN_EGG);
            entries.add(JoyItems.ENBEE_SPAWN_EGG);
            entries.add(JoyItems.TRANS_BEE_SPAWN_EGG);
            entries.add(JoyItems.TREE_SPAWN_EGG);
            entries.add(JoyItems.FROG_SPAWN_EGG);
            entries.add(JoyItems.SNIFFER_SPAWN_EGG);

        });

    public static final ItemGroup PLUSHIES = register("plushies",
        BLAHAJ_ITEM::getDefaultStack,
        (displayContext, entries) -> {
            entries.add(BlahajBlocks.GRAY_SHARK_ITEM);
            entries.add(BlahajBlocks.BLAHAJ_ITEM);
            entries.add(BlahajBlocks.BLAVINGAD_ITEM);
            entries.add(BlahajBlocks.BREAD_ITEM);
            entries.add(BlahajBlocks.BROWN_BEAR_ITEM);

            for (Item item : PRIDE_ITEMS) {
                entries.add(new ItemStack(item));
            }
        });
    public static final ItemGroup JOY_MISC = register("misc",
        JoyItems.PRIDE_BRUSH::getDefaultStack,
        (displayContext, entries) -> {
            entries.add(JoyItems.TOTEM_OF_PRIDE);
            entries.add(JoyItems.PRIDE_BRUSH);
            entries.add(JoyItems.PRIDE_ELYTRA);
        });

    public static final ItemGroup FOOD = register("food",
        () -> new ItemStack(JoyBlocks.PRIDE_CAKE),
        (ctx, entries) -> {
            entries.add(JoyBlocks.PRIDE_CAKE);
        });

    public static final ItemGroup JOY_BUNDLES = register("bundles",
        JoyItems.PROGRESS_BUNDLE::getDefaultStack,
        (ctx, entries) -> JoyItems.BUNDLES.forEach(entries::add));

    public static final ItemGroup FLOWERS = register("flowers",
        () -> new ItemStack(JoyBlocks.TRANS_ALLIUM),
        (ctx, entries) -> {
            entries.add(JoyBlocks.PINK_ALLIUM);
            entries.add(JoyBlocks.BLUE_ALLIUM);
            entries.add(JoyBlocks.WHITE_ALLIUM);
            entries.add(JoyBlocks.TRANS_ALLIUM);
        }
    );

    public static final ItemGroup ROSETTES = register("rosettes",
        JoyItems.PLURAL_ROSETTE::getDefaultStack,
        (ctx, entries) -> JoyItems.ROSETTES.forEach(entries::add));

    public static final ItemGroup DYES = register("dyes",
        JoyItems.PROGRESS_DYE::getDefaultStack,
        (ctx, entries) -> {
            entries.add(Items.WHITE_DYE);
            entries.add(Items.ORANGE_DYE);
            entries.add(Items.MAGENTA_DYE);
            entries.add(Items.LIGHT_BLUE_DYE);
            entries.add(Items.YELLOW_DYE);
            entries.add(Items.LIME_DYE);
            entries.add(Items.PINK_DYE);
            entries.add(Items.GRAY_DYE);
            entries.add(Items.LIGHT_GRAY_DYE);
            entries.add(Items.CYAN_DYE);
            entries.add(Items.PURPLE_DYE);
            entries.add(Items.BLUE_DYE);
            entries.add(Items.BROWN_DYE);
            entries.add(Items.GREEN_DYE);
            entries.add(Items.RED_DYE);
            entries.add(Items.BLACK_DYE);
            JoyItems.PRIDE_DYES.forEach(entries::add);
        });

    private static ItemGroup register(String name, Supplier<ItemStack> icon, ItemGroup.EntryCollector collector) {
        return Pivot.INSTANCE.register(RegistryKeys.ITEM_GROUP, name, Pivot.itemGroupBuilder()
            .displayName(Text.translatable("itemgroup.joy." + name))
            .icon(icon)
            .entries(collector)
            .build()
        );
    }

    public static void init() {
        // Joy.LOGGER.info("Making the gay items in" + Joy.MOD_ID, "have a group...");
    }
}

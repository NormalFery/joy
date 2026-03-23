package gay.pridecraft.joy.fabric;

import gay.pridecraft.joy.Pivot;
import gay.pridecraft.joy.config.Config;
import gay.pridecraft.joy.entity.PrideSlimeEntity;
import gay.pridecraft.joy.fabric.entity.SpawnModifier;
import gay.pridecraft.joy.registry.JoyAxolotlVariants;
import gay.pridecraft.joy.registry.JoyEntities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.world.Heightmap;

public class Joy implements ModInitializer {
    public static final String MOD_ID = "joy";

    @Override
    public void onInitialize() {
        // i'm sorry, i don't know how to do this in any other way :sob: -Fery
        SpawnRestriction.register(
            JoyEntities.PRIDE_SLIME,
            SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            (type, world, spawnReason, pos, random) -> SlimeEntity.canSpawn((EntityType<SlimeEntity>)(EntityType<?>)type, world, spawnReason, pos, random)
        );
        BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            SpawnGroup.MONSTER,
            JoyEntities.PRIDE_SLIME,
            100,
            1,
            4
        );
        Pivot.init();
        JoyAxolotlVariants.init();
        if (Config.mobSpawning) SpawnModifier.modifySpawning();

        // MidnightConfig.init(Joy.MOD_ID, Config.class);
        registerEntityAttributes();
    }

    private void registerEntityAttributes() {
        // TODO: move to common
        FabricDefaultAttributeRegistry.register(JoyEntities.SOCK_FOX, FoxEntity.createFoxAttributes());

        FabricDefaultAttributeRegistry.register(JoyEntities.BII, BeeEntity.createBeeAttributes());
        FabricDefaultAttributeRegistry.register(JoyEntities.ENBEE, BeeEntity.createBeeAttributes());
        FabricDefaultAttributeRegistry.register(JoyEntities.TRANS_BEE, BeeEntity.createBeeAttributes());
        FabricDefaultAttributeRegistry.register(JoyEntities.TREE, BeeEntity.createBeeAttributes());

        FabricDefaultAttributeRegistry.register(JoyEntities.FROG, FrogEntity.createFrogAttributes());

        FabricDefaultAttributeRegistry.register(JoyEntities.SNIFFER, SnifferEntity.createSnifferAttributes());
        FabricDefaultAttributeRegistry.register(JoyEntities.PRIDE_SLIME, PrideSlimeEntity.createPrideSlimeAttributes().build());
    }
}

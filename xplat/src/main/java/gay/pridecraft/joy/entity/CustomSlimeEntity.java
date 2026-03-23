package gay.pridecraft.joy.entity;


import gay.pridecraft.joy.JoyUtil;
import gay.pridecraft.joy.registry.JoyEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtString;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class CustomSlimeEntity {
    public static class PrideSlimeEntity extends SlimeEntity {

        public PrideSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
            super(entityType, world);
        }
        public static DefaultAttributeContainer.Builder createPrideSlimeAttributes() {
            return SlimeEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0);
        }

        private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(PrideSlimeEntity.class, TrackedDataHandlerRegistry.INTEGER);

        protected void initDataTracker(DataTracker.Builder builder) {
            super.initDataTracker(builder);
            builder.add(VARIANT, 0);
        }

        public PrideSlimeVariant getVariant() {
            return PrideSlimeVariant.values()[this.dataTracker.get(VARIANT)];
        }

        public void setVariant(PrideSlimeVariant variant) {
            this.dataTracker.set(VARIANT, variant.ordinal());
            System.out.println("Variant set to: " + this.getVariant().asString());
        }

        @Override
        public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                     SpawnReason spawnReason, EntityData entityData) {
            EntityData data = super.initialize(world, difficulty, spawnReason, entityData);
            PrideSlimeVariant[] variants = PrideSlimeVariant.values();
            this.setVariant(variants[this.random.nextInt(variants.length)]);
            System.out.println("initialize called, variant: " + this.getVariant().asString());
            return data;
        }

        @Override
        public void writeCustomDataToNbt(NbtCompound nbt) {
            super.writeCustomDataToNbt(nbt);
            nbt.putString("Variant", this.getVariant().asString());
        }

        @Override
        public void readCustomDataFromNbt(NbtCompound nbt) {
            super.readCustomDataFromNbt(nbt);
            if (nbt.contains("Variant", NbtElement.STRING_TYPE)) {
                PrideSlimeVariant variant = PrideSlimeVariant.CODEC
                    .parse(NbtOps.INSTANCE, NbtString.of(nbt.getString("Variant")))
                    .result()
                    .orElse(PrideSlimeVariant.GAY);
                this.setVariant(variant);
            } else {
                // No variant in NBT, assign randomly (covers /summon case)
                PrideSlimeVariant[] variants = PrideSlimeVariant.values();
                this.setVariant(variants[this.random.nextInt(variants.length)]);
            }
        }


        @Override
        public void remove(Entity.RemovalReason reason) {
            int i = this.getSize();
            if (!this.getWorld().isClient && i > 1 && this.isDead()) {
                Text text = this.getCustomName();
                boolean bl = this.isAiDisabled();
                float f = this.getDimensions(this.getPose()).width();
                float g = f / 2.0F;
                int j = i / 2;
                int k = 2 + this.random.nextInt(3);

                for (int l = 0; l < k; ++l) {
                    float h = ((float)(l % 2) - 0.5F) * g;
                    float m = ((float)(l / 2) - 0.5F) * g;
                    PrideSlimeEntity child = (PrideSlimeEntity) JoyEntities.PRIDE_SLIME.create(this.getWorld());
                    if (child != null) {
                        if (this.isPersistent()) child.setPersistent();
                        child.setCustomName(text);
                        child.setAiDisabled(bl);
                        child.setInvulnerable(this.isInvulnerable());
                        child.setSize(j, true);
                        child.setVariant(this.getVariant());
                        child.refreshPositionAndAngles(
                            this.getX() + h, this.getY() + 0.5F, this.getZ() + m,
                            this.random.nextFloat() * 360F, 0F);
                        this.getWorld().spawnEntity(child);
                    }
                }
            }
            super.remove(reason); // mixin cancels SlimeEntity's split, so this safely reaches MobEntity
        }


        @Override
        protected RegistryKey<LootTable> getLootTableId() {
            return RegistryKey.of(RegistryKeys.LOOT_TABLE,
                JoyUtil.id("entities/pride_slime_" + this.getVariant().asString()));
        }

    }
}

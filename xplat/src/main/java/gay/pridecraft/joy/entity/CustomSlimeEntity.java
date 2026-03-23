package gay.pridecraft.joy.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SlimeEntity;
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

    }
}

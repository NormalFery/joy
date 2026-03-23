package gay.pridecraft.joy.mixin;

import gay.pridecraft.joy.entity.PrideSlimeEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    @Redirect(
        method = "remove",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z")
    )
    private boolean redirectSpawnSplit(World world, Entity entity) {
        if ((Object) this instanceof PrideSlimeEntity) {
            return false; // suppress vanilla split spawning
        }
        return world.spawnEntity(entity);
    }
}

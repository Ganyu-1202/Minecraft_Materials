// src/main/java/org/materials/materials/entity/CalmingSplashPotionEntity.java
package org.materials.materials;

import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.EntityHitResult;
import javax.annotation.Nonnull;

public class CalmingSplashPotionEntity extends ThrownPotion
{
    public CalmingSplashPotionEntity(EntityType<? extends ThrownPotion> type, Level level)
    {
        super(type, level);
    }

    public CalmingSplashPotionEntity(Level level, LivingEntity thrower)
    {
        super(level, thrower); // 需要注册自定义 EntityType
    }

    @Override
    protected void onHitEntity(@Nonnull EntityHitResult result)
    {
        super.onHitEntity(result);
        if (result.getEntity() instanceof LivingEntity entity)
        {
            entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 320, 0, false, true, true)); // 8秒反胃
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 1280, 0, false, true, true)); // 64秒虚弱
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3000, 4, false, true, true)); // 2.5分钟缓慢V
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 3000, 5, false, true, true)); // 2.5分钟挖掘疲劳VI
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 600, 0, false, true, true)); // 30秒失明
        }
    }
}

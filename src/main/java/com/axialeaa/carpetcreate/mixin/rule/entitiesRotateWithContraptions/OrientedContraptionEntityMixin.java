package com.axialeaa.carpetcreate.mixin.rule.entitiesRotateWithContraptions;

import org.spongepowered.asm.mixin.Mixin;

import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.contraptions.OrientedContraptionEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

@Mixin(OrientedContraptionEntity.class)
public abstract class OrientedContraptionEntityMixin extends AbstractContraptionEntity {

//	@Shadow public abstract void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> key);
//	@Shadow protected Vec3 motionBeforeStall;

	public OrientedContraptionEntityMixin(EntityType<?> entityTypeIn, Level worldIn) {
		super(entityTypeIn, worldIn);
	}

//	@WrapOperation(method = "getViewXRot", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;prevPitch:F"))
//	private float replacePrevPitchWith(OrientedContraptionEntity instance, Operation<Float> original) {
//		return CarpetCreateSettings.entityContraptionRotationFix ? instance.xRotO : original.call(instance);
//	}
//
//	@WrapOperation(method = { "getRotationState", "writeAdditional", "getViewXRot" }, at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;pitch:F"))
//	private float replacePitchWith(OrientedContraptionEntity instance, Operation<Float> original) {
//		return CarpetCreateSettings.entityContraptionRotationFix ? instance.getXRot() : original.call(instance);
//	}
//
//	@WrapOperation(method = "getViewYRot", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;prevYaw:F"))
//	private float replacePrevYawWith(OrientedContraptionEntity instance, Operation<Float> original) {
//		return CarpetCreateSettings.entityContraptionRotationFix ? instance.yRotO : original.call(instance);
//	}
//
//	@WrapOperation(method = { "getRotationState", "writeAdditional", "getViewYRot" }, at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;yaw:F"))
//	private float replaceYawWith(OrientedContraptionEntity instance, Operation<Float> original) {
//		return CarpetCreateSettings.entityContraptionRotationFix ? instance.getYRot() : original.call(instance);
//	}
//
//	@WrapOperation(method = "readAdditional", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;pitch:F", ordinal = 0))
//	private void test0(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.setXRot(value);
//		else original.call(instance, value);
//	}
//
//	@WrapOperation(method = "readAdditional", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;yaw:F", ordinal = 0))
//	private void test1(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.setYRot(value);
//		else original.call(instance, value);
//	}
//
//	@WrapOperation(method = "readAdditional", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;prevYaw:F", ordinal = 0))
//	private void test10(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix) {
//			instance.setYRot(instance.getYRot() + yawFromVector(motionBeforeStall));
//			instance.yRotO = instance.getYRot();
//		}
//		else original.call(instance, value);
//	}
//
//	@Inject(method = "startAtYaw", at = @At("TAIL"))
//	private void test2(float yaw, CallbackInfo ci) {
//		this.setYRot(yaw);
//		this.yRotO = yaw;
//	}
//
//	@WrapOperation(method = "updateOrientation", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;prevPitch:F", ordinal = 0))
//	private void test30(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.xRotO = instance.getXRot();
//		else original.call(instance, value);
//	}
//
//	@WrapOperation(method = "updateOrientation", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;prevYaw:F", ordinal = 0))
//	private void test40(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.yRotO = instance.getYRot();
//		else original.call(instance, value);
//	}
//
//	@WrapOperation(method = "updateOrientation", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;pitch:F", ordinal = 1))
//	private void test3(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.setXRot(value);
//		else original.call(instance, value);
//	}
//
//	@WrapOperation(method = "updateOrientation", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;yaw:F", ordinal = 1))
//	private void test4(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.setYRot(value);
//		else original.call(instance, value);
//	}
//
//	@ModifyExpressionValue(method = "updateOrientation", at = @At(value = "INVOKE", target = "Ljava/util/UUID;equals(Ljava/lang/Object;)Z"))
//	private boolean test40(boolean original) {
//		if (original && CarpetCreateSettings.entityContraptionRotationFix) {
//			this.setXRot(-this.getXRot());
//			this.setYRot(this.getYRot() + 180);
//			return false;
//		}
//		return original;
//	}
//
//	@WrapOperation(method = "updateOrientation", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/core/Direction$Axis;isVertical()Z"), to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;getDeltaMovement()Lnet/minecraft/world/phys/Vec3;")), at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;prevYaw:F"))
//	private void test41(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.yRotO = instance.getYRot();
//		else original.call(instance, value);
//	}
//
//	@WrapOperation(method = "updateOrientation", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/core/Direction$Axis;isVertical()Z"), to = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;getXsize()D")), at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;yaw:F", ordinal = 1))
//	private void test42(OrientedContraptionEntity instance, float value, Operation<Void> original) {
//		if (CarpetCreateSettings.entityContraptionRotationFix)
//			instance.setYRot(value);
//		else original.call(instance, value);
//	}
//
//	@Inject(method = "updateOrientation", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;yawFromVector(Lnet/minecraft/world/phys/Vec3;)F", ordinal = 0))
//	private void test43(boolean rotationLock, boolean wasStalled, Entity riding, boolean isOnCoupling, CallbackInfoReturnable<Boolean> cir) {
//		if (CarpetCreateSettings.entityContraptionRotationFix && this.getYRot() > 0)
//			this.setYRot(getYRot() + 360);
//	}
//
//	@WrapOperation(method = "updateOrientation", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;getXsize()D"), to = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F")), at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/contraptions/OrientedContraptionEntity;yaw:F"))
//	private float test44(OrientedContraptionEntity instance, Operation<Float> original) {
//		return CarpetCreateSettings.entityContraptionRotationFix ? instance.getYRot() : original.call(instance);
//	}

}

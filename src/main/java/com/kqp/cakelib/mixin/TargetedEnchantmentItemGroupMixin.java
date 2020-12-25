package com.kqp.cakelib.mixin;

import com.kqp.cakelib.enchantment.TargetedEnchantment;
import java.util.Iterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(EnchantedBookItem.class)
public class TargetedEnchantmentItemGroupMixin {

    @Inject(
        method = "appendStacks",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemGroup;" +
            "containsEnchantments(Lnet/minecraft/enchantment/EnchantmentTarget;)Z"
        ),
        locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private void injectAppendStacks(
        ItemGroup group,
        DefaultedList<ItemStack> stacks,
        CallbackInfo callbackInfo,
        Iterator iterator,
        Enchantment enchantment
    ) {
        if (enchantment instanceof TargetedEnchantment) {
            if (((TargetedEnchantment) enchantment).getItemGroup() == group) {
                stacks.add(
                    EnchantedBookItem.forEnchantment(
                        new EnchantmentLevelEntry(
                            enchantment,
                            enchantment.getMaxLevel()
                        )
                    )
                );
            }
        }
    }
}

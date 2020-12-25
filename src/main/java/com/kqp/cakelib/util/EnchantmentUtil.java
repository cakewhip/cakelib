package com.kqp.cakelib.util;

import java.util.Optional;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

public class EnchantmentUtil {

    public static int getLevel(
        Optional<Enchantment> optionalEnchantment,
        ItemStack itemStack
    ) {
        return optionalEnchantment
            .map(
                enchantment ->
                    EnchantmentHelper.getLevel(enchantment, itemStack)
            )
            .orElse(0);
    }

    public static int getMaxLevel(Optional<Enchantment> optionalEnchantment) {
        return optionalEnchantment
            .map(enchantment -> enchantment.getMaxLevel())
            .orElse(0);
    }
}

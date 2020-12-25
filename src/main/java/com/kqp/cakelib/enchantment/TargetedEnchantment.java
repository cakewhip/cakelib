package com.kqp.cakelib.enchantment;

import java.util.function.Predicate;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public abstract class TargetedEnchantment extends Enchantment {

    public final Predicate<Item> itemTargetPredicate;

    protected TargetedEnchantment(
        Rarity weight,
        Predicate<Item> itemTargetPredicate,
        EquipmentSlot[] slotTypes
    ) {
        super(weight, null, slotTypes);
        this.itemTargetPredicate = itemTargetPredicate;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return itemTargetPredicate.test(stack.getItem());
    }

    public abstract ItemGroup getItemGroup();
}

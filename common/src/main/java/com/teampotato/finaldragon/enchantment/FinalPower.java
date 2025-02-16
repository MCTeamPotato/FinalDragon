package com.teampotato.finaldragon.enchantment;

import com.teampotato.finaldragon.FinalDragon;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FinalPower extends Enchantment {
    public static final ResourceLocation ID = new ResourceLocation(FinalDragon.MOD_ID, "final_power");

    public FinalPower() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}
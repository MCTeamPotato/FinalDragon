package com.teampotato.finaldragon.enchantment;

import com.teampotato.finaldragon.FinalDragon;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class FinalPower extends Enchantment {
    public static final Enchantment INSTANCE = new FinalPower();

    public static void register() {
        Registry.register(BuiltInRegistries.ENCHANTMENT, new ResourceLocation(FinalDragon.MOD_ID, "final_power"), INSTANCE);
    }

    public FinalPower() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }
}
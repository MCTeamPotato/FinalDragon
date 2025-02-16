package com.teampotato.finaldragon;

import com.teampotato.finaldragon.enchantment.FinalPower;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;

@Mod(FinalDragon.MOD_ID)
public final class FinalDragon {
    public static final String MOD_ID = "finaldragon";

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final RegistryObject<Enchantment> FINAL_POWER = ENCHANTMENTS.register("final_power", FinalPower::new);

    public FinalDragon() {
        LogManager.getLogger().warn("Hello from Final Dragon!");
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    public static boolean entityDontHaveFinalPower(LivingEntity livingSource) {
        Enchantment enchantment = FINAL_POWER.get();
        ItemStack mainHand = livingSource.getMainHandItem();
        ItemStack offHand = livingSource.getOffhandItem();
        return EnchantmentHelper.getItemEnchantmentLevel(enchantment, mainHand) == 0 && EnchantmentHelper.getItemEnchantmentLevel(enchantment, offHand) == 0;
    }

    public static final class Config {
        public static final ForgeConfigSpec COMMON_CONFIG;
        public static final ForgeConfigSpec.BooleanValue DISABLE_SHIELD;

        static {
            ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
            builder.push("FinalDragon");
            DISABLE_SHIELD = builder.define("WhetherFinalPowerIgnoreShields", true);
            builder.pop();
            COMMON_CONFIG = builder.build();
        }
    }
}
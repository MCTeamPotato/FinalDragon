package com.teampotato.finaldragon;

import com.teampotato.finaldragon.enchantment.FinalPower;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(FinalDragon.MOD_ID)
public final class FinalDragon {
    public static final String MOD_ID = "finaldragon";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, MOD_ID);
    public static final RegistryObject<Enchantment> FINAL_POWER = ENCHANTMENTS.register("final_power", FinalPower::new);

    public FinalDragon(FMLJavaModLoadingContext context) {
        LogManager.getLogger().warn("Hello from Final Dragon!");
        ENCHANTMENTS.register(context.getModEventBus());
        context.registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    public static boolean entityHasFinalPower(LivingEntity livingSource) {
        Map<Enchantment, Integer> mainhand = livingSource.getMainHandItem().getAllEnchantments();
        Map<Enchantment, Integer> offhand = livingSource.getOffhandItem().getAllEnchantments();
        Enchantment enchantment = FINAL_POWER.get();
        return mainhand.containsKey(enchantment) || offhand.containsKey(enchantment);
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
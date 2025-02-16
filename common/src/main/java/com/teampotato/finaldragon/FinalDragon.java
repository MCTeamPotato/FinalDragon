package com.teampotato.finaldragon;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teampotato.finaldragon.enchantment.FinalPower;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public final class FinalDragon {
    public static final String MOD_ID = "finaldragon";
    public static boolean disableShield;

    public static boolean entityHaveFinalPower(@NotNull LivingEntity livingSource) {
        ItemStack mainHand = livingSource.getMainHandItem();
        ItemStack offHand = livingSource.getOffhandItem();
        return EnchantmentHelper.getItemEnchantmentLevel(FinalPower.INSTANCE, mainHand) != 0 || EnchantmentHelper.getItemEnchantmentLevel(FinalPower.INSTANCE, offHand) != 0;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void init() {
        FinalPower.register();
        File configPath = FabricLoader.getInstance().getConfigDir().toFile();
        configPath.mkdirs();
        File config = new File(configPath, MOD_ID + ".json");
        if (!config.exists()) {
            try {
                FileWriter writer = writeFile(config);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(config));
            disableShield = JsonParser.parseReader(reader).getAsJsonObject().get("WhetherFinalPowerIgnoresShields").getAsBoolean();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static @NotNull FileWriter writeFile(File configFile) throws IOException {
        JsonObject defaultConfig = new JsonObject();
        defaultConfig.addProperty("WhetherFinalPowerIgnoresShields", true);
        FileWriter writer = new FileWriter(configFile);
        writer.write(defaultConfig.toString());
        return writer;
    }
}

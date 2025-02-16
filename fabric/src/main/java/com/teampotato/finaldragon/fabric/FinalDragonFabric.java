package com.teampotato.finaldragon.fabric;

import com.teampotato.finaldragon.FinalDragon;
import net.fabricmc.api.ModInitializer;

public final class FinalDragonFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FinalDragon.init();
    }
}

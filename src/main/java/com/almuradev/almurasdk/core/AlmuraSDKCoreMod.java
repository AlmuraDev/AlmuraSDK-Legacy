/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.core;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.Map;

@SortingIndex(10000)
@MCVersion("1.7.10")
public final class AlmuraSDKCoreMod implements IFMLLoadingPlugin {

    public AlmuraSDKCoreMod() {
        MixinBootstrap.init();
        MixinEnvironment.getCurrentEnvironment().addConfiguration("mixins.almurasdk.json");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                MixinBootstrap.TRANSFORMER_CLASS
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
    }

    @Override
    public String getAccessTransformerClass() {
        return CommonAccessTransformer.class.getName();
    }
}

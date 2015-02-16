package com.almuradev.almurasdk.core;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public class CommonAccessTransformer extends AccessTransformer {

    public static final String CLASSPATH = "com.almuradev.almurasdk.core.CommonAccessTransformer";

    public CommonAccessTransformer() throws IOException {
        super("almurasdk_at.cfg");
    }
}

/**
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.core;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public class CommonAccessTransformer extends AccessTransformer {

    public static final String CLASSPATH = "com.almuradev.almurasdk.core.CommonAccessTransformer";

    public CommonAccessTransformer() throws IOException {
        super("almurasdk_at.cfg");
    }
}

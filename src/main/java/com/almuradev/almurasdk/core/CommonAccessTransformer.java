/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.core;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;

import java.io.IOException;

public final class CommonAccessTransformer extends AccessTransformer {

    public CommonAccessTransformer() throws IOException {
        super("almurasdk_at.cfg");
    }
}

/*
 * This file is part of AlmuraSDK, licensed under the MIT License (MIT).
 *
 * Copyright (c) AlmuraDev <http://beta.almuramc.com/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.almuradev.almurasdk.lang;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LanguageRegistry {

    /*
     * Language -> Key (example: itemGroup.fruit.apple) -> value (example: "Apple")
     */
    private static final Map<Languages, Map<String, String>> languageMap = Maps.newHashMap();

    public static String put(Languages lang, String key, String value) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Attempted to put a key that was null or empty!");
        }
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Attempted to put a value for " + key + " that was null or empty!");
        }

        Map<String, String> keyMap = languageMap.get(lang);
        if (keyMap == null) {
            keyMap = new HashMap<>();
            languageMap.put(lang, keyMap);
        }

        return keyMap.put(key, value);
    }

    public static Map<String, String> get(Languages lang) {
        final Map<String, String> map = languageMap.get(lang);
        return Collections.unmodifiableMap(map == null ? Maps.<String, String>newHashMap() : map);
    }

    public static void injectIntoForge() {
        for (Map.Entry<Languages, Map<String, String>> entry : languageMap.entrySet()) {
            Map<String, String> injectMap = new HashMap<>();
            for (Map.Entry<String, String> keyEntry : entry.getValue().entrySet()) {
                injectMap.put(keyEntry.getKey(), keyEntry.getValue());
            }
            cpw.mods.fml.common.registry.LanguageRegistry.instance().injectLanguage(entry.getKey().value(), (HashMap<String, String>) injectMap);
        }
    }
}

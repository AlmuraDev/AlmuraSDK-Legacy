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
package com.almuradev.almurasdk;

import com.google.common.base.Function;

import javax.annotation.Nullable;

public final class Functions {

    public static Function<Object, String> FUNCTION_STRING_TRANSFORMER = new Function<Object, String>() {
        @Nullable
        @Override
        public String apply(Object input) {
            if (input == null) {
                return "";
            } else if (input.getClass() == String.class) {
                return (String) input;
            } else {
                return input.toString();
            }
        }
    };

    private Functions() {
    }
}

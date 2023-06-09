/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package de.shadowhunt.javascript.nashorn.internal.runtime;

import static de.shadowhunt.javascript.nashorn.internal.codegen.CompilerConstants.staticCallNoLookup;

import de.shadowhunt.javascript.nashorn.internal.codegen.CompilerConstants.Call;

/**
 * A class with static helper methods invoked from generated bytecode for setting values of parameters of variable-arity
 * functions.
 */
public final class ArgumentSetter {
    private ArgumentSetter() {}

    /** Method handle for setting a function argument at a given index in an arguments object. Used from generated bytecode */
    public static final Call SET_ARGUMENT      = staticCallNoLookup(ArgumentSetter.class, "setArgument", void.class, Object.class, ScriptObject.class, int.class);

    /** Method handle for setting a function argument at a given index in an arguments array. Used from generated bytecode */
    public static final Call SET_ARRAY_ELEMENT = staticCallNoLookup(ArgumentSetter.class, "setArrayElement", void.class, Object.class, Object[].class, int.class);


    /**
     * Used from generated bytecode to invoke {@link ScriptObject#setArgument(int, Object)} without having to reorder
     * the arguments on the stack. When we're generating a store into the argument, we first have the value on the
     * stack, and only afterwards load the target object and the index.
     * @param value the value to write at the given argument index.
     * @param arguments the arguments object that we're writing the value to
     * @param key the index of the argument
     */
    public static void setArgument(final Object value, final ScriptObject arguments, final int key) {
        arguments.setArgument(key, value);
    }

    /**
     * Used from generated bytecode to set a variable arity parameter - an array element - without having to reorder
     * the arguments on the stack. When we're generating a store into the array, we first have the value on the
     * stack, and only afterwards load the target array and the index.
     * @param value the value to write at the given argument index.
     * @param arguments the arguments array that we're writing the value to
     * @param key the index of the argument
     */
    public static void setArrayElement(final Object value, final Object[] arguments, final int key) {
        arguments[key] = value;
    }
}

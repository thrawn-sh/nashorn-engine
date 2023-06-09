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

package de.shadowhunt.javascript.nashorn.internal.codegen.types;

import org.objectweb.asm.MethodVisitor;

/**
 * Bitwise operations not supported by all types
 */
interface BytecodeBitwiseOps {

    /**
     * Pop and logically shift the two values on top of the stack (steps, value)
     * right and push the result on the stack
     *
     * @param method method visitor
     * @return result type
     */
    Type shr(MethodVisitor method);

    /**
     * Pop and arithmetically shift of the two values on top of the stack
     * (steps, value) right and push the result on the stack
     *
     * @param method method visitor
     * @return result type
     */
    Type sar(MethodVisitor method);

    /**
     * Pop and logically shift of the two values on top of the stack (steps,
     * value) left and push the result on the stack
     *
     * @param method method visitor
     * @return result type
     */
    Type shl(MethodVisitor method);

    /**
     * Pop and AND the two values on top of the stack and push the result on the
     * stack
     *
     * @param method method visitor
     * @return result type
     */
    Type and(MethodVisitor method);

    /**
     * Pop and OR the two values on top of the stack and push the result on the
     * stack
     *
     * @param method method visitor
     * @return result type
     */
    Type or(MethodVisitor method);

    /**
     * Pop and XOR the two values on top of the stack and push the result on the
     * stack
     *
     * @param method method visitor
     * @return result type
     */
    Type xor(MethodVisitor method);

    /**
     * Comparison with int return value, e.g. LCMP.
     *
     * @param method the method visitor
     * @return int return value
     */
    Type cmp(MethodVisitor method);
}

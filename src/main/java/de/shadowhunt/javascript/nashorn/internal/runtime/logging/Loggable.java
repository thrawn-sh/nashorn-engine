/*
 * Copyright (c) 2010, 2014, Oracle and/or its affiliates. All rights reserved.
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
package de.shadowhunt.javascript.nashorn.internal.runtime.logging;

import de.shadowhunt.javascript.nashorn.internal.runtime.Context;

/**
 * Interface implemented by classes that are loggable.
 * Their instances will provide functionality for initializing
 * a logger (usually by asking Global for it, with a reference
 * to this.getClass()) and a method to return the logger in
 * use
 *
 * Typically a class implementing this interface also has the
 * Logger annotation
 *
 * @see Logger
 */
public interface Loggable {
    /**
     * Initialize a logger, by asking Context to get or create it
     * and then keep it in a table by name
     *
     * @param context context
     * @return the initialized logger
     */
    public DebugLogger initLogger(final Context context);

    /**
     * Return the logger in use
     * @return logger
     */
    public DebugLogger getLogger();
}

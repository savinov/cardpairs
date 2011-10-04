/*
 * CardPairs is an open source project that represents memory game variation with cards.
 * Copyright (C) 2011  Guram Savinov <savinov.guram@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.savinov.cardpairs.model;

/**
 * Represents the board size.
 * 
 * @author Guram Savinov
 * 
 */
public enum BoardSize {

    /** The small board size. */
    SMALL,

    /** The medium board size. */
    MEDIUM,

    /** The large board size. */
    LARGE,

    /** The extra large board size. */
    EXTRA_LARGE;

    /** The default board size constant. */
    public static final BoardSize DEFAULT_SIZE = MEDIUM;

}

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

import java.util.List;

import org.junit.Test;

public class CardTableTest {
    
    @Test
	public void testRandomizeNumbers() {
		System.out.println("Card table testing ...");

		for (BoardSize size : BoardSize.values()) {
			System.out.println();
			System.out.println("Board size: " + size);

			CardTable table = new CardTable(size);

			int rows = table.getRows();
			int cols = table.getCols();
			List<Integer> cardNumbers = table.getCardNumbers();

			int index = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					System.out.printf("%2d ", cardNumbers.get(index++));
				}
				System.out.println();
			}
		}
	}
    
}

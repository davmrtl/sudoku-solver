package com.github.davmrtl.sodokusolver;

import java.awt.geom.Point2D;

/**
 * Created by Davmrtl on 2016-05-11.
 */
public class Grid {
    private int[][] cells = new int[9][9];

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public boolean isValueForCellValid(Point2D cell, int value) {
        int y = (int) cell.getY();
        int x = (int) cell.getX();

        // Test the value in current row
        for (int r = 0; r < 9; r++) {
            if (cells[y][r] == value) {
                return false;
            }
        }

        // Test the value in current column
        for (int c = 0; c < 9; c++) {
            if (cells[c][x] == value) {
                return false;
            }
        }

        // Test the value in current section
        int fromY = 3 * (y / 3);
        int fromX = 3 * (x / 3);
        int toY = fromY + 2;
        int toX = fromX + 2;

        for (int testY = fromY; testY <= toY; testY++) {
            for (int testX = fromX; testX <= toX; testX++) {
                if (cells[testY][testX] == value) {
                    return false;
                }
            }
        }

        // The value is valid for current cell
        return true;
    }

    public Point2D getNextCell(Point2D currentCell) {
        Point2D nextCell = new Point2D.Float();

        nextCell.setLocation(currentCell.getX() + 1, currentCell.getY());

        if (nextCell.getX() > 8) {
            nextCell.setLocation(0, currentCell.getY() + 1);
        }

        if (nextCell.getY() > 8) {
            nextCell = null;
        }

        return nextCell;
    }

    public boolean solveCell(Point2D cell) {
        if (cell == null) {
            return true;
        }

        int y = (int) cell.getY();
        int x = (int) cell.getX();

        // Test if the cell has already a value
        if (cells[y][x] != 0) {
            return solveCell(getNextCell(cell));
        }

        for (int i = 1; i <= 9; i++) {
            boolean valid = isValueForCellValid(cell, i);

            if (!valid) {
                continue;
            }

            cells[y][x] = i;

            boolean solved = solveCell(getNextCell(cell));

            if (solved) {
                return true;
            } else {
                cells[y][x] = 0;
            }
        }

        return false;
    }

    public boolean solve() {
        return solveCell(new Point2D.Float(0, 0));
    }
}

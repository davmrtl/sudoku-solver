package com.github.davmrtl.sodokusolver.runtime;

import com.github.davmrtl.sodokusolver.Grid;
import com.github.davmrtl.sodokusolver.GridSerializer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Davmrtl on 2016-05-11.
 */
public class Tester {

    public static void main(String[] args) throws IOException {
        Grid grid = GridSerializer.fromFile(new File("src\\main\\resources\\easy-grid-1.sud"));

        if (grid.solve()) {
            System.out.println(GridSerializer.toString(grid));
        } else {
            System.out.println("Unable to parse this Sudoku :(");
        }
    }
}

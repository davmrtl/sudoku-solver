package com.github.davmrtl.sodokusolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Davmrtl on 2016-05-11.
 */
public final class GridSerializer {
    private static final int[] gridCharPos = {2, 4, 6, 10, 12, 14, 18, 20, 22};

    private GridSerializer() {
    }

    public static Grid fromFile(File file) throws IOException {
        Grid grid = new Grid();

        int posY = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String line; (line = reader.readLine()) != null; ) {

                if (line.startsWith("|")) {
                    if (line.length() != 25) {
                        throw new IndexOutOfBoundsException("A line must have a 25 chars length");
                    }

                    int[] gridLine = new int[9];

                    for (int i = 0; i < gridCharPos.length; i++) {
                        int pos = gridCharPos[i];

                        gridLine[i] = 0;

                        try {
                            gridLine[i] = Integer.parseInt(line.charAt(pos) + "");
                        } catch (Exception ignored) {
                        }
                    }

                    grid.getCells()[posY] = gridLine;

                    posY++;
                }
            }
        }

        return grid;
    }

    public static String toString(Grid grid) {
        String output = "+-----------------------+\n";

        for (int y = 0; y < grid.getCells().length; y++) {
            int[] gridLine = grid.getCells()[y];

            if (y % 3 == 0 && y > 0) {
                output += "+-------+-------+-------+\n";
            }

            output += "|";

            for (int x = 0; x < gridLine.length; x++) {
                Integer gridLineItem = gridLine[x];

                if (x % 3 == 0 && x > 0) {
                    output += " |";
                }

                if (gridLineItem == 0) {
                    output += "  ";
                } else {
                    output += " " + gridLineItem;
                }
            }

            output += " |\n";
        }

        output += "+-----------------------+\n";

        return output;
    }
}

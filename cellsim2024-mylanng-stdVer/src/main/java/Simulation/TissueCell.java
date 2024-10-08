package Simulation;


import Util.Pair;

import java.util.ArrayList;

import static Util.Calculator.coordFromIndex;
import static Util.Calculator.indexFromCoord;

/**
 * A tissue cell. It wants to grow, but not as much as cancer. Has a chance to turn a dead
 * cell into a live one every time step
 */

public class TissueCell extends Cell{

    /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */

    public TissueCell (Pair coords){
        super (0, coords.getX(), coords.getY(), 1);
    }

    @Override
    public void interactNeighbors (ArrayList<Cell> cellList) {
        int x = this.getX();
        int y = this.getY();

        ArrayList <Cell> tissueList = validCell(cellList, x, y);
        ArrayList<Integer> DeadCellIndices = addCellID (tissueList, 0);

        if(DeadCellIndices.size() > 0){
            double chances = Math.random();
            if(chances >= 0.3){
                int replace = getRandomCellIndex(DeadCellIndices);
                cellList.set (replace, new TissueCell(coordFromIndex(replace)));
            }
        }
    }
}

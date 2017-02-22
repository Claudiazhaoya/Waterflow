/**
 * Sample Test Program
 * This file will be replaced for final Grading! Do not submit it.
 * This file does NOT contains all the test cases and corner cases.
 */
public class Test {
    /**
     * It's a sample main, you can leave it blank.
     */

    public static void main(String[] args) {
        WaterFlow WF = new WaterFlow();
        if (WF.visual) WF.visualize();

        WF.determineFlow();
       /* for(int i = 0; i < WF.getRows(); i++) {
            for(int j = 0; j < WF.getColumns(); j++) {
                System.out.printf("%-5d", WF.getReachTimeGrid()[i][j]);
            }
            System.out.println();
        }*/
        WF.earliestFlowPath();

        System.out.print("Earliest Path: ");
        for (Cell c : WF.earliestPath)
            System.out.print("[" + c.row + "," + c.column + "] ");
        System.out.println();
        System.out.println(WF.isReached());

        for (int i = 0; i < WF.getReachTimeGrid().length; i++) {
            for (int j = 0; j < WF.getReachTimeGrid()[0].length; j++) {
                System.out.printf("%d\t", WF.reachTimeGrid[i][j]);
            }
            System.out.println();
        }

    }

}


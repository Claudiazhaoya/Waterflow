/**
 * WaterFlow
 * Water flow problem which start every empty cell in the first row.
 *
 * @author sun515
 * @CS login ID: sun515
 * @PSO section: 18
 * @date 2016.09.13
 */


import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class WaterFlow {
    public int rows; //number of rows of the grid
    public int columns; //number of columns of grid
    public int[][] delayTimeGrid; //time of water in the grid, and 0 means the water is blocked
    public int[][] reachTimeGrid; //time of water flow reach that point
    public boolean[][] earliestPathGrid; //a boolean grid that identify the shortest path. For visualization purpose
    public List<Cell> earliestPath; //The earliest flow path
    public WaterFlowVisualization visualization;
    public Scanner s = new Scanner(System.in);
    public boolean visual = true;
    public boolean isReached = false;
    boolean hasReached = true;
    public int time;
    public int row;
    public int col;
    public LinkedQueue<Object> Q1, Q2, Q3;


    //TODO: add variables that you need

    /* The default constructor
     * Read Input from terminal, do not modify it
     * */
    public WaterFlow() {
        //get inputs
        rows = s.nextInt();
        columns = s.nextInt();
        delayTimeGrid = new int[rows][columns];
        reachTimeGrid = new int[rows][columns];

        earliestPathGrid = new boolean[rows][columns];

        Q1 = new LinkedQueue<>();
        Q2 = new LinkedQueue<>();
        Q3 = new LinkedQueue<>();

        earliestPath = new LinkedList<>();


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                delayTimeGrid[i][j] = s.nextInt();
            }
        }
        //TODO: initial the variables
    }


    /**
     * Update the water flow once.
     *
     * @return Null
     */
    public void flow() {
        // Don't Change this part, it's visualize Part
        if (visual) try {
            Thread.sleep(100);
            //count++;
            //if (count == 5) Thread.sleep(20000);
            visualization.repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //TODO: Start your implementation of progress here
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (delayTimeGrid[i][j] == 0) {
                    reachTimeGrid[i][j] = -1;
                } else {
                    if (delayTimeGrid[0][j] != 0) {
                        reachTimeGrid[0][j] = 0;
                    }
                    reachTimeGrid[i][j] = -9;
                }
            }
        }

        for (int j = 0; j < columns; j++) {
            if (delayTimeGrid[0][j] == 1) {
                Q1.enqueue(new Cell(0, j));
            } else if (delayTimeGrid[0][j] == 2) {
                Q2.enqueue(new Cell(0, j));
            } else if (delayTimeGrid[0][j] == 3) {
                Q3.enqueue(new Cell(0, j));
            }
        }

        time = 1;
        while (!Q1.isEmpty() || !Q2.isEmpty() || !Q3.isEmpty()) {
            while (!Q1.isEmpty() && time - 1 == reachTimeGrid[((Cell) Q1.peek()).getRow()][((Cell) Q1.peek()).getColumn()]) {
                row = ((Cell) Q1.peek()).getRow();
                col = ((Cell) Q1.peek()).getColumn();
                if (col - 1 > 0 && reachTimeGrid[row][col - 1] == -9) {
                    reachTimeGrid[row][col - 1] = time;
                    if (delayTimeGrid[row][col - 1] == 1) {
                        Q1.enqueue(new Cell(row, col - 1));
                    } else if (delayTimeGrid[row][col - 1] == 2) {
                        Q2.enqueue(new Cell(row, col - 1));
                    } else if (delayTimeGrid[row][col - 1] == 3) {
                        Q3.enqueue(new Cell(row, col - 1));
                    }
                }

                if (col + 1 < columns && reachTimeGrid[row][col + 1] == -9) {
                    reachTimeGrid[row][col + 1] = time;
                    if (delayTimeGrid[row][col + 1] == 1) {
                        Q1.enqueue(new Cell(row, col + 1));
                    } else if (delayTimeGrid[row][col + 1] == 2) {
                        Q2.enqueue(new Cell(row, col + 1));
                    } else if (delayTimeGrid[row][col + 1] == 3) {
                        Q3.enqueue(new Cell(row, col + 1));
                    }
                }

                if (row + 1 < rows && reachTimeGrid[row + 1][col] == -9) {
                    reachTimeGrid[row + 1][col] = time;
                    if (delayTimeGrid[row + 1][col] == 1) {
                        Q1.enqueue(new Cell(row + 1, col));
                    } else if (delayTimeGrid[row + 1][col] == 2) {
                        Q2.enqueue(new Cell(row + 1, col));
                    } else if (delayTimeGrid[row + 1][col] == 3) {
                        Q3.enqueue(new Cell(row + 1, col));
                    }
                }
                Q1.dequeue();
            }
            while (!Q2.isEmpty() && time - 2 == reachTimeGrid[((Cell) Q2.peek()).getRow()][((Cell) Q2.peek()).getColumn()]) {
                row = ((Cell) Q2.peek()).getRow();
                col = ((Cell) Q2.peek()).getColumn();

                if (col - 1 > 0 && reachTimeGrid[row][col - 1] == -9) {
                    reachTimeGrid[row][col - 1] = time;
                    if (delayTimeGrid[row][col - 1] == 1) {
                        Q1.enqueue(new Cell(row, col - 1));
                    } else if (delayTimeGrid[row][col - 1] == 2) {
                        Q2.enqueue(new Cell(row, col - 1));
                    } else if (delayTimeGrid[row][col - 1] == 3) {
                        Q3.enqueue(new Cell(row, col - 1));
                    }
                }

                if (col + 1 < columns && reachTimeGrid[row][col + 1] == -9) {
                    reachTimeGrid[row][col + 1] = time;
                    if (delayTimeGrid[row][col + 1] == 1) {
                        Q1.enqueue(new Cell(row, col + 1));
                    } else if (delayTimeGrid[row][col + 1] == 2) {
                        Q2.enqueue(new Cell(row, col + 1));
                    } else if (delayTimeGrid[row][col + 1] == 3) {
                        Q3.enqueue(new Cell(row, col + 1));
                    }
                }
                if (row + 1 < rows && reachTimeGrid[row + 1][col] == -9) {
                    reachTimeGrid[row + 1][col] = time;
                    if (delayTimeGrid[row + 1][col] == 1) {
                        Q1.enqueue(new Cell(row + 1, col));
                    } else if (delayTimeGrid[row + 1][col] == 2) {
                        Q2.enqueue(new Cell(row + 1, col));
                    } else if (delayTimeGrid[row + 1][col] == 3) {
                        Q3.enqueue(new Cell(row + 1, col));
                    }
                }
                Q2.dequeue();
            }
            while (!Q3.isEmpty() && time - 3 == reachTimeGrid[((Cell) Q3.peek()).getRow()][((Cell) Q3.peek()).getColumn()]) {
                row = ((Cell) Q3.peek()).getRow();
                col = ((Cell) Q3.peek()).getColumn();
                if (col - 1 > 0 && reachTimeGrid[row][col - 1] == -9) {
                    reachTimeGrid[row][col - 1] = time;
                    if (delayTimeGrid[row][col - 1] == 1) {
                        Q1.enqueue(new Cell(row, col - 1));
                    } else if (delayTimeGrid[row][col - 1] == 2) {
                        Q2.enqueue(new Cell(row, col - 1));
                    } else if (delayTimeGrid[row][col - 1] == 3) {
                        Q3.enqueue(new Cell(row, col - 1));
                    }
                }

                if (col + 1 < columns && reachTimeGrid[row][col + 1] == -9) {
                    reachTimeGrid[row][col + 1] = time;
                    if (delayTimeGrid[row][col + 1] == 1) {
                        Q1.enqueue(new Cell(row, col + 1));
                    } else if (delayTimeGrid[row][col + 1] == 2) {
                        Q2.enqueue(new Cell(row, col + 1));
                    } else if (delayTimeGrid[row][col + 1] == 3) {
                        Q3.enqueue(new Cell(row, col + 1));
                    }

                }
                if (row + 1 < rows && reachTimeGrid[row + 1][col] == -9) {
                    reachTimeGrid[row + 1][col] = time;
                    if (delayTimeGrid[row + 1][col] == 1) {
                        Q1.enqueue(new Cell(row + 1, col));
                    } else if (delayTimeGrid[row + 1][col] == 2) {
                        Q2.enqueue(new Cell(row + 1, col));
                    } else if (delayTimeGrid[row + 1][col] == 3) {
                        Q3.enqueue(new Cell(row + 1, col));
                    }
                }
                Q3.dequeue();
            }
            time = time + 1;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (reachTimeGrid[i][j] == -9) {
                    reachTimeGrid[i][j] = -1;
                }
            }
        }
        hasReached = false;
    }

    /**
     * Calculate the water flow until it ends.
     */
    public void determineFlow() {
        //TODO: Fill in the condition of the while loop
        while (hasReached) {
            this.flow();
        }
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getDelayTimeGrid() {
        return delayTimeGrid;
    }

    /**
     * Create the Visualization of the water flow
     */
    public void visualize() {
        visualization = new WaterFlowVisualization(this);

        JFrame frame = new JFrame("Water Flow Visualization");
        frame.add(visualization);
        visualization.init();
        visualization.start();
        frame.setSize(visualization.getSize());
        frame.setVisible(true);
    }

    /**
     * Find one shortest path and update the shortestGrid.
     */
    public void earliestFlowPath() {
        //TODO: implement the earliest path method
        int smallestSum = Integer.MAX_VALUE;
        int index = 0;
        int counter = 0;
        boolean hasExit = false;
        for (int j = 0; j < columns; j++) {
            if (reachTimeGrid[rows - 1][j] != -1 && (reachTimeGrid[rows - 1][j] + delayTimeGrid[rows - 1][j]) < smallestSum) {
                hasExit = true;
                smallestSum = reachTimeGrid[rows - 1][j] + delayTimeGrid[rows - 1][j];
                index = j;
            }
        }
        if (!hasExit) {
            return;
        }
        earliestPath.add(0, new Cell(rows - 1, index));
        time = reachTimeGrid[rows - 1][index];
        //System.out.println("Initial Row:" + earliestPath.get(0).getRow());
        // System.out.println("Initial Col:" + earliestPath.get(0).getColumn());
        while (time != 0) {
            row = earliestPath.get(counter).getRow();
            col = earliestPath.get(counter).getColumn();
            //System.out.println("Row:" + row);
            //System.out.println("Col:" + col);
            //System.out.println("Counter" + counter);

            if (col - 1 >= 0 && reachTimeGrid[row][col - 1] != -1 && reachTimeGrid[row][col - 1] + delayTimeGrid[row][col - 1] == reachTimeGrid[row][col]) {
                earliestPath.add(new Cell(row, col - 1));
                time = time - delayTimeGrid[row][col - 1];


            } else if (row - 1 >= 0 && reachTimeGrid[row - 1][col] != -1 && reachTimeGrid[row - 1][col] + delayTimeGrid[row - 1][col] == reachTimeGrid[row][col]) {
                earliestPath.add(new Cell(row - 1, col));
                time = time - delayTimeGrid[row - 1][col];


            } else if (col + 1 < columns && reachTimeGrid[row][col + 1] != -1 && reachTimeGrid[row][col + 1] + delayTimeGrid[row][col + 1] == reachTimeGrid[row][col]) {
                earliestPath.add(new Cell(row, col + 1));
                time = time - delayTimeGrid[row][col + 1];
            }
            counter++;
        }

        getEarliestPathGrid();
    }


    public int[][] getReachTimeGrid() {
        return reachTimeGrid;
    }

    public boolean isReached() {
        for (int j = 0; j < columns; j++) {
            if (reachTimeGrid[rows - 1][j] != -1) {
                isReached = true;
            }
        }
        return isReached;
    }


    public boolean[][] getEarliestPathGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = earliestPath.size() - 1; k >= 0; k--) {
                    if (i == earliestPath.get(k).getRow() && j == earliestPath.get(k).getColumn()) {
                        earliestPathGrid[i][j] = true;
                    }
                }
            }
        }
        return earliestPathGrid;
    }
}


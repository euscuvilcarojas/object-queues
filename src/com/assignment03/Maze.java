package com.assignment03;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Maze {
    private boolean isFound = false;
    private boolean isExecuted = false;

    private Queue<Point> solutionQueue = new Queue<Point>();
    private int startingRow;
    private int startingColumn;
    private char [][]charMaze;

    public Maze(String fileName){
        try{
            List<String> fileLines = Files.readAllLines(Paths.get(".\\src\\maze_files\\" + fileName), StandardCharsets.UTF_8);

            int totalRows = Integer.parseInt(fileLines.get(0).split(" ")[0].substring(1,3));
            int totalColumns = Integer.parseInt(fileLines.get(0).split(" ")[1]);

            setStartingRow(Integer.parseInt(fileLines.get(1).split(" ")[0]));
            setStartingColumn(Integer.parseInt(fileLines.get(1).split(" ")[1]));

            charMaze = new char[totalRows][totalColumns];
            for(int i = 0; i < totalRows + 2; i++){
                for(int j = 0; j < totalColumns; j++){
                    charMaze[i][j] = fileLines.get(i+2).charAt(j);
                }
            }
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }

    public Maze(int startingRow, int startingColumn, char[][] existingMaze){
        setStartingRow(startingRow);
        setStartingColumn(startingColumn);
        setCharMaze(existingMaze);

        if(getCharMaze()[GetStartingPoint().getRow()][GetStartingPoint().getColumn()] == 'W' || getCharMaze()[GetStartingPoint().getRow()][GetStartingPoint().getColumn()] == 'E'){
            throw new UnsupportedOperationException();
        }
    }

    public Point GetStartingPoint(){
        return new Point(getStartingRow(),getStartingColumn(), null);
    }

    public int GetRowLength(){
        return getCharMaze().length;
    }

    public int GetColumnLength(){
        return getCharMaze()[0].length;
    }

    public String PrintMaze(){
        String mazePrinted = "";
        for(int i=0; i < GetRowLength(); i++){
            for(int j=0; j < GetColumnLength(); j++){
                mazePrinted = mazePrinted + getCharMaze()[i][j];
            }
            mazePrinted = mazePrinted + "\n";
        }
        return mazePrinted.stripTrailing();
    }

    // Queue Related solution
    public String BreadthFirstSearch(){
        isExecuted = true;
        Stack<Point> localSolutionStack = new Stack<Point>();
        getSolutionQueue().Enqueue(GetStartingPoint());
        while(!getSolutionQueue().IsEmpty() && !isFound){
            checkPointForSolutionsOnPoint(getSolutionQueue().Dequeue(), getSolutionQueue());
        }
        if(getSolutionQueue().IsEmpty() && !isFound){
            return "No exit found in maze!\n\n" + PrintMaze();
        }
        localSolutionStack = GetPathToFollow();
        return "Path to follow from Start " + GetStartingPoint().toString() + " to Exit " + getSolutionQueue().getTail().getElement().toString() + " - " + localSolutionStack.getSize() +" steps:\n" + printingDotsForSolution(localSolutionStack) + PrintMaze();
    }

    public Stack<Point> GetPathToFollow(){
        Stack<Point> solutionStackClone = new Stack<>();
        if(getSolutionQueue().IsEmpty() && !isExecuted){
            throw new UnsupportedOperationException();
        }

        if (getSolutionQueue().IsEmpty() && isExecuted){
            return solutionStackClone;
        }

        Point currentPoint = getSolutionQueue().getTail().getElement();

        while(currentPoint.getParentPoint() != null){
            solutionStackClone.Push(currentPoint);
            currentPoint = currentPoint.getParentPoint();
        }
        solutionStackClone.Push(currentPoint);

        return solutionStackClone;
    }

    //region helper methods
    public String printingDotsForSolution(Stack<Point> solutionStack){
        String pathFollowed = "";
        Node<Point> currentPoint = solutionStack.getHead();
        for(int index = 0; index < solutionStack.getSize(); index++){
            pathFollowed = pathFollowed + currentPoint.getElement().toString() + "\n";
            if(index != solutionStack.getSize() - 1){
                getCharMaze()[currentPoint.getElement().getRow()][currentPoint.getElement().getColumn()] = '.';
            }
            currentPoint = currentPoint.getNext();
        }
        return pathFollowed;
    }

    public void evaluateNeighborNode(int row, int column, Point parentPoint){
        if(charMaze[row][column] == 'E'){
            isFound = true;
        } else {
            charMaze[row][column] = 'V';
        }
        getSolutionQueue().Enqueue(new Point(row , column, parentPoint));
    }

    public void checkPointForSolutionsOnPoint(Point currentPoint, Queue<Point> solutionQueue){
        int row = currentPoint.getRow();
        int column = currentPoint.getColumn();

//       ` if(charMaze[row + 1][column] != 'W' && charMaze[row + 1][column] != 'V'){
//            evaluateNeighborNode(row + 1,column, currentPoint);
//        } else if(charMaze[row][column + 1] != 'W' && charMaze[row][column + 1] != 'V'){
//            evaluateNeighborNode(row,column + 1, currentPoint);
//        } else if(charMaze[row][column - 1] != 'W' && charMaze[row][column - 1] != 'V'){
//            evaluateNeighborNode(row,column - 1, currentPoint);
//        } else if(charMaze[row - 1][column] != 'W' && charMaze[row - 1][column] != 'V') {
//            evaluateNeighborNode(row - 1,column, currentPoint);
//        } else {
//            if(!solutionQueue.IsEmpty()){
//                solutionQueue.Dequeue();
//            }
//        }`

        if(charMaze[row + 1][column] != 'W' && charMaze[row + 1][column] != 'V'){
            evaluateNeighborNode(row + 1,column, currentPoint);
        }
        if(charMaze[row][column + 1] != 'W' && charMaze[row][column + 1] != 'V'){
            evaluateNeighborNode(row,column + 1, currentPoint);
        }
        if(charMaze[row][column - 1] != 'W' && charMaze[row][column - 1] != 'V'){
            evaluateNeighborNode(row,column - 1, currentPoint);
        }
        if(charMaze[row - 1][column] != 'W' && charMaze[row - 1][column] != 'V') {
            evaluateNeighborNode(row - 1,column, currentPoint);
        }
//        if(!solutionQueue.IsEmpty()){
//            solutionQueue.Dequeue();
//        }

    }
    //endregion

    //region self-generated-code
    public char[][] GetMaze(){
        return getCharMaze();
    }

    public int getStartingRow() {
        return startingRow;
    }

    public void setStartingRow(int startingRow) {
        this.startingRow = startingRow;
    }

    public int getStartingColumn() {
        return startingColumn;
    }

    public void setStartingColumn(int startingColumn) {
        this.startingColumn = startingColumn;
    }

    public char[][] getCharMaze() {
        return charMaze;
    }

    public void setCharMaze(char[][] charMaze) {
        this.charMaze = charMaze;
    }

    public Queue<Point> getSolutionQueue() {
        return solutionQueue;
    }

    public void setSolutionQueue(Queue<Point> solutionQueue) {
        this.solutionQueue = solutionQueue;
    }
    //endregion
}
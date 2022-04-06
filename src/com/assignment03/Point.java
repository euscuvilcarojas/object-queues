package com.assignment03;

public class Point {
    private int column;
    private int row;

    public Point getParentPoint() {
        return parentPoint;
    }

    public void setParentPoint(Point parentPoint) {
        this.parentPoint = parentPoint;
    }

    private Point parentPoint;

    public Point (int row, int column){
        setRow(row);
        setColumn(column);
    }

    public Point (int row, int column, Point parentPoint){
        setRow(row);
        setColumn(column);
        setParentPoint(parentPoint);
    }

    @Override
    public String toString(){
        return String.format("[%d, %d]", getRow(), getColumn());
    }

    // Self generated code

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}


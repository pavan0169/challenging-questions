package maze;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Maze 
{
    static final int ROAD = 0;
    static final int WALL = 1;
    static final int START = 2;
    static final int EXIT = 3;
    static final int PATH = 4;

    int[][] maze;
    boolean[][] visited;
    Point start;
    Point end;

    public Maze(File maze) throws FileNotFoundException 
    {
        String fileText = "";
        try (Scanner input = new Scanner(maze)) 
        {
            while (input.hasNextLine()) 
            {
                fileText += input.nextLine() + "\n";
            }
        }
        initializeMaze(fileText);
    }

    private void initializeMaze(String text) 
    {
        if (text == null || (text = text.trim()).length() == 0) 
        {
            throw new IllegalArgumentException("no data");
        }

        String[] lines = text.split("[\r]?\n");
        
        maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];
        
        //double old=Integer.MAX_VALUE;
        for (int row = 0; row < getHeight(); row++) 
        {
            if (lines[row].length() != getWidth()) 
            {
                throw new IllegalArgumentException("wrong length");
            }
            
            for (int col = 0; col < getWidth(); col++) 
            {
                if (lines[row].charAt(col) == '#')
                    maze[row][col] = WALL;
                else if (lines[row].charAt(col) == 'S') 
                {
                    maze[row][col] = START;
                    start = new Point(row, col);
                } 
                else if (lines[row].charAt(col) == 'E')
                {
                    maze[row][col] = EXIT;
                	end = new Point(row, col); 
                } 
                else
                    maze[row][col] = ROAD;
            }
        }
    }

    public void printPath(List<Point> path) 
    {
        int[][] tempMaze = Arrays.stream(maze).map(int[]::clone).toArray(int[][]::new);
        
        for (Point coordinate : path) 
        {
            if (isStart(coordinate.getX(), coordinate.getY()) || isExit(coordinate.getX(), coordinate.getY())) 
            {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = PATH;
        }
        System.out.println(toString(tempMaze));
    }

    
    
    
    public String toString(int[][] maze) 
    {
        StringBuilder result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (int row = 0; row < getHeight(); row++) 
        {
            for (int col = 0; col < getWidth(); col++) 
            {
                if (maze[row][col] == ROAD) 
                {
                    result.append(' ');
                } 
                else if (maze[row][col] == WALL) 
                {
                    result.append('#');
                } 
                else if (maze[row][col] == START) 
                {
                    result.append('S');
                } 
                else if (maze[row][col] == EXIT) 
                {
                    result.append('E');
                } 
                else 
                {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void reset() {
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], false);
    }
    

    
    
    
    
    
    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Point getEntry() {
        return start;
    }

    public Point getExit() {
        return end;
    }

    public boolean isExit(int x, int y) 
    {
    	return maze[x][y] == EXIT;
        //return x == end.getX() && y == end.getY();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isWall(int row, int col) {
        return maze[row][col] == WALL;
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }

}

package maze;

import java.io.File;
import java.util.List;

public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        File maze = new File("E:\\maze3.txt");
        execute(maze);
    }

    private static void execute(File file) throws Exception 
    {
        Maze maze = new Maze(file);
        bfs(maze);
    }

    private static void bfs(Maze maze) 
    {
    	BFSSolution bfs = new BFSSolution();
        List<Point> path = bfs.solve(maze);

        maze.printPath(path);
        maze.reset();
    }

}
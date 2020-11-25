package maze;

import java.util.*;


public class BFSSolution 
{
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Point> solve(Maze maze) 
    {
        LinkedList<Point> nextToVisit = new LinkedList<>();
        Point start = maze.getEntry();
        nextToVisit.add(start);

        while (!nextToVisit.isEmpty()) 
        {
        	Point cur = nextToVisit.remove();

            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) 
            {
                continue;
            }

            if (maze.isWall(cur.getX(), cur.getY()))
            {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            } 

            if (maze.isExit(cur.getX(), cur.getY())) 
            {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) 
            {
            	Point coordinate = new Point(cur.getX() + direction[0], cur.getY() + direction[1], cur);
                nextToVisit.add(coordinate);
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    public List<Point> backtrackPath(Point cur) 
    {
        List<Point> path = new ArrayList<>();
        Point iter = cur;

        while (iter != null) 
        {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}

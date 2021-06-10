package channelTalk;

import java.util.LinkedList;
import java.util.Queue;

public class CTSolution2 {

    private static int N, M;

    public int solution(int n, int m, int x, int y) {
        N = n;
        M = m;
        Robot robot = new Robot(new Point(0, 0), 0, Direction.EAST, 0);
        return find(robot, new Point(x, y));
    }

    public int find(Robot robot, Point destination) {
        Queue<Robot> que = new LinkedList<>();
        que.add(robot);
        while (!que.isEmpty()) {
            Robot currentRobot = que.poll();
            if (currentRobot.isArrived(destination)) {
                return currentRobot.getMovedCount();
            }

            Robot robot1 = currentRobot.firstCommandMove();
            if (isValid(robot1)) {
                que.add(robot1);
            }
            Robot robot2 = currentRobot.secondCommandMove();
            if (isValid(robot2)) {
                que.add(robot2);
            }
            Robot robot3 = currentRobot.thirdCommandMove();
            if (isValid(robot3)) {
                que.add(robot3);
            }

        }
        return -1;

    }

    private boolean isValid(Robot robot) {
        return robot.getCurrentPoint().isValid(N, M) && !robot.isStuck();
    }

}

class Robot {
    private final Point currentPoint;
    private final int movedCount;
    private final Direction direction;
    private final int stuckCount;

    public Robot(Point currentPoint, int movedCount, Direction direction, int stuckCount) {
        this.currentPoint = currentPoint;
        this.movedCount = movedCount;
        this.direction = direction;
        this.stuckCount = stuckCount;
    }


    public boolean isArrived(Point destination) {
        return currentPoint.equals(destination);
    }

    public int getMovedCount() {
        return movedCount;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    private Point nextPoint(Point currentPoint, Direction currentDirection) {
        if (currentDirection == Direction.WEST) {
            return new Point(currentPoint.getX() - 1, currentPoint.getY());
        } else if (currentDirection == Direction.EAST) {
            return new Point(currentPoint.getX() + 1, currentPoint.getY());
        } else if (currentDirection == Direction.NORTH) {
            return new Point(currentPoint.getX(), currentPoint.getY() + 1);
        } else {
            return new Point(currentPoint.getX(), currentPoint.getY() - 1);
        }
    }

    public Robot firstCommandMove() {
        return new Robot(nextPoint(this.currentPoint, this.direction), this.movedCount + 1, turn(this.direction), 0);
    }

    public Robot secondCommandMove() {
        Direction nextDirection = turn(this.direction);
        return new Robot(nextPoint(this.currentPoint, nextDirection), this.movedCount + 1, nextDirection, 0);
    }

    public Robot thirdCommandMove() {
        return new Robot(this.currentPoint, this.movedCount + 1, turn(this.direction), stuckCount + 1);
    }

    public boolean isStuck() {
        return this.stuckCount >= 4;
    }


    private Direction turn(Direction currentDirection) {
        if (currentDirection == Direction.WEST) {
            return Direction.NORTH;
        } else if (currentDirection == Direction.EAST) {
            return Direction.SOUTH;
        } else if (currentDirection == Direction.NORTH) {
            return Direction.EAST;
        } else {
            return Direction.WEST;
        }
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public boolean isValid(int n, int m) {
        return x >= 0 && y >= 0 && x <= n && y <= m;
    }
}


enum Direction {
    WEST,
    EAST,
    NORTH,
    SOUTH
}
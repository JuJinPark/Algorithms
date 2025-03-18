package leetCode.medium;

import java.util.*;

public class CourseSchedule {
    public static void main(String[] args) {
        boolean result = new CourseScheduleSolution().canFinish(3, new int[][]{{0, 1}, {1, 0}});
    }
}

class CourseScheduleSolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return this.firsSolution(numCourses, prerequisites);
    }

    private boolean firsSolution(int numCourses, int[][] prerequisites) {
        /**
         n (numCourses) +  (m number of prerequisites)
         tc - n + m + n + m+n => m+n
         sc - n + + n + m+n = > n+m
         */
        Map<Integer, ScheduleCourse> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            ScheduleCourse course = new ScheduleCourse(i);
            map.put(i, course);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int targetCourseNo = prerequisites[i][0];
            int preCourseNo = prerequisites[i][1];
            ScheduleCourse targetCourse = map.get(targetCourseNo);
            targetCourse.inDegreeCount++;
            ScheduleCourse preCourse = map.get(preCourseNo);
            preCourse.outDegreeCourse.add(targetCourse);
        }


        Queue<ScheduleCourse> queue = new LinkedList();
        for (var entry : map.entrySet()) {
            ScheduleCourse course = entry.getValue();
            if (course.inDegreeCount == 0) {
                queue.offer(course);
            }
        }

        int numberOfCourseTaken = 0;
        while (!queue.isEmpty()) {
            ScheduleCourse toTakeCourse = queue.poll();
            numberOfCourseTaken++;
            for (ScheduleCourse outDegree : toTakeCourse.outDegreeCourse) {
                outDegree.inDegreeCount--;
                if (outDegree.inDegreeCount == 0) {
                    queue.offer(outDegree);
                }
            }
        }

        return numCourses == numberOfCourseTaken;
    }
}

class ScheduleCourse {
    public int inDegreeCount = 0;
    public int courseNo;
    public List<ScheduleCourse> outDegreeCourse = new ArrayList();

    public ScheduleCourse(int courseNo) {
        this.courseNo = courseNo;
    }
}

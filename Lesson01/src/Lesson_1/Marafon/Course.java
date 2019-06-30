package Lesson_1.Marafon;

import Lesson_1.Competitors.Competitor;
import Lesson_1.Obstacles.Obstacle;

public class Course {
    Obstacle[] obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = new Obstacle[obstacles.length];
        for (int i = 0; i < obstacles.length; i++) {
            this.obstacles[i] = obstacles[i];
        }
    }

    public void startCourse(Team team) {
        Competitor[] competitors = team.getCompetitors();
        for (Competitor c : competitors) {
            for (Obstacle o : obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }

}

package Lesson_1.Marafon.Obstacle;

import Lesson_1.Marafon.Competitor.Competitor;
import Lesson_1.Marafon.Competitor.Team;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle... obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team) {
        for (Competitor c : team.getMembers()) {
            for (Obstacle o : obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }
}
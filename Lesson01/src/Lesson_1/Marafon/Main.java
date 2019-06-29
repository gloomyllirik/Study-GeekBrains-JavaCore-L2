package Lesson_1.Marafon;

import Lesson_1.Competitors.*;
import Lesson_1.Obstacles.*;


public class Main {
    public static void main(String[] args) {
        Team team = new Team("Команда", new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"), new Cat("Мурзик"), new Dog("Шарик"));
        Course course = new Course(new Cross(80), new Wall(20), new Wall(1), new Cross(120));
        course.startCourse(team);
        team.getResultsCourse();
    }
}
package Lesson_1.Marafon;

import Lesson_1.Competitors.Competitor;

public class Team {
    String name;
    Competitor[] competitors;

    public Team(String name, Competitor... competitors) {
        this.name = name;
        this.competitors = new Competitor[competitors.length];
        for (int i = 0; i < competitors.length; i++) {
            this.competitors[i] = competitors[i];
        }
    }

    public Competitor[] getCompetitors(){
        return competitors;
    }

    public void getResultsCourse() {
        for (Competitor c : competitors) {
            c.info();
        }
    }
}

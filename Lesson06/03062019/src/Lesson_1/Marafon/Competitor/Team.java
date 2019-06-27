package Lesson_1.Marafon.Competitor;

public class Team {
    private String title;
    private Competitor[] members;

    public Competitor[] getMembers() {
        return members;
    }

    public Team(String title, Competitor...members) {
        this.title = title;
        this.members = members;
    }

    public void showWinners() {
        for(Competitor o: members) {
            if(o.isOnDistance()) {
                o.info();
            }
        }
    }
//
//    public Human getHuman() {
//        return new Human("asd");
//    }
}


package com.foxminded.rodin.formulaone;

public class Racer {

    private String name;
    private String team;
    private String abbreviation;

    public Racer(String name, String team, String abbreviation) {
        super();
        this.name = name;
        this.team = team;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}

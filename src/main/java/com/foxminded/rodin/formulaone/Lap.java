package com.foxminded.rodin.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lap implements Comparable<Lap> {

    private LocalDateTime start;
    private LocalDateTime stop;
    private Duration duration;
    private Racer racer;

    public Lap(LocalDateTime start, LocalDateTime stop, Duration duration, Racer racer) {
        super();
        this.start = start;
        this.stop = stop;
        this.duration = duration;
        this.racer = racer;
    }

    @Override
    public int compareTo(Lap o) {
        if (o == null)
            return -1;
        return duration.compareTo(o.getDuration());
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getStop() {
        return stop;
    }

    public void setStop(LocalDateTime stop) {
        this.stop = stop;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

}

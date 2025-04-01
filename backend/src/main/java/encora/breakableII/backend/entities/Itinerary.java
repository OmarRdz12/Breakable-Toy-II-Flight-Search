package encora.breakableII.backend.entities;

import java.util.List;

public class Itinerary {
    private String duration;
    private List<Segment> segments;

    public String getDuration() {
        return duration;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
}

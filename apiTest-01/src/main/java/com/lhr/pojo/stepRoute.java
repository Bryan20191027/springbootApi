package com.lhr.pojo;

import java.util.ArrayList;
import java.util.List;

public class stepRoute {
    private String instruction;
    private String orientation;
    private String road_name;
    private String step_distance;
    private String duration;
    private String polyline;

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration+"秒";
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public String getStep_distance() {
        return step_distance;
    }

    public void setStep_distance(String step_distance) {
        this.step_distance = step_distance;
    }

    @Override
    public String toString() {
        return "stepRoute{" +
                "instruction='" + instruction + '\'' +
                ", orientation='" + orientation + '\'' +
                ", road_name='" + road_name + '\'' +
                ", step_distance='" + step_distance + '\'' +
                '}';
    }
}

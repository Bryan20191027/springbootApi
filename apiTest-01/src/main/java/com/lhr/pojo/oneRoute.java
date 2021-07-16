package com.lhr.pojo;

import java.util.ArrayList;
import java.util.List;

public class oneRoute {
    private List<stepRoute> route=new ArrayList<>();

    public List<stepRoute> getRoute() {
        return route;
    }

    public void setRoute(stepRoute stepRouteTemp) {
        route.add(stepRouteTemp);
    }

    @Override
    public String toString() {
        return "oneRoute{" +
                "route=" + route +
                '}';
    }
}

package com.lhr.Service;

import com.lhr.utils.aroundSearch;
import com.lhr.utils.getRoute;
import org.springframework.beans.factory.annotation.Autowired;

public class aroundSearchService {

    private aroundSearch arounds = new aroundSearch();
    private getRoute getRouteCls = new getRoute();

    public void getUserAround(String longitude,String latitude){
        System.out.println(arounds.getAroundMap(longitude,latitude));
    }
    public void getRouteToDes(String origin_log,String origin_lat,String des_log,String des_lat,String des_id){
        System.out.println(getRouteCls.getRouteCar(origin_log,origin_lat,des_log,des_lat,des_id));
    }
}

package com.lhr.utils;

import com.lhr.pojo.hospitalAround;
import com.lhr.pojo.oneRoute;
import com.lhr.pojo.stepRoute;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class getRoute {
    private final RestTemplate restTemplate;
    private final String apiKey = "e3a4ef9c939c554ace674a69801f8184";
    /*private String urlRouteCar = "https://restapi.amap.com/v5/direction/driving?key={key}&origin={origin}&destination={destination}&destination_id={destination_id}";*/
    private String urlRouteCar = "https://restapi.amap.com/v3/direction/driving?key={key}&origin={origin}&destination={destination}";
    private String urlRouteWalk = "https://restapi.amap.com/v3/direction/walking?key={key}&origin={origin}&destination={destination}";
    private String urlRouteRide = "https://restapi.amap.com/v4/direction/bicycling?key={key}&origin={origin}&destination={destination}";
    //private String urlRouteEbike = "https://restapi.amap.com/v5/direction/electrobike?key={key}&origin={origin}&destination={destination}";
    //private String urlRouteBus = "https://restapi.amap.com/v3/direction/transit/integrated?key={key}&origin={origin}&destination={destination}";
    private Map<String, String> params = new HashMap<>();

    public getRoute() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(3000);
        factory.setConnectTimeout(3000);
        this.restTemplate = new RestTemplate(factory);
    }

    public Map getRouteCar(String origin_log,String origin_lat,String des_log,String des_lat,String des_id){
        params.clear();
        params.put("key", apiKey);
        params.put("origin",origin_log+","+origin_lat);
        params.put("destination",des_log+","+des_lat);
        //params.put("destination_id",des_id);
        ResponseEntity<LinkedHashMap> result = restTemplate.getForEntity(urlRouteCar, LinkedHashMap.class, params);
        List<oneRoute> routeWayList = staticsDeal.toDealRoute(result.getBody());
        Map<String,List> resultMap = new LinkedHashMap<>();
        LinkedHashMap lmapTemp = (LinkedHashMap)((ArrayList)(((LinkedHashMap)(result.getBody()).get("route")).get("paths"))).get(0);
        List<String> infoList=new ArrayList<>();
        infoList.add((String)lmapTemp.get("distance")+"米");
        infoList.add((String)lmapTemp.get("duration")+"秒");
        resultMap.put("routeWayList",routeWayList);
        resultMap.put("info",infoList);
        return resultMap;
    }

    public Map getRouteWalk(String origin_log,String origin_lat,String des_log,String des_lat,String des_id){
        params.clear();
        params.put("key", apiKey);
        params.put("origin",origin_log+","+origin_lat);
        params.put("destination",des_log+","+des_lat);
        //params.put("destination_id",des_id);
        ResponseEntity<LinkedHashMap> result = restTemplate.getForEntity(urlRouteWalk, LinkedHashMap.class, params);
        List<oneRoute> routeWayList = staticsDeal.toDealRoute(result.getBody());
        Map<String,List> resultMap = new LinkedHashMap<>();
        LinkedHashMap lmapTemp = (LinkedHashMap)((ArrayList)(((LinkedHashMap)(result.getBody()).get("route")).get("paths"))).get(0);
        List<String> infoList=new ArrayList<>();
        infoList.add((String)lmapTemp.get("distance")+"米");
        infoList.add((String)lmapTemp.get("duration")+"秒");
        resultMap.put("routeWayList",routeWayList);
        resultMap.put("info",infoList);
        return resultMap;
    }

    public Map getRouteRide(String origin_log,String origin_lat,String des_log,String des_lat){
        params.clear();
        params.put("key", apiKey);
        params.put("origin",origin_log+","+origin_lat);
        params.put("destination",des_log+","+des_lat);
        //params.put("destination_id",des_id);
        ResponseEntity<LinkedHashMap> result = restTemplate.getForEntity(urlRouteRide, LinkedHashMap.class, params);
        List<oneRoute> routeWayList = staticsDeal.toDealRouteBike(result.getBody());
        Map<String,List> resultMap = new LinkedHashMap<>();
        LinkedHashMap lmapTemp = (LinkedHashMap)((ArrayList)(((LinkedHashMap)(result.getBody()).get("data")).get("paths"))).get(0);
        List<String> infoList=new ArrayList<>();
        infoList.add(lmapTemp.get("distance").toString()+"米");
        infoList.add(lmapTemp.get("duration").toString()+"秒");
        resultMap.put("routeWayList",routeWayList);
        resultMap.put("info",infoList);
        return resultMap;
    }

    /*public List<oneRoute> getRouteEBike(String origin_log,String origin_lat,String des_log,String des_lat){
        params.clear();
        params.put("key", apiKey);
        params.put("origin",origin_log+","+origin_lat);
        params.put("destination",des_log+","+des_lat);
        ResponseEntity<LinkedHashMap> result = restTemplate.getForEntity(urlRouteBus, LinkedHashMap.class, params);
        *//*List<oneRoute> routeWayList = staticsDeal.toDealRoute(result.getBody());*//*
        return null;
    }*/
}



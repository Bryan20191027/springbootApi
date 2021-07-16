package com.lhr.utils;

import com.lhr.pojo.hospitalAround;
import com.lhr.pojo.oneRoute;
import com.lhr.pojo.stepRoute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class staticsDeal {
    public static List<hospitalAround> toDeal(LinkedHashMap hospitalMap){
        List<hospitalAround> hosList=new ArrayList<>();
        List<Object> hospitalList = (ArrayList<Object>)hospitalMap.get("pois");
        hospitalAround hospitalTemp;

        for(Object obj:hospitalList){
            hospitalTemp=new hospitalAround();
            Map<String,Object> objMap = (Map<String,Object>)obj;
            if(!typeEsitimate.typeJudge((String)objMap.get("typecode"))){
                continue;
            }
            if(objMap.get("location")!=null){
                String loc=(String)objMap.get("location");
                String log=loc.split(",")[0];
                String lat=loc.split(",")[1];
                hospitalTemp.setLocation(log,lat);
            }
            else{
                continue;
            }
            if(objMap.get("id")!=null){
                hospitalTemp.setId((String)objMap.get("id"));
            }
            else if(objMap.get("address")!=null&&objMap.get("address") instanceof ArrayList){
                hospitalTemp.setAddress((ArrayList)objMap.get("address"));
            }
            else{
                continue;
            }
            if(objMap.get("address")!=null&&objMap.get("address") instanceof String){
                hospitalTemp.setAddress((String)objMap.get("address"));

            }
            else{
                continue;
            }

            if(objMap.get("typecode")!=null&&objMap.get("typecode") instanceof String){
                hospitalTemp.setTypecode((String)objMap.get("typecode"));
            }
            else{
                continue;
            }

            if(objMap.get("name")!=null){
                hospitalTemp.setName((String)objMap.get("name"));
            }
            if(objMap.get("type")!=null){
                hospitalTemp.setType(((String)objMap.get("type")).split(";")[2]);
            }
            if(objMap.get("pname")!=null){
                hospitalTemp.setPname((String)objMap.get("pname"));
            }

            if(objMap.get("cityname")!=null){
                hospitalTemp.setCityname((String)objMap.get("cityname"));
            }

            if(objMap.get("adname")!=null) {
                hospitalTemp.setAdname((String) objMap.get("adname"));
            }

            if(objMap.get("distance")!=null){
                hospitalTemp.setDistance((String)objMap.get("distance"));

            }

            /*if(objMap.get("tel")!=null&&objMap.get("tel") instanceof ArrayList){
                hospitalTemp.setTel((ArrayList<String>)objMap.get("tel"));
                System.out.println((ArrayList<String>)objMap.get("tel"));
            }
            else */
            if(objMap.get("tel").toString()!="[]"/*&&objMap.get("tel") instanceof String*/){
                hospitalTemp.setTel(splitUtil.telResult((String)objMap.get("tel")));

            }

            if(objMap.get("photos")!=null&&objMap.get("photos") instanceof ArrayList){
                hospitalTemp.setPhotosUrl(splitUtil.photosUrlListResult((ArrayList)objMap.get("photos")));
            }

            hosList.add(hospitalTemp);
            /*System.out.println(hospitalTemp);*/
        }
        return hosList;
    }

    public static List<oneRoute> toDealRoute(LinkedHashMap routeMap){
        List<LinkedHashMap> routeMapList = (ArrayList)((LinkedHashMap)routeMap.get("route")).get("paths");
        List<oneRoute> routeWayList=new ArrayList<>();
        oneRoute routeWay;
        stepRoute routeTemp;
        for(LinkedHashMap routeListTemp:routeMapList) {
            List<LinkedHashMap> routeListTempStep=(ArrayList)routeListTemp.get("steps");
            routeWay=new oneRoute();
            for (LinkedHashMap routeMapTemp : routeListTempStep) {
                routeTemp = new stepRoute();
                if(routeMapTemp.get("instruction") instanceof String)
                    routeTemp.setInstruction((String) routeMapTemp.get("instruction"));
                if(routeMapTemp.get("orientation") instanceof String)
                    routeTemp.setOrientation((String) routeMapTemp.get("orientation"));
                if(routeMapTemp.get("road") instanceof String)
                    routeTemp.setRoad_name((String) routeMapTemp.get("road"));
                if(routeMapTemp.get("distance") instanceof String)
                    routeTemp.setStep_distance((String) routeMapTemp.get("distance"));
                else if(routeMapTemp.get("distance") instanceof Integer)
                    routeTemp.setStep_distance(((Integer) routeMapTemp.get("distance")).toString());
                if(routeMapTemp.get("duration") instanceof String)
                    routeTemp.setDuration((String) routeMapTemp.get("duration"));
                if(routeMapTemp.get("polyline")instanceof String)
                    routeTemp.setPolyline((String)routeMapTemp.get("polyline"));
                routeWay.setRoute(routeTemp);
            }
            routeWayList.add(routeWay);
        }
        return routeWayList;
    }

    public static List<oneRoute> toDealRouteBike(LinkedHashMap routeMap){
        List<LinkedHashMap> routeMapList = (ArrayList)((LinkedHashMap)routeMap.get("data")).get("paths");
        List<oneRoute> routeWayList=new ArrayList<>();
        oneRoute routeWay;
        stepRoute routeTemp;
        for(LinkedHashMap routeListTemp:routeMapList) {
            List<LinkedHashMap> routeListTempStep=(ArrayList)routeListTemp.get("steps");
            routeWay=new oneRoute();
            for (LinkedHashMap routeMapTemp : routeListTempStep) {
                routeTemp = new stepRoute();
                if(routeMapTemp.get("instruction") instanceof String)
                    routeTemp.setInstruction((String) routeMapTemp.get("instruction"));
                if(routeMapTemp.get("orientation") instanceof String)
                    routeTemp.setOrientation((String) routeMapTemp.get("orientation"));
                if(routeMapTemp.get("road") instanceof String)
                    routeTemp.setRoad_name((String) routeMapTemp.get("road"));
                if(routeMapTemp.get("distance") instanceof String)
                    routeTemp.setStep_distance((String) routeMapTemp.get("distance"));
                else if(routeMapTemp.get("distance") instanceof Integer)
                    routeTemp.setStep_distance(((Integer) routeMapTemp.get("distance")).toString());
                if(routeMapTemp.get("duration") instanceof Integer)
                    routeTemp.setDuration(routeMapTemp.get("duration").toString());
                if(routeMapTemp.get("polyline")instanceof String)
                    routeTemp.setPolyline((String)routeMapTemp.get("polyline"));
                routeWay.setRoute(routeTemp);
            }
            routeWayList.add(routeWay);
        }
        return routeWayList;
    }
}

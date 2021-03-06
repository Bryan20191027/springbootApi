package com.lhr.Controller;

import com.lhr.pojo.hospitalAround;
import com.lhr.pojo.locationOrigin;
import com.lhr.pojo.oneRoute;
import com.lhr.pojo.stepRoute;
import com.lhr.utils.aroundSearch;
import com.lhr.utils.getRoute;
import com.lhr.utils.mapStatic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class listController {
    @RequestMapping("/indexList/{log}/{lat}/{rad}")
    public String list(Model model,@PathVariable("log")String log,@PathVariable("lat")String lat,@PathVariable("rad")String rad){
        aroundSearch arounds = new aroundSearch();
        mapStatic map=new mapStatic();
        List<hospitalAround> tempListAll = arounds.getAroundMap(log,lat,rad);
        model.addAttribute("mapUrl",map.getStaticMap(tempListAll,log,lat));
        model.addAttribute("listHos", tempListAll);
        model.addAttribute("loc",new locationOrigin(log,lat));
        model.addAttribute("rad",rad);
        return "listTemp";
    }

    /*@RequestMapping("/indexList/{log}/{lat}/total")
    public String listTotal(Model model,@PathVariable("log")String log,@PathVariable("lat")String lat) {
        List<hospitalAround> listTotal = new ArrayList<>();
        List<hospitalAround> tempListAll = arounds.getAroundMap(log,lat,"1000");
        for(hospitalAround hosTemp:tempListAll){
            if(hosTemp.getTypecode().equals("090100")){
                listTotal.add(hosTemp);
            }
        }
        model.addAttribute("listHos",listTotal);
        model.addAttribute("log",log);
        model.addAttribute("lat",lat);
        return "listTemp";
    }

    @RequestMapping("/indexList/{log}/{lat}/mouth")
    public String listMouth(Model model,@PathVariable("log")String log,@PathVariable("lat")String lat) {
        List<hospitalAround> listMouth = new ArrayList<>();
        List<hospitalAround> tempListAll = arounds.getAroundMap(log,lat,"1000");
        for(hospitalAround hosTemp:tempListAll){
            if(hosTemp.getTypecode().equals("090202")){
                listMouth.add(hosTemp);
            }
        }
        model.addAttribute("listHos",listMouth);
        model.addAttribute("log",log);
        model.addAttribute("lat",lat);
        return "listTemp";
    }

    @RequestMapping("/indexList/{log}/{lat}/eye")
    public String listEye(Model model,@PathVariable("log")String log,@PathVariable("lat")String lat) {
        List<hospitalAround> listEye = new ArrayList<>();
        List<hospitalAround> tempListAll = arounds.getAroundMap(log,lat,"1000");
        for(hospitalAround hosTemp:tempListAll){
            if(hosTemp.getTypecode().equals("090203")){
                listEye.add(hosTemp);
            }
        }
        model.addAttribute("listHos",listEye);
        model.addAttribute("log",log);
        model.addAttribute("lat",lat);
        return "listTemp";
    }*/

    @RequestMapping("/hosList/{log}/{lat}/{rad}/{id}")
    public String toUpdateEmp(@PathVariable("id")String id, Model model,@PathVariable("log")String log,@PathVariable("lat")String lat,@PathVariable("rad")String rad){
        aroundSearch arounds = new aroundSearch();
        hospitalAround tempHos =arounds.getCertainHospital(id,log,lat,rad);
        model.addAttribute("hos",tempHos);
        getRoute route = new getRoute();
        Map routeWayCar = route.getRouteCar(log,lat,tempHos.getLocation()[0],tempHos.getLocation()[1],tempHos.getId());
        Map routeWayWalk = route.getRouteWalk(log,lat,tempHos.getLocation()[0],tempHos.getLocation()[1],tempHos.getId());
        Map routeWayRide = route.getRouteRide(log,lat,tempHos.getLocation()[0],tempHos.getLocation()[1]);
        String routeCarUrl = (new mapStatic()).getRouteStatic(tempHos,(List)routeWayCar.get("routeWayList"),log,lat);
        String routeWalkUrl = (new mapStatic()).getRouteStatic(tempHos,(List)routeWayWalk.get("routeWayList"),log,lat);
        String routeRideUrl = (new mapStatic()).getRouteStatic(tempHos,(List)routeWayRide.get("routeWayList"),log,lat);
        //List<oneRoute> routeWayEBike = route.getRouteEBike(log,lat,tempHos.getLocation()[0],tempHos.getLocation()[1]);
        //List<oneRoute> routeWayBus = route.getRouteBus(origin_log,origin_lat,tempHos.getLocation()[0],tempHos.getLocation()[1]);


        model.addAttribute("routeWayCarList",routeWayCar.get("routeWayList"));
        model.addAttribute("routeWayCarInfo",routeWayCar.get("info"));
        model.addAttribute("routeCarUrl",routeCarUrl);
        model.addAttribute("routeWayWalkList",routeWayWalk.get("routeWayList"));
        model.addAttribute("routeWayWalkInfo",routeWayWalk.get("info"));
        model.addAttribute("routeWalkUrl",routeWalkUrl);
        model.addAttribute("routeWayRideList",routeWayRide.get("routeWayList"));
        model.addAttribute("routeWayRideInfo",routeWayRide.get("info"));
        model.addAttribute("routeRideUrl",routeRideUrl);
        //model.addAttribute("routeWayEBikeList",routeWayEBike);
        model.addAttribute("loc",new locationOrigin(log,lat));
        model.addAttribute("rad",rad);
        //model.addAttribute("routeWayBusList",routeWayBus);
        return "listTemp1";
    }
}

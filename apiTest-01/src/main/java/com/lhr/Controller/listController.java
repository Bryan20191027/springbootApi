package com.lhr.Controller;

import com.lhr.pojo.hospitalAround;
import com.lhr.pojo.oneRoute;
import com.lhr.pojo.stepRoute;
import com.lhr.utils.aroundSearch;
import com.lhr.utils.getRoute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class listController {
    private aroundSearch arounds = new aroundSearch();
    private getRoute route = new getRoute();
    private String origin_log="114.349444";
    private String origin_lat="30.528055";

    private List<hospitalAround> tempListAll = arounds.getAroundMap(origin_log,origin_lat,"10000");
    @RequestMapping("/indexList")
    public String list(Model model){
        model.addAttribute("listHos", tempListAll);
        return "listTemp";
    }

    @RequestMapping("/indexList/total")
    public String listTotal(Model model) {
        List<hospitalAround> listTotal = new ArrayList<>();
        for(hospitalAround hosTemp:tempListAll){
            if(hosTemp.getTypecode().equals("090100")){
                listTotal.add(hosTemp);
            }
        }
        model.addAttribute("listHos",listTotal);
        return "listTemp";
    }

    @RequestMapping("/indexList/mouth")
    public String listMouth(Model model) {
        List<hospitalAround> listMouth = new ArrayList<>();
        for(hospitalAround hosTemp:tempListAll){
            if(hosTemp.getTypecode().equals("090202")){
                listMouth.add(hosTemp);
            }
        }
        model.addAttribute("listHos",listMouth);
        return "listTemp";
    }

    @RequestMapping("/indexList/eye")
    public String listEye(Model model) {
        List<hospitalAround> listEye = new ArrayList<>();
        for(hospitalAround hosTemp:tempListAll){
            if(hosTemp.getTypecode().equals("090203")){
                listEye.add(hosTemp);
            }
        }
        model.addAttribute("listHos",listEye);
        return "listTemp";
    }

    @RequestMapping("/hosList/{id}")
    public String toUpdateEmp(@PathVariable("id")String id, Model model){
        hospitalAround tempHos =arounds.getCertainHospital(id);
        model.addAttribute("hos",tempHos);
        List<oneRoute> routeWayCar = route.getRouteCar(origin_log,origin_lat,tempHos.getLocation()[0],tempHos.getLocation()[1],tempHos.getId());
        List<oneRoute> routeWayWalk = route.getRouteWalk(origin_log,origin_lat,tempHos.getLocation()[0],tempHos.getLocation()[1],tempHos.getId());
        List<oneRoute> routeWayRide = route.getRouteRide(origin_log,origin_lat,tempHos.getLocation()[0],tempHos.getLocation()[1]);
        List<oneRoute> routeWayEBike = route.getRouteEBike(origin_log,origin_lat,tempHos.getLocation()[0],tempHos.getLocation()[1]);
        //List<oneRoute> routeWayBus = route.getRouteBus(origin_log,origin_lat,tempHos.getLocation()[0],tempHos.getLocation()[1]);

        model.addAttribute("routeWayCarList",routeWayCar);
        model.addAttribute("routeWayWalkList",routeWayWalk);
        model.addAttribute("routeWayRideList",routeWayRide);
        model.addAttribute("routeWayEBikeList",routeWayEBike);
        //model.addAttribute("routeWayBusList",routeWayBus);
        return "listTemp1";
    }
}

package com.lhr.utils;

import com.lhr.pojo.hospitalAround;
import com.lhr.pojo.locationOrigin;
import com.lhr.pojo.oneRoute;
import com.lhr.pojo.stepRoute;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class mapStatic {
    private final String apiKey="e3a4ef9c939c554ace674a69801f8184";
    private final String urlMapStatic="https://restapi.amap.com/v3/staticmap?";

    public mapStatic() {

    }

    public String getStaticMap(List<hospitalAround> tempListAll,String log,String lat){
        String urlMapStaticAppend=urlMapStatic+"key="+apiKey+"&zoom=13"+"&size=400*400"+"&location="+(new locationOrigin(log,lat)).toStringUrl()+"&scale=2";
        urlMapStaticAppend+="&markers=";
        int index=0;
        String appendStrMark="";
        String appendStrLabel="";
        for(hospitalAround tempHos:tempListAll){
            index++;
            if(index>9)
                break;
            if(index!=1){
                appendStrMark+="|";
                appendStrLabel+="|";
            }
            appendStrMark+="large,0xFF0000,"+index+":"+(new locationOrigin(tempHos.getLocation()[0],tempHos.getLocation()[1])).toStringUrl();
            appendStrLabel+=(tempHos.getName().length()>15?"名字过长":tempHos.getName())+",0,0,16,0xFFFFFF,0x008000:"+(new locationOrigin(tempHos.getLocation()[0],tempHos.getLocation()[1])).toStringUrl();
        }
        /*urlMapStatic+=appendStrMark+"&labels=";
        index=0;
        for(hospitalAround tempHos:tempListAll){
            index++;
            if(index>9)
                break;
            if(index!=1){
                appendStrLabel+="|";
            }
            appendStrLabel+=(tempHos.getName().length()>15?"名字过长":tempHos.getName())+",0,0,16,0xFFFFFF,0x008000:"+(new locationOrigin(tempHos.getLocation()[0],tempHos.getLocation()[1])).toStringUrl();
        }
        urlMapStatic+=appendStrLabel;*/
        urlMapStaticAppend+=appendStrMark+"&labels="+appendStrLabel;
        return urlMapStaticAppend;
    }

    public String getRouteStatic(hospitalAround hosEnd,List<oneRoute> tempRouteAll, String log, String lat){
        String urlOrigin="https://restapi.amap.com/v3/staticmap?key="+apiKey+"&zoom=13&size=600*300&location="+(new locationOrigin(log,lat)).toStringUrl()
                +"&scale=2&paths=5,0x0000FF,0.5,,:"+(new locationOrigin(log,lat)).toStringUrl();
        oneRoute tempOneRoute = null;
        if(tempRouteAll.size()>0) {
            tempOneRoute = tempRouteAll.get(0);
        }
        String endPointLoc=hosEnd.getLocation()[0]+","+hosEnd.getLocation()[1];
        for(stepRoute stepTemp:tempOneRoute.getRoute()){
            urlOrigin+=";"+stepTemp.getPolyline();
        }
        urlOrigin+="&markers=large,0xFC6054,S:"+(new locationOrigin(log,lat)).toStringUrl()+"|large,0x008000,E:"+endPointLoc+
                "&labels=当前,0,0,15,0xffffff,0x008000:"+(new locationOrigin(log,lat)).toStringUrl()+"|"+hosEnd.getName()+",0,0,15,0xffffff,0x008000:"+endPointLoc;
        System.out.println(urlOrigin);
        return urlOrigin;
    }
}

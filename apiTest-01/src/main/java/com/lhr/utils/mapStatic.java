package com.lhr.utils;

import com.lhr.pojo.hospitalAround;
import com.lhr.pojo.locationOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class mapStatic {
    private final String apiKey="e3a4ef9c939c554ace674a69801f8184";
    private String urlMapStatic="https://restapi.amap.com/v3/staticmap?";

    public mapStatic() {

    }

    public String getStaticMap(List<hospitalAround> tempListAll,String log,String lat){
        urlMapStatic+="key="+apiKey+"&zoom=13"+"&size=400*400"+"&location="+(new locationOrigin(log,lat)).toStringUrl()+"&scale=2";
        urlMapStatic+="&markers=";
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
        urlMapStatic+=appendStrMark+"&labels="+appendStrLabel;
        return urlMapStatic;
    }
}

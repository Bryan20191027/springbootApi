package com.lhr.utils;

import com.lhr.pojo.hospitalAround;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.*;

public class aroundSearch {
    private final RestTemplate restTemplate;
    private final String apiKey="e3a4ef9c939c554ace674a69801f8184";
    private String urlAround="https://restapi.amap.com/v3/place/around?key={key}&location={longitude},{latitude}&keywords={keywords}&radius={radius}&page={page}";
    private final String keywords="医院";
    private final String defaultRadius="3000";
    private final int offset=20;
    private Map<String, String> params = new HashMap<>();
    private List<hospitalAround> hosList=new ArrayList<>();

    public aroundSearch() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(3000);
        factory.setConnectTimeout(3000);
        this.restTemplate = new RestTemplate(factory);
    }

    /*private Map paramsMap(String longitude,String latitude,String radius) {
        params.put("key", apiKey);
        params.put("longitude",longitude);
        params.put("latitude",latitude);
        params.put("radius",radius);
        params.put("keywords",keywords);
        return params;
    }*/

    public List<hospitalAround> getAroundMap(String longitude, String latitude, String radius){
        hosList.clear();
        params.clear();
        params.put("key", apiKey);
        params.put("longitude",longitude);
        params.put("latitude",latitude);
        params.put("radius",radius);
        params.put("keywords",keywords);
        params.put("page","1");
        ResponseEntity<LinkedHashMap> result = restTemplate.getForEntity(urlAround, LinkedHashMap.class, params);
        int pageCount= Integer.parseInt((String)result.getBody().get("count"))/offset+1;
        for(int i=1;i<=pageCount;i++){
            params.put("page",""+i);
            result = restTemplate.getForEntity(urlAround, LinkedHashMap.class, params);
            hosList.addAll(staticsDeal.toDeal(result.getBody()));
        }

        //System.out.println(result.getBody());
        return hosList;
    }

/*    public LinkedHashMap getAroundMapTest(String longitude, String latitude, String radius){
        params.clear();
        params.put("key", apiKey);
        params.put("longitude",longitude);
        params.put("latitude",latitude);
        params.put("radius",radius);
        params.put("keywords",keywords);
        ResponseEntity<LinkedHashMap> result = restTemplate.getForEntity(urlAround, LinkedHashMap.class, params);
        if (result.getBody() == null) {
            return null;
        }*//*

        return result.getBody();
    }*/

    public List<hospitalAround> getAroundMap(String longitude, String latitude){
        return getAroundMap(longitude,latitude,defaultRadius);
    }

    public hospitalAround getCertainHospital(String id){
        if(hosList.size()==0){
            return null;
        }
        else{
            for(hospitalAround ha:hosList){
                if(ha.getId().equals(id)){
                    return ha;
                }
            }
            return null;
        }
    }
}

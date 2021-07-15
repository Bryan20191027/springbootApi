package com.lhr.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hospitalAround {
    private String id;
    private String name;
    private String type;
    private String typecode;

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    private String pname;
    private String cityname;
    private String adname;

    private List<String> address;
    private String distance;
    private String[] location;

    private List<String> tel;
    private List<String> photosUrl;

    public List<String> getTel() {
        return tel;
    }

    public void setTel(List<String> tel) {
        this.tel = tel;
    }

    public void setTel(String tel) {
        this.tel=new ArrayList<>();
        this.tel.add(tel);
    }

    public List<String> getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(List<String> photosUrl) {
        this.photosUrl = photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl=new ArrayList<>();
        this.photosUrl.add(photosUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=new ArrayList<>();
        this.address.add(address);
    }

    public void setAddress(List<String> list){
        this.address=list;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String log,String lat) {
        this.location = new String[]{log,lat};
    }

    @Override
    public String toString() {
        return "hospitalAround{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", pname='" + pname + '\'' +
                ", cityname='" + cityname + '\'' +
                ", adname='" + adname + '\'' +
                ", address='" + address + '\'' +
                ", distance='" + distance + '\'' +
                ", location=" + Arrays.toString(location) +
                ", tel=" + tel +
                ", photosUrl=" + photosUrl +
                '}';
    }
}

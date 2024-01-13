package com.isamm.myspace;

public class  Planet {
    private String name;
    private String briefdesc;
    private String description;
    private String imgPath;
    private String color;

    public Planet(String name, String description,String color,String imgPath) {
        this.name = name;
        this.description = description;
        this.imgPath = imgPath;
        this.color = color;
        briefdesc = copyBriefDesc(description);
    }

    private String copyBriefDesc(String description){ //génerer une brève description à partir de la description initiale
        int i = 0;
        String rslt="";
        while(i<=50 && i<description.length()){
            rslt = rslt+description.charAt(i);
            i++;
        }
        if(i<description.length()){
            rslt=rslt+"...";
        }
        return rslt;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getBriefdesc() {
        return briefdesc;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

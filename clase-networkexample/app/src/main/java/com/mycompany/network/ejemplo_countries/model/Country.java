package com.mycompany.network.ejemplo_countries.model;

/**
 * Created by romina valladaresromina@gmail.com on 10/11/18.
 */
public class Country {
    private String region;

    private String numericCode;

    private String nativeName;

    private String capital;

    private String subregion;

    private String flag;

    private String name;

    private String population;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "ClassPojo [region = " + region + ", numericCode = " + numericCode + ", nativeName = " + nativeName + ", capital = " + capital + ", subregion = " + subregion + ", flag = " + flag + ", name = " + name + ", population = " + population + "]";
    }
}
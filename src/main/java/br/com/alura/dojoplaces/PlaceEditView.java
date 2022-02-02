package br.com.alura.dojoplaces;

public class PlaceEditView {

    private String code;
    private String city;
    private String district;
    private String name;

    public PlaceEditView(Place place) {
        this.code = place.getCode();
        this.city = place.getCity();
        this.district = place.getDistrict();
        this.name = place.getName();
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getName() {
        return name;
    }
}

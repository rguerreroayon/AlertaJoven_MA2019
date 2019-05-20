package com.nearsoft.sofe.map.model;

public class Servicio {
    private String serviceName;
    private int icon;

    public Servicio(String serviceName, int icon) {
        this.serviceName = serviceName;
        this.icon = icon;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}

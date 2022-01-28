package com.example.proxies;

public class DefaultSimpleBean implements SimpleBean {
    private long dummy = 0;

    @Override
    public void adviced() {
        dummy = System.currentTimeMillis();
    }

    @Override
    public void unadviced() {
        dummy = System.currentTimeMillis();
    }
}

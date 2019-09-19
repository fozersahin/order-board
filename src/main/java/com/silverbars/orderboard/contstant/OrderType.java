package com.silverbars.orderboard.contstant;

public enum OrderType {

    BUY("buy"),
    SELL("sell");

    OrderType(String order) {

        this.order = order;
    }

    private String order;


    public String getOrder() {
        return order;
    }

}

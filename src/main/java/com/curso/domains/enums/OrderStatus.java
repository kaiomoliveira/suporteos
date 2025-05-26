package com.curso.domains.enums;

public enum OrderStatus {
    CLOSED(0,"CLOSED)"),OPEN(1,"OPEN");
    
    private Integer id;
    private String orderStatusType;

    OrderStatus(Integer id, String orderStatusType) {
        this.id = id;
        this.orderStatusType = orderStatusType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(String orderStatusType) {
        this.orderStatusType = orderStatusType;
    }

    public static OrderStatus toEnum(Integer id) {
        if (id == null) return null;
        for (OrderStatus x : OrderStatus.values()) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        throw new IllegalArgumentException("OrderStatus inválido!");
    }

}

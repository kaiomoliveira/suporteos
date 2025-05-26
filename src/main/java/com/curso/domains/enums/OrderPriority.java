package com.curso.domains.enums;

public enum OrderPriority {
    HIGH(0,"HIGH)"),MID(1,"MID"),LOW(2,"LOW");

    private Integer id;
    private String orderPriorityType;

    OrderPriority(Integer id, String orderPriorityType) {
        this.id = id;
        this.orderPriorityType = orderPriorityType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderPriorityType() {
        return orderPriorityType;
    }

    public void setOrderPriorityType(String orderPriorityType) {
        this.orderPriorityType = orderPriorityType;
    }

    public static OrderPriority toEnum(Integer id) {
        if (id == null) return null;
        for (OrderPriority x : OrderPriority.values()) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        throw new IllegalArgumentException("OrderPriority inválido!");
    }

}

package com.webservices.course.entities.enums;

public enum OrderStatus {
    
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code; //codigo do tipo enumerado

    private OrderStatus(int code){
        this.code = code;
    }

    public int getCode(){ //acessar o codigo do tipo enumerado
        return code;
    }

    public static OrderStatus valueOf(int code){ //converter um valor numerico para um tipo enumerado
        for(OrderStatus value : OrderStatus.values()){ //percorre oa valores possiveis do orderstatus
            if(value.getCode() == code){ //se o codigo for igual, retorna o valor
                return value;
            }
        } 
        throw new IllegalArgumentException("Invalid OrderStatus code"); //o codigo nao exite
    }
}

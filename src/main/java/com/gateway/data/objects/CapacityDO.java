package com.gateway.data.objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CapacityDO {
    private String name;
    private int damage;
    private int level;
    private TypeDO type;
}

package com.hugui.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Bill {

    String username;
    Integer total;

}

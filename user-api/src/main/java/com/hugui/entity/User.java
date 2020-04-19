package com.hugui.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author:hugui
 */

@Setter
@Getter
@Builder
public class User {

    String username;
    String password;

}
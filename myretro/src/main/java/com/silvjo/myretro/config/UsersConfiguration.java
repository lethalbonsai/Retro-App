package com.silvjo.myretro.config;

import lombok.Data;

@Data
public class UsersConfiguration {
    String server;
    Integer port;
    String username;
    String password;
}

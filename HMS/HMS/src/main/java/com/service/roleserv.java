package com.service;

import org.springframework.http.ResponseEntity;

import com.Entity.roles;

public interface roleserv {
public ResponseEntity<?> roleexist(roles role);
}

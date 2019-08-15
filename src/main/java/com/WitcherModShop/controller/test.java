package com.WitcherModShop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {
    @RequestMapping(value = "/dupa", method = RequestMethod.GET)
    public ResponseEntity<String> test()
    {
        return new ResponseEntity<String>("qasdfsdsdg", HttpStatus.OK);
    }

}

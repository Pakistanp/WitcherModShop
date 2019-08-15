package com.WitcherModShop.controller;

import com.WitcherModShop.config.JwtTokenUtil;
import com.WitcherModShop.model.JwtRequest;
import com.WitcherModShop.model.JwtResponse;
import com.WitcherModShop.model.UserDTO;
import com.WitcherModShop.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        ResponseEntity<String> resp = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        if (resp.getStatusCode().is2xxSuccessful()) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return  resp;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private ResponseEntity<String> authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (DisabledException e) {
            e.printStackTrace();
            return new ResponseEntity<>("USER_DISABLED", HttpStatus.UNAUTHORIZED);
//            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return new ResponseEntity<>("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED);
//            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

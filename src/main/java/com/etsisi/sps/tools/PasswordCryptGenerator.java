package com.etsisi.sps.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class PasswordCryptGenerator {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter password to crypt: ");
        String password=reader.nextLine();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(String.format("Encoded password password is %s",passwordEncoder.encode(password)));
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.servicios;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Dennys
 */
public class TestServicio {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("admin"));;
    }
}

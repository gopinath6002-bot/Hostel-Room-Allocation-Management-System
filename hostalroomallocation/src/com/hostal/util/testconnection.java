package com.hostal.util;

import java.sql.Connection;

public class testconnection {

    public static void main(String[] args) {

        try {
            Connection con = dbconnection.getConnection();

            if (con != null) {
                System.out.println("Database Connected Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package com.pims.database;

public class Test {

    public static void main(String[] args) {

        System.out.println(
                "Testing database connection..."
        );

        if (Connection.testConnection()) {

            System.out.println(
                    "Successful connected to database."
            );

        } else {

            System.out.println(
                    "Error could not connect to database."
            );
        }
    }
}
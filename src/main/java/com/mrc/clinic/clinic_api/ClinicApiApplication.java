package com.mrc.clinic.clinic_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ClinicApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicApiApplication.class, args);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date = sdf.format(new Date());
        System.out.println("Aplicação iniciada com sucesso às " + date);
    }

}

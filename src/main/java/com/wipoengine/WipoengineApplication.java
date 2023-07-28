package com.wipoengine;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Scanner;

@SpringBootApplication
public class WipoengineApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WipoengineApplication.class, args);

	}

}

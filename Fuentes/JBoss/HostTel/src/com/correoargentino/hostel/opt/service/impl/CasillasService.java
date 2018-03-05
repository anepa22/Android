package com.correoargentino.hostel.opt.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.correoargentino.hostel.opt.service.ICasillasService;

public class CasillasService implements ICasillasService {

	@Override
	public String agregarCasilla(String casilla) {
		return agregarColaGLI(casilla, "java:/jms/queue/"+casilla);
	}


	/**
	 *@author anepa
	 */
	private String agregarColaGLI(String name, String jndi) {
		Process p;

//		name=TestQ
//		jndi=java:/jms/queue/TestQ3
		
		String command = "C:/servers/jboss-eap-6.4/bin/jboss-cliaep.bat --connect /subsystem=messaging/hornetq-server=default/jms-queue="+name+":add(entries=[\""+jndi+"\"])";

		System.out.println(command);

		String salida = "Error";
		
		try {
			p = Runtime.getRuntime().exec(command);
			
			InputStreamReader input = new InputStreamReader(p.getInputStream());
            BufferedReader stdInput = new BufferedReader(input);

            salida = stdInput.readLine();
            
            System.out.println("salidaaa: "+salida);
		} catch (IOException io) {
			System.out.println("io Error" + io.getMessage());
			return "Error";
		}
		return salida;
	}
}

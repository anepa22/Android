package com.correoargentino.hostel.opt.facade;

import javax.ws.rs.core.Response;

import com.correoargentino.hostel.opt.service.ICasillasService;
import com.correoargentino.hostel.opt.service.impl.CasillasService;

public class CasillaFacade {
	
	ICasillasService casillasService = new CasillasService();
	
	public Response agregarCasilla(String casilla) {
		Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
		try {
			String casillaResponse = casillasService.agregarCasilla(casilla);

			builder.entity(casillaResponse);
			builder.status(Response.Status.OK);
		}
		catch (Exception e){
			builder.entity("Salida Error.");
			builder.status(Response.Status.BAD_REQUEST);
		}
		return builder.build();
	}
}

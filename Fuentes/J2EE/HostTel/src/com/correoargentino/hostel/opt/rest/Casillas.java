package com.correoargentino.hostel.opt.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.correoargentino.hostel.opt.facade.CasillaFacade;


@Path("rest")
public class Casillas {
	
	private CasillaFacade casillaFacadeWs = new CasillaFacade();
	
	
	@GET
	@Path("/agregarCasilla")
	@Produces(MediaType.APPLICATION_JSON)
	public Response agregarCasilla(@QueryParam("casilla") String casilla) {
		return casillaFacadeWs.agregarCasilla(casilla);
	}
}

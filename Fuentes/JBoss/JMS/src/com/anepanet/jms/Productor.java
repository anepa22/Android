package com.anepanet.jms;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Productor {
	private static final String PROVIDER_URL = "remote://localhost:4447";
	private static final String DEFAULT_USERNAME = "cliente";
	private static final String DEFAULT_PASSWORD = "1234.qwer";
	private static final String	DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	private static final String	INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private static final String DEFAULT_DESTINATION = "java:jboss/hosttel/InputMsg";

	/**
	 * Método estático principal de la clase.
	 * @param args argumentos por omisión.
	 * @throws Exception lanzada en caso de alguna falla.
	 */
	public static void main(String[] args) throws Exception {
		Context contexto = getInitialContext();
		ConnectionFactory fabrica =	(ConnectionFactory) contexto.lookup(DEFAULT_CONNECTION_FACTORY);
		Connection conexion = fabrica.createConnection(DEFAULT_USERNAME,DEFAULT_PASSWORD);
		Session sesion = conexion.createSession(false, Session.AUTO_ACKNOWLEDGE);

//		Queue cola = (Queue) contexto.lookup(DEFAULT_DESTINATION);
		
		Queue cola = (Queue)sesion.createQueue("InputMsg");
		
		MessageProducer productor = sesion.createProducer(cola);
		
		
		String texto = "Hola Anepa" + new java.util.Date();

		ObjectMessage msg1 = sesion.createObjectMessage();
		
		TextMessage msg = sesion.createTextMessage(texto);
		System.out.println("Cuerpo del mensaje a enviar: " + texto);
		
		// La fecha y hora en milisegundos se envía como una propiedad del mensaje.
		long propiedad = System.currentTimeMillis();
		msg.setLongProperty("propiedad", propiedad);
		
		productor.send(msg);

		productor.close();
		sesion.close();
		
		conexion.close();
		
		contexto.close();
	}

	/** 
	 * Obtención del contexto remoto inicial para JBoss Application Server 7.1.1
	 * @return el contexto inicial asociado con JBoss.
	 * @throws NamingException lanzada en caso de error en la ejecución del método.
	 */
	private static Context getInitialContext() throws NamingException {
		final Properties parm = new Properties();
		parm.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		parm.put(Context.PROVIDER_URL, PROVIDER_URL);
		parm.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
		parm.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);

		Context context = new InitialContext(parm);
		return context;
	}
}

package exceptions;

import utilities.EventTypes;
import utilities.Severities;

public class ConnectionFileNotFoundException extends AuditingException {


	/**
	 * Se genera esta excepci�n cuando no existe el archivo solicitado en la ruta especificada.
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionFileNotFoundException(String message, Exception cause) {
		super(message, cause,Severities.CRITIC,EventTypes.PROCESS);
		// TODO Auto-generated constructor stub
	}

}

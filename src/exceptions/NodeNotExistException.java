package exceptions;

import utilities.EventTypes;
import utilities.Severities;

public class NodeNotExistException extends AuditingException {


	/**
	 * Se genera esta excepci�n no existe uno de los nodos requeridos en el archivo xml
	 */
	private static final long serialVersionUID = 1L;

	public NodeNotExistException(String message, Exception cause) {
		super(message, cause,Severities.CRITIC,EventTypes.PROCESS);
		// TODO Auto-generated constructor stub
	}

}

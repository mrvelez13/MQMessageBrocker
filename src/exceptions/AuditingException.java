package exceptions;

import utilities.EventTypes;
import utilities.Severities;

public class AuditingException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de excepci�n base de notificaciones
	 * @param message Mensaje descriptivo
	 * @param cause Excepci�n Original
	 * @param severity Severidad del Evento: 1-Cr�tico, 2-Alerta, 3-Notificaci�n
	 * @param event Tipo de Evento: 1-Funcional, 2-Proceso, 3-Infraestructura
	 */
	public AuditingException(String message, Exception cause, Severities severity, EventTypes event) {
		super(message, cause);
		this.severity = severity.getCode();
		this.event = event.getCode();
	}
	
	private final short severity;
	private final short event;

	public short getSeverity() {
		return severity;
	}
	
	public short getEvent() {
		return event;
	}

}

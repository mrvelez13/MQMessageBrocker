/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author jonathan.velez
 */
public class MQMMessageBrockerUnexpectedException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MQMMessageBrockerUnexpectedException(String message, Exception cause) {
		super(message,cause);
        }    
}

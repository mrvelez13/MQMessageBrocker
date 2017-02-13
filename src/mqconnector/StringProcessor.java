/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqconnector;

/**
 *
 * @author jonathan.velez
 */
public interface StringProcessor {
    public void process(String stringData, byte[] correlationId);
}

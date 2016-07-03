/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import br.com.etefgarcia.armarios.model.Config;
import br.com.etefgarcia.armarios.util.ConfigUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando-pucci
 */
public class Tester {
    
    public static void main(String[] args) {
        
        try {
            Config c = ConfigUtils.getConfiguracao();
            
            System.out.println(c);
        } catch (IOException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

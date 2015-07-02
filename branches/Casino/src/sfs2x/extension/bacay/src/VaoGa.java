/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.User;

/**
 *
 * @author MT405
 */
public class VaoGa {
    public Double vaoga(int maxpoint,int pointuser,User user,int count){
        double tienvaoga = user.getVariable("tienvaoga").getDoubleValue();
        Double kq = 0.0;
        if(tienvaoga == 0){
            return 0.0;
        }
            if(pointuser < maxpoint){
                kq = (tienvaoga* -1);
            }else
            {
                kq = tienvaoga*(count-1);
            }
        return kq; 
    }
    
}

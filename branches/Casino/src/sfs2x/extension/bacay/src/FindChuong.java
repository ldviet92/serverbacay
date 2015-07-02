/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT405
 */
public class FindChuong {

    public int timchuong(Room room){
            for (int i = 0; i < room.getUserList().size(); i++) {
            if(room.getUserList().get(i).getVariable("ischuong").getBoolValue()){
                return room.getUserList().get(i).getId();
            }
        }
                
            int idchuongfirst = room.getUserList().get(0).getId();
            SFSUserVariable ischuong = SFSUserVariable.newInstance("ischuong", true);
        try {
            room.getUserList().get(0).setVariable(ischuong);
        } catch (SFSVariableException ex) {
            Logger.getLogger(FindChuong.class.getName()).log(Level.SEVERE, null, ex);
        }  
            return idchuongfirst;
    }
}

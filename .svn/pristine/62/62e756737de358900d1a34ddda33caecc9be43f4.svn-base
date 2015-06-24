/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leduc
 */
public class ChickenGameEventHandler extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
         Double chicken = isfso.getDouble("tienvaoga");
         trace(chicken);
        SFSUserVariable money = SFSUserVariable.newInstance("tienvaoga", chicken);
        try {
            user.setVariable(money);
        } catch (SFSVariableException ex) {
            
        }
        ISFSObject obj = new SFSObject();
        obj.putInt("userid", user.getId());
        obj.putDouble("chickenmoney", chicken);
        send("vaoga", obj, this.getParentExtension().getParentRoom().getUserList());
    }
    
}

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
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT405
 */
public class BetGameEventHandler extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        Double bet = 0.0;
              bet =  isfso.getDouble("betmoney");
              trace(bet);
        SFSUserVariable money = SFSUserVariable.newInstance("bet", bet);
        try {
            user.setVariable(money);
        } catch (SFSVariableException ex) {
        }
        ISFSObject obj = new SFSObject();
        obj.putInt("betid", user.getId());
        obj.putDouble("betmoney", bet);
        send("betgame", obj, this.getParentExtension().getParentRoom().getUserList());
    }
    
}

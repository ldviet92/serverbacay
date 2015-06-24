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
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author leduc
 */
public class PlusGameEventHandler extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
          // lay id ke, id nguoi bi ke, so tien ke.
        int userid = user.getId();
        int friendid =  isfso.getInt("friendid");
        Double plusmoney = isfso.getDouble("tienke");
        ISFSObject obj = new SFSObject();
        obj.putInt("userid", userid);
        obj.putInt("friendid",friendid);
        obj.putDouble("tienke", plusmoney);
//        SFSUserVariable user = SFSUserVariable.newFromSFSArray(null)
        send("kecua", isfso, this.getParentExtension().getParentRoom().getUserList());
    }
    
}

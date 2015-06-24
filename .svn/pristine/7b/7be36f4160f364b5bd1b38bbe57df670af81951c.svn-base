/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author MT405
 */
public class nhanbien extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        int userid = user.getId();
        int friendid =  isfso.getInt("friendid");
        Double tienbien = isfso.getDouble("tienbien");
        ISFSObject obj = new SFSObject();
        obj.putUtfString("status", "success");
//        SFSUserVariable user = SFSUserVariable.newFromSFSArray(null)
        send("nhanbien", isfso, user);
    }
    
}

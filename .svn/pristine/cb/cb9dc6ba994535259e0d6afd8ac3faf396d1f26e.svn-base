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
public class StartGameEventHandler extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        trace("Gui xuong client");
        ISFSObject obj = new SFSObject();
        // gui xuong client gia tri: success, id nguoi cam chuong
        obj.putUtfString("status", "success");
        obj.putInt("id", user.getId());
        trace(user.getId());
        trace( this.getParentExtension().getParentRoom().getUserList());
        send("startgame",obj,  this.getParentExtension().getParentRoom().getUserList());
    }
    
}

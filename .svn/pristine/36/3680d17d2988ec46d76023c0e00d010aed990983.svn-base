/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
/**
 *
 * @author MT405
 */
public class SendInformationUser extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        trace("Gui thong tin cua user join room");
        ISFSObject obj = new SFSObject();
        obj.putDouble("money", isfso.getDouble("money"));
        obj.putUtfString("username", isfso.getUtfString("username"));
        send("sendInformationUser",obj,  this.getParentExtension().getParentRoom().getUserList());
    }
    
}

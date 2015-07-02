/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.buddylist.SFSBuddyVariable;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

/**
 *
 * @author MT405
 */
public class TimChuongEventHandler extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        FindChuong find = new FindChuong();
        int id = find.timchuong(getParentExtension().getParentRoom());
        ISFSObject obj = new SFSObject();
        obj.putInt("id", id);
        
        send("timchuong", obj, getParentExtension().getParentRoom().getUserList());
    }
    
}

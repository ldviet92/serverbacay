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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author MT405
 */
public class SendInfomartionListUser extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        trace("Gui thong tin cua user join room");
        ISFSObject obj = new SFSObject();
        List<JSONObject> listjson = new ArrayList<>();
         JSONObject jsonobj = new JSONObject();
        int count = this.getParentExtension().getParentRoom().getUserList().size();
        for (int i = 0; i < count; i++) {
            try {
                jsonobj = new JSONObject();
                jsonobj.put("id", this.getParentExtension().getParentRoom().getUserList().get(i).getId());
                jsonobj.put("name", this.getParentExtension().getParentRoom().getUserList().get(i).getName());
                jsonobj.put("money", getParentExtension().getParentRoom().getUserList().get(i).getVariable("money").getDoubleValue());
                jsonobj.put("avatar", getParentExtension().getParentRoom().getUserList().get(i).getVariable("avatar").getStringValue());
                
                listjson.add(jsonobj);
            } catch (JSONException ex) {
                Logger.getLogger(SendInfomartionListUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String listUser = String.valueOf(listjson);
        obj.putUtfString("list", listUser);
        
        send("sendinformatioinlistuser",obj, getParentExtension().getParentRoom().getUserList());
    }
    
}

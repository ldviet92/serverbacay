/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
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
public class SendInformationUser extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        // luu id vao user
        SFSUserVariable iddb = SFSUserVariable.newInstance("iddb", isfso.getInt("iddb"));
        SFSUserVariable avatar = SFSUserVariable.newInstance("avatar", isfso.getUtfString("avatar"));
        SFSUserVariable money = SFSUserVariable.newInstance("money", isfso.getDouble("money"));
        SFSUserVariable isplay = SFSUserVariable.newInstance("isplay", false);
        SFSUserVariable ischuong = SFSUserVariable.newInstance("ischuong", false);
        try {
            user.setVariable(iddb);
            user.setVariable(avatar);
            user.setVariable(money);
             user.setVariable(isplay);
             user.setVariable(ischuong);
        } catch (SFSVariableException ex) {
            Logger.getLogger(SendInformationUser.class.getName()).log(Level.SEVERE, null, ex);
        }


//            trace("Gui thong tin cua user join room: iddb: "+isfso.getInt("iddb")+"avatar: "+isfso.getUtfString("avatar"));
//            ISFSObject obj = new SFSObject();
//            obj.putDouble("money", isfso.getDouble("money"));
//            obj.putUtfString("username", isfso.getUtfString("username"));
//            obj.putUtfString("avatar", isfso.getUtfString("avatar"));
//            obj.putInt("userid", user.getId());
//            trace("--SendInformationUser--thong tin cua user: "+obj);
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
        send("sendInformationUser", obj, getParentExtension().getParentRoom().getUserList());
    }

}

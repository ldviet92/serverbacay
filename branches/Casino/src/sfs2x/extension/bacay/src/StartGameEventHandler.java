/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.buddylist.SFSBuddyVariable;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MT405
 */
public class StartGameEventHandler extends BaseClientRequestHandler{

    @Override
    public void handleClientRequest(User user, ISFSObject isfso) {
        trace("Bat dau choi game - dat cuoc: ");
        
        int count = getParentExtension().getParentRoom().getUserList().size();
//        SFSBuddyVariable userchuong = new SFSBuddyVariable("idChuong", 0);
//        trace("Nguoi cam chuong: "+ valueChuong);
//        trace("Id nguoi cam chuong: " + userchuong.getIntValue());
//        ISFSArray list  = new SFSArray();
        List<Integer> userplay = new ArrayList<>();
        SFSUserVariable isplay = SFSUserVariable.newInstance("isplay", true);
//        SFSUserVariable ischuong = SFSUserVariable.newInstance("ischuong", false);
        //dang ki tao mang rong phan choi ke
        ISFSArray ilist = new SFSArray();
        ISFSObject objdefault = new SFSObject();
        objdefault.putDouble("tienke", 0);
        objdefault.putInt("friendid", -1);
        ilist.addSFSObject(objdefault);
        SFSUserVariable iske = SFSUserVariable.newInstance("choike", ilist);
        objdefault  = new SFSObject();
        objdefault.putDouble("tienbien", 0);
        objdefault.putInt("friendid", -1);
         ilist.addSFSObject(objdefault);
        SFSUserVariable isbien = SFSUserVariable.newInstance("choibien", ilist);
        
//        objdefault = new SFSObject();
//        objdefault.putDouble("tienvaoga", 0.0);
        SFSUserVariable tienvaoga = SFSUserVariable.newInstance("tienvaoga", 0.0);
        
//        objdefault = new SFSObject();
//        objdefault.putDouble("bet", 0.0);
        SFSUserVariable betgame  = SFSUserVariable.newInstance("bet", 0.0);
        for (int i = 0; i < count; i++) {
            try {
                User user_set = getParentExtension().getParentRoom().getUserList().get(i);
                user_set.setVariable(isplay);
//              user_set.setVariable(ischuong);
                user_set.setVariable(betgame);
                user_set.setVariable(tienvaoga);
                user_set.setVariable(iske);
                user_set.setVariable(isbien);
            } catch (SFSVariableException ex) {
                Logger.getLogger(StartGameEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            userplay.add(getParentExtension().getParentRoom().getUserList().get(i).getId());
        }
        
        // chon nguoi cam chuong
        
//        int  n = (int) (Math.random() * count);
//        User userchuong = getParentExtension().getParentRoom().getUserList().get(n);
//        ischuong = SFSUserVariable.newInstance("ischuong", true);
//        try {
//            userchuong.setVariable(ischuong);
//        } catch (SFSVariableException ex) {
//            Logger.getLogger(StartGameEventHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //tim chuonh
        FindChuong findchuong = new FindChuong();
        int idchuong = findchuong.timchuong(getParentExtension().getParentRoom());
        
        ISFSObject obj = new SFSObject();
        // gui xuong client gia tri: success, id nguoi cam chuong
        obj.putUtfString("status", "success");
        obj.putInt("id", idchuong);
//        obj.putInt("id", user.getId());
        send("startgame",obj,  this.getParentExtension().getParentRoom().getUserList());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.sun.corba.se.impl.naming.cosnaming.TransientNameServer;

/**
 *
 * @author MT405
 */
public class DatBien {
    public Double datbien(User user,Room room){
        Double bien = 0.0;
        ISFSArray choibien = user.getVariable("choibien").getSFSArrayValue();
//        ISFSObject ilist =  ilists.getSFSObject(0);
        ISFSObject friend = new SFSObject();
        for(int i=0;i<choibien.size();i++){
            friend = new SFSObject();
            friend = choibien.getSFSObject(i);
            int idfriend = friend.getInt("friendid");
            
            if(idfriend!= -1){
            int pointfriend = room.getUserById(idfriend).getVariable("point").getIntValue();
            int yourpoint = user.getVariable("point").getIntValue();
            if(yourpoint  > pointfriend){
                bien += friend.getDouble("tienbien");
            }else{
                bien -= friend.getDouble("tienbien");
            }
            }
        }
        return bien;
    }
}

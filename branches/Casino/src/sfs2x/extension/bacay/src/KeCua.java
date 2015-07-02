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
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

/**
 *
 * @author MT405
 */
public class KeCua {

    public Double kecua(User user, int pointchuong,Room room) {
        Double ke = 0.0;
//                           trace("list: "+list);
//                            trace("ilist: "+user.getVariable("ilist").getSFSArrayValue());
        ISFSArray choike = new SFSArray();
        choike = user.getVariable("choike").getSFSArrayValue();
//        ISFSObject ilist =  ilists.getSFSObject(0);
        ISFSObject friend = new SFSObject();
        for(int i=0;i<choike.size();i++){
            friend = new SFSObject();
            friend = choike.getSFSObject(i);
            int idfriend = friend.getInt("friendid");
            if(idfriend != -1){
           
            int pointfriend = room.getUserById(idfriend).getVariable("point").getIntValue();
            if(pointfriend > pointchuong){
                ke += friend.getDouble("tienke");
            }else{
                ke -= friend.getDouble("tienke");
            }
            }
            
        }
        return ke;
    }
}

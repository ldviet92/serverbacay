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
public class UserStartPlayEventHandler extends BaseClientRequestHandler {

    @Override
    public void handleClientRequest(User user, ISFSObject isfso){
        trace("Gui xuong client");
        ISFSObject person = new SFSObject();
        
        int people = this.getParentExtension().getParentRoom().getVariablesCount()+1;
        trace(this.getParentExtension().getParentRoom().getUserList());
        if(people >5 ){
            List<User> list1 = this.getParentExtension().getParentRoom().getUserList();
            trace(list1.get(0).getId());
            trace(this.getParentExtension().getParentRoom().getUserList());
            person.putBool("status", false);
            send("getCard", person, user);
            
        }
//        int valueChuong = 341;
        CardBaCay js4 = new CardBaCay();
        //Tao 36 la bai trong ba cay
        ArrayList<String>   list =  js4.createCard();
        // Tao 3 la cho moi nguoi choi
        String[][] listCard = js4.createCardPeople(people,list);
        // Tinh điểm cho mỗi bộ bài
        int[] point = new int[people];
        // tinh diem tung bo bai
        for (int i = 0; i < people; i++) {
            point[i] = js4.createPoint(listCard[i]);
           
        }
        trace("so nguoi choi: "+people);
        int valueChuong = point[0];
        trace("Nguoi cam chuong: "+ valueChuong);
        //choi chuong
        // so sanh voi chuong
        // cho list diem va tra ve mang nhung bai thang
        ArrayList<Integer> listWin = js4.playChuong(valueChuong, point);
        System.out.println(listWin);
//        String json = "{[";
//        for (int i = 0; i < people; i++) {
//            json += "{'id':"+ getParentExtension().getParentRoom().getUserList().get(i).getId()+",";
//            for (int j = 0; j < 3; j++) {
//                json += "'la"+(j+1)+"':"+"'"+listCard[i][j]+"',";
//            }
//            if(point[i]> 1000){
//            json += "'diem': 10,";
//            }else{
//            json += "'diem':"+ String.valueOf(point[i]).substring(0, 1)+",";
//            }
//            json+="'datga':0,'kecua':0,'datbien':0}";
//            if(i < people-1){
//                json+=",";
//            }
//        }
//       json+= "]}";
       List<JSONObject> listjson = new ArrayList<JSONObject>();
       JSONObject jsonobj = new JSONObject();
       for(int i = 0;i< people;i++){
            try {
                jsonobj.put("id", getParentExtension().getParentRoom().getUserList().get(i).getId());
                for (int j = 0; j < 3; j++) {
                    jsonobj.put("card_"+j, listCard[i][j]);
                }
                if(point[i] >1000){
                    jsonobj.put("diem", 10);
                }else{
                    jsonobj.put("diem",Integer.parseInt(String.valueOf(point[i]).substring(0, 1)));
                }
                jsonobj.put("datga",0);
                jsonobj.put("kecua",0);
                jsonobj.put("datbien",0);
//           jsonobj.put(json, list)
            } catch (JSONException ex) {
                Logger.getLogger(UserStartPlayEventHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       
//       String json = "{[{'id':1,'la1':'1-2','la2':'3-2','la3':'4-1','diem':8,'datga':0,'kecua':0,'datbien':0},{'id':2,'la1':'1-2','la2':'3-2','la3':'4-1','diem':8,'datga':0,'kecua':0,'datbien':0}]}";
       person.putUtfString("json",String.valueOf(jsonobj));
       
        trace(person);
        
        send("getcard",person,  this.getParentExtension().getParentRoom().getUserList());
    }
    
}

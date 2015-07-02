/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.buddylist.SFSBuddyVariable;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSVariableException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void handleClientRequest(User user, ISFSObject isfso) {
        trace("Id nguoi gui request UserStartPlayEvent: "+ user.getId());
        if (!user.getVariable("isplay").getBoolValue()) {
            SFSObject obj = new SFSObject();
            obj.putBool("status", false);
            obj.putUtfString("message", "Không có quyền chơi, chờ ván sau");
            send("getcard", obj, user);
        }else{
        if (!user.getVariable("ischuong").getBoolValue()) {
            SFSObject obj = new SFSObject();
            obj.putBool("status", false);
            obj.putUtfString("message", "Chương mới có quyền gửi lên");
            send("getcard", obj, user);
        }else
        {
        trace("Gui xuong client");
        ISFSObject person = new SFSObject();

//        int people = this.getParentExtension().getParentRoom().getUserList().size();
        trace(this.getParentExtension().getParentRoom().getUserList());
        int people = 0;
        for (int i = 0; i < this.getParentExtension().getParentRoom().getUserList().size(); i++) {
            if (this.getParentExtension().getParentRoom().getUserList().get(i).getVariable("isplay").getBoolValue()) {
                people++;
            }
        }
        if (people < 2) {
            List<User> list1 = this.getParentExtension().getParentRoom().getUserList();
            trace(list1.get(0).getId());
            trace(this.getParentExtension().getParentRoom().getUserList());
            person.putBool("status", false);
            person.putUtfString("message", "ít hơn 2 người, không thể chơi");
            send("getCard", person, user);

        }
//        int valueChuong = 341;
        CardBaCay js4 = new CardBaCay();
        //Tao 36 la bai trong ba cay
        ArrayList<String> list = js4.createCard();
        // Tao 3 la cho moi nguoi choi
        String[][] listCard = js4.createCardPeople(people, list);
        // Tinh điểm cho mỗi bộ bài
        int[] point = new int[people];

        //gia su nguoi cam chuong
//        int valueChuong = point[0];
//        SFSBuddyVariable userchuong = new SFSBuddyVariable("idChuong", 0);
//        trace("Nguoi cam chuong: "+ valueChuong);
//        trace("Id nguoi cam chuong: " + userchuong.getIntValue());
//        SFSBuddyVariable pointchuong = null;
        // tinh diem tung bo bai
        List<User> listuser = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < people; i++) {
            point[i] = js4.createPoint(listCard[i]);

            while (true) {
                if (this.getParentExtension().getParentRoom().getUserList().get(j).getVariable("isplay").getBoolValue()) {
                    try {
                        SFSUserVariable setpoint = SFSUserVariable.newInstance("point", point[i]);
                        getParentExtension().getParentRoom().getUserList().get(j).setVariable(setpoint);
                    } catch (SFSVariableException ex) {
                        Logger.getLogger(UserStartPlayEventHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listuser.add(getParentExtension().getParentRoom().getUserList().get(j));
                    j++;
                    break;
                } else {
                    j++;
                }
            }
        }

        trace("so nguoi choi: " + people);
        trace("list user dang choi: " + listuser);
        String jsonobj = json(people, listCard, point, user, listuser);
        person.putUtfString("json", jsonobj);
        person.putBool("status", true);
        send("getcard", person, this.getParentExtension().getParentRoom().getUserList());
        }
        
        }
        
    }

    private String json(int people, String[][] listCard, int[] point, User user, List<User> listuser) {
        List<JSONObject> listjson = new ArrayList<>();
        String obj = null;
        Connection conn = null;
        //tim chuong
        //thang gui len la thang cam chuong
        //check xem no co phai thang cam chuong khong

        if (!user.getVariable("ischuong").getBoolValue()) {
            //false
        }
        int pointchuong = user.getVariable("point").getIntValue();
        int userchuong = user.getId();
        //tinh diem cao nhat
        MaxPoint maxPoint = new MaxPoint();
        int maxpoint = maxPoint.maxpoint(point, pointchuong);
        try {

            try {
                conn = getParentExtension().getParentZone().getDBManager().getConnection();
                conn.setAutoCommit(false);
                JSONObject jsonobj = new JSONObject();
                JSONObject jsonchuong = new JSONObject();
                Double cuoc = 0.0;
                Double cuocchuong = 0.0;
                Double ga = 0.0;
                Double ke = 0.0;
                Double kechuong = 0.0;
                Double bien = 0.0;
                Double sum = 0.0;
                int idchuongmoi = -1;
                //xem bao nhieu nguoi dat ga
                int countga = 0;
                for (int j = 0; j < listuser.size(); j++) {
                    if (listuser.get(j).getVariable("tienvaoga").getDoubleValue() > 0) {
                        countga++;
                    }
                }
                for (int i = 0; i < listuser.size(); i++) {

                    if (listuser.get(i).getId() == userchuong) {
                        jsonchuong = new JSONObject();
                        jsonchuong.put("id", userchuong);
                        for (int j = 0; j < 3; j++) {
                            String[] quan = listCard[i][j].split("-");
                            jsonchuong.put("card_" + j, quan[0]);
                            jsonchuong.put("matter_" + j, quan[1]);
                        }
                        if (point[i] > 1000) {
                            jsonchuong.put("diem", 10);
                        } else {
                            jsonchuong.put("diem", Integer.parseInt(String.valueOf(point[i]).substring(0, 1)));
                        }
                    } else {
                        jsonobj = new JSONObject();
                        jsonobj.put("id", listuser.get(i).getId());
                        trace("id cua nguoi choi: " + listuser.get(i).getId());
                        for (int j = 0; j < 3; j++) {
                            String[] quan = listCard[i][j].split("-");
                            jsonobj.put("card_" + j, quan[0]);
                            jsonobj.put("matter_" + j, quan[1]);
                        }
                        if (point[i] > 1000 && point[i] < 2000) {
                            jsonobj.put("diem", 10);
                        } else if (point[i] >= 2000) {
                            jsonobj.put("diem", point[i]);
                        } else {
                            jsonobj.put("diem", Integer.parseInt(String.valueOf(point[i]).substring(0, 1)));
                        }

                        if (listuser.get(i).getId() != userchuong) {
                            //dat cuoc
                            DatCuoc datcuoc = new DatCuoc();
                            cuoc = datcuoc.datCuoc(pointchuong, point[i], listuser.get(i));
                            cuocchuong -= cuoc;
                            if (point[i] > 1000 && point[i] < 2000 && point[i] > pointchuong) {
                                idchuongmoi = listuser.get(i).getId();
                            }
                            //vao ga

                            VaoGa vaoga = new VaoGa();
                            ga = vaoga.vaoga(maxpoint, point[i], listuser.get(i), countga);

                            //                  ke cua
                            KeCua kecua = new KeCua();
                            ke = kecua.kecua(listuser.get(i), pointchuong, getParentExtension().getParentRoom());
                            kechuong -= ke;
                            //dat bien
                            DatBien datbien = new DatBien();
                            bien = datbien.datbien(listuser.get(i), getParentExtension().getParentRoom());
                            trace("Ket qua cuoc: " + cuoc);
                            trace("Ket qua ga: " + ga);
                            trace("Ket qua ke: " + ke);

                        }
                        jsonobj.put("cuoc", cuoc);
                        jsonobj.put("datga", ga);
                        jsonobj.put("kecua", ke);
                        jsonobj.put("datbien", bien);
                        trace("json tung user khong phai chuong: " + jsonobj);
                        listjson.add(jsonobj);
                    }
                    sum = cuoc + ga + ke + bien;
                    PreparedStatement querymoney = conn.prepareStatement("select balance from user where id = ?");
                    querymoney.setInt(1, listuser.get(i).getVariable("iddb").getIntValue());
                    ResultSet result = querymoney.executeQuery();
                    double money = 0.0;
                    while(result.next()){
                        money = result.getDouble("balance");
                    }
                   conn.commit();
                   querymoney.close();
                   double endmoney = 0.0;
                    if (sum < 0 && (money + sum) < 0) {

                        PreparedStatement stmt = conn.prepareStatement("update user set balance = 0 where id=?");
                        stmt.setDouble(1, sum);
                        stmt.setInt(2, listuser.get(i).getVariable("iddb").getIntValue());
                        stmt.executeUpdate();
                        conn.commit();
                        stmt.close();
                    } else {
                        endmoney = money + sum;
                        PreparedStatement stmt = conn.prepareStatement("update user set balance = ? where id=?");
                        stmt.setDouble(1, endmoney);
                        stmt.setInt(2, listuser.get(i).getVariable("iddb").getIntValue());
                        stmt.executeUpdate(); 
                        conn.commit();
                        stmt.close();
                        
                    }
                    jsonobj.put("money", endmoney);

                }
                if(idchuongmoi != -1){
                                SFSUserVariable ischuong = SFSUserVariable.newInstance("ischuong", true);
                                SFSUserVariable nochuong = SFSUserVariable.newInstance("ischuong", false);
                                try {
                                    user.setVariable(nochuong);
                                    getParentExtension().getParentRoom().getUserById(idchuongmoi).setVariable(ischuong);
                                } catch (SFSVariableException ex) {
                                    Logger.getLogger(UserStartPlayEventHandler.class.getName()).log(Level.SEVERE, null, ex);
                                }
                }

                jsonchuong.put("cuoc", cuocchuong);
                jsonchuong.put("datga", 0);
                jsonchuong.put("kecua", kechuong);
                jsonchuong.put("datbien", 0);
                listjson.add(jsonchuong);
                trace("cuoc_chuong: " + cuocchuong);
                trace("ke chuong: " + kechuong);
                sum = cuocchuong + kechuong;
                PreparedStatement querymoney = conn.prepareStatement("select balance from user where id = ?");
                    querymoney.setInt(1, user.getVariable("iddb").getIntValue());
                    ResultSet result = querymoney.executeQuery();
                    double money = 0.0;
                    while(result.next()){
                        money = result.getDouble("balance");
                    }
                   conn.commit();
                   querymoney.close();
                   double endmoney = 0.0;
                    if (sum < 0 && (money + sum) < 0) {

                        PreparedStatement stmt = conn.prepareStatement("update user set balance = 0 where id=?");
                        stmt.setDouble(1, sum);
                        stmt.setInt(2, user.getVariable("iddb").getIntValue());
                        stmt.executeUpdate();
                        conn.commit();
                        stmt.close();
                    } else {
                        PreparedStatement stmt = conn.prepareStatement("update user set balance = balance+? where id=?");
                        stmt.setDouble(1, sum);
                        stmt.setInt(2, user.getVariable("iddb").getIntValue());
                        stmt.executeUpdate(); 
                        conn.commit();
                        stmt.close();
                        endmoney = money + sum;
                    }
//                PreparedStatement stmt = conn.prepareStatement("update user set balance = balance+? where id=?");
//                stmt.setDouble(1, sum);
//                stmt.setInt(2, user.getVariable("iddb").getIntValue());
//                stmt.executeUpdate();

//                conn.commit();
//                stmt.close();
                    jsonchuong.put("money", endmoney);
                conn.close();
            } catch (SQLException ex) {
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(UserStartPlayEventHandler.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
                Logger.getLogger(UserStartPlayEventHandler.class.getName()).log(Level.SEVERE, null, ex);

            }
        } catch (JSONException e) {
        }
        obj = String.valueOf(listjson);
        return obj;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.variables.SFSUserVariable;
import java.util.ArrayList;

/**
 *
 * @author MT405
 */
public class DatCuoc {
//        public ArrayList<Integer> playChuong(int valueChuong,int [] point){
//        ArrayList<Integer> a = new ArrayList<Integer>();
//        for (int i = 0; i < point.length; i++) {
//            if(point[i]>valueChuong){
//                a.add(point[i]);
//            }
//        }
//        return a;
//    }

    public Double datCuoc(int pointchuong, int pointuser, User user) {
        Double money = 0.0;
        if (pointchuong > pointuser) {
            if (pointchuong > 1000 && pointchuong < 2000) {
                money = (user.getVariable("bet").getDoubleValue() * -2);
            } else if (pointchuong >= 2000) {
                money = (user.getVariable("bet").getDoubleValue() * -3);
            } else {
                money = (user.getVariable("bet").getDoubleValue() * -1);
            }
        } else {
            if (pointuser > 1000 && pointuser < 2000) {
                money = (user.getVariable("bet").getDoubleValue() * 2);
            } else if (pointuser >= 2000) {
                money = (user.getVariable("bet").getDoubleValue() * 3);
            } else {
                money = user.getVariable("bet").getDoubleValue();
            }
        }
        return money;
    }
}

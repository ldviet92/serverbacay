/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author MT405
 */

public class CardBaCay {
        static String peoples[][];
     // Tao 36 la bai
 public  ArrayList<String> createCard() {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 1;i<= 9;i++){
            for(int j=1;j<=4;j++){
              list.add(i+"-"+j);
            }
        }
        return list;
//        System.out.println(list);
    }

    public String[][] createCardPeople(int people,ArrayList<String> list) {
        peoples = new  String[people][3];
        int cards = people*3;
        String arraycards[] = new String[cards];
        int count = 0;
        int index = 0;
        for (int i = 0; i < cards; i++) {
            index = (int) (Math.random()*list.size());
            arraycards[count] = list.get(index);
            list.remove(index);
            count++;
        }
        System.out.println(Arrays.toString(arraycards));
        // chia 3 bai cho tung nguoi
        int j = 0;
        int countPeople = 0;
        for (int i = 0; i < arraycards.length; i++) {
//            System.out.println(arraycards[j]);
               peoples[countPeople][j] = arraycards[i];
                j++;
               if(j==3){
               j = 0;
               countPeople++;
               }
        }
        return peoples;
//         System.out.println(Arrays.toString(peoples[1]));
//         JavaApplication4 jv4 = new JavaApplication4(); 
//         int point1 = jv4.createPoint(peoples[1]);
//         System.out.println(point1);
         
         
    }

    public int createPoint(String[] point) {
        int countPoint = 0;   // diem tong 3 quan
        int maxMaterial = 0; // chat lon nhat trong bo
        int val = 0;
        int val1 = 0; 
        int maxItemCard = 0; // quan bai lon nhat trong bo
        for (int i = 0; i < 3; i++) {
            // Tính điểm đầu tiên
            val1 = Integer.parseInt(point[i].split("-")[0]);
            countPoint += val1;
            // chat lon nhat trong bo
            val = Integer.parseInt(point[i].split("-")[1]);
            if(maxMaterial <  val){
                maxMaterial = val;
              // xem gia tri cua chat lon nhat
                maxItemCard = val1;
            }
        }
        if(countPoint >10){
           
            countPoint = Integer.parseInt(String.valueOf(countPoint).substring(1));
//            return countPoint;
//            System.out.println(String.valueOf(countPoint).split("")[1]);
        }
        
        // Diem dau tien nhan voi 100
        countPoint = countPoint*100;
        maxMaterial = maxMaterial * 10;
        int result = countPoint + maxMaterial + maxItemCard;
        if(maxItemCard + maxItemCard == 41){
            result += 10;
        }
        return result;
    }
    
    //so sanh cac bo bai voi nhau
    //choi Chuong
    // Nguoi choi dat o dau van, het thoi gian so bai voi nha chuong. neu thang nha chuong thi se nhan duoc so tien minh dat cuoc
    public ArrayList<Integer> playChuong(int valueChuong,int [] point){
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < point.length; i++) {
            if(point[i]>valueChuong){
                a.add(point[i]);
            }
        }
        return a;
    }
}

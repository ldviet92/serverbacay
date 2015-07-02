/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfs2x.extension.bacay.src;

/**
 *
 * @author MT405
 */
public class MaxPoint {

    public int maxpoint(int[] point, int pointchuong) {
        int max = 0;
        for (int i = 0; i < point.length; i++) {
            if (point[i] != pointchuong) {
                if (max < point[i]) {
                    max = point[i];
                }
            }
        }
        return max;
    }

}

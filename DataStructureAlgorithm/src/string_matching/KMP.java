/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string_matching;

/**
 *
 * @author Nam
 */
public class KMP {

    public int[] nextArray(String pattern) {
        if (pattern.isEmpty()) {
            return null;
        } else {
            int l = pattern.length();
            int[] next = new int[l];
            next[0] = -1;
            for (int i = 1; i < l; i++) {
                int k = i - 1;
                while (k > 0) {
                    if (pattern.substring(0, k).equals(pattern.substring(i - k, i))) {
                        if (pattern.charAt(i) == pattern.charAt(k)) {
                            next[i] = next[k];
                        } else {
                            next[i] = k;
                        }
                        break;
                    } else {
                        k--;
                    }
                }
            }
            return next;
        }
    }

    public int indexOf(String text, String pattern) {
        int i = 0; //index to find
        int j = 0; //index of pattern
        int lText = text.length();
        int lPattern = pattern.length();
        int[] next = this.nextArray(pattern);
        while (i < lText && j < lPattern) {
            if (j == -1) {
                i++;
                j++;
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == lPattern ? (i - lPattern) : -1;
    }
}

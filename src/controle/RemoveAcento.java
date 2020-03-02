/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.text.Normalizer;

/**
 *
 * @author geniv
 */
public class RemoveAcento {
    public static String removeAcentos(String str) {
    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
}
}

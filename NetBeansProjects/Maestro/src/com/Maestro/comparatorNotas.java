/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Maestro;

import java.util.Comparator;

/**
 *
 * @author rodrigo.baya
 */
public class comparatorNotas implements Comparator<MidiNote> {

    @Override
    public int compare(MidiNote t, MidiNote t1) {
        return t.compare(t, t1);
    }
    
}

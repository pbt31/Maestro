/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Maestro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author rodrigo.baya
 */
class Midi2Partiture {
    
    private List<List<MidiNote>> channels;
    private boolean[] usedChannels ;
    
    public Midi2Partiture(){
        int i;
        usedChannels = new boolean[16];
        for (i=0; i < 16; i++){
            usedChannels[i]=false;
        }
        channels = new LinkedList<>(); 
    }
    
    public void transform(String inputMidi, String outputFile,int accel,int cantTiempo) throws FileNotFoundException, UnsupportedEncodingException {
        
        String midiFileName = inputMidi;
        byte[] data;
        float quarter;
        try {
            File info = new File(midiFileName);
            FileInputStream file = new FileInputStream(midiFileName);

            data = new byte[ (int)info.length() ];
            int offset = 0;
            int len = (int)info.length();
            while (true) {
                if (offset == len)
                    break;
                int n = file.read(data, offset, len- offset);
                if (n <= 0)
                    break;
                offset += n;
            }
            file.close();
        }
        catch(IOException e) {
            System.out.println("ehhhh");
            return;
        }

        MidiFile f = new MidiFile(data, midiFileName);
        quarter = f.getQuarterNote();
//        System.out.println(f.toString());
        MidiTrack mtrack;
        MidiOptions mopt = new MidiOptions(f);
        int finalPulse = Math.round(((cantTiempo * quarter)/((float)mopt.tempo/1000000))*accel);
        List<List<MidiNote>> auxchannels;
        List<String> listnotaux =null;
        int i,j;
        try (PrintWriter writer = new PrintWriter(outputFile, "UTF-8")) {
            List<List<String>> channelsNotas = new LinkedList<>();
            //colapsa los tracks en uno solo
            mtrack = colapsarTracks(f.getTracks());
            mtrack = correctTrack(mtrack,quarter,accel);
            System.out.println(mtrack);
            //separa el track en canales
            splitIntoChannels(mtrack.getNotes(),finalPulse);
            auxchannels = getChannels(); 
            for (i=0;i < channels.size();i++){            
                    listnotaux = parseChannel(channels.get(i),quarter);
                    channelsNotas.add(listnotaux);
            }
            if (channelsNotas.size() < 15) {           
                for (i=0;i < channelsNotas.size();i++){               
                    if (i < 9){
                        writer.print("V" + Integer.toString(i)+" ");

                    } else {
                        writer.print("V" + Integer.toString(i+1)+" ");

                    }
                    for (j=0;j < channelsNotas.get(i).size();j++){
                        writer.print(channelsNotas.get(i).get(j) + " ");
                    }
                    writer.println();
                }
            } else {
                i=0;
                while (i < channelsNotas.size()){                   
                    writer.print("V" + Integer.toString(i)+" ");
                    for (j=0;j < channelsNotas.get(i).size();j++){
                        writer.print(channelsNotas.get(i).get(j) + " ");
                    }
                    writer.println();
                    if (i==8){
                        i = i + 2;
                    } else {
                        i = i + 1;
                    }
                }
            }
        }
        
        

    }
    
    private MidiTrack colapsarTracks(ArrayList<MidiTrack> tracks){         
        int i,j;
        MidiTrack mtrack;        
        mtrack = new MidiTrack(1);
        for (i = 0; i < tracks.size(); i++){
            for (j = 0; j < tracks.get(i).getNotes().size();j++){
                if (tracks.get(i).getNotes().get(j).getChannel()!= 9){
                    mtrack.AddNote(tracks.get(i).getNotes().get(j));
                }
            }
                
        }
        Collections.sort(mtrack.getNotes(), new comparatorNotas());
        return mtrack;
    }
        
    private  void splitIntoChannels (List<MidiNote> mt, int finalPulse){
        int i;
        List<List<MidiNote>> channels = new LinkedList<>();
        for (i=0; i < 16 ; i++){
            channels.add(new LinkedList<MidiNote>());
        }
        
        int tstart = 0;
        int tend = 0;
        int dnt = 0;
        int canal = 0;
        i = 0;
        while ((i < mt.size()) && (tstart <= finalPulse)){
            dnt = duracionNotaTrack(tstart,tend,mt.get(i));
            if (dnt > 0){
                canal = cambiarChannel(mt.get(i),channels);
            }
            channels.get(canal).add(mt.get(i));
            tstart = mt.get(i).getStartTime();
               if (tend < mt.get(i).getEndTime()){
                   tend = mt.get(i).getEndTime();
               }
            i++;
        }  
        addToChannels(channels);
    }
    
     private List<String> parseChannel(List<MidiNote> lmn, float quarter) throws FileNotFoundException, UnsupportedEncodingException{
        MidiNote mnote;
        String notaux;
        List<String> listNoteString = new LinkedList();
        int tstart = 0;
        int tend = 0;
        int dnt = 0;
        for (int j=0; j < lmn.size(); j++){
               mnote = lmn.get(j);
               dnt = duracionNotaTrack(tstart,tend,mnote);                  
               if (dnt < 0 ){
                   notaux=calcularSilencios(-dnt,quarter);
                   if (!notaux.isEmpty()){
                    listNoteString.add(notaux);
                   }
               }
               notaux = mnote.getScale() + tiempoNota(mnote.getDuration(),quarter);
               listNoteString.add(notaux);
               tstart = mnote.getStartTime();
               if (tend < mnote.getEndTime()){
                   tend = mnote.getEndTime();
               }
            }
       return listNoteString;
    }
     
     private int cambiarChannel(MidiNote mnote,List<List<MidiNote>> channels){
        int channel = 0;
        List<MidiNote> canal;
        int dnt = 1;
        MidiNote ultima;
        int i=0;
            while((i < channels.size()) && (dnt > 0)){                
                canal = channels.get(i);
                if (canal.isEmpty()){
                    dnt = 0;
                    channel=i;
                } else {
                ultima = canal.get(canal.size()-1);
                dnt = duracionNotaTrack(ultima.getStartTime(),ultima.getEndTime(),mnote);
                if (dnt <= 0){
                    channel=i;
                }
                i++;
                }
            }
        return channel;
    }
     
    private int duracionNotaTrack(int tstart, int tend, MidiNote sigNota){
        int solapamiento;
        solapamiento = (Math.max(tend, sigNota.getEndTime()) - Math.min(tstart, sigNota.getStartTime())) - (Math.abs(tstart - sigNota.getStartTime()) + Math.abs(tend - sigNota.getEndTime()));
        return solapamiento;      
    }
    
    private String tiempoNota(int duracion,float quarter){
        float relacionquarter;
        String nota;
        
        relacionquarter=(duracion)/(quarter);
        
        double[] tiempoNotas = {
            0.25, 0.375, 0.5, 0.75, 1, 1.5, 2, 3, 4, 6
        };
        int i;        
        
        double[] intervaloNota = new double[9];
        
        for (i=0; i < intervaloNota.length; i++){
//            intervaloNota[i] = (tiempoNotas[i] + ((tiempoNotas[i+1] - tiempoNotas[i])*0.5 ));
            intervaloNota[i] = tiempoNotas[i];
        }
        
        if (relacionquarter <= intervaloNota[0]){
            nota="s";
        } else if (relacionquarter == intervaloNota[1]){
            nota="s.";
        } else if (relacionquarter == intervaloNota[2] ){
            nota="i";
        } else if (relacionquarter == intervaloNota[3] ){
            nota="i.";
        } else if (relacionquarter == intervaloNota[4] ){
            nota="q";
        } else if (relacionquarter == intervaloNota[5] ){
            nota="q.";
        } else if (relacionquarter == intervaloNota[6] ){
            nota="h";
        } else if (relacionquarter == intervaloNota[7] ){
            nota="h.";
        } else if (relacionquarter == intervaloNota[8] ){
            nota="w";
        } else {
            nota="w.";
        }
        
        return nota;
    }
    
    
    
    private String calcularSilencios(int dnt,float quarter){
        String silencios = "";
        String tiempoString;
        //lo paso a positivo
        float relacionquarter;
        double[] tiempoNotas = {
            0.0625, 0.125, 0.25, 0.375, 0.5, 0.75, 1, 1.5, 2, 3, 4, 6
        };
        int i;        

        double[] intervaloNota = new double[11];
        for (i=0; i < intervaloNota.length; i++){
//            intervaloNota[i] = (tiempoNotas[i] + ((tiempoNotas[i+1] - tiempoNotas[i])*0.5 ));
              intervaloNota[i] = tiempoNotas[i];
        }
        
        while ((Math.abs(dnt) >= quarter/16)){
            
            relacionquarter=(dnt)/(quarter); 
            
            if (relacionquarter < intervaloNota[0]){
                tiempoString="x";
                dnt = dnt - (int)quarter/16;
            } else if (relacionquarter < intervaloNota[1]){
                tiempoString="t";
                dnt = dnt - (int)quarter/8;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/16;
                   tiempoString="x";
                }
                
            } else if (relacionquarter < intervaloNota[2]){
                tiempoString="s";
                dnt = dnt - (int)quarter/4;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/8;
                   tiempoString="t";
                }
                
            } else if (relacionquarter < intervaloNota[3]){
                tiempoString="s.";
                dnt = dnt - (int)quarter/4 - (int)quarter/8 ;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/8;
                   tiempoString="s";
                }
                
            } else if (relacionquarter < intervaloNota[4] ){
                tiempoString="i";
                dnt = dnt - (int)quarter/2;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/8;
                   tiempoString="s.";
                }
            } else if (relacionquarter < intervaloNota[5] ){
                tiempoString="i.";
                dnt = dnt - (int)quarter/2 - (int)quarter/4;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/4;
                   tiempoString="i";
                }
                
            } else if (relacionquarter < intervaloNota[6] ){
                tiempoString="q";
                dnt = dnt - (int)quarter;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/4;
                   tiempoString="i.";
                }
                
            } else if (relacionquarter < intervaloNota[7] ){
                tiempoString="q.";
                dnt = dnt - (int)quarter - (int)quarter/2;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/2;
                   tiempoString="q";
                }
                
            } else if (relacionquarter < intervaloNota[8] ){
                tiempoString="h";
                dnt = dnt - (int)quarter*2;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter/2;
                   tiempoString="q.";
                }
                
            } else if (relacionquarter < intervaloNota[9] ){
                tiempoString="h.";
                dnt = dnt - (int)quarter*2 -(int)quarter;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter;
                   tiempoString="h";
                }
                
            } else if (relacionquarter < intervaloNota[10] ){
                tiempoString="w";
                dnt = dnt - (int)quarter*4;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter;
                   tiempoString="h.";                   
                }
                
            } else{
                tiempoString="w.";
                dnt = dnt - (int)quarter*4 - (int)quarter*2;
                
                if ((dnt < 0) && ((Math.abs(dnt) >= quarter/16))){
                   dnt = dnt + (int)quarter*2;
                   tiempoString="w";
                }
                
            }
            
            silencios = silencios + "R" + tiempoString + " "; 
            
        }
        //saco el ultimo blanco del ultimo silencio
        if (!silencios.isEmpty()){
            silencios = silencios.substring(0, silencios.length()-1);
        }
        
        return silencios;
    }
  
    private void addToChannels(List<List<MidiNote>> inputChannels){
        int i,j;
        for (i=0; i < inputChannels.size(); i++){
            if (!inputChannels.get(i).isEmpty()){
                j=0;
                while (usedChannels[j]){
                    j++;
                }
                if (j < 16){
                 channels.add(inputChannels.get(i));
                 usedChannels[j]=true;
                } else{
                    System.out.println("ERROR : El Midi Ingresado posee mas de 16 canales");
                    System.exit(0);
                }
            }
        }
    }
    
    public List<List<MidiNote>> getChannels(){
        return channels;
    }
    
    public MidiTrack correctTrack(MidiTrack mt,float quarter,int accel){
        MidiTrack accelTrack = new MidiTrack(1);
        List<MidiNote> lmn;
        MidiNote maux;
        int i;
        lmn = mt.getNotes();
        for (i=0; i < lmn.size(); i++){
            maux = new MidiNote((correctStartNote(lmn.get(i).getStartTime(),quarter))*accel,lmn.get(i).getChannel(),lmn.get(i).getNumber(),(correctDurationNote(lmn.get(i).getDuration(),quarter))*accel);
            accelTrack.AddNote(maux);
        }
        return accelTrack;
    }
    
    public int correctStartNote(int nstart,float quarter){
        float tiempo;
        float minTime = quarter/8;
        tiempo = minTime*Math.round(nstart/minTime);
        return (int)tiempo;
    }
    
    public int correctDurationNote(int duration,float quarter){
        float tiempo = 0;
        float relacionquarter;
        
        relacionquarter=(duration)/(quarter);       
        
        double[] tiempoNotas = {
            0.25, 0.375, 0.5, 0.75, 1, 1.5, 2, 3, 4, 6
        };
        int i;        
        
        double[] intervaloNota = new double[9];
        
        for (i=0; i < intervaloNota.length; i++){
            intervaloNota[i] = (tiempoNotas[i] + ((tiempoNotas[i+1] - tiempoNotas[i])*0.5 ));
        }      
        
        if (relacionquarter < intervaloNota[0]){
            tiempo = quarter/4;
        } else if (relacionquarter < intervaloNota[1]){
            tiempo = quarter/4 + quarter/8;
        } else if (relacionquarter < intervaloNota[2] ){
            tiempo = quarter/2;
        } else if (relacionquarter < intervaloNota[3] ){
            tiempo = quarter/2 + quarter/4;
        } else if (relacionquarter < intervaloNota[4] ){
            tiempo = quarter;
        } else if (relacionquarter < intervaloNota[5] ){
            tiempo = quarter + quarter/2;
        } else if (relacionquarter < intervaloNota[6] ){
            tiempo = quarter*2;
        } else if (relacionquarter < intervaloNota[7] ){
            tiempo = quarter*2 +quarter;
        } else if (relacionquarter < intervaloNota[8] ){
            tiempo = quarter*4;
        } else{
            tiempo = quarter*4 + quarter*2;
        }
        
        return (int)tiempo;        
    }
    
}

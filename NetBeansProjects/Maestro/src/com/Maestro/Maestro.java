/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Maestro;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author rodrigo.baya
 */
public class Maestro {
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
        
//        generateMozart();
//        generateBach();
//        generateBeethoven();
//        generateTchaikovsky();
          generateBeatles();
          
       
       
    }
    
 private static void generateMozart() throws FileNotFoundException, UnsupportedEncodingException{
    String name1 = "BD midi/Mozart/mozk1";
    String name2 = "BD midi/Mozart/mozk2";
    String name3 = "BD midi/Mozart/mozk3";
    String name4 = "BD midi/Mozart/mozk4";
    String name5 = "BD midi/Mozart/mozk5";
    String name6 = "BD midi/Mozart/mozk6";
    String name7 = "BD midi/Mozart/mozk7";
    String name8 = "BD midi/Mozart/mozk8";
    String name9 = "BD midi/Mozart/mozk9";
    String name10 = "BD midi/Mozart/mozk10";

    Midi2Partiture m2p1 = new Midi2Partiture();
    Midi2Partiture m2p2 = new Midi2Partiture();
    Midi2Partiture m2p3 = new Midi2Partiture();
    Midi2Partiture m2p4 = new Midi2Partiture();        
    Midi2Partiture m2p5 = new Midi2Partiture();
    Midi2Partiture m2p6 = new Midi2Partiture();
    Midi2Partiture m2p7 = new Midi2Partiture();
    Midi2Partiture m2p8 = new Midi2Partiture();
    Midi2Partiture m2p9 = new Midi2Partiture();
    Midi2Partiture m2p10 = new Midi2Partiture();

    m2p1.transform(name1 + ".mid", name1 + ".txt",2,20);
    m2p2.transform(name2 + ".mid", name2 + ".txt",2,20);
    m2p3.transform(name3 + ".mid", name3 + ".txt",2,20);
    m2p4.transform(name4 + ".mid", name4 + ".txt",3,20);
    m2p5.transform(name5 + ".mid", name5 + ".txt",2,20);       
    m2p6.transform(name6 + ".mid", name6 + ".txt",2,20);
    m2p7.transform(name7 + ".mid", name7 + ".txt",2,20);
    m2p8.transform(name8 + ".mid", name8 + ".txt",2,20);
    m2p9.transform(name9 + ".mid", name9 + ".txt",2,20);
    m2p10.transform(name10 + ".mid", name10 + ".txt",2,20);
 }
 
 private static void generateBach() throws FileNotFoundException, UnsupportedEncodingException{
    
    String name1 = "BD midi/Bach/jsbach1";
    String name2 = "BD midi/Bach/jsbach2";
    String name3 = "BD midi/Bach/jsbach3";
    String name4 = "BD midi/Bach/jsbach4";
    String name5 = "BD midi/Bach/jsbach5";
    String name6 = "BD midi/Bach/jsbach6";
    String name7 = "BD midi/Bach/jsbach7";
    String name8 = "BD midi/Bach/jsbach8";
    String name9 = "BD midi/Bach/jsbach9";
    String name10 = "BD midi/Bach/jsbach10";
    String name11 = "BD midi/Bach/jsbach11";
    String name12 = "BD midi/Bach/jsbach12";
    String name13 = "BD midi/Bach/jsbach13";
    String name14 = "BD midi/Bach/jsbach14";
    String name15 = "BD midi/Bach/jsbach15";
    String name16 = "BD midi/Bach/jsbach16";
    String name17 = "BD midi/Bach/jsbach17";
    String name18 = "BD midi/Bach/jsbach18";
    String name19 = "BD midi/Bach/jsbach19";
    String name20 = "BD midi/Bach/jsbach20";
    String name21 = "BD midi/Bach/jsbach21";
    String name22 = "BD midi/Bach/jsbach22";
    String name23 = "BD midi/Bach/jsbach23";
    String name24 = "BD midi/Bach/jsbach24";
    String name25 = "BD midi/Bach/jsbach25";

    Midi2Partiture m2p1 = new Midi2Partiture();
    Midi2Partiture m2p2 = new Midi2Partiture();
    Midi2Partiture m2p3 = new Midi2Partiture(); 
    Midi2Partiture m2p4 = new Midi2Partiture();
    Midi2Partiture m2p5 = new Midi2Partiture();
    Midi2Partiture m2p6 = new Midi2Partiture();    
    Midi2Partiture m2p7 = new Midi2Partiture();
    Midi2Partiture m2p8 = new Midi2Partiture();
    Midi2Partiture m2p9 = new Midi2Partiture();
    Midi2Partiture m2p10 = new Midi2Partiture();
    Midi2Partiture m2p11 = new Midi2Partiture();
    Midi2Partiture m2p12 = new Midi2Partiture();
    Midi2Partiture m2p13 = new Midi2Partiture(); 
    Midi2Partiture m2p14 = new Midi2Partiture();
    Midi2Partiture m2p15 = new Midi2Partiture();
    Midi2Partiture m2p16 = new Midi2Partiture();    
    Midi2Partiture m2p17 = new Midi2Partiture();
    Midi2Partiture m2p18 = new Midi2Partiture();
    Midi2Partiture m2p19 = new Midi2Partiture();
    Midi2Partiture m2p20 = new Midi2Partiture();
    Midi2Partiture m2p21 = new Midi2Partiture();
    Midi2Partiture m2p22 = new Midi2Partiture();
    Midi2Partiture m2p23 = new Midi2Partiture();
    Midi2Partiture m2p24 = new Midi2Partiture();
    Midi2Partiture m2p25 = new Midi2Partiture();
    
    m2p1.transform(name1 + ".mid", name1 + ".txt",2,20);
    m2p2.transform(name2 + ".mid", name2 + ".txt",2,20);
    m2p3.transform(name3 + ".mid", name3 + ".txt",2,20);
    m2p4.transform(name4 + ".mid", name4 + ".txt",2,20);
    m2p5.transform(name5 + ".mid", name5 + ".txt",2,20);
    m2p6.transform(name6 + ".mid", name6 + ".txt",2,20);
    m2p7.transform(name7 + ".mid", name7 + ".txt",2,20);
    m2p8.transform(name8 + ".mid", name8 + ".txt",2,20);
    m2p9.transform(name9 + ".mid", name9 + ".txt",2,20);
    m2p10.transform(name10 + ".mid", name10 + ".txt",2,20);
    m2p11.transform(name11 + ".mid", name11 + ".txt",1,20);
    m2p12.transform(name12 + ".mid", name12 + ".txt",2,20);    
    m2p13.transform(name13 + ".mid", name13 + ".txt",1,20);   
    m2p14.transform(name14 + ".mid", name14 + ".txt",1,20);
    m2p15.transform(name15 + ".mid", name15 + ".txt",2,20);
    m2p16.transform(name16 + ".mid", name16 + ".txt",2,20);    
    m2p17.transform(name17 + ".mid", name17 + ".txt",2,20);
    m2p18.transform(name18 + ".mid", name18 + ".txt",1,20);
    m2p19.transform(name19 + ".mid", name19 + ".txt",1,20);
    m2p20.transform(name20 + ".mid", name20 + ".txt",1,20);
    m2p21.transform(name21 + ".mid", name21 + ".txt",1,20);    
    m2p22.transform(name22 + ".mid", name22 + ".txt",1,20);
    m2p23.transform(name23 + ".mid", name23 + ".txt",2,20);    
    m2p24.transform(name24 + ".mid", name24 + ".txt",1,20);
    m2p25.transform(name25 + ".mid", name25 + ".txt",2,20);
    }
 
    
private static void generateBeethoven() throws FileNotFoundException, UnsupportedEncodingException{
    
    String name1 = "BD midi/Beethoven/beeth1";
    String name2 = "BD midi/Beethoven/beeth2";
    String name3 = "BD midi/Beethoven/beeth3";
    String name4 = "BD midi/Beethoven/beeth4";
    String name5 = "BD midi/Beethoven/beeth5";
    String name6 = "BD midi/Beethoven/beeth6";
    String name7 = "BD midi/Beethoven/beeth7";
    String name8 = "BD midi/Beethoven/beeth8";
    String name9 = "BD midi/Beethoven/beeth9";
    String name10 = "BD midi/Beethoven/beeth10";
    
    Midi2Partiture m2p1 = new Midi2Partiture(); 
    Midi2Partiture m2p2 = new Midi2Partiture();
    Midi2Partiture m2p3 = new Midi2Partiture();    
    Midi2Partiture m2p4 = new Midi2Partiture();
    Midi2Partiture m2p5 = new Midi2Partiture();
    Midi2Partiture m2p6 = new Midi2Partiture();
    Midi2Partiture m2p7 = new Midi2Partiture();
    Midi2Partiture m2p8 = new Midi2Partiture();
    Midi2Partiture m2p9 = new Midi2Partiture();
    Midi2Partiture m2p10 = new Midi2Partiture();
    
    m2p1.transform(name1 + ".mid", name1 + ".txt",2,20);
    m2p2.transform(name2 + ".mid", name2 + ".txt",2,20);
    m2p3.transform(name3 + ".mid", name3 + ".txt",2,20);
    m2p4.transform(name4 + ".mid", name4 + ".txt",2,20);
    m2p5.transform(name5 + ".mid", name5 + ".txt",2,20);
    m2p6.transform(name6 + ".mid", name6 + ".txt",2,20);
    m2p7.transform(name7 + ".mid", name7 + ".txt",2,20);
    m2p8.transform(name8 + ".mid", name8 + ".txt",2,20);
    m2p9.transform(name9 + ".mid", name9 + ".txt",2,20);
    m2p10.transform(name10 + ".mid", name10 + ".txt",2,20);
    
    }

private static void generateTchaikovsky() throws FileNotFoundException, UnsupportedEncodingException{
    
    String name1 = "BD midi/Tchaikovsky/tchai1";
    String name2 = "BD midi/Tchaikovsky/tchai2";
    String name3 = "BD midi/Tchaikovsky/tchai3";
    String name4 = "BD midi/Tchaikovsky/tchai4";
    String name5 = "BD midi/Tchaikovsky/tchai5";
    String name6 = "BD midi/Tchaikovsky/tchai6";
    String name7 = "BD midi/Tchaikovsky/tchai7";
    String name8 = "BD midi/Tchaikovsky/tchai8";
    String name9 = "BD midi/Tchaikovsky/tchai9";
    String name10 = "BD midi/Tchaikovsky/tchai10";
    
    Midi2Partiture m2p1 = new Midi2Partiture(); 
    Midi2Partiture m2p2 = new Midi2Partiture();
    Midi2Partiture m2p3 = new Midi2Partiture();
    Midi2Partiture m2p4 = new Midi2Partiture();
    Midi2Partiture m2p5 = new Midi2Partiture();
    Midi2Partiture m2p6 = new Midi2Partiture();
    Midi2Partiture m2p7 = new Midi2Partiture();
    Midi2Partiture m2p8 = new Midi2Partiture();
    Midi2Partiture m2p9 = new Midi2Partiture();
    Midi2Partiture m2p10 = new Midi2Partiture();
    
    m2p1.transform(name1 + ".mid", name1 + ".txt",2,20);
    m2p2.transform(name2 + ".mid", name2 + ".txt",2,20);
    m2p3.transform(name3 + ".mid", name3 + ".txt",2,20);
    m2p4.transform(name4 + ".mid", name4 + ".txt",2,20);
    m2p5.transform(name5 + ".mid", name5 + ".txt",2,20);
    m2p6.transform(name6 + ".mid", name6 + ".txt",2,20);
    m2p7.transform(name7 + ".mid", name7 + ".txt",2,20);
    m2p8.transform(name8 + ".mid", name8 + ".txt",2,20);
    m2p9.transform(name9 + ".mid", name9 + ".txt",2,20);
    m2p10.transform(name10 + ".mid", name10 + ".txt",2,20);
    
    }

private static void generateBeatles() throws FileNotFoundException, UnsupportedEncodingException{
    
    String name1 = "BD midi/Beatles/beatles1";
    String name2 = "BD midi/Beatles/beatles2";
    String name3 = "BD midi/Beatles/beatles3";
    String name4 = "BD midi/Beatles/beatles4";
    String name5 = "BD midi/Beatles/beatles5";
    String name6 = "BD midi/Beatles/beatles6";
    String name7 = "BD midi/Beatles/beatles7";
    String name8 = "BD midi/Beatles/beatles8";
    String name9 = "BD midi/Beatles/beatles9";
    String name10 = "BD midi/Beatles/beatles10";

    
    Midi2Partiture m2p1 = new Midi2Partiture(); 
    Midi2Partiture m2p2 = new Midi2Partiture();
    Midi2Partiture m2p3 = new Midi2Partiture();
    Midi2Partiture m2p4 = new Midi2Partiture();
    Midi2Partiture m2p5 = new Midi2Partiture();
    Midi2Partiture m2p6 = new Midi2Partiture();
    Midi2Partiture m2p7 = new Midi2Partiture();
    Midi2Partiture m2p8 = new Midi2Partiture();
    Midi2Partiture m2p9 = new Midi2Partiture();
    Midi2Partiture m2p10 = new Midi2Partiture();
    
    m2p1.transform(name1 + ".mid", name1 + ".txt",2,20);
    m2p2.transform(name2 + ".mid", name2 + ".txt",1,20);
    m2p3.transform(name3 + ".mid", name3 + ".txt",1,20);
    m2p4.transform(name4 + ".mid", name4 + ".txt",1,20);
    m2p5.transform(name5 + ".mid", name5 + ".txt",1,20);
    m2p6.transform(name6 + ".mid", name6 + ".txt",1,20);
    m2p7.transform(name7 + ".mid", name7 + ".txt",1,20);
    m2p8.transform(name8 + ".mid", name8 + ".txt",1,20);
    m2p9.transform(name9 + ".mid", name9 + ".txt",2,20);
    m2p10.transform(name10 + ".mid", name10 + ".txt",2,20);
    
    }
 
}

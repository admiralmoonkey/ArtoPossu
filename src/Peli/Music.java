package Peli;

/**
 *	SimpleAudioPlayer.java
 *
 *	This file is part of jsresources.org
 */
/**
 * 
 * Luokka musiikkia varten. Alkuperäisestä koodista on muutettu kommentit,
 * sekä inputStreamiksi on syötetty suoraan tiedostonimi getResourcin avulla.
 * Oh lord thanks Pfisterer for this one!
 * 
 * Tutoriaali:http://www.jsresources.org/examples/SimpleAudioPlayer.java.html
 *
 * 
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
|<---            this code is formatted to fit into 80 columns             --->|
 */
import javax.sound.sampled.*;
//import java.io.File;
import java.io.IOException;

public class Music {

    private static final int BUFFER = 12800;
    private String fileName;
    //private File soundFile;
    private AudioInputStream inputStream = null;
    private AudioFormat format;
    private SourceDataLine line;
    private DataLine.Info info;

    public Music() {

        fileName = "music/spain-compressed.wav";
        //soundFile = new File("spain.wav");
        /*this.getClass().getResource(fileName);   
        soundFile=soundFile.getAbsoluteFile();
         **/
        try {
            inputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(fileName));
        } catch (Exception e) {
        }
        format = inputStream.getFormat();
        line = null;
        info = new DataLine.Info(SourceDataLine.class, format);
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
        } catch (LineUnavailableException e) {
        } catch (Exception e) {
        }
        line.start();
        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER];
        while (nBytesRead != -1) {
            try {
                nBytesRead = inputStream.read(abData, 0, abData.length);
            } catch (IOException e) {
            }
           // if (nBytesRead >= 0) {
                int nBytesWritten = line.write(abData, 0, nBytesRead);
            //}
        }
        line.drain();
        line.close();
    }
}

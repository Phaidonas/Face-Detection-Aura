import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gab.opencv.*; 
import java.awt.Rectangle; 
import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FaceDetectionAuraV9F extends PApplet {





Capture video;
OpenCV opencv;
Rectangle[] faces;

int number = 0;


int num =480 ;
boolean Button =false;

//int[] savX = new int[num];
//int[] savY = new int[num];
//int[]Ycomp = new int[width];

int[]Dsav = new int[num];


int []Yface = new int[num];

float x = 240;
float y = 320;
float w = 150;
float h = 80;



public void setup() {
  




  int rectX, rectY;     
  int rectSize = 90;

  video = new Capture(this, width, height);
  opencv = new OpenCV(this, width, height);

  video.start();
  opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);
}

public void draw() {
background(255);
  //rectX = width/2-rectSize-10;
  //rectY = height/2-rectSize/2;

  rect(x, y, w, h);
  fill(255);
  /*if (mousePressed) {
    if (mouseX>x && mouseX <x+w && mouseY>y && mouseY <y+h) {
      println("The mouse is pressed and over the button");
      Button=true;
      println(Button);
    }
  }*/ 

  /*for (int i = num-1; i > 0; i--) {
    savX[i] = savX[i-1];
    savY[i] = savY[i-1];
  }

  savX[0] = mouseX;
  savY[0] = mouseY;

  for (int i = 0; i < num; i++) {

    for (int k = 0; k < Ycomp.length; k++) {  
      if (savY[i] == Ycomp[k]) {
        Button = true;
      }
    }  
    ellipse(savX[i], savY[i], i/2.0, i/2.0);
  }*/
  
  
  /*for (int i = Yface.length-1; i> 0; i--){
   Yface[i] = Yface[i-1]; 
  }
  
  Yface[0] = mouseY;
  
  for(int i =1; i < Yface.length; i++){
   line(i,Yface[i],i-1,Yface[i-1]); 
  }*/
  



  //if (Button== true) {
    opencv.loadImage(video);
    image(video, 0, 0);
    faces = opencv.detect();
    noFill();
    stroke(0, 255, 0);
    strokeWeight(3);

    ///load pixels - pws vrisko thn periohh

    for (int i = 0; i < faces.length; i++) {
      rect(faces[i].x, faces[i].y, faces[i].width, faces[i].height);
      ellipse((faces[i].x+faces[i].width/2), (faces[i].y+faces[i].height/2), 10, 10);
      
      for (int k = (faces[i].y+faces[i].height/2)-1;k>0;k--){  
        Dsav[k] = Dsav[k-1];
       }
      
      Dsav[0] = faces[i].y;
     
     for (int m = 1; m< Dsav.length; m++){
      line(m,Dsav[m],m-1,Dsav[m-1]);
     } 
      
    }
  }
//}
public void captureEvent(Capture video) {
  video.read();
}

/*void keyPressed(){
  if(key == 's'){
    println("Saving...");
    String s = "screen" + nf(number,4) +".jpg";
    save(s);
    number++;
    println("Done saving.");
  }
}*/
  public void settings() {  size(640, 480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "FaceDetectionAuraV9F" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}


import javax.swing.JFileChooser;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;  
import javax.crypto.CipherInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.nio.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author varunkansal
 */
class Encryption {
    static int matrix[][]=new int [16][16];
    static int a;
    
    static int red;
    static int green;
    static int blue;
                //changes to be brought here
    static int a1;
    static int red1;
    static int green1;
    static int blue1;

    public static void matrix()
    {
        int i,j,k,ab;
        
        int abc[]={254,252,251,250,0,5,10};
        int q;
        ab=0;
        for(i=0;i<16;i++)
        {   
            if(ab<abc.length)
            {
            for(k=0;k<abc.length;k++)
            {
                    matrix[i][k]=abc[k];
                    ab++;
            }
            }
            
        }

       
        q=0;
        for(i=0;i<16;i++)
        {   
            for(j=0;j<16;j++)
            {
                if((i==0) && (j==0))
                {
                    j=abc.length-1;
                }
                else
                {
                    
                    
                for(k=0;k<abc.length;k++)
                {
                    if(abc[k]==q)
                        {   q++;k=0;
                            
                            
                        }
                }
                
                    matrix[i][j]=q;
                    q++;
                    
                }
            }
            
             }
            
         
        
         for(i=0;i<16;i++)
         {
             for(j=0;j<16;j++)
             {
                System.out.print(matrix[i][j]+" ");  
             }
             System.out.println(" ");
         }
    }
    
    
    public static int[] getCharPos(int ch)
    {
        int[] keyPos = new int[2];
        
        for (int i = 0; i < 16; i++) 
        {
            for (int j = 0; j < 16; j++)
            {
                
                if (matrix[i][j] == ch)
                {
                    keyPos[0] = i;
                    keyPos[1] = j;
                    break;
                }
            }
        }
        return keyPos;
    }
public static int [] enc12(int r, int g)
{
        //String[] msgPairs = formPairs(message);
        int encText [] = new int[2];
        
            int[] ch1Pos = getCharPos(r);
            int[] ch2Pos = getCharPos(g);
           // System.out.println("last 0= "+ch1Pos[0]);
            // if both the characters are in the same row 0=i , 1=j
            if (ch1Pos[0] == ch2Pos[0]&&ch1Pos[1]==ch2Pos[1])
            {
              

            }
           
            else if (ch1Pos[0] == ch2Pos[0]) {
               
               ch1Pos[1]=(ch1Pos[1]+1)%16;
               ch2Pos[1]=(ch2Pos[1]+1)%16;
            }
            
            // if both the characters are in the same column
            else if (ch1Pos[1] == ch2Pos[1])
            {
                ch1Pos[0]=(ch1Pos[0]+1)%16;
               ch2Pos[0]=(ch2Pos[0]+1)%16;
               
            }
            
            // if both the characters are in different rows
            // and columns
            else { 
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }
            
            // get the corresponding cipher characters from
            // the key matrix
            
            encText[0] = matrix[ch1Pos[0]][ch1Pos[1]];
            encText[1] = matrix[ch2Pos[0]][ch2Pos[1]];
                    //  System.out.println("last 0= "+encText[0]);
                     // System.out.println("last 1="+encText[1]);
    
        
        return encText;
}
   
    public static void encryption(String ab,String s4)
throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException
{
    try
    {
        BufferedImage img = null;
        File f = null;
        matrix();
        // read image
        try
        {
            f = new File(ab);
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        //System.out.println
        int width = img.getWidth();
        int height = img.getHeight();
  
        // convert to red image
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = img.getRGB(x,y);
                int z;
                int u;
                int red2[]=new int[2];
                int green2[]=new int[2];
                int blue2 []= new int[2];
                
                z=x+1;
                
                u=y;
                int p1 = img.getRGB(z,u);
                
               //System.out.println(p);
                //Color color = new Color(p, true);
                 //Retrieving the R G B values
                 a = (p>>24)&0xff;
                 red = (p>>16)&0xff;
                 green = (p>>8)&0xff;
                 blue = p&0xff;
                //changes to be brought here
                a1 = (p1>>24)&0xff;
                red1 = (p1>>16)&0xff;
                green1 = (p1>>8)&0xff;
                blue1 = p1&0xff;
                
                
                
               //System.out.println("red="+red);
                //System.out.println("red1="+red1);
              red2=enc12(red,red1);
              // System.out.println("green="+green);
              // System.out.println("green1="+green1);
               green2=enc12(green,green1);
            // System.out.println("blue="+blue);
              //   System.out.println("blue1="+blue1);
               blue2=enc12(blue,blue1);
                
               red=red2[0];
                green=green2[0];
               blue=blue2[0];

                red1=red2[1];
                green1=green2[1];
                blue1=blue2[1];
             //System.out.println("red="+red);
           //System.out.println("red1="+red1);
              ///  System.out.println("green="+green);
               // System.out.println("green1="+green1);
               // System.out.println("blue1="+blue1);
               // System.out.println("blue="+blue);
                 p= (a<<24) | (red<<16) | (green<<8) | (blue) ;
                 p1= (a1<<24) | (red1<<16) | (green1<<8) | (blue1) ;
                 //System.out.println("p"+p);
                //System.out.println("z="+x+"u="+y);
                img.setRGB(x, y, p);
                img.setRGB(z,u,p1);
                x++;
                
            }
        }
        try
        {   
             f = new File(s4+"/encrypt.png");
           
             ImageIO.write(img, "png", f);
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
                                     
                
              
        
        

    }
catch( Exception e)
{e.printStackTrace();}

}//function
    
    

public static void xor(int p,String s3)
throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException
{
    Scanner sc = new Scanner(System.in);
        System.out.println("Note : Encryption Key act as Password to Decrypt the same Image,otherwise it will corrupt the Image.");
       
        
        System.out.print("Enter key for Encryption : ");
        int key1 = p;
    
    FileInputStream fis = new FileInputStream(s3+"/encrypt.png");
    File f= new File(s3+"/encrypt.png");
    f.delete();
                             
        byte data[] = new byte[fis.available()];
                             
        
        fis.read(data);
        int i1 = 0;
                             
        for (byte b : data) {
            data[i1] = (byte)(b ^ key1);
            i1++;
        }
        
        FileOutputStream fos = new FileOutputStream(s3+"/encrypt1.png");
        
        fos.write(data);
        
}
}


class Decryption 
{
    static int matrix[][]=new int [16][16];
    static int a;
    
    static int red;
    static int green;
    static int blue;
                //changes to be brought here
    static int a1;
    static int red1;
    static int green1;
    static int blue1;

    public static void matrix()
    {
        int i,j,k,ab;
        
        int abc[]={254,252,251,250,0,5,10};
        int q;
        ab=0;
        for(i=0;i<16;i++)
        {   
            if(ab<abc.length)
            {
            for(k=0;k<abc.length;k++)
            {
                    matrix[i][k]=abc[k];
                    ab++;
            }
            }
            
        }

       
        q=0;
        for(i=0;i<16;i++)
        {   
            for(j=0;j<16;j++)
            {
                if((i==0) && (j==0))
                {
                    j=abc.length-1;
                }
                else
                {
                    
                    
                for(k=0;k<abc.length;k++)
                {
                    if(abc[k]==q)
                        {   q++;k=0;
                            
                            
                        }
                }
                
                    matrix[i][j]=q;
                    q++;
                    
                }
            }
            
             }
            
         
        
         for(i=0;i<16;i++)
         {
             for(j=0;j<16;j++)
             {
                System.out.print(matrix[i][j]+" ");  
             }
             System.out.println(" ");
         }
    }
    
    
    public static int[] getCharPos(int ch)
    {
        int[] keyPos = new int[2];
        
        for (int i = 0; i < 16; i++) 
        {
            for (int j = 0; j < 16; j++)
            {   
                
                if (matrix[i][j] == ch)
                {  // System.out.println("CH="+ch);
                    keyPos[0] = i;
                    keyPos[1] = j;
                    break;
                }
            }
        }
        return keyPos;
    }
public static int[] dec12(int r, int g)
{
        //String[] msgPairs = formPairs(message);
        int encText[] = new int[2];
        
        
            int[] ch1Pos = getCharPos(r);
            int[] ch2Pos = getCharPos(g);
            //System.out.println("last 0= "+ch1Pos[0]);
            // if both the characters are in the same row
            if (ch1Pos[0] == ch2Pos[0]&&ch1Pos[1]==ch2Pos[1])
            {

            }
            else if (ch1Pos[0] == ch2Pos[0]) {
                if(ch1Pos[1]>0)
                {   
                    ch1Pos[1]--;
                    if(ch2Pos[1]>0)
                   {
                        ch2Pos[1]--;
                    }
                    else
                    {
                        ch2Pos[1]=15;
                    }
                }
                else
                   { ch1Pos[1]=15;
                    if(ch2Pos[1]>0)
                    {
                         ch2Pos[1]--;
                     }
                     else
                     {
                         ch2Pos[1]=15;
                     }
                    }
                
                
             }
            
            // if both the characters are in the same column
            else if (ch1Pos[1] == ch2Pos[1])
            {   if(ch1Pos[0]>0)
                {   
                    ch1Pos[0]--;
                    if(ch2Pos[0]>0)
                   {
                        ch2Pos[0]--;
                    }
                    else
                    {
                        ch2Pos[0]=15;
                    }
                }
                else
                   { ch1Pos[0]=15;
                    if(ch2Pos[0]>0)
                    {
                         ch2Pos[0]--;
                     }
                     else
                     {
                         ch2Pos[0]=15;
                     }
                    }
             }
            // if both the characters are in different rows
            // and columns
            else {
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }
            
            // get the corresponding cipher characters from
            // the key matrix
            encText[0] = matrix[ch1Pos[0]][ch1Pos[1]];
            encText[1]  = matrix[ch2Pos[0]][ch2Pos[1]];
                      
        
        //System.out.println("i ="+encText[0]+" j="+encText[1]);
    
        return encText;
}
   
    public static int decryption(String s1)
throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException
{
    try
    {
        BufferedImage img = null;
        File f = null;
        matrix();
        // read image
        try
        {
            f = new File(s1+"/Decrypt.png");
            img = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
        //System.out.println
        int width = img.getWidth();
        int height = img.getHeight();
  
        // convert to red image
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            { 
                int p = img.getRGB(x,y);
                int u,z;
                int red2[]=new int[2];
                int green2[]=new int[2];
                int blue2 []= new int[2];
                z=x+1;
                
                u=y;
                int p1 = img.getRGB(z,u);
               // System.out.println("z="+z+"u="+u);
               // System.out.println(p);
                //Color color = new Color(p, true);
                 //Retrieving the R G B values
                 a = (p>>24)&0xff;
                 red = (p>>16)&0xff;
                 green = (p>>8)&0xff;
                 blue = p&0xff;
                //changes to be brought here
                a1 = (p1>>24)&0xff;
                red1 = (p1>>16)&0xff;
                green1 = (p1>>8)&0xff;
                blue1 = p1&0xff;
                
               //System.out.println("red="+red);
              // System.out.println("red1="+red1);
               // System.out.println("green="+green);
               // System.out.println("green1="+green1);
               // System.out.println("blue1="+blue1);
               // System.out.println("blue="+blue);

               red2=dec12(red,red1);
               green2=dec12(green,green1);
               blue2=dec12(blue,blue1);

                red=red2[0];
               green=green2[0];
               blue=blue2[0];

                red1=red2[1];
                green1=green2[1];
              blue1=blue2[1];
             
            /// System.out.println("red="+red);
               // System.out.println("red1="+red1);
              //  System.out.println("green="+green);
               // System.out.println("green1="+green1);
               // System.out.println("blue1="+blue1);
               // System.out.println("blue="+blue);
                 p= (a<<24) | (red<<16) | (green<<8) | (blue) ;
                 p1= (a1<<24) | (red1<<16) | (green1<<8) | (blue1) ;
                //System.out.println(p);
                img.setRGB(x, y, p);
                img.setRGB(z,u,p1);
                x++;
                
            }
        }
        
        try
        {    f = new File(s1+"/Decrypt.png");
            ImageIO.write(img, "png", f);
            return 0;
        }
        catch(IOException e)
        {
            System.out.println("Code is incorrect");
          
            
        File f2= new File(s1+"/decrypt.png");
            f2.delete();
                    return 1;
        }
                                     
                
         
        
      
    }
catch( Exception e)
{  
    System.out.println("Code is Incorrect");
    
    //e.printStackTrace();
     File f3= new File(s1+"/decrypt.png");
            f3.delete();
            return 1;
    
}

}//function   
public static void xor(int p1,String fs,String s2)
throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, FileNotFoundException, IOException
{   
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a key for Decryption : ");
    int key2 = p1;
        
    // Selecting a Image for Decryption.
        
    FileInputStream fis1 = new FileInputStream(fs);
      
    
    byte data2[] = new byte[fis1.available()];
        

        
    fis1.read(data2);
    int i2 = 0;
        
    for (byte b : data2) {
        data2[i2] = (byte)(b ^ key2);
        i2++;
    }
        
    FileOutputStream fos2 = new FileOutputStream(s2+"/Decrypt.png");
    
    fos2.write(data2);
}
}

public class Inc extends javax.swing.JFrame {
    String s="a";
    String se="a";
    int p=0;
    int pa=0;
    /**
     * Creates new form Inc
     */
    public Inc() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(249, 127, 81));

        jLabel1.setFont(new java.awt.Font("Herculanum", 0, 18)); // NOI18N
        jLabel1.setText("Encryption & Decryption");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(254, 202, 87));

        jLabel2.setFont(new java.awt.Font("Papyrus", 0, 13)); // NOI18N
        jLabel2.setText("Enter the pin for encryption & Decryption:");

        pin.setFont(new java.awt.Font("Papyrus", 1, 13)); // NOI18N
        pin.setText("PIN");
        pin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pinMouseExited(evt);
            }
        });
        pin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pinActionPerformed(evt);
            }
        });
        pin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pinKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Papyrus", 0, 13)); // NOI18N
        jLabel3.setText("Browse the file");

        jButton1.setFont(new java.awt.Font("Papyrus", 1, 13)); // NOI18N
        jButton1.setText("Encrypt");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Papyrus", 1, 13)); // NOI18N
        jButton2.setText("Decrypt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Papyrus", 1, 13)); // NOI18N
        jButton3.setText("Browse");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Papyrus", 0, 13)); // NOI18N
        jLabel4.setText("Location to store the file");

        jLabel5.setFont(new java.awt.Font("Hiragino Sans GB", 0, 10)); // NOI18N
        jLabel5.setText("Browsing Location");

        jLabel6.setFont(new java.awt.Font("Hiragino Sans GB", 1, 10)); // NOI18N
        jLabel6.setText("Work is Under Process");

        jLabel7.setFont(new java.awt.Font("Hiragino Sans GB", 0, 10)); // NOI18N
        jLabel7.setText("Location of storage");

        jButton4.setFont(new java.awt.Font("Papyrus", 1, 13)); // NOI18N
        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(141, 141, 141)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addComponent(jButton3))))
                        .addContainerGap(107, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(82, 82, 82))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pinActionPerformed
//text added //pin portion        // TODO add your handling code here:

    }//GEN-LAST:event_pinActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
///encrypt        // TODO add your handling code here:

try
{
    
          
if(p!=0&&se!="a")
{   jLabel6.setText("Encryption is under progress.. wait for a while");
    Encryption.encryption(s,se); //se add karna hn
    Encryption.xor(p,se);

    jLabel6.setText("Encryption have been completed");
    System.out.println(p);
}
else if(p==0)
{
    jLabel6.setText("pin not set");
}
else if(se=="a")
{
    jLabel6.setText("location not set");
}
}
catch(Exception e)
{
e.printStackTrace();
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    // decrypt    // TODO add your handling code here:
    try
{
    System.out.println(pa);
    int x=0;
if(pa!=0&&se!="a")
{   jLabel6.setText("Decryption is under progress.. wait for a while");
    Decryption.xor(pa,s,se);
    x=Decryption.decryption(se); 
    if(x==1)
    {
    jLabel6.setText("Code is Incorrect");
    }
    else
    {
    jLabel6.setText("Decryption have been completed");
    System.out.println(pa);
}
}
else if(pa==0)
{
    jLabel6.setText("pin not set");
}
else if(se=="a")
{
    jLabel6.setText("location not set");
}
}
catch(Exception e)
{
e.printStackTrace();
}
    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // browse button
        String com = evt.getActionCommand();
 
        if (com.equals("save")) {
            
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
 
            int r = j.showSaveDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
                
                   jLabel5.setText(j.getSelectedFile().getAbsolutePath());
            }
            
            else
                jLabel5.setText("the user cancelled the operation");
        }
 

        else {
        
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
 
           
            int r = j.showOpenDialog(null);
 
       
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
                
                jLabel5.setText(j.getSelectedFile().getAbsolutePath());
                s=j.getSelectedFile().getAbsolutePath();
                System.out.println(s);
                
            }
        
            else
                jLabel5.setText("the user cancelled the operation");
        }
   
           
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void pinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pinKeyTyped
//pin after typed        // TODO add your handling code here:
 

    }//GEN-LAST:event_pinKeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//to store file place        // TODO add your handling code here:
 String com = evt.getActionCommand();
 
        if (com.equals("save")) {
            
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
           
            int r = j.showSaveDialog(null);
 
         
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
             
                   jLabel7.setText(j.getSelectedFile().getAbsolutePath());
            }
       
            else
                jLabel7.setText("the user cancelled the operation");
        }
 
   
        else {
          
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 
       
            int r = j.showOpenDialog(null);
 
           
            if (r == JFileChooser.APPROVE_OPTION)
 
            {
                jLabel7.setText(j.getSelectedFile().getAbsolutePath());
                se=j.getSelectedFile().getAbsolutePath();
                System.out.println(se);
                
            }
           
            else
                jLabel7.setText("the user cancelled the operation");
        }
   

    }//GEN-LAST:event_jButton4ActionPerformed

    private void pinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pinMouseExited
p=Integer.parseInt(pin.getText());
 pa=Integer.parseInt(pin.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_pinMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField pin;
    // End of variables declaration//GEN-END:variables
}

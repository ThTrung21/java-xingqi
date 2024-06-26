/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GUI;
import java.awt.*;
/**
 *
 * @author TRUNG
 */
public class Profile {
    private Color p1Color;
    private Color p2Color;
    private Color backGround;
    private Color foreGround;
    private Color lineColor;
    private String p1String;
    private String p2String;
    private int minutes;


    Profile() {
        p1Color = Color.RED;
        p2Color = Color.BLACK;
//        backGround = Color.lightGray;
backGround = new Color(204,160,104);
        foreGround = new Color(245, 245, 220);
        p1String = "Player 1";
        p2String = "Player 2";
        minutes = 10;
        lineColor = Color.BLACK;
    }

    Profile(Color p1color, Color p2Color, Color backGround, Color foreGround, Color lineColor) {
        this.p1Color = p1color;
        this.p2Color = p2Color;
        this.backGround = backGround;
        this.foreGround = foreGround;
        this.lineColor = lineColor;
        this.p1String = "Player 1";
        this.p2String = "Player 2";
        this.minutes = 10;

    }

    public void setP1Color(Color p1Color) {
        this.p1Color = p1Color;
    }

    public void setP2Color(Color p2Color) {
        this.p2Color = p2Color;
    }


    public Color getForeGround() {
        return foreGround;
    }

    public Color getBackground() {
        return backGround;
    }

    public void setForeGround(Color foreGround) {
        this.foreGround = foreGround;
    }

    public void setBackGround(Color newBackGround) {
        this.backGround = newBackGround;
    }

    public void setP1String(String p1String) {
        this.p1String = p1String;
    }

    public void setP2String(String p2String) {
        this.p2String = p2String;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Color getP1Color() {
        return p1Color;
    }

    public Color getP2Color() {
        return p2Color;
    }

    public Color background() {
        return backGround;
    }

    public String getP1String() {
        return p1String;
    }

    public String getP2String() {
        return p2String;
    }


    public int getMinutes() {
        return minutes;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
}

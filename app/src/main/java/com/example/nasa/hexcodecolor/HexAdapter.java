package com.example.nasa.hexcodecolor;

public class HexAdapter
{
    public HexAdapter()
    {

    }

    public HexAdapter(String color) {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    String color;
}

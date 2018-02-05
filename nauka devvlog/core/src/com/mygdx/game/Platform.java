package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Platform extends Rectangle {

    private Texture texture;

    public Platform(Texture texture){
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }
    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

}

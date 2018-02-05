package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class JumpPlayer extends Rectangle {


    private Sound jumpSound;
    private Texture texture;
    public boolean canJump = true;

    public float jumpVelocity;

    public JumpPlayer(Texture texture){
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
    }
    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

    public void  jump (){
        if(canJump && jumpVelocity>=-100){
            jumpVelocity +=800;
            canJump = false;
            jumpSound.play();
        }
    }



}

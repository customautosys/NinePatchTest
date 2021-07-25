package com.customautosys.ninepatchtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class NinePatchTest extends Game implements Screen{
	public Stage stage;
	public Image ninePatch;
	public ProgressBar ninePatchBar;
	public NinePatchDrawable ninePatchBarDrawable;
	public ScreenViewport screenViewport;
	public OrthographicCamera camera;
	
	@Override
	public void create (){
		stage=new Stage(
			screenViewport=new ScreenViewport(
				camera=new OrthographicCamera(
					Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight()
				)
			)
		);
		camera.setToOrtho(true);
		TextureRegion textureRegion=new TextureRegion(
			new Texture(Gdx.files.internal("barbackground.png"))
		);
		textureRegion.flip(false,true);
		ninePatch=new Image(
			new NinePatch(
				textureRegion,
				11,
				11,
				11,
				11
			)
		);
		ninePatch.setBounds(
			50,
			50,
			Gdx.graphics.getWidth()-100,
			50
		);
		stage.addActor(ninePatch);
		ninePatchBar=new ProgressBar(
			0f,
			100f,
			0.1f,
			false,
			new ProgressBar.ProgressBarStyle(
				ninePatchBarDrawable=new NinePatchDrawable(
					new NinePatch(
						textureRegion,
						11,
						11,
						11,
						11
					)
				),
				null
			)
		);
		ninePatchBarDrawable.tint(Color.RED);
		ninePatchBar.setBounds(
			50,
			100,
			Gdx.graphics.getWidth()-100,
			50
		);
		stage.addActor(ninePatchBar);
		setScreen(this);
	}

	@Override
	public void show(){}

	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0f,0f,0f,0f);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void hide(){}

	@Override
	public void dispose () {
		stage.dispose();
	}

	@Override
	public void resize(int width,int height){
		screenViewport.update(width,height);
		camera.update();
	}
}

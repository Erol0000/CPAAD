/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: engine/Engine.java 2015-03-11 buixuan.
 * ******************************************************/
package engine;

import tools.Chrono;
import tools.HardCodedParameters;
import tools.User;
import userInterface.Viewer;
import tools.Position;
import tools.Sound;

import specifications.EngineService;
import specifications.DataService;
import specifications.RequireDataService;

import java.util.Timer;
import java.util.TimerTask;

import com.sun.glass.ui.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.ArrayList;

public class Engine implements EngineService, RequireDataService{
  private static final double friction=HardCodedParameters.friction,
                              heroesStep=HardCodedParameters.heroesStep;
  private Timer engineClock;
  private DataService data;
  private User.COMMAND command;
  private Random gen;
  private boolean moveLeft,moveRight,moveUp;
  private double heroesVX,heroesVY;

  public Engine(){}

  @Override
  public void bindDataService(DataService service){
    data=service;
  }
  
  @Override
  public void init(){
    engineClock = new Timer();
    command = User.COMMAND.NONE;
    gen = new Random();
    moveLeft = false;
    moveRight = false;
    moveUp = false;
    heroesVX = 0;
    heroesVY = 0;
  }

  @Override
  public void start(){
    engineClock.schedule(new TimerTask(){
      public void run() {
        
        updateSpeedHeroes();
        updateCommandHeroes();
        updatePositionHeroes();

        int score=0;

        data.addScore(score);

      }
    },0,HardCodedParameters.enginePaceMillis);
  }

  @Override
  public void stop(){
    engineClock.cancel();
  }

  @Override
  public void setHeroesCommand(User.COMMAND c){
    if (c==User.COMMAND.LEFT) {
    	moveLeft=true;
    	data.setImg(new Image("file:src/images/courir_inverse.gif"));
    }
    if (c==User.COMMAND.RIGHT) {
    	moveRight=true;
    	data.setImg(new Image("file:src/images/v2.gif"));
    }
  }
  
  @Override
  public void releaseHeroesCommand(User.COMMAND c){
    if (c==User.COMMAND.LEFT) moveLeft=false;
    if (c==User.COMMAND.RIGHT) moveRight=false;
    if (c==User.COMMAND.UP) {
    	if (data.isJumping() == true)
    		return ;
    	data.setJumping(true);
    }

  }

  private void updateSpeedHeroes(){
    heroesVX*=friction;
    heroesVY*=friction;
  }

  private void updateCommandHeroes(){
    if (moveLeft) heroesVX-=heroesStep;
    if (moveRight) heroesVX+=heroesStep;
  }
  
  private void updatePositionHeroes(){
	  if (data.endGame() == true) {
		  return ;
	  }
    data.setHeroesPosition(new Position(data.getHeroesPosition().x+heroesVX,data.getHeroesPosition().y));
  }

	@Override
	public void gravity() {
		collisionPiece();
		for (Rectangle r : data.getCollisionMap()) {
			if (collisionPlateforme(r)) {
				data.setVitesseY(0);
				data.setJumping(false);
			}
		}
		Position p = new Position(data.getHeroesPosition().x,data.getHeroesPosition().y+data.getVitesseY());
		data.setHeroesPosition(p);
		data.setVitesseY(data.getVitesseY()+HardCodedParameters.g);
		
		if (data.getHeroesPosition().y > HardCodedParameters.defaultHeight) {
			p = new Position(HardCodedParameters.heroesStartX,HardCodedParameters.heroesStartY);
			data.setHeroesPosition(p);
			data.setVitesseY(0);
			data.setJumping(false);
		}
	  }
	
	public void jump() {
		if (data.isJumping()) {
			Position p = new Position(data.getHeroesPosition().x, data.getHeroesPosition().y - 7);
			data.setHeroesPosition(p);
		}
	}
	
	public boolean collisionPlateforme(Rectangle r) {

		Rectangle rec = new Rectangle( data.getHeroesWidth() - 35 , data.getHeroesHeight() - 40);
	    rec.setTranslateX(data.getHeroesPosition().x + 50);
	    rec.setTranslateY(data.getHeroesPosition().y + 50);
	    
	    Boolean intersection = intersection(rec,r);
	    
	    return intersection;
	}
	
	private boolean intersection(Rectangle r1, Rectangle r2) {
		double y1 = r1.getTranslateY() + data.getVitesseY();
		double yh = y1 + r1.getHeight();
		
		double x1 = r1.getTranslateX();
		double xw = x1 + r1.getWidth();
		
	    if (y1 < r2.getTranslateY() && yh > r2.getTranslateY()
	    		&& x1 + r1.getWidth() > r2.getTranslateX() && x1 < r2.getTranslateX() + r2.getWidth()) {
	            return true;
	    }
	    return false;
	}
	
	private void collisionPiece() {
		if (data.getHeroesPosition().y > data.getCollisionPiece().getTranslateY() 
				|| data.isJumping() == true) {
			return ;
		}
		Rectangle rec = new Rectangle( data.getHeroesWidth() - 35 , data.getHeroesHeight() - 40);
	    rec.setTranslateX(data.getHeroesPosition().x + 50);
	    rec.setTranslateY(data.getHeroesPosition().y + 50);
		
		Rectangle r2 = data.getCollisionPiece();
		double y2 = r2.getTranslateY();
		double yh = y2 + r2.getHeight();
		
		double x2 = r2.getTranslateX();
		double xw = x2 + r2.getWidth();
		
		if (rec.getTranslateX() + rec.getWidth() > x2 && rec.getTranslateX() + rec.getWidth() < xw) {
			data.setIsAddPiece(false);
		}
	}
	
	public void openDoor() {
		double x = data.getHeroesPosition().x + 50;
		double y = data.getHeroesPosition().y + 50;
		double xw = data.getHeroesWidth() - 35;
		double xh = data.getHeroesHeight() - 40;
		
		double mx = x + xw/2;
		double mh = y + xh/2;
		
		
		if (data.isJumping()==false && mx > data.getDoor().getTranslateX() && mx < data.getDoor().getTranslateX() + data.getDoor().getImage().getWidth()
				&& mh > data.getDoor().getTranslateY() && mh < data.getDoor().getTranslateY() + data.getDoor().getImage().getHeight()) {
			data.setDoor(new ImageView(new Image("file:src/images/ouverture.jpg")));
			
			Chrono.stop();
			long time = Chrono.getTime();
			System.out.println(time/1000);
			data.endGame(true);
		}
	}
	
}

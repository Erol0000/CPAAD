/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: data/Data.java 2015-03-11 buixuan.
 * ******************************************************/
package data;

import tools.HardCodedParameters;
import tools.Position;
import tools.Sound;
import javafx.scene.image.Image;
import specifications.DataService;

public class Data implements DataService{
  //private Heroes hercules;
  private Position heroesPosition;
  private int score;
  private double heroesWidth,heroesHeight;
  private double vitesseY = 0;
  private int timerSaut;
  private double g = HardCodedParameters.g;
  private boolean jumping = false;
  private Image img = new Image("file:src/images/v2.gif");

  public Data(){}

  @Override
  public void init(){
    //hercules = new Heroes;
    heroesPosition = new Position(HardCodedParameters.heroesStartX,HardCodedParameters.heroesStartY);
    score = 0;
    heroesWidth = HardCodedParameters.heroesWidth;
    heroesHeight = HardCodedParameters.heroesHeight;
  }

  @Override
  public Position getHeroesPosition(){ return heroesPosition; }
  
  @Override
  public double getHeroesWidth(){ return heroesWidth; }
  
  @Override
  public double getHeroesHeight(){ return heroesHeight; }
  
  @Override
  public int getScore(){ return score; }

  @Override
  public void setHeroesPosition(Position p) { heroesPosition=p; }
    
  @Override
  public void addScore(int score){ this.score+=score; }


@Override
public void setVitesseY(double vitesseY) {
	this.vitesseY = vitesseY;
}

@Override
public double getVitesseY() {
	return vitesseY;
}

@Override
public boolean isJumping() {
	return jumping;
}

@Override
public int getTimerSaut() {
	return timerSaut;
}

@Override
public void setTimerSaut(int i) {
	timerSaut =i;
}

@Override
public void setJumping(boolean b) {
	jumping = b;
}

@Override
public void setImg(Image image) {
	this.img = image;
}

@Override
public Image getImage() {
	return img;
}

}

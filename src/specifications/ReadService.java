/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/ReadService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import tools.Position;
import tools.Sound;


public interface ReadService {
  public Position getHeroesPosition();
  public double getHeroesWidth();
  public double getHeroesHeight();
  public int getScore();
  
  public Image getImage();
  public void addCollisionMap(Rectangle r);
  public boolean isAddPiece();
  public ImageView getDoor();
  public boolean endGame();
  public ArrayList<Rectangle> getCollisionMap();
  public Rectangle getCollisionPiece();
  public boolean isJumping();
  public double getVitesseY();


}

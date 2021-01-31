/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/WriteService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Position;
import tools.Sound;


public interface WriteService {
  public void setHeroesPosition(Position p);
  public void addScore(int score);
  public void setIsAddPiece(boolean b);
  public void setDoor(ImageView image);
  public void endGame(boolean b);
  public void setImg(Image image);
  public void setTimerSaut(int i);
  public void setJumping(boolean b);
  public void setVitesseY(double VitesseY);

}

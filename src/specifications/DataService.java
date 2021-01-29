/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/DataService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications;

import javafx.scene.image.Image;

public interface DataService extends ReadService, WriteService{
  public void init();
  
  public void setVitesseY(double VitesseY);
  public double getVitesseY();
  public boolean isJumping();
  public int getTimerSaut();
  public void setTimerSaut(int i);
  public void setJumping(boolean b);
  public void setImg(Image image);
}

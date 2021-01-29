/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: specifications/ReadService.java 2015-03-11 buixuan.
 * ******************************************************/
package specifications;

import javafx.scene.image.Image;
import tools.Position;
import tools.Sound;


public interface ReadService {
  public Position getHeroesPosition();
  public double getHeroesWidth();
  public double getHeroesHeight();
  public int getScore();
  
public Image getImage();
}

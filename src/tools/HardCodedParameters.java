/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: tools/HardCodedParameters.java 2015-03-11 buixuan.
 * ******************************************************/
package tools;

public class HardCodedParameters {
  //---HARD-CODED-PARAMETERS---//
  public static String defaultParamFileName = "in.parameters";
  public static final int defaultWidth = 800, defaultHeight = 600,
                          heroesStartX = 80, heroesStartY = 200, heroesWidth=60, heroesHeight=90, heroesStep = 10;
  public static final int enginePaceMillis = 100,
                          spriteSlowDownRate = 7;
  public static final double g = 0.1;
  public static final double vitesseSaut = 0.022d;

  public static final double friction = 0.50;

  //---MISCELLANOUS---//
  
  public static <T> T instantiate(final String className, final Class<T> type){
    try{
      return type.cast(Class.forName(className).newInstance());
    } catch(final InstantiationException e){
      throw new IllegalStateException(e);
    } catch(final IllegalAccessException e){
      throw new IllegalStateException(e);
    } catch(final ClassNotFoundException e){
      throw new IllegalStateException(e);
    }
  }
}

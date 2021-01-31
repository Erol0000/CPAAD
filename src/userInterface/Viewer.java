/* ******************************************************
 * Project alpha - Composants logiciels 2015.
 * Copyright (C) 2015 <Binh-Minh.Bui-Xuan@ens-lyon.org>.
 * GPL version>=3 <http://www.gnu.org/licenses/>.
 * $Id: userInterface/Viewer.java 2015-03-11 buixuan.
 * ******************************************************/
package userInterface;

import tools.Chrono;
import tools.HardCodedParameters;

import specifications.ViewerService;
import specifications.ReadService;
import specifications.RequireReadService;
import engine.Engine;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Viewer implements ViewerService, RequireReadService{
  private static final double defaultMainWidth=HardCodedParameters.defaultWidth,
                              defaultMainHeight=HardCodedParameters.defaultHeight;
  private ReadService data;
  private ImageView heroesAvatar;
  private Image heroesSpriteSheet;
  private double xShrink,yShrink,shrink,xModifier,yModifier;
  private ImageView door;
  private ImageView map;
  public static ImageView piece;
  private Rectangle rec1;
  private Rectangle rec2;
  private Rectangle rec3;
  private Rectangle rec4;
  private Rectangle pieceBox;

  public Viewer(){}
  
  @Override
  public void bindReadService(ReadService service){
    data=service;
  }

  @Override
  public void init(){
    xShrink=1;
    yShrink=1;
    xModifier=0;
    yModifier=0;

    //Yucky hard-conding
    heroesSpriteSheet = new Image("file:src/images/v2.gif");
    heroesAvatar = new ImageView(heroesSpriteSheet);
    map = new ImageView(new Image("file:src/images/essaie2.png"));
    door = data.getDoor();
    door.setTranslateX(400);
    door.setTranslateY(420);
    
    rec1= new Rectangle(65, 20);
    rec1.setTranslateX(70);
    rec1.setTranslateY(341);
    rec1.setFill(Color.BLUE);
    
    rec2= new Rectangle(65, 20);
    rec2.setTranslateX(237);
    rec2.setTranslateY(245);
    rec2.setFill(Color.BLUE);
    
    rec3= new Rectangle(149, 20);
    rec3.setTranslateX(0);
    rec3.setTranslateY(515);
    rec3.setFill(Color.RED);
    
    rec4= new Rectangle(156, 20);
    rec4.setTranslateX(285);
    rec4.setTranslateY(515);
    rec4.setFill(Color.BLUE);
    
    
    data.addCollisionMap(rec1);
    data.addCollisionMap(rec2);
    data.addCollisionMap(rec3);
    data.addCollisionMap(rec4);
    
    piece = new ImageView(new Image("file:src/images/Piece.gif"));
    piece.setTranslateX(120);
    piece.setTranslateY(70);
    piece.setScaleX(0.10);
    piece.setScaleY(0.10);
    
    pieceBox = new Rectangle(35,35);
    pieceBox.setTranslateX(255);
    pieceBox.setTranslateY(205);
    pieceBox.setFill(Color.PINK);
    pieceBox.setOpacity(0.5);
  }

  @Override
  public Parent getPanel(){
	  
    shrink=Math.min(xShrink,yShrink);
    xModifier=.01*shrink*defaultMainHeight;
    yModifier=.01*shrink*defaultMainHeight;

    
    
    Group panel = new Group();
    heroesAvatar = new ImageView(data.getImage());
    heroesAvatar.setTranslateX(data.getHeroesPosition().x);
    heroesAvatar.setTranslateY(data.getHeroesPosition().y);
    heroesAvatar.setScaleX(0.25);
    heroesAvatar.setScaleY(0.25);
    
    Rectangle rec = new Rectangle( data.getHeroesWidth() - 35 , data.getHeroesHeight() - 40);
    rec.setTranslateX(data.getHeroesPosition().x + 50);
    rec.setTranslateY(data.getHeroesPosition().y + 50);
    rec.setOpacity(0.25);
    rec.setFill(Color.AQUA);
    
    
    map.setTranslateX(-230);
    map.setTranslateY(0);
    map.setScaleX(0.5);
    map.setScaleY(0.5);
    
    Rectangle r = new Rectangle(door.getImage().getWidth(), door.getImage().getHeight());
    r.setTranslateX(door.getTranslateX());
    r.setTranslateY(door.getTranslateY());
    r.setFill(Color.BLACK);
    r.setOpacity(0.5);
    
    double x = data.getHeroesPosition().x + 50;
	double y = data.getHeroesPosition().y + 50;
	double xw = data.getHeroesWidth() - 35;
	double xh = data.getHeroesHeight() - 40;
	
	
	double mx = x + xw/2;
	double mh = y + xh/2;
	Circle c = new Circle(mx, mh, 4);
	c.setFill(Color.GREEN);
    
	door = data.getDoor();
	door.setTranslateX(400);
    door.setTranslateY(420);
	
    if (data.isAddPiece() == true)
    	panel.getChildren().addAll(piece,door,heroesAvatar,map,rec, rec1, rec2, rec3, rec4, pieceBox,r,c);
    else
    	panel.getChildren().addAll(door,heroesAvatar,map,rec, rec1, rec2, rec3, rec4, pieceBox,r,c);
    
    if (data.endGame() == true) {
    	ImageView fin = new ImageView(new Image("file:src/images/Congrulation.jpg"));
    	panel.getChildren().add(fin);
    }
    
    return panel;
  }

  @Override
  public void setMainWindowWidth(double width){
    xShrink=width/defaultMainWidth;
  }
  
  @Override
  public void setMainWindowHeight(double height){
    yShrink=height/defaultMainHeight;
  }
}
package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class UlatPicker extends VBox {
	private ImageView circleImage;
	private ImageView ulatImage;
	
	private String circleNotChoosen = "model/resources/grey_circle.png";
	private String circleChoosen = "model/resources/green_boxTick.png";
			
	private ULAT ulat;
	private boolean isCircleChoosen;
	
	public UlatPicker(ULAT ulat) {
		circleImage = new ImageView(circleNotChoosen);
		ulatImage = new ImageView(ulat.getUrl());
		this.ulat = ulat;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(ulatImage);
		
	}
	
	public ULAT getUlat() {
		return ulat;
	}
	
	public boolean getCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));
	}
}


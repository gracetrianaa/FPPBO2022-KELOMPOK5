package view;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import application.HighScore;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ULAT;

public class GameManager {
	private Group root;
	private Canvas canvas;
	private Scene scene;
	private Stage primaryStage;
	
	private static final int WIDTH = 600;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    
    private static final String[] FOODS_IMAGE = new String[]{"model/resources/orange.png", "model/resources/apple.png", "model/resources/cherry.png",
            "model/resources/strawberry.png", "model/resources/coconut.png", "model/resources/peach.png", "model/resources/watermelon.png", "model/resources/tomato.png",
            "model/resources/pomegranate.png"};
    
    private static final String BODY_IMAGE = new String("model/resources/Badan.png");
    private static final String FONT_PATH = ("src/model/resources/MadouFutoMaru.ttf");
 
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    private GraphicsContext gc;
    private List<Point> snakeBody = new ArrayList();
    private Point snakeHead;
    private Image KepalaUlat;
    private Image BadanUlat;
    private Image foodImage;
    private ULAT ulatChoosen;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int currentDirection;
    private int score = 0;
    private Timeline timeline;
    
    public GameManager() {
    	root = new Group();
        canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        scene = new Scene(root);
    }

    public void start(ULAT choosenUlat){
    	ulatChoosen = choosenUlat;
    	KepalaUlat = new Image (choosenUlat.getKepala());
    	
    	primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ulat Bulu");
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                    }
                }
            }
        });

        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        generateFood();
        
        timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc) {
    	if (gameOver) {
    		GameOverManager GOScene = new GameOverManager();
    		GOScene.createGOScene(primaryStage, score, ulatChoosen);
    		HighScore.writeToFile(score);
    		timeline.stop();

    		return;
        }
    	drawBackground(gc);
    	drawFood(gc);
    	drawSnake(gc);
    	drawScore();
    	     
    	
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).x = snakeBody.get(i - 1).x;
            snakeBody.get(i).y = snakeBody.get(i - 1).y;
        }

    	switch (currentDirection) {
        case RIGHT:
            moveRight();
            break;
        case LEFT:
            moveLeft();
            break;
        case UP:
            moveUp();
            break;
        case DOWN:
            moveDown();
            break;
    	}
    	
    	gameOver();
    	eatFood();
    }
    	
    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("6D77AB"));
                } else {
                    gc.setFill(Color.web("9198CC"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    private void drawFood(GraphicsContext gc) {
        gc.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawSnake(GraphicsContext gc) {
    	BadanUlat = new Image(BODY_IMAGE);
        gc.drawImage(KepalaUlat, snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1);

        for (int i = 1; i < snakeBody.size(); i++) {
            gc.drawImage(BadanUlat, snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1);
        }
    }

    private void moveRight() {
        snakeHead.x++;
    }

    private void moveLeft() {
        snakeHead.x--;
    }

    private void moveUp() {
        snakeHead.y--;
    }

    private void moveDown() {
        snakeHead.y++;
    }

    public void gameOver() {
        if (snakeHead.x < 0 || snakeHead.y < 0 || snakeHead.x * SQUARE_SIZE >= WIDTH || snakeHead.y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }

        //destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.x == snakeBody.get(i).getX() && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            generateFood();
            score += 5;
        }
    }

    private void drawScore() {
        gc.setFill(Color.WHITE);
        try {
			gc.setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 30));
		} catch (FileNotFoundException e1) {
			gc.setFont(Font.font("Verdana", 40));
		}
        gc.fillText("Score: " + score, 10, 35);
    }
    
    public Stage getMainStage() {
		return primaryStage;
	}
}

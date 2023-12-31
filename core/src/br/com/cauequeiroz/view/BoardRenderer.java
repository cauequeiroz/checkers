package br.com.cauequeiroz.view;

import br.com.cauequeiroz.model.Board;
import br.com.cauequeiroz.model.Moviment;
import br.com.cauequeiroz.model.Stone;
import br.com.cauequeiroz.model.StoneType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class BoardRenderer {
    private final Board board;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;
    private Texture whiteStoneTexture;
    private Texture blackStoneTexture;
    private Texture whiteQueenStoneTexture;
    private Texture blackQueenStoneTexture;

    public BoardRenderer(Board board, Camera camera) {
        this.board = board;

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        whiteStoneTexture = new Texture(Gdx.files.internal("Stone_White_x2.png"));
        blackStoneTexture = new Texture(Gdx.files.internal("Stone_Black_x2.png"));
        whiteQueenStoneTexture = new Texture(Gdx.files.internal("Stone_White_2_x2.png"));
        blackQueenStoneTexture = new Texture(Gdx.files.internal("Stone_Black_2_x2.png"));

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    public void draw() {
        drawBoard();
        drawStones();
    }

    private void drawBoard() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int row=0; row<8; row++) {
            for (int col=0; col<8; col++) {
                boolean isDarkTile = (row + col) % 2 == 0;
                Color tileColor = isDarkTile ? Color.valueOf("B1845B") : Color.valueOf("E3CBA5");

                int x = col * 60;
                int y = row * 60;

                shapeRenderer.setColor(tileColor);
                shapeRenderer.rect(x, y, 60, 60);

                for (Moviment moviment : board.getHighlightedPositions()) {
                    Point point = moviment.getDestinyPosition();

                    if (point.equals(new Point(col, row))) {
                        shapeRenderer.setColor(Color.GRAY);
                        shapeRenderer.circle(x + 30, y + 30, 4);
                    }
                }

            }
        }

        shapeRenderer.end();
    }

    private void drawStones() {
        spriteBatch.begin();

        for (int row=0; row<8; row++) {
            for (int col=0; col<8; col++) {
                if (board.hasStone(col, row)) {
                    Stone stone = board.getStone(col, row);
                    Texture stoneTexture = getStoneTexture(stone);
                    int x = col*60 + 5;
                    int y = row*60 + 5;

                    spriteBatch.draw(stoneTexture, x, y);
                }
            }
        }

        if (board.getMovingStone() != null) {
            Stone movingStone = board.getMovingStone().getStone();
            Texture movingStoneTexture = getStoneTexture(movingStone);
            int x = Gdx.input.getX() - movingStoneTexture.getWidth() / 2;
            int y = Gdx.input.getY() - movingStoneTexture.getHeight() / 2;
            spriteBatch.draw(movingStoneTexture, x, y);
        }

        spriteBatch.end();
    }

    public void dispose() {
        whiteStoneTexture.dispose();
        blackStoneTexture.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }

    private Texture getStoneTexture(Stone stone) {
        Texture texture = null;

        switch (stone.getType()) {
            case BLACK:
                texture = blackStoneTexture;
                break;
            case BLACK_QUEEN:
                texture = blackQueenStoneTexture;
                break;
            case WHITE:
                texture = whiteStoneTexture;
                break;
            case WHITE_QUEEN:
                texture = whiteQueenStoneTexture;
        }

        return texture;
    }
}

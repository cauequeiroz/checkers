package br.com.cauequeiroz.controller;

import br.com.cauequeiroz.model.*;
import br.com.cauequeiroz.view.BoardRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class GameController {

    private final Camera camera;
    private Vector3 clickPosition;
    private Board board;
    private BoardRenderer boardRenderer;
    private Sound stoneMoveSound;
    private Sound stonePromotionSound;
    private Judge judge;

    public GameController(Camera camera) {
        this.camera = camera;
    }

    public void initialize() {
        clickPosition = new Vector3();
        stoneMoveSound = Gdx.audio.newSound(Gdx.files.internal("move.wav"));
        stonePromotionSound = Gdx.audio.newSound(Gdx.files.internal("promotion.mp3"));

        board = new Board();

        board.insertStone(new Stone(StoneType.BLACK), 1, 0);
        board.insertStone(new Stone(StoneType.BLACK), 3, 0);
        board.insertStone(new Stone(StoneType.BLACK), 5, 0);
        board.insertStone(new Stone(StoneType.BLACK), 7, 0);
        board.insertStone(new Stone(StoneType.BLACK), 0, 1);
        board.insertStone(new Stone(StoneType.BLACK), 2, 1);
        board.insertStone(new Stone(StoneType.BLACK), 4, 1);
        board.insertStone(new Stone(StoneType.BLACK), 6, 1);
        board.insertStone(new Stone(StoneType.BLACK), 1, 2);
        board.insertStone(new Stone(StoneType.BLACK), 3, 2);
        board.insertStone(new Stone(StoneType.BLACK), 5, 2);
        board.insertStone(new Stone(StoneType.BLACK), 7, 2);
        board.insertStone(new Stone(StoneType.WHITE), 0, 7);
        board.insertStone(new Stone(StoneType.WHITE), 2, 7);
        board.insertStone(new Stone(StoneType.WHITE), 4, 7);
        board.insertStone(new Stone(StoneType.WHITE), 6, 7);
        board.insertStone(new Stone(StoneType.WHITE), 1, 6);
        board.insertStone(new Stone(StoneType.WHITE), 3, 6);
        board.insertStone(new Stone(StoneType.WHITE), 5, 6);
        board.insertStone(new Stone(StoneType.WHITE), 7, 6);
        board.insertStone(new Stone(StoneType.WHITE), 0, 5);
        board.insertStone(new Stone(StoneType.WHITE), 2, 5);
        board.insertStone(new Stone(StoneType.WHITE), 4, 5);
        board.insertStone(new Stone(StoneType.WHITE), 6, 5);

        boardRenderer = new BoardRenderer(board, camera);

        judge = new Judge(board);
    }

    public void onEachFrame() {
        boardRenderer.draw();
    }

    public void handleMouseDown(int x, int y) {
        clickPosition.set(x, y, 0);
        camera.unproject(clickPosition);

        int col = (int) clickPosition.x / 60;
        int row = (int) clickPosition.y / 60;

        startMoviment(col, row);
    }

    private void startMoviment(int col, int row) {
        if (judge.isAValidStartMoviment(col, row)) {
            board.takeStone(col, row);
            judge.calculateValidMoviments();
            board.setHighlightedPositions(judge.getValidMoviments());
        }
    }

    private void endMoviment(int col, int row) {
        if (judge.isAValidEndMoviment(col, row)) {
            Moviment moviment = judge.getMoviment(col, row);
            Point takenStonePosition = moviment.getEatenStonePosition();
            Point destinyPosition = moviment.getDestinyPosition();

            if (takenStonePosition != null) {
                board.removeStone(takenStonePosition.x, takenStonePosition.y);
            }

            if (judge.isAPromotion(destinyPosition)) {
                stoneMoveSound.play();
                stonePromotionSound.play();
                board.leaveStone(col, row, true);
            } else {
                stoneMoveSound.play();
                board.leaveStone(col, row, false);
            }

            judge.nextTurn();
        } else {
            board.returnMovingStone();
        }

        board.resetHighlightedPositions();
    }

    public void handleMouseUp(int x, int y) {
        if (board.getMovingStone() == null) {
            return;
        }

        clickPosition.set(x, y, 0);
        camera.unproject(clickPosition);

        int col = (int) clickPosition.x / 60;
        int row = (int) clickPosition.y / 60;

        endMoviment(col, row);
    }

    public void dispose() {
        boardRenderer.dispose();
        stoneMoveSound.dispose();
    }
}

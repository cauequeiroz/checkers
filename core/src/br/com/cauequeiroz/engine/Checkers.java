package br.com.cauequeiroz.engine;

import br.com.cauequeiroz.controller.GameController;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class Checkers extends ApplicationAdapter {
    private OrthographicCamera camera;

    private GameController gameController;

    @Override
    public void create() {
        // Create camera
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 480, 480);

        // Create Game Controller
        gameController = new GameController(camera);
        gameController.initialize();

        // Handle events
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) { return false; }
            @Override
            public boolean keyUp(int keycode) { return false; }
            @Override
            public boolean keyTyped(char character) { return false; }
            @Override
            public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
            @Override
            public boolean mouseMoved(int screenX, int screenY) { return false; }
            @Override
            public boolean scrolled(float amountX, float amountY) { return false; }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                gameController.handleMouseDown(screenX, screenY);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                gameController.handleMouseUp(screenX, screenY);
                return false;
            }
        });
    }

    @Override
    public void render() {
        // Clear screen and update camera
        ScreenUtils.clear(Color.CLEAR);
        camera.update();

        gameController.onEachFrame();
    }

    @Override
    public void dispose() {
        gameController.dispose();
    }
}

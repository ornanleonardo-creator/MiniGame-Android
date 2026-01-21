package com.example.monapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class GameView extends View {

    float birdY;
    float velocity = 0;
    float gravity = 1.5f;

    int pipeX;
    int gapY;
    int gapSize = 300;

    int score = 0;
    boolean gameOver = false;

    Paint birdPaint = new Paint();
    Paint pipePaint = new Paint();
    Paint textPaint = new Paint();

    Random random = new Random();

    // 👉 CONSTRUCTEUR POUR XML (IMPORTANT)
    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Init commun
    private void init() {
        birdY = 500;
        pipeX = 1200;
        gapY = 400;

        birdPaint.setColor(Color.YELLOW);
        pipePaint.setColor(Color.GREEN);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60);

        setOnClickListener(v -> {
            if (!gameOver) {
                velocity = -20;
            } else {
                resetGame();
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.CYAN);

        velocity += gravity;
        birdY += velocity;

        canvas.drawCircle(200, birdY, 40, birdPaint);

        pipeX -= 10;
        if (pipeX < -200) {
            pipeX = getWidth();
            gapY = random.nextInt(getHeight() - gapSize);
            score++;
        }

        canvas.drawRect(pipeX, 0, pipeX + 120, gapY, pipePaint);
        canvas.drawRect(pipeX, gapY + gapSize, pipeX + 120, getHeight(), pipePaint);

        if (birdY < 0 || birdY > getHeight()
                || (200 > pipeX && 200 < pipeX + 120
                && (birdY < gapY || birdY > gapY + gapSize))) {
            gameOver = true;
        }

        canvas.drawText("Score: " + score, 50, 80, textPaint);

        if (gameOver) {
            canvas.drawText("GAME OVER", 200, getHeight() / 2, textPaint);
            canvas.drawText("Tap to restart", 160, getHeight() / 2 + 80, textPaint);
        } else {
            invalidate();
        }
    }

    void resetGame() {
        birdY = 500;
        velocity = 0;
        pipeX = getWidth();
        score = 0;
        gameOver = false;
        invalidate();
    }
            }

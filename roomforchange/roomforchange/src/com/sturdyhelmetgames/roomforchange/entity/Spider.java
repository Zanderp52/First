package com.sturdyhelmetgames.roomforchange.entity;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sturdyhelmetgames.roomforchange.assets.Assets;
import com.sturdyhelmetgames.roomforchange.level.Level;
import com.sturdyhelmetgames.roomforchange.tween.EntityAccessor;

public class Spider extends Enemy {

	public Spider(float x, float y, Level level) {
		super(x, y, 1f, 1f, level);
		health = 3;
	}

	@Override
	public void render(float delta, SpriteBatch batch) {
		if (blinkTick < BLINK_TICK_MAX) {
			super.render(delta, batch);
			batch.draw(Assets.spiderFront.getKeyFrame(stateTime, true),
					bounds.x - 0.1f, bounds.y - 0.1f, width, height);
			for (int i = 0; i < 10; i++) {
				batch.draw(Assets.getGameObject("spider-thread"),
						bounds.x - 0.1f, bounds.y + i * height + 1f - 0.1f,
						width, height);
			}
		}
	}

	@Override
	public void update(float fixedStep) {
		stateTime += fixedStep;
		final TweenManager tweenManager = level.entityTweenManager;
		if (!tweenManager.containsTarget(this)) {
			Tween.to(this, EntityAccessor.POSITIONY, MathUtils.random(3f))
					.target(level.gameScreen.currentCamPosition.y
							+ MathUtils.random(7f)).ease(Quad.IN)
					.start(tweenManager);
		}

		if (bounds.overlaps(level.player.bounds)) {
			level.player.takeDamage();
		}
	}

}
package com.rezagames.echomaze.utils

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rezagames.echomaze.systems.MovementSystem
import com.rezagames.echomaze.systems.PlayerInputSystem
import com.rezagames.echomaze.systems.RenderSystem

data class GameContext(
    val batch: SpriteBatch,
    val engine: Engine,
    val renderSystem: RenderSystem,
    val playerInputSystem: PlayerInputSystem,
    val movementSystem: MovementSystem,
    val assetManager: AssetManager,
)

package com.rezagames.echomaze.utils

import com.badlogic.gdx.Gdx

object GameConstants {
    const val CAMERA_LAZINESS = 0.05F
    const val PLAYER_SPEED = 150f
    const val PLAYER_WIDTH = 16f
    const val PLAYER_HEIGHT = 24f
    const val PLAYER_HITBOX_WIDTH = 3f
    const val PLAYER_HITBOX_OFFSET_X = (PLAYER_WIDTH - PLAYER_HITBOX_WIDTH) / 2f
    const val PLAYER_HITBOX_OFFSET_Y = -5f
    const val PLAYER_HITBOX_HEIGHT = 16f
    const val WORLD_WIDTH = 1920
    const val WORLD_HEIGHT = 1080
    const val WORLD_WIDTH_CAMERA = 355.5555f
    const val WORLD_HEIGHT_CAMERA = 200f
    const val PLAYER_STARTING_POSITION_X = (WORLD_WIDTH - PLAYER_WIDTH)/2
    const val PLAYER_STARTING_POSITION_Y = (WORLD_HEIGHT - PLAYER_HEIGHT)/2
}

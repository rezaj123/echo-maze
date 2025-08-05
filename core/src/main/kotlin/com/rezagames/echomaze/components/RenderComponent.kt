package com.rezagames.echomaze.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import ktx.ashley.mapperFor

data class RenderComponent(
    var texture : Texture ?= null
): Component{
    companion object {
        val mapper = mapperFor<RenderComponent>()
    }
}

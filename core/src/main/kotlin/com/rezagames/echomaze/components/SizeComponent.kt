package com.rezagames.echomaze.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

data class SizeComponent (
    var height : Float = 0f,
    var width : Float = 0f
): Component{
    companion object {
        val mapper = mapperFor<SizeComponent>()
    }
}

package me.gregd.www.model.cineworld

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use=JsonTypeInfo.Id.MINIMAL_CLASS, 
    include=JsonTypeInfo.As.WRAPPER_ARRAY, 
    property="type"
)
abstract class Event {}
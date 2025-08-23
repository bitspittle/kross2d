package dev.bitspittle.kross2d.core.concurrency

import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER

@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
@Retention(AnnotationRetention.SOURCE)
@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
internal expect annotation class Synchronized()

@Target(FIELD)
@Retention(AnnotationRetention.SOURCE)
@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
internal expect annotation class Volatile()

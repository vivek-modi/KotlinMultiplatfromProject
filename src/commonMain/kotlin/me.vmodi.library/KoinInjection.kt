package me.vmodi.library

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun libraryModule() = timePassModule

val timePassModule = module {
    singleOf(::TimePass)
}
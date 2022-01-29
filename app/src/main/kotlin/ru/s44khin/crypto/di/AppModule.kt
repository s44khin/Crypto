package ru.s44khin.crypto.di

import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
object AppModule
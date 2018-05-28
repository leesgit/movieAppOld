package com.lbc.practice.movieapp.data.resource;

import java.lang.annotation.Documented
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier

import javax.inject.Scope;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class RemoteScope

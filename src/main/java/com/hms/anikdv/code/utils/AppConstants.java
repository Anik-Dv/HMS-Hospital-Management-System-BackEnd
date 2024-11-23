package com.hms.anikdv.code.utils;

/**
 * This is Application Constants Class.
 *
 * @author AnikDV
 *
 */
public class AppConstants {

    /**
     * This is JWT Token Validity Time
     */
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    /**
     * This is Constant key Admin User
     */
    public static final Long ADMIN = 9901L;

    /**
     * This is Constant key Patient User
     */
    public static final Long PATIENT = 9902L;

    /**
     * This is Constant key Doctor User
     */
    public static final Long DOCTOR = 9903L;

    /**
     * This is Constant key Authorization header
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";
}

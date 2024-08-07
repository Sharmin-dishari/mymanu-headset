/**********************************************************************************************************************
 * Copyright © 2016 GEMALTO                                                                                           *
 * *
 * This computer program includes confidential and proprietary information of Gemalto and is a trade secret           *
 * of Gemalto. All use, disclosure, and/or reproduction is prohibited unless authorized in writing by Gemalto.        *
 * All Rights Reserved.                                                                                               *
 * *
 * The computer program is provided "AS IS" without warranty of any kind. Gemalto makes no warranties to              *
 * any person or entity with respect to the computer program and disclaims all other warranties, expressed            *
 * or implied. Gemalto expressly disclaims any implied warranty of merchantability, fitness for particular            *
 * purpose and any warranty which may arise from course of performance, course of dealing, or usage of                *
 * trade. Further Gemalto does not warrant that the computer program will meet requirements or that                   *
 * operation of the computer program will be uninterrupted or error-free.                                             *
 * *
 **********************************************************************************************************************/

package com.mymanu.companionapp;

/**
 * @author mbaharsyah
 */
public final class SmxxConstants {

    public static final String PATH_PREFIX = "/gsma/rsp2/es9plus";

    public static final String PATH_INITIATE_AUTHENTICATION = "/initiateAuthentication";
    public static final String PATH_AUTHENTICATE_CLIENT = "/authenticateClient";
    public static final String PATH_GET_BOUND_PROFILE_PACKAGE="/getBoundProfilePackage";
    public static final String PATH_HANDLE_NOTIFICATION = "/handleNotification";
    public static final String PATH_CANCEL_SESSION="/cancelSession";

    public static final String HEADER_PROTOCOL = "X-Admin-Protocol";
    public static final String HEADER_VALUE_PROTOCOL = "gsma/rsp/v2.3.0";

    public static final String HEADER_USERAGENT = "User-Agent";
    public static final String HEADER_VALUE_USERAGENT = "gsma-rsp-lpad";


    private SmxxConstants() {
    }
}

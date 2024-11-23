package com.hms.anikdv.code.services;


import com.hms.anikdv.code.payloads.AdminPayload;
import com.hms.anikdv.code.payloads.UserPayload;

import java.util.List;

/**
 * This is Declaration of Admin Service
 *
 * @author AnikDV
 */
public interface AdminService {

    /**
     * This Method For Registration Admin
     *
     * @param userPayload
     * @return a new registered Admin
     */
    UserPayload registerAdmin(final UserPayload userPayload) throws Exception;

    /**
     * Update Admin Information
     * @param adminId
     * @param adminPayload containing updated data
     * @return updated Admin
     * @throws Exception if Admin not found
     */
    public AdminPayload updateAdmin(final Long adminId, final AdminPayload adminPayload) throws Exception;


        /**
         * This Method For Delete Admin
         *
         * @param patientId
         * @return a Delete Admin | True/False
         */
    boolean deleteAdmin(final Long patientId) throws Exception;

    /**
     * This Method For Get All Admins
     *
     * @return a All Admins
     */
    List<AdminPayload> getAllAdmin();


    /**
     * This Method For Get Single Admin
     * @param adminId
     * @return a Single Admin
     */
    AdminPayload getSingleAdmin(final Long adminId);
}

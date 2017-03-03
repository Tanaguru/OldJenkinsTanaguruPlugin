/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.rest.util;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.tanaguru.model.AuditModel;

/**
 * This class store temporarily the lauched audit from the Rest call
 *
 * @author mkebri
 */
public class TrackAuditImpl {

    private final static Logger LOGGER = Logger.getLogger(TrackAuditImpl.class);
    HashMap<String, AuditModel> hashMap = new HashMap<>();

    /**
     * Add audit lauched from the rest call api
     *
     * @param idAudit
     * @param am
     */
    public void addAuditToHashmap(String codeAudit, AuditModel am) {
        LOGGER.info("Adding AuditModel to TackAuditImpl.");
        LOGGER.info(" AuditModel added state: "+am.getState());
        hashMap.put(codeAudit, am);

    }

    /**
     * Remove audit when we have used the result of stocked audit
     *
     * @param idAudit
     */
    public void removeAuditFromHashmap(String codeAudit) {

        if (hashMap.containsKey(codeAudit)) {
            LOGGER.info("Remove AuditModel from TackAuditImpl: auditCode: " + getAuditModel(codeAudit).getIdCode());
            hashMap.remove(codeAudit);
        }
    }

    /**
     * get AuditModel
     *
     * @param index
     * @return
     */
    public AuditModel getAuditModel(String codeAudit) {
        if (hashMap.containsKey(codeAudit)) {
            return hashMap.get(codeAudit);
        } else {
            return null;
        }
    }

    
    /**
     * return the number of auditModel that the hashMap contains
     * @return 
     */
    public int getSize() {
        return this.hashMap.size();

    }
}

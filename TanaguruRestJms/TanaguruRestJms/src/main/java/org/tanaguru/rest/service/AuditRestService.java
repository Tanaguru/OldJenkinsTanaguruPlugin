/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.rest.service;

/**
 *
 * @author tanaguru
 */
public interface AuditRestService {

    public void auditPage(final String pageURL,
            final String level,
            final String tblMarker,
            final String prTblMarker,
            final String dcrImgMarker,
            final String infImgMarker
    );
    
    
    public String auditPage(final String pageURL,
            final String level
         
    );
    
    
    public String greeting();

}

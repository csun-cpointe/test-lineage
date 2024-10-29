package com.boozallen;

/*-
 * #%L
 * Test Lineage::Pipelines::Spark Pipeline
 * %%
 * Copyright (C) 2021 Booz Allen
 * %%
 * All Rights Reserved. You may not copy, reproduce, distribute, publish, display, 
 * execute, modify, create derivative works of, transmit, sell or offer for resale, 
 * or in any way exploit any part of this solution without Booz Allen Hamilton’s 
 * express written permission.
 * #L%
 */


import jakarta.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;

import com.boozallen.aissemble.core.metadata.MetadataModel;

/**
 * Performs the business logic for Step1.
 *
 * Because this class is {@link ApplicationScoped}, exactly one managed singleton instance will exist
 * in any deployment.
 *
 * GENERATED STUB CODE - PLEASE ***DO*** MODIFY
 *
 * Originally generated from: templates/data-delivery-spark/synchronous.processor.impl.java.vm
 */
@ApplicationScoped
public class Step1 extends Step1Base {

    private static final Logger logger = LoggerFactory.getLogger(Step1.class);

    public Step1(){
        super("synchronous",getDataActionDescriptiveLabel());
    }

    /**
    * Provides a descriptive label for the action that can be used for logging (e.g., provenance details).
    *
    * @return descriptive label
    */
    private static String getDataActionDescriptiveLabel(){
        // TODO: replace with descriptive label
        return"Step1";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeStepImpl() {
        // TODO: Add your business logic here for this step!
        logger.error("Implement executeStepImpl(..) or remove this pipeline step!");

    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected MetadataModel createProvenanceMetadata(String resource,String subject,String action){
        // TODO: Add any additional provenance-related metadata here
        return new MetadataModel(resource,subject,action,Instant.now());
    }
}

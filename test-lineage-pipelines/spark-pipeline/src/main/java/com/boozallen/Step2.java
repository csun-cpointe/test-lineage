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
import io.smallrye.mutiny.Multi;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;



import java.time.Instant;

import com.boozallen.aissemble.core.metadata.MetadataModel;

/**
 * The Reactive Messaging connector consumes one message at a time - this class writes
 * multiple source data records within one message so that they can be ingested
 * in batches.
 *
 * Because this class is {@link ApplicationScoped}, exactly one managed singleton instance will exist
 * in any deployment.
 *
 * GENERATED STUB CODE - PLEASE ***DO*** MODIFY
 *
 * Originally generated from: templates/data-delivery-spark/asynchronous.processor.impl.java.vm 
 */
@ApplicationScoped
public class Step2 extends Step2Base {

    private static final Logger logger = LoggerFactory.getLogger(Step2.class);

    public Step2() {
		super("asynchronous", getDataActionDescriptiveLabel());
	}

	/**
	 * Provides a descriptive label for the action that can be used for logging (e.g., provenance details).
	 *
	 * @return descriptive label
	 */
	private static String getDataActionDescriptiveLabel() {
		// TODO: replace with descriptive label
		return "Step2";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CompletionStage<Void> executeStepImpl() {
		/*  Asynchronous steps return CompletionStage types to support
			asynchronous processing. Synchronous results can be returned as CompletableFuture.completedFuture({value})
			while asynchronous processing can be achieved with CompletableFuture run/supply async methods or any other
			compatible implementation. See https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2/model/model.html
			for additional details
		 */
		// TODO add processing logic here
		return CompletableFuture.completedFuture(null);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MetadataModel createProvenanceMetadata(String resource, String subject, String action) {
		// TODO: Add any additional provenance-related metadata here
		return new MetadataModel(resource, subject, action, Instant.now());
	}
}

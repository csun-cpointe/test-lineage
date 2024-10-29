###
# #%L
# Test Lineage::Pipelines::Py Spark Pipeline Messaging
# %%
# Copyright (C) 2021 Booz Allen
# %%
# All Rights Reserved. You may not copy, reproduce, distribute, publish, display,
# execute, modify, create derivative works of, transmit, sell or offer for resale,
# or in any way exploit any part of this solution without Booz Allen Hamilton’s
# express written permission.
# #L%
###
from py_spark_pipeline_messaging.step.spark_sync_step import SparkSyncStep
from threading import Thread
from krausening.logging import LogManager

"""
Driver to run the PySparkPipelineMessaging.

GENERATED STUB CODE - PLEASE ***DO*** MODIFY

Originally generated from: templates/data-delivery-pyspark/pipeline.driver.py.vm 
"""

logger = LogManager.get_instance().get_logger("PySparkPipelineMessaging")


def start_messaging_inbound_step(step):
    """
    Used for starting messaging inbound steps on individual threads to prevent a blocking situation. Override this method to specify your own configurations.
    """
    step_execution = Thread(target=step.execute_step)
    step_execution.start()
    logger.info("Step {} started and waiting for messages".format(type(step).__name__))


if __name__ == "__main__":
    logger.info("STARTED: PySparkPipelineMessaging driver")

    # TODO: Execute steps in desired order and handle any inbound and outbound types
    start_messaging_inbound_step(SparkSyncStep())

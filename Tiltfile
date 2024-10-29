allow_k8s_contexts('local')
docker_prune_settings(num_builds=1, keep_recent=1)

aissemble_version = '1.10.0-SNAPSHOT'

build_args = { 'DOCKER_BASELINE_REPO_ID': 'ghcr.io/',
               'VERSION_AISSEMBLE': aissemble_version}

# Kafka
yaml = helm(
    'test-lineage-deploy/src/main/resources/apps/kafka-cluster',
    values=['test-lineage-deploy/src/main/resources/apps/kafka-cluster/values.yaml',
        'test-lineage-deploy/src/main/resources/apps/kafka-cluster/values-dev.yaml']
)
k8s_yaml(yaml)

yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/spark-infrastructure',
   name='spark-infrastructure',
   values=['test-lineage-deploy/src/main/resources/apps/spark-infrastructure/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/spark-infrastructure/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/metadata',
   name='metadata',
   values=['test-lineage-deploy/src/main/resources/apps/metadata/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/metadata/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/postgres',
   name='postgres',
   values=['test-lineage-deploy/src/main/resources/apps/postgres/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/postgres/values-dev.yaml']
)
k8s_yaml(yaml)
k8s_yaml('test-lineage-deploy/src/main/resources/apps/spark-worker-image/spark-worker-image.yaml')


k8s_yaml('test-lineage-deploy/src/main/resources/apps/logistic-training-image/logistic-training-image.yaml')


yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/mlflow-ui',
   name='mlflow-ui',
   values=['test-lineage-deploy/src/main/resources/apps/mlflow-ui/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/mlflow-ui/values-dev.yaml']
)
k8s_yaml(yaml)
k8s_yaml('test-lineage-deploy/src/main/resources/apps/logistic-training1-image/logistic-training1-image.yaml')


yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/spark-operator',
   name='spark-operator',
   values=['test-lineage-deploy/src/main/resources/apps/spark-operator/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/spark-operator/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/policy-decision-point',
   name='policy-decision-point',
   values=['test-lineage-deploy/src/main/resources/apps/policy-decision-point/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/policy-decision-point/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/model-training-api',
   name='model-training-api',
   values=['test-lineage-deploy/src/main/resources/apps/model-training-api/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/model-training-api/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/s3-local',
   name='s3-local',
   values=['test-lineage-deploy/src/main/resources/apps/s3-local/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/s3-local/values-dev.yaml']
)
k8s_yaml(yaml)
yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/shared-infrastructure',
   name='shared-infrastructure',
   values=['test-lineage-deploy/src/main/resources/apps/shared-infrastructure/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/shared-infrastructure/values-dev.yaml']
)
k8s_yaml(yaml)

yaml = helm(
   'test-lineage-deploy/src/main/resources/apps/pipeline-invocation-service',
   name='pipeline-invocation-service',
   values=['test-lineage-deploy/src/main/resources/apps/pipeline-invocation-service/values.yaml',
       'test-lineage-deploy/src/main/resources/apps/pipeline-invocation-service/values-dev.yaml']
)
k8s_yaml(yaml)

# logistic-training-compiler
local_resource(
    name='compile-logistic-training',
    cmd='cd test-lineage-pipelines/classification-training/logistic-training && poetry run behave tests/features && poetry build && cd - && \
    cp -r test-lineage-pipelines/classification-training/logistic-training/dist test-lineage-docker/test-lineage-logistic-training-docker/target/logistic-training',
    deps=['test-lineage-pipelines/classification-training/logistic-training'],
    auto_init=False,
    ignore=['**/dist/']
)


k8s_kind('SparkApplication', image_json_path='{.spec.image}')

# test-lineage-logistic-training-docker
docker_build(
    ref='test-lineage-logistic-training-docker',
    context='test-lineage-docker/test-lineage-logistic-training-docker',
    build_args=build_args,
    extra_tag='test-lineage-logistic-training-docker:latest',
    dockerfile='test-lineage-docker/test-lineage-logistic-training-docker/src/main/resources/docker/Dockerfile'
)


# test-lineage-logistic-training1-docker
docker_build(
    ref='test-lineage-logistic-training1-docker',
    context='test-lineage-docker/test-lineage-logistic-training1-docker',
    build_args=build_args,
    extra_tag='test-lineage-logistic-training1-docker:latest',
    dockerfile='test-lineage-docker/test-lineage-logistic-training1-docker/src/main/resources/docker/Dockerfile'
)



yaml = local('helm template oci://ghcr.io/boozallen/aissemble-spark-application-chart --version %s --values test-lineage-pipelines/spark-pipeline/src/main/resources/apps/spark-pipeline-base-values.yaml,test-lineage-pipelines/spark-pipeline/src/main/resources/apps/spark-pipeline-dev-values.yaml' % aissemble_version)
k8s_yaml(yaml)
k8s_resource('spark-pipeline', port_forwards=[port_forward(4747, 4747, 'debug')], auto_init=False, trigger_mode=TRIGGER_MODE_MANUAL)
# spark-worker-image
docker_build(
    ref='test-lineage-spark-worker-docker',
    context='test-lineage-docker/test-lineage-spark-worker-docker',
    build_args=build_args,
    extra_tag='test-lineage-spark-worker-docker:latest',
    dockerfile='test-lineage-docker/test-lineage-spark-worker-docker/src/main/resources/docker/Dockerfile'
)


# policy-decision-point
docker_build(
    ref='test-lineage-policy-decision-point-docker',
    context='test-lineage-docker/test-lineage-policy-decision-point-docker',
    build_args=build_args,
    dockerfile='test-lineage-docker/test-lineage-policy-decision-point-docker/src/main/resources/docker/Dockerfile'
)



# python-pipeline-compiler
local_resource(
    name='compile-python-pipeline',
    cmd='cd test-lineage-pipelines/python-pipeline && poetry run behave tests/features && poetry build && cd - && \
    cp -r test-lineage-pipelines/python-pipeline/dist/* test-lineage-docker/test-lineage-spark-worker-docker/target/dockerbuild/python-pipeline && \
    cp test-lineage-pipelines/python-pipeline/dist/requirements.txt test-lineage-docker/test-lineage-spark-worker-docker/target/dockerbuild/requirements/python-pipeline',
    deps=['test-lineage-pipelines/python-pipeline'],
    auto_init=False,
    ignore=['**/dist/']
)

# logistic-training1-compiler
local_resource(
    name='compile-logistic-training1',
    cmd='cd test-lineage-pipelines/classification-training/logistic-training1 && poetry run behave tests/features && poetry build && cd - && \
    cp -r test-lineage-pipelines/classification-training/logistic-training1/dist test-lineage-docker/test-lineage-logistic-training1-docker/target/logistic-training1',
    deps=['test-lineage-pipelines/classification-training/logistic-training1'],
    auto_init=False,
    ignore=['**/dist/']
)

yaml = local('helm template oci://ghcr.io/boozallen/aissemble-spark-application-chart --version %s --values test-lineage-pipelines/python-pipeline/src/python_pipeline/resources/apps/python-pipeline-base-values.yaml,test-lineage-pipelines/python-pipeline/src/python_pipeline/resources/apps/python-pipeline-dev-values.yaml' % aissemble_version)
k8s_yaml(yaml)
k8s_resource('python-pipeline', port_forwards=[port_forward(4747, 4747, 'debug')], auto_init=False, trigger_mode=TRIGGER_MODE_MANUAL)

# py-spark-pipeline-messaging-compiler
local_resource(
    name='compile-py-spark-pipeline-messaging',
    cmd='cd test-lineage-pipelines/py-spark-pipeline-messaging && poetry run behave tests/features && poetry build && cd - && \
    cp -r test-lineage-pipelines/py-spark-pipeline-messaging/dist/* test-lineage-docker/test-lineage-spark-worker-docker/target/dockerbuild/py-spark-pipeline-messaging && \
    cp test-lineage-pipelines/py-spark-pipeline-messaging/dist/requirements.txt test-lineage-docker/test-lineage-spark-worker-docker/target/dockerbuild/requirements/py-spark-pipeline-messaging',
    deps=['test-lineage-pipelines/py-spark-pipeline-messaging'],
    auto_init=False,
    ignore=['**/dist/']
)

yaml = local('helm template oci://ghcr.io/boozallen/aissemble-spark-application-chart --version %s --values test-lineage-pipelines/py-spark-pipeline-messaging/src/py_spark_pipeline_messaging/resources/apps/py-spark-pipeline-messaging-base-values.yaml,test-lineage-pipelines/py-spark-pipeline-messaging/src/py_spark_pipeline_messaging/resources/apps/py-spark-pipeline-messaging-dev-values.yaml' % aissemble_version)
k8s_yaml(yaml)
k8s_resource('py-spark-pipeline-messaging', port_forwards=[port_forward(4747, 4747, 'debug')], auto_init=False, trigger_mode=TRIGGER_MODE_MANUAL)

# Add deployment resources here
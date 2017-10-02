# cloud-dataflow-pipeline-sydney
Spins up a Cloud Dataflow Pipeline in the Sydney region, reads lot's of useless Wikipedia data from BigQuery, processes it, and dumps the results to a CSV file in GCS.

export GOOGLE_APPLICATION_CREDENTIALS=key.json

--project=grey-sort-challenge
--runner=DataflowRunner
--jobName=hello-gdg-cloud-melbourne-from-the-new-sydney-region
--maxNumWorkers=50
--zone=australia-southeast1-a
--stagingLocation=gs://sydney-dataflow-pipeline/jars
--tempLocation=gs://sydney-dataflow-pipeline/tmp

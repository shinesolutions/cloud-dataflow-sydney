# cloud-dataflow-pipeline-sydney
[![Open in Cloud Shell](http://gstatic.com/cloudssh/images/open-btn.svg)](https://console.cloud.google.com/cloudshell/open?git_repo=https%3A%2F%2Fgithub.com%2Fshinesolutions%2Fcloud-dataflow-sydney&page=shell)

Spins up a Cloud Dataflow Pipeline in the Sydney region, reads lot's of useless Wikipedia data from BigQuery, processes it, and dumps the results to a CSV file in GCS.

export `GOOGLE_APPLICATION_CREDENTIALS`=<path-to-your-json-auth-key>

`--project=grey-sort-challenge
 --runner=DataflowRunner
 --jobName=hello-gdg-cloud-melbourne-from-the-new-sydney-region
 --maxNumWorkers=50
 --zone=australia-southeast1-a
 --stagingLocation=gs://<your-bucket>/jars
 --tempLocation=gs://<your-bucket>/tmp`

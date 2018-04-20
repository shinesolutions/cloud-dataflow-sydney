# Running a dataflow pipeline

This tutorial will help you run this demo pipeline in the Sydney GCP region.
The pipeline reads data
It'll show you how to create the cloud storage buckets you'll need, how to
start the pipeline, and view the results.

When you clicked "open in cloud shell", this github repo was cloned onto your
cloud shell instance (creating a cloud shell instance if you did not have one running).
Follow these steps to get everything going.

## Step 1: create a GCS bucket

Dataflow needs a GCS bucket to store your java classes, and we'll also need somewhere
to put the output of our pipeline.

In the cloud shell, run this command (replacing `<some-bucket-name>` with a good name):
```
gsutil mb -l australia-southeast1 gs://<some-bucket-name>
```

If you get an error, it's most likely that there's another bucket with the same name, so try a different one.
Remember the name of your bucket, you'll need it again soon.

`gsutil` is the command you'll use for most operations relating to GCS buckets. `gsutil mb` here stands for "make bucket". To find out more details use `gsutil help` or `gsutil help mb`.

## Step 2: Edit the Java code

We need to tell the Java code for our pipeline where to write the data. There are ways to pass parameters
to dataflow pipelines on the command line, but to keep the code simple we'll just put the bucket name directly in the Java class.

`walkthrough editor-open-file src/main/java/shinesolutions/SydneyPipeline.java "Open the pipeline code"`

The line you need to change has a comment above it that says `EDIT THIS LINE`. Replace `<put that bucket name you chose here>` with the bucket name you created in step 1. Save the file.

If that link seems to do nothing, it's most likely because you have third-party cookies disabled in your browser. Click the editor link `walkthrough orion-editor-icon`, and it should provide you a way to work around this problem. You'll then need to navigate to the file we want to edit: `src/main/java/shinesolutions/SydneyPipeline.java`.


## Step 3: Build the dataflow pipeline

Back in the command line, we need to build the java code ready to be deployed in the next step.
```
mvn compile
```
Maven is a build tool for Java. It will download the dependencies needed by the pipeline and compile our Java code. If all goes well, you should see "BUILD SUCCESS" at the end.

## Step 4: Deploy the pipeline

Now we need to tell GCP about the pipeline we want to run. To do that we use this command:
```
mvn exec:java -Dexec.mainClass=shinesolutions.SydneyPipeline \
  -Dexec.args="--project=<name of this project> \
    --runner=DataflowRunner \
    --zone=australia-southeast1-a \
    --stagingLocation=gs://<some-bucket-name>/jars \
    --tempLocation=gs://<some-bucket-name>/tmp"
```
Replace `<name of this project>` with your GCP project name, and `<some-bucket-name>` with your bucket from earlier on.

## Step 5: Watch the data flow.

Go to the [dataflow console](https://console.cloud.google.com/dataflow) and you should be able to see your job running. When it has completed, move on to the next step.

## Step 6: View the results.

To copy the output to your cloud shell, we're going to use another `gsutil` command.
```
gsutil cp gs://<some-bucket-name>/output/titles.csv titles.csv
```
This will copy the output file from cloud storage to your local cloud shell. You can view the file using the standard Unix command `more`:
```
more titles.csv
```

## Step 7: Bask in the afterglow.

If all went well, then you are done. Good job!

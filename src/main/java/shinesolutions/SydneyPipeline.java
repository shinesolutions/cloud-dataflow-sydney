package shinesolutions;

import com.google.api.services.bigquery.model.TableRow;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

/**
 * Read one billion rows of wikipedia data from BigQuery,
 * find all the titles that contain "elbourne",
 * write those rows out to a CSV file in a GCS bucket
 */
public class SydneyPipeline {
    private static final String BIG_QUERY_TABLE = "bigquery-samples:wikipedia_benchmark.Wiki1B";

    // EDIT THE LINE BELOW
    private static final String BUCKET = "gs://<put that bucket name you chose here>/output/titles";

    public static void main(String[] args) {
        DataflowPipelineOptions options = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(DataflowPipelineOptions.class);
        Pipeline pipeline = Pipeline.create(options);

        pipeline.apply(BigQueryIO.read().from(BIG_QUERY_TABLE))
                .apply(ParDo.of(new DoFn<TableRow, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) throws Exception {
                        TableRow inputRow = c.element();
                        String title = (String) inputRow.get("title");
                        if (title.contains("elbourne")) {
                          c.output(title);
                        }
                    }
                }))
                .apply(TextIO.write().to(BUCKET)
                        .withoutSharding()
                        .withSuffix(".csv"));
        pipeline.run();
    }
}

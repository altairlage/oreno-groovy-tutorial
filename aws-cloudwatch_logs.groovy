@Grapes([
  @Grab(group='com.amazonaws', module='aws-java-sdk', version='1.10.28'),
  @Grab(group='org.jyaml', module='jyaml', version='1.3'),
])

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AWSLogsClient;
import com.amazonaws.services.logs.model.*;
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.ho.yaml.Yaml;

// Import topicARN from setting.yml
Map settings = Yaml.load(new File("setting.yml"));

String log_group = "JavaSDKTest";
String log_stream = "foo";
String msg = '{"key": "value"}';
long epoch = System.currentTimeMillis();

//def post_to_cloudwatch_logs(line) {
AWSLogsClient cwlClient = new AWSLogsClient();		                           
cwlClient.setRegion(Region.getRegion(Regions.fromName("ap-northeast-1")));
//
InputLogEvent inputLogEvent = new InputLogEvent();
event = inputLogEvent.withMessage(msg).withTimestamp(epoch);
//System.out.println(event);
//
LogStream logStream = new LogStream()
stream = logStream.withLogStreamName(log_stream)
//System.out.println(stream);

DescribeLogStreamsRequest describeLogStreamsRequest = new DescribeLogStreamsRequest(log_group);
DescribeLogStreamsResult describeLogStreamsResult = cwlClient.describeLogStreams(describeLogStreamsRequest);
for(int i = 0; i < describeLogStreamsResult.getLogStreams().size(); i++) {
  //
  System.out.println("Stream :" + describeLogStreamsResult.getLogStreams()[i].getLogStreamName());
  System.out.println("Stream :" + log_stream);
  //
  if (describeLogStreamsResult.getLogStreams()[i].getLogStreamName() == log_stream) { 
    //
    PutLogEventsRequest putLogEventsRequest = new PutLogEventsRequest(log_group, log_stream, [event]);
    //
    if (describeLogStreamsResult.getLogStreams()[i].getUploadSequenceToken()) {
      String token = describeLogStreamsResult.getLogStreams()[i].getUploadSequenceToken();
      PutLogEventsResult putLogEventsResult = cwlClient.putLogEvents(putLogEventsRequest.withSequenceToken(token));
      System.out.println(putLogEventsResult.getNextSequenceToken())
      //
      try {
        FileWriter fileWriter = new FileWriter("/tmp/cwl_next_tolen", false);
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));
        printWriter.println(putLogEventsResult.getNextSequenceToken())
        printWriter.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }   
    } else {
      PutLogEventsResult putLogEventsResult = cwlClient.putLogEvents(putLogEventsRequest);
    }
  }
}

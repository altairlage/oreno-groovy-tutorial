@Grapes([
  @Grab(group='com.amazonaws', module='aws-java-sdk', version='1.10.28'),
  @Grab(group='org.jyaml', module='jyaml', version='1.3'),
])

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sns.model.CreateTopicRequest;
//import com.amazonaws.services.sns.model.CreateTopicResult;
//import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
//import com.amazonaws.services.sns.model.DeleteTopicRequest;
import org.ho.yaml.Yaml;

// Import topicARN from setting.yml
Map settings = Yaml.load(new File("setting.yml"));

//create AWS Connection
AmazonSNSClient snsClient = new AmazonSNSClient();		                           
snsClient.setRegion(Region.getRegion(Regions.fromName(settings.get("region"))));

//create a new SNS topic
//CreateTopicRequest createTopicRequest = new CreateTopicRequest("MyNewTopic");
//CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
//print TopicArn
//System.out.println(createTopicResult);
//get request id for CreateTopicRequest from SNS metadata		
//System.out.println("CreateTopicRequest - " + snsClient.getCachedResponseMetadata(createTopicRequest));

//publish to a SNS topic
String msg = "My text published to SNS topic with email endpoint";
PublishRequest publishRequest = new PublishRequest(settings.get("topic_arn"), msg);
PublishResult publishResult = snsClient.publish(publishRequest);

//print MessageId of message published to SNS topic
System.out.println("MessageId - " + publishResult.getMessageId());

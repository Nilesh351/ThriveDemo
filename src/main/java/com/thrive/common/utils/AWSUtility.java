package com.thrive.common.utils;

import java.io.File;

import org.apache.log4j.LogManager;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.thrive.ui.core.Config;
import org.apache.log4j.Logger;
import java.time.Instant;
import java.net.URL;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AWSUtility {
	
	public static final String BUCKET_NAME = Config.getAWSReportBucketName();
	public static final String ACCESS_KEY = Config.getAWSAccessKey();
	public static final String SECRET_KEY = Config.getAWSSecretKey(); 		
	public static final String REGION= Config.getAWSRegion();
	
	static BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
	static AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(REGION).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
	
	
	protected static final Logger LOGGER = LogManager.getLogger(AWSUtility.class);
	
	// Upload file utility
	public static void copyToS3(String source,String awsTarget) throws Exception{
		
		
		try {
			client.putObject(BUCKET_NAME, awsTarget, new File(source));
			LOGGER.info("test report uploaded to S3 bucket..");
		} catch (AmazonServiceException e) {
			LOGGER.error(e.getErrorMessage());
		}
	}
	
	public static String getSignedUrl(String keyName) {
		
		// Set the presigned URL to expire after twenty-four hour.
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = Instant.now().toEpochMilli();
        expTimeMillis += 1000 * 60 * 60 * 24;
        expiration.setTime(expTimeMillis);
        
        // generating URL
        LOGGER.info("Generating pre-signed URL.");
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(BUCKET_NAME, keyName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);

        LOGGER.info("Pre-Signed URL: " + url.toString());
        return url.toString();
              
	}
	
	public static void sendMail(String to, String from, String subject, String body) {
		  final String TEXTBODY = "This email was sent through Amazon SES "
		      + "using the AWS SDK for Java.";

		    try {
		      AmazonSimpleEmailService client = 
		          AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.EU_WEST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
		          
		      SendEmailRequest request = new SendEmailRequest()
		          .withDestination(
		              new Destination().withToAddresses(to))
		          .withMessage(new Message()
		              .withBody(new Body()
		                  .withHtml(new Content()
		                      .withCharset("UTF-8").withData(body))
		                  .withText(new Content()
		                      .withCharset("UTF-8").withData(TEXTBODY)))
		              .withSubject(new Content()
		                  .withCharset("UTF-8").withData(subject)))
		          .withSource(from);
		
		      client.sendEmail(request);
		      LOGGER.info("Email sent!");
		    } catch (Exception ex) {
		      LOGGER.error("The email was not sent. Error message: " 
		          + ex.getMessage());
		    }
	}
	
}

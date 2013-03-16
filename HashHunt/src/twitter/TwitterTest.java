package twitter;

import twitter4j.*;

/**
* <p>This is a code example of Twitter4J Streaming API - sample method support.<br>
* Usage: java twitter4j.examples.PrintSampleStream<br>
* </p>
*
* @author Yusuke Yamamoto - yusuke at mac.com
*/
public final class TwitterTest {
    /**
* Main entry of this application.
*
* @param args
*/
    public static void main(String[] args) throws TwitterException {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
            	if (status.getPlace() != null) {
            		System.out.println("GEO: " + status.getPlace() + " @" + status.getUser().getScreenName() + " - " + status.getText());
            	} else {
            		System.out.print("");
            	}
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
//                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
//                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
//                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}

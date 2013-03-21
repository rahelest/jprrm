package twitter;

import shared.Chopshop;
import shared.Writer;
import twitter4j.*;

public final class TwitterTest implements Runnable{

	TwitterStream twitterStream;
	Boolean notrunning = true;
	Chopshop chopper = null;
	Boolean debug = false;

	public TwitterTest(Chopshop chopShop) {
		this.chopper = chopShop;
	}

	public void run() {
		if (notrunning) {
			notrunning = false;
			twitterStream = new TwitterStreamFactory().getInstance();
			StatusListener listener = new StatusListener() {
				@Override
				public void onStatus(Status status) {
					if (status.getGeoLocation() != null) {
						if (debug) {
							System.out.println(status.getGeoLocation().getLatitude() + " " + status.getGeoLocation().getLongitude() + " " + status.getText());
						} else {
							chopper.stringify(status.getGeoLocation().getLatitude() + " " + status.getGeoLocation().getLongitude() + " " + status.getText());
						}						
					} else {
						//TODO donothing?
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

	public void stop() throws TwitterException {
		twitterStream.shutdown();
		notrunning = true;
		//    	this.stop();
	}
	
	public static void main(String[] args) {
		TwitterTest test = new TwitterTest(new Chopshop(new Writer()));
		test.debug = true;
		test.run();
	}
}

package twitter;

import twitter4j.*;

public final class TwitterTest implements Runnable{

	TwitterStream twitterStream;
	Boolean notrunning = true;

	public void run() {
		if (notrunning) {
			notrunning = false;
			twitterStream = new TwitterStreamFactory().getInstance();
			StatusListener listener = new StatusListener() {
				@Override
				public void onStatus(Status status) {
					if (status.getGeoLocation() != null) {
						System.out.println(status.getGeoLocation() + /*" @" + status.getUser().getScreenName() + */" - " + status.getText());
					} else {
						//            		System.out.print("");
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
}

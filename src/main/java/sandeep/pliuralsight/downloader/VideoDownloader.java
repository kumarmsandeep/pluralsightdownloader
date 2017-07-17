package sandeep.pliuralsight.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class VideoDownloader implements Runnable {

	private Video video;

	public VideoDownloader(Video video) {
		this.video = video;
	}

	public void run() {
		try {
			downloadFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void downloadFile() throws IOException {
		URL website = new URL(video.getUrl());
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		String name = video.getLocalPath() + java.io.File.separator + video.getHeader() + java.io.File.separator
				+ video.getTitle() + java.io.File.separator + video.getName() + ".mp4";
		java.io.File file = new java.io.File(name);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(file);

			System.out.println("[" + Thread.currentThread().getName() + "] Downloading ..[" + video.getUrl() + "] to ["
					+ name + "]");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			System.out.println("[" + Thread.currentThread().getName() + "] Downloading completed ..[" + video.getUrl()
					+ "] to [" + name + "]");

		} else {
			System.out.println("[" + Thread.currentThread().getName() + "] Downloading already existed ..["
					+ video.getUrl() + "] at [" + name + "]");
		}
	}
}
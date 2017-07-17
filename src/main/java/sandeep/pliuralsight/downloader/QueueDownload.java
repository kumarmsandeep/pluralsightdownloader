package sandeep.pliuralsight.downloader;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QueueDownload {

	private static final String LEARNING_PLURALSIGHT_JAVA = "/Users/sakumarm/Documents/Personal/learning/pluralsight/Spring/";

	public static void main(String[] args) throws HeadlessException, UnsupportedFlavorException, IOException {
		Set<Video> videos = new HashSet<Video>();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		while (true) {

			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

			if (!data.startsWith("<div id=\"video-container\"")) {
				continue;
			}

			final String courceTitleLink = "id=\"course-title-link\">";
			int titleStart = data.indexOf(courceTitleLink);
			int titleEnd = data.indexOf("</a>", titleStart);
			String title = data.substring(titleStart + courceTitleLink.length(), titleEnd);

			final String subTitlePrefix = "id=\"module-clip-title\">";
			int subTitleStart = data.indexOf(subTitlePrefix);
			int subTitleEnd = data.indexOf("</div>", subTitleStart);
			String subTitle = data.substring(subTitleStart + subTitlePrefix.length(), subTitleEnd);
			String[] split = subTitle.split(" : ");

			final String videoPrefix = "class=\"vjs-tech\" src=\"";
			int videoStart = data.indexOf(videoPrefix);
			int videoEnd = data.indexOf("\"></video>", videoStart);
			String video = data.substring(videoStart + videoPrefix.length(), videoEnd);
			System.out.println(video);

			final Video videoObj = new Video();
			videoObj.setHeader(title);
			videoObj.setTitle(split[0]);
			videoObj.setName(split[1]);
			videoObj.setUrl(video);
			videoObj.setLocalPath(LEARNING_PLURALSIGHT_JAVA);

			if (!videos.contains(videoObj)) {
				newFixedThreadPool.submit(new VideoDownloader(videoObj));
			}

			StringSelection stringSelection = new StringSelection("");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}

	}

}

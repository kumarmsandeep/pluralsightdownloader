package sandeep.pliuralsight.downloader;

public class Video {
	private String header;

	private String title;

	private String url;

	private String name;
	
	private String localPath;

	public Video() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getHeader() == null) ? 0 : getHeader().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
		result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (getHeader() == null) {
			if (other.getHeader() != null)
				return false;
		} else if (!getHeader().equals(other.getHeader()))
			return false;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (getTitle() == null) {
			if (other.getTitle() != null)
				return false;
		} else if (!getTitle().equals(other.getTitle()))
			return false;
		if (getUrl() == null) {
			if (other.getUrl() != null)
				return false;
		} else if (!getUrl().equals(other.getUrl()))
			return false;
		return true;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

}

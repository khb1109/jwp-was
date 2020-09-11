package webserver.domain.request;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import utils.StaticFileType;

@Getter
public class RequestLine {

	private static final Pattern staticFilePattern = Pattern.compile("^.*[.][a-z]+$");
	private static final String REQUEST_LINE_DELIMITER = " ";
	private static final String EXTENSION_DELIMITER = "\\.";

	private final RequestMethod requestMethod;
	private final RequestUri requestUri;

	public RequestLine(String requestLine) throws UnsupportedEncodingException {
		String[] requestLineParameters = requestLine.split(REQUEST_LINE_DELIMITER);
		this.requestMethod = RequestMethod.from(requestLineParameters[0]);
		this.requestUri = new RequestUri(requestLineParameters[1]);
	}

	public boolean hasPathOfStaticFile() {
		Matcher matcher = staticFilePattern.matcher(getPath());
		return matcher.matches();
	}

	public boolean hasEqualPathWith(String target) {
		return getPath().equals(target);
	}

	public StaticFileType findExtension() {
		String[] split = getPath().split(EXTENSION_DELIMITER);
		String extensionName = split[split.length - 1];
		return StaticFileType.valueOf(extensionName.toUpperCase());
	}

	public String getPath() {
		return requestUri.getPath();
	}

	public String getQuery() {
		return requestUri.getQuery();
	}
}

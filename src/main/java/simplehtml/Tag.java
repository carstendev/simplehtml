package simplehtml;

import java.util.Optional;

/**
 * Specifies some of the most used HTML tags.
 * 
 * @author c.timpert
 */
enum Tag implements HtmlTag {
	
	HTML("<html>","</html>"), HEAD("<head>","</head>"), BODY("<body>","</body>"),
	H1("<h1>","</h1>"), H2("<h2>","</h2>"), H3("<h3>","</h3>"), H4("<h4>","</h4>"), H5("<h5>","</h5>"), H6("<h6>","</h6>"),
	P("<p>","</p>"), PRE("<pre>","</pre>"), SPAN("<span>","</span>"), BOLD("<b>","</b>"), ITALIC("<i>","</i>"), UNDERLINE("<u>","</u>"),
	BREAK("<br>", null);
	
	private String openingTag;
	private String closingTag;

	private Tag(String openingTag, String closingTag) {
		this.openingTag = openingTag;
		this.closingTag = closingTag;
	}

	public String getOpeningTag() {
		return openingTag;
	}

	public Optional<String> getClosingTag() {
		return Optional.of(closingTag);
	}

}

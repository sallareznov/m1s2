package car;

public class ItemBuilder {
	
	public String buildList(String[] names, String canonicalPath) {
		final StringBuilder menu = new StringBuilder();
		menu.append("<h1>Index of " + canonicalPath + "</h1>");
		menu.append("<pre><img src=\"/apache-icons/back.gif\" alt=\"[DIR]\"> <a href=\"..\">Parent Directory</a></pre>");
		for (final String name : names) {
			menu.append(buildItem("/apache-icons/text.gif", "[TXT]",  name));
		}
		return menu.toString();
	}

	private String buildItem(String src, String alt, String href) {
		return "<img src=\"" + src + "\" alt=\"" + alt + "\"> <a href= \"/rest/api/ftp/" + href + "\">" + href + "</a><br>";
	}

}

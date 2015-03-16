package car;

import org.apache.commons.net.ftp.FTPFile;

public class ItemBuilder {
	
	public String buildList(String canonicalPath, FTPFile[] files, String directorySeparator) {
		final StringBuilder menu = new StringBuilder();
		menu.append("<h1>Index of " + canonicalPath + "</h1>");
		menu.append("<pre><img src=\"/apache-icons/back.gif\" alt=\"[DIR]\"> <a href=\"..\">Parent Directory</a></pre>");
		for (final FTPFile file : files) {
			menu.append(buildItem(canonicalPath, file.getName(), directorySeparator));
			menu.append("<br>");
		}
		return menu.toString();
	}

//	private String buildItem(String src, String alt, String href) {
//		return "<img src=\"" + src + "\" alt=\"" + alt + "\"> <a href= \"/rest/api/ftp/" + href + "\">" + href + "</a><br>";
//	}
	
	private String buildItem(String canonicalPath, String name, String directorySeparator) {
		final String itemHref = canonicalPath + directorySeparator + name;
		return "<a href=\"" + itemHref+ "\">" + name + "</a>";
	}

}

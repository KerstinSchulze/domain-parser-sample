package src;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DomainLink {

	private Elements linkList;
	private String domainName;
	private int count;
	private ProtocolType protocol;

	public DomainLink(Element link, String domainName, ProtocolType protocol) {
		this.linkList = new Elements();
		this.linkList.add(link);
		this.domainName = domainName;
		this.count = 1;
		this.protocol = protocol;
	}

	public void update(Element link) {
		this.linkList.add(link);
		this.count++;
	}
	
	public String getDomainName() {
		return domainName;
	}

	public int getCount() {
		return count;
	}

	public ProtocolType getProtocol() {
		return protocol;
	}

}

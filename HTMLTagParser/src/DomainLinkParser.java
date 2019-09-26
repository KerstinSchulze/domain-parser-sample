package src;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class DomainLinkParser {
	
	protected List<DomainLink> parseLinks(Elements links, ProtocolType protocol) {
		Elements LinksByProtocol = this.findExternalLinksByProtocol(protocol, links);
		return this.filterUniqueDomainsForProtocolType(protocol, LinksByProtocol);
	}

	protected Elements findExternalLinksByProtocol(ProtocolType protocol, Elements links) {
		Elements reducedLinks = links.clone();
		reducedLinks.removeIf(element ->
			element.attr("href").contains(protocol.toString()) == false
		);
		return reducedLinks;
	}

	protected List<DomainLink> filterUniqueDomainsForProtocolType(ProtocolType protocol, Elements links) {
		List<DomainLink> result = new ArrayList<DomainLink>();

		links.forEach(newLink -> {
			String domain = parseLinkDomainName(protocol, newLink.attr("href"));
			boolean duplicationFound = false;
			// makes sure a exception is thrown once an edge case occurs there the link is no duplication
			// still the programm tries to handle it like a duplication
			int duplicationIndex = -1;
			
			for(int i = 0; i < result.size(); i++) {
			    if(this.checkDuplication(result.get(i), domain)) {
			    	duplicationFound = true;
			    	duplicationIndex = i;
			    }
			}
			
			if(duplicationFound){
				result.get(duplicationIndex).update(newLink);
			} 
				else {
				result.add(new DomainLink(newLink, domain, protocol));
			}

		});
		return result;
	}

	public String parseLinkDomainName(ProtocolType protocol, String linkadress) {
		int indexOdDomainend = linkadress.indexOf('/', protocol.toString().length());
		// if there is no internal routing it can be only the domain adress..
		if(indexOdDomainend == -1) indexOdDomainend = linkadress.length();
		String domain = linkadress.substring(protocol.toString().length(), indexOdDomainend);
		return domain;
	}

	public boolean checkDuplication(DomainLink link, String searchingDomainName) {
		if (link.getDomainName().equals(searchingDomainName)) {
			return true;
		}
		return false;
	}
	
}

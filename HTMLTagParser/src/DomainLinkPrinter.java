package src;

import java.util.List;

public class DomainLinkPrinter {

	public void printSearchResult(List<DomainLink> resultList) {
		System.out.println(" ");
		System.out.println("Result for " + resultList.get(0).getProtocol().toString());
		
		resultList.forEach(link -> {
			System.out.println("Found Domain Link... " + link.getDomainName());
			System.out.println("Count was... " + link.getCount());
		});
	}
	
}

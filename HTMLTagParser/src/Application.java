package src;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws IOException, NotYetImplementedException {
		System.out.println("Hello this will parse external domain links from a certain url-adress . . .");
		System.out.println("if you want to search on http://wawalove.pl for external domains press 'y'");
		System.out.println("else please enter a valid url");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		in.close();
		
		String url = "";
		if(s.equals("y")) {
			url = "http://wawalove.pl";
		} else {
			// TODO: validate url and parse the ProtocolType
			throw new NotYetImplementedException();
		}
		
		System.out.println("fetching data from . . ." + url);
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a");

		
		
		System.out.println("start parsing . . .");
		DomainLinkParser parser = new DomainLinkParser();
		DomainLinkPrinter printer = new DomainLinkPrinter();
		printer.printSearchResult(parser.parseLinks(links, ProtocolType.HTTP));
		printer.printSearchResult(parser.parseLinks(links, ProtocolType.HTTPS));

	}
	
}

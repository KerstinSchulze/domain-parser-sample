package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import src.DomainLink;
import src.DomainLinkParser;
import src.ProtocolType;

public class DomainLinkParserTest {
	
	// SUT
    private DomainLinkParser parser;
    
    private String savedDomainName = "myDomain.de";
    private DomainLink savedDomain;
    
	@Mock
	private Element element;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.parser = new DomainLinkParser();
		this.savedDomain = new DomainLink(element, savedDomainName, ProtocolType.HTTP);
	}

	@Test
	public void testDomainDuplication() {
		String newDomainName = "myDomain.de";
		boolean result = parser.checkDuplication(savedDomain, newDomainName);
		assertTrue("if domains match result should be true", result);
	}
	
	@Test
	public void testDomainDuplicationForNotMatchinDomains() {
		String newDomainName = "not-yet-saved-domain.de";
		boolean result = parser.checkDuplication(savedDomain, newDomainName);
		assertFalse("if domains dont match result should be false", result);
	}
	
	@Test
	public void parseLinkDomainNameTestForDomainwithSubRouting() {
		String linkadress = "http://myDomain.de/home";
		assertThat("when parsing a link i want it to return just the domain adresspart", 
				parser.parseLinkDomainName(ProtocolType.HTTP,linkadress),
				is(savedDomainName));
	}
	
	@Test
	public void parseLinkDomainNameTestForDomainWithoutSubRouting() {
		String linkadress = "http://myDomain.de";
		assertThat("when parsing a link with just the domainpart it should returnthat without the protocol part", 
				parser.parseLinkDomainName(ProtocolType.HTTP,linkadress),
				is(savedDomainName));
	}
	
	// TODO: extend Testcases to increase Coverage, also extend the blackboxtests to greyboxtests if possible ...

}

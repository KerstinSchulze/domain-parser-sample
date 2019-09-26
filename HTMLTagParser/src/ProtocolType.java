package src;

public enum ProtocolType {

	HTTP("http://"), HTTPS("https://");

	private String adressPraefix;

	private ProtocolType(String brand) {
		this.adressPraefix = brand;
	}

	@Override
	public String toString() {
		return adressPraefix;
	}

}

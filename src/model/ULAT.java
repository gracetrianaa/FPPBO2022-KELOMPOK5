package model;

public enum ULAT {
	
		GREEN("model/resources/ulat_hijau.png"),
		WHITE("model/resources/ulat_putih.png"),
		ORANGE("model/resources/ulat_orange.png"),
		YELLOW("model/resources/ulat_kuning.png");
	
	private String urlUlat;

	
	private ULAT(String urlUlat) {
		this.urlUlat = urlUlat;
	}

	public String getUrl() {
		return this.urlUlat;
	}
	
}
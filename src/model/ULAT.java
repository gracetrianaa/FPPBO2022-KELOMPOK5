package model;

public enum ULAT {
	
		GREEN("model/resources/ulat_hijau.png", "model/resources/kepala_hijau.png"),
		WHITE("model/resources/ulat_putih.png", "model/resources/kepala_putih.png"),
		ORANGE("model/resources/ulat_orange.png", "model/resources/kepala_orange.png"),
		YELLOW("model/resources/ulat_kuning.png", "model/resources/kepala_kuning.png");
	
	private String urlUlat;
	private String urlKepala;
	
	private ULAT(String urlUlat, String urlKepala) {
		this.urlUlat = urlUlat;
		this.urlKepala = urlKepala;
	}

	public String getUrl() {
		return this.urlUlat;
	}
	
	public String getKepala() {
		return this.urlKepala;
	}
	
	
}

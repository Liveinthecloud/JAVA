package almanc;

/**
 * TODO ������ʵ����
 * @author ��ˬ
 * @date 2017��11��18��
 * @version
 */
public class Almanac {
	private String solar;
	private String lunar;
	private String chineseAra;
	// ��������
	private String should;
	// ����������
	private String avoid;
	// ����
	private String festival;

	public String getSolar() {
		return solar;
	}

	public void setSolar(String date) {
		this.solar = date;
	}

	public String getLunar() {
		return lunar;
	}

	public void setLunar(String lunar) {
		this.lunar = lunar;
	}

	public String getChineseAra() {
		return chineseAra;
	}

	public void setChineseAra(String chineseAra) {
		this.chineseAra = chineseAra;
	}

	public String getAvoid() {
		return avoid;
	}

	public void setAvoid(String avoid) {
		this.avoid = avoid;
	}

	public String getShould() {
		return should;
	}

	public void setShould(String should) {
		this.should = should;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public Almanac(String solar, String lunar, String chineseAra, String should, String avoid, String festival) {
		this.solar = solar;
		this.lunar = lunar;
		this.chineseAra = chineseAra;
		this.should = should;
		this.avoid = avoid;
		this.festival = festival;
	}
}
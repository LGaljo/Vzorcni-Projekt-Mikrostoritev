package si.lg.vzorcni.entitete;

public class OdgovorDTO {
    private Double avg;
    private Vprasanje vprasanje;

    public OdgovorDTO() {
    }

    public OdgovorDTO(Double avg, Vprasanje vprasanje) {
        this.avg = avg;
        this.vprasanje = vprasanje;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Vprasanje getVprasanje() {
        return vprasanje;
    }

    public void setVprasanje(Vprasanje vprasanje) {
        this.vprasanje = vprasanje;
    }
}

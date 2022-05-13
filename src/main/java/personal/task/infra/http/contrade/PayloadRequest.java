package personal.task.infra.http.contrade;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "document")
@XmlType(propOrder = {"datatype", "period"})
public class PayloadRequest {

    private String datatype;
    private String period;

    public PayloadRequest() {
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "PayloadRequest{" +
                "datatype='" + datatype + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}

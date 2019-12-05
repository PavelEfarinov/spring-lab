package hello.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DotDTO {
    @NotNull(message = "X is required")
    @NotEmpty(message = "X is required")
    private List<Double> x;

    @NotNull(message = "Y is required")
    @Min(value = -5, message = "Y must be greater than -5")
    @Max(value = 3, message = "Y must be less than 3")
    private Double y;

    @NotNull(message = "R is required")
    @Min(value = 0, message = "R must be greater than 0")
    @Max(value = 2, message = "R must be less than 2")
    private Double r;

    private Boolean result;

    DotDTO() {
    }

    DotDTO(List<Double> x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public DotDTO(Dot d) {
        this.x = new ArrayList<>();
        this.x.add(d.getX());
        this.y = d.getY();
        this.r = d.getR();
        this.result = d.isResult();
    }

    public List<Double> getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public Boolean getResult() {
        return result;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}

package ar.edu.unq.desapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class OperatedVolumeReportDTO {
    private Long userId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String message;
    private double totalVolumeInDollars;
    private double totalVolumeInPesos;
    private List<OrderReportDetailDTO> orderDetails;

    public OperatedVolumeReportDTO(Long userId, LocalDate fromDate, LocalDate toDate, String message,
                                     double totalVolumeInDollars, double totalVolumeInPesos,
                                     List<OrderReportDetailDTO> orderDetails) {
        this.userId = userId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.message = message;
        this.totalVolumeInDollars = totalVolumeInDollars;
        this.totalVolumeInPesos = totalVolumeInPesos;
        this.orderDetails = orderDetails;
    }
}

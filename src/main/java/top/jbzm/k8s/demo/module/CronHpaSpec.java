package top.jbzm.k8s.demo.module;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CronHpaSpec {
  @SerializedName("excludeDates")
  private String excludeDates;

  @SerializedName("jobs")
  private List<CronHpaJobs> jobs;

  @SerializedName("scaleTargetRef")
  private ScaleTargetRef scaleTargetRef;
}

package top.jbzm.k8s.demo.module;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CronHpaJobs {
  @SerializedName("name")
  private String name;

  @SerializedName("runOnce")
  private boolean runOnce;

  @SerializedName("schedule")
  private String schedule;

  @SerializedName("targetSize")
  private int targetSize;
}

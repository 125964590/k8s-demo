package top.jbzm.k8s.demo.module;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScaleTargetRef {
  @SerializedName("apiVersion")
  private String apiVersion;

  @SerializedName("kind")
  private String kind;

  @SerializedName("name")
  private String name;
}

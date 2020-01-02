package top.jbzm.k8s.demo.module;

import com.google.gson.annotations.SerializedName;
import io.kubernetes.client.models.V1ObjectMeta;
import lombok.*;

/**
 * @author jbzm
 * @date 2019-11-25 14:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CronHpa {
  @SerializedName("apiVersion")
  private String apiVersion = null;

  @SerializedName("kind")
  private String kind = null;

  @SerializedName("metadata")
  private V1ObjectMeta metadata = null;

  @SerializedName("spec")
  private CronHpaSpec cronHpaSpec;
}

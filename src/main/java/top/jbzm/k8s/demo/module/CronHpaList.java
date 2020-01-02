package top.jbzm.k8s.demo.module;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbzm
 * @date 2019-11-25 15:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CronHpaList {
  @SerializedName("apiVersion")
  private String apiVersion = null;

  @SerializedName("items")
  private List<CronHpa> items = new ArrayList<CronHpa>();

  @SerializedName("kind")
  private String kind = null;
}

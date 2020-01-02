package top.jbzm.k8s.demo;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.models.V1DeleteOptions;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1Status;
import io.kubernetes.client.proto.Meta;
import io.kubernetes.client.util.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import top.jbzm.k8s.demo.module.*;
import top.jbzm.k8s.demo.util.CronHpaV1beta1Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jbzm
 * @date 2019-11-25 16:09
 */
public class CreatePodTest {
  private static ApiClient apiClient;

  @BeforeAll
  static void before() throws IOException {
    apiClient = Config.fromConfig("src/main/resources/local-config");
    Configuration.setDefaultApiClient(apiClient);
  }

  @Test
  public void GetCronHpaTest() throws ApiException {
    CronHpaV1beta1Api cronHpaV1beta1Api = new CronHpaV1beta1Api();
    CronHpaList cronHpaList =
        cronHpaV1beta1Api.listNamespacedCronHpa(
            "default", "true", null, null, null, null, null, null, null, null);
    System.out.println(cronHpaList.toString());
  }

  @Test
  public void PostCronHpaTest() throws ApiException {
    CronHpa cronHpa = new CronHpa();
    cronHpa.setApiVersion("autoscaling.alibabacloud.com/v1beta1");
    cronHpa.setKind("CronHorizontalPodAutoscaler");
    V1ObjectMeta v1ObjectMeta = new V1ObjectMeta();
    v1ObjectMeta.setName("cronhpa-test1");
    cronHpa.setMetadata(v1ObjectMeta);
    CronHpaSpec cronHpaSpec = new CronHpaSpec();
    CronHpaJobs cronHpaJobs = new CronHpaJobs();
    cronHpaJobs.setName("start");
    cronHpaJobs.setRunOnce(false);
    cronHpaJobs.setSchedule("0 */2 * * * *");
    cronHpaJobs.setTargetSize(3);
    CronHpaJobs cronHpaJobs1 = new CronHpaJobs();
    cronHpaJobs1.setName("start");
    cronHpaJobs1.setRunOnce(false);
    cronHpaJobs1.setSchedule("0 */2 * * * *");
    cronHpaJobs1.setTargetSize(3);
    List<CronHpaJobs> jobs = new ArrayList<CronHpaJobs>();
    jobs.add(cronHpaJobs);
    jobs.add(cronHpaJobs1);
    cronHpaSpec.setExcludeDates(null);
    cronHpaSpec.setJobs(jobs);
    ScaleTargetRef scaleTargetRef = new ScaleTargetRef();
    scaleTargetRef.setApiVersion("apps/v1");
    scaleTargetRef.setKind("Deployment");
    scaleTargetRef.setName("nginx-base");
    cronHpaSpec.setScaleTargetRef(scaleTargetRef);
    cronHpa.setCronHpaSpec(cronHpaSpec);

    CronHpaV1beta1Api cronHpaV1beta1Api = new CronHpaV1beta1Api();
    CronHpa cronhpa = cronHpaV1beta1Api.createNamespacedCronHpa("default", cronHpa, "true");

    System.out.println(cronhpa.toString());
  }

  @Test
  public void DeleteCronhpaTest() throws ApiException {
    V1DeleteOptions body = new V1DeleteOptions();
    body.setPropagationPolicy("Background");
    CronHpaV1beta1Api cronHpaV1beta1Api = new CronHpaV1beta1Api();
    V1Status aDefault =
        cronHpaV1beta1Api.deleteNamespacedCronHpa(
            "cronhpa-test", "default", body, null, null, null, null);

    System.out.println(aDefault);
  }

  @Test
  public void UpdateCronhpaTest() throws ApiException {
    CronHpaV1beta1Api cronHpaV1beta1Api = new CronHpaV1beta1Api();
    CronHpa cronHpa =
        cronHpaV1beta1Api.readNamespacedCronHpa("cronhpa-sample1", "default", null, null, null);
    cronHpa.getCronHpaSpec().getJobs().get(0).setTargetSize(1);
    String name = "cronhpa-sample1";
    CronHpa replaceCronHpa = cronHpaV1beta1Api.replaceCronHpa(name, cronHpa, null);

    System.out.println(replaceCronHpa);
  }
}

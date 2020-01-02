package top.jbzm.k8s.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1beta2Api;
import io.kubernetes.client.models.*;
import io.kubernetes.client.util.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author jbzm
 * @date 2019-12-03 18:36
 */
public class CopyDeployment {
  private static ApiClient apiClient;

  @BeforeAll
  static void before() throws IOException {
    apiClient = Config.fromConfig("src/main/resources/local-config");
    Configuration.setDefaultApiClient(apiClient);
  }

  @Test
  public void update() throws ApiException {
    AppsV1beta2Api appsV1beta2Api = new AppsV1beta2Api();
    V1beta2Deployment deployment =
        appsV1beta2Api.readNamespacedDeployment(
            "nginx-base", "godhand-paas-dev", "true", null, null);
    deployment.getSpec().setReplicas(2);
    appsV1beta2Api.replaceNamespacedDeployment(
        "nginx-base", "godhand-paas-dev", deployment, "true");
  }

  @Test
  public void copy() throws ApiException {
    AppsV1beta2Api appsV1beta2Api = new AppsV1beta2Api();
    V1beta2Deployment v1beta2Deployment =
        appsV1beta2Api.readNamespacedDeployment(
            "nginx-base", "godhand-paas-dev", "true", null, null);
    String s =
        v1beta2Deployment
            .getMetadata()
            .getAnnotations()
            .get("kubectl.kubernetes.io/last-applied-configuration");
    V1beta2Deployment deployment = JSON.parseObject(s, V1beta2Deployment.class);
    Map<String, String> cronHpaLabels = new HashMap<String, String>();
    cronHpaLabels.put("cronhpa", "paas");
    String cronHpaDeploymentName = "nginx-base-cron" + "-" + 12;
    String cronHpaName = "nginx-base-cron" + "-cronhpa-" + 12;
    HashMap<String, String> cronHpaTolerationsNoExecutex = new HashMap<String, String>();
    cronHpaTolerationsNoExecutex.put("cronhpa", "paas");
    Set<String> tolerationKeySets = cronHpaTolerationsNoExecutex.keySet();
    Set<String> labelKeySets = cronHpaLabels.keySet();
    deployment.getMetadata().setResourceVersion(null);

    deployment.getMetadata().setUid(null);
    deployment.getMetadata().getLabels().putAll(cronHpaLabels);
    deployment.getSpec().getTemplate().getMetadata().getLabels().putAll(cronHpaLabels);
    deployment.getMetadata().setName(cronHpaDeploymentName);
    deployment.getSpec().getTemplate().getMetadata().setName(cronHpaDeploymentName);
    //         set node affinity
    {
      V1Affinity v1Affinity =
          Optional.ofNullable(deployment.getSpec().getTemplate().getSpec().getAffinity())
              .orElse(new V1Affinity());
      V1NodeAffinity v1NodeAffinity =
          Optional.ofNullable(v1Affinity.getNodeAffinity()).orElse(new V1NodeAffinity());
      V1NodeSelector v1NodeSelector =
          Optional.ofNullable(v1NodeAffinity.getRequiredDuringSchedulingIgnoredDuringExecution())
              .orElse(new V1NodeSelector());
      V1NodeSelectorTerm v1NodeSelectorTerm = new V1NodeSelectorTerm();
      labelKeySets.forEach(
          k -> {
            V1NodeSelectorRequirement v1NodeSelectorRequirement = new V1NodeSelectorRequirement();
            v1NodeSelectorRequirement.setKey(k);
            v1NodeSelectorRequirement.setOperator("In");
            ArrayList<String> valueList = Lists.newArrayList(cronHpaTolerationsNoExecutex.get(k));
            v1NodeSelectorRequirement.setValues(valueList);
            v1NodeSelectorTerm.addMatchExpressionsItem(v1NodeSelectorRequirement);
          });
      v1NodeSelector.addNodeSelectorTermsItem(v1NodeSelectorTerm);
      v1NodeAffinity.setRequiredDuringSchedulingIgnoredDuringExecution(v1NodeSelector);
      v1Affinity.setNodeAffinity(v1NodeAffinity);
      deployment.getSpec().getTemplate().getSpec().setAffinity(v1Affinity);
    }
    {
      tolerationKeySets.forEach(
          k -> {
            V1Toleration v1Toleration = new V1Toleration();
            v1Toleration.setKey(k);
            v1Toleration.setValue(cronHpaTolerationsNoExecutex.get(k));
            v1Toleration.effect("NoExecute");
            deployment.getSpec().getTemplate().getSpec().addTolerationsItem(v1Toleration);
          });
    }
    //     init deployment replicas
    deployment.getSpec().setReplicas(2);
    appsV1beta2Api.createNamespacedDeployment("godhand-paas-dev", deployment, "true");
  }

  @Test
  public void getJson() throws ApiException {
    AppsV1beta2Api appsV1beta2Api = new AppsV1beta2Api();
    V1beta2Deployment deployment =
        appsV1beta2Api.readNamespacedDeployment(
            "nginx-base", "godhand-paas-dev", "true", null, null);
    String s =
        deployment
            .getMetadata()
            .getAnnotations()
            .get("kubectl.kubernetes.io/last-applied-configuration");
    V1beta2Deployment v1beta2Deployment = JSON.parseObject(s, V1beta2Deployment.class);
    System.out.println(v1beta2Deployment.toString());
  }
}

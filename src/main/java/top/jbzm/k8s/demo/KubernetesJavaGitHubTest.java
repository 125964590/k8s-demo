package top.jbzm.k8s.demo;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.ProtoClient;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.proto.Meta;
import io.kubernetes.client.proto.V1;
import io.kubernetes.client.util.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author jbzm
 * @date 2019-11-20 11:14
 */
public class KubernetesJavaGitHubTest {
  private static ApiClient apiClient;

  @BeforeAll
  static void before() throws IOException {
    apiClient = Config.fromConfig("src/main/resources/local-config");
    Configuration.setDefaultApiClient(apiClient);
  }

  @Test
  void testConnectionClient() throws Exception {
    CoreV1Api api = new CoreV1Api();
    V1PodList list =
        api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
    for (V1Pod item : list.getItems()) {
      System.out.println(item.getMetadata().getName());
    }
  }

  /**
   * 通过提供的原型方法操作对象的属性
   *
   * @throws Exception error
   */
  @Test
  void protoClientTest() throws Exception {
    ProtoClient pc = new ProtoClient(apiClient);
    ProtoClient.ObjectOrStatus<V1.PodList> list =
        pc.list(V1.PodList.newBuilder(), "/api/v1/namespaces/default/pods");

    if (list.object.getItemsCount() > 0) {
      V1.Pod p = list.object.getItems(0);
      System.out.println(p);
    }

    V1.Namespace namespace =
        V1.Namespace.newBuilder()
            .setMetadata(Meta.ObjectMeta.newBuilder().setName("test").build())
            .build();

    ProtoClient.ObjectOrStatus<V1.Namespace> ns =
        pc.create(namespace, "/api/v1/namespaces", "v1", "Namespace");
    System.out.println(ns);
    if (ns.object != null) {
      namespace =
          ns.object
              .toBuilder()
              .setSpec(V1.NamespaceSpec.newBuilder().addFinalizers("test").build())
              .build();
      // This is how you would update an object, but you can't actually
      // update namespaces, so this returns a 405
      ns = pc.update(namespace, "/api/v1/namespaces/test", "v1", "Namespace");
      System.out.println(ns.status);
    }

    ns = pc.delete(V1.Namespace.newBuilder(), "/api/v1/namespaces/test");
    System.out.println(ns);
  }


}

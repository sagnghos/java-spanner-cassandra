/*
Copyright 2025 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.google.cloud.spanner.adapter.configs;

import com.google.common.base.Strings;
import java.util.Map;

/** Represents the global client configurations loaded from a YAML file. */
public class GlobalClientConfigs {
  private final String spannerEndpoint;
  private final Boolean enableBuiltInMetrics;
  private final String healthCheckEndpoint;
  private final Boolean usePlainText;
  private final String clientCertificate;
  private final String clientKey;
  private final String experimentalHost;

  public GlobalClientConfigs(
      String spannerEndpoint,
      Boolean enableBuiltInMetrics,
      String healthCheckEndpoint,
      Boolean usePlainText,
      String experimentalHost,
      String clientCertificate,
      String clientKey) {
    this.spannerEndpoint = spannerEndpoint;
    this.enableBuiltInMetrics = enableBuiltInMetrics;
    this.healthCheckEndpoint = healthCheckEndpoint;
    this.usePlainText = usePlainText;
    this.experimentalHost = experimentalHost;
    this.clientCertificate = clientCertificate;
    this.clientKey = clientKey;
  }

  public GlobalClientConfigs(
      String spannerEndpoint, Boolean enableBuiltInMetrics, String healthCheckEndpoint) {
    this(spannerEndpoint, enableBuiltInMetrics, healthCheckEndpoint, null, null, null, null);
  }

  public GlobalClientConfigs(
      String spannerEndpoint,
      Boolean enableBuiltInMetrics,
      String healthCheckEndpoint,
      Boolean usePlainText) {
    this(
        spannerEndpoint, enableBuiltInMetrics, healthCheckEndpoint, usePlainText, null, null, null);
  }

  public GlobalClientConfigs(
      String spannerEndpoint,
      Boolean enableBuiltInMetrics,
      String healthCheckEndpoint,
      Boolean usePlainText,
      String experimentalHost) {
    this(
        spannerEndpoint,
        enableBuiltInMetrics,
        healthCheckEndpoint,
        usePlainText,
        experimentalHost,
        null,
        null);
  }

  public static GlobalClientConfigs fromMap(Map<String, Object> yamlMap) {
    String spannerEndpoint = (String) yamlMap.get("spannerEndpoint");
    Boolean enableBuiltInMetrics = (Boolean) yamlMap.get("enableBuiltInMetrics");
    String healthCheckEndpoint = (String) yamlMap.get("healthCheckEndpoint");
    Boolean usePlainText = (Boolean) yamlMap.get("usePlainText");
    String experimentalHost = (String) yamlMap.get("experimentalHost");
    String clientCertificate = (String) yamlMap.get("clientCertificate");
    String clientKey = (String) yamlMap.get("clientKey");
    if (!Strings.isNullOrEmpty(experimentalHost)) {
      spannerEndpoint = experimentalHost;
    }
    if (Strings.isNullOrEmpty(clientCertificate) || Strings.isNullOrEmpty(clientKey)) {
      return new GlobalClientConfigs(
          spannerEndpoint,
          enableBuiltInMetrics,
          healthCheckEndpoint,
          usePlainText,
          experimentalHost);
    }
    return new GlobalClientConfigs(
        spannerEndpoint,
        enableBuiltInMetrics,
        healthCheckEndpoint,
        usePlainText,
        experimentalHost,
        clientCertificate,
        clientKey);
  }

  public String getSpannerEndpoint() {
    return spannerEndpoint;
  }

  public Boolean getEnableBuiltInMetrics() {
    return enableBuiltInMetrics;
  }

  public String getHealthCheckEndpoint() {
    return healthCheckEndpoint;
  }

  public Boolean getUsePlainText() {
    return usePlainText;
  }

  public String getClientCertificate() {
    return clientCertificate;
  }

  public String getClientKey() {
    return clientKey;
  }

  public String getExperimentalHost() {
    return experimentalHost;
  }
}

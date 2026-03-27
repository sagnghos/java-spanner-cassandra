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
  private final String experimentalHostEndpoint;
  private final String clientCertPath;
  private final String clientKeyPath;

  public GlobalClientConfigs(
      String spannerEndpoint,
      Boolean enableBuiltInMetrics,
      String healthCheckEndpoint,
      Boolean usePlainText,
      String experimentalHostEndpoint,
      String clientCertPath,
      String clientKeyPath) {
    this.spannerEndpoint = spannerEndpoint;
    this.enableBuiltInMetrics = enableBuiltInMetrics;
    this.healthCheckEndpoint = healthCheckEndpoint;
    this.usePlainText = usePlainText;
    this.experimentalHostEndpoint = experimentalHostEndpoint;
    this.clientCertPath = clientCertPath;
    this.clientKeyPath = clientKeyPath;
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
      String experimentalHostEndpoint) {
    this(
        spannerEndpoint,
        enableBuiltInMetrics,
        healthCheckEndpoint,
        usePlainText,
        experimentalHostEndpoint,
        null,
        null);
  }

  public static GlobalClientConfigs fromMap(Map<String, Object> yamlMap) {
    String spannerEndpoint = (String) yamlMap.get("spannerEndpoint");
    Boolean enableBuiltInMetrics = (Boolean) yamlMap.get("enableBuiltInMetrics");
    String healthCheckEndpoint = (String) yamlMap.get("healthCheckEndpoint");
    Boolean usePlainText = (Boolean) yamlMap.get("usePlainText");
    String experimentalHostEndpoint = (String) yamlMap.get("experimentalHostEndpoint");
    String clientCertPath = (String) yamlMap.get("clientCertPath");
    String clientKeyPath = (String) yamlMap.get("clientKeyPath");
    if (Strings.isNullOrEmpty(clientCertPath) || Strings.isNullOrEmpty(clientKeyPath)) {
      return new GlobalClientConfigs(
          spannerEndpoint,
          enableBuiltInMetrics,
          healthCheckEndpoint,
          usePlainText,
          experimentalHostEndpoint);
    }
    return new GlobalClientConfigs(
        spannerEndpoint,
        enableBuiltInMetrics,
        healthCheckEndpoint,
        usePlainText,
        experimentalHostEndpoint,
        clientCertPath,
        clientKeyPath);
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

  public String getExperimentalHostEndpoint() {
    return experimentalHostEndpoint;
  }

  public String getClientCertPath() {
    return clientCertPath;
  }

  public String getClientKeyPath() {
    return clientKeyPath;
  }
}

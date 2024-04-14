import React, { useEffect } from "react";
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import { WebView } from "react-native-webview";
import { NativeModules } from "react-native";

const HelloWorld = () => {
  useEffect(() => {
    NativeModules.IntentModule.dataToJS(
      (data) => {
        console.log(data);
      },
      (err) => {
        console.log(err);
      }
    );
  }, []);
  return (
    <View style={styles.container}>
      <WebView
        source={{
          uri: "https://reactnative.cn/docs/integration-with-existing-apps",
        }}
        style={{ marginTop: 20 }}
      />
    </View>
  );
};
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
  },
  hello: {
    fontSize: 20,
    textAlign: "center",
    margin: 10,
  },
});

AppRegistry.registerComponent("MyReactNativeApp", () => HelloWorld);

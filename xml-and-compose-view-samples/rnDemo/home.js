import React from "react";
import { AppRegistry, View, Text, Button } from "react-native";
import { NativeModules } from "react-native";

const App = () => {
  return (
    <View style={{ flex: 1 }}>
      <Text>rn base page</Text>
      <Button
        title="Click me"
        onPress={() => {
          NativeModules.IntentModule.startActivityFromJS(
            "MyReactNativeApp",
            "index.android.bundle",
            true
          );
        }}
      />
    </View>
  );
};

AppRegistry.registerComponent("home", () => App);

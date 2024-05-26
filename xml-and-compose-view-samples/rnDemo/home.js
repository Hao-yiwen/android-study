import React from "react";
import { AppRegistry, View, Text } from "react-native";

const App = () => {
  return (
    <View style={{ flex: 1 }}>
      <Text>rn base page</Text>
    </View>
  );
};

AppRegistry.registerComponent("home", () => App);

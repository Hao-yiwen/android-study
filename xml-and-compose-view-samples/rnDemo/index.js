import React, { useEffect } from "react";
import { AppRegistry, StyleSheet, Text, View } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Home from "./src/pages/Home";
import WebViewPage from "./src/pages/WebViewPage";

const Stack = createNativeStackNavigator();

const isHermes = () => !!global.HermesInternal;
console.log("isHermes", isHermes());

function App(props) {
  console.log("AppProps", props);
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="WebView" component={WebViewPage} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

AppRegistry.registerComponent("MyReactNativeApp", () => App);

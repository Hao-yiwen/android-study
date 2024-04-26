import React from "react";
import { View, Text, ScrollView, Button } from "react-native";

const Index = ({navigation}) => {
  return (
    <View style={{ flex: 1, backgroundColor: "red", alignItems: "center" }}>
      <Text>Home</Text>
      <ScrollView>
        <Button
          title="Go to WebView"
          onPress={() => navigation.navigate("WebView")}
        />
      </ScrollView>
    </View>
  );
};

export default Index;
